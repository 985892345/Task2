import java.util.*;

public class Hero implements Fight{
    private final String name;//名字
    private final int hp;//固定血量
    private int atk;//攻击力
    private int def;//防御力
	private int crit = 20;//暴击率（1~100）
	private double critValue = 1.3;//暴击效果
	private int dodge = 0;//闪避率（1~100）
	private Monster[] monster;//此处创建一个monster变量，此变量放在栈内存中
	private int mortality = 0;//记录一个关卡里的死亡数
	private int nowLvNum = 1;//当前关卡
	private int monsterHp;//怪物总血量
	
	public Hero(Monster[] monster, String name, int[] attr){
		this.monster = monster;//传入第一关的怪物
		this.name = "👨‍" + name;
		hp = attr[0];
		atk = attr[1];
		def = attr[2];
	}
	//修改Hero类中怪物关卡为下一关卡
	public void setMonster(Monster[] monster){
		this.monster = monster;//保存下一关怪物初始血量
		monsterHp = monster[0].getHp();
	}
	//加载战斗，写进第一关怪物
	public void fight(){
	    monsterHp = monster[0].getHp();//保存第一关怪物初始血量
		fighting(hp, 0);//此处hp为传入英雄初始血量，0是为了启动111行代码
	}
	//开始战斗
	private int places = 0;//记录当前怪物位数
	public void fighting(int hp, int monsterPlaces){
        if(hp > 0){//判断英雄是否死亡
			//当怪物都打了英雄后，英雄才还击
		    if(monsterPlaces == monster.length || monsterPlaces == 0){
			    new Scanner(System.in).nextLine();
			    //printHp中分别对应名字，当前血量，总血量，打印的长度
			    printHp(name, hp, this.hp, 130);
				for(int i = 0; i < monster.length; i++){
					if(monster[i].getHp() > 0){
						printHp("第" + (i + 1) + "个" + monster[0].getName(), monster[i].getHp(), monsterHp, 70);
					}
				}
			    monsterPlaces = 0;
				int atk;//保存暴击伤害
				//判断是否暴击
				if(Math.random()*100 < crit){
					atk = (int)(this.atk * critValue);
					System.out.println(critValue > 1 ? 
									   "！！！看我的全力一击！（触发暴击）！！！" : 
									   "******艹，武器太重挥不动！（暴击效果为负，请去卖掉装备！）******");
				//不暴击
				}else{
					atk = this.atk;
				}
				
				
				//怪物所受伤害值
				int harm;
				//英雄攻击大于怪物防御
				if(atk > monster[0].getDef()){
					//加入随机数表示上下浮动的伤害
					harm = atk - monster[0].getDef() + (int)(Math.random() * (-atk / 25.0) + (atk / 50.0));
					//英雄攻击小于等于怪物防御
				}else{
					harm = 1;
				}
				monster[places].setHp(harm);
				//如果怪物只有一个，就不输出“对第几个”
				System.out.println(monster.length != 1 ? 
								   name + "对第" + (places + 1) + "个" + monster[0].getName() + "造成" + harm + "点伤害" :
								   name + "对" + monster[0].getName() + "造成" + harm + "点伤害");
								   

				//如果打死了一个怪物，判断是否是当前关卡最后一个
				if(monster[places].getHp() <= 0){
					//这个三目运算用来判断是否是打赢了boss
					System.out.println(nowLvNum != Start.LvNum ? 
									   "终于打死第" + (places + 1) + "个怪物了" :
									   "果冻冻被" + name + "斩于刀下！" +
									   "————哈哈哈哈，区区管理员，也不过如此！" + "\n" +
									   "恭喜你，你成功过本游戏！");
					places++;//攻击位数加1
					getEquipment();//打死怪物掉落装备
					mortality++;//当前关卡怪物死亡物数加1
					//判断当前关怪物是否打完，死亡数等于开始设置的总数就打完了
					if(mortality == Start.monsterNum[nowLvNum - 1]){
						if(nowLvNum == Start.LvNum){//这里是全部关卡都打完
							System.out.println("请点击回车结束本程序");
							new Scanner(System.in).nextLine();
							System.exit(0);
						//没打完全部关卡
						}else{
							System.out.println("\n" + "恭喜你进入下一关！（按回车进入下一关）");
							places = 0;//重置位数
							mortality = 0;//进入下一关重制死亡数
							nowLvNum++;//当前关卡数加1
							setMonster(Start.monster[nowLvNum - 1]);//Hero类中的怪物换成下一关的
							new Scanner(System.in).nextLine();//回车暂停
							if(nowLvNum == Start.LvNum){//如果接下来的关卡是最后一关，启动boss前的对话
								boss();
							}
							fighting(hp, monsterPlaces);//进入下一关重新调用fighting()，防止调用下面的方法
						}                               //调用下面方法会从怪物开始进攻
					}
				}
			}
			//分别调用每个Monster类的对象，用monsterPlaces来判断怪物位数
			monster[monsterPlaces].fighting(this, name, hp, def, nowLvNum, dodge);
        }else{//英雄死亡
		    printHp(name, hp, this.hp, 40);
			if(nowLvNum == Start.LvNum){
				System.out.println("————哎！，我说过你是打不赢我的！哈哈哈哈～～～～哈哈哈哈～～～～" + "\n");
				System.out.println("————结果总是悲剧的，她太强了，我还是打不赢管理员，不行我得重新设置属性，我就不信我打不赢她！");
				new Scanner(System.in).nextLine();
			}else{
				System.out.println("战斗结束，你被第" + monsterPlaces + "个" + monster[0].getName() + "打死了！");
			}
			System.exit(0);
		}
    }

    public void getEquipment(){
		//物品掉落率
		int dropRate = 3;//1~10
		if(Math.random() * 10 < dropRate){
			//数组分别为装备名字，攻击增减率，防御增减率，暴击，暴击效果
    		Object[] equipment = new Equipment().equipment();
			
			System.out.println("恭喜你获得了装备：" + equipment[0]);
			//攻击力
			System.out.println("攻击力：" + atk + "→" + (int)(atk * (double)equipment[1]));
			atk = (int)(atk * (double)equipment[1]);
			//防御力
			System.out.println("防御力：" + def + "→" + (int)(def * (double)equipment[2]));
			def = (int)(def * (double)equipment[2]);
			//暴击率
			if(crit + (int)equipment[3] <= 60){
				System.out.println("暴击率：" + crit + "%→" + (crit + (int)(equipment[3])) + "%");
				crit += (int)equipment[3];
			}else{
				System.out.println("暴击率：" + crit + "%→" + "60%");
				System.out.println("******暴击率已满，最高为60%，可以选择卖掉装备！******");
				crit = 60;
			}
			//暴击效果
			if(critValue * (double)equipment[4] <= 2.0){
				System.out.println("暴击效果：" + critValue * 100 + "%→" + (int)(critValue * (double)equipment[4] * 100) + "%");
				critValue *= (double)equipment[4];
			}else{
				System.out.println("暴击效果：" + critValue * 100 + "%→" + "200%");
				System.out.println("******暴击效果已满，最高为200%，可以选择卖掉装备！******");
				critValue = 2.0;
			}
			//闪避率
			if(dodge + (int)equipment[5] <= 30){
				System.out.println("闪避率：" + dodge + "%→" + (dodge + (int)equipment[5]) + "%");
				dodge += (int)equipment[5];
			}else{
				System.out.println("闪避率：" + dodge + "%→" + "30%");
				System.out.println("******闪避率率已满，最高为30%，可以选择卖掉装备！******");
				dodge = 30;
			}
		}
	}
	
	//打印血条
	public void printHp(String name, int hp, int totalHp, int totalNum){
		int num = (int)((double)hp / totalHp * totalNum);
		System.out.println(name + "：" + hp + " / " + totalHp);
		for(int i = 0; i < totalNum; i++){
			System.out.print("▁");
		}
		System.out.println();
		for(int i = 0; i < totalNum; i++){
			if(i < num){
				System.out.print("█");
			}else{
				System.out.print("▕");
			}
		}
		System.out.println();
		for(int i = 0; i < totalNum; i++){
			System.out.print("▔");
		}
		System.out.println();
	}
	
	private void boss(){
		System.out.println("\n" +
		                   "轰轰轰！轰轰轰！" + "\n" +
						   "————咦？这是怎么了？（按回车继续）");
		new Scanner(System.in).nextLine();
		System.out.println("（远处传来了一阵悠长沉重的语气，空气中弥漫着一股杀气）" + "\n" + "————你终于来了，我早已等候多时！");
		new Scanner(System.in).nextLine();
		System.out.println("————你……你是谁？（面对如此强大的杀气" + name + "颤抖了一下）");
		new Scanner(System.in).nextLine();
		System.out.println("————哈哈哈哈！我就是本游戏中最强的boss————QQ群管理员：果冻冻！继续颤抖吧，少年！你是打不赢我的！");
		new Scanner(System.in).nextLine();
		System.out.println("————废话少说！区区管理员能奈我何？看招！");
		new Scanner(System.in).nextLine();
	}
}
