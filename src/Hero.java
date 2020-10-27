public class Hero implements Fight{
    private String name;//名字
    private int hp;//血量
    private int atk;//攻击力
    private int def;//防御力
	private int crit = 0;//暴击率（1~100）
	private double critValue = 1.0;//暴击效果
	private Monster monster;//此处创建一个monster变量，此变量放在栈内存中
	
	public Hero(Monster monster, String name, int[] attr){
		//将Start类中创建的Monster实例赋给这个类中的monster变量
		//就相当于把Start类中Monster实例的堆内存地址给这个类中的monster变量，共用同个实例
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
        if(hp > 0){
			int atk;
        	//判断是否暴击
        	if(Math.random()*100 < crit){
        		atk = (int)(this.atk * critValue);
			}else{
        		atk = this.atk;
			}
			//怪物所受伤害值
			int harm;
			if(atk > monster.def){
				harm = atk - monster.def;
                monster.hp -= harm;
            }else{
				harm = 1;
                monster.hp--;
            }
            System.out.println(name + "对第" + monster.getNum() + "个" + monster.name + "造成" + harm + "点伤害");
            monster.fighting(this, name, hp, atk, def);//调用monster对象，用this返回引用Hero类的实例
        }else{
			System.out.println("战斗结束，你被第" + monster.getNum() + "个怪物打死了！");
		}
    }

    public void getEquipment(Equipment equipment){
		System.out.println("恭喜你获得了装备：" + equipment.name);
		atk = (int)(atk * equipment.atkRate);
	}
}
