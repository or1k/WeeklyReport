package ReportConstructor;

import BrowserConfig.MyChromeBrowserClass;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.testng.BrowserPerClass;
import com.codeborne.selenide.testng.TextReport;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;


@Listeners({TextReport.class, BrowserPerClass.class})
public class BaseTest {

//    @BeforeTest
//    public void setup(){
//
//    }

    @Test
    public static void createReport(){
        Configuration.browser = MyChromeBrowserClass.class.getName();
        Configuration.startMaximized = true;
        Configuration.holdBrowserOpen = false;
        Configuration.timeout = 10000;
        Testtest.reportCreate();
    }



}
