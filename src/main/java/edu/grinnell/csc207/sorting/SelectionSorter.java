package edu.grinnell.csc207.sorting;

import java.util.Comparator;

import edu.grinnell.csc207.util.ArrayUtils;

/**
 * Something that sorts using selection sort.
 *
 * @param <T>
 *   The types of values that are sorted.
 *
 * @author Samuel A. Rebelsky
 */

public class SelectionSorter<T> implements Sorter<T> {
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
  public SelectionSorter(Comparator<? super T> comparator) {
    this.order = comparator;
  } // SelectionSorter(Comparator)

  // +---------+-----------------------------------------------------
  // | Methods |
  // +---------+

  /**
   * Sort an array in place using selection sort.
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
    // Search through the array to find the smallest element and sort it.
    for (int i = 0; i < values.length; i++) {
      int index = i;
      // Find the smallest element in the array.
      for (int j = i + 1; j < values.length; j++) {
        // Check if the element is the smallest being checked.
        if (order.compare(values[j], values[index]) <= 0) {
          index = j;
        } // if
      } // for-loop

      // A check to see if the smallest element is in the correct place.
      if (index != i) {
        ArrayUtils.swap(values, i, index);
      } // if
    } // for-loop
  } // sort(T[])
} // class SelectionSorter
