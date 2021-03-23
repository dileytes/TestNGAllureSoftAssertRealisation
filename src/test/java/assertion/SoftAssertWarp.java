package assertion;

import allure.CustomAllureLifecycle;
import io.qameta.allure.Allure;

import org.assertj.core.api.AbstractAssert;
import org.assertj.core.api.SoftAssertions;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class SoftAssertWarp extends SoftAssertions {

    List<Throwable> errors = new ArrayList<>();

    public void assertSoft(AbstractAssert assert1) {
        List<Throwable> curentList = this.errorsCollected();
        if (curentList.size() > 0 && errors.size() != this.errorsCollected().size()) {
            Throwable error = curentList.get(curentList.size() - 1);
            if (Allure.getLifecycle() instanceof CustomAllureLifecycle) {
                if (!((CustomAllureLifecycle) Allure.getLifecycle()).getFailedStep()) {
                    Allure.getLifecycle().updateStep(stepResult -> stepResult.setName(stepResult.getName() + " \n *Failed*" /*on: " + error.getMessage() + "]"*/));
                }
                ((CustomAllureLifecycle) Allure.getLifecycle()).setFailedStep(true);
            }
            byte[] errorBody = error.getMessage().getBytes(Charset.forName("UTF-8"));
            Allure.getLifecycle().addAttachment("fail"+(errors.size()+1), "application/json","txt",errorBody);
            errors.add(error);
        }
    }
}
