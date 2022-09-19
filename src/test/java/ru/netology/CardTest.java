package ru.netology;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.$x;

public class CardTest {

    @Test
    void test() {

        Configuration.holdBrowserOpen = true;
        open("http://localhost:9999");
        $("[placeholder=\"Город\"]").setValue("Самара");
        $("[placeholder=\"Дата встречи\"]").doubleClick().sendKeys(Keys.DELETE);
        $("[placeholder=\"Дата встречи\"]").setValue("22.09.2022");
        $("[data-test-id=\"name\"] input").setValue("Иванов Иван");
        $("[data-test-id=\"phone\"] input").setValue("+12345678900");
        $x("//span[contains(text(), 'Я соглашаюсь с условиями обработки')]").click();
        $(".button__content").click();
        $x("//div[text()= 'Успешно!']").should(Condition.visible, Duration.ofSeconds(15));
        $x("//div[contains(text(), 'Встреча успешно забронирована')]").should(Condition.visible, Duration.ofSeconds(15));

    }
    @Test
    void incorrectPhoneNumber() {
        Configuration.holdBrowserOpen = true;
        open("http://localhost:9999");
        $("[placeholder=\"Город\"]").setValue("Владивосток");
        $("[placeholder=\"Дата встречи\"]").doubleClick().sendKeys(Keys.DELETE);
        $("[placeholder=\"Дата встречи\"]").setValue("23.09.2022");
        $("[data-test-id=\"name\"] input").setValue("Иванов Иван");
        $("[data-test-id=\"phone\"] input").setValue("09898гргр6к6в");
        $x("//span[contains(text(), 'Я соглашаюсь с условиями обработки')]").click();
        $(".button__content").click();
        $x("//span[contains(text(), 'Телефон указан неверно')]").hover();

    }

}
