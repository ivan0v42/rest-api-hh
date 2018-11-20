import static io.restassured.RestAssured.get;

import io.restassured.response.Response;
import org.json.JSONArray;
import org.json.JSONException;
import org.testng.Assert;
import org.testng.annotations.Test;
/**
 * Created by artem on 20.11.2018.
 */
public class RestAssuredTestEmployers {

    @Test(description = "GET")
    public void getRequestExampleTest() throws JSONException {
        Response response = get("https://api.hh.ru/employers/668019");
        JSONArray jsonResponse = new JSONArray('['+response.asString()+']');
        String employer_id = jsonResponse.getJSONObject(0).getString("id");
        System.out.println(employer_id);
        Assert.assertEquals(employer_id, "668019");
    }

}





