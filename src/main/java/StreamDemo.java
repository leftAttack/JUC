import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * @author Yue_
 * @create 2020-12-27-21:41
 * 题目：请按照给出数据，找出同时满足
 *      偶数ID且年龄大于24且用户名转为大写且用户名字母倒排序
 *      最后只输出一个用户名字
 */
public class StreamDemo {

    public static void main(String[] args) {

        User u1 = new User(11,"a",23);
        User u2 = new User(12,"b",24);
        User u3 = new User(13,"c",22);
        User u4 = new User(14,"d",28);
        User u5 = new User(16,"e",26);

        List<User> list = Arrays.asList(u1,u2,u3,u4,u5);

        list.stream().filter(u -> {
            return u.getId() % 2 == 0;
        }).filter(u -> {
            return u.getAge() > 24;
        }).map(u -> {
            return u.getUserName().toUpperCase();
        }).sorted((n1,n2) -> {
            return n2.compareTo(n1);
            //如果指定的数与参数相等返回 0
            //如果指定的数小于参数返回 -1
            //如果指定的数大于参数返回 1
        }).limit(1).forEach(System.out::println);
    }
}
