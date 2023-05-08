package org.ooka.component;

import org.ooka.lzu.Start;
import org.ooka.lzu.Stop;
import org.ooka.lzu.Inject;
import org.ooka.lzu.Logger;
// import org.ooka.lzu.LoggerImpl;

/**
 * Hello world!
 *
 */

public class App {

    @Inject
    private Logger myLog;

    @Start
    public int test() {
        System.out.println("Executing test method.");

        if (myLog != null) {
            myLog.sendLog("Meldung aus App: Prozess gestartet");
        } else {
            System.out.println("Logging Error");
        }

        System.out.println("test method finished");
        return 0;
    }

    @Stop
    public int stop() {
        System.out.println("Stopping method executed.");
        return 0;
    }

    public static void main(String[] args) {
        System.out.println("Hello World! I am the Component #1");

        Class<App> appClass = App.class;

        // Check if the @Start annotation is present on the App class
        boolean hasStartAnnotation = appClass.isAnnotationPresent(Start.class);

        if (hasStartAnnotation) {
            System.out.println("@Start annotation found");
        } else {
            System.out.println("No @Start annotation");
        }
    }
}
