import lombok.Data;

/**
 * @author Yue_
 * @create 2020-12-26-18:18
 */
@Data
public class Person{

    private Integer id;
    private String personName;

    public Person(String personName){
        this.personName = personName;
    }
}