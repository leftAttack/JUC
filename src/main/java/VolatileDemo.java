import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Yue_
 * @create 2020-12-28-10:37
 */

/**
 * 验证volatile的可见性
 * 1.当number未被volatile修饰时，new Thread将number值改为60，但main线程并不知道，会一直在循环中出不来
 * 2.当number使用volatile修饰，new Thread改变number值后，会通知main线程主内存的值已被修改，结束任务。体现了可见性
 *
 * 验证volatile不保证原子性
 * 1.原子性是指，某个线程在执行某项业务时，中间不可被加塞或分割，需要整体完整。要么同时成功，要么同时失败
 *
 * 如何解决呢？
 * 1.使用synchronize
 * 2.使用AtomicInteger
 *
 */
public class VolatileDemo {

    public static void main(String[] args) {

        Mydata mydata = new Mydata();

        for(int i = 1;i <= 20;i ++){
            new Thread(() -> {
                for (int j = 0; j < 1000;j ++){
                    mydata.addPlusPlus();
                    mydata.atomicAddPlusPlus();
                }
            },String.valueOf(i)).start();
        }

        while (Thread.activeCount() > 2){
            Thread.yield();
        }

        System.out.println(mydata.num);
        System.out.println(mydata.atomicInteger);
    }

    public void seeOk() {

        Mydata mydata = new Mydata();

        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "\t come in");
            try { TimeUnit.SECONDS.sleep(3); } catch (InterruptedException e) { e.printStackTrace(); }
            mydata.addTo60();
            System.out.println(Thread.currentThread().getName() + "\t updata ok value = " + mydata.num);
        },"AAA").start();

        while (mydata.num == 0){

        }

        System.out.println(Thread.currentThread().getName() + "\t finish");

    }
}

class Mydata{

    //int num = 0;
    volatile int num = 0;

    public void addTo60() {
        this.num = 60;
    }

    public void addPlusPlus(){
        num ++;
    }

    AtomicInteger atomicInteger = new AtomicInteger();

    public void atomicAddPlusPlus(){
        atomicInteger.getAndIncrement();
    }

}
