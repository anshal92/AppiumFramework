package test.api_test;

import org.testng.annotations.DataProvider;


import static test.api_test.LoginDp.GetUserDataScenario.*;
import static test.api_test.LoginDp.GetUserDataByNameScenario.*;
import static test.api_test.LoginDp.CreateUserDataScenario.*;

public class LoginDp {

    public enum GetUserDataScenario {VALIDHEADER, INVALIDHEADER}

    public enum GetUserDataByNameScenario {VALIDNAME, INVALIDNAME}

    public enum CreateUserDataScenario {PROPERDATA, INVALIDPAYLOAD, INVALIDHEADERFORCREATEDATA, STALEDATA}

    @DataProvider(name = "getCreatedUserData")
    public Object[][] getCreatedUserData() {
        return new Object[][]{
                {VALIDHEADER, ""},
                {INVALIDHEADER, ""}
        };
    }

    @DataProvider(name = "getUserDataByName")
    public Object[][] getUserByNameData() {
        return new Object[][]{
                {VALIDNAME, "{\n" +
                        "    \"username\": \"hitesh\",\n" +
                        "    \"email\": \"hitesh.28jan@gmail.com\"\n" +
                        "}\n"},
                {INVALIDNAME, ""}
        };
    }

    @DataProvider(name = "postCreateUserData")
    public Object[][] postCreateUserData() {
        return new Object[][]{
                {PROPERDATA, "{\n" +
                        "    \"username\": \"hitesh\",\n" +
                        "    \"email\": \"hitesh.28jan@gmail.com\"\n" +
                        "}\n"},
                {INVALIDHEADERFORCREATEDATA, ""},
                {INVALIDPAYLOAD, ""},
                {STALEDATA, ""}
        };
    }
}
