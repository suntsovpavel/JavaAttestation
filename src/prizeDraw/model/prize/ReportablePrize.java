package prizeDraw.model.prize;
import prizeDraw.model.Reportable;
import java.io.Serializable;

public class ReportablePrize implements Reportable<Prize>, Serializable {

    @Override
    public String getInfo(Prize prize) {
        StringBuilder result = new  StringBuilder();
        result.append("(");
        result.append(String.format("id: %d, ", prize.id));
        result.append(String.format("Наименование: %s, ", prize.name));
        result.append(String.format("Количество: %d, ", prize.amount));
        result.append(String.format("Вес (частота выпадения): %d", prize.weight));
        result.append(")");
        return result.toString();
    }
}
