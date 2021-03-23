import allure.CustomAllureLifecycle;
import io.qameta.allure.Allure;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

@Epic("EpicEpic")
@Feature("FeatureFeature")
public class oneTEST {

    @BeforeSuite(alwaysRun = true)
    public void setListener() {
        Allure.setLifecycle(CustomAllureLifecycle.getInstance(Allure.getLifecycle()));
    }

//    @BeforeMethod
//    public void startTest() {
//        steps = new Steps();
//    }

    @Test(description = "first test description", dataProvider = "soft assert")
    public void test(Steps steps) {
        long id = Thread.currentThread().getId();
        System.out.println("first test thread " + id);
        steps.stepTwo("'StepTwo with true'", true);
        steps.stepOne("'StepOne'");
        steps.assertAll();
    }

    @Test(description = "second test description", dataProvider = "soft assert")
    public void test2(Steps steps) {
        long id = Thread.currentThread().getId();
        System.out.println("second test thread " + id);
        steps.stepTwo("'StepTwo with false'", false);
        steps.stepOne("'StepOne'");
        steps.stepFour("'StepFour'");
        steps.failTest();
        steps.stepFour("'StepFourSecondUse'");
        steps.assertAll();
    }

    @Test(description = "third test description", dataProvider = "soft assert")
    public void test3(Steps steps) {
        long id = Thread.currentThread().getId();
        System.out.println("third test thread " + id);
        steps.failTest();
        steps.stepThree("'StepThree'");
        steps.assertAll();
    }

    @DataProvider(name = "soft assert")
    public Object[][] newSoftAssert() {
        return new Object[][]{{new Steps()}};
    }
}
