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

For this method we did 3 test cases. The first one we put an empty heap as an input and we expect an exception `NoSuchElementException`. Our test will compare the BinaryHeap and see if there's any element to do a comparaison and when it won't find, it will throw an exception. For the second one, as an input it takes a heap with one element, returns the element and leave the heap empty. As for the last one, it takes a heap with multiple elements in correct order, returns the smallest one, and adapt the heap appropriatly. 

  * Method **Peek** :

For this method, we did also 3 test cases as the previous method. For the first test case is the same as the first one as the the first one in the pop method. As for the second, it returns the element without removing it. And for the third one it returns the smallest element without removing it.   


   
         
* Method **Push** :
  
For this method, we did like before 3 test cases. For the first one, we push into an empty and the element become the root. For the second one, we push in the heap with one element, it would reorder the heap and put the smallest element as the new root. And for the third one, we push in a heep with multiple elements and we reorder them and let the smallest one as the root.   

  * Method **Count**:

As for this method we did 3 test cases. For the first one we tested on an empty heap, the expected outcome is 0. For the second we tested after pushing elements and it should return the number of elements pushed. As for the last one, we tested after popping, it should return the number of elements left.   

3. In our case, we don't have any boolean operators. 

4. Using **PiTest** this is the result that we had a mutant score of 95%.   
    ![coverage](https://github.com/user-attachments/assets/c3d745be-20f6-4702-a0e9-2ba7567ff7a3)

After refactoring the test suite we added some new test cases and we noticed that the mutation score improved by 3%.   ![coverage2](https://github.com/user-attachments/assets/a72eb3c5-4b36-43de-839d-4db7cb38e1d4)     
This is the new cases that we added :   
* We tested by pushing null element to see the reaction of the heap.
```Java
@Test
    void testPushNull(){
        BinaryHeap<Integer> heap = new BinaryHeap<>(Integer::compareTo);
        assertThrows(IllegalArgumentException.class, ()->heap.push(null));
    }
```
* We tested by pushing large number
```Java
@Test
    void testPushLargeNum(){
        BinaryHeap<Integer> heap = new BinaryHeap<>(Integer::compareTo);
        heap.push(Integer.MAX_VALUE);
        heap.push(Integer.MIN_VALUE);
        assertEquals(2, heap.count());
        assertEquals(Integer.MIN_VALUE,heap.peek());
    }
```
* We tested how the heap will react if we pop after multiple pushing.

  ```Java
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
```

 * We tested to see it still count correctly even it's duplicate values
```Java
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
```

 

 
* We tested the consistency of `Peek` method to see if it returns the right result even after poping multiple times .

  ```Java
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
  ```




            
   
          



        
