package ReportConstructor;

import Pages.LoginPage;
import Pages.ReportPage;
import Utils.Settings;
import com.codeborne.selenide.testng.TextReport;
import com.codeborne.selenide.testng.annotations.Report;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.io.IOException;

import static com.codeborne.selenide.Selenide.*;

@Report
@Listeners(TextReport.class)
public class CreateReport {


    @Test
    public static void reportCreate() throws IOException {
        Settings.saveLogin();
        clearBrowserCookies();
        LoginPage loginPage = open("https://maxiproject.atlassian.net", LoginPage.class);
        ReportPage reportPage = loginPage.login();
        reportPage.getReportWithTitle();
        reportPage.openReport();
    }
}
