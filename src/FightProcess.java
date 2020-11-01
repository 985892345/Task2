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
                System.out.println("\n现在是你的回合\n请输入你的英雄" + hero.getName() + "想攻击的怪物位数（输入回车，你的英雄将自由攻击）：");
                Scanner input= new Scanner(System.in);
                String in = input.nextLine();
                int index;
                //防止输错位数
                if (isNum(in)){
                    index = Integer.parseInt(in) - 1;
                    while (index > monsters.size()){
                        System.out.println("超出范围！请重新输入");
                        index = input.nextInt() - 1;
                    }
                }else {
                    index = (int)(Math.random() * monsters.size());
                }
                hero.fight(monsters.get(index));
                //判断英雄打的index位怪物是否被打死
                if (monsters.get(index).getHp() <= 0) {
                    System.out.println("你的英雄" + hero.getName() + "打死了第" + (index + 1) + "个" + monsters.get(index).getName());
                    monsters.remove(index);
                    getEquipment();
                    for (int j = index; j < monsters.size(); j++)
                        monsters.get(j).setIndex(j + 1);
                }
            }
            //如果当前关卡怪物全死亡
            if (monsters.size() == 0) {
                if (Lv <= Start.totalLv - 1)
                    System.out.println("\n恭喜你进入下一关");
                if (Math.random() * 10 < 5){
                    System.out.println("\n恭喜你获得了一个新英雄!");
                    Start.createHero(heroes);
                }
                if (Lv == Start.totalLv - 1)
                    boss();
                Start.createMonster(monsters, ++Lv);//加载Lv+1关的怪物
            }else {
                System.out.println("\n现在是怪物的回合");
                for (int i = 0; i < monsters.size(); i++) {
                    if (heroes.size() != 0){
                        //怪物随机进攻一个英雄
                        int index = (int)(Math.random() * heroes.size());
                        monsters.get(i).fight(heroes.get(index));
                        if (heroes.get(index).getHp() <= 0) {
                            System.out.println("你的英雄" + heroes.get(index).getName() + "被第" + i + "个" + monsters.get(i).getName() + "打死了");
                            heroes.remove(index);
                        }
                    }
                }
            }
        }
        //如果英雄全死亡
        if (heroes.size() == 0)
            System.out.println("GAME OVER!");
        //如果Lv数大于总关数即通关
        if (Lv > Start.totalLv)
            System.out.println("果冻冻被你的英雄斩于刀下!\n————哈哈哈哈，区区管理员，也不过如此!\n恭喜你，你成功通过本游戏！");
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

    //判断输入是否是数字
    private boolean isNum(String s) {
        if (s.length() == 0)
            return false;
        return s.chars().allMatch(Character::isDigit);
    }

    //随机返回装备
    private Equipment returnEquip(){
        Equipment returnEquipment = new Empty();
        int index = (int) (Math.random() * 10);
        if (index == 0) {
            returnEquipment = new the1();
        } else if (index == 1) {
            returnEquipment = new the2();
        } else if (index == 2) {
            returnEquipment = new the3();
        } else if (index == 3) {
            returnEquipment = new the4();
        } else if (index == 4) {
            returnEquipment = new the5();
        } else if (index == 5) {
            returnEquipment = new the6();
        } else if (index == 6) {
            returnEquipment = new the7();
        } else if (index == 7) {
            returnEquipment = new the8();
        } else if (index == 8) {
            returnEquipment = new the9();
        } else if (index == 9) {
            returnEquipment = new the10();
        }
        return returnEquipment;
    }

    //判断能否得到装备
    private int amount = 0;//装备槽里放的装备数
    public void getEquipment() {
        Equipment[] moreEquipment = new Equipment[12];//能带的装备数量
        int dropRate = 3;////物品掉落率(0~10)
        if(Math.random() * 10 < dropRate) {
            if (amount < moreEquipment.length){
                //得到哪件装备
                moreEquipment[amount++] = returnEquip();
                System.out.println("\n恭喜你获得装备【" + moreEquipment[amount-1].getName() + "】!\n是否更换装备？\n（输入1进行更换，按其他将装备装进背包）背包剩余槽位：" + (12 - amount));
                int in = new Scanner(System.in).nextInt();
                if (in == 1)
                    putOn(moreEquipment[--amount]);
            }else {
                System.out.println("装备已满!\n输入1丢掉该装备，2换上该装备，3卖掉该装备");
                int select = new Scanner(System.in).nextInt();
                if (select == 2){
                    putOn(returnEquip());
                }else if (select == 3){
                    sell();
                }
            }
        }
    }

    //穿上装备
    private void putOn(Equipment equipment) {
        for (int i = 0; i < heroes.size(); i++){
            System.out.println("你的英雄" + heroes.get(i).getName() + "有以下装备：");
            for (int j = 0; j < 4; j++)
                System.out.print("【" + heroes.get(i).getEquipment()[j].getName() + "】   ");
            System.out.println();
        }
        Scanner input = new Scanner(System.in);
        int who = 0;
        if (heroes.size() != 1){
            System.out.println("请输入你想给谁换上装备：");
            who = input.nextInt() - 1;
        }
        System.out.println("请输入想更换装备的位置（输入0取消更换，丢掉该装备）：");
        int index = input.nextInt();
        if (index != 0){
            Equipment delete = heroes.get(who).getEquipment()[index - 1];
            heroes.get(who).getEquipment()[index - 1] = equipment;
            attrIncrease(who, delete, equipment);
        }
    }

    //穿上装备后的英雄属性提升
    private void attrIncrease(int who, Equipment delete, Equipment change) {
        int atk = heroes.get(who).getAtk();
        int def = heroes.get(who).getDef();
        int hpMax = heroes.get(who).getHpMax();
        int crit = heroes.get(who).getCrit();
        int critValue = heroes.get(who).getCritValue();
        int dodge = heroes.get(who).getDodge();
        System.out.println("你的英雄" + heroes.get(who).getName() + "：");
        //攻击力
        System.out.println("攻击力：" + atk + "→" + (int)(atk / delete.getAtkRate() * change.getAtkRate()));
        heroes.get(who).setAtk((int)(atk / delete.getAtkRate() * change.getAtkRate()));
        //防御力
        System.out.println("防御力：" + def + "→" + (int)(def / delete.getDefRate() * change.getDefRate()));
        heroes.get(who).setDef((int)(def / delete.getDefRate() * change.getDefRate()));
        //血量
        System.out.println("血量：" + hpMax + "→" + (int)(hpMax / delete.getHpRate() * change.getHpRate()));
        heroes.get(who).setHpMax((int)(hpMax / delete.getHpRate() * change.getHpRate()));
        //暴击率
        if(crit - delete.getCrit() + change.getCrit() <= 60){
            System.out.println("暴击率：" + crit + "%→" + (crit - delete.getCrit() + change.getCrit()) + "%");
            heroes.get(who).setCrit(crit - delete.getCrit() + change.getCrit());
        }else{
            System.out.println("暴击率：" + crit + "%→" + "60%");
            System.out.println("******暴击率已满，最高为60%，可以选择卖掉装备！******");
            heroes.get(who).setCrit(60);
        }
        //暴击效果
        if(critValue - delete.getCritValue() + change.getCritValue() <= 20){
            System.out.println("暴击效果：" + critValue * 10 + "%→" + ((critValue - delete.getCritValue() + change.getCritValue()) * 10) + "%");
            heroes.get(who).setCritValue(critValue - delete.getCritValue() + change.getCritValue());
        }else{
            System.out.println("暴击效果：" + critValue * 10 + "%→" + "200%");
            System.out.println("******暴击效果已满，最高为200%，可以选择卖掉装备！******");
            heroes.get(who).setCritValue(20);
        }
        //闪避率
        if(dodge + delete.getDodge() + change.getDodge() <= 30){
            System.out.println("闪避率：" + dodge + "%→" + (dodge + delete.getDodge() + change.getDodge()) + "%");
            heroes.get(who).setDodge(dodge + delete.getDodge() + change.getDodge());
        }else{
            System.out.println("闪避率：" + dodge + "%→" + "30%");
            System.out.println("******闪避率已满，最高为30%，可以选择卖掉装备！******");
            heroes.get(who).setDodge(30);
        }
        new Scanner(System.in).nextLine();
    }

    //出售装备
    private void sell() {
        System.out.println("写的太累了，暂时未开通");
    }

    //最后一关遇到boss的对话
    private void boss() {
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
