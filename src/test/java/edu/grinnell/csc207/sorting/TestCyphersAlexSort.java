package edu.grinnell.csc207.sorting;

import org.junit.jupiter.api.BeforeAll;

/**
 * Tests of our FakeSorter.
 */
public class TestCyphersAlexSort extends TestSorter {
  /**
   * Set up the sorters.
   */
  @BeforeAll
  static void setup() {
    stringSorter = new CyphersAlexSort<String>((x,y) -> x.compareTo(y));
    intSorter = new CyphersAlexSort<Integer>((x,y) -> x.compareTo(y));
  } // setup()

} // class TestFakeSorter

