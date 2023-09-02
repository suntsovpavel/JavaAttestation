package prizeDraw.view.commands;

import prizeDraw.view.ConsoleUI;

public class SaveToFile extends Command{
    public SaveToFile(ConsoleUI consoleUI) {
        super(consoleUI);
        description = "Выполнить запись в файл списка игрушек, участвующих в розыгрыше";
    }
    public void execute(){
        consoleUI.saveToFile();
    }
}
