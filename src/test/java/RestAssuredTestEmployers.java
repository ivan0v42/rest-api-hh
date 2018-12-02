import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;


public class RestAssuredTestEmployers {

    private String employerId;
    private int statusCode;
    private String baseUrl;
    private String key;
    private String nameCompany = "СимбирСофт,ООО";


    @BeforeTest
    public void getProps() throws IOException {
        Properties property = new Properties();
        property.load(new FileInputStream("src/test/properties/test-hh-rest-api.properties"));
        employerId = property.getProperty("employerId");
        statusCode = Integer.parseInt(property.getProperty("statusCode"));
        baseUrl = property.getProperty("baseUrl");
        key = property.getProperty("key");
       // nameCompany = property.getProperty("nameCompany"); //проблема c кодировкой IDEA
    }

    @Test
        public void testSearchVacancies() {
            RequestSpecification request = RestAssured.given().header("Content-Type", "application/json\r\n");

            Vacancies vacancies = request
                    .params(key, employerId)
                    .get(baseUrl)
                    .then()
                    .statusCode(statusCode)
                    .extract()
                    .as(Vacancies.class);

            for (VacancyInfo vacancy :
                    vacancies.getAllVacancies()) {
                Assert.assertEquals(vacancy.employer.id, employerId);
                Assert.assertEquals(vacancy.employer.name, nameCompany);
            }
    }
}





