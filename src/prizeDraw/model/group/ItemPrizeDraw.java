package prizeDraw.model.group;

public interface ItemPrizeDraw {
    String getName();
    String getInfo();
    int getId();
    int getAmount();

    void addAmount(int add); // добавляем количество данного типа

    boolean decreaseAmountByOne();  //уменьшаем количество на единицу

    int getWeight();
}