package e2e.pages;

import e2e.wait.Wait;
import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.awt.*;
import java.awt.Dimension;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class BasePage {
    public WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public Wait getWait(){
        return new Wait(driver);
    }

    public Select getSelect(WebElement element){
        return new Select(element);

    }
    protected boolean isElementDisplayed(WebElement element)  {

        try {
            element.isDisplayed();
            return true;
        }catch (NoSuchElementException e){
            return false;
        }
    }
    protected void setInput(WebElement input,String value){
        input.click();
        input.clear();
        input.sendKeys(value);
    }
    private File takeScreenshot(WebElement element) {
        File tmp;
        if (element == null) {
            tmp = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            System.out.println("Take screenshot page");
        } else {
            tmp = element.getScreenshotAs(OutputType.FILE);
            System.out.println("Take screenshot element");
        }

        return tmp;
    }
    private double calculateMaxDifferentPercentRation(){
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = screenSize.width;
        int height = screenSize.height;

        return 0.01 * width * height;

    }
    private Process setCompareCommandToTerminal(String refImgFilePath,String tmpFilePath) throws IOException {
        ProcessBuilder pb = new ProcessBuilder("C:\\Program Files\\ImageMagick-7.1.1-Q16\\magick.exe", "compare", "-metric", "AE", refImgFilePath, tmpFilePath, "null:");
        System.out.println("set compare command to terminal");
        return pb.start();
    }
    private  double getDifferenceFromLogs(BufferedReader reader) throws IOException {
        String line;
        double difference = 0;
        while ((line = reader.readLine()) != null) {
            difference = Integer.parseInt(line.trim());
        }
        return difference;
    }
    protected void takeAndCompareScreenshot(String actualScreenshotName, WebElement element)  {
        String referenceImageFilePath = "reference/" + actualScreenshotName + ".png";
        String tmpFilePath = "reference/tmp_" + actualScreenshotName + ".png";
        File tmp = takeScreenshot(element);
        try {
            Files.copy(tmp.toPath(), new File(tmpFilePath).toPath(), StandardCopyOption.REPLACE_EXISTING);
            File referenceImageFile = new File(referenceImageFilePath);
            if (!referenceImageFile.exists()) {
                throw new RuntimeException("Reference image file does not exist ,but there is tmp file, need remove tmp_ from name file" + tmpFilePath);
            }
            double maxDiffPercent = calculateMaxDifferentPercentRation();
            Process process = setCompareCommandToTerminal(referenceImageFilePath, tmpFilePath);
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getErrorStream()));
            double difference = getDifferenceFromLogs(reader);
            reader.close();
            process.destroy();

            if (difference > maxDiffPercent) {
                throw new RuntimeException(referenceImageFilePath + " not equal " + tmpFilePath + " difference: " + difference);
            }
            Files.deleteIfExists(new File(tmpFilePath).toPath());
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
