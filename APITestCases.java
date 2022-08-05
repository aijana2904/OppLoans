import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


public class APITestCases {

    private final static String API_KEY = "b813b584-6932-4d0a-909f-43ae22df452a";
    private final static String TARGET_URL = "https://partner-test.opploans.com/api/lde/v4/offer";

    @BeforeTest
    void setUp() {
        System.out.println("setUp");
    }

    @Test
    void testAcceptOffer() {
        RestAssured.baseURI = TARGET_URL;

        RequestSpecification request = RestAssured.given();
        request.header("Content-Type", "application/json");
        request.header("x-api-key", API_KEY);

        TestRequest requestBody = new TestRequest();
        requestBody.setGrossMonthlyIncome(100000);
        requestBody.setRequestedLoanAmount(1500);
        request.body(requestBody.getJSONString());

        Response response = request.post();

        System.out.println("Status Code:");
        System.out.println(response.getStatusCode());
        System.out.println("Response: " + response.asString());

        JsonPath bodyJson = response.jsonPath();
        Assert.assertEquals(true, bodyJson.get("accepted"));
        Assert.assertEquals("APPROVED", bodyJson.get("status"));

    }

    @Test
    void testDeclineCustomer() {

        System.out.println("testDeclineCustomer");
        RestAssured.baseURI = TARGET_URL;

        RequestSpecification request = RestAssured.given();
        request.header("Content-Type", "application/json");
        request.header("x-api-key", API_KEY);

        TestRequest requestBody = new TestRequest();
        requestBody.setSocialSecurityNumber("123450000");
        request.body(requestBody.getJSONString());

        Response response = request.post();

        System.out.println("Status Code:");
        System.out.println(response.getStatusCode());
        System.out.println("Response: " + response.asString());
        JsonPath bodyJson = response.jsonPath();
        Assert.assertEquals(false, bodyJson.get("accepted"));
        Assert.assertEquals("DECLINED", bodyJson.get("status"));
        //Assert.assertArrayEquals(response.getBody());

    }

    @Test
    void testMalformedRequest() {

        System.out.println("testMalformedRequest");
        System.out.println("testDeclineCustomer");
        RestAssured.baseURI = TARGET_URL;

        RequestSpecification request = RestAssured.given();
        request.header("Content-Type", "application/json");
        request.header("x-api-key", API_KEY);

        TestRequest requestBody = new TestRequest();
        requestBody.setSocialSecurityNumber("1234500");
        request.body(requestBody.getJSONString());

        Response response = request.post();

        System.out.println("Status Code:");
        System.out.println(response.getStatusCode());
        System.out.println("Response: " + response.asString());

        JsonPath bodyJson = response.jsonPath();
        Assert.assertEquals(false, bodyJson.get("accepted"));
        Assert.assertEquals("DECLINED", bodyJson.get("status"));
    }
}

