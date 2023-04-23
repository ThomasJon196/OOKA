package org.ooka.lzu;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * Hello world!
 *
 */

public class RuntimeEnvironment {

    private static RuntimeEnvironment instance;
    private ArrayList<Component> components = new ArrayList<Component>();

    // Singleton Pattern
    public static RuntimeEnvironment getInstance() {
        if (instance == null) {
            instance = new RuntimeEnvironment();
        }
        return instance;
    }

    public static void startRuntime() {
        System.out.println("Starting runtime environment");
        if (instance == null) {
            instance = new RuntimeEnvironment();
        }
    }

    public static void stopRuntime() {
        instance = null;
        System.out.println("Stopping runtime environment");
    }

    public void deployComponent(String path) throws Exception {
        // TODO: Set component via input argument. Jar currently hard coded.
        String pathToJar;
        if (path != null) {
            pathToJar = path;
        } else {
            pathToJar = "/home/thomas/Documents/Studies/Modules/ObjektOrientierteKompArch/Codebase/Java_Runtime_Environment/MyComponent/target/MyComponent-1.0-SNAPSHOT.jar";
        }
        System.out.println(pathToJar);
        JarFile jarFile = new JarFile(pathToJar);
        Enumeration<JarEntry> e = jarFile.entries();

        URL[] urls = { new URL("jar:file:" + pathToJar + "!/") };
        URLClassLoader cl = URLClassLoader.newInstance(urls);

        ArrayList<Class> classes = new ArrayList<Class>();

        Object obj;

        while (e.hasMoreElements()) {
            JarEntry je = e.nextElement();
            if (je.isDirectory() || !je.getName().endsWith(".class")) {
                continue;
            }
            // -6 because of .class
            String className = je.getName().substring(0, je.getName().length() - 6);
            className = className.replace('/', '.');
            Class c = cl.loadClass(className);

            // System.out.println(c.getName());
            Method[] meth = c.getDeclaredMethods();
            for (Method m : meth) {
                // System.out.println("Checking method" + m.getName());

                Annotation[] anno = m.getAnnotations();
                for (Annotation a : anno) {
                    // System.out.println("Checking annotation" + a.toString());
                    if (a.toString().equals("@org.ooka.lzu.Start()")) {
                        obj = c.getConstructor().newInstance();
                        // m.invoke(obj);
                        components.add(new Component(c, cl));
                        // System.out.println("Invoked class: " + a.toString());
                    }

                }

            }

        }

        System.out.println("Deployed component");

    }

    public void startComponent(String identifier) throws Exception {
        for (Component comp : components) {
            Class c = comp.startClass;
            Method[] meth = c.getDeclaredMethods();
            for (Method m : meth) {
                // System.out.println("Checking method" + m.getName());

                Annotation[] anno = m.getAnnotations();
                for (Annotation a : anno) {
                    System.out.println("Checking annotation" + a.toString());
                    if (a.toString().equals("@org.ooka.lzu.Start()")) {
                        Object obj = c.getConstructor().newInstance();
                        m.invoke(obj);
                        System.out.println("Invoked class: " + a.toString());
                    }

                }

            }
        }
        System.out.println("Started component");
    }

    public ArrayList<Component> getComponents() {
        return this.components;
    }

    public static void main(String[] args) throws Exception {

        System.out.println("Hello, I am the Assembler!");

        System.out.println("Searching for all classes annotated by @Start...");

        RuntimeEnvironment run = RuntimeEnvironment.getInstance();

        String path_to_jar = "/home/thomas/Documents/Studies/Modules/ObjektOrientierteKompArch/Codebase/Java_Runtime_Environment/MyComponent/target/MyComponent-1.0-SNAPSHOT.jar";
        path_to_jar = "/home/thomas/Documents/Studies/Modules/ObjektOrientierteKompArch/Codebase/Java_Runtime_Environment/Runtime_Env/components/test-component-with-start.jar";

        run.deployComponent(path_to_jar);

        run.startComponent("0");

        RuntimeEnvironment.stopRuntime();

        //
        // Load all .jar files inside path
        //

        // String pathToJar =
        // "/home/thomas/Documents/Studies/Modules/ObjektOrientierteKompArch/Codebase/Component_Implementation/target/codesOOKA-1.0-SNAPSHOT.jar";
        // String pathToJar = "components";

        // File componentDir = new File(pathToJar);

        // // Get a list of all JAR files in the components directory
        // List<File> jarFiles = new ArrayList<>();
        // for (File file : componentDir.listFiles()) {
        // if (file.isFile() && file.getName().endsWith(".jar")) {
        // jarFiles.add(file);
        // }
        // }

        // // Create a URLClassLoader to load classes from the JAR files
        // URL[] jarURLs = new URL[jarFiles.size()];
        // for (int i = 0; i < jarFiles.size(); i++) {
        // jarURLs[i] = jarFiles.get(i).toURI().toURL();
        // }
        // URLClassLoader classLoader = new URLClassLoader(jarURLs);

        // // Iterate over all JAR files and load all classes from each JAR file
        // for (File jarFile : jarFiles) {
        // JarFile jar = new JarFile(jarFile);
        // Enumeration<JarEntry> entries = jar.entries();
        // while (entries.hasMoreElements()) {
        // JarEntry entry = entries.nextElement();
        // if (entry.getName().endsWith(".class")) {
        // String className = entry.getName().replaceAll("/", ".").replace(".class",
        // "");
        // Class<?> clazz = classLoader.loadClass(className);
        // // Check if the class is annotated with @Start

        // Annotation[] annotation = clazz.getAnnotations();
        // System.out.println("Found class: " + className + ", Annotation: " +
        // annotation);
        // if (annotation != null) {
        // // Do something with the loaded class
        // System.out.println(
        // "Found annotated class" + clazz.getName() + ", Annotation: " +
        // annotation.length);
        // // System.out.println(annotation[0].annotationType());

        // // Annotation annotation_raw = clazz.getAnnotation(Start.class);
        // // System.out.println(annotation_raw);
        // }
        // }
        // }
        // jar.close();
        // }
        // classLoader.close();

        //
        // Load specific jar file from path
        //

        // pathToJar =
        // "/home/thomas/Documents/Studies/Modules/ObjektOrientierteKompArch/Codebase/Java_Runtime_Environment/Runtime_Env/components/MyComponent-1.0-SNAPSHOT.jar";

        // JarFile jarFile = new JarFile(pathToJar);
        // Enumeration<JarEntry> e = jarFile.entries();

        // URL[] urls = { new URL("jar:file:" + pathToJar + "!/") };
        // URLClassLoader cl = URLClassLoader.newInstance(urls);

        // while (e.hasMoreElements()) {
        // JarEntry je = e.nextElement();
        // if (je.isDirectory() || !je.getName().endsWith(".class")) {
        // continue;
        // }
        // // -6 because of .class
        // String className = je.getName().substring(0, je.getName().length() - 6);
        // className = className.replace('/', '.');
        // Class<?> c = cl.loadClass(className);

        // System.out.println(c.getAnnotation(Start.class));
        // }
        // jarFile.close();

        // JarFile jarFile = new JarFile(pathToJar);
        // Enumeration<JarEntry> e = jarFile.entries();

        // URL[] urls = { new URL("jar:file:" + pathToJar+"!/") };
        // URLClassLoader cl = URLClassLoader.newInstance(urls);

        // while (e.hasMoreElements()) {
        // JarEntry je = e.nextElement();
        // if(je.isDirectory() || !je.getName().endsWith(".class")){
        // continue;
        // }
        // // -6 because of .class
        // String className = je.getName().substring(0,je.getName().length()-6);
        // className = className.replace('/', '.');
        // Class c = cl.loadClass(className);

        // }

        // System.out.println("Searching insdie" + urls);
    }
}
