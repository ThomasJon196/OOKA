package org.ooka.lzu;

import org.ooka.component.Start;
import org.ooka.component.App;
import java.io.File;
import java.lang.annotation.Annotation;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * Hello world!
 *
 */

public class ComponentAssembler {

    private RuntimeEnvironment runtime;

    public ComponentAssembler() {
    }

    public void startRuntime() {
        runtime = new RuntimeEnvironment();
        System.out.println("Starting runtime environment");
    }

    public void stopRuntime() {
        System.out.println("Stopping runtime environment");
        runtime = null;
    }

    public void deployComponent() {
    }

    public void startComponent() {
    }

    public static void main(String[] args) throws Exception {
        System.out.println("Hello, I am the Assembler!");

        System.out.println("Searching for all classes annotated by @Start...");


        //
        // Load all .jar files inside path
        //

        String pathToJar = "components";

        File componentDir = new File(pathToJar);

        // Get a list of all JAR files in the components directory
        List<File> jarFiles = new ArrayList<>();
        for (File file : componentDir.listFiles()) {
            if (file.isFile() && file.getName().endsWith(".jar")) {
                jarFiles.add(file);
            }
        }

        // Create a URLClassLoader to load classes from the JAR files
        URL[] jarURLs = new URL[jarFiles.size()];
        for (int i = 0; i < jarFiles.size(); i++) {
            jarURLs[i] = jarFiles.get(i).toURI().toURL();
        }
        URLClassLoader classLoader = new URLClassLoader(jarURLs);

        // Iterate over all JAR files and load all classes from each JAR file
        for (File jarFile : jarFiles) {
            JarFile jar = new JarFile(jarFile);
            Enumeration<JarEntry> entries = jar.entries();
            while (entries.hasMoreElements()) {
                JarEntry entry = entries.nextElement();
                if (entry.getName().endsWith(".class")) {
                    String className = entry.getName().replaceAll("/", ".").replace(".class", "");
                    Class<?> clazz = classLoader.loadClass(className);
                    // Check if the class is annotated with @Start

                    Annotation[] annotation = clazz.getAnnotations();
                    System.out.println("Found class: " + className + ", Annotation: " + annotation);
                    if (annotation != null) {
                        // Do something with the loaded class
                        System.out.println(
                                "Found annotated class" + clazz.getName() + ", Annotation: " + annotation.length);
                        System.out.println(annotation[0].annotationType());

                        Annotation annotation_raw = clazz.getAnnotation(Start.class);
                        System.out.println(annotation_raw);
                    }
                }
            }
            jar.close();
        }
        classLoader.close();

        //
        // Load specific jar file from path
        //

        pathToJar = "/home/thomas/Documents/Studies/Modules/ObjektOrientierteKompArch/Codebase/Java_Runtime_Environment/Runtime_Env/components/MyComponent-1.0-SNAPSHOT.jar";

        JarFile jarFile = new JarFile(pathToJar);
        Enumeration<JarEntry> e = jarFile.entries();

        URL[] urls = { new URL("jar:file:" + pathToJar + "!/") };
        URLClassLoader cl = URLClassLoader.newInstance(urls);

        while (e.hasMoreElements()) {
            JarEntry je = e.nextElement();
            if (je.isDirectory() || !je.getName().endsWith(".class")) {
                continue;
            }
            // -6 because of .class
            String className = je.getName().substring(0, je.getName().length() - 6);
            className = className.replace('/', '.');
            Class<?> c = cl.loadClass(className);

            System.out.println(c.getAnnotation(Start.class));
        }
        jarFile.close();

        // 
        // Check annotation via direct import
        // (works)


        Class<App> appClass = App.class;

        // Check if the @Start annotation is present on the App class
        boolean hasStartAnnotation = appClass.isAnnotationPresent(Start.class);

        if (hasStartAnnotation) {
            System.out.println("@Start annotation found");
        } else {
            System.out.println("No @Start annotation");
        }

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
