import java.util.concurrent.atomic.AtomicReference;

/**
 * @author Yue_
 * @create 2020-12-28-22:27
 */
public class AtomicReferenceDemo {

    public static void main(String[] args) {

        User zhangsan = new User("zhangsan", 18);
        User lisi = new User("lisi",20);

        AtomicReference<User> userAtomicReference = new AtomicReference<>();
        userAtomicReference.set(zhangsan);

        System.out.println(userAtomicReference.compareAndSet(zhangsan, lisi) + "\t" + userAtomicReference.get());
        System.out.println(userAtomicReference.compareAndSet(zhangsan, lisi) + "\t" + userAtomicReference.get());

    }
}