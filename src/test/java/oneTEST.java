import allure.CustomAllureLifecycle;
import io.qameta.allure.Allure;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

@Epic("EpicEpic")
@Feature("FeatureFeature")
public class oneTEST {

    Steps steps;

    @BeforeSuite(alwaysRun = true)
    public void setListener() {
        Allure.setLifecycle(CustomAllureLifecycle.getInstance(Allure.getLifecycle()));
    }

    @BeforeMethod
    public void startTest() {
        steps = new Steps();
    }

    @Test(description = "first test description")
    public void test() {
        steps.stepTwo("'StepTwo with true'", true);
        steps.stepOne("'StepOne'");
        steps.assertAll();
    }

    @Test(description = "second test description")
    public void test2() {
        steps.stepTwo("'StepTwo with false'", false);
        steps.stepOne("'StepOne'");
        steps.stepFour("'StepFour'");
        steps.failTest();
        steps.stepFour("'StepFourSecondUse'");
        steps.assertAll();
    }

    @Test(description = "third test description")
    public void test3() {
        steps.failTest();
        steps.stepThree("'StepThree'");
        steps.assertAll();
    }
}
