import java.util.Iterator;
import java.util.NoSuchElementException;

// A double-ended queue or deque is a generalization of a stack and a queue that supports adding and removing items
// from either the front or the back of the data structure

//  Performance Requirements
//  The deque implementation must support each deque operation (including construction) in constant worst-case time.
//  A deque containing n items must use at most 48n + 192 bytes of memory.
//  Additionally, your iterator implementation must support each operation (including construction) in constant worst-case time.


public class Deque<Item> implements Iterable<Item> {

    private Node first;
    private Node last;
    private int N;

    private class Node {
        Item item;
        Node previous;
        Node next;
    }

    // construct an empty deque
    public Deque() {
        first = null;
        last = null;
        N = 0;
    }

    // is the deque empty?
    public boolean isEmpty() {
        return N == 0;
    }

    // return the number of items on the deque
    public int size() {
        return N;
    }

    // add the item to the front
    public void addFirst(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }
        Node oldFirst = first;
        first = new Node();
        first.item = item;
        first.next = oldFirst;
        if (oldFirst != null) {
            first.previous = oldFirst;
        } else {
            first.previous = null;
        }
        if (last == null) {
            last = first;
        }
        N++;
    }

    // add the item to the back
    public void addLast(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("Item is null");
        }
        Node oldLast = last;
        last = new Node();
        last.item = item;
        last.previous = oldLast;
        last.next = null;
        if (oldLast != null) {
            oldLast.next = last;
        } else {
            first = last;
        }
        N++;
    }

    // remove and return the item from the front
    public Item removeFirst() {
        if (N == 0) {
            throw new NoSuchElementException();
        }
        Item item = first.item;
        first = first.next;
        if (first != null) {
            first.previous = null;
        } else {
            last = null;
        }
        N--;
        return item;
    }

    // remove and return the item from the back
    public Item removeLast() {
        if (N == 0) {
            throw new NoSuchElementException();
        }
        Item item = last.item;
        last = last.previous;
        last.next = null;
        if (last == null) {
            first = null;
        }
        N--;
        return item;
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {
        return new DequeIterator();
    }

    private class DequeIterator implements Iterator<Item> {
        private Node current = first;

        public boolean hasNext() {
            return current != null;
        }

        public void remove() {
            throw new UnsupportedOperationException("Method remove cannot be invoke");
        }

        public Item next() {
            if (current == null) {
                throw new NoSuchElementException();
            }
            Item item = current.item;
            current = current.next;
            return item;
        }

    }

    // unit testing (required)
    public static void main(String[] args) throws IllegalAccessException {
        Deque<String> dequeOfStrings = new Deque<String>();
        dequeOfStrings.addFirst("Hi");
        dequeOfStrings.addLast("There");
        dequeOfStrings.addFirst("1");
        dequeOfStrings.addLast("2");
        System.out.println(dequeOfStrings.size());
        Iterator<String> iterator = dequeOfStrings.iterator();
        for (String str : dequeOfStrings) {
            System.out.println(str);
        }
        System.out.println(dequeOfStrings.removeFirst());
        System.out.println(dequeOfStrings.removeLast());
        System.out.println(dequeOfStrings.removeFirst());
        System.out.println(dequeOfStrings.removeFirst());


    }

}
