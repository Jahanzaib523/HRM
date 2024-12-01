package org.automation;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Main {
    public static void main(String[] args) {
        
        // Set the path to the ChromeDriver executable
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver"); // Replace with your ChromeDriver path

        WebDriver driver = new ChromeDriver();
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        System.out.println("Page title is: " + driver.getTitle());

        try {
            // Login to the application
            Login.login(driver, "Admin", "admin123");

            // Perform leave assignment
            LeaveAssignment.assignLeave(driver, "James Butler", "US - Vacation", "2020-10-19", "2020-10-23", "Vacation leave request for personal reasons.");
        } catch (Exception e) {
            System.out.println("Error occurred: " + e.getMessage());
        } finally {
            // Logout and close the browser
            Logout.logout(driver);
            driver.quit();
        }
    }
}
