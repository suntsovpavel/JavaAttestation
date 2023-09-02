package prizeDraw.view.commands;

import prizeDraw.view.ConsoleUI;

//возвращаем информацию о игрушках в списке разыгрываемых:
public class AddPrize extends Command{
    public AddPrize(ConsoleUI consoleUI) {
        super(consoleUI);
        description = "добавить новую игрушку в список разыгрываемых";
    }
    public void execute(){
        consoleUI.addPrize();
    }
}
