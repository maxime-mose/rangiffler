package io.student.rangiffler.tests;

import com.codeborne.selenide.Configuration;
import io.student.rangiffler.config.Config;
import io.student.rangiffler.jupiter.User;
import io.student.rangiffler.model.UserJson;
import io.student.rangiffler.page.AuthPage;
import io.student.rangiffler.page.LoginPage;
import net.datafaker.Faker;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.*;

public class LoginTest {

  private static final Faker FAKER = new Faker();
  private final String username = FAKER.credentials().username();
  private final String password = FAKER.credentials().password(3, 12);

  @BeforeEach
  void setUp() {
    Configuration.browserSize = "1920x1080";
    // TODO Переделать на открытие страницы входа по прямой ссылке http://localhost:9000/login вместо AuthPage
    open(Config.getInstance().frontUrl(), AuthPage.class).clickLoginButton();
  }

  @Test
  @User
  void mainPageShouldBeDisplayedAfterSuccessfulLogin(UserJson user) {
    page(LoginPage.class)
        .login(user.username(), user.password())
        .checkHeader()
        .checkTravelMapIsPresent();
  }

  @Test
  @User
  void userShouldStayOnLoginPageAfterLoginWithInvalidPassword(UserJson user) {
    page(LoginPage.class)
        .loginWithInvalidCredentials(user.username(), password)
        .checkLoginFailed();
  }

  @Test
  @User
  void userShouldStayOnLoginPageAfterLoginWithInvalidUsername(UserJson user) {
    page(LoginPage.class)
        .loginWithInvalidCredentials(username, user.password())
        .checkLoginFailed();
  }

  @Test
  void userShouldStayOnLoginPageAfterLoginWithInvalidCredentials() {
    page(LoginPage.class)
        .loginWithInvalidCredentials(username, password)
        .checkLoginFailed();
  }

  @AfterEach
  void tearDown() {
    clearBrowserCookies();
    clearBrowserLocalStorage();
  }
}
