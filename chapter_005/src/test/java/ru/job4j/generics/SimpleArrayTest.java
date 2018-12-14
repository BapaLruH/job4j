package ru.job4j.generics;

import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class SimpleArrayTest {

    private SimpleArray<String> array;

    @Before
    public void setUp() {
        this.array = new SimpleArray<>(10);
        this.array.add("element_1");
        this.array.add("element_2");
        this.array.add("element_3");
        this.array.add("element_4");
        this.array.add("element_5");
    }

    @Test
    public void whenAddElementInArrayThenArrayContainsElement() {
        String element = "element_6";
        this.array.add(element);
        String result = "";
        for (String s : this.array) {
            if (s.equals(element)) {
                result = s;
                break;
            }
        }
        assertThat(this.array.get(5), is(result));
    }

    @Test
    public void whenSetElementByIndexThenElementWithIndexEqualsCurrentElement() {
        String element = "element_6";
        this.array.set(4, element);
        assertThat(this.array.get(4), is(element));
    }

    @Test
    public void whenRemoveElementInArrayThenArrayNotContainsElement() {
        this.array.remove(3);
        assertThat(this.array.get(3), is("element_5"));
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void whenAddNewElementInFilledArrayThenException() {
        this.array.add("element_6");
        this.array.add("element_7");
        this.array.add("element_8");
        this.array.add("element_9");
        this.array.add("element_10");
        this.array.add("exception");
    }

    @Test(expected = NoSuchElementException.class)
    public void whenNextElementMissingThenException() {
        Iterator<String> it = this.array.iterator();
        for (int i = 0; i < 10; i++) {
            it.next();
        }
    }
}