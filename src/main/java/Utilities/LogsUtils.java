package Utilities;

import io.qameta.allure.Allure;
import org.apache.logging.log4j.LogManager;

public class LogsUtils {
    public static String LOGS_PATH = "test-outputs/Logs";

    public static void trace(String message) {
        LogManager.getLogger(Thread.currentThread().getStackTrace()[2].toString())
                .trace(message);
    }

    public static void debug(String message) {
        LogManager.getLogger(Thread.currentThread().getStackTrace()[2].toString())
                .debug(message);
        Allure.addAttachment("logs", message);
    }

    public static void info(String message) {
        LogManager.getLogger(Thread.currentThread().getStackTrace()[2].toString())
                .info(message);
    }

    public static void warn(String message) {
        LogManager.getLogger(Thread.currentThread().getStackTrace()[2].toString())
                .warn(message);
    }

    public static void error(String message) {
        LogManager.getLogger(Thread.currentThread().getStackTrace()[2].toString())
                .error(message);
    }

    public static void fatal(String message) {
        LogManager.getLogger(Thread.currentThread().getStackTrace()[2].toString())
                .fatal(message);
    }
}
