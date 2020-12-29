import java.util.concurrent.*;

/**
 * @author Yue_
 * @create 2020-12-26-23:37
 */
public class ThreadPoolDemo {

    public static void main(String[] args) {

        //ExecutorService threadPool = Executors.newFixedThreadPool(5);  //一个银行网点，5个受理业务的窗口
        //ExecutorService threadPool = Executors.newSingleThreadExecutor(); //一个银行网点，1个受理业务的窗口
        //ExecutorService threadPool = Executors.newCachedThreadPool(); //一个银行网点，可扩展受理业务的窗口
        ExecutorService threadPool = getThreadPool(); //自定义方法

        try {
            for(int i = 1;i <= 13;i ++){
                threadPool.execute(() -> {
                    System.out.println(Thread.currentThread().getName()+"\t 办理业务");
                    try { TimeUnit.SECONDS.sleep(1); } catch (InterruptedException e) { e.printStackTrace(); }
                });
            }
        }catch (Exception e){
            e.printStackTrace();
        } finally {
            threadPool.shutdown();
        }

    }

    public static ExecutorService getThreadPool(){
        //在CPU密集型的程序 线程池最大连接数最好设置为当前服务器的最大线程数+1
        int processors = Runtime.getRuntime().availableProcessors(); //当前电脑的线程数  如:我的电脑4核八线程
        ExecutorService threadPool = new ThreadPoolExecutor(
                2,
                processors+1,
                2L,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<Runnable>(3),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.DiscardPolicy());
        return threadPool;
    }
}
