package prizeDraw.model.prize;

import prizeDraw.model.Reportable;
import prizeDraw.model.group.ItemPrizeDraw;

import java.io.Serializable;

public class Prize implements Serializable, ItemPrizeDraw {
    //модификатор доступа полей по умолчанию для доступа в InformerPrize:
    int id;
    String name;
    int amount;  // количество единиц
    int weight;     //вес = частота выпадения при розыгрыше
    boolean flag;   //используется, если в конструктор переданы некорректные параметры
    private Reportable<Prize> reportable;

    // вес задается в диапазоне от 1 до 99 включительно
    private boolean checkWeight(int weight){
        return weight > 0 && weight < 100;
    }

    public Prize(int id, String name, int amount, int weight, Reportable<Prize> reportable) {
        this.flag = true;
        this.id = id;
        this.name = name;
        if(amount <= 0) { flag = false; return; }
        this.amount = amount;
        if(!checkWeight(weight))  { flag = false; return; }
        this.weight = weight;
        this.reportable = reportable;
    }
    public int getId(){ return id; }
    public String getName(){ return name; }
    public int getAmount(){ return amount; }

    public void addAmount(int add){  this.amount += add; }

    //уменьшаем количество на единицу
    public boolean decreaseAmountByOne(){
        if(amount == 0) return false;
        amount--;
        return true;
    }

    public int getWeight(){ return weight; }

    public boolean changeWeight(int weight){
        if(!checkWeight(weight)) return false;
        this.weight = weight;
        return true;
    }

    public String getInfo(){
        return reportable.getInfo(this);
    }
    @Override
    public String toString() {
        return getInfo();
    }
}
