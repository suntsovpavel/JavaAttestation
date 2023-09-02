package prizeDraw.view.commands;

import prizeDraw.view.ConsoleUI;

//возвращаем информацию о игрушках в списке разыгрываемых:
public class Draw extends Command{
    public Draw(ConsoleUI consoleUI) {
        super(consoleUI);
        description = "разыграть призовую игрушку";
    }
    public void execute(){
        consoleUI.draw();
    }
}
