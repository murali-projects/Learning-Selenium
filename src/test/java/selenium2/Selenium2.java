package selenium2;


import java.io.File;
import java.util.List;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class Selenium2 {

    @Test
    public void selenium2() {
        WebDriver driver = new FirefoxDriver();

        String htmlPath = this.createPathToHtml("selenium2.html");
        System.out.println(String.format("Will open browser at '%s'", htmlPath));
        driver.get(htmlPath);
        
        this.waitForAwhile();

        WebElement adminCheckbox;
        // CSS Selector
        adminCheckbox = driver.findElement(By.cssSelector("#filter-by-role input[value=Admin]"));
        // ...or equivalent XPath
        adminCheckbox = driver.findElement(By.xpath("//div[@id='filter-by-role']/input[@value='Admin']"));
        adminCheckbox.click();

        this.waitForAwhile();
        
        // XPath magic :>        
        WebElement removeLink = driver.findElement(By.xpath("//table//tr[contains(td[1], '3')]/td/a[text() = 'Remove']"));
        removeLink.click();
        
        this.waitForAwhile();
        
        WebElement saveButton = driver.findElement(By.cssSelector("#save-discard button:nth-child(1)"));
        saveButton.click();
        
        this.waitForAwhile();
        
        Alert alert = driver.switchTo().alert();
        alert.accept();

        this.waitForAwhile();
        
        driver.quit();
    }

    private String createPathToHtml(String htmlFilename) {
        char sc = File.separatorChar;
        String currentDir = System.getProperty("user.dir");
        String resourcesPath = currentDir + sc + "src" + sc + "test" + sc + "resources";
        String htmlPath = resourcesPath + sc + htmlFilename;
        return htmlPath;
    }

    private void waitForAwhile() {
        try {
            Thread.sleep(2 * 1000);
        } catch (InterruptedException ex) {
            // for simplicity we don't care
        }
    }
}
