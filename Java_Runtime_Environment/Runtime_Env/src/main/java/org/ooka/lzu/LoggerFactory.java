package org.ooka.lzu;


// Should be used in other Class methods than @Start annotated one.
public class LoggerFactory {
    public static Logger createLogger() {
        return new Logger();
    }
}
