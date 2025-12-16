package io.student.rangiffler.page;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class AuthPage {

  private final SelenideElement loginButton = $("button");

  public void clickLoginButton() {
    loginButton.click();
  }
}
