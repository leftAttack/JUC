import java.util.concurrent.CountDownLatch;

/**
 * @author Yue_
 * @create 2020-12-26-15:57
 */

//例子:自习室锁门
public class CountDownLatchDemo {

    public static void main(String[] args) throws InterruptedException {

        CountDownLatch countDownLatch = new CountDownLatch(6);
        for (int i = 1; i <= 6; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + "  离开自习室");
                countDownLatch.countDown();
            }, String.valueOf(i)).start();
        }
        countDownLatch.await();
        System.out.println(Thread.currentThread().getName() + "  班长锁门");
    }
}

