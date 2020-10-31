public class Hero implements HeroInterface {
    private final String name;//名字
    private final int hp;//没有装备时的固定血量
    private int hpMax;//有装备后的血量上限
    private final int atk;//攻击力
    private final int def;//防御力
    private final int crit = 20;//暴击率（1~100）
    private final int critValue = 13;//暴击效果（换成int类型好写进数组）（10~25）
    private final int dodge = 0;//闪避率（1~100）
    private int[] attr;//用来记录当前英雄变动的属性

    public Hero(String name, int[] heroAttr) {
        this.name = name;
        hpMax = heroAttr[0];
        hp = heroAttr[0];
        atk = heroAttr[1];
        def = heroAttr[2];
        attr = new int[]{hp, atk, def, crit, critValue, dodge};
    }

    @Override
    public String getName() {
        return name;
    }

    public int getHpMax() {
        return hpMax;
    }

    public int getHp() {
        return attr[0];
    }

    @Override
    public void setHp(int harm) {
        attr[0] -= harm;
    }

    @Override
    public int getDef() {
        return def;
    }

    @Override
    public int getDodge() {
        return dodge;
    }

    @Override
    public void fight(MonsterInterface monster) {
        int atk;//保存暴击伤害
        //判断是否暴击
        if(Math.random() * 100 < attr[3]){
            atk = attr[1] * attr[4] /10;
            System.out.println("！！！看我的全力一击！（触发暴击）！！！");
            //不暴击
        }else{
            atk = attr[1];
        }
        int harm;
        //英雄攻击大于怪物防御
        if(atk > monster.getDef()){
            //加入随机数表示上下浮动的伤害
            harm = atk - monster.getDef() + (int)(Math.random() * (-atk / 25.0) + (atk / 50.0));
            //英雄攻击小于等于怪物防御
        }else{
            harm = 1;
        }
        System.out.println("你的英雄" + name + "对第" + monster.getIndex() + "个" + monster.getName() + "造成" + harm + "点伤害");
        monster.setHp(harm);
    }
}
