package ru.job4j.tracker;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ValidateInputTest {
    private final ByteArrayOutputStream baos = new ByteArrayOutputStream();
    private final PrintStream out = System.out;

    @Before
    public void loadBaos() {
        System.setOut(new PrintStream(baos));
    }

    @After
    public void loadSystemOut() {
        System.setOut(out);
    }

    @Test
    public void whenInvalidInput() {
        ValidateInput input = new ValidateInput(
                new StubInput(new String[]{"invalid", "1"})
        );
        input.ask("Enter", new int[]{1});
        assertThat(
                this.baos.toString(),
                is(
                        String.format("Please enter validate data again!%n")
                )
        );
    }

    @Test
    public void whenOInput() {
        ValidateInput input = new ValidateInput(
                new StubInput(new String[]{"5", "1"})
        );
        input.ask("Enter", new int[]{1});
        assertThat(
                this.baos.toString(),
                is(
                        String.format("Please select key from menu! 0 - %s%n", 0)
                )
        );
    }
}