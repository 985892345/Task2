public class Monster implements BeBeatBy{
    private String name;//名字
    private int hp;//血量
    private int atk;//攻击力
    private int def;//防御力
    private Hero hero;

    public Monster(Hero hero, String name){
        this.hero = hero;
        this.name = name;
    }
    public void setAttribute(int[] attribute){
        hp = attribute[0];
        atk = attribute[1];
        def = attribute[2];
    }
    private int i = 0;
    public void beBeatBy() {
        if(hp > 0 && hero.hp > 0){
            if(hero.atk > def){
                hp -= hero.atk - def;
            }else{
                hp--;
            }
            i++;
            System.out.println(hero.name + "打了" + name + i + "次");
            hero.beBeatBy(this, hp, atk, name);
        }
    }
}