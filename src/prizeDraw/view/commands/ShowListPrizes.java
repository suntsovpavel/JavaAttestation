package prizeDraw.view.commands;

import prizeDraw.view.ConsoleUI;

//возвращаем информацию о игрушках в списке разыгрываемых:
public class ShowListPrizes extends Command{
    public ShowListPrizes(ConsoleUI consoleUI) {
        super(consoleUI);
        description = "Вывести список разыгрываемых игрушек";
    }
    public void execute(){
        consoleUI.ShowListPrizes();
    }
}
