package prizeDraw.model;

import prizeDraw.model.group.Group;
import prizeDraw.model.prize.Prize;
import prizeDraw.model.prize.ReportablePrize;
import saveload.LoadFrom;
import saveload.SaveTo;
import java.io.IOException;
import java.util.List;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class ServicePrizeDraw {
    private PrizeDraw<Prize> prizeDraw;
    private SaveTo saver;
    private LoadFrom loader;
    private ReportablePrize informerHuman;
    private String errorMessage;
    private String pathFilePrizes = "prizes.txt";

    public ServicePrizeDraw(SaveTo saver, LoadFrom loader){

        this.prizeDraw = new PrizeDraw(new Group<Prize>(), new ReportablePrizeDraw());
        this.saver = saver;
        this.loader = loader;
        this.informerHuman = new ReportablePrize();
        this.errorMessage = null;
    }

    //добавляем игрушку в список разыгрываемых:
    public void addItem(String name, int amount, int weight){
        //Получаем максимальный id по текущему списку и увеличиваем на единицу:
        int id = prizeDraw.getMaxId() + 1;

        //!!! Объекты можем создавать только здесь, не в PrizeDraw !!!
        prizeDraw.addItem(new Prize(id, name, amount, weight, informerHuman));
    }

    //возвращаем информацию о игрушках в списке разыгрываемых:
    public String getInfo(){
        return prizeDraw.getInfo();
    }

    //Разыгрываем игрушку
    public String draw(){
        Prize prize = prizeDraw.draw();
        if(prize == null){
            return prizeDraw.getErrorMessage();
        }else{
            return "Разыграна игрушка: " + prize.getName()
                    + "\nОчередь выдачи (имена игрушек): " + prizeDraw.getInfoDeque();
        }
    }

    //Получаем призовые игрушки - освобождаем очередь PrizeDraw.queue,
    //возвращаем список имен игрушек и делаем записи в файл
    public String getPrizes(){
        List<String> names = prizeDraw.eraseDeque();

        if(names.size() > 0){
            //дописываем в файл имена выданных призовых игрушек
            try {
                FileWriter writer = new FileWriter(pathFilePrizes, true);
                BufferedWriter bufferWriter = new BufferedWriter(writer);
                for (String name : names)
                    bufferWriter.write(name + '\n');
                bufferWriter.close();
            }
            catch (IOException e) {
                System.out.println(e.toString());
            }
            return  "Очередь выдачи: [" + String.join(",", names) + "]\n"
                    + "Очередь очищена, игрушки выданы; Имена игрушек дописаны в файл " + pathFilePrizes;
        }else{
            return "Очередь выдачи пуста, разыграйте игрушки";
        }
    }

    //Пополняем количество единиц для игрушки по id
    //return false, если id задан некорректно либо add <= 0
    public boolean addAmountPrizeById(int id, int add){
        if(add <= 0 ) return false; //пополняемое количество должно быть > 0
        Prize p = prizeDraw.getItemById(id);
        if(p == null) return false; //некорретный id
        p.addAmount(add);
        return true;
    }

    //изменяем вес игрушки по id
    public boolean changeWeight(int id, int weight){
        Prize p = prizeDraw.getItemById(id);
        if(p == null) return false; //некорретный id
        return p.changeWeight(weight);
    }

    //Выполняем запись содержимого prizeDraw
    public boolean saveTo(String path) {
        try {
            saver.saveTo(prizeDraw, path);
        }
        catch(IOException e) {
            System.out.println(e.toString());
            return false;
        }
        return true;
    }
    //Выполняем чтение содержимого дерева
    public boolean loadFrom(String path) {
        try {
            prizeDraw = (PrizeDraw)loader.loadFrom(path);
        }
        catch(IOException e) {
            return false;
        }
        catch(ClassNotFoundException e) {
            return false;
        }
        return true;
    }
}
