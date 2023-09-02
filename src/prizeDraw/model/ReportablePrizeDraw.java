package prizeDraw.model;

import java.io.Serializable;

public class ReportablePrizeDraw implements Reportable<PrizeDraw>, Serializable {
    @Override
    public String getInfo(PrizeDraw prizeDraw) {
        return "{ prizes: \n"
                + prizeDraw.getInfo()
                + "\n}";
    }
}
