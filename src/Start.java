import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Start {
    private static int[] initialMonsterAttr;

    public static void main(String[] args) {
        ArrayList<Hero> heroes = new ArrayList<>();
        ArrayList<Monster> monsters = new ArrayList<>();

        createHero(heroes);

        System.out.println("请输入怪物的属性");
        initialMonsterAttr = inputAttr();
        createMonster(monsters, 1);

        FightProcess fightProcess = new FightProcess(heroes, monsters);
        fightProcess.startFight();
    }

    //创造英雄
    public static void createHero(ArrayList<Hero> heroes) {
        System.out.println("请输入英雄名字：");
        String name = new Scanner(System.in).nextLine();
        while (name.isEmpty()) {
            System.out.println("名字不允许为空！");
            name = new Scanner(System.in).nextLine();
        }
        heroes.add(new Hero(name, inputAttr()));
    }

    //创造怪物
    public static int totalLv = 6;
    public static void createMonster(ArrayList<Monster> monsters, int Lv) {
            if (Lv == 1){
                int num = (int)(Math.random() * 4 + 6);
                for (int i = 0; i < num; i++)
                    monsters.add(new Monster(i, "骷髅兵💀", initialMonsterAttr));
                monsters.add(new Monster(num, "恶魔👿", increaseAttr(initialMonsterAttr, 3)));
            }
            if (Lv == 2){
                int num = (int)(Math.random() * 4 + 4);
                for (int i = 0; i < num; i++)
                    monsters.add(new Monster(i, "恶魔👿", increaseAttr(initialMonsterAttr, 3)));
                monsters.add(new Monster(num, "长鼻子怪👺", increaseAttr(initialMonsterAttr, 5)));
            }
            if (Lv == 3){
                int num = (int)(Math.random() * 4 + 3);
                for (int i = 0; i < (int)(Math.random() * 4 + 4); i++)
                    monsters.add(new Monster(i, "长鼻子怪👺", increaseAttr(initialMonsterAttr, 5)));
                monsters.add(new Monster(num, "恐怖外星人👽", increaseAttr(initialMonsterAttr, 8)));
            }
            if (Lv == 4){
                int num = (int)(Math.random() * 4 + 2);
                for (int i = 0; i < (int)(Math.random() * 4 + 2); i++)
                    monsters.add(new Monster(i, "恐怖外星人👽", increaseAttr(initialMonsterAttr, 8)));
                monsters.add(new Monster(num, "面相怪杰👹", increaseAttr(initialMonsterAttr, 12)));
            }
            if (Lv == 5)
                for (int i = 0; i < (int)(Math.random() * 4 + 1); i++)
                    monsters.add(new Monster(i, "面相怪杰👹", increaseAttr(initialMonsterAttr, 12)));
            if (Lv == 6)
                monsters.add(new Monster(1,"果冻冻🤖", increaseAttr(initialMonsterAttr, 20)));
    }

    //控制台输入属性
    public static int[] inputAttr() {
        Scanner input = new Scanner(System.in);
        System.out.println("请输入血量（500 ≤ 血量 ≤ 20000）：");
        int hp = 0;
        while (true) {
          try {
              hp = input.nextInt();
          } catch (InputMismatchException e) {
              System.out.println("非法输入");
              input.nextLine();
          }
          if (hp > 20000 || hp < 500) {
              System.out.println("请输入范围内的血量!");
          } else {
              break;
          }
        }
        System.out.println("请输入攻击力（100 ≤ 攻击 ≤ 400）：");
        int atk = 0;
        while (true) {
            try {
                atk = input.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("非法输入");
                input.nextLine();
            }
            if (atk > 400 || atk < 100) {
                System.out.println("请输入范围内的攻击力!");
            } else {
                break;
            }
        }
        System.out.println("请输入防御力（50 ≤ 防御 ≤ 200）：");
        int def = 0;
        while (true) {
            try {
                def = input.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("非法输入");
                input.nextLine();
            }
            if (def > 200 || def < 50) {
                System.out.println("请输入范围内的攻击力!");
            } else {
                break;
            }
        }
        return new int[]{hp, atk, def};
    }

    //该方法用于提升下一个关的怪物属性，是以初始怪物为基准，提升一个倍数
    private static int[] increaseAttr(int[] attr, int multiple) {
        int[] attribute = new int[attr.length];//重新定义个数组，如果直接返回attr会出问题
        for(int i = 0; i < attr.length; i++)
            attribute[i] = multiple * attr[i];
        return attribute;
    }
}
