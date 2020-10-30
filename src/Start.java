import java.util.*;

public class Start{

	static final int[] monsterNum = {10, 8, 6, 4 ,2 ,1};//每关的怪物数
	static final int LvNum = monsterNum.length;//关卡数
	static Monster[][] monster = new Monster[LvNum][];
	
    public static void main(String[] args){
		
		String[] nameGather = {"骷髅兵💀", "恶魔👿", "长鼻子怪👺", "恐怖外星人👽", "面相怪杰👹", "果冻冻🤖"};//每关的怪物名
		
		for(int i = 0; i < LvNum; i++){
			monster[i] = new Monster[monsterNum[i]];
		}
		
		Hero hero = new Hero(monster[0], inputName(), inputAttr(1));

		System.out.println("请输入初始野怪的血量、攻击力、防御力");
		int[] initialAttr = inputAttr(1);//输入怪物初始属性
		int[][] attrGather = new int[LvNum][];//用来保存每一关的怪物属性

		attrGather[0] = initialAttr;//如果要改动，就要改动前面的2个量！！！分别是monsterNum,nameGather
		attrGather[1] = increaseAttr(initialAttr, 5.0);
		attrGather[2] = increaseAttr(initialAttr, 10.0);
		attrGather[3] = increaseAttr(initialAttr, 20.0);
		attrGather[4] = increaseAttr(initialAttr, 30.0);
		attrGather[5] = increaseAttr(initialAttr, 100.0);
		
        for(int i = 0; i < LvNum; i++){
			for(int j = 0; j < monsterNum[i]; j++){
				//赋给monster对象中有同一关卡中每个怪物名字、位数、属性、
				monster[i][j] = new Monster(nameGather[i], j + 1, attrGather[i]);
			}
		}
		
        hero.fight();//开始战斗
    }

	//输入英雄名字
    private static String inputName(){
        System.out.println("请输入英雄名字：");
        return new Scanner(System.in).next();
    }

	//输入属性
	private static int[] attribute = new int[3];
    private static int[] inputAttr(int i){
        if(i == 1){
			System.out.println("请输入血量（500 ≤ 血量 ≤ 50000）：");
			int hp = new Scanner(System.in).nextInt();
			if(hp >= 500 && hp <= 50000){
				attribute[0] = hp;
				inputAttr(2);
			}else{
				System.out.println("超出范围！请重新输入");
				inputAttr(1);
			}
		}
        if(i == 2){
			System.out.println("请输入攻击力（100 ≤ 攻击 ≤ 1000）：");
			int atk = new Scanner(System.in).nextInt();
			if(atk >= 100 && atk <= 1000){
				attribute[1] = atk;
				inputAttr(3);
			}else{
				System.out.println("超出范围！请重新输入");
				inputAttr(2);
			}
		}
		if(i == 3){
			System.out.println("请输入防御力（50 ≤ 防御 ≤ 500）：");
			int def = new Scanner(System.in).nextInt();
			if(def >= 50 && def <= 500){
				attribute[2] = def;
			}else{
				System.out.println("超出范围！请重新输入");
				inputAttr(3);
			}
		}
        return attribute;
    }
	
	//该方法用于提升下一个关的怪物属性，是以初始怪物为基准，提升一个倍数
	private static int[] increaseAttr(int[] attr, double multiple){
		int[] attribute = new int[attr.length];//重新定义个数组，如果直接返回attr会出问题
		for(int i = 0; i < attr.length; i++)
			attribute[i] = (int)(multiple * attr[i]);
		return attribute;
	}
}
