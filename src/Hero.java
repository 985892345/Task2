public class Hero{
    public String name;//名字
    public int hp;//血量
    public int atk;//攻击力
    public int def;//防御力

    public void setAttribute(String name, int[] attribute){
        this.name = name;
        hp = attribute[0];
        atk = attribute[1];
        def = attribute[2];
    }

    private int i = 0;
    public void beBeatBy(BeBeatBy monster, int hp, int atk, String name){
        if(this.hp > 0 && hp > 0){
            if(atk > this.def){
                this.hp -= atk - this.def;
            }else{
                this.hp--;
            }
            i++;
            System.out.println(name + "打了" + this.name + i + "次");
            monster.beBeatBy();
        }
    }
}