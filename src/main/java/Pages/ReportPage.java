package Pages;

import Utils.Settings;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.joda.time.format.PeriodFormatter;
import org.joda.time.format.PeriodFormatterBuilder;
import org.openqa.selenium.By;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.*;

import static com.codeborne.selenide.Selenide.*;

public class ReportPage {

    private String urlMyWork = "https://maxiproject.atlassian.net/plugins/servlet/ac/is.origo.jira.tempo-plugin/tempo-my-work?project.key=FE1";
    ElementsCollection numbersOfTask = $$(By.xpath("//div[@data-type = 'WORKLOG']//span[contains(@title, 'FE1')]"));
    ElementsCollection titleTask = $$(By.xpath("//*[@data-type=\"WORKLOG\"]/div[1]/div[1]"));
    SelenideElement totalTime = $(By.xpath("//*[@id=\"tempo-container\"]/div/div/div[2]/div[2]/div[2]/div/div/div[2]"));

    public void getReportWithTitle(){
        try {
            $(By.xpath("//h1[text() = 'System Dashboard']")).waitUntil(Condition.visible, 5000);
            open(urlMyWork);
            switchTo().frame(0);
            $(By.xpath("//button[contains(text(), 'Submit period')]")).waitUntil(Condition.visible, 10000);
        }catch (AssertionError ex){
            open(urlMyWork);
            switchTo().frame(0);
            $(By.xpath("//button[contains(text(), 'Submit period')]")).waitUntil(Condition.visible, 10000);
        }
        sleep(1500);



        Map<String, String> m = new HashMap<String, String>();


        for(int i=0;i<numbersOfTask.size();i++){
            m.put(numbersOfTask.get(i).getText(), titleTask.get(i).getAttribute("title"));

        }

        // get all the set of keys
        Set<String> keys = m.keySet();
        try { OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream("WeeklyReport.txt", false), StandardCharsets.UTF_8);
            writer.write("Отчет по затраченному времени за период с " + Settings.getDateRange());
            writer.append('\n');

            for (Object key : keys) {
                writer.write(key + " - " + m.get(key) + " " +totalTimeforTask(key));
                writer.append('\n');
            }

            writer.write("Total hours : " + totalTime.getText());

            writer.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public String totalTimeforTask(Object key){
        ElementsCollection hours = $$(By.xpath("//span[@title='" + key + "']/..//span[@name='tempoCardDuration']"));
        ArrayList<String> test = new ArrayList<String>();

        for (SelenideElement selenideElement : hours) {
            test.add(selenideElement.getText());
        }

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
        return String.format("%d:%02d", h, m);
    }



}
