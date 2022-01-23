package utility;

import io.qameta.allure.Step;

public class AllureLogger {

    /**
     * Uses the @Step annotation to log the given log message to Allure.
     *
     * @param message the message to log to the allure report
     */
    @Step("{0}")
    public static void logToAllure(String message) {
    }
    
}
