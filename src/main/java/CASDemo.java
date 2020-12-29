import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Yue_
 * @create 2020-12-28-16:45
 */
public class CASDemo {

    public static void main(String[] args) {

        AtomicInteger atomicInteger = new AtomicInteger(5);
        System.out.println(atomicInteger.compareAndSet(5, 2048)  + "\t" +  atomicInteger.get());
        System.out.println(atomicInteger.compareAndSet(5, 1024)  + "\t" +  atomicInteger.get());
    }
}
