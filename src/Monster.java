public class Monster{
    public String name;//名字
    public int hp;//血量
    public int atk;//攻击力
    public int def;//防御力

    public void setName(String name){
        this.name = name;
    }

    public void setAttribute(int[] attribute){
        hp = attribute[0];
        atk = attribute[1];
        def = attribute[2];
    }

    private int i = 0;
    public void fight(Fight fight, int hp, int atk, String name) {
        if(this.hp > 0 && hp > 0){
            if(atk > this.def){
                this.hp -= atk - this.def;
            }else{
                this.hp--;
            }
            i++;
            System.out.println(this.name + "打了" + name + i + "次");
            fight.fight();
        }
    }
}