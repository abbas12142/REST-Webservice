package RestWebTest;


import com.google.gson.Gson;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.put;
import static org.hamcrest.Matchers.equalTo;

public class finanzin {

    String url = "https://finanzen.check24.de/accounts/r/frs/productInfo/kreditkarte/";

    @Test(priority = 1)
    public void GETMethod_2000007() {

        given()
                .when()
                .get(url + "200007")
                .then()

                .statusCode(200)
                //.log().body("tabs.general.tabName")
                .body("productId", equalTo(0))
                .body("tabs.general.tabName", equalTo("Allgemein"));

    }

    @Test(priority = 2)
    public void GETMethod_abcd() {

        given()
                .when()
                .get(url + "abcd")
                .then()

                .statusCode(200);
        //.log().body("tabs.general.tabName")
        //.body("productId", equalTo(0))
        // .body("tabs.general.tabName", equalTo("Allgemein"));

    }

    @Test(priority = 3)
    public void Post_Method() {
        HashMap data = new HashMap();
        data.put("name", "morpheus");
        data.put("job", "leader");
        data.put("id", "996");
        data.put("createdAt", "2021-04-13T14:46:49.221Z");
        Gson gson = new Gson();
        String jsondata = gson.toJson(data);

        given()
                .contentType("Application/JSON")
                .body(jsondata)
                .when()
                .post("https://reqres.in/api/users")
                .then()
                .statusCode(201)
                .log().body();


    }


    @Test(priority = 4)
    public void Get_UnSucess_Method() {
        HashMap data = new HashMap();
        data.put("email", "sydney@fife");
        //data.put("job", "leader");
        //data.put("id", "996");
        //  data.put("createdAt", "2021-04-13T14:46:49.221Z");
        Gson gson = new Gson();
        String jsondata = gson.toJson(data);
        Response res =
                given()
                        .contentType("Application/JSON")
                        .body(jsondata)
                        .when()
                        .post("https://reqres.in/api/login")
                        .then()
                        .statusCode(400)
                        .log().body()
                        .extract().response();
        String jsonString = res.asString();
        Assert.assertEquals(jsonString.contains("Missing password"), true);


    }

}
