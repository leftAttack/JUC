import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @author Yue_
 * @create 2020-12-26-16:01
 */

//例子:集齐七龙珠
public class CyclicBarrierDemo {

    public static void main(String[] args) {

        CyclicBarrier cyclicBarrier = new CyclicBarrier(7, () -> {
            System.out.println(Thread.currentThread().getName() + "  召唤神龙");
        });

        for(int i = 1; i <= 7; i++){
            final int num = i;
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + "  收集到" + num + "龙珠");
                try {
                    cyclicBarrier.await();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            },String.valueOf(i)).start();
        }
    }
}
