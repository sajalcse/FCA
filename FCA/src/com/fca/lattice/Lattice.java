package com.fca.lattice;

public abstract class Lattice {
	/**
	 * This field represents the number of total elements to construct a
	 * lattice .For example, boolean lattice consists of total number of two elements...
	 */
	private int noOfElements;

	/**
	 * This method helps to access the private field noOfElements.
	 * 
	 * @return it returns the number of elements representing by lattice.
	 */
	public int getNoOfElements() {
		return noOfElements;
	}

	/**
	 * This method sets the value to the private field noOfElements.
	 * 
	 * @param noOfElements
	 *            the number of elements. For example, boolean lattice set 2 as
	 *            the number of elements.
	 */
	public void setNoOfElements(int noOfElements) {
		this.noOfElements = noOfElements;
	}

	/**
	 * This method returns the bottom element of the lattice.By Default, 0 is the
	 * value element of any lattice.
	 * 
	 * @return return bottom element.
	 */
	public abstract int GetZero();

	/**
	 * This method returns the top element of lattice. By Default, 1 is the value
	 * element of any lattice.
	 * 
	 * @return return top element.
	 */
	public abstract int GetOne();

	/**
	 * This method determines the meet or infimum of two elements. In other
	 * words, it returns the greatest element from the set of elements where
	 * each element of that set is smaller than both input variables.
	 * 
	 * @param elem1
	 *            the first element of meet operation.
	 * @param elem2
	 *            the second element of meet operation.
	 * @return the greatest lower bound of elem1 and elem2.
	 */
	public abstract int Meet(int elem1, int elem2);

	/**
	 * This method determines the join or supremum of two elements.In other
	 * words, it returns the least element from the set of elements where
	 * each element of that set is greater than both input variables.
	 * 
	 * @param elem1
	 *            the first element of join operation.
	 * @param elem2
	 *            the second element of join operation.
	 * @return the least greater bound of elem1 and elem2.
	 */
	public abstract int Join(int elem1, int elem2);

	/**
	 * This method determines the greatest element (x) such that the meet of x
	 * and elem1 is less or equal to elem2. Here, x can be any element from lattice.
	 * 
	 * @param elem1
	 *            the first element of implication operation.
	 * @param elem2
	 *            the second element of implication operation.
	 * @return implication of elem1 and elem2.
	 */
	public abstract int Implication(int elem1, int elem2);

}
