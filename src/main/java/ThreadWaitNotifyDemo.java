/**
 * @author Yue_
 * @create 2020-12-25-16:13
 */

//口诀:判断 干活 唤醒
public class ThreadWaitNotifyDemo {

    public static void main(String[] args) {

        AirConditioner airConditioner = new AirConditioner();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    airConditioner.increment();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "A").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    airConditioner.increment();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "B").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    airConditioner.decrement();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "C").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    airConditioner.decrement();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "D").start();
    }
}

class AirConditioner {

    private int num = 0;

//    public synchronized void increment() throws InterruptedException {
//        //判断
//        if (num != 0) {
//            this.wait();
//        }
//        //干活
//        num++;
//        System.out.println(num + "..." + Thread.currentThread().getName());
//        //唤醒
//        this.notifyAll();
//    }
//
//    public synchronized void decrement() throws InterruptedException {
//        //判断
//        if (num == 0) {
//            this.wait();
//        }
//        //干活
//        num--;
//        System.out.println(num + "..." + Thread.currentThread().getName());
//        //唤醒
//        this.notifyAll();
//    }

    public synchronized void increment() throws InterruptedException {
        //判断
        //口诀:多线程交互中，必须要防止多线程的虚假唤醒，也即（判断只用while，不能用if）
        while (num != 0) {
            System.out.println(Thread.currentThread().getName() + "调用wait方法");
            this.wait();
        }
        //干活
        num++;
        System.out.println(num + "..." + Thread.currentThread().getName());
        //唤醒
        //System.out.println(Thread.currentThread().getName() + "调用notifyAll方法");
        this.notifyAll();
    }

    public synchronized void decrement() throws InterruptedException {
        //判断
        while (num == 0) {
            System.out.println(Thread.currentThread().getName() + "调用wait方法");
            this.wait();
        }
        //干活
        num--;
        System.out.println(num + "..." + Thread.currentThread().getName());
        //唤醒
        //System.out.println(Thread.currentThread().getName() + "调用notifyAll方法");
        this.notifyAll();
    }
}
