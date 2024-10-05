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
         2. If there's one element, return that element.   
         3. If there's more than one element and in a correct order, it returns the minimum element.
         4. If there's more than one element and in a random order, returns the minimum element, but hold the heap property.
      * Method **Peek** :
          **Characteristics** :
             The same as the **Pop's** characteristics.
          **Blocks** :
         1. If the heap is empty, it should throw an exception `NoSuchElementException`.   
         2. If there's one element, return that element.   
         3. If there's more than one element and in a correct order, it returns the minimum element.
         4. If there's more than one element and in a random order, returns the minimum element, but without erasing.
    * Method **Push** :
        **Charasteristics** :
      1. **Heap Size** :  The same as the previous ones.
      2. **Position of elements**(influence the order after the insertion) : - Root : If the element is smaller than the current root, it becomes the new root.
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


        
