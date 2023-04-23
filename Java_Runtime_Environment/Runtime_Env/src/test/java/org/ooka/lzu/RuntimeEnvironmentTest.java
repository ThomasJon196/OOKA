package org.ooka.lzu;

import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class RuntimeEnvironmentTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    String path_to_jar_with_annotation = "/home/thomas/Documents/Studies/Modules/ObjektOrientierteKompArch/Codebase/Java_Runtime_Environment/Runtime_Env/components/test-component-with-start.jar";
    String path_to_jar_without_annotation = "/home/thomas/Documents/Studies/Modules/ObjektOrientierteKompArch/Codebase/Java_Runtime_Environment/Runtime_Env/components/test-component-without-start.jar";

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @After
    public void destroyRuntime() {
        RuntimeEnvironment.stopRuntime();
    }

    @Test
    public void getInstance_ReturnsSameInstance() {
        RuntimeEnvironment environment1 = RuntimeEnvironment.getInstance();
        RuntimeEnvironment environment2 = RuntimeEnvironment.getInstance();

        assertSame(environment1, environment2);
    }

    @Test
    public void deployComponent_WithStartAnnotation_IsDeployed() throws Exception {
        // given
        RuntimeEnvironment environment = RuntimeEnvironment.getInstance();

        // when
        environment.deployComponent(path_to_jar_with_annotation);

        // then
        assertFalse(environment.getComponents().isEmpty());
    }

    @Test
    public void deployComponent_WithoutStartAnnotation_IsNotDeployed() throws Exception {
        // given
        RuntimeEnvironment environment = RuntimeEnvironment.getInstance();

        // when
        environment.deployComponent(path_to_jar_without_annotation);

        // then
        assertTrue(environment.getComponents().isEmpty());
        assertEquals(environment.getComponents().size(), 0);
    }

    @Test
    public void startComponent_WithStartAnnotation_IsStarted() throws Exception {
        // given
        RuntimeEnvironment environment = RuntimeEnvironment.getInstance();
        environment.deployComponent(path_to_jar_with_annotation);

        // when
        environment.startComponent("test-component");

        // then
        assertTrue(outContent.toString().contains("Invoked class: @org.ooka.lzu.Start()"));
        // assertEquals(outContent.toString(), "a");
        // assertEquals(jarPath, 0);
        // assertEquals(environment.getComponents().size(), 1 );
    }

    @Test
    public void startComponent_WithoutStartAnnotation_IsNotStarted() throws Exception {
        // given
        RuntimeEnvironment environment = RuntimeEnvironment.getInstance();
        environment.deployComponent(path_to_jar_without_annotation);

        // when
        environment.startComponent("test-component-without-start-annotation");

        // then
        // assertEquals(outContent.toString(), "a");
        assertFalse(outContent.toString().contains("Invoked class: @org.ooka.lzu.Start()"));
        assertEquals(environment.getComponents().size(), 0);
    }
}
