import java.util.HashMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author Yue_
 * @create 2020-12-26-16:22
 */

//例子:缓存读写
public class ReadWriteLockDemo {

    public static void main(String[] args) {

        MyCache myCache = new MyCache();

        for(int i = 1;i <= 5;i ++){
            final int num = i;
            new Thread(() -> {
                try {
                    myCache.write(num+"",num+"");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            },String.valueOf(i)).start();
        }

        for(int i = 1;i <= 5;i ++){
            final int num = i;
            new Thread(() -> {
                try {
                    myCache.read(num+"");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            },String.valueOf(i)).start();
        }
    }
}

class MyCache{

    private HashMap<String,Object> hashMap = new HashMap<>();
    private ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    public void write(String key,Object value) throws InterruptedException {
        readWriteLock.writeLock().lock();
        try {
            System.out.println("开始写入数据");
            TimeUnit.SECONDS.sleep(3);
            hashMap.put(key,value);
            System.out.println("写入数据完成" + key);
        }finally {
            readWriteLock.writeLock().unlock();
        }
    }

    public void read(String key) throws InterruptedException {
        readWriteLock.readLock().lock();
        try {
            System.out.println("开始读取数据");
            TimeUnit.SECONDS.sleep(3);
            Object value = hashMap.get(key);
            System.out.println("读取数据完成" + value);
        }finally {
            readWriteLock.readLock().unlock();
        }
    }
}
