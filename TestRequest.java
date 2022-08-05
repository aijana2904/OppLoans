import org.json.simple.JSONObject;

public class TestRequest {
    String socialSecurityNumber;
    String leadOfferId;
    String email;
    String stateCode;
    int requestedLoanAmount;
    int grossMonthlyIncome;

    public TestRequest() {
        this.socialSecurityNumber = "123456780";
        this.leadOfferId = "20160912-21EC2020-3AEA-4069-A2DD-08002B30309D";
        this.email = "test_customer@gmail.com";
        this.stateCode = "FL";
        this.requestedLoanAmount = 4000;
        this.grossMonthlyIncome = 2800;
    }

    public void setSocialSecurityNumber(String socialSecurityNumber) {
        this.socialSecurityNumber = socialSecurityNumber;
    }

    public void setLeadOfferId(String leadOfferId) {
        this.leadOfferId = leadOfferId;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setStateCode(String stateCode) {
        this.stateCode = stateCode;
    }

    public void setRequestedLoanAmount(int requestedLoanAmount) {
        this.requestedLoanAmount = requestedLoanAmount;
    }

    public void setGrossMonthlyIncome(int grossMonthlyIncome) {
        this.grossMonthlyIncome = grossMonthlyIncome;
    }

    public String getJSONString() {
        JSONObject requestOut = new JSONObject();
        requestOut.put("socialSecurityNumber", this.socialSecurityNumber);
        requestOut.put("leadOfferId", this.leadOfferId);
        requestOut.put("email", this.email);
        requestOut.put("stateCode", this.stateCode);
        requestOut.put("requestedLoanAmount", this.requestedLoanAmount);
        requestOut.put("grossMonthlyIncome", this.grossMonthlyIncome);

        return requestOut.toString();
    }
}
