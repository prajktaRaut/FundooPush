import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Headers;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;

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

    @Test
    public void givenUser_WhenPerformOperationOnRedirect_ShouldReturnRedirectAddedSuccessfully() {
        File testFile = new File("/home/admin1/Downloads/antoine-beauvillain-qrPqGP-SG8w-unsplash.jpg");
        Response response=RestAssured.given()
                .accept(ContentType.JSON)
                .header("token","eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJkYXRhIjp7Il9pZCI6IjVlMDk4MmRlNGQyMjY3MDAzMjUzMGYwZSJ9LCJpYXQiOjE1Nzc3NzE3NTAsImV4cCI6MTU3Nzg1ODE1MH0.Uahb7KIdTLXE9NSuxKpIP0Z2xuaB-4yaCBH9MAOQv4Y")
                .multiPart("image",testFile)
                //.formParam("Content-Type", "multipart/form-data")
                .formParam("title","Image of Nature")
                .formParam("description","Beautiful Image")
                .formParam("redirect_link","www.google.com")
                .formParam("is_published",false)
                .formParam("archive",false)
                .formParam("youtube_flag",false)
                .post("/redirects");
        int statusCode = response.getStatusCode();
        String resAsString = response.asString();
        JsonPath jsonPath = new JsonPath(resAsString);
        String message = jsonPath.getString("message");
        Assert.assertEquals(201,statusCode);
        Assert.assertEquals("Redirect added Successfully",message);
    }

    @Test
    public void givenUser_WhenPerformOperationOnUpdateRedirect_ShouldReturnRedirectUpdatedSuccessfully() {
        File testFile = new File("/home/admin1/Downloads/water-lily-3478924_960_720.jpg");
        Response response=RestAssured.given()
                .accept(ContentType.JSON)
                .header("token","eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJkYXRhIjp7Il9pZCI6IjVlMDk4MmRlNGQyMjY3MDAzMjUzMGYwZSJ9LCJpYXQiOjE1Nzc3MTQ3MDksImV4cCI6MTU3NzgwMTEwOX0.tBN7d3Qf6FpU_rb-iLGJALlOIYzpiExFV2iFoHwR574")
                .formParam("_id","5e0ae4e14d22670032531054")
                .multiPart("image",testFile)
                .formParam("title","Image of flower")
                .formParam("description","Beautiful Image")
                .formParam("redirect_link","www.google.com")
                .formParam("is_published",false)
                .formParam("archive",false)
                .formParam("youtube_flag",false)
                .formParam("youtube_url","https://www.youtube.com/watch?v=yDdBOspPp_c")
                .formParam("video_link","https://bit.ly/2QbakoC")
                .put("/redirects");
        int statusCode = response.getStatusCode();
        String resAsString = response.asString();
        JsonPath jsonPath = new JsonPath(resAsString);
        String message = jsonPath.getString("message");
        Assert.assertEquals(200,statusCode);
        Assert.assertEquals("Redirect updated Successfully",message);
    }

    @Test
    public void givenUser_WhenPerformOperationOnGetRedirect_ShouldReturnAllRedirectsRetrievedSuccessfully() {
        Response response=RestAssured.given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("token","eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJkYXRhIjp7Il9pZCI6IjVlMDk4MmRlNGQyMjY3MDAzMjUzMGYwZSJ9LCJpYXQiOjE1Nzc3NzE3NTAsImV4cCI6MTU3Nzg1ODE1MH0.Uahb7KIdTLXE9NSuxKpIP0Z2xuaB-4yaCBH9MAOQv4Y")
                .when()
                .get("/redirects");
        int statusCode = response.getStatusCode();
        String resAsString = response.asString();
        JsonPath jsonPath = new JsonPath(resAsString);
        String message = jsonPath.getString("message");
        Assert.assertEquals(200,statusCode);
        Assert.assertEquals("All Redirects retrieved Successfully",message);
    }

    @Test
    public void givenUser_WhenPerformOperationOnDeleteRedirect_ShouldReturnRedirectDeleteSuccessfully() {
        Response response=RestAssured.given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("token","eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJkYXRhIjp7Il9pZCI6IjVlMDk4MmRlNGQyMjY3MDAzMjUzMGYwZSJ9LCJpYXQiOjE1Nzc3NzE3NTAsImV4cCI6MTU3Nzg1ODE1MH0.Uahb7KIdTLXE9NSuxKpIP0Z2xuaB-4yaCBH9MAOQv4Y")
                .body("{\"_id\":\"5e0af5a14d2267003253108d\"}")
                .when()
                .post("/redirects/delete");
        int statusCode = response.getStatusCode();
        String resAsString = response.asString();
        JsonPath jsonPath = new JsonPath(resAsString);
        String message = jsonPath.getString("message");
        Assert.assertEquals(200,statusCode);
        Assert.assertEquals("Redirect deleted Successfully",message);
    }

    @Test
    public void givenUser_WhenPerformOperationOnGetRedirectsOfBl_ShouldReturnAllRedirectsRetrievedSuccessfully() {
        Response response=RestAssured.given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .when()
                .get("/bl-redirects");
        int statusCode = response.getStatusCode();
        String resAsString = response.asString();
        JsonPath jsonPath = new JsonPath(resAsString);
        String message = jsonPath.getString("message");
        Assert.assertEquals(200,statusCode);
        Assert.assertEquals("All Redirects retrieved Successfully",message);
    }

    @Test
    public void givenUser_WhenPerformOperationOnEditHashtag_ShouldReturnHashtagEditSuccessfully() {
        Response response=RestAssured.given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("token","eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJkYXRhIjp7Il9pZCI6IjVlMDk4MmRlNGQyMjY3MDAzMjUzMGYwZSJ9LCJpYXQiOjE1Nzc3NzE3NTAsImV4cCI6MTU3Nzg1ODE1MH0.Uahb7KIdTLXE9NSuxKpIP0Z2xuaB-4yaCBH9MAOQv4Y")
                .body("{\"redirect_id\":\"5e0afb5a82644d02f0fc0f70\",\"hashtag\":\"#fundoopush12345\"}")
                .when()
                .post("/hashtag/edit");
        int statusCode = response.getStatusCode();
        String resAsString = response.asString();
        JsonPath jsonPath = new JsonPath(resAsString);
        String message = jsonPath.getString("message");
        Assert.assertEquals(200,statusCode);
        Assert.assertEquals("Hashtag edit done Successfully",message);
    }

    @Test
    public void givenUser_WhenPerformOperationOnGetDataOnHashtag_ShouldReturnHashtagSentSuccessfully() {
        Response response=RestAssured.given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("token","eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJkYXRhIjp7Il9pZCI6IjVlMDk4MmRlNGQyMjY3MDAzMjUzMGYwZSJ9LCJpYXQiOjE1Nzc3NzE3NTAsImV4cCI6MTU3Nzg1ODE1MH0.Uahb7KIdTLXE9NSuxKpIP0Z2xuaB-4yaCBH9MAOQv4Y")
                .body("{\"redirect_id\":\"5e0afb5a82644d02f0fc0f70\"}")
                .when()
                .get("redirects/hashtag/#fundoopush12345");
        int statusCode = response.getStatusCode();
        String resAsString = response.asString();
        JsonPath jsonPath = new JsonPath(resAsString);
        String message = jsonPath.getString("message");
        Assert.assertEquals(200,statusCode);
        Assert.assertEquals("Hashtag sent Successfully",message);
    }

    @Test
    public void givenUser_WhenPerformOperationOnWebScrappingApi_ShouldReturnSuccessfullyscrappedData() {
        Response response=RestAssured.given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("token","eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJkYXRhIjp7Il9pZCI6IjVlMDk4MmRlNGQyMjY3MDAzMjUzMGYwZSJ9LCJpYXQiOjE1Nzc3NzE3NTAsImV4cCI6MTU3Nzg1ODE1MH0.Uahb7KIdTLXE9NSuxKpIP0Z2xuaB-4yaCBH9MAOQv4Y")
                .body("{\"url\":\"https://www.deccanchronicle.com/technology/in-other-news/270319/companies-that-are-changing-the-way-education-is-being-delivered-to-st.html\"}")
                .when()
                .post("/web-scraping");
        int statusCode = response.getStatusCode();
        String resAsString = response.asString();
        JsonPath jsonPath = new JsonPath(resAsString);
        String message = jsonPath.getString("message");
        Assert.assertEquals(200,statusCode);
        Assert.assertEquals("Successfully scrapped data",message);
    }

    @Test
    public void givenUser_WhenPerformOperationOnSearchHashtagApi_ShouldReturnSuccessfullySearchedData() {
        Response response=RestAssured.given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("token","eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJkYXRhIjp7Il9pZCI6IjVlMDk4MmRlNGQyMjY3MDAzMjUzMGYwZSJ9LCJpYXQiOjE1Nzc3NzE3NTAsImV4cCI6MTU3Nzg1ODE1MH0.Uahb7KIdTLXE9NSuxKpIP0Z2xuaB-4yaCBH9MAOQv4Y")
                .body("{\"hashtag\":\"#fundoopush12345\"}")
                .when()
                .post("/search/hashtag");
        int statusCode = response.getStatusCode();
        String resAsString = response.asString();
        JsonPath jsonPath = new JsonPath(resAsString);
        String message = jsonPath.getString("message");
        Assert.assertEquals(200,statusCode);
        Assert.assertEquals("Successfully searched data",message);
    }

    @Test
    public void givenUser_WhenPerformOperationOnPostJob_ShouldReturnJobsAddedSuccessfully() {
        Response response=RestAssured.given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("token","eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJkYXRhIjp7Il9pZCI6IjVjODY1YTc0OWZkNTkxNWFjNjQwZDZhNyJ9LCJpYXQiOjE1Nzc4MDAzNzIsImV4cCI6MTU3Nzg4Njc3Mn0.7pkUttoymtaiwDTfziCirlU2A8HLQMI9YtiX8wYbya4")
                .body("{\"redirect_id\":\"5e0b4281fe754c00321c8631\",\"years_of_experience\":\"1\",\"salary\":\"3.6\",\"location\":\"Mumbai\",\"company_profile\":\"Ideation\"}")
                .when()
                .post("/jobs");
        int status=response.getStatusCode();
        String resAsString=response.asString();
        System.out.println(resAsString);
        JsonPath jsonPath = new JsonPath(resAsString);
        String message = jsonPath.getString("message");
        Assert.assertEquals(200,status);
        Assert.assertEquals("Jobs added successfully",message);
    }

    @Test
    public void givenUser_WhenPerformOperationOnAddHashForJob_ShouldReturnJobsHashtagAddedSuccessfully() {
        Response response=RestAssured.given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("token","eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJkYXRhIjp7Il9pZCI6IjVjODY1YTc0OWZkNTkxNWFjNjQwZDZhNyJ9LCJpYXQiOjE1Nzc4MDAzNzIsImV4cCI6MTU3Nzg4Njc3Mn0.7pkUttoymtaiwDTfziCirlU2A8HLQMI9YtiX8wYbya4")
                .body("{\"job_id\":\"5e0b5317f24f8605182dea54\",\"hashtag\":\"#FundooPushApp\"}")
                .when()
                .post("/jobs/hashtag/add");
        int status=response.getStatusCode();
        String resAsString=response.asString();
        System.out.println(resAsString);
        JsonPath jsonPath = new JsonPath(resAsString);
        String message = jsonPath.getString("message");
        Assert.assertEquals(200,status);
        Assert.assertEquals("Jobs hashtag added successfully",message);
    }

    @Test
    public void givenUser_WhenPerformOperationOnRemoveHashTagFromJob_ShouldReturnJobsHashtagAddedSuccessfully() {
        Response response=RestAssured.given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("token","eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJkYXRhIjp7Il9pZCI6IjVjODY1YTc0OWZkNTkxNWFjNjQwZDZhNyJ9LCJpYXQiOjE1Nzc4MDAzNzIsImV4cCI6MTU3Nzg4Njc3Mn0.7pkUttoymtaiwDTfziCirlU2A8HLQMI9YtiX8wYbya4")
                .body("{\"job_id\":\"5e0b4f21f24f8605182dea42\",\"hashtag_id\":\"5d32fc4bd307d40054c3b652\"}")
                .when()
                .post("/jobs/hashtag/remove");
        int status=response.getStatusCode();
        String resAsString=response.asString();
        System.out.println(resAsString);
        JsonPath jsonPath = new JsonPath(resAsString);
        String message = jsonPath.getString("message");
        Assert.assertEquals(200,status);
        Assert.assertEquals("Jobs hashtag removed successfully",message);
    }

}

