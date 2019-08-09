package Utils;

import org.apache.commons.io.FileUtils;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public class DownloadFile {

    public  void downloadUpdate(String url) throws IOException {

        FileUtils.copyURLToFile(new URL("https://github.com/or1k/WeeklyReport/releases/download/2/WeeklyReport_v2.exe"), new File("D:\\javaProject\\Selenium\\WeeklyReport\\lol.exe"));
    }
}
