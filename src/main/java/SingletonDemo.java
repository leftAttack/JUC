/**
 * @author Yue_
 * @create 2020-12-28-15:21
 */
public class SingletonDemo {

    public static void main(String[] args) {

        //单机版单例模式(只有main线程时)
//        System.out.println(Singleton.getInstance() == Singleton.getInstance());
//        System.out.println(Singleton.getInstance() == Singleton.getInstance());
//        System.out.println(Singleton.getInstance() == Singleton.getInstance());

        //多线程的单例模式
        for (int i = 1; i <= 10; i++) {
            new Thread(() -> {
                Singleton.getInstance();
            }, String.valueOf(i)).start();
        }
    }
}

class Singleton {

    private volatile static Singleton instance = null;

    private Singleton() {
        System.out.println(Thread.currentThread().getName() + "构造方法");
    }

    //使用DCL(Double Check Lock双端检锁机制)
    public static Singleton getInstance() {
        if (instance == null) {
            synchronized (Singleton.class) {
                if (instance == null) {
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }

//    public static Singleton getInstance() {
//        if (instance == null) {
//            instance = new Singleton();
//        }
//        return instance;
//    }
}
