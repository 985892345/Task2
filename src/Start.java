import java.util.Scanner;

public class Start{

    public static void main(String[] args){
		Monster monster = new Monster();
		Hero hero = new Hero(monster, inputName(), inputAttr());
		System.out.println("请输入野怪的血量、攻击力、防御力");
		int[] attr = inputAttr();
		String[] otherName = {"中哥布林", "大哥布林"};
		int[] otherAmout = {2, 1};
		int[][] otherAttr = {increaseAttr(attr, 1.2), increaseAttr(attr, 1.5)};
		monster.add(otherName, otherAmout, otherAttr);
		monster.setAttr("小哥布林", 3, attr);
		
        hero.fight();
    }

    public static String inputName(){
        System.out.println("请输入英雄名字：");
        return new Scanner(System.in).next();
    }

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
	
	public static int[] increaseAttr(int[] attr, double multiple){
		for(int i = 0; i < attr.length; i++)
			attr[i] = (int)multiple * attr[i];
		return attr;
	}
}
