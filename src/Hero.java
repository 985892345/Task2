public class Hero implements Fight{
    private String name;//名字
    private int hp;//血量
    private int atk;//攻击力
    private int def;//防御力
    private Monster monster;

    public Hero(Monster monster){
        this.monster = monster;
    }
    public void setAttribute(String name, int[] attribute){
        this.name = name;
        hp = attribute[0];
        atk = attribute[1];
        def = attribute[2];
    }
    private int i = 0;
    public void fight() {
        if(hp > 0 && monster.hp > 0){
            if(monster.atk > def){
                hp -= atk - def;
            }else{
                hp--;
            }
            i++;
            System.out.println(name + "打了" + monster.name + i + "次");
            monster.fight(this, hp, atk, def, name);
        }
    }
}