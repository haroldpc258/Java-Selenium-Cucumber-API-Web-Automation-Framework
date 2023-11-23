package com.globant.utils;

import com.globant.pages.EditPage;
import com.globant.pages.HistoryPage;
import com.globant.pages.HomePage;
import com.globant.pages.ArticlePage;
import lombok.Data;

@Data
public class ScenarioContext {
    private final String SWAPI_URL = "https://swapi.dev/api/";
    private final String BROWSER = "Chrome";
    private final String WIKIPEDIA_URL = "https://es.wikipedia.org/wiki/Wikipedia:Portada";

    private HomePage homePage;
    private ArticlePage articlePage;
    private EditPage editPage;
    private HistoryPage historyPage;
    private String characterName;
    private String filmName;
    private String ArticleTitle;
}
