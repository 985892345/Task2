import java.util.Scanner;

public class Start{

    public static void main(String[] args){
        Hero hero = new Hero();
        Monster monster = new Monster(hero, "小兵");
        hero.setAttribute(inputName(), inputAttribute());
        System.out.println("请输入野怪名血量、攻击力、防御力");
        monster.setAttribute(inputAttribute());
        monster.beBeatBy();
    }

    public static String inputName(){
        System.out.println("请输入英雄名字：");
        return new Scanner(System.in).next();
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