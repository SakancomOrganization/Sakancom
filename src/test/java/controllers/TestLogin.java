package controllers;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import models.Sakancom;

import java.text.ParseException;

import static org.junit.Assert.*;

public class TestLogin {
    private String username;
    private String password;
    @Given("Database is already filled")
    public void databaseIsAlreadyFilled() throws ParseException {
        Sakancom.initSakancomWithData();
    }
    @When("username is {string}")
    public void usernameIs(String username) {
        this.username = username;
    }
    @When("password is {string}")
    public void passwordIs(String password) {
        this.password = password;
    }
    @Then("the user will log in successfully")
    public void theUserWillLogInSuccessfully() {
        assertTrue(Login.login(username, password));
        assertNotNull(Sakancom.getCurrentUser());
    }
    @Then("the user will not log in successfully")
    public void theUserWillNotLogInSuccessfully() {
        assertFalse(Login.login(username, password));
    }
}
