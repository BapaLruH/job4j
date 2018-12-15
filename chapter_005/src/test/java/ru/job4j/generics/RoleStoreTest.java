package ru.job4j.generics;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class RoleStoreTest {
    private RoleStore roleStore;

    @Before
    public void setUp() {
        roleStore = new RoleStore(10);
        roleStore.add(new Role("1"));
        roleStore.add(new Role("2"));
        roleStore.add(new Role("3"));
        roleStore.add(new Role("4"));
        roleStore.add(new Role("5"));
    }

    @Test
    public void whenAddNewRoleThenStoreContainThisRole() {
        Role role = new Role("new id");
        roleStore.add(role);
        assertThat(roleStore.findById("new id"), is(role));
    }

    @Test
    public void whenReplaceUserWithId1() {
        Role role = new Role("new id");
        roleStore.replace("1", role);
        assertThat(roleStore.findById("new id"), is(role));
    }

    @Test
    public void whenDeleteUserWithId5() {
        roleStore.delete("5");
        assertNull(roleStore.findById("5"));
    }

    @Test
    public void whenSearchUserById() {
        Role role = new Role("new id");
        roleStore.add(role);
        roleStore.add(new Role("6"));
        roleStore.add(new Role("7"));
        assertThat(roleStore.findById("new id"), is(role));
    }
}