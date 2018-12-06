package ru.job4j.bank;

import java.util.*;

/**
 * Сlass Bank.
 *
 * @author Evgeny Shevchenko (xxx-zet-xxx@yandex.ru)
 * @version 001
 * @since 02.12.2018
 */
public class Bank {
    private Map<User, List<Account>> userAccounts = new HashMap<>();

    public Bank(User user, Account account) {
        this.userAccounts.put(user, new ArrayList<>(Arrays.asList(account)));
    }

    /**
     * Method addUser.
     * Adds the user to userAccounts.
     *
     * @param user type User.
     */
    public void addUser(User user) {
        this.userAccounts.putIfAbsent(user, new ArrayList<>());
    }

    /**
     * Method deleteUser.
     * Remove the user from the userAccounts.
     *
     * @param user type User.
     */
    public void deleteUser(User user) {
        this.userAccounts.remove(user);
    }

    /**
     * Method addAccountToUser.
     * Adds a user account to userAccounts.
     *
     * @param passport type String.
     * @param account  type Account.
     */
    public void addAccountToUser(String passport, Account account) {
        User user = getUserByPassport(passport);
        if (user != null && this.userAccounts.containsKey(user)) {
            this.userAccounts.get(user).add(account);
        }
    }

    /**
     * Method deleteAccountFromUser.
     * Delete the user account.
     *
     * @param passport type String.
     * @param account  type Account.
     */
    public void deleteAccountFromUser(String passport, Account account) {
        User user = getUserByPassport(passport);
        if (user != null && this.userAccounts.containsKey(user)) {
            this.userAccounts.get(user).remove(account);
        }
    }

    /**
     * Method getUserAccounts.
     * Get all user accounts by the passport.
     *
     * @param passport type String.
     */
    public List<Account> getUserAccounts(String passport) {
        User user = getUserByPassport(passport);
        return this.userAccounts.entrySet().stream()
                .filter(entry -> entry.getKey().equals(user))
                .map(Map.Entry::getValue)
                .findFirst()
                .orElse(new ArrayList<>());
    }

    /**
     * Method transferMoney.
     * Money transfer from source to destination.
     *
     * @param srcPassport   type String.
     * @param srcRequisite  type String.
     * @param destPassport  type String.
     * @param destRequisite type String.
     * @param amount        type double.
     */
    public boolean transferMoney(String srcPassport, String srcRequisite,
                                 String destPassport, String destRequisite, double amount) {
        boolean result = false;
        Account srcAccount = getAccountByRequisite(srcPassport, srcRequisite);
        Account destAccount = getAccountByRequisite(destPassport, destRequisite);
        if (srcAccount != null && destAccount != null && !srcAccount.equals(destAccount)) {
            if (amount > 0 && srcAccount.getValue() >= amount) {
                srcAccount.setValue(srcAccount.getValue() - amount);
                destAccount.setValue(destAccount.getValue() + amount);
                result = true;
            }
        }
        return result;
    }

    private Account getAccountByRequisite(String passport, String requisite) {
        List<Account> accounts = this.getUserAccounts(passport);
        return accounts.stream()
                .filter(account -> account.getRequisites().equals(requisite))
                .findFirst()
                .orElse(null);
    }

    private User getUserByPassport(String passport) {
        return userAccounts.keySet().stream()
                .filter(usr -> usr.getPassport().equals(passport))
                .findFirst()
                .orElse(null);
    }
}
