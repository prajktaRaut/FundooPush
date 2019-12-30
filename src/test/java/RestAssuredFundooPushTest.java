import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Headers;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class RestAssuredFundooPushTest {

    @Before
    public void setUp() throws Exception {
        RestAssured.baseURI="https://fundoopush-backend-dev.bridgelabz.com";
    }

    @Test
    public void givenUser_LoginWithRegistredEmailAndPassword_ShouldReturnLoginSuccessfully() {
        Response response=RestAssured.given()
                                        .contentType(ContentType.JSON)
                                        .accept(ContentType.JSON)
                                        .body("{\"email\":\"prajktasraut16@gmail.com\",\"password\":\"123456\"}")
                                        .when()
                                        .post("/login");
        int statusCode = response.getStatusCode();
        String resAsString = response.asString();
        JsonPath jsonPath = new JsonPath(resAsString);
        String message = jsonPath.getString("message");
        Assert.assertEquals(200,statusCode);
        Assert.assertEquals("Logged in Successfully",message);
    }

    @Test
    public void givenUser_LoginWithBlankEmailAndPassword_ShouldReturnEmailIsRequired() {
        Response response=RestAssured.given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body("{\"email\":\"\",\"password\":\"123456\"}")
                .when()
                .post("/login");
        int statusCode = response.getStatusCode();
        String resAsString = response.asString();
        JsonPath jsonPath = new JsonPath(resAsString);
        String message = jsonPath.getString("message");
        Assert.assertEquals(400,statusCode);
        Assert.assertEquals("email is required",message);
    }

    @Test
    public void givenUser_LoginWithEmailAndBlankPassword_ShouldReturnPasswordIsRequired() {
        Response response=RestAssured.given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body("{\"email\":\"prajktasraut16@gmail.com\",\"password\":\"\"}")
                .when()
                .post("/login");
        int statusCode = response.getStatusCode();
        String resAsString = response.asString();
        JsonPath jsonPath = new JsonPath(resAsString);
        String message = jsonPath.getString("message");
        Assert.assertEquals(400,statusCode);
        Assert.assertEquals("password is required",message);

    }

    @Test
    public void givenUser_LoginWithBlankEmailAndBlankPassword_ShouldReturnEmailIsRequired() {
        Response response=RestAssured.given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body("{\"email\":\"\",\"password\":\"\"}")
                .when()
                .post("/login");
        int statusCode = response.getStatusCode();
        String resAsString = response.asString();
        JsonPath jsonPath = new JsonPath(resAsString);
        String message = jsonPath.getString("message");
        Assert.assertEquals(400,statusCode);
        Assert.assertEquals("email is required",message);
    }

    @Test
    public void givenUser_RegisterWithNewEmailAndPassword_ShouldReturnRegisterSuccessfully() {
        Response response=RestAssured.given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body("{\"email\":\"prajktaraut123@gmail.com\",\"password\":\"12345\"}")
                .when()
                .post("/registration");
        int statusCode = response.getStatusCode();
        String resAsString = response.asString();
        JsonPath jsonPath = new JsonPath(resAsString);
        String message = jsonPath.getString("message");
        Assert.assertEquals(201,statusCode);
        Assert.assertEquals("Registered Successfully",message);
    }

    @Test
    public void givenUser_RegisterWithAlreadyPresentEmailAndPassword_ShouldReturnalreadyExists() {
        Response response=RestAssured.given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body("{\"email\":\"prajktaraut123@gmail.com\",\"password\":\"12345\"}")
                .when()
                .post("/registration");
        int statusCode = response.getStatusCode();
        String resAsString = response.asString();
        JsonPath jsonPath = new JsonPath(resAsString);
        String message = jsonPath.getString("message");
        Assert.assertEquals(409,statusCode);
        Assert.assertEquals("Email id already exists",message);
    }

    @Test
    public void givenUser_RegisterWithBlankEmailAndPassword_ShouldReturnEmailIsRequired() {
        Response response=RestAssured.given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body("{\"email\":\"\",\"password\":\"123456\"}")
                .when()
                .post("/registration");
        int statusCode = response.getStatusCode();
        String resAsString = response.asString();
        JsonPath jsonPath = new JsonPath(resAsString);
        String message = jsonPath.getString("message");
        Assert.assertEquals(400,statusCode);
        Assert.assertEquals("email is required",message);
    }

    @Test
    public void givenUser_RegisterWithEmailAndBlankPassword_ShouldReturnPasswordIsRequired() {
        Response response=RestAssured.given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body("{\"email\":\"prajktasraut16@gmail.com\",\"password\":\"\"}")
                .when()
                .post("/registration");
        int statusCode = response.getStatusCode();
        String resAsString = response.asString();
        JsonPath jsonPath = new JsonPath(resAsString);
        String message = jsonPath.getString("message");
        Assert.assertEquals(400,statusCode);
        Assert.assertEquals("password is required",message);
    }

    @Test
    public void givenUser_LogOutWithValidToken_ShouldReturnLogoutSuccessfully() {
        Response response=RestAssured.given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("token","eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJkYXRhIjp7Il9pZCI6IjVlMDk4MmRlNGQyMjY3MDAzMjUzMGYwZSJ9LCJpYXQiOjE1Nzc3MTEwNDUsImV4cCI6MTU3Nzc5NzQ0NX0.nMPMrsGe4IoRsvfCAUz7KypmNrGLa9pgFpxd3q_jxuo")
                .when()
                .post("/logout");
        int statusCode = response.getStatusCode();
        String resAsString = response.asString();
        JsonPath jsonPath = new JsonPath(resAsString);
        String message = jsonPath.getString("message");
        Assert.assertEquals(200,statusCode);
        Assert.assertEquals("Logged out successfully from the system",message);
    }
}

