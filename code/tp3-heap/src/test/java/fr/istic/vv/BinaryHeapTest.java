package fr.istic.vv;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import java.util.Comparator;
import java.util.NoSuchElementException;

class BinaryHeapTest {
    @Test
    void testPopEmpty(){
        BinaryHeap<Integer> heap = new BinaryHeap<>(Comparator.<Integer>naturalOrder());

        assertThrows(NoSuchElementException.class, heap::pop);
    }

    @Test
    void testPopOneElem(){
        BinaryHeap<Integer> heap = new BinaryHeap<>(Integer::compareTo);
        heap.push(10);
        assertEquals(10, heap.pop());
        assertEquals(0, heap.count());
    }

    @Test
    void testPopMultipleElem(){
        BinaryHeap<Integer> heap = new BinaryHeap<>(Integer::compareTo);
        heap.push(14);
        heap.push(4);
        heap.push(20);
        assertEquals(4,heap.pop());//the small element
        assertEquals(2, heap.count());   // the staying elements: 20,14
    }

    @Test
    void testPeekEmpty(){
        BinaryHeap<Integer> heap = new BinaryHeap<>(Integer::compareTo);
        assertThrows(NoSuchElementException.class,heap::peek);
    }

    @Test
    void testPeekOneElem(){
        BinaryHeap<Integer> heap = new BinaryHeap<>(Integer::compareTo);
        heap.push(16);
        assertEquals(16,heap.peek());
        assertEquals(1,heap.count()); // not removing just peeking
    }
    @Test
    void testPeekMultiElem(){
        BinaryHeap<Integer> heap = new BinaryHeap<>(Integer::compareTo);
        heap.push(20);
        heap.push(4);
        heap.push(13);
        assertEquals(4, heap.peek());// the smallest element
        assertEquals(3, heap.count()); // element are not removed they stay the same
    }

    @Test
    void testPushEmpty(){
        BinaryHeap<Integer> heap = new BinaryHeap<>(Integer::compareTo);
        heap.push(18);
        assertEquals(1,heap.count());
        assertEquals(18, heap.peek()); // the element that we pushed become the root
    }

    @Test
    void testPushOneElem(){
        BinaryHeap<Integer> heap = new BinaryHeap<>(Integer::compareTo);
        heap.push(16);
        heap.push(3);
        assertEquals(2,heap.count());
        assertEquals(3,heap.peek());// the small one will be the new root
    }

    @Test
    void testPushMultiElem(){
        BinaryHeap<Integer> heap = new BinaryHeap<>(Integer::compareTo);
        heap.push(20);
        heap.push(10);
        heap.push(25);
        heap.push(2);
        assertEquals(4,heap.count());
        assertEquals(2,heap.peek()); // the small one will be the root
    }

    @Test
    void testCountEmpty(){
        BinaryHeap<Integer> heap = new BinaryHeap<>(Integer::compareTo);
        assertEquals(0,heap.count());
    }

    @Test
    void testCountPushElem(){
        BinaryHeap<Integer> heap = new BinaryHeap<>(Integer::compareTo);
        heap.push(12);
        assertEquals(1,heap.count());
        heap.push(30);
        assertEquals(2,heap.count());
    }

    @Test
    void testCountPopElem(){
        BinaryHeap<Integer> heap = new BinaryHeap<>(Integer::compareTo);
        heap.push(12);
        heap.push(29);
        heap.pop();
        assertEquals(1,heap.count());
    }

    @Test
    void testPushDupElem(){
        BinaryHeap<Integer> heap = new BinaryHeap<>(Integer::compareTo);
        heap.push(12);
        heap.push(12);
        assertEquals(12,heap.pop());
        assertEquals(12,heap.pop());
    }

    @Test
    void testPopElem(){
        BinaryHeap<Integer> heap = new BinaryHeap<>(Integer::compareTo);
        heap.push(12);
        heap.push(13);
        heap.pop();
        heap.pop();
        assertThrows(NoSuchElementException.class,heap::pop);
    }

    @Test
    void testReorderUpMulti(){
        BinaryHeap<Integer> heap = new BinaryHeap<>(Integer::compareTo);
        heap.push(12);
        heap.push(13);
        heap.push(14);
        heap.push(5);
        assertEquals(5,heap.peek());
    }

    @Test
    void testReorderDownMulti(){
        BinaryHeap<Integer> heap = new BinaryHeap<>(Integer::compareTo);
        heap.push(12);
        heap.push(13);
        heap.push(10);
        heap.push(5);
        heap.pop();
        assertEquals(10,heap.peek());

    }

    @Test
    void testPushNull(){
        BinaryHeap<Integer> heap = new BinaryHeap<>(Integer::compareTo);
        assertThrows(IllegalArgumentException.class, ()->heap.push(null));
    }

    @Test
    void testPushLargeNum(){
        BinaryHeap<Integer> heap = new BinaryHeap<>(Integer::compareTo);
        heap.push(Integer.MAX_VALUE);
        heap.push(Integer.MIN_VALUE);
        assertEquals(2, heap.count());
        assertEquals(Integer.MIN_VALUE,heap.peek());
    }

    @Test
    void testPopAfterMultiAdd() {
        BinaryHeap<Integer> heap = new BinaryHeap<>(Integer::compareTo);
        heap.push(5);
        heap.push(10);
        heap.push(2);
        heap.push(1);
        heap.pop(); // pops 1
        heap.pop(); // pops 2
        heap.pop(); // pops 5
        assertEquals(1, heap.count()); // only 10 should remain
        assertEquals(10, heap.peek());
    }

    @Test
    void testCountWithDupliVal() {
        BinaryHeap<Integer> heap = new BinaryHeap<>(Integer::compareTo);
        heap.push(5);
        heap.push(5);
        heap.push(5);
        assertEquals(3, heap.count());
        heap.pop(); // pops one of the 5s
        assertEquals(2, heap.count());
    }

    @Test
    void testPeekConsist() {
        BinaryHeap<Integer> heap = new BinaryHeap<>(Integer::compareTo);
        heap.push(3);
        heap.push(2);
        heap.push(4);
        assertEquals(2, heap.peek());
        heap.pop();
        assertEquals(3, heap.peek());
        heap.pop();
        assertEquals(4, heap.peek());
    }

}
