import lombok.Data;

/**
 * @author Yue_
 * @create 2020-12-26-16:59
 */

//ctrl + F12 / Alt + 7 查看类中所有属性和方法
public class TestTransferValue {

    public void changValue(Integer age){
        age = 30;
    }

    public void changValue(Person person){
        person.setPersonName("xxx");
    }

    public void changValue(String str){
        str = "xxx";
    }

    public static void main(String[] args) {

        TestTransferValue testTransferValue = new TestTransferValue();
        Integer age = 20;
        testTransferValue.changValue(age);
        System.out.println("age----" + age);  //20
        Person person = new Person("abc");
        testTransferValue.changValue(person);
        System.out.println("person----" + person.getPersonName()); //xxx
        String str = "abc";
        testTransferValue.changValue(str);
        System.out.println("str----" + str); //abc
    }
}

