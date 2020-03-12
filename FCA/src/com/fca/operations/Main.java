package com.fca.operations;

import com.fca.lattice.BoolLattice;
import com.fca.lattice.ThreeElementLattice;
import com.fca.utility.Utility;

import java.util.ArrayList;


public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		/*
		 * int[][] geometry = { { 1, 0, 1, 0 }, { 1, 0, 0, 0 }, { 0, 1, 0, 0 }, { 0, 1,
		 * 0, 1 }, { 0, 1, 1, 1 } };
		 * 
		 * int[][] girlWomen = { { 1, 1, 0, 0 }, { 1, 0, 1, 0 }, { 0, 1, 0, 1 }, { 0, 0,
		 * 1, 1 } };
		 * 
		 * int[][] numberMat = { { 1, 1, 0 }, { 1, 1, 0 } };
		 * 
		 * int[][] f1c1 = { { 1, 0, 0, 1, 0, 1, 1 }, { 0, 1, 0, 1, 1, 0, 1 }, { 0, 0, 1,
		 * 0, 1, 1, 1 } };
		 * 
		 * int[][] num = { { 0, 0, 1, 0, 1 }, { 0, 1, 0, 1, 0 }, { 0, 0, 1, 1, 0 }, { 1,
		 * 1, 0, 0, 1 }, { 0, 0, 1, 1, 0 }, { 1, 1, 0, 0, 0 }, { 0, 0, 1, 1, 0 }, { 1,
		 * 1, 0, 0, 0 }, { 1, 0, 1, 0, 1 }, { 1, 1, 0, 0, 0 } };
		 * 
		 * int[][] x = { { 0, 0, 0, 0, 1, 1, 1, 1 }, { 0, 0, 1, 1, 0, 0, 1, 1 }, { 0, 1,
		 * 0, 1, 0, 1, 0, 1 } };
		 * 
		 * int[][] y = { { 1, 0, 0, 1, 0, 1, 1 }, { 0, 1, 0, 1, 1, 0, 1 }, { 0, 0, 1, 0,
		 * 1, 1, 1 } };
		 * 
		 * int[][] test = { { 1, 0, 1 }, { 1, 1, 1 } };
		 * 
		 * Relation gRw = new Relation(4, 4, new BoolLattice()); Relation fRc = new
		 * Relation(3, 7, new BoolLattice()); Relation nR = new Relation(10, 5, new
		 * BoolLattice()); Relation testR = new Relation(2, 3, new BoolLattice());
		 * Relation xx = new Relation(3, 8, new BoolLattice()); Relation yy = new
		 * Relation(3, 7, new BoolLattice()); Relation numR = new Relation(2, 3, new
		 * BoolLattice()); Relation geo = new Relation(5, 4, new BoolLattice());
		 * 
		 * gRw.setMatrix(girlWomen); fRc.setMatrix(f1c1); nR.setMatrix(num);
		 * testR.setMatrix(test); xx.setMatrix(x); yy.setMatrix(y);
		 * numR.setMatrix(numberMat); geo.setMatrix(geometry);
		 */

		/*
		 * ArrayList<String> attribute = new ArrayList<String>(); attribute.add("Red");
		 * attribute.add("Green"); attribute.add("Yellow");
		 * 
		 * ArrayList<String> object = new ArrayList<String>(); object.add("Apple");
		 * object.add("Mango"); object.add("Banana");
		 * 
		 * int[][] fruitMatrix = { { 1, 1,0}, { 1, 1,1 },{0,1,1}}; Relation
		 * fruitRelation = new Relation(3, 3, new BoolLattice());
		 * fruitRelation.setMatrix(fruitMatrix); fruitRelation.setSource(object);
		 * fruitRelation.setTarget(attribute);
		 * 
		 * 
		 * fruitRelation.composition(Relation.Kappa(8,8).transpose());
		 */

		 int[][] p= {{1,0,0,1,1},{0,0,1,1,0},{1,0,0,1,1},{0,0,1,0,1}};
		
		  ArrayList<String> attribute = new ArrayList<String>(); attribute.add("Red");
		  attribute.add("Green"); attribute.add("Yellow");attribute.add("Sweet");attribute.add("Sour");
		  
		  ArrayList<String> object = new ArrayList<String>(); object.add("Apple");
		  object.add("Banana"); object.add("Cherry"); object.add("Lemon"); 
		  
		 Relation pr = new Relation(object, attribute, new BoolLattice());
		 pr.setMatrix(p);

		 
//		Utility.PrintArray(pr.generateImplicationUsingLemma1().getMatrix());

		System.out.println(Utility.isEqual(pr.generateImplicationUsingLemma1(), pr.generateImplicationsUsingBase()));
	}

}
