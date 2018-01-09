import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author SZA
 * @date 2018/01/08
 */
public class Menu {
    private Scanner scanner = new Scanner(System.in, "UTF-8");
    private Coach coach = new Coach();
    private Truck truck = new Truck();
    private static Pattern pattern = Pattern.compile("[0-9]*");

    public void main() {
        System.out.println("欢迎使用笨鸟租车系统\n" +
                "1.查看车辆信息\n" +
                "2.确认租车\n" +
                "3.退出系统");
        String mainOption = scanner.next();
        if (judge(mainOption)) {
            int option = Integer.parseInt(mainOption);
            switch (option) {
                case 1:
                    browse();
                    break;
                case 2:
                    confirm();
                    break;
                case 3:
                    System.exit(0);
                    break;
                default:
                    System.out.println("请输入正确的指令\n");
                    main();
                    break;
            }
        } else {
            System.out.println("请输入正确的指令\n");
            main();
        }
    }

    private void browse() {
        System.out.println("客车信息\n" +
                "价格：" + coach.price + "元/天    载客量：" + coach.people + "人/车\n" +
                "库存" + coach.quantity + "辆\n\n" +
                "货车信息\n" +
                "价格：" + truck.price + "元/天    载重量：" + truck.weight + "吨/车\n" +
                "库存" + truck.quantity + "辆\n");
        try {
            Thread.sleep(2000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        main();
    }

    private void confirm() {
        System.out.println("请输入车辆类型\n" +
                "1.客车（剩余" + coach.quantity + "辆)\n" +
                "2.货车（剩余" + truck.quantity + "辆)\n" +
                "3.返回主菜单");
        String confirmQuantity;
        String confirmOption = scanner.next();
        if (judge(confirmOption)) {
            int option = Integer.parseInt(confirmOption);
            switch (option) {
                case 1:
                    System.out.println("请输入客车数量");
                    confirmQuantity = scanner.next();
                    if (judge(confirmQuantity)) {
                        int quantity = Integer.parseInt(confirmQuantity);
                        if (coach.quantity >= quantity) {
                            coach.quantity = coach.quantity - quantity;
                            System.out.println("请输入租车天数(整数)");
                            int days = scanner.nextInt();
                            System.out.println("租车成功，共计金额" + quantity * days * coach.price + "元");
                            try {
                                Thread.sleep(1000);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            main();
                        } else {
                            System.out.println("车辆库存不足，请减少数量\n");
                            try {
                                Thread.sleep(1000);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            confirm();
                        }
                    } else {
                        System.out.println("请输入正整数\n");
                        confirm();
                    }
                    break;
                case 2:
                    System.out.println("请输入货车数量");
                    confirmQuantity = scanner.next();
                    if (judge(confirmQuantity)) {
                        int quantity = Integer.parseInt(confirmQuantity);
                        if (truck.quantity >= quantity) {
                            truck.quantity = truck.quantity - quantity;
                            System.out.println("请输入租车天数(整数)");
                            int days = scanner.nextInt();
                            System.out.println("租车成功，共计金额" + quantity * days * truck.price + "元");
                            try {
                                Thread.sleep(1000);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            main();
                        } else {
                            System.out.println("车辆库存不足，请减少数量\n");
                            try {
                                Thread.sleep(1000);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            confirm();
                        }
                    } else {
                        System.out.println("请输入正整数\n");
                        confirm();
                    }
                    break;
                case 3:
                    main();
                    break;
                default:
                    System.out.println("请输入正确的指令\n");
                    confirm();
                    break;
            }
        } else {
            System.out.println("请输入正确的指令\n");
            confirm();
        }
    }

    private boolean judge(String str) {
        Matcher m = pattern.matcher(str);
        return m.matches();
    }
}
