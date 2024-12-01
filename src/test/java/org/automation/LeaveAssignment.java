package org.automation;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.Keys;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class LeaveAssignment {

    public static void assignLeave(WebDriver driver, String employeeName, String leaveType, String fromDate, String toDate, String comment) {
        try {
            // Click "Assign Leave"
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement assignLeaveButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@title='Assign Leave']")));
            assignLeaveButton.click();
            System.out.println("Assign Leave button clicked.");

            // Select Employee
            WebElement employeeField = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@placeholder='Type for hints...']")));
            employeeField.sendKeys(employeeName);
            TimeUnit.SECONDS.sleep(5);  // Sleep to simulate delay for suggestions
            employeeField.sendKeys(Keys.ARROW_DOWN);
            employeeField.sendKeys(Keys.ENTER);
            System.out.println("Employee '" + employeeName + "' selected.");

            // Select Leave Type
            WebElement leaveTypeDropdown = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(@class, 'oxd-select-text')]")));
            leaveTypeDropdown.click();
            WebElement leaveTypeOption = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='" + leaveType + "']")));
            leaveTypeOption.click();
            System.out.println("Leave type '" + leaveType + "' selected.");

            // Set dates (you can create a helper method to handle setting dates)
            setDate(driver, "(//input[@placeholder='yyyy-dd-mm'])[1]", fromDate, "From Date");
            setDate(driver, "(//input[@placeholder='yyyy-dd-mm'])[2]", toDate, "To Date");

            // Add Comment
            WebElement commentField = driver.findElement(By.xpath("//textarea"));
            commentField.sendKeys(comment);
            System.out.println("Comment added.");

            OverlayHandler.handleOverlay(driver, true);
            TimeUnit.SECONDS.sleep(10);

            // Submit
            WebElement submitButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@type='submit']")));
            submitButton.click();
            System.out.println("Leave assigned successfully.");

        } catch (Exception e) {
            System.out.println("Error assigning leave: " + e.getMessage());
        }
    }

    private static void setDate(WebDriver driver, String xpath, String date, String label) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement dateField = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));

            // Click to focus on the field
            dateField.click();

            // Clear the field using JavaScript if it's not already empty
            String currentValue = dateField.getAttribute("value");
            if (currentValue != null && !currentValue.isEmpty()) {
                // Simulate backspace to clear the field if there is a value
                for (int i = 0; i < currentValue.length(); i++) {
                    dateField.sendKeys(Keys.BACK_SPACE);
                }
            }

            // Enter the new date
            dateField.sendKeys(date);
            dateField.sendKeys(Keys.ENTER);
            System.out.println(label + " set to " + date);
        } catch (Exception e) {
            System.out.println("Error setting date for " + label + ": " + e.getMessage());
        }
    }
}
