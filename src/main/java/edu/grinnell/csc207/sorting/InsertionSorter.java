package edu.grinnell.csc207.sorting;

import java.util.Comparator;

/**
 * Something that sorts using insertion sort.
 *
 * @param <T>
 *   The types of values that are sorted.
 *
 * @author Alex Cyphers
 * @author Samuel A. Rebelsky
 */

public class InsertionSorter<T> implements Sorter<T> {
  // +--------+------------------------------------------------------
  // | Fields |
  // +--------+

  /**
   * The way in which elements are ordered.
   */
  Comparator<? super T> order;

  // +--------------+------------------------------------------------
  // | Constructors |
  // +--------------+

  /**
   * Create a sorter using a particular comparator.
   *
   * @param comparator
   *   The order in which elements in the array should be ordered
   *   after sorting.
   */
  public InsertionSorter(Comparator<? super T> comparator) {
    this.order = comparator;
  } // InsertionSorter(Comparator)

  // +---------+-----------------------------------------------------
  // | Methods |
  // +---------+

  /**
   * Sort an array in place using insertion sort.
   *
   * @param values
   *   an array to sort.
   *
   * @post
   *   The array has been sorted according to some order (often
   *   one given to the constructor).
   * @post
   *   For all i, 0 &lt; i &lt; values.length,
   *     order.compare(values[i-1], values[i]) &lt;= 0
   */
  @Override
  public void sort(T[] values) {
    //Performs insertion sort on the array.
    for (int i = 1; i < values.length; i++) {
      insert(values, i);
    } // for-loop
  } // sort(T[])


  /**
   * Inserts the value, at index i, into the correct position in the array.
   *
   * @param values
   *  The array being sorted.
   * @param i
   *  The index of the element we are sorting.
   */
  public void insert(T[] values, int i) {
    T val = values[i];
    int current = i - 1;

    //Shifts all values that are more than the current value to the right.
    while (current >= 0 && order.compare(values[current], val) > 0) {
      values[current + 1] = values[current];
      current--;
    } // while-loop

    values[current + 1] = val;
  } // insert(T[], int)
} // class InsertionSorter
