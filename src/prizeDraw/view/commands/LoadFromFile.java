package prizeDraw.view.commands;

import prizeDraw.view.ConsoleUI;

public class LoadFromFile extends Command {
    public LoadFromFile(ConsoleUI consoleUI) {
        super(consoleUI);
        description = "Выполнить чтение из файла списка игрушек, участвующих в розыгрыше";
    }
    public void execute(){
        consoleUI.loadFromFile();
    }
}
