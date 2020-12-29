import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Yue_
 * @create 2020-12-25-14:21
 */

//口诀:线程 操作 资源类！
public class TicketDemo {

    public static void main(String[] args) {

        final Ticket ticket = new Ticket();

        //1.初始版
//        new Thread(new Runnable() {
//            public void run() {
//                for(int i = 0; i < 40 ; i++) {
//                    ticket.saleTicket();
//                }
//            }
//        }, "A").start();
//
//        new Thread(new Runnable() {
//            public void run() {
//                for(int i = 0; i < 40 ; i++) {
//                    ticket.saleTicket();
//                }
//            }
//        }, "B").start();

        //2.lambda表达式版
        new Thread(() -> { for(int i = 0; i < 40 ; i++)  ticket.saleTicket(); }, "A").start();
        new Thread(() -> { for(int i = 0; i < 40 ; i++)  ticket.saleTicket(); }, "B").start();

    }
}

class Ticket{

    private int num = 30;

    //1.同步方法
//    public synchronized void saleTicket(){
//        if(num > 0) {
//            num--;
//            System.out.println("当前剩余票数" + num + "..." +  Thread.currentThread().getName());
//        }
//    }

    //2.Lock方法
    Lock lock = new ReentrantLock();
    public void saleTicket(){
        lock.lock();
        try {
            if(num > 0) {
                num--;
                System.out.println("当前剩余票数" + num + "..." +  Thread.currentThread().getName());
            }
        }finally {
            lock.unlock();
        }
    }

}


