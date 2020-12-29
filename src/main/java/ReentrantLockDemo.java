import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Yue_
 * @create 2020-12-29-9:57
 */
public class ReentrantLockDemo {

    public static void main(String[] args) throws InterruptedException {

        Phone1 phone = new Phone1();

        new Thread(() -> {
            try {
                phone.sendSMS();
            } catch (Exception e) {
                e.printStackTrace();
            }
        },"AAA").start();

        TimeUnit.SECONDS.sleep(1);

        new Thread(() -> {
            phone.sendSMS2();
        },"BBB").start();
    }
}

class Phone1{

    public synchronized void sendSMS(){
        System.out.println(Thread.currentThread().getName() + "\t发送SMS");
        sendEmail();
    }

    public synchronized void sendEmail(){
        System.out.println(Thread.currentThread().getName() + "\t发送Email");
    }

    Lock lock = new ReentrantLock();

    public void sendSMS2(){
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + "\t发送SMS");
            sendEmail2();
        }finally {
            lock.unlock();
        }
    }

    public void sendEmail2(){
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + "\t发送Email");
        }finally {
            lock.unlock();
        }
    }
}
