import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Yue_
 * @create 2020-12-25-17:01
 */

//口诀:标志位
//多线程之间按顺序调用，实现A->B->C
public class ThreadOrderAccess {

    public static void main(String[] args) {

        ShareResource shareResource = new ShareResource();

        new Thread(() -> {
            for(int i = 0; i < 10 ; i++)
            try {
                shareResource.A();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"A").start();

        new Thread(() -> {
            for(int i = 0; i < 10 ; i++)
                try {
                    shareResource.B();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
        },"B").start();

        new Thread(() -> {
            for(int i = 0; i < 10 ; i++)
                try {
                    shareResource.C();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
        },"C").start();
    }
}

class ShareResource{

    //标志位
    private int num = 1;
    private Lock lock = new ReentrantLock();
    private Condition condition1 = lock.newCondition();
    private Condition condition2 = lock.newCondition();
    private Condition condition3 = lock.newCondition();

    public void A() throws InterruptedException {
        lock.lock();
        try {
            //判断
            while(num != 1){
                condition1.await();
            }
            //干活
            System.out.println("我是A");
            //唤醒
            num = 2;
            condition2.signal();
        }finally {
            lock.unlock();
        }
    }

    public void B() throws InterruptedException {
        lock.lock();
        try {
            //判断
            while (num != 2) {
                condition2.await();
            }
            //干活
            System.out.println("我是B");
            //唤醒
            num = 3;
            condition3.signal();
        }finally {
            lock.unlock();
        }
    }

    public void C() throws InterruptedException {
        lock.lock();
        try {
            //判断
            while (num != 3) {
                condition3.await();
            }
            //干活
            System.out.println("我是C");
            //唤醒
            num = 1;
            condition1.signal();
        }finally {
            lock.unlock();
        }
    }
}
