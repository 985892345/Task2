import java.util.*;

public class Monster{
    public String name;//名字
    public int hp, totalHp;//第一个为当时血量,后面为总血量
    public int atk;//攻击力
    public int def;//防御力
	private int amount, num;//一种类型怪物个数，amount为变动值，num为固定值
	private String[] otherName;//剩下全部关卡怪物的名字
	private int[] otherAmount;//剩下全部关卡怪物的数量
	private int[][] otherAttr;//剩下全部关卡怪物的属性
	private int nowSpecies = 1;//记录当前怪物类型

    public void setAttr(String name, int amount, int[] attr){
        this.name = name;
		this.amount = amount;
		num = amount;
		totalHp = attr[0];
		hp = attr[0];
		atk = attr[1];
		def = attr[2];
    }
	
	//返回当前怪物位数
	public int getNum(){
		return num + 1 - amount;
	}
	
	public boolean isFinal(){
		return nowSpecies == otherName.length + 1;
	}

	public void fighting(Fight hero, String name,int hp, int def) {//导入英雄的属性
        if(this.hp > 0){
			int harm;//怪物受到的伤害
			if(this.atk > def){
				harm = this.atk - def;
                hp -= harm;
            }else{
				harm = 1;
                hp--;
            }
            System.out.println(num - amount != 0 ? 
			                   "第" + (num + 1 - amount) + "个" + this.name + "对" + name + "造成" + harm + "点伤害" : 
							   this.name + "对" + name + "造成" + harm + "点伤害");
			
        }else{
			System.out.println(nowSpecies != otherName.length + 1 ? 
			                   "终于打死第" + (num + 1 - amount) + "个怪物了" :
							   "哈哈哈哈，管理员也不过如此！");
			getEquipment(hero);//打死怪物掉落装备
			hero.printHp(hp);//打印血量
			System.out.print("按回车攻击下一个");
			new Scanner(System.in).nextLine();//按回车进行下一步
			if(amount > 1){//判断当前关怪物是否打完
				this.hp = totalHp;
				amount--;
			}else{//当amount = 1时，当前关卡怪物就打完了
				nowSpecies++;//怪物打完，关卡加一
				if(nowSpecies <= otherName.length + 1){//判断关卡是否打完
					System.out.println("\n" + "恭喜你进入下一关！");
					upgrade(nowSpecies - 2);//放进新的怪物，修改this的属性
					if(nowSpecies == otherName.length + 1){
						boss(name);
					}
				}else{
					System.out.println("恭喜你通关！");
					new Scanner(System.in).nextLine();
					System.exit(0);
				}
			}
		}
		hero.fighting(hp);//把英雄的血传回去
    }

	//把Start类中的每一关的怪物的属性（除了第一关）转移到Monster类中
	public void add(String[] otherName, int[] otherAmount, int[][] otherAttr){
		this.otherName = otherName;
	    this.otherAmount = otherAmount;
		this.otherAttr = otherAttr;
	}
	
	//用下一关怪物属性覆盖掉上一关怪物属性
	private void upgrade(int i){
		setAttr(otherName[i], otherAmount[i], otherAttr[i]);
	}

	//怪物掉落装备，这里控制掉落概率，在Equipment类控制掉落哪个装备
	private void getEquipment(Fight hero){
		//装备掉落率
		int dropRate = 3;//1~10
		if(Math.random() * 10 < dropRate){
    		hero.getEquipment(new Equipment().equipment());
		}
	}
	
	private void boss(String name){
		System.out.println("\n" +
		                   "轰轰轰！轰轰轰！" + "\n" +
						   "————咦？这是怎么了？（按回车继续）");
		new Scanner(System.in).nextLine();
		System.out.println("（远处传来了一阵悠长沉重的语气，空气中弥漫着一股杀气）" + "\n" + "————你终于来了，我早已等候多时！");
		new Scanner(System.in).nextLine();
		System.out.println("————你……你是谁？（面对如此强大的杀气" + name + "颤抖了一下）");
		new Scanner(System.in).nextLine();
		System.out.println("————哈哈哈！我就是本游戏中最强的boss————QQ群管理员：果冻冻！继续颤抖吧，少年！你是打不赢我的！");
		new Scanner(System.in).nextLine();
		System.out.println("————废话少说！区区管理员能奈我何？看招！");
		new Scanner(System.in).nextLine();
	}
}
