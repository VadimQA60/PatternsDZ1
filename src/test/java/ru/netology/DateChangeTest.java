package ru.netology;

import Data.DataGenerator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class DateChangeTest {
    @BeforeEach
    void setup() {
        open("http://localhost:9999");
    }

    private String getFutureDate(int addDays) {
        LocalDate currentDate = LocalDate.now();
        LocalDate futureDate = currentDate.plusDays(addDays);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        String formattedDate = futureDate.format(formatter);
        return formattedDate;


    }

    @Test

    public void сardDeliveryTest() {
        open("http://localhost:9999");
        DataGenerator.generateCity();
        DataGenerator.generateName();
        DataGenerator.generatePhoneNumber();
        $("[data-test-id=city] input").setValue(DataGenerator.generateCity());
        $(".calendar-input__custom-control input").doubleClick().sendKeys(getFutureDate(4));
        $("[data-test-id=name] input").setValue("Вадим Пенкин");
        $("[data-test-id=phone] input").setValue("+79168268994");
        $(".checkbox__box").click();
        $(".button").click();
        $("[data-test-id=success-notification] .notification__title")
                .shouldBe(visible, Duration.ofSeconds(15)).shouldHave(text("Успешно"));
        $("[data-test-id=success-notification] .notification__content")
                .shouldBe(visible, Duration.ofSeconds(15)).shouldHave(text("Встреча успешно запланирована на " + getFutureDate(4)));
        $(".calendar-input__custom-control input").doubleClick().sendKeys(getFutureDate(4));
        $(".button").click();
        $("[data-test-id=replan-notification] .notification__title")
                .shouldHave(exactText("Необходимо подтверждение"));
        $("[data-test-id=replan-notification] button").click();
        $("[data-test-id=success-notification] .notification__title")
                .shouldHave(exactText("Успешно!"), Duration.ofSeconds(40));
        $("[data-test-id=success-notification] .notification__content")
                .shouldHave(exactText("Встреча успешно запланирована на " + getFutureDate(4)), Duration.ofSeconds(10));

    }


}



