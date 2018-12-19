package ru.job4j.list;

/**
 * Class SimpleQueue.
 *
 * @author Evgeny Shevchenko (xxx-zet-xxx@yandex.ru)
 * @version 001
 * @since 19.12.2018
 */
public class Node<T> {
    T value;
    Node<T> next;

    public Node(T value) {
        this.value = value;
    }

    public void setNext(Node<T> next) {
        this.next = next;
    }

    public boolean hasLoop() {
        boolean result = false;
        Node<T> slow = this;
        Node<T> fast = this;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                result = true;
                break;
            }

        }
        return result;
    }
}
