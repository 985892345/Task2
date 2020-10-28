public class Hero implements Fight{
    private final String name;//名字
    private int hp;//固定血量
    private int atk;//攻击力
    private int def;//防御力
	private int crit = 20;//暴击率（1~100）
	private double critValue = 1.3;//暴击效果
	private final Monster monster;//此处创建一个monster变量，此变量放在栈内存中
	
	public Hero(Monster monster, String name, int[] attr){
		//将Start类中创建的Monster实例赋给这个类中的monster变量
		//就相当于把Start类中Monster实例的堆内存地址给这个类中的monster变量，共用同个对象
		this.monster = monster;
		this.name = name;
		hp = attr[0];
		atk = attr[1];
		def = attr[2];
	}

	public void fight(){//开始战斗
		fighting(hp);//此处hp为传入英雄初始血量
	}

	public void fighting(int hp){
        if(hp > 0){//判断英雄是否死亡
			int atk;
        	//判断是否暴击
        	if(Math.random()*100 < crit){
				atk = (int)(this.atk * critValue);
			    System.out.println(critValue > 1 ? 
				                   "！！！看我的全力一击！（触发暴击）！！！" : 
								   "******艹，武器太重挥不动！（暴击效果为负，请去卖掉装备！）******");
			}else{
        		atk = this.atk;
			}
			//怪物所受伤害值
			int harm;
			if(atk > monster.def){//英雄攻击大于怪物防御
				harm = atk - monster.def;
                monster.hp -= harm;
            }else{//英雄攻击小于等于怪物防御
				harm = 1;
                monster.hp--;
            }
            System.out.println(monster.getNum() != 1 ? 
			                   name + "对第" + monster.getNum() + "个" + monster.name + "造成" + harm + "点伤害" :
							   name + "对" + monster.name + "造成" + harm + "点伤害");
            monster.fighting(this, name, hp, def);//调用monster对象，用this返回引用Hero类的实例，再导出英雄属性
        }else{//英雄死亡
		    printHp(hp);
			if(monster.isFinal()){
				System.out.println("————哎！，我说过你是打不赢我的！哈哈哈哈～～～～哈哈哈哈～～～～" + "\n");
				System.out.println("————结果总是悲剧的，她太强了，我还是打不赢管理员，不行我得重新设置属性，我就不信我打不赢她！");
			}else{
				System.out.println("战斗结束，你被第" + monster.getNum() + "个" + monster.name + "打死了！");
			}
		}
    }

    public void getEquipment(Object[] equipment){
		//数组分别为装备名字，攻击增减率，防御增减率，暴击，暴击效果
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
		}
		//暴击效果
		if(critValue * (double)equipment[4] <= 2.0){
			System.out.println("暴击效果：" + critValue * 100 + "%→" + (int)(critValue * (double)equipment[4] * 100) + "%");
			critValue *= (double)equipment[4];
		}else{
			System.out.println("暴击效果：" + critValue * 100 + "%→" + "200%");
			critValue = 2.0;
			System.out.println("******暴击效果已满，最高为200%，可以选择卖掉装备！******");
		}
	}
	
	//打印血条
	public void printHp(int hp){
		int num = (int)((double)hp / this.hp * 30);
		System.out.println("▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁");
		for(int i = 0; i < 30; i++){
			if(i < num){
				System.out.print("█");
			}else{
				System.out.print("▕");
			}
		}
		System.out.println("\n" + "▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔");
		System.out.println("                  " + hp + " / " + this.hp);
	}
}
