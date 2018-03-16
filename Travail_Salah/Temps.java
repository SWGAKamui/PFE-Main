
import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


class Temps implements Runnable {
    Thread d;
    DateFormat dateformat = new SimpleDateFormat("dd/MM/yyyy");
    Date date = new Date();
    String dat = dateformat.format(date);

    public void run() {
        Boolean bool = true;
        while (bool) {
            Station_Sol.TDate.setText(" " + dat);
            Time heur;
            heur = new Time(0);
            heur.setTime(System.currentTimeMillis());
            Station_Sol.THeure.setText(" " + heur);
            try {
                d.sleep(1000);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}