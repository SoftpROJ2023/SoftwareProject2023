package frontendproductioncode;
import java.util.logging.ConsoleHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
public class Main {
    public static void main(String[] args) {
        System.setProperty("java.util.logging.SimpleFormatter.format", "%4$s: %5$s %n");
        configureLogging();

        FrontendComponents myapp=new FrontendComponents();
        myapp.frontendView();
    }
    private static void configureLogging() {
        Logger rootLogger = Logger.getLogger("");
        Handler[] handlers = rootLogger.getHandlers();

        // Remove the default handlers to prevent the default formatting
        for (Handler handler : handlers) {
            rootLogger.removeHandler(handler);
        }

        // Create a custom console handler
        ConsoleHandler consoleHandler = new ConsoleHandler();
        consoleHandler.setLevel(Level.INFO);
        consoleHandler.setFormatter(new SimpleFormatter() {
            @Override
            public String format(java.util.logging.LogRecord record) {
                return record.getMessage() + System.lineSeparator();
            }
        });

        // Add the custom handler to the root logger
        rootLogger.addHandler(consoleHandler);
    }
}
