package com.globant.tests;

import com.globant.pages.EditPage;
import com.globant.pages.HistoryPage;
import com.globant.pages.HomePage;
import com.globant.pages.ArticlePage;
import com.globant.utils.ScenarioContext;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import org.testng.Assert;
import org.testng.AssertJUnit;

public class stepDefinitions extends BaseTest {

    private ScenarioContext scenarioContext;

    @Given("I am an user at the Wikipedia WebPage")
    public void i_am_at_the_Wikipedia_WebPage() {
        navigateTo(scenarioContext.getWIKIPEDIA_URL());
        HomePage homePage = loadFirstPage();
        scenarioContext.setHomePage(homePage);
    }

    @When("I search for the name of the character with path {int}")
    public void search_for_the_name_of_the_character_with_path(int characterPath) {
        String characterName = getCharacterName(characterPath);
        scenarioContext.setCharacterName(characterName);
        ArticlePage articlePage = scenarioContext.getHomePage().search(characterName);
        scenarioContext.setArticlePage(articlePage);
    }

    @Then("I should be able to see the article page correctly displayed for the requested character")
    public void see_the_article_page_correctly_displayed_for_the_requested_character() {
        Assert.assertEquals(scenarioContext.getArticlePage().getTitle(), scenarioContext.getCharacterName());
    }

    @When("I search for a random SW film")
    public void search_for_a_random_SW_film() {
        String filmName = getARandomFilm();
        scenarioContext.setFilmName(filmName);
        ArticlePage articlePage = scenarioContext.getHomePage().search(filmName);
        scenarioContext.setArticlePage(articlePage);
    }

    @Then("I will be able to see the article correctly displayed for the requested film")
    public void see_the_article_correctly_correctly_displayed_for_the_requested_film() {
        Assert.assertEquals(scenarioContext.getArticlePage().getRedirectLabel(), scenarioContext.getFilmName());
    }

    @When("I try to edit the article")
    public void try_to_edit_the_article() {
        ArticlePage articlePage = scenarioContext.getArticlePage();
        scenarioContext.setArticleTitle(articlePage.getTitle());
        EditPage editPage = articlePage.goToEditPage();
        scenarioContext.setEditPage(editPage);
    }

    @Then("I will be redirected to the editing page")
    public void be_redirected_to_the_edit_page() {
        Assert.assertTrue(scenarioContext.getEditPage().isTitleCorrect(scenarioContext.getArticleTitle()));
    }

    @When("I try to see the history of the article")
    public void try_to_see_the_history_of_the_article() {
        ArticlePage articlePage = scenarioContext.getArticlePage();
        scenarioContext.setArticleTitle(articlePage.getTitle());
        HistoryPage historyPage = articlePage.goToHistoryPage();
        scenarioContext.setHistoryPage(historyPage);
    }

    @Then("I will be redirected to the history page")
    public void be_redirected_to_the_history_page() {
        Assert.assertTrue(scenarioContext.getHistoryPage().isTitleCorrect(scenarioContext.getArticleTitle()));
    }

    @Before
    public void setUp() {
        scenarioContext = new ScenarioContext();
        RestAssured.baseURI = scenarioContext.getSWAPI_URL();
        setUpDriver(scenarioContext.getBROWSER());
        maximizeWindow();
    }

    @After
    public void tearDown() {
        shutDown();
    }

}
