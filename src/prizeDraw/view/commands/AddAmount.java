package prizeDraw.view.commands;

import prizeDraw.view.ConsoleUI;

//возвращаем информацию о игрушках в списке разыгрываемых:
public class AddAmount extends Command{
    public AddAmount(ConsoleUI consoleUI) {
        super(consoleUI);
        description = "добавить количество единиц игрушки по id";
    }
    public void execute(){
        consoleUI.addAmount();
    }
}
