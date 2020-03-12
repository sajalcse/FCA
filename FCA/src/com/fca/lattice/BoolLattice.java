package com.fca.lattice;

import com.fca.lattice.Lattice;

/**
 * 
 * @author sajal
 * 
 */
public class BoolLattice extends Lattice {
	int[][] implication = {{1,1},{0,1}};
	int[][] meet = {{0,0},{0,1}};
	int[][] join = {{0,1},{1,1}};
	/**
	 * This is the constructor. Constructor assign the the total number of
	 * elements to represent this lattice.
	 */
	public BoolLattice() {
		setNoOfElements(2);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.relation.lattice.Lattice#GetZero()
	 */
	@Override
	public int GetZero() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.relation.lattice.Lattice#GetOne()
	 */
	@Override
	public int GetOne() {
		// TODO Auto-generated method stub
		return 1;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.relation.lattice.Lattice#Meet(int, int)
	 */
	@Override
	public int Meet(int elem1, int elem2) {
		return elem1 * elem2;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.relation.lattice.Lattice#Join(int, int)
	 */
	@Override
	public int Join(int elem1, int elem2) {
		// TODO Auto-generated method stub
		return join[elem1][elem2];
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.relation.lattice.Lattice#Implication(int, int)
	 */
	@Override
	public int Implication(int elem1, int elem2) {

		// TODO Auto-generated method stub
		return implication[elem1][elem2];
	}

	

}
