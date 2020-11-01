# Task2
## Start类：
1. 用ArrayList创造Hero、Monster类多个实例  
2. 初始化Hero、Monster数据
3. 将Hero、Monster对象传入FightProcess类
## FightProcess类：
1. startFight()，写了多打多的战斗逻辑  
   1. while (heroes.size() != 0 && Lv <= Start.totalLv)， 判断英雄是否被全部打死或者是否超过总关卡数 
      1. for (Hero hero : heroes) 英雄先进攻———>调用英雄类里的战斗方法，并传入Monster实例
      2. if (monsters.size() == 0) 如果怪物全死亡———>则进入下一关
      3. else 怪物未死亡——————>调用怪物类里的战斗方法，并传入Hero实例
   2. if (heroes.size() == 0)， 判断英雄是否全死亡
   3. if (Lv > Start.totalLv)， 如果Lv数大于总关数即通关
2. printHp(), 打印血条
3. getEquipment()， 打死怪物后判断能否掉落装备
4. returnEquip()， 实例化装备类，随机返回装备
5. putOn()， 穿上装备
6. attrIncrease()， 改动穿上装备后的英雄属性
7. sell()， 出售装备
## Hero类：
1. fight()， 让传进来的Monster实例通过接口调用setHp()
## Monster类：
1. fight()， 让传进来的Hero实例通过接口调用setHp()
## Equipment类：
装备类，方便管理装备
