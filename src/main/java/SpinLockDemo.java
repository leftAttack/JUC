import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author Yue_
 * @create 2020-12-29-9:57
 */
public class SpinLockDemo {

    AtomicReference<Thread> atomicReference  = new AtomicReference<Thread>();

    public void mylock(){

        Thread thread = Thread.currentThread();
        System.out.println(thread.getName() + "\t come in");
        while (!atomicReference.compareAndSet(null,thread)){

        }
    }

    public void myunlock(){

        Thread thread = Thread.currentThread();
        atomicReference.compareAndSet(thread,null);
        System.out.println(thread.getName() + "\t unlock ok");
    }

    public static void main(String[] args) {

        SpinLockDemo spinLockDemo = new SpinLockDemo();

        new Thread(() -> {
            spinLockDemo.mylock();
            System.out.println("线程A开始工作");
            try { TimeUnit.SECONDS.sleep(5); } catch (InterruptedException e) { e.printStackTrace(); }
            System.out.println("线程A结束工作");
            spinLockDemo.myunlock();
        },"A").start();

        new Thread(() -> {
            spinLockDemo.mylock();
            System.out.println("线程B开始工作");
            System.out.println("线程B结束工作");
            spinLockDemo.myunlock();
        },"B").start();
    }
}
