package com.pages;

import io.cucumber.datatable.DataTable;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;

import java.util.Calendar;

public class BenefitCalculator extends BaseClass {

    Actions actions = new Actions(driver);
    private final By supportCalculatorHeading = By.xpath("//div[@data-testid='mastheadSubtitle']/span");
    private final By startBtn = By.xpath("//*[contains(text(), 'Start')]/parent::button");
    private final By notice = By.xpath("//*[contains(text(), 'Important to note')]");
    private final By yearOfBirth = By.xpath("//div[@id='personalInfo.yearOfBirth-container']");
    private final By yearOfBirthInput = By.xpath("//input[@aria-labelledby='personalInfo.yearOfBirth']");
    private final By assessableIncome = By.xpath("//div[@id='personalInfo.assessableIncome-container']");
    private final By assessableIncomeInput = By.xpath("//input[@aria-labelledby='personalInfo.assessableIncome']");
    //    private final By noRadioBtnForMoreThan1Property = By.xpath("//input[@name='property.ownsMoreThanOneProperty']");
    private final By noRadioBtnForMoreThan1Property = By.xpath("//*[@id='property.ownsMoreThanOneProperty']/div[2]//label[1]");
    private final By yesRadioBtnForMoreThan1Property = By.xpath("//*[@id='property.ownsMoreThanOneProperty']/div[2]//label[2]");
    private final By propertyOwnerShipDropDown = By.xpath("//div[@id='property.ownsPropertyOfResidence-container']");
    private final By propertyOwnerShipDropDownInput = By.xpath("//input[@aria-labelledby='property.ownsPropertyOfResidence']");
    private final By housingTypeDropDown = By.xpath("//div[@id='property.typeOfPropertyOfResidence-container']");
    private final By housingTypeDropDownInput = By.xpath("//input[@aria-labelledby='property.typeOfPropertyOfResidence']");
    private final By addNewMemberBtn = By.xpath("//*[contains(text(), 'Add household member')]/parent::button");
    private final By memberYOB = By.xpath("//div[@id='member.0.yearOfBirth-container']");
    private final By memberYOBInput = By.xpath("//input[@aria-labelledby='member.0.yearOfBirth']");
    private final By memberAssessableIncome = By.xpath("//div[@id='member.0.assessableIncome-container']");
    private final By memberAssessableIncomeInput = By.xpath("//input[@aria-labelledby='member.0.assessableIncome']");
    private final By showEstimateBenefitBtn = By.xpath("//*[contains(text(), 'Show estimated benefits')]/parent::button");
    private final By totalEstimateValue = By.xpath("//span[@id='2024-aggregate']");
    private final By individualBenefitValue = By.xpath("//span[@id='2024-You-sum']");
    private final By householdBenefitValue = By.xpath("//span[@id='2024-household-sum']");
    private final By memberBenefitValue = By.xpath("//span[@id='2024-Member1-sum']");
    private final By yearOfBirthValidationErrorMsg = By.xpath("//div[@id='personalInfo.yearOfBirth-container']/ancestor-or-self::fieldset/div[3]/div/span");
    private final By propertyValidationErrorMsg = By.xpath("//*[@id='property.ownsMoreThanOneProperty']/ancestor-or-self::fieldset/div[3]/div/span");
    private final By propertyOwnershipValidationErrorMsg = By.xpath("//input[@aria-labelledby='property.ownsPropertyOfResidence']/ancestor-or-self::fieldset/div[3]/div/span");
    private final By housingTypeValidationErrorMsg = By.xpath("//input[@aria-labelledby='property.typeOfPropertyOfResidence']/ancestor-or-self::fieldset/div[3]/div/span");

    public BenefitCalculator() {
        super();
    }

    public void checkSupportCalculatorPage() {
        waitForElementToBeVisible(supportCalculatorHeading);
    }

    public void acceptTheNotice() throws InterruptedException {
        waitForElementToBeVisible(notice);
        scrollAndWait("window.scrollBy(0,document.body.scrollHeight)");
        waitForElementToBeVisible(startBtn).click();
    }

    public void inputUserSpecificInformation(int memberCount, DataTable table) throws InterruptedException {
        Thread.sleep(1000);
        doClick(yearOfBirth);
        waitForElementToBeClickable(yearOfBirthInput).sendKeys(table.cell(1, 1));
        waitForElementToBeClickable(yearOfBirthInput).sendKeys(Keys.ENTER);
        if (isUser21YearsOld(table.cell(1, 1))) {
            doClick(assessableIncome);
            waitForElementToBeClickable(assessableIncomeInput).sendKeys(table.cell(2, 1));
            waitForElementToBeClickable(assessableIncomeInput).sendKeys(Keys.ENTER);
        }
        scrollAndWait("window.scroll(0,1000)");
        if (table.cell(3, 1).equals("No")) {
            scrollAndWait("window.scroll(0,1000)");
            waitForElementToBeClickable(noRadioBtnForMoreThan1Property).click();
        } else {
            scrollAndWait("window.scroll(0,1000)");
            doClick(yesRadioBtnForMoreThan1Property);
        }
        scrollAndWait("window.scroll(0,1000)");
        waitForElementToBeClickable(propertyOwnerShipDropDown).click();
        waitForElementToBeClickable(propertyOwnerShipDropDownInput).sendKeys(table.cell(4, 1));
        waitForElementToBeClickable(propertyOwnerShipDropDownInput).sendKeys(Keys.ENTER);
        waitForElementToBeClickable(housingTypeDropDown).click();
        waitForElementToBeClickable(housingTypeDropDownInput).sendKeys(table.cell(5, 1));
        waitForElementToBeClickable(housingTypeDropDownInput).sendKeys(Keys.ENTER);
        inputUserMemberDetails(table, memberCount);
        waitForElementToBeVisible(showEstimateBenefitBtn).click();
    }

    private void inputUserMemberDetails(DataTable table, int memberCount) throws InterruptedException {
        if (table.cell(6, 1).equals("Yes")) {
            for (int i = 0; i < memberCount; i++) {
                waitForElementToBeVisible(addNewMemberBtn).click();
                waitForElementToBeVisible(By.xpath("//div[@id='member." + i + ".yearOfBirth-container']")).click();
                waitForElementToBeVisible(By.xpath("//input[@aria-labelledby='member." + i + ".yearOfBirth']")).sendKeys(table.cell(7, 1));
                waitForElementToBeVisible(By.xpath("//input[@aria-labelledby='member." + i + ".yearOfBirth']")).sendKeys(Keys.ENTER);
                waitForElementToBeVisible(By.xpath("//div[@id='member." + i + ".assessableIncome-container']")).click();
                waitForElementToBeVisible(By.xpath("//input[@aria-labelledby='member." + i + ".assessableIncome']")).sendKeys(table.cell(8, 1));
                waitForElementToBeVisible(By.xpath("//input[@aria-labelledby='member." + i + ".assessableIncome']")).sendKeys(Keys.ENTER);
                scrollAndWait("window.scrollBy(0,document.body.scrollHeight)");
            }
        }
    }

    private boolean isUser21YearsOld(String age) {
        int current_year = Calendar.getInstance().get(Calendar.YEAR);
        int calculated_year = current_year - Integer.parseInt(age);
        if (calculated_year >= 21)
            return true;
        else
            return false;
    }

    private void scrollAndWait(String script) throws InterruptedException {
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript(script);
        Thread.sleep(1000);
    }

    public void verifyTheResults(DataTable table) throws InterruptedException {
        scrollAndWait("window.scroll(0,500)");
        String total_estimate_value = waitForElementToBeClickable(totalEstimateValue).getText();
        System.out.println("total_estimate_value value:" + total_estimate_value);
        String individual_benefit_value = waitForElementToBeVisible(individualBenefitValue).getText();
        String household_benefit_value = waitForElementToBeVisible(householdBenefitValue).getText();
        Assert.assertEquals(individual_benefit_value, table.cell(1, 1));
        Assert.assertEquals(household_benefit_value, table.cell(2, 1));
        Assert.assertEquals(total_estimate_value, table.cell(3, 1));
    }

    public void verifyTheResultsForMember(int memberCount, DataTable table) throws InterruptedException {
        scrollAndWait("window.scroll(0,500)");
        String total_estimate_value = waitForElementToBeClickable(totalEstimateValue).getText();
        System.out.println("total_estimate_value value:" + total_estimate_value);
        String individual_benefit_value = waitForElementToBeVisible(individualBenefitValue).getText();
        String household_benefit_value = waitForElementToBeVisible(householdBenefitValue).getText();
        Assert.assertEquals(individual_benefit_value, table.cell(1, 1));
        Assert.assertEquals(household_benefit_value, table.cell(2, 1));
        Assert.assertEquals(total_estimate_value, table.cell(4, 1));
        for (int i = 1; i <= memberCount; i++) {
            String member_benefit_value = waitForElementToBeVisible(By.xpath("//span[@id='2024-Member" + i + "-sum']")).getText();
            Assert.assertEquals(member_benefit_value, table.cell(3, 1));
        }
    }

    public void clickGetEstimateBtn() throws InterruptedException {
        scrollAndWait("window.scrollBy(0,document.body.scrollHeight)");
        waitForElementToBeVisible(showEstimateBenefitBtn).click();
    }

    public void validateErrorMessage(String errorMsg) {
        String yobValidationErrorMsg = waitForElementToBeVisible(yearOfBirthValidationErrorMsg).getText();
        String noOfPropertyValidationErrorMsg = waitForElementToBeVisible(propertyValidationErrorMsg).getText();
        String poValidationErrorMsg = waitForElementToBeVisible(propertyOwnershipValidationErrorMsg).getText();
        String hTypeValidationErrorMsg = waitForElementToBeVisible(housingTypeValidationErrorMsg).getText();
        Assert.assertEquals(yobValidationErrorMsg, errorMsg);
        Assert.assertEquals(noOfPropertyValidationErrorMsg, errorMsg);
        Assert.assertEquals(poValidationErrorMsg, errorMsg);
        Assert.assertEquals(hTypeValidationErrorMsg, errorMsg);
    }
}
