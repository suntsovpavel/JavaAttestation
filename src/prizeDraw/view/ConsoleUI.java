package prizeDraw.view;

import prizeDraw.presenter.Presenter;

import java.util.Scanner;

public class ConsoleUI implements View{
    private static final String INPUT_ERROR = "Вы ввели неверное значение";
    private Scanner scanner;
    private Presenter presenter;
    private boolean work;
    private MainMenu menu;

    public ConsoleUI(){
        scanner = new Scanner(System.in);
        presenter = new Presenter(this);
        work = true;
        menu = new MainMenu(this);
    }

    @Override
    public void print(String txt) {
        System.out.println(txt);
    }

    @Override
    public void start() {
        hello();
        while (work){
            printMenu();
            execute();
        }
    }

    private void hello(){
        System.out.println("Здравствуйте!");
    }
    public void finish() {
        System.out.println("До свидания!");
        work = false;
    }
    private void execute(){
        String line = scanner.nextLine();
        if (checkTextForInt(line)){
            int numCommand = Integer.parseInt(line);
            if (checkCommand(numCommand)){
                menu.execute(numCommand);
            }
        }
    }

    private boolean checkTextForInt(String text){
        if (text.matches("[0-9]+")){
            return true;
        } else {
            inputError();
            return false;
        }
    }

    private boolean checkCommand(int numCommand){
        if (numCommand <= menu.getSize()){
            return true;
        } else {
            inputError();
            return false;
        }
    }

    private void printMenu(){
        System.out.println(menu.menu());
    }

    private void inputError(){
        System.out.println(INPUT_ERROR);
    }

    public void ShowListPrizes(){
        System.out.println(presenter.getInfo());
    }

    public boolean addPrize(){
        System.out.println("Введите наименование игрушки: ");
        String name = scanner.nextLine();

        int amount = 0, weight=0;
        {
            System.out.println("Введите количество единиц: ");
            String str = scanner.nextLine();
            try {
                amount = Integer.parseInt( str );
                if(amount < 1){
                    System.out.println("Введенное количество единиц меньше 1");
                    return false;
                }
            } catch (NumberFormatException  e) {
                System.out.println("Ошибка ввода (не целое)");
                return false;
            }
        }
        {
            System.out.println("Введите вес (частоту выпадения игрушки) от 1 до 99: ");
            String str = scanner.nextLine();
            try {
                weight = Integer.parseInt( str );
                if(weight < 1 || weight >= 100){
                    System.out.println("Введено число вне пределов от 1 до 99");
                    return false;
                }
            } catch (NumberFormatException  e) {
                System.out.println("Ошибка ввода (не целое)");
                return false;
            }
        }
        if(presenter.addPrize(name, amount, weight)){
            System.out.println("Игрушка успешно добавлена в список разыгрываемых:");
            ShowListPrizes();
        }else {
            System.out.println("Игрушка не добавлена: заданы некорректные параметры");
        }
        return true;
    }

    //разыграть игрушку
    public void draw(){
        System.out.println(presenter.draw());
    }

    //получить игрушки (очистить очередь выдачи)
    public void getPrizes(){
        System.out.println(presenter.getPrizes());
    }


    public boolean addAmount(){
        int id=0, amount = 0;
        {
            System.out.println("Введите id игрушки: ");
            String str = scanner.nextLine();
            try {
                id = Integer.parseInt( str );
                if(id < 0){
                    System.out.println("Введено значение меньше нуля");
                    return false;
                }
            } catch (NumberFormatException  e) {
                System.out.println("Ошибка ввода (не целое)");
                return false;
            }
        }
        {
            System.out.println("Введите количество добавляемых единиц: ");
            String str = scanner.nextLine();
            try {
                amount = Integer.parseInt( str );
                if(amount < 1){
                    System.out.println("Введено значение меньше единицы");
                    return false;
                }
            } catch (NumberFormatException  e) {
                System.out.println("Ошибка ввода (не целое)");
                return false;
            }
        }
        if(!presenter.addAmount(id, amount))
            System.out.println("Введен некорректный id (игрушки нет в списке)");
        else
            System.out.println("Количество единиц успешно изменено");
        return true;
    }

    //изменить вес (частоту выпадения) игрушки
    public boolean changeWeight(){
        int id=0, weight = 0;
        {
            System.out.println("Введите id игрушки: ");
            String str = scanner.nextLine();
            try {
                id = Integer.parseInt( str );
                if(id < 0){
                    System.out.println("Введено значение меньше нуля");
                    return false;
                }
            } catch (NumberFormatException  e) {
                System.out.println("Ошибка ввода (не целое)");
                return false;
            }
        }
        {
            System.out.println("Введите вес (частоту выпадения игрушки) от 1 до 99: ");
            String str = scanner.nextLine();
            try {
                weight = Integer.parseInt( str );
                if(weight < 1 || weight >= 100){
                    System.out.println("Введено число вне пределов от 1 до 99");
                    return false;
                }
            } catch (NumberFormatException  e) {
                System.out.println("Ошибка ввода (не целое)");
                return false;
            }
        }
        if(!presenter.changeWeight(id, weight))
            System.out.println("Введен некорректный id (игрушки нет в списке)");
        else
            System.out.println("Вес (частота выпадения) успешно изменена");
        return true;
    }

    public void saveToFile(){
        System.out.println("Введите путь к файлу: ");
        String path = scanner.nextLine();
        if(presenter.saveToFile(path)){
            System.out.println("Запись успешно выполнена");
        }else{
            System.out.println("Ошибка записи файла");
        }
    }

    public void loadFromFile(){
        System.out.println("Введите путь к файлу: ");
        String path = scanner.nextLine();
        if(presenter.loadFromFile(path)){
            System.out.println("Чтение списка успешно выполнено:");
            ShowListPrizes();
        }else{
            System.out.println("Ошибка чтения файла");
        }
    }
}
