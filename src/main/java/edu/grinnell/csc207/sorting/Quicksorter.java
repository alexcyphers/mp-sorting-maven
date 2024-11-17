package edu.grinnell.csc207.sorting;

import java.util.Comparator;
import java.util.Random;

import edu.grinnell.csc207.util.ArrayUtils;

/**
 * Something that sorts using Quicksort.
 *
 * @param <T>
 *   The types of values that are sorted.
 *
 * @author Samuel A. Rebelsky
 */

public class Quicksorter<T> implements Sorter<T> {
  // +--------+------------------------------------------------------
  // | Fields |
  // +--------+

  /**
   * The way in which elements are ordered.
   */
  Comparator<? super T> order;

  /**
   * Allows us to use random numbers for the pivot.
   */
  static Random rand = new Random();


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
  public Quicksorter(Comparator<? super T> comparator) {
    this.order = comparator;
  } // Quicksorter(Comparator)

  // +---------+-----------------------------------------------------
  // | Methods |
  // +---------+

  /**
   * Sort an array in place using Quicksort.
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
    sortHelper(values, 0, values.length - 1);
  } // sort(T[])


  /**
   * Sorts the array of values using quick sort with recursion.
   *
   * @param values
   *  The array of values being sorted.
   * @param lb
   *  The lower bound of the portion of the array being sorted.
   * @param ub
   *  The upper bound of the portion of the array being sorted.
   */
  public void sortHelper(T[] values, int lb, int ub) {

    // Base case saying when there is no more array to sort.
    if (ub < lb) {
      return;
    } else {
      int pivot = partition(values, lb, ub);

      sortHelper(values, lb, pivot - 1); //Splits into left array
      sortHelper(values, pivot + 1, ub); //Splits into right array
    } // if/else
  } // sortHelper(T[], int, int)



  /**
   * Partitions a portion of the array by using a random pivot where
   * all values less than the pivot are on the left and all values
   * more than the pivot are to the right.
   *
   * @param values
   *  The array of values being sorted.
   * @param lb
   *  The lower bound of the portion of the array being sorted.
   * @param ub
   *  The upper bound of the portion of the array being sorted.
   * @return
   *  A number representing the index of the pivot.
   */
  public int partition(T[] values, int lb, int ub) {

    int pivot = lb + rand.nextInt(ub - lb + 1);
    //Find the random index for a pivot.

    int index = lb;

    ArrayUtils.swap(values, pivot, ub);

    // Shifts the elements of the array according to the pivot.
    for (int i = lb; i < ub; i++) {
      if (order.compare(values[i], values[ub]) < 0) {
        ArrayUtils.swap(values, index, i);
        index++;
      } // if
    } // for-loop

    ArrayUtils.swap(values, index, ub);

    return index;
  } // partition(T[], int, int)
} // class Quicksorter
