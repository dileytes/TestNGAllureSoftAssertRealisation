import assertion.SoftAssertWarp;
import io.qameta.allure.Step;

import javax.xml.ws.WebServiceException;

public class Steps extends SoftAssertWarp {

    @Step("This step always pass and has name {0}")
    public void stepOne(String stepName) {
        assertSoft(assertThat(true).as("Always true").isTrue());
    }

    @Step("This step depend on vale and has name {0} with vale {1}")
    public void stepTwo(String stepName, Boolean checkWith) {
        assertSoft(assertThat(checkWith).as("Value not equals", checkWith.toString()).isTrue());
        assertSoft(assertThat("ddd").as("string not equals").isEqualTo("ddd"));
        assertSoft(assertThat(555).as("integer not equal").isEqualTo(555));
    }

    @Step("This step throw exception and has name {0}")
    public void stepThree(String stepName) {
        throw (new WebServiceException(stepName + "exception info"));
    }

    @Step("This step also always pass and has name {0}")
    public void stepFour(String stepName) {
        assertSoft(assertThat("str").as("string equals ignore case").isEqualToIgnoringCase("Str"));
    }

    @Step("This step fail")
    public void failTest() {
        assertSoft(assertThat(true).isFalse());
    }
}
