package edu.grinnell.csc207.sorting;

import java.util.Comparator;

/**
 * Something that sorts using merge sort.
 *
 * @param <T>
 *   The types of values that are sorted.
 *
 * @author Samuel A. Rebelsky
 */

public class MergeSorter<T> implements Sorter<T> {
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
  public MergeSorter(Comparator<? super T> comparator) {
    this.order = comparator;
  } // MergeSorter(Comparator)

  // +---------+-----------------------------------------------------
  // | Methods |
  // +---------+




  
  /**
   * Sort an array in place using merge sort.
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

    T[] tmp = values.clone();
    
    sortHelper(values, tmp, 0, values.length - 1);
  } // sort(T[])


  public void sortHelper(T[] values, T[] tmp, int lb, int ub) {

    if(lb >= ub){
      return;
    }
    else {
      int mid = lb + ((ub - lb) / 2);

      sortHelper(values, tmp, lb, mid);
      sortHelper(values, tmp, mid + 1, ub);

      merge(values, tmp, lb, ub);
    }
  }

  public void merge(T[] values, T[] tmp, int lb, int ub) {

    int mid = (lb + ((ub - lb) / 2));

    //Copy the values into the temporary array.
    for(int i = lb; i <= ub; i++) {
      tmp[i] = values[i];
    }

    int valIndex = lb; // Index where we are sorting the smallest element.
    int startIndex = lb; // Index of left side of the array.
    int midIndex = mid + 1; // Index of right side of the array.

    while((startIndex != mid + 1) && (midIndex != ub + 1)) {
      if(order.compare(tmp[startIndex], tmp[midIndex]) <= 0) {
        values[valIndex] = tmp[startIndex];
        startIndex++;
      }
      else {
        values[valIndex] = tmp[midIndex];
        midIndex++;
      }
      valIndex++;
    }

    while(startIndex != mid + 1) {
      values[valIndex] = tmp[startIndex];
      startIndex++;
      valIndex++;
    }
  }


} // class MergeSorter
