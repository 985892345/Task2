public class Equipment{
	//分别为装备位置，名字，打印的图标，攻击增减率，防御增减率，血量增减率，暴击，暴击效果，闪避率，装备等级
	private final int index;
	private final String name;
	private final String print;
	private double atkRate;
	private double defRate;
	private double hpRate;
	private int crit;
	private int critValue;
	private int dodge;
	private int Lv;

	public Equipment(int index, String name, double atkRate, double defRate, double hpRate, int crit, int critValue, int dodge, String print) {
		this.index = index;
		this.name = name;
		this.atkRate = atkRate;
		this.defRate = defRate;
		this.crit = crit;
		this.critValue = critValue;
		this.dodge = dodge;
		this.hpRate = hpRate;
		this.print = print;
	}

	public int getI() {
		return index;
	}

	public String getName() {
		return name;
	}

	public double getAtkRate() {
		return atkRate;
	}

	public void setAtkRate(double atkRate) {
		this.atkRate = atkRate;
	}

	public double getDefRate() {
		return defRate;
	}

	public void setDefRate(double defRate) {
		this.defRate = defRate;
	}

	public double getHpRate() {
		return hpRate;
	}

	public void setHpRate(double hpRate) {
		this.hpRate = hpRate;
	}

	public int getCrit() {
		return crit;
	}

	public void setCrit(int crit) {
		this.crit = crit;
	}

	public int getCritValue() {
		return critValue;
	}

	public void setCritValue(int critValue) {
		this.critValue = critValue;
	}

	public int getDodge() {
		return dodge;
	}

	public void setDodge(int dodge) {
		this.dodge = dodge;
	}

	public void print(){
		System.out.println(print);
	}

	public int getLv() {
		return Lv;
	}

	public void setLv(int lv) {
		Lv = lv;
	}
}

class Empty extends Equipment{
	public Empty() {
		super(0,"空",1.0,1.0,1.0,0,0,0,null);
	}
}

class the1 extends Equipment{
	public the1() {
		super(1,"麻痹戒指",1.1,1.2,1.05,0,1,4,"  💍\n╰─┬┬─╯");
	}
}

class the2 extends Equipment{
	public the2() {
		super(2,"暴击弓",1.2,1.0,1.0,10,3,0,"  🏹\n╰─┬┬─╯");
	}
}

class the3 extends Equipment{
	public the3() {
		super(3,"开天辟地锤",1.3,1.0,1.0,5,2,0,"  🔨\n╰─┬┬─╯");
	}
}

class the4 extends Equipment{
	public the4() {
		super(4,"夺命匕首",1.4,1.0,1.0,0,1,0,"  🔪\n╰─┬┬─╯");
	}
}

class the5 extends Equipment{
	public the5() {
		super(5,"啸天拳套",1.25,1.1,1.0,0,1,5,"  🥊\n╰─┬┬─╯");
	}
}

class the6 extends Equipment{
	public the6() {
		super(6,"生命之伞",1.0,1.3,1.3,0,0,9,"  ☂️\n╰─┬┬─╯");
	}
}

class the7 extends Equipment{
	public the7() {
		super(7,"无尽之剑",1.1,1.0,1.0,20,3,0,"  🗡️\n╰─┬┬─╯");
	}
}

class the8 extends Equipment{
	public the8() {
		super(8,"双圣之力",1.1,1.0,1.0,15,2,0,"  ⚔️\n╰─┬┬─╯");
	}
}

class the9 extends Equipment{
	public the9() {
		super(9,"高压粒子枪",1.5,1.0,1.0,0,1,0,"  🔫\n╰─┬┬─╯");
	}
}

class the10 extends Equipment{
	public the10() {
		super(10,"安全帽",1.0,1.5,1.2,0,0,8,"  ⛑️\n╰─┬┬─╯");
	}
}