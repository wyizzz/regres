package com.api.regres.stepDefs;

import com.api.regres.endpoints.Endpoints;
import com.api.regres.utils.ReusableSteps;

import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.json.JSONException;
import org.json.JSONObject;
import com.api.regres.utils.Context;
import com.api.regres.utils.ScenarioContext;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;
import cucumber.api.Scenario;

public class Stepdefs extends Context {
    public Stepdefs(ScenarioContext context) {
        super(context);
    }

    private Scenario scenario;
    @Before
    public void before(Scenario scenario) {
        this.scenario = scenario;
    }

    @Given("^I setup the \"([^\"]*)\" for POST User operation$")
    public void iSetupTheForPOSTUserOperation(String fileName) throws Throwable {
        String jsonBody = ReusableSteps.getDataFromFile(fileName+".json");
        JSONObject jsonRequestObject = new JSONObject(jsonBody);
        context.vars.put("reqBodyPOST", jsonRequestObject);
        scenario.write(context.vars.get("reqBodyPOST").toString());
    }

    @When("^I send POST HTTP Request$")
    public void iSendPOSTHTTPRequest() {
        context.response = Endpoints.postUsers(context.vars.get("reqBodyPOST").toString());
        context.response.prettyPrint();
        scenario.write(context.response.asString());
    }

    @Then("^I should get a response with \"([^\"]*)\" status$")
    public void iShouldGetAResponseWithStatus(String statusCode) {
        context.response.then().statusCode(Integer.parseInt(statusCode));
    }

    @And("^The POST response should be valid as per the request$")
    public void thePOSTResponseShouldBeValidAsPerTheRequest() throws JSONException {
        JSONObject request = (JSONObject)context.vars.get("reqBodyPOST");
        assertThat(request.get("name").toString(), containsString(context.response.then().extract().path("name")));
        assertThat(request.get("job").toString(), containsString(context.response.then().extract().path("job")));
        assertThat(context.response.then().extract().path("createdAt").toString(), containsString(ReusableSteps.getCurrentDateString("YYYY-MM-dd'T'HH")));
    }

    @Given("^I setup the \"([^\"]*)\" for PUT User operation$")
    public void iSetupTheForPUTUserOperation(String fileName) throws Throwable {
        String jsonBody = ReusableSteps.getDataFromFile(fileName+".json");
        JSONObject jsonRequestObject = new JSONObject(jsonBody);
        context.vars.put("reqBodyPUT", jsonRequestObject);
        scenario.write(context.vars.get("reqBodyPUT").toString());
    }

    @When("^I send PUT HTTP Request$")
    public void iSendPUTHTTPRequest() {
        context.response = Endpoints.putUsers(context.vars.get("reqBodyPUT").toString());
        context.response.prettyPrint();
        scenario.write(context.response.asString());
    }

    @And("^The PUT response should have a valid updatedDateTime$")
    public void thePUTResponseShouldHaveAValidUpdatedDateTime() throws JSONException {
        JSONObject request = (JSONObject)context.vars.get("reqBodyPUT");
        assertThat(request.get("name").toString(), containsString(context.response.then().extract().path("name")));
        assertThat(request.get("job").toString(), containsString(context.response.then().extract().path("job")));
        assertThat(context.response.then().extract().path("updatedAt").toString(), containsString(ReusableSteps.getCurrentDateString("YYYY-MM-dd'T'HH")));
    }

    @Given("^I setup the parameters for GET User operation$")
    public void iSetupTheParametersForGETUserOperation() {
        RequestSpecification requestSpec = new RequestSpecBuilder().addParam("page", "2").build();
        context.vars.put("reqSpecGET", requestSpec);
    }

    @When("^I send GET HTTP Request$")
    public void iSendGETHTTPRequest() {
        context.response = Endpoints.getUsers((RequestSpecification)context.vars.get("reqSpecGET"));//Users(context.vars.get("reqBodyPUT").toString());
        context.response.prettyPrint();
        scenario.write(context.response.asString());
    }

    @And("^The GET response should be valid$")
    public void theGETResponseShouldBeValid() {
        List<String> dataSet = context.response.path("data");
        assertThat(Integer.parseInt(context.response.path("per_page").toString()), is(dataSet.size()));
    }

    @Given("^I setup the \"([^\"]*)\" for DELETE User operation$")
    public void iSetupTheForDELETEUserOperation(String fileName) throws Throwable {
        String jsonBody = ReusableSteps.getDataFromFile(fileName+".json");
        JSONObject jsonRequestObject = new JSONObject(jsonBody);
        context.vars.put("reqBodyDELETE", jsonRequestObject);
        scenario.write(context.vars.get("reqBodyDELETE").toString());
    }

    @When("^I send DELETE HTTP Request$")
    public void iSendDELETEHTTPRequest() {
        context.response = Endpoints.deleteUsers(context.vars.get("reqBodyDELETE").toString());
        context.response.prettyPrint();
        scenario.write(context.response.asString());
    }
}
