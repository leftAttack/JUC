import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

/**
 * @author Yue_
 * @create 2020-12-27-21:41
 */
public class ForkJoinDemo {

    public static void main(String[] args) throws Exception {

        ForkJoinPool forkJoinPool = new ForkJoinPool();
        try {
            MyTask myTask = new MyTask(0, 100);
            ForkJoinTask<Integer> forkJoinTask = forkJoinPool.submit(myTask);
            System.out.println(forkJoinTask.get());
        }finally {
            forkJoinPool.shutdown();
        }
    }
}

class MyTask extends RecursiveTask<Integer>{

    private static final Integer ADJUST_VALUE = 10;
    private int begin;
    private int end;
    private int result;

    public MyTask(int begin, int end) {
        this.begin = begin;
        this.end = end;
    }

    @Override
    protected Integer compute() {
        if((end - begin) <= ADJUST_VALUE){
            for (int i = begin;i <= end;i ++){
                result = result + i;
            }
        }else{
            int middle = (begin + end)/2;
            MyTask task01 = new MyTask(begin,middle);
            MyTask task02 = new MyTask(middle+1,end);
            task01.fork();
            task02.fork();
            result =  task01.join() + task02.join();
        }
        return result;
    }
}