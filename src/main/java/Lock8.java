import java.util.concurrent.TimeUnit;

/**
 * @author Yue_
 * @create 2020-12-25-21:11
 *  * @Description: 8锁
 *  *
 *  1 标准访问，先打印短信还是邮件
 *  2 停4秒在短信方法内，先打印短信还是邮件
 *  3 新增普通的hello方法，是先打短信还是hello
 *  4 现在有两部手机，先打印短信还是邮件
 *  5 两个静态同步方法，1部手机，先打印短信还是邮件
 *  6 两个静态同步方法，2部手机，先打印短信还是邮件
 *  7 1个静态同步方法,1个普通同步方法，1部手机，先打印短信还是邮件
 *  8 1个静态同步方法,1个普通同步方法，2部手机，先打印短信还是邮件
 *  *
 *  *
 */
public class Lock8 {

    public static void main(String[] args) throws InterruptedException {

        Phone phone1 = new Phone();
        Phone phone2 = new Phone();

        new Thread(() -> {
            try {
                phone1.sendEmail();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "AA").start();

        Thread.sleep(100);

        new Thread(() -> {
            try {
                phone1.sendSMS();
                //phone1.getHello();
                //phone2.sendSMS();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "BB").start();

    }
}

class Phone{

    public synchronized void sendEmail() throws Exception
    {
        TimeUnit.SECONDS.sleep(4);
        System.out.println("------sendEmail");
    }

    public  synchronized void sendSMS() throws Exception
    {

        System.out.println("------sendSMS");
    }

    public void getHello()
    {
        System.out.println("------getHello");
    }

}
