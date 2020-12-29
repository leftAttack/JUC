import java.util.UUID;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

/**
 * @author Yue_
 * @create 2020-12-26-12:53
 */
public class CallableDemo {

    public static void main(String[] args) throws Exception {

        //FutureTask<Integer> futureTask = new FutureTask(new MyThread());
        FutureTask<Integer> futureTask = new FutureTask(() -> {
            System.out.println(Thread.currentThread().getName() + "  come in callable");
            TimeUnit.SECONDS.sleep(4);
            return 1024;
        });
        FutureTask<Integer> futureTask2 = new FutureTask(() -> {
            System.out.println(Thread.currentThread().getName() + "  come in callable");
            TimeUnit.SECONDS.sleep(4);
            return 2048;
        });

        new Thread(futureTask, "zhang3").start();
        new Thread(futureTask2, "li4").start();

        //System.out.println(futureTask.get());
        //System.out.println(futureTask2.get());
        //1、一般放在程序后面，直接获取结果
        //2、只会计算结果一次

        System.out.println("***main方法执行结束");
        System.out.println(futureTask.get());
        //System.out.println(futureTask2.get());
        System.out.println(Thread.currentThread().getName() + " come over");
    }
}

class MyCallable implements Callable<String> {

    @Override
    public String call() throws Exception {
        System.out.println("进入了Callable方法");
        return UUID.randomUUID().toString();
    }
}
