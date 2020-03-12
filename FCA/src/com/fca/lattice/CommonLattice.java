/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fca.lattice;

/**
 *
 * @author susmi
 */
public class CommonLattice extends Lattice{

    /**
     * @return the implicationArray
     */
    public static int[][] getImplicationArray() {
        return implicationArray;
    }

    /**
     * @param aImplicationArray the implicationArray to set
     */
    public static void setImplicationArray(int[][] aImplicationArray) {
        implicationArray = aImplicationArray;
    }

    /**
     * @return the joinArray
     */
    public static int[][] getJoinArray() {
        return joinArray;
    }

    /**
     * @param aJoinArray the joinArray to set
     */
    public static void setJoinArray(int[][] aJoinArray) {
        joinArray = aJoinArray;
    }

    /**
     * @return the meetArray
     */
    public static int[][] getMeetArray() {
        return meetArray;
    }

    /**
     * @param aMeetArray the meetArray to set
     */
    public static void setMeetArray(int[][] aMeetArray) {
        meetArray = aMeetArray;
    }

    private static int[][] meetArray = null;

	/**
	 * This static two dimensional array contains the result of a join operation
	 * between two elements.Since it is a three element lattice, we need to
	 * declare a 3 by 3 dimension array.
	 */
	private static int[][] joinArray = null;

	/**
	 * This static two dimensional array contains the result of an implication
	 * operation between two elements.Since it is a three element lattice, we
	 * need to declare a 3 by 3 dimension array.
	 */
	private static int[][] implicationArray = null;
        
     int noOfElements=0;
    public CommonLattice()
    {
        setNoOfElements(noOfElements);
    }
    public CommonLattice(int elements)
    {
        setNoOfElements(elements);
    }
    @Override
    public int GetZero() {
       return 0;
    }

    @Override
    public int GetOne() {
      return 1;
              }

    @Override
    public int Meet(int elem1, int elem2) {
               return getMeetArray()[elem1][elem2];    }

    @Override
    public int Join(int elem1, int elem2) {
        return getJoinArray()[elem1][elem2];    }

    @Override
    public int Implication(int elem1, int elem2) {
        return getImplicationArray()[elem1][elem2];
    }
    
}
