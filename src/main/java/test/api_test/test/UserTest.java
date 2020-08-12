package test.api_test.test;

import com.github.tomakehurst.wiremock.WireMockServer;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import test.api_test.LoginApiHelper;
import test.api_test.LoginDp;

public class UserTest extends LoginDp {
    String baseUri = "http://localhost:8080";
    LoginApiHelper loginApiHelper = new LoginApiHelper();
    WireMockServer wireMockServer;
    String stubPayLoad = "{\n" +
            "    \"email\": \"hitesh.28jan@gmail.com\",\n" +
            "    \"name\": \"hitesh\",\n" +
            "    \"time\": 1540801676.559354\n" +
            "}\n";

    @BeforeClass
    void setUp() {
        wireMockServer = new WireMockServer();
        wireMockServer.start();
        wireMockServer.stubFor(get(urlEqualTo("/get_token")).willReturn(aResponse()
                .withStatus(200).withHeader("Content-Type", "application/json").withBody("{\"token\":\"harshit\"}")));
    }

    @AfterClass
    void breakDown() {
        wireMockServer.stop();
    }

    @Test()
    void getTokenTest() {
        System.out.println("------------------- Test Started for getToken --------------------");
        String pathToHit = "/get_token";

        Response response = loginApiHelper.getUserToken(baseUri, "", pathToHit);

        /** Assertion */
        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(response.jsonPath().getString("token"), "harshit");
        System.out.println("XXXXXXXXXXXXXXXXXXXXXX Test Completed for getToken XXXXXXXXXXXXXXXXXXXXXX");
    }

    @Test(dataProvider = "postCreateUserData")
    void postUserLoginData(CreateUserDataScenario scenario, String data) {
        System.out.println("------------------- Test Started for " +scenario.toString()+ "--------------------");
        String pathToHit = "/user";

        String toSendPL = "{\n" +
                "    \"username\": \"hitesh\",\n" +
                "    \"email\": \"hitesh.28jan@gmail.com\"\n" +
                "}\n";

        switch (scenario){
            case PROPERDATA:
                wireMockServer.stubFor(post(urlEqualTo(pathToHit)).withRequestBody(equalToJson(toSendPL)).willReturn(aResponse()
                        .withStatus(201).withHeader("Content-Type", "application/json").withBody(stubPayLoad)));
                break;
            case INVALIDPAYLOAD:
                wireMockServer.stubFor(post(urlEqualTo(pathToHit)).willReturn(aResponse()
                        .withStatus(400)));
                break;
            case INVALIDHEADERFORCREATEDATA:
                wireMockServer.stubFor(post(urlEqualTo(pathToHit)).willReturn(aResponse()
                        .withStatus(401)));
                break;
            case STALEDATA:
                wireMockServer.stubFor(post(urlEqualTo(pathToHit)).willReturn(aResponse()
                        .withStatus(409)));
                break;

        }

        Response getTokenResponse = loginApiHelper.getUserToken(baseUri, "", "/get_token");
        String token = getTokenResponse.jsonPath().getString("token");

        switch (scenario) {
            case PROPERDATA:
            case STALEDATA:
                break;
            case INVALIDPAYLOAD:
                toSendPL = "";
                break;
            case INVALIDHEADERFORCREATEDATA:
                token = "";
                break;
        }

        Response createUserResponse = loginApiHelper.createUserLoginData(baseUri, token, pathToHit, toSendPL);

        /** Assertion */
        switch (scenario) {
            case PROPERDATA:
                Assert.assertEquals(createUserResponse.getStatusCode(), 201);
                String email = createUserResponse.jsonPath().getString("email");
                String name = createUserResponse.jsonPath().getString("name");
                SoftAssert softAssert = new SoftAssert();
                softAssert.assertEquals(email, "hitesh.28jan@gmail.com");
                softAssert.assertEquals(name, "hitesh");
                softAssert.assertAll();
                break;
            case INVALIDPAYLOAD:
                Assert.assertEquals(createUserResponse.getStatusCode(), 400);
                break;
            case INVALIDHEADERFORCREATEDATA:
                Assert.assertEquals(createUserResponse.getStatusCode(), 401);
                break;
            case STALEDATA:
                Assert.assertEquals(createUserResponse.getStatusCode(), 409);
                break;
        }
        System.out.println("XXXXXXXXXXXXXXXXXXXXXX Test Completed for " + scenario.toString()+ " XXXXXXXXXXXXXXXXXXXXXX");
    }

    @Test(dataProvider = "getCreatedUserData")
    void getUserData(GetUserDataScenario scenario, String data) {
        System.out.println("------------------- Test Started for " +scenario.toString()+ "--------------------");
        String pathToHit = "/user";

        //Stub
        wireMockServer.stubFor(get(urlEqualTo("/get_token")).willReturn(aResponse()
                .withStatus(200).withHeader("Content-Type", "application/json").withBody("{\"token\":\"harshit\"}")));


        switch (scenario){
            case VALIDHEADER:
                wireMockServer.stubFor(get(urlEqualTo(pathToHit)).withHeader("Authorization", containing("Bearer")).willReturn(aResponse()
                        .withStatus(200).withHeader("Content-Type", "application/json").withBody(stubPayLoad)));
                break;
            case INVALIDHEADER:
                wireMockServer.stubFor(get(urlEqualTo(pathToHit)).willReturn(aResponse()
                        .withStatus(401)));
                break;
        }

        Response userTokenResponse = loginApiHelper.getUserToken(baseUri, "", "/get_token");
        String tokenName = userTokenResponse.jsonPath().getString("token");

        switch (scenario) {
            case VALIDHEADER:
                break;
            case INVALIDHEADER:
                tokenName = "";
                break;
        }

        Response res = loginApiHelper.getUserData(baseUri, tokenName, pathToHit);

        /** Assertion */
        switch (scenario) {
            case INVALIDHEADER:
                Assert.assertEquals(res.getStatusCode(), 401);
                break;
            case VALIDHEADER:
                String email = res.jsonPath().getString("email");
                String name = res.jsonPath().getString("name");
                Assert.assertEquals(res.getStatusCode(), 200);
                SoftAssert softAssert = new SoftAssert();
                softAssert.assertEquals(email, "hitesh.28jan@gmail.com");
                softAssert.assertEquals(name, "hitesh");
                softAssert.assertAll();
                break;
        }
        System.out.println("XXXXXXXXXXXXXXXXXXXXXX Test Completed for " + scenario.toString()+ " XXXXXXXXXXXXXXXXXXXXXX");
    }

    @Test(dataProvider = "getUserDataByName")
    void getUserDataByName(GetUserDataByNameScenario scenario, String data) {
        System.out.println("------------------- Test Started for " +scenario.toString()+ "--------------------");
        String pathToHit = "/user/";
        String userToCheck = "hitesh";
        switch (scenario) {
            case VALIDNAME:
                break;
            case INVALIDNAME:
                userToCheck = "asdfsa";
                break;
        }

        //Stub
        wireMockServer.stubFor(get(urlEqualTo(pathToHit + "hitesh")).withHeader("Authorization", containing("Bearer")).willReturn(aResponse()
                .withStatus(200).withHeader("Content-Type", "application/json").withBody(stubPayLoad)));

        Response res1 = loginApiHelper.getUserToken(baseUri, "", "/get_token");
        String tokenName = res1.jsonPath().getString("token");
        Response resp = loginApiHelper.getUserDataByName(baseUri, tokenName, pathToHit, userToCheck);
        /** Assertion */
        switch (scenario) {
            case VALIDNAME:
                String email = resp.jsonPath().getString("email");
                String name = resp.jsonPath().getString("name");
                Assert.assertEquals(resp.getStatusCode(), 200);
                SoftAssert softAssert = new SoftAssert();
                softAssert.assertEquals(email, "hitesh.28jan@gmail.com");
                softAssert.assertEquals(name, "hitesh");
                softAssert.assertAll();
                break;
            case INVALIDNAME:
                Assert.assertEquals(resp.getStatusCode(), 404);
                break;
        }
        System.out.println("XXXXXXXXXXXXXXXXXXXXXX Test Completed for " + scenario.toString()+ " XXXXXXXXXXXXXXXXXXXXXX");
    }
}
