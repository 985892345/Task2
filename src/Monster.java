public class Monster implements MonsterInterface {
    private int index;
    private final String name;//怪物名字
    private final int hpMax;//固定血量
    private int hp;//变动血量
    private final int atk;//攻击力
    private final int def;//防御力

    public Monster(int index, String name, int[] monsterAttr) {
        this.index = index + 1;
        this.name = name;
        hpMax = monsterAttr[0];
        hp = monsterAttr[0];
        atk = monsterAttr[1];
        def = monsterAttr[2];
    }

    @Override
    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    @Override
    public String getName() {
        return name;
    }

    public int getHpMax() {
        return hpMax;
    }

    public int getHp() {
        return hp;
    }

    @Override
    public int getDef() {
        return def;
    }

    @Override
    public void setHp(int harm) {
        hp -= harm;
    }

    @Override
    public void fight(HeroInterface hero) {
        if(Math.random()*100 < hero.getDodge()){
            System.out.println("哈哈，你就是打不打我！（成功躲开怪物攻击）");
        }else{
            int harm;//英雄受到的伤害
            if(atk > hero.getDef()){
                harm = atk - hero.getDef() + (int)(Math.random() * (-atk / 25.0) + (atk / 50.0));
            }else{
                harm = 1;
            }
            System.out.println("第" + index + "个" + name + "对你的英雄" + hero.getName() + "造成" + harm + "点伤害");
            hero.getHarm(harm);
        }
    }
}
