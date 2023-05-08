package org.ooka.lzu;

import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class Component {
    enum Level {
        LOW,
        MEDIUM,
        HIGH
    }

    private static int nextId = 1;
    private int id;
    private Class startClass;
    private URLClassLoader classLoader;
    private String file_path;

    public Component(Class startClass, URLClassLoader classLoader, String file_path) {
        this.id = nextId++;
        this.startClass = startClass;
        this.file_path = file_path;
        this.classLoader = classLoader;
    }

    public String getFilePath() {
        return file_path;
    }

    public Class getStartClass() {
        return startClass;
    }

    public static void main(String[] args)
            throws Exception {
        // File jarFile = new
        // File("/home/thomas/Documents/Studies/Modules/ObjektOrientierteKompArch/Codebase/Java_Runtime_Environment/MyComponent/target/MyComponent-1.0-SNAPSHOT.jar");
        // URLClassLoader classLoader = new URLClassLoader(new URL[] {
        // jarFile.toURI().toURL() });

        // // ClassLoader classLoader = ClassLoader.getSystemClassLoader();
        // Class<?> componentClass = classLoader.loadClass("org.ooka.lzu.App");

        // Object componentObject =
        // componentClass.getDeclaredConstructor().newInstance();
        // App component = (App)componentClass.cast(componentObject);
        // component.test();

        String pathToJar = "/home/thomas/Documents/Studies/Modules/ObjektOrientierteKompArch/Codebase/Java_Runtime_Environment/MyComponent/target/MyComponent-1.0-SNAPSHOT.jar";

        JarFile jarFile = new JarFile(pathToJar);
        Enumeration<JarEntry> e = jarFile.entries();

        URL[] urls = { new URL("jar:file:" + pathToJar + "!/") };
        URLClassLoader cl = URLClassLoader.newInstance(urls);

        ArrayList<Class> classes = new ArrayList<Class>();

        while (e.hasMoreElements()) {
            JarEntry je = e.nextElement();
            if (je.isDirectory() || !je.getName().endsWith(".class")) {
                continue;
            }
            // -6 because of .class
            String className = je.getName().substring(0, je.getName().length() - 6);
            className = className.replace('/', '.');
            Class c = cl.loadClass(className);

            System.out.println(c.getName());
            classes.add(c);
            Method[] meth = c.getDeclaredMethods();
            for (Method m : meth) {
                System.out.println(m.getName());
                if (m.getName() == "test") {
                    Object obj = c.getConstructor().newInstance();
                    m.invoke(obj);
                }
            }

        }
    }

}
