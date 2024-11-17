package edu.grinnell.csc207.sorting;

import java.util.Comparator;
import edu.grinnell.csc207.util.ArrayUtils;

/**
 * Something that sorts using insertion sort.
 *
 * @param <T>
 *   The types of values that are sorted.
 *
 * @author Alex Cyphers
 * @author Samuel A. Rebelsky
 */

public class CyphersAlexSort<T> implements Sorter<T> {
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
  public CyphersAlexSort(Comparator<? super T> comparator) {
    this.order = comparator;
  } // InsertionSorter(Comparator)

  // +---------+-----------------------------------------------------
  // | Methods |
  // +---------+

  /**
   * Sort an array in place using several sorting algorithms.
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
    if (values.length < 11) {
      InsertionSorter<T> insertionSort = new InsertionSorter<>(order);
      insertionSort.sort(values);
    } else if (isReversed(values)) {
      Quicksorter<T> quickSort = new Quicksorter<>(order);
      quickSort.sort(values);
    } else if (values.length < 10000) {
      fastQuickSort(values, 0, values.length - 1);
    } else {
      MergeSorter<T> mergeSort = new MergeSorter<>(order);
      mergeSort.sort(values);
    } // if/else
  } // sort(T[])


  /**
   * Sort an array using quick sort with two pivots.
   *
   * @param values
   *  an array to sort
   *
   * @param lb the lowerbound of the part of the array being sorted.
   * @param hb the upperbound of the part of the array being sorted.
   */
  public void fastQuickSort(T[] values, int lb, int hb) {

    //Base case saying when there is no more array to sort.
    if (lb >= hb) {
      return;
    } // if

    if (order.compare(values[lb], values[hb]) > 0) {
      ArrayUtils.swap(values, lb, hb);
    } // if

    T pivot1 = values[lb];
    T pivot2 = values[hb];


    int low = lb + 1;
    int high = hb - 1;

    //Inserts the pivot values into the proper parts of the array.
    for (int i = low; i <= high;) {
      if (order.compare(values[i], pivot1) < 0) {
        ArrayUtils.swap(values, i, low);
        low++;
        i++;
      } else if (order.compare(values[i], pivot2) > 0) {
        ArrayUtils.swap(values, i, high);
        high--;
      } else {
        i++;
      } // else/if
    } // for-loop

    low--;
    high++;
    ArrayUtils.swap(values, lb, low);
    ArrayUtils.swap(values, hb, high);
    
    fastQuickSort(values, lb, low - 1);
    fastQuickSort(values, low + 1, high - 1);
    fastQuickSort(values, high + 1, hb);
  } // fastQuickSort(T[], int, int)


  public Boolean isReversed(T[] values) {
    //Check through the array until a value in order is found.
    int expect = 0;
    for (int i = 0; i < values.length - 1; i++) {
      if (order.compare(values[i], values[i + 1]) < 0) {
        expect++;
      } // if

      if (expect > values.length * 0.1) {
        return false;
      }
    } // for-loop
    return true;
  }
} // class InsertionSorter

