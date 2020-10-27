public class Monster{
    public String name;//名字
    public int hp, totalHp;//第一个为当时血量,后面为总血量
    public int atk;//攻击力
    public int def;//防御力
	private int amount, num;//一种类型怪物个数
	private int dropRate;//装备掉落率
	private String[] otherName;
	private int[] otherAmount;
	private int[][] otherAttr;
	private int nowSpecies = 1;//记录当前怪物类型

    public void setAttr(String name, int amout, int[] attr){
        this.name = name;
		this.amount = amout;
		num = amout;
		totalHp = attr[0];
		hp = attr[0];
		atk = attr[1];
		def = attr[2];
    }
	public int getNum(){
		return num + 1 - amount;
	}

	public void fighting(Fight hero, String name,int hp, int atk, int def) {
        if(this.hp > 0 && hp > 0){
			int harm;
			if(this.atk > def){
				harm = this.atk - def;
                hp -= harm;
            }else{
				harm = 1;
                hp--;
            }
            System.out.println("第" + (num + 1 - amount) + "个" + this.name + "对" + name + "造成" + harm + "点伤害");
        }else if(this.hp <= 0 && hp > 0){
			if(amount > 1){
				System.out.println("终于打死第" + (num + 1 - amount) + "个怪物了");
				getEquipment(hero);
				this.hp = totalHp;
				amount--;
			}else{
				System.out.println("终于打死第" + (num + 1 - amount) + "个怪物了");
				getEquipment(hero);
				nowSpecies++;
				if(nowSpecies <= otherName.length + 1){
					System.out.println("\n" + "恭喜你进入下一关！");
					upgrade(nowSpecies - 2);
				}else{
					System.out.println("恭喜你通关！");
					System.exit(0);
				}
			}
		}
		hero.fighting(hp);//把英雄的血传回去
    }
	
	public void add(String[] otherName, int[] otherAmout, int[][] otherAttr){
		this.otherName = otherName;
	    this.otherAmount = otherAmout;
		this.otherAttr = otherAttr;
	}
	
	public void upgrade(int i){
		setAttr(otherName[i], otherAmount[i], otherAttr[i]);
	}
	
	public static int[] increaseAttr(int[] attr, double multiple){
		for(int i = 0; i < attr.length; i++)
			attr[i] = (int)multiple * attr[i];
		return attr;
	}

	public void getEquipment(Fight hero){
    	if(Math.random() < dropRate){
    		hero.getEquipment(new the1());
		}
	}
}
