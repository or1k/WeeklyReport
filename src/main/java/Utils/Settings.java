package Utils;

import View.Window;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import javax.swing.*;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;



public class Settings {

    private static final String USER_AGENT =
            "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/535.1 (KHTML, like Gecko) Chrome/13.0.782.112 Safari/535.1";

    public static String profile() throws IOException {
        File f = new File(System.getProperty("user.dir") + "/profile/users.txt");
        if(f.exists() && f.isFile()) {
            FileReader fr= new FileReader(System.getProperty("user.dir") + "/profile/users.txt");
            Scanner scan = new Scanner(fr);
           return scan.next();
        }
        return null;
    }

    public static void saveLogin() throws IOException {
        OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(System.getProperty("user.dir") + "/profile/users.txt", false), StandardCharsets.UTF_8);
        writer.write(Window.userText.getText());
        writer.append('\n');
        writer.flush();
    }

    public static String getDateRange(){
        LocalDate date = LocalDate.now();
        LocalDate minusWeek = LocalDate.now().minusDays(4);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM");
        String startDay = minusWeek.format(formatter);
        String endDay = date.format(formatter);

    return startDay + " по " + endDay;
    }

    public static boolean checkUpdate(){
        String url = "https://github.com/or1k/WeeklyReport/releases/tag/" + (Window.numberVersion+1);
        Connection connection = Jsoup.connect(url).userAgent(USER_AGENT);
        try {
            Document htmlDocument = connection.get();
            if (connection.response().statusCode() == 200) {// 200 is the HTTP OK status code
                System.out.println("Have new Update");
                return true;
            }
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }
        return false;
    }
}
