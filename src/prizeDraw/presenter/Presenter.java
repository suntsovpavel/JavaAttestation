package prizeDraw.presenter;

import prizeDraw.model.ServicePrizeDraw;
import prizeDraw.model.prize.Prize;
import prizeDraw.view.View;
import saveload.FileLoader;
import saveload.FileSaver;

import java.time.LocalDate;

public class Presenter {
    private View view;
    private ServicePrizeDraw service;

    public Presenter(View view) {
        this.view = view;
        FileSaver saver = new FileSaver();
        FileLoader loader = new FileLoader();
        service = new ServicePrizeDraw(saver, loader);
    }

    public boolean addPrize(String name, int amount, int weight)
    {
        return service.addItem(name, amount, weight);
    }

    //возвращаем информацию о игрушках в списке разыгрываемых:
    public String getInfo(){
        return service.getInfo();
    }

    //Разыгрываем игрушку
    public String draw(){
        return service.draw();
    }

    //получаем все игрушки для выдачи
    // освобождаем очередь и возвращаем список имен игрушек
    public String getPrizes(){
        return service.getPrizes();
    }

    //Пополняем количество единиц для игрушки по id
    //return false, если id задан некорректно
    public boolean addAmount(int id, int add){
        return service.addAmountPrizeById(id, add);
    }

    //изменяем вес игрушки по id
    public boolean changeWeight(int id, int weight){
        return service.changeWeight(id, weight);
    }

    public boolean saveToFile(String path){
        return service.saveTo(path);
    }
    public boolean loadFromFile(String path){
        return service.loadFrom(path);
    }
}
