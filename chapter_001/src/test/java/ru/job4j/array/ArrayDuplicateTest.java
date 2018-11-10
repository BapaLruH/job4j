package ru.job4j.array;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * ArrayDuplicateTest.
 *
 * @author Evgeny Shevchenko (xxx-zet-xxx@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class ArrayDuplicateTest {

    @Test
    public void whenRemoveDuplicatesThreeUniqueThenArrayWithoutDuplicate() {
        ArrayDuplicate duplicate = new ArrayDuplicate();
        String[] input = new String[]{"Привет", "Мир", "Привет", "Супер", "Мир"};
        String[] expect = new String[]{"Привет", "Мир", "Супер"};
        String[] result = duplicate.remove(input);
        assertThat(result, is(expect));
    }

    @Test
    public void whenRemoveDuplicatesTwoUniqueThenArrayWithoutDuplicate() {
        ArrayDuplicate duplicate = new ArrayDuplicate();
        String[] input = new String[]{"Привет", "Мир", "Привет", "Привет", "Мир"};
        String[] expect = new String[]{"Привет", "Мир"};
        String[] result = duplicate.remove(input);
        assertThat(result, is(expect));
    }

    @Test
    public void whenRemoveDuplicatesOneUniqueThenArrayWithoutDuplicate() {
        ArrayDuplicate duplicate = new ArrayDuplicate();
        String[] input = new String[]{"Привет", "Привет", "Привет", "Привет", "Привет"};
        String[] expect = new String[]{"Привет"};
        String[] result = duplicate.remove(input);
        assertThat(result, is(expect));
    }

}