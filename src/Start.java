import java.util.Scanner;

public class Start{

    public static void main(String args[]){
        Monster monster = new Monster();
        Hero hero = new Hero(monster);
        hero.setAttribute(inputName(), inputAttribute());
        monster.setName("小兵");
        monster.setAttribute(inputAttribute());
        hero.fight();
    }

    public static String inputName(){
        System.out.println("请输入英雄名字：");
        String name = new Scanner(System.in).next();
        return name;
    }

    public static int[] inputAttribute(){
        int[] attribute = new int[3];
        System.out.println("请输入血量：");
        attribute[0] = new Scanner(System.in).nextInt();
        System.out.println("请输入攻击力：");
        attribute[1] = new Scanner(System.in).nextInt();
        System.out.println("请输入防御力：");
        attribute[2] = new Scanner(System.in).nextInt();
        return attribute;
    }
}