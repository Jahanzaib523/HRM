package org.automation;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;

public class Logout {

    public static void logout(WebDriver driver) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement userDropdown = wait.until(ExpectedConditions.elementToBeClickable(By.className("oxd-userdropdown-tab")));
            userDropdown.click();
            WebElement logoutOption = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Logout")));
            logoutOption.click();
            System.out.println("Logged out successfully.");
        } catch (Exception e) {
            System.out.println("Logout failed: " + e.getMessage());
        }
    }
}
