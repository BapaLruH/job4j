package ru.job4j.student;

import java.util.Comparator;

/**
 * Сlass Student.
 *
 * @author Evgeny Shevchenko (xxx-zet-xxx@yandex.ru)
 * @version 001
 * @since 07.12.2018
 */
public class Student implements Comparator<Student> {
    private String name;
    private int scope;

    public Student(String name, int scope) {
        this.name = name;
        this.scope = scope;
    }

    public String getName() {
        return name;
    }

    public int getScope() {
        return scope;
    }

    public void setScope(int scope) {
        this.scope = scope;
    }

    @Override
    public int compare(Student o1, Student o2) {
        return Integer.compare(o2.scope, o1.scope);
    }
}
