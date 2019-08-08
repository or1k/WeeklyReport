package Utils;

import View.Window;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Settings {


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

}
