package org.automation;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;

public class OverlayHandler {

    public static void handleOverlay(WebDriver driver, boolean confirm) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement overlay = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[contains(@class, 'oxd-dialog-sheet')]")));
            System.out.println("Overlay detected.");
            WebElement button = overlay.findElement(By.xpath(".//button[contains(@class, 'oxd-button--secondary')]"));
            if (!confirm) {
                button = overlay.findElement(By.xpath(".//button[contains(@class, 'oxd-button--ghost')]"));
            }
            button.click();
            System.out.println("Overlay handled.");
        } catch (Exception e) {
            System.out.println("Error handling overlay: " + e.getMessage());
        }
    }
}
