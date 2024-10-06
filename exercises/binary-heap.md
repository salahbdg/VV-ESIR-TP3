# Implementing and testing a binary heap

A [*binary heap*](https://en.wikipedia.org/wiki/Binary_heap) is a data structure that contains comparable objects and it is able to efficiently return the lowest element.
This data structure relies on a binary tree to keep the insertion and deletion operations efficient. It is the base of the [*Heapsort* algorithm](https://en.wikipedia.org/wiki/Heapsort).

Implement a `BinaryHeap` class with the following interface:

```java
class BinaryHeap<T> {

    public BinaryHeap(Comparator<T> comparator) { ... }

    public T pop() { ... }

    public T peek() { ... }

    public void push(T element) { ... }

    public int count() { ... }

}
```

A `BinaryHeap` instance is created using a `Comparator` object that represents the ordering criterion between the objects in the heap.
`pop` returns and removes the minimum object in the heap. If the heap is empty it throws a `NotSuchElementException`.
`peek` similar to `pop`, returns the minimum object but it does not remove it from the `BinaryHeap`.
`push` adds an element to the `BinaryHeap`.
`count` returns the number of elements in the `BinaryHeap`.

Design and implement a test suite for this `BinaryHeap` class.
Feel free to add any extra method you may need.

Use the following steps to design the test suite:

1. With the help of *Input Space Partitioning* design a set of initial test inputs for each method. Write below the characteristics and blocks you identified for each method. Specify which characteristics are common to more than one method.
2. Evaluate the statement coverage of the test cases designed in the previous step. If needed, add new test cases to increase the coverage. Describe below what you did in this step.
3. If you have in your code any predicate that uses more than two boolean operators check if the test cases written to far satisfy *Base Choice Coverage*. If needed add new test cases. Describe below how you evaluated the logic coverage and the new test cases you added.
4. Use PIT to evaluate the test suite you have so far. Describe below the mutation score and the live mutants. Add new test cases or refactor the existing ones to achieve a high mutation score.

Use the project in [tp3-heap](../code/tp3-heap) to complete this exercise.

## Answer   

1. With the help of *Input Space Partitioning* we identified the characteristics and blocks for each method.
    * Method **Pop** :
      
         **Characteristics :**
         1. **Heap Size** : - Empty : There's no elements
                            - Non-Empty : There's at least one element
         2. **Element's order** : - Correct : The elements are in the correct order
                                  - Random : The elements are placed in a random order but the heap property should hold.
            **Blocks** :
         1. If the heap is empty, it should throw an exception `NoSuchElementException`.   
         2. If there's one element, return that element and leave the heap empty.   
         3. If there's more than one element and in a correct order, it returns the minimum element.
         4. If there's more than one element and in a random order, returns the minimum element, but hold the heap property.
    * Method **Peek** :
   
         **Characteristics** :The same as the **Pop's** characteristics.
      
        **Blocks** :   
         1. If the heap is empty, it should throw an exception `NoSuchElementException`.   
         2. If there's one element, return that element.   
         3. If there's more than one element and in a correct order, it returns the minimum element.
         4. If there's more than one element and in a random order, returns the minimum element, but without erasing.
    * Method **Push** :
   
        **Charasteristics** :
      
      1. **Heap Size** :  The same as the previous ones.
      2. **Position of elements**(influence the order after the insertion) :   
            - Root : If the element is smaller than the current root, it becomes the new root.
            - Leaf : The added element as a leaf but it might cause fixing the binary search tree structure.
         
            
         **Blocks** :
         
      1. If the heap is empty, insert the elements as the root.
      2. If the heap has one element, insert the new element but maintain the heap structure.
      3. If the heap has more than one elements, refix the order if the binary search tree structure after insertion.
      
    * Method **Count** :
       
         **Characteristics** : The same as **Peek** and **Pop**(empty and non-empty).
      
        
         **Blocks** :
        
      1. If the heap is empty, return 0.
      2. If the heap has one element, return 1.
      3. If the heap has more elements, return the correct number of elements after push or pop operations.

The Characteristics that are more common to more than one method are **empty** and the **non-empty**.    

2. Now we are going to write test cases that cover the description we did previously.

   * Method **Pop** :
        - **Test case 1:** from an empty heap
          ```Java
          @Test
          void testPopEmpty(){
          BinaryHeap<Integer> heap = new BinaryHeap<>(Comparator.naturalOrder());
          assertThrows(NoSuchElementException.class, heap::pop);
          }
          ```
        - **Test case 2:** a heap with one element
          ```Java
          @Test
          void testPopOneElem(){
          BinaryHeap<Integer> heap = new BinaryHeap<>(Integer::compareTo);
          heap.push(10);
          assertEquals(10, heap.pop());
          assertEquals(0, heap.count());
          }
          ```
         
        - **Test case 3:** more than one element
          ```Java
          @Test
          void testPopMultipleElem(){
          BinaryHeap<Integer> heap = new BinaryHeap<>(Integer::compareTo);
          heap.push(14);
          heap.push(4);
          heap.push(20);
          assertEquals(4,heap.pop());//the small element
          assertEquals(2, heap.count());   // the staying elements: 20,14
          }
          ```

For this method we did 3 test cases. The first one we put an empty heap as an input and we expect an exception `NoSuchElementException`. Our test will compare the BinaryHeap and see if there's any element to do a comparaison and when it won't find, it will throw an exception. For the second one, as an input it takes a heap with one element, returns the element and leave the heap empty. As for the last one, it takes a heap with multiple elements in correct order, returns the smallest one, and adapt the heap appropriatly. 

  * Method **Peek** :
       - **Test case 1:** empty heap
          ```Java
          @Test
          void testPeekEmpty(){
          BinaryHeap<Integer> heap = new BinaryHeap<>(Integer::compareTo);
          assertThrows(NoSuchElementException.class,heap::peek);
          }
          ```
       - **Test case 2:** one element
         ```Java
         @Test
         void testPeekOneElem(){
         BinaryHeap<Integer> heap = new BinaryHeap<>(Integer::compareTo);
         heap.push(16);
         assertEquals(16,heap.peek());
         assertEquals(1,heap.count()); // not removing just peeking
         }
         ```
       - **Test case 3:** more than one element
         ```Java
         @Test
         void testPeekMultiElem(){
         BinaryHeap<Integer> heap = new BinaryHeap<>(Integer::compareTo);
         heap.push(20);
         heap.push(4);
         heap.push(13);
         assertEquals(4, heap.peek());// the smallest element
         assertEquals(3, heap.count()); // element are not removed they stay the same
         }
         ```
For this method, we did also 3 test cases as the previous method. For the first test case is the same as the first one as the the first one in the pop method. As for the second, it returns the element without removing it. And for the third one it returns the smallest element without removing it.   


   
         
* Method **Push** :
  
  - **Test case 1:** empty heap
  
    ```Java
       @Test
        void testPushEmpty(){
        BinaryHeap<Integer> heap = new BinaryHeap<>(Integer::compareTo);
        heap.push(18);
        assertEquals(1,heap.count());
        assertEquals(18, heap.peek()); // the element that we pushed become the root
         }
    ```
      
      - **Test case 2:** heap with one element
     
        ```Java
        @Test
        void testPushOneElem(){
        BinaryHeap<Integer> heap = new BinaryHeap<>(Integer::compareTo);
        heap.push(16);
        heap.push(3);
        assertEquals(2,heap.count());
        assertEquals(16,heap.peek());// the small one will be the new root
        }
        ```
        
        
      - **Test case 3:** more than one element
        
    ```Java
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
    ```
For this method, we did like before 3 test cases. For the first one, we push into an empty and the element become the root. For the second one, we push in the heap with one element, it would reorder the heap and put the smallest element as the new root. And for the third one, we push in a heep with multiple elements and we reorder them and let the smallest one as the root.   

  * Method **Count**:
       - **Test case 1**: on an empty heap
    
    ```Java
    @Test
    void testCountEmpty(){
    BinaryHeap<Integer> heap = new BinaryHeap<>(Integer::compareTo);
    assertEquals(0,heap.count());
    }
    ```
      - **Test case 2**: after pushing elements
    ```Java
    @Test
    void testCountPushElem(){
    BinaryHeap<Integer> heap = new BinaryHeap<>(Integer::compareTo);
    heap.push(12);
    assertEquals(1,heap.count());
    heap.push(30);
    assertEquals(2,heap.count());
    }
    ```

      - **Test case 3**: after popping elements
    ```Java
    @Test
    void testCountPopElem(){
    BinaryHeap<Integer> heap = new BinaryHeap<>(Integer::compareTo);
    heap.push(12);
    heap.push(29);
    heap.pop();
    assertEquals(1,heap.count());
    }
    ```

As for this method we did 3 test cases. For the first one we tested on an empty heap, the expected outcome is 0. For the second we tested after pushing elements and it should return the number of elements pushed. As for the last one, we tested after popping, it should return the number of elements left 
    

        
            
             
            
   
          



        
