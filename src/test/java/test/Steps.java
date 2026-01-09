package test;

import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import org.testng.Assert;

public class Steps {

    public void step(String text) {
        Allure.step("Step: " + text, () -> {
            Assert.assertTrue(text.contains("Google"), "Title does not contain Google");
        });
    }

    @Attachment(type = "image/png")
    public void step2(String text) {
        Allure.step("Step: " + text, () -> {
            Assert.assertTrue(text.contains("Google"), "Title does not contain Google");
        });
    }
}
