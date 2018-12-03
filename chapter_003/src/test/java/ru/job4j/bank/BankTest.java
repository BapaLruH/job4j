package ru.job4j.bank;

import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class BankTest {

    @Test
    public void whenAddNewUserThenContainsUser() {
        User ivan = new User("Ivan", "6090 555656");
        User petr = new User("Petr", "6090 123456");
        Account account = new Account(5.0D, "123456789");
        Bank bank = new Bank(ivan, account);
        bank.addUser(petr);
        try {
            Method method = bank.getClass().getDeclaredMethod("getUserByPassport", String.class);
            method.setAccessible(true);
            User result = (User) method.invoke(bank, "6090 123456");
            assertThat(result, is(petr));
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void whenDeleteUserThenNotContainsUser() {
        User ivan = new User("Ivan", "6090 555656");
        Account account = new Account(5.0D, "123456789");
        Bank bank = new Bank(ivan, account);
        bank.deleteUser(ivan);
        try {
            Method method = bank.getClass().getDeclaredMethod("getUserByPassport", String.class);
            method.setAccessible(true);
            User result = (User) method.invoke(bank, "6090 555656");
            assertNull(result);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void whenAddNewAccountUserThenContainsAccounts() {
        User ivan = new User("Ivan", "6090 555656");
        Account first = new Account(5.0D, "123456789");
        Account second = new Account(5.0D, "987654321");
        Bank bank = new Bank(ivan, first);
        bank.addAccountToUser(ivan.getPassport(), second);
        assertThat(bank.getUserAccounts(ivan.getPassport()), is(
                Arrays.asList(first, second)
        ));
    }

    @Test
    public void whenDeleteAccountUserThenNotContainsAccount() {
        User ivan = new User("Ivan", "6090 555656");
        Account first = new Account(5.0D, "123456789");
        Bank bank = new Bank(ivan, first);
        bank.deleteAccountFromUser(ivan.getPassport(), first);
        assertThat(bank.getUserAccounts(ivan.getPassport()), is(new ArrayList()));
    }

    @Test
    public void whenTransferMoneyFromFirstAccountToSecondAccountThenSecondAccountValue10() {
        User ivan = new User("Ivan", "6090 555656");
        Account first = new Account(5.0D, "123456789");
        Account second = new Account(5.0D, "987654321");
        Bank bank = new Bank(ivan, first);
        bank.addAccountToUser(ivan.getPassport(), second);
        bank.transferMoney(ivan.getPassport(), first.getRequisites(), ivan.getPassport(), second.getRequisites(), 5D);
        assertThat(second.getValue(), is(10D));
    }
}