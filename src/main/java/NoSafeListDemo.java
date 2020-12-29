import org.junit.Test;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @author Yue_
 * @create 2020-12-26-8:56
 */
public class NoSafeListDemo {

    public static void main(String[] args) {
        //ArrayList<String> list = new ArrayList<>();
        //Vector<String> list = new Vector<>();
        //List<String> list = Collections.synchronizedList(new ArrayList<String>());
        CopyOnWriteArrayList<String> list = new CopyOnWriteArrayList<String>();

        for (int i = 1; i <= 30; i++) {
            new Thread(() -> {
                list.add(UUID.randomUUID().toString().substring(0, 8));
                System.out.println(list);
            }, String.valueOf(i)).start();
        }
    }
}

class NoSafeSetDemo{

    public static void main(String[] args) {

        //HashSet<String> set = new HashSet<>();
        //Set<String> set = Collections.synchronizedSet(new HashSet<String>());
        CopyOnWriteArraySet<String> set = new CopyOnWriteArraySet<>();

        for (int i = 1; i <= 30; i++) {
            new Thread(() -> {
                set.add(UUID.randomUUID().toString().substring(0, 8));
                System.out.println(set);
            }, String.valueOf(i)).start();
        }

    }
}

class NoSafeMapDemo{

    public static void main(String[] args) {

        //HashMap<String, String> map = new HashMap<String, String>();
        //Map<String, String> map = Collections.synchronizedMap(new HashMap<String, String>());
        ConcurrentHashMap<String, String> map = new ConcurrentHashMap<>();

        for (int i = 1; i <= 30; i++) {
            new Thread(() -> {
                map.put(Thread.currentThread().getName(),UUID.randomUUID().toString().substring(0, 8));
                System.out.println(map);
            }, String.valueOf(i)).start();
        }

    }
}

