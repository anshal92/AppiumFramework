package test.api_test;

import framework.api_autoamtion.ApiUtils;
import io.restassured.response.Response;

public class LoginApiHelper {
    ApiUtils apiUtils = new ApiUtils();
    public Response getUserToken(String baseUri, String token, String pathToHit){
            Response res = apiUtils.getApi(baseUri, token, pathToHit);
        return res;
    }

    public Response createUserLoginData(String baseUri, String token, String pathToHit, String payLoad){
        Response res = apiUtils.postApi(baseUri, token, pathToHit, payLoad);
        return res;
    }

    public Response getUserData(String baseUri, String token, String pathToHit){
        Response res = apiUtils.getApi(baseUri, token, pathToHit);
        return res;
    }

    public Response getUserDataByName(String baseUri, String token, String pathToHit, String userToBeChecked){
        Response res = apiUtils.getApi(baseUri,token, pathToHit+userToBeChecked);
        return res;
    }
}
