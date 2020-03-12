package com.fca.utility;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import com.fca.operations.Relation;

/**
 * 
 * @author sajal
 *
 */
public class Utility {
	/**
	 * This method prints a two dimensional array in the form of row and column
	 * @param data is the input array to print.
	 */
	public static void PrintArray(Relation r, String title) {

		System.out.println("--------"+title+"-------");
		int[][] data = r.getMatrix();
		int row = data.length;
		int column = data[0].length;
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < column; j++) {
				System.out.print(data[i][j] + " ");
			}
			System.out.print("\n");
		}
		System.out.println("--------End "+title+"--------");
	}
	public static void PrintArray(int[][] data) {
		
		int row = data.length;
		int column = data[0].length;
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < column; j++) {
				System.out.print(data[i][j] + " ");
			}
			System.out.print("\n");
		}
	}

	public static void Write(int[][] data) {
		PrintWriter writer;
		try {
			writer = new PrintWriter("output.txt", "UTF-8");
			
			
			int row = data.length;
			int column = data[0].length;
			for (int i = 0; i < row; i++) {
				for (int j = 0; j < column; j++) {
					writer.print(String.valueOf(data[i][j])+" ");
				}
				writer.println();
			}
			writer.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static int getNumberOfConcepts(Relation r, int n) {

		int count = 0;
		for (int i = 0; i < r.getRow(); i++)
			for (int j = 0; j < r.getColumn(); j++)
				if (r.getMatrix()[i][j] == n)
					count++;
		System.out.println(count);
		return count;
	}
	
	public static boolean isEqual(Relation rel1, Relation rel2) {
		boolean result = true;
		if ((rel1.getRow() == rel2.getRow())
				&& (rel1.getColumn() == rel2.getColumn())) {
			for (int i = 0; i < rel1.getRow(); i++)
				for (int j = 0; j < rel1.getColumn(); j++)
					if (rel1.getMatrix()[i][j] != rel2.getMatrix()[i][j]) {
						result = false;
						break;
					}

		} else {
			System.err
					.println("Relations are incompatiable for equality check");
		}
		System.out.println(result);
		return result;
	}
}


