package io.student.rangiffler.page;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class MainPage {

  private final SelenideElement header = $("h1");
  private final SelenideElement travelMap = $("figure svg");

  public MainPage checkHeader() {
    header.shouldHave(exactText("Rangiffler"));
    return this;
  }

  public void checkTravelMapIsPresent() {
    travelMap.shouldBe(visible);
  }
}
