package fr.istic.vv;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.NoSuchElementException;

class BinaryHeap<T> {
    private ArrayList<T> list; //list that stores the elements of the heap
    private Comparator<T> comparator; // to compare the elements

    public BinaryHeap(Comparator<T> comparator) {
        this.comparator = comparator;
        this.list = new ArrayList<>();
    }





    //remove the smallest element, change it with the last element and reorder the heap from downward
    public T pop() {
        if (list.isEmpty()) {
            throw new NoSuchElementException("Empty");
        }
        T result = list.get(0);
        T last = list.remove(list.size() - 1);
        if (!list.isEmpty()) {
            list.set(0, last);
            restoreOrderDown(0);
        }
        return result; }
//return the root without removing it
    public T peek() {
        if (list.isEmpty()) {
            throw new NoSuchElementException("Empty");
        }
        return list.get(0); }
// add an element to the list and reorder the heap upward
    public void push(T element) {
        if (element == null)
            throw new IllegalArgumentException("Null element");
        list.add(element);
        restoreOrderUp(list.size() - 1);
    }
// count the number of elements in the heap, the size of the heap
    public int count() { return list.size();}
    //reorder the heap after insertion of a new element from bottom to top, when there's a new push to put the addes element on the right place
    private void restoreOrderUp(int i) {
        T temp = list.get(i);
        while (i >0){
            int j = (i - 1) / 2; //index of the parent
            T parent = list.get(j);
            if (comparator.compare(temp, parent) < 0) { // check if the child is greater than the parent so we change the order if it is
                list.set(i, parent);
                i = j;
            } else {
                break;
            }
        }
        list.set(i, temp);
    }
    //reorder the heap after removing the root from top to bottom , tp put the new root who is normally the second smallest after the root
    private void restoreOrderDown(int i) {
        T temp = list.get(i);
        int s = list.size();
        while (i < s/2){ // while it has at least one child
            int j = i * 2 + 1; //leftchild
            int k = j + 1;//rightchild
            int smallest = j;//assume is the right one
            if (k < s && comparator.compare(list.get(k), list.get(j)) < 0) { // comparison between the right and left child seeing who dominates more, the smallest between both of them
                smallest = k;
            }
            if (comparator.compare(temp,list.get(smallest)) <= 0) { //if it equals don't change anything 
                break;
            }
            list.set(i,list.get(smallest));
            i = smallest;
        }
        list.set(i, temp);// place at the correct position
    }

}
