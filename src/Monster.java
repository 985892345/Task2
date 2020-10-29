public class Monster{
    public final String name;//怪物名字
    public int hp;//变动血量
    public final int atk;//攻击力
    public final int def;//防御力
	private final int places;//当前monster对象的位数，是从1开始

	public Monster(String name, int places, int[] attr){
		this.name = name;
		this.places = places;
		hp = attr[0];
		atk = attr[1];
		def = attr[2];
	}

	public void fighting(Fight hero, String name, int hp, int def, int nowLvNum) {//导入英雄的属性
	
        if(this.hp > 0){
			int harm;//英雄受到的伤害
			if(this.atk > def){
				harm = this.atk - def + (int)(Math.random() * (-this.atk / 25.0) + (this.atk / 50.0));
                hp -= harm;
            }else{
				harm = 1;
                hp--;
            }
            System.out.println(Start.monsterNum[nowLvNum - 1] != 1 ? 
			                   "第" + places + "个" + this.name + "对" + name + "造成" + harm + "点伤害" : 
							   this.name + "对" + name + "造成" + harm + "点伤害");
		}
		hero.fighting(hp, places);//把英雄的血传回去，places是判断英雄攻击条件，只有怪物都打了英雄，英雄才回击
    }
}
