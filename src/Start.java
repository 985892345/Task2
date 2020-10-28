import java.util.*;

public class Start{

    public static void main(String[] args){
		Monster monster = new Monster();
		Hero hero = new Hero(monster, inputName(), inputAttr());
		System.out.println("请输入野怪的血量、攻击力、防御力");
		int[] attr = inputAttr();
        monster.setAttr("小哥布林", 4, attr);//设置初始怪物
		String[] otherName = {"中哥布林", "大哥布林", "哥布林投矛手", "哥布林硬汉", "果冻冻"};
		int[] otherAmount = {4, 3, 2, 1, 1};
		int[][] otherAttr = new int[otherName.length][];
		otherAttr[0] = increaseAttr(attr, 1.3);
		otherAttr[1] = increaseAttr(attr, 1.5);
		otherAttr[2] = increaseAttr(attr, 1.8);
		otherAttr[3] = increaseAttr(attr, 2.0);
		otherAttr[4] = increaseAttr(attr, 800.0);
		monster.add(otherName, otherAmount, otherAttr);
		
        hero.fight();//开始战斗
    }

	//输入英雄名字
    public static String inputName(){
        System.out.println("请输入英雄名字：");
        return new Scanner(System.in).next();
    }

	//输入属性
    public static int[] inputAttr(){
        int[] attribute = new int[3];
        System.out.println("请输入血量：");
        attribute[0] = new Scanner(System.in).nextInt();
        System.out.println("请输入攻击力：");
        attribute[1] = new Scanner(System.in).nextInt();
        System.out.println("请输入防御力：");
        attribute[2] = new Scanner(System.in).nextInt();
        return attribute;
    }
	
	//该方法用于提升下一个关的怪物属性，是以初始怪物为基准，提升一个倍数
	public static int[] increaseAttr(int[] attr, double multiple){
		int[] attribute = new int[attr.length];//重新定义个数组，如果直接返回attr会出问题
		for(int i = 0; i < attr.length; i++)
			attribute[i] = (int)(multiple * attr[i]);
		return attribute;
	}
}
