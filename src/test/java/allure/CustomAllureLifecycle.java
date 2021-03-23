package allure;

import io.qameta.allure.AllureLifecycle;
import io.qameta.allure.AllureResultsWriter;
import io.qameta.allure.model.FixtureResult;
import io.qameta.allure.model.Status;
import io.qameta.allure.model.StepResult;
import io.qameta.allure.model.TestResult;
import io.qameta.allure.model.TestResultContainer;

import java.io.InputStream;
import java.util.Optional;
import java.util.function.Consumer;

public class CustomAllureLifecycle extends AllureLifecycle {

    Boolean failedStep = false;

    AllureLifecycle allureLifecycle;

    private static CustomAllureLifecycle instance;

    public static synchronized CustomAllureLifecycle getInstance(AllureLifecycle allureLifecycle) {
        if (instance == null) {
            instance = new CustomAllureLifecycle();
            instance.setLifecycle(allureLifecycle);
        }
        return instance;
    }

    public Boolean getFailedStep() {
        return failedStep;
    }

    private void setLifecycle(AllureLifecycle allureLifecycle) {
        this.allureLifecycle = allureLifecycle;
    }

    @Override
    public void stopStep() {
        if (failedStep) {
            updateStep(stepResult -> stepResult.setStatus(Status.FAILED));
        }
        failedStep = false;
        allureLifecycle.stopStep();
    }

    @Override
    public void stopStep(String uuid) {
        allureLifecycle.stopStep(uuid);
    }

    public CustomAllureLifecycle() {
        super();
    }

    public CustomAllureLifecycle(AllureResultsWriter writer) {
        super(writer);
    }

    @Override
    public void startTestContainer(String containerUuid, TestResultContainer container) {
        allureLifecycle.startTestContainer(containerUuid, container);
    }

    @Override
    public void startTestContainer(TestResultContainer container) {
        allureLifecycle.startTestContainer(container);
    }

    @Override
    public void updateTestContainer(String uuid, Consumer<TestResultContainer> update) {
        allureLifecycle.updateTestContainer(uuid, update);
    }

    @Override
    public void stopTestContainer(String uuid) {
        allureLifecycle.stopTestContainer(uuid);
    }

    @Override
    public void writeTestContainer(String uuid) {
        allureLifecycle.writeTestContainer(uuid);
    }

    @Override
    public void startPrepareFixture(String containerUuid, String uuid, FixtureResult result) {
        allureLifecycle.startPrepareFixture(containerUuid, uuid, result);
    }

    @Override
    public void startTearDownFixture(String containerUuid, String uuid, FixtureResult result) {
        allureLifecycle.startTearDownFixture(containerUuid, uuid, result);
    }

    @Override
    public void updateFixture(Consumer<FixtureResult> update) {
        allureLifecycle.updateFixture(update);
    }

    @Override
    public void updateFixture(String uuid, Consumer<FixtureResult> update) {
        allureLifecycle.updateFixture(uuid, update);
    }

    @Override
    public void stopFixture(String uuid) {
        allureLifecycle.stopFixture(uuid);
    }

    @Override
    public Optional<String> getCurrentTestCase() {
        return allureLifecycle.getCurrentTestCase();
    }

    @Override
    public Optional<String> getCurrentTestCaseOrStep() {
        return allureLifecycle.getCurrentTestCaseOrStep();
    }

    @Override
    public boolean setCurrentTestCase(String uuid) {
        return allureLifecycle.setCurrentTestCase(uuid);
    }

    @Override
    public void scheduleTestCase(String containerUuid, TestResult result) {
        allureLifecycle.scheduleTestCase(containerUuid, result);
    }

    @Override
    public void scheduleTestCase(TestResult result) {
        allureLifecycle.scheduleTestCase(result);
    }

    @Override
    public void startTestCase(String uuid) {
        allureLifecycle.startTestCase(uuid);
    }

    @Override
    public void updateTestCase(Consumer<TestResult> update) {
        allureLifecycle.updateTestCase(update);
    }

    @Override
    public void updateTestCase(String uuid, Consumer<TestResult> update) {
        allureLifecycle.updateTestCase(uuid, update);
    }

    @Override
    public void stopTestCase(String uuid) {
        allureLifecycle.stopTestCase(uuid);
    }

    @Override
    public void writeTestCase(String uuid) {
        allureLifecycle.writeTestCase(uuid);
    }

    @Override
    public void startStep(String uuid, StepResult result) {
        allureLifecycle.startStep(uuid, result);
    }

    @Override
    public void startStep(String parentUuid, String uuid, StepResult result) {
        allureLifecycle.startStep(parentUuid, uuid, result);
    }

    @Override
    public void updateStep(Consumer<StepResult> update) {
        allureLifecycle.updateStep(update);
    }

    @Override
    public void updateStep(String uuid, Consumer<StepResult> update) {
        allureLifecycle.updateStep(uuid, update);
    }

    @Override
    public void addAttachment(String name, String type, String fileExtension, byte[] body) {
        allureLifecycle.addAttachment(name, type, fileExtension, body);
    }

    @Override
    public void addAttachment(String name, String type, String fileExtension, InputStream stream) {
        allureLifecycle.addAttachment(name, type, fileExtension, stream);
    }

    @Override
    public String prepareAttachment(String name, String type, String fileExtension) {
        return allureLifecycle.prepareAttachment(name, type, fileExtension);
    }

    @Override
    public void writeAttachment(String attachmentSource, InputStream stream) {
        allureLifecycle.writeAttachment(attachmentSource, stream);
    }

    public void setFailedStep(Boolean failedStep) {
        this.failedStep = failedStep;
    }
}
