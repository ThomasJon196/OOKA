package org.ooka.component;

/**
 * Hello world!
 *
 */
@Start
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World! I am the Component #1" );

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

