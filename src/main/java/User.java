import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author Yue_
 * @create 2020-12-27-22:04
 */
@Data
@AllArgsConstructor
public class User {

    private Integer id;
    private String userName;
    private int age;

    public User(String userName, int age) {
        this.userName = userName;
        this.age = age;
    }
}
