package ru.job4j.list;

/**
 * Class Store.
 *
 * @author Evgeny Shevchenko (xxx-zet-xxx@yandex.ru)
 * @version 001
 * @since 14.12.2018
 */
public class SimpleArrayList<E> {

    private int size;
    private Node<E> first;

    /**
     * Ensures that this array contains the specific element.
     *
     * @param date element whose presence in this array is to be ensured
     */
    public void add(E date) {
        Node<E> newLink = new Node<>(date);
        newLink.next = this.first;
        this.first = newLink;
        this.size++;
    }

    /**
     * Removes the first element of the array.
     *
     * @return deleted element
     */
    public E delete() {
        Node<E> first = null;
        if (this.first != null) {
            Node<E> next = this.first.next;
            first = this.first;
            this.first = next;
            size--;
        }
        return first.date;
    }

    /**
     * Returns the element at the specified id in the list.
     *
     * @param index id of the element to search
     * @return the element at the specified id
     */
    public E get(int index) {
        Node<E> result = this.first;
        for (int i = 0; i < index; i++) {
            result = result.next;
        }
        return result.date;
    }

    /**
     * Returns the size of the array.
     * @return Returns the size of the array
     */
    public int getSize() {
        return this.size;
    }

    private static class Node<E> {
        E date;
        Node<E> next;

        Node(E date) {
            this.date = date;
        }
    }
}
