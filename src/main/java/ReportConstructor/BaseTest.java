package ReportConstructor;

import BrowserConfig.MyChromeBrowserClass;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.testng.BrowserPerClass;
import com.codeborne.selenide.testng.TextReport;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.io.IOException;


@Listeners({TextReport.class, BrowserPerClass.class})
public class BaseTest {

//    @BeforeTest
//    public void setup(){
//
//    }

    @Test
    public static void createReport() throws IOException {
        Configuration.browser = MyChromeBrowserClass.class.getName();
        Configuration.startMaximized = true;
        Configuration.holdBrowserOpen = false;
        Configuration.timeout = 10000;
        CreateReport.reportCreate();

    }



}
