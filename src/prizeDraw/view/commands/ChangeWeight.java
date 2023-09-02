package prizeDraw.view.commands;

import prizeDraw.view.ConsoleUI;

//возвращаем информацию о игрушках в списке разыгрываемых:
public class ChangeWeight extends Command{
    public ChangeWeight(ConsoleUI consoleUI) {
        super(consoleUI);
        description = "изменить вес (частоту выпадения) игрушки по id";
    }
    public void execute(){
        consoleUI.changeWeight();
    }
}
