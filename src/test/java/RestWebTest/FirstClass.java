package RestWebTest;

import io.restassured.RestAssured;
import io.restassured.response.Response;
//import org.junit.Assert;
import org.testng.Assert;
import org.junit.Test;
import org.testng.asserts.SoftAssert;

public class FirstClass {
    String[] idList = {"200007", "abcd", "5456"};


    @Test
    public void GetRequest() {

        String mainUrl = "https://finanzen.check24.de/accounts/r/frs/productInfo/kreditkarte/{id}";


        for (int i = 0; i < this.idList.length; i++) {
            String id = this.idList[i];
            // String url = "https://finanzen.check24.de/accounts/r/frs/productInfo/kreditkarte/{id}";

            String tempUrl = mainUrl;
            tempUrl = tempUrl.replace("{id}", id);
            System.out.println(tempUrl);
            //customConnector.setUrl(tempUrl);
            System.out.println("Testing API with " + id);
            Response response = RestAssured.get(tempUrl);
            SoftAssert softAssertion = new SoftAssert();
            //int responseCode = con.getResponseCode();
            System.out.println("GET Response Code :: " + response.getStatusCode());
            Assert.assertEquals(response.getStatusCode(), 200, "Mismatch");
            org.junit.Assert.assertEquals(200, response.getStatusCode());
            //customConnector.sendGET();
        }


    }
}
