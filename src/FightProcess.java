import java.util.ArrayList;
import java.util.Scanner;

public class FightProcess {
    private ArrayList<Hero> heroes;
    private ArrayList<Monster> monsters;
    public FightProcess(ArrayList<Hero> heroes, ArrayList<Monster> monsters) {
        this.heroes = heroes;
        this.monsters = monsters;
    }

    public void startFight() {
        int Lv = 1;
        System.out.println();
        while (heroes.size() != 0 && Lv <= Start.totalLv) {
            //打印血条
            for (Hero hero : heroes) printHp(hero.getName(), hero.getHp(), hero.getHpMax(), 70);
            for (int i = 0; i < monsters.size(); i++)
                printHp("第"+(i+1)+"个"+monsters.get(i).getName(), monsters.get(i).getHp(), monsters.get(i).getHpMax(), 50);
            //英雄先进攻
            for (Hero hero : heroes) {
                System.out.println("\n现在是你的回合\n请输入你的英雄" + hero.getName() + "想攻击的怪物位数：");
                int index = new Scanner(System.in).nextInt() - 1;
                hero.fight(monsters.get(index));
                if (monsters.get(index).getHp() <= 0) {
                    System.out.println("你的英雄" + hero.getName() + "打死了第" + (index + 1) + "个" + monsters.get(index).getName());
                    monsters.remove(index);
                    for (int j = index; j < monsters.size(); j++)
                        monsters.get(j).setIndex(j + 1);
                }
            }
            //如果当前关卡怪物全死亡
            if (monsters.size() == 0) {
                if (Lv <= Start.totalLv - 1)
                    System.out.println("\n恭喜你进入下一关");
                if (Lv == Start.totalLv - 1)
                    boss();
                if (Math.random() * 10 < 6){
                    System.out.println("\n恭喜你获得了一个新英雄!");
                    Start.createHero(heroes);
                }
                Start.createMonster(monsters, ++Lv);//加载Lv+1关的怪物
            }else {
                System.out.println("\n现在是怪物的回合");
                for (int i = 0; i < monsters.size(); i++) {
                    int index = (int)(Math.random() * heroes.size());
                    monsters.get(i).fight(heroes.get(index));
                    if (heroes.get(index).getHp() <= 0) {
                        heroes.remove(index);
                        System.out.println("你的" + heroes.get(i).getName() + "英雄被第" + (index + 1) + "个" + monsters.get(index).getName() + "打死了");
                    }
                }
            }
        }
        if (heroes.size() == 0)
            System.out.println("GAME OVER!");
        if (Lv > Start.totalLv)
            System.out.println("恭喜你通关!");
    }

    //打印血条
    public void printHp(String name, int hp, int totalHp, int totalNum){
        int num = (int)((double)hp / totalHp * totalNum);
        System.out.println(name + "：" + hp + " / " + totalHp);
        for(int i = 0; i < totalNum; i++){
            if(i < num){
                System.out.print("█");
            }else{
                System.out.print("▕");
            }
        }
        System.out.println();
    }

    private void boss(){
        Scanner input = new Scanner(System.in);
        System.out.println("\n轰轰轰！轰轰轰！\n————咦？这是怎么了？（按回车继续）");
        input.nextLine();
        System.out.println("（远处传来了一阵悠长沉重的语气，空气中弥漫着一股杀气）\n————你终于来了，我早已等候多时！");
        input.nextLine();
        System.out.println("————你……你是谁？（面对如此强大的杀气你的英雄都颤抖了一下）");
        input.nextLine();
        System.out.println("————哈哈哈哈！我就是本游戏中最强的boss————QQ群管理员：果冻冻！继续颤抖吧，少年！你是打不赢我的！");
        input.nextLine();
        System.out.println("————废话少说！区区管理员能奈我何？看招！");
        input.nextLine();
    }
}
