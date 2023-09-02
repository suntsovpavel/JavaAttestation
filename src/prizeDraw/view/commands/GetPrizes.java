package prizeDraw.view.commands;

import prizeDraw.view.ConsoleUI;

//возвращаем информацию о игрушках в списке разыгрываемых:
public class GetPrizes extends Command{
    public GetPrizes(ConsoleUI consoleUI) {
        super(consoleUI);
        description = "получить игрушки (очистить очередь выдачи)";
    }
    public void execute(){
        consoleUI.getPrizes();
    }
}
