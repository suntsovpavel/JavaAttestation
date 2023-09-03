package prizeDraw.model;

import prizeDraw.model.group.*;

import java.io.Serializable;
import java.util.*;

// Класс, реализующий сервис выдачи призов-игрушек
public class PrizeDraw<T extends ItemPrizeDraw> implements Serializable {
    private Collectable<T> items;  //список игрушек, участвующих в розыгрыше
    private Deque<String> deque;     //очередь имен разыгранных игрушек, ожидающих выдачи
    private String errorMessage; // для метода draw, поскольку он возвращает объект T
    public PrizeDraw(Collectable<T> items){
        this.items = items;
        this.deque = new ArrayDeque<>();
        this.errorMessage=null;
    }
    public String getErrorMessage(){ return errorMessage; }

    public String getInfoDeque(){
        return deque.toString();
    }

    public void addItem(T p){
        items.add(p);
    }

    //Если по заданному id в списке игрушек нет, возвращаем null:
    public T getItemById(int id){
        for(T t : items)
            if(t.getId() == id)
                return t;
        return null;
    }

    //возвращаем максимальный id По всем элементам items
    //Если список пуст, возвращаем -1
    public int getMaxId(){
        int maxId = -1;
        for(T t : items)
            if(t.getId() > maxId)
                maxId =t.getId();
        return maxId;
    }

    //Разыгрываем игрушку
    public T draw(){
        errorMessage = null;
        if(items.getSize() == 0) {
            errorMessage = "Список игрушек, участвующих в розыгрыше, пуст";
            return null;
        }
        //Проверяем, что количество игрушек , участвующих в розыгрыше, отлично от 0:
        for (T t : items) {
            if (t.getAmount() == 0) {
                errorMessage = "Игрушки id=" + t.getId() + " закончились, нужно пополнить";
                return null;
            }
        }

        //1. Суммируем веса всех игрушек, участвующих в розыгрыше:
        int sumWeight=0;
        for (T t : items)
            sumWeight += t.getWeight();

        //2. генерим целое число от 0 до sumWeight:
        Random rand = new Random();
        int value = rand.nextInt(sumWeight);

        //3. Повторно пробегаемся по списку items
        //Рандомная игрушка - та, для которой будет достигнуто  value < sumWeight
        sumWeight=0;
        for (T t : items) {
            sumWeight += t.getWeight();
            if(value < sumWeight){
                deque.add(t.getName());   //Добавляем объект в очередь выдачи
                t.decreaseAmountByOne();    //Уменьшаем количество на единицу
                return t;
            }
        }
        //по идее сюда добраться не должны...
        errorMessage = "ошибка алгоритма";
        return null;
    }

    //получаем все игрушки для выдачи
    // освобождаем очередь и возвращаем список имен игрушек
    public List<String> eraseDeque(){
        List<String> names = new ArrayList<>();
        while(deque.size() > 0){
            names.add(deque.poll());
        }
        return names;
    }

    public String getInfo(){
        return items.getInfo();
    }
    @Override
    public String toString() {
        return getInfo();
    }
}
