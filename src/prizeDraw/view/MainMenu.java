package prizeDraw.view;

import prizeDraw.view.commands.*;

import java.util.ArrayList;
import java.util.List;

public class MainMenu {
    private List<Command> commandList;

    public MainMenu(ConsoleUI consoleUI) {
        commandList = new ArrayList<>();
        commandList.add(new ShowListPrizes(consoleUI));   //вывести список разыгрываемых игрушек
        commandList.add(new AddPrize(consoleUI));   //добавить игрушку в список разыгрываемых
        commandList.add(new Draw(consoleUI));     //разыграть призовую игрушку
        commandList.add(new GetPrizes(consoleUI));  // получить игрушки (очистить очередь выдачи)
        commandList.add(new AddAmount(consoleUI));  // добавить количество единиц игрушки по id
        commandList.add(new ChangeWeight(consoleUI));  // изменить вес (вероятность выпадения) игрушки по id
        commandList.add(new SaveToFile(consoleUI));
        commandList.add(new LoadFromFile(consoleUI));
        commandList.add(new Finish(consoleUI));
    }

    public String menu(){
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < commandList.size(); i++) {
            stringBuilder.append(i+1);
            stringBuilder.append(". ");
            stringBuilder.append(commandList.get(i).getDescription());
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }

    public void execute(int choice){
        Command command = commandList.get(choice-1);
        command.execute();
    }

    public int getSize(){
        return commandList.size();
    }
}
