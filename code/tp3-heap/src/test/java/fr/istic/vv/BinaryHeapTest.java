package fr.istic.vv;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import java.util.Comparator;
import java.util.NoSuchElementException;

class BinaryHeapTest {
    // testing on an empty so the code should return that the heap is empty
    @Test
    void testPopEmpty(){
        BinaryHeap<Integer> heap = new BinaryHeap<>(Comparator.<Integer>naturalOrder());

        assertThrows(NoSuchElementException.class, heap::pop);
    }

    /*
    Testing when there's just one element on the heap
     */
    @Test
    void testPopOneElem(){
        BinaryHeap<Integer> heap = new BinaryHeap<>(Integer::compareTo);
        heap.push(10);
        assertEquals(10, heap.pop());
        assertEquals(0, heap.count());
    }
/*
after pushing multiple elements we pop the smallest element and verify how many element staying
normally it should be minus 1 knowing that pop delete the element when it returns it
 */
    @Test
    void testPopMultipleElem(){
        BinaryHeap<Integer> heap = new BinaryHeap<>(Integer::compareTo);
        heap.push(14);
        heap.push(4);
        heap.push(20);
        assertEquals(4,heap.pop());//the small element
        assertEquals(2, heap.count());   // the staying elements: 20,14
    }

    // peek on an empty heap, nothing to see !!!
    @Test
    void testPeekEmpty(){
        BinaryHeap<Integer> heap = new BinaryHeap<>(Integer::compareTo);
        assertThrows(NoSuchElementException.class,heap::peek);
    }
/*
peeking the only element that exists in the heap and verify if it's still there or not
 */
    @Test
    void testPeekOneElem(){
        BinaryHeap<Integer> heap = new BinaryHeap<>(Integer::compareTo);
        heap.push(16);
        assertEquals(16,heap.peek());
        assertEquals(1,heap.count()); // not removing just peeking
    }
    /*
    peeking after adding multiple elements, it returns the smallest element without deleting it
    we verify that by counting how many are there, it should be the same as we pushed knowing that the heap was empty at the start
     */
    @Test
    void testPeekMultiElem(){
        BinaryHeap<Integer> heap = new BinaryHeap<>(Integer::compareTo);
        heap.push(20);
        heap.push(4);
        heap.push(13);
        assertEquals(4, heap.peek());// the smallest element
        assertEquals(3, heap.count()); // element are not removed they stay the same
    }

    // pushing on a empty element it will be the only one that exists
    @Test
    void testPushEmpty(){
        BinaryHeap<Integer> heap = new BinaryHeap<>(Integer::compareTo);
        heap.push(18);
        assertEquals(1,heap.count());
        assertEquals(18, heap.peek()); // the element that we pushed become the root
    }
/*
pushing a new element, we verify if it's the small one if it is it will be the new root
and we verify that by peeking to see if it returns the smallest
 */
    @Test
    void testPushOneElem(){
        BinaryHeap<Integer> heap = new BinaryHeap<>(Integer::compareTo);
        heap.push(16);
        heap.push(3);
        assertEquals(2,heap.count());
        assertEquals(3,heap.peek());// the small one will be the new root
    }
/*
we push multiple element and we see if the smallest one is the one it returns when we peek
 */
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

    // when we count on an empty heap it returns 0
    @Test
    void testCountEmpty(){
        BinaryHeap<Integer> heap = new BinaryHeap<>(Integer::compareTo);
        assertEquals(0,heap.count());
    }
// counting after pushing it returns the number of the existed ones plus the new ones
    @Test
    void testCountPushElem(){
        BinaryHeap<Integer> heap = new BinaryHeap<>(Integer::compareTo);
        heap.push(12);
        assertEquals(1,heap.count());
        heap.push(30);
        assertEquals(2,heap.count());
    }
// counting after deleting to see if it takes into consideration
    @Test
    void testCountPopElem(){
        BinaryHeap<Integer> heap = new BinaryHeap<>(Integer::compareTo);
        heap.push(12);
        heap.push(29);
        heap.pop();
        assertEquals(1,heap.count());
    }
/*
we push the same values multiple time to see if the order is still maintened even if it's equals
 */
    @Test
    void testPushEquElem(){
        BinaryHeap<Integer> heap = new BinaryHeap<>(Integer::compareTo);
        heap.push(12);
        heap.push(12);
        assertEquals(12,heap.pop());
        assertEquals(12,heap.pop());
    }

    /*
    testing if poping works correctly it should delete the element after returning it
     */
    @Test
    void testPopElem(){
        BinaryHeap<Integer> heap = new BinaryHeap<>(Integer::compareTo);
        heap.push(12);
        heap.push(13);
        heap.pop();
        heap.pop();
        assertThrows(NoSuchElementException.class,heap::pop);
    }
/*
to test if the child are always calculated and the heap is still correct even with differnet push and pop
 */
    @Test
    void testPopOdElem(){
        BinaryHeap<Integer> heap = new BinaryHeap<>(Integer::compareTo);
        heap.push(12);
        heap.push(14);
        heap.push(7);
        heap.push(3);
        heap.push(1);
        assertEquals(1,heap.pop());
        assertEquals(3,heap.peek());

    }

    /*
    reorder the heap from bottom to top after pushing new elements
     */
    @Test
    void testReorderUpMulti(){
        BinaryHeap<Integer> heap = new BinaryHeap<>(Integer::compareTo);
        heap.push(12);
        heap.push(13);
        heap.push(14);
        heap.push(5);
        assertEquals(5,heap.peek());
    }
/*
we tested the reorder from top to bottom , also when the childs have close values to see the reation of the code
 */
    @Test
    void testReorderDownMulti(){
        BinaryHeap<Integer> heap = new BinaryHeap<>(Integer::compareTo);
        heap.push(12);
        heap.push(13);
        heap.push(10);
        heap.push(9); // has a close value to the other child
        heap.push(5);
        heap.pop();
        assertEquals(9,heap.pop());
        assertEquals(10,heap.peek());

    }
    /*
    here we test when all the values are the same to see if he takes it in consideration or not
     */
@Test
void testReorderDownSameElem(){
        BinaryHeap<Integer> heap = new BinaryHeap<>(Integer::compareTo);
        heap.push(12);
        heap.push(12);
        heap.push(12);
        assertEquals(12,heap.pop());
        assertEquals(12,heap.peek());
}

    /*
To test the reOrder fucntion multiple times when the right child is grater than the left child
just to see if it will be tested correctly
     */
    @Test
    void testReorderMany(){
        BinaryHeap<Integer> heap = new BinaryHeap<>(Integer::compareTo);
        heap.push(13);
        heap.push(12);
        heap.push(30);
        heap.push(5);
        heap.push(35); //right child greater
        assertEquals(5,heap.pop()); // delete the smallest element
        assertEquals(12,heap.pop()); // see the smallest element after deleting the first one
    }

    // when we push a null element an exception is rised
    @Test
    void testPushNull(){
        BinaryHeap<Integer> heap = new BinaryHeap<>(Integer::compareTo);
        assertThrows(IllegalArgumentException.class, ()->heap.push(null));
    }
// we tested with large numbers to see if it's still works when we stress it
    @Test
    void testPushLargeNum(){
        BinaryHeap<Integer> heap = new BinaryHeap<>(Integer::compareTo);
        heap.push(Integer.MAX_VALUE);
        heap.push(Integer.MIN_VALUE);
        assertEquals(2, heap.count());
        assertEquals(Integer.MIN_VALUE,heap.peek());
    }
// to see if after a series of popping it pop the correct one
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

    // test if it counts the equal values
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


}
