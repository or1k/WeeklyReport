import org.joda.time.format.PeriodFormatter;
import org.joda.time.format.PeriodFormatterBuilder;

import java.util.ArrayList;

public class vcbcvb {
    public static void main(String[] args) {



        ArrayList<String> test = new ArrayList<String>();
        test.add("1h");
        test.add("2h 5m");



//        String time[] = {"1h 35min","1h", "71min"};
        int hour = 0;
        int mins = 0;
        PeriodFormatter formatter = new PeriodFormatterBuilder()

                .appendHours().appendSuffix("h")
                .appendMinutes().appendSuffix("m")
                .toFormatter();
        for (int i = 0; i < test.size(); i++){
            hour += formatter.parsePeriod(test.get(i).replaceAll(" ","")).getHours();
            mins += formatter.parsePeriod(test.get(i).replaceAll(" ","")).getMinutes();
        }

        int t = (hour*60) + mins;

        int h = t / 60;
        int m = t % 60;
        System.out.printf("%d:%02d", h, m);
    }
}
