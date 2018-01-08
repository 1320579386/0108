import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author SZA
 * @date 2018/01/08
 */
public class Menu {
    Scanner scanner = new Scanner(System.in);
    Coach coach = new Coach();
    Truck truck = new Truck();
    private static Pattern pattern = Pattern.compile("[0-9]*");

    public void main() {
        System.out.println("欢迎使用笨鸟租车系统\n" +
                "1.查看车辆信息\n" +
                "2.确认租车\n" +
                "3.退出系统");
        String option = scanner.next();
        if (option.equals("1")) {
            browse();
        } else if (option.equals("2")) {
            confirm();
        } else if (option.equals("3")) {
            System.exit(0);
        } else {
            System.out.println("请输入正确的指令\n");
            main();
        }
    }

    public void browse() {
        System.out.println("客车信息\n" +
                "价格：" + coach.price + "元/天    载客量：" + coach.people + "人/车\n" +
                "库存" + coach.quantity + "辆\n\n" +
                "货车信息\n" +
                "价格：" + truck.price + "元/天    载重量：" + truck.weight + "吨/车\n" +
                "库存" + truck.quantity + "辆\n");
        try {
            Thread.sleep(2000);
        } catch (Exception e) {

        }
        main();
    }

    public void confirm() {
        System.out.println("请输入车辆类型\n" +
                "1.客车（剩余" + coach.quantity + "辆)\n" +
                "2.货车（剩余" + truck.quantity + "辆)\n" +
                "3.返回主菜单");
        String option = scanner.next();
        if (option.equals("1")) {
            check(coach.quantity);
        } else if (option.equals("2")) {
            check(truck.quantity);
        } else if (option.equals("3")) {
            main();
        } else {
            System.out.println("请输入正确的指令\n");
            confirm();
        }
    }

    public void check(int left) {
        System.out.println("请输入租车数量");
        String Quantity = scanner.next();
        if (judge(Quantity)) {
            int quantity = Integer.valueOf(Quantity);
            if (left >= quantity) {
                pay(quantity);
            } else {
                System.out.println("车辆库存不足，请减少数量\n");
                try {
                    Thread.sleep(1000);
                } catch (Exception e) {

                }
                confirm();
            }
        } else {
            System.out.println("请输入正整数\n");
            confirm();
        }
    }

    public void pay(int amount) {
        left = left - amount;
        System.out.println("请输入租车天数(整数)");
        int days = scanner.nextInt();
        System.out.println("租车成功，共计金额" + amount * days * coach.price + "元");
        try {
            Thread.sleep(1000);
        } catch (Exception e) {

        }
        main();
    }

    public boolean judge(String str) {
        Matcher m = pattern.matcher(str);
        return m.matches();
    }
}