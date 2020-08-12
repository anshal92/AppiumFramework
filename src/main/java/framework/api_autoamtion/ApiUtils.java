package framework.api_autoamtion;

import com.github.tomakehurst.wiremock.matching.StringValuePattern;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class ApiUtils {

    public Response getApi(String baseUri, String token, String pathToHit) {
        RequestSpecBuilder rsq = new RequestSpecBuilder();
        Map<String, String> header = new HashMap<String, String>();
        if (!token.isEmpty()) header.put("Authorization", "Bearer " + token);
        RequestSpecification request = rsq.setBaseUri(baseUri + pathToHit)
                .addHeaders(header)
                .build();
        Response res = given().spec(request).log().all().get();
        System.out.println("Response Code ==> " + res.getStatusCode() + "\n" +
                "Response Body ==> \n" + res.getBody().prettyPrint());
        return res;
    }

    public Response postApi(String baseUri, String token, String pathToHit, String payLoad) {
        RequestSpecBuilder rsb = new RequestSpecBuilder();
        Map<String, String> header = new HashMap<String, String>();
        if (!token.isEmpty()) header.put("Authorization", "Bearer " + token);
        RequestSpecification request = rsb.setBaseUri(baseUri + pathToHit)
                .setContentType(ContentType.JSON)
                .addHeaders(header)
                .setBody(payLoad)
                .build();

        Response res = given().spec(request).log().all().post();
        System.out.println("Response Code ==> " + res.getStatusCode() + "\n" +
                "Response Body ==> \n" + res.getBody().prettyPrint());

        //Response res = request.request(Method.POST, pathToHit);
        return res;
    }
}
