public class Equipment{
	//分别为装备名字，攻击增减率，防御增减率，暴击，暴击效果
	private final Object[][] equipment = {{"麻痹戒指", 1.4, 1.4, -10, 0.8}, 
	                                      {"暴击弓", 1.1, 1.0, 10, 1.2}, 
										  {"开天辟地锤", 1.2, 1.0, 5, 1.2},
										  {"夺命匕首", 1.5, 1.0, -15, 0.8},
										  {"啸天拳套", 1.25, 1.1, 0, 1.0},
										  {"生命之伞", 0.7, 2.0, -5, 1.0},
										  {"无尽之剑", 1.1, 1.0, 10, 1.2},
										  {"双圣之力", 1.1, 0.8, 10, 1.3},
										  {"高压粒子枪", 1.8, 0.8, -10, 0.9}};
	public Object[] equipment(){
		int places = (int)(Math.random() * equipment.length);
		switch (places){
			case 0:{
				System.out.println("        💍" + "\n" +
				                   "    ╰─┬┬─╯");
				break;
			}case 1:{
				System.out.println("        🏹" + "\n" +
						     	   "    ╰─┬┬─╯");
				break;
			}case 2:{
				System.out.println("        🔨" + "\n" +
								   "    ╰─┬┬─╯");
				break;
			}case 3:{
			    System.out.println("        🔪" + "\n" +
								   "    ╰─┬┬─╯");
				break;
			}case 4:{
				System.out.println("        🥊" + "\n" +
								   "    ╰─┬┬─╯");
				break;
			}case 5:{
				System.out.println("        ☂️" + "\n" +
								   "    ╰─┬┬─╯");
				break;
			}case 6:{
				System.out.println("        🗡️" + "\n" +
								   "    ╰─┬┬─╯");
				break;
			}case 7:{
				System.out.println("        ⚔️" + "\n" +
								   "    ╰─┬┬─╯");
				break;
			}case 8:{
				System.out.println("        🔫" + "\n" +
								   "    ╰─┬┬─╯");
				break;
			}
		}
		return equipment[places];//随机数控制掉落哪个装备
	}
}
