package StepDefinitions;

import com.pages.BenefitCalculator;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

public class UserBenefitCalculator {

    private final BenefitCalculator benefitCalculator = new BenefitCalculator();

    @Given("^User is on support calculator page$")
    public void userNavigatesToContactDetailsSection() {
        benefitCalculator.checkSupportCalculatorPage();
    }

    @Then("User reads the important notice and start the assessment")
    public void userReadsTheImportantNoticeAndStartTheAssessment() throws InterruptedException {
        benefitCalculator.acceptTheNotice();
    }

    @Then("User provides his personal information")
    public void userProvidesHisPersonalInformation(DataTable table) throws InterruptedException {
        benefitCalculator.inputUserSpecificInformation(1, table);
    }

    @And("Validate the total estimate for the Individual and household benefits")
    public void validateTheTotalEstimateForTheIndividualAndHouseholdBenefits(DataTable table) throws InterruptedException {
        benefitCalculator.verifyTheResults(table);
    }

    @And("Validate the total estimate for the Individual and household and member benefits")
    public void validateTheTotalEstimateForTheIndividualAndHouseholdAndMemberBenefits(DataTable table) throws InterruptedException {
        benefitCalculator.verifyTheResultsForMember(1, table);
    }

    @Then("User provides no information and clicks the estimate button")
    public void userProvidesNoInformationAndClicksTheEstimateButton() throws InterruptedException {
        benefitCalculator.clickGetEstimateBtn();
    }

    @And("Validate the error message {string} for each web elements")
    public void validateTheErrorMessageForEachWebElements(String errorMsg) {
        benefitCalculator.validateErrorMessage(errorMsg);
    }

    @Then("User provides his personal information and {int} other member details")
    public void userProvidesHisPersonalInformationAndOtherMemberDetails(int memberCount, DataTable table) throws InterruptedException {
        benefitCalculator.inputUserSpecificInformation(memberCount, table);

    }

    @Then("Validate the total estimate for the Individual and household and {int} member benefits")
    public void validateTheIndividualAndHouseholdAndMemberBenefits(int memberCount, DataTable table) throws InterruptedException {
        benefitCalculator.verifyTheResultsForMember(memberCount, table);
    }
}