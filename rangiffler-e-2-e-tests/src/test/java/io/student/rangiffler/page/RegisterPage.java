package io.student.rangiffler.page;

import com.codeborne.selenide.SelenideElement;
import io.student.rangiffler.config.Config;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;

public class RegisterPage {

  private final SelenideElement usernameInput = $("#username");
  private final SelenideElement passwordInput = $("#password");
  private final SelenideElement confirmPasswordInput = $("#passwordSubmit");
  private final SelenideElement submitButton = $("button[type=submit]");
  private final SelenideElement usernameErrorMessage = $("#username ~ .form__error");
  private final SelenideElement passwordErrorMessage = $("#password ~ .form__error");
  private final SelenideElement successMessage = $(".form__paragraph_success");
  private final SelenideElement signInButton = $(".form_sign-in");

  public RegisterPage register(String username, String password) {
    return register(username, password, password);
  }

  public RegisterPage register(String username, String password, String confirmPassword) {
    usernameInput.setValue(username);
    passwordInput.setValue(password);
    confirmPasswordInput.setValue(confirmPassword);
    submitButton.click();
    return this;
  }

  public RegisterPage checkRegistrationFormDisappeared() {
    usernameInput.should(disappear);
    passwordInput.should(disappear);
    confirmPasswordInput.should(disappear);
    submitButton.should(disappear);
    return this;
  }

  public RegisterPage checkSuccessMessage() {
    successMessage.shouldHave(exactOwnText("Congratulations! You've registered!"));
    return this;
  }

  public void checkSignInButton() {
    signInButton.shouldHave(exactOwnText("Sign in!"), href(Config.getInstance().frontUrl()));
  }

  public void checkRegistrationFailed(String username) {
    usernameInput.shouldBe(visible).shouldHave(exactValue(username));
    passwordInput.shouldBe(visible, empty);
    confirmPasswordInput.shouldBe(visible, empty);
    submitButton.shouldBe(visible);
  }

  public RegisterPage checkUsernameLengthError() {
    checkUsernameError("Allowed username length should be from 3 to 50 characters");
    return this;
  }

  public RegisterPage checkUsernameHasWhitespacesError() {
    checkUsernameError("Username must not contain whitespaces");
    return this;
  }

  public RegisterPage checkPasswordsDoNotMatchError() {
    checkPasswordError("Passwords should be equal");
    return this;
  }

  public RegisterPage checkPasswordLengthError() {
    checkPasswordError("Allowed password length should be from 3 to 12 characters");
    return this;
  }

  public RegisterPage checkPasswordHasWhitespacesError() {
    checkPasswordError("Password must not contain whitespaces");
    return this;
  }

  public void checkUsernameErrorIsHidden() {
    usernameErrorMessage.shouldBe(hidden);
  }

  public void checkPasswordErrorIsHidden() {
    passwordErrorMessage.shouldBe(hidden);
  }

  private void checkUsernameError(String error) {
    usernameErrorMessage.shouldHave(exactOwnText(error));
  }

  private void checkPasswordError(String error) {
    passwordErrorMessage.shouldHave(exactOwnText(error));
  }
}
