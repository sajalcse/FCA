package com.fca.operations;

import java.util.ArrayList;
import com.fca.lattice.Lattice;

import com.fca.utility.Utility;

public class Relation implements IRelationOperation, IFormalConceptOperation {

    /**
     * This is the number of row in the relation.
     */
    private int row;
    /**
     * This is the number of column in the relation.
     */
    private int column;
    /**
     * This two dimensional array represents the relation.
     */
    private int matrix[][];
    /**
     * This is the default constructor.
     */
    private ArrayList<String> target;
    private ArrayList<String> source;

    private static Lattice lattice;
    private static ArrayList<String> epsilonTarget;

    /**
     * This is the constructor which takes initial parameter to define the
     * relation.
     *
     * @param row the number of row in relation
     * @param column the number of column in relation
     * @param lattice the type of lattice.
     */
    public Relation(int row, int column, Lattice lattice) {
        this.row = row;
        this.column = column;
        Relation.lattice = lattice;
        setMatrix(new int[row][column]);
    }

    public Relation(ArrayList<String> source, ArrayList<String> target, Lattice lattice) {
        this.source = source;
        this.target = target;
        Relation.lattice = lattice;
        setRow(source.size());
        setColumn(target.size());
        setMatrix(new int[getRow()][getColumn()]);
    }

    public Relation() {
        // TODO Auto-generated constructor stub
    }

    /**
     * This method accesses the private field in row.
     *
     * @return the row number.
     */
    public int getRow() {
        return row;
    }

    /**
     * This method sets the row in the relation.
     *
     * @param row the row number.
     */
    public void setRow(int row) {
        this.row = row;
    }

    /**
     * This method accesses the private field in column.
     *
     * @return the column number.
     */
    public int getColumn() {
        return column;
    }

    /**
     * This method sets the column in the relation.
     *
     * @param column the column number.
     */
    public void setColumn(int column) {
        this.column = column;
    }

    /**
     * This methods accesses the private field in matrix.
     *
     * @return the attached matrix with a relation.
     */
    public int[][] getMatrix() {
        return matrix;
    }

    /**
     * This method sets the matrix in the relation.
     *
     * @param matrix the matrix to attach with the relation.
     */
    public void setMatrix(int matrix[][]) {
        this.matrix = matrix;
    }

    public ArrayList<String> getTarget() {
        return target;
    }

    public void setTarget(ArrayList<String> attribute) {
        this.target = attribute;
    }

    public ArrayList<String> getSource() {
        return source;
    }

    public void setSource(ArrayList<String> object) {
        this.source = object;
    }

    /**
     * This method determines the union of two relations with same dimension.
     * Every element of one relation is compared with the corresponding element
     * from the other relation and if one of them is true then it writes true in
     * the output relation.
     *
     * @param relation is one of the input relation for union method. The other
     * input relation accessed using this operator in java.
     * @return union of two relation.
     */
    @Override
    public Relation union(Relation relation) {
        if ((this.getRow() != relation.getRow()) && (this.getColumn() != relation.getColumn())) {
            System.err.println("Union Operation is not possible because number of column is different!");
            return null;
        } else {
            Relation resultRelation = new Relation(relation.getRow(), relation.getColumn(), getLattice());
            int resultMatrix[][] = null;
            resultMatrix = resultRelation.getMatrix();
            for (int i = 0; i < row; i++) {
                for (int j = 0; j < column; j++) {
                    resultMatrix[i][j] = getLattice().Join(this.getMatrix()[i][j], relation.getMatrix()[i][j]);
                }
            }
            resultRelation.setSource(relation.getSource());
            resultRelation.setTarget(relation.getTarget());
            resultRelation.setMatrix(resultMatrix);
            return resultRelation;
        }

    }

    /**
     * This method determines the intersection of two relation with same
     * dimension. Every element of one relation is compared with the
     * corresponding element from the other relation and if both of them is true
     * then it writes true in the output relation.
     *
     * @param relation is one of the input relation for union method. The other
     * input relation accessed using this operator in java.
     * @return intersection of two relation
     */
    public Relation intersection(Relation relation) {
        if ((this.getRow() != relation.getRow()) && (this.getColumn() != relation.getColumn())) {
            System.err.println("Intersection Operation is not possible because number of column is different!");
            return null;
        } else {
            Relation resultRelation = new Relation(relation.getRow(), relation.getColumn(), getLattice());
            int resultMatrix[][] = null;
            resultMatrix = resultRelation.getMatrix();
            for (int i = 0; i < row; i++) {
                for (int j = 0; j < column; j++) {
                    resultMatrix[i][j] = getLattice().Meet(this.getMatrix()[i][j], relation.getMatrix()[i][j]);
                }
            }
            resultRelation.setMatrix(resultMatrix);
            resultRelation.setSource(relation.getSource());
            resultRelation.setTarget(relation.getTarget());
            return resultRelation;
        }
    }

    public Relation implication(Relation relation) {
        if ((this.getRow() != relation.getRow()) && (this.getColumn() != relation.getColumn())) {
            System.err.println("Intersection Operation is not possible because number of column is different!");
            return null;
        } else {
            Relation resultRelation = new Relation(relation.getRow(), relation.getColumn(), getLattice());
            int resultMatrix[][] = null;
            resultMatrix = resultRelation.getMatrix();
            for (int i = 0; i < row; i++) {
                for (int j = 0; j < column; j++) {
                    resultMatrix[i][j] = getLattice().Implication(this.getMatrix()[i][j], relation.getMatrix()[i][j]);
                }
            }
            resultRelation.setMatrix(resultMatrix);
            resultRelation.setSource(relation.getSource());
            resultRelation.setTarget(relation.getTarget());
            return resultRelation;
        }
    }

    /**
     * This method calculates the relative multiplication of two relation. The
     * (x,z) element would be true in the resulting relation if and only if
     * there exists one element y such that (x,y) is true in the first relation
     * and (y,z) is true in the other relation.
     *
     * @param relation one of the input relation for union method. The other
     * input relation accessed using this operator in java.
     * @return the composition of two relation.
     */
    public Relation composition(Relation relation) {
        int resultMatrix[][] = null;

        System.out.println("Composition:---" + getLattice());
        if (this.getColumn() != relation.getRow()) {
            System.err.println("First Relation column should be equal to the second relation row!");
            return null;
        } else {
            Relation resultRelation = new Relation(this.getRow(), relation.getColumn(), getLattice());
            resultMatrix = resultRelation.getMatrix();

            for (int v = 0; v < this.getColumn(); v++) {
                for (int i = 0; i < this.getRow(); i++) {
                    for (int j = 0; j < relation.getColumn(); j++) {
                        resultMatrix[i][j] = getLattice().Join(resultMatrix[i][j],
                                getLattice().Meet(this.getMatrix()[i][v], relation.getMatrix()[v][j]));

                    }
                }

            }
            resultRelation.setMatrix(resultMatrix);
            resultRelation.setSource(this.getSource());
            resultRelation.setTarget(relation.getTarget());
            return resultRelation;
        }
    }

    /**
     * This method calculates the left residual of two relation R and S (R\S)
     * which is the greatest of all relation X with (R.X) is the subset of S.
     *
     * @param relation one of the input relation for union method. The other
     * input relation accessed using this operator in java.
     * @return the left residue of two relation.
     */
    public Relation rightResidue(Relation relation) {

        Relation resultRelation = new Relation(this.getColumn(), relation.getColumn(), getLattice());
        int row = this.getRow();
        for (int i = 0; i < resultRelation.getRow(); i++) {
            for (int j = 0; j < resultRelation.getColumn(); j++) {
                resultRelation.getMatrix()[i][j] = getLattice().GetOne();
                for (int n = 0; n < row; n++) {
                    resultRelation.getMatrix()[i][j] = getLattice().Meet(resultRelation.getMatrix()[i][j],
                            getLattice().Implication(this.getMatrix()[n][i], relation.getMatrix()[n][j]));
                }
            }
        }
        resultRelation.setSource(this.getTarget());
        resultRelation.setTarget(relation.getTarget());
        return resultRelation;
    }

    /**
     * This method calculates the right residual of two relation R and S (R/S)
     * which is the greatest of all relation X with (X.R) is the subset of S.
     *
     * @param relation one of the input relation for union method. The other
     * input relation accessed using this operator in java.
     * @return right residue of two relation.
     */
    public Relation leftResidue(Relation relation) {
        Relation resultRelation = new Relation(this.getRow(), relation.getRow(), getLattice());
        int column = relation.getColumn();
        for (int i = 0; i < resultRelation.getRow(); i++) {
            for (int j = 0; j < resultRelation.getColumn(); j++) {
                resultRelation.getMatrix()[i][j] = getLattice().GetOne();
                for (int n = 0; n < column; n++) {
                    resultRelation.getMatrix()[i][j] = getLattice().Meet(resultRelation.getMatrix()[i][j],
                            getLattice().Implication(relation.getMatrix()[j][n], this.getMatrix()[i][n]));
                }
            }
        }
        resultRelation.setSource(this.getSource());
        resultRelation.setTarget(relation.getSource());
        return resultRelation;
    }

    /**
     * This method creates an entry for each subset. For example, there are a
     * total of 8 subsets for 3 elements.Then this method creates a relation
     * with dimension 8 by 3 where row represents each of the subset and column
     * represents each object. Empty subset contain 0 for every column while
     * subset with one element contain 1 in the column which represents the
     * element in the subset.
     *
     * @param element no of elements in the set.
     * @return the epsilon relation for the input.
     */
    public static Relation epsilon(int element) {
        ArrayList<String> list = new ArrayList<>();
        int row = element;
        int column = (int) Math.pow(getLattice().getNoOfElements(), row);
        Relation resultRelation = new Relation(row, column, getLattice());

        for (int c = 0; c < column; c++) {
            String singlePair = "";

            int n = c;
            int r = row - 1;
            while (n > 0) {
                int value = n % getLattice().getNoOfElements();
                singlePair = singlePair + "," + "(" + r + "," + value + ")";
                resultRelation.getMatrix()[r][c] = value;
                n = n / getLattice().getNoOfElements();
                r--;
            }
            list.add(singlePair);

        }
        System.out.println("---Epsilon Start----");
        Utility.PrintArray(resultRelation.getMatrix());

        for (String data : list) {
            System.out.println(data);
        }
        return resultRelation;
    }

    public static Relation epsilon(ArrayList<String> attribute) {
        ArrayList<String> list = new ArrayList<>();
        int row = attribute.size();
        int column = (int) Math.pow(getLattice().getNoOfElements(), row);
        epsilonTarget = new ArrayList<String>();
        Relation resultRelation = new Relation(row, column, getLattice());

        for (int c = 0; c < column; c++) {
            String singlePair = "";
            int n = c;
            int r = row - 1;
            while (n > 0) {
                int value = n % getLattice().getNoOfElements();
                if (getLattice().getNoOfElements() == 2) {
                    if (value > 0) {
                        singlePair = attribute.get(r) + "," + singlePair;
                    }
                } else {
                    if (value > 0) {
                        singlePair = singlePair + "," + value + "/" + attribute.get(r);
                    }
                }
                resultRelation.getMatrix()[r][c] = value;
                n = n / getLattice().getNoOfElements();
                r--;
            }
            list.add(singlePair);

        }

        Utility.PrintArray(resultRelation.getMatrix());
        for (String data : list) {
            if (!data.equals("")) {

                System.out.println("{" + data.substring(1, data.length()) + "}");
                if (getLattice().getNoOfElements() > 2) {
                    epsilonTarget.add("{" + data.substring(1, data.length()) + "}");
                } else {
                    epsilonTarget.add("{" + data.substring(0, data.length() - 1) + "}");
                }

            } else {
                System.out.println("{}");
                epsilonTarget.add("{}");
            }
        }
        resultRelation.setSource(attribute);
        resultRelation.setTarget(epsilonTarget);
        return resultRelation;

    }

    public static ArrayList<String> subSetList(ArrayList<String> attribute) {
        ArrayList<String> list = new ArrayList<>();
        int row = attribute.size();
        int column = (int) Math.pow(getLattice().getNoOfElements(), row);
        for (int c = 0; c < column; c++) {
            String singlePair = "";
            int n = c;
            int r = row - 1;
            while (n > 0) {
                int value = n % getLattice().getNoOfElements();

                if (getLattice().getNoOfElements() == 2) {

                    if (value > 0) {
                        singlePair = attribute.get(r) + "," + singlePair;
                    }
                } else {
                    if (value > 0) {
                        singlePair = singlePair + "," + value + "/" + attribute.get(r);
                    }
                }
                n = n / getLattice().getNoOfElements();
                r--;
            }
            list.add(singlePair);
        }
        ArrayList<String> subset = new ArrayList<String>();
        for (String data : list) {
            if (!data.equals("")) {
                if (getLattice().getNoOfElements() > 2) {
                    subset.add("{" + data.substring(1, data.length()) + "}");
                } else {
                    subset.add("{" + data.substring(0, data.length() - 1) + "}");
                }
                System.out.println("{" + data.substring(1, data.length()) + "}");
            } else {
                subset.add("{}");
            }
        }
        return subset;
    }

    public static ArrayList<String> generateObjectAttributePair(ArrayList<String> obj, ArrayList<String> att) {
        ArrayList<String> objectSubset = subSetList(obj);
        ArrayList<String> attributeSubset = subSetList(att);
        for (String singleObj : objectSubset) {
            for (String singleAtt : attributeSubset) {
                System.out.println("( " + singleObj + "," + singleAtt + " )");
            }
        }
        return null;
    }

    /**
     * This method calculates the intersection of left residue and right residue
     * to two relation R and S. Let define R:X-Y and S:X-Z. Then the symmetric
     * quotient relates an element y from Y to an element z from Z when y and z
     * have the same set of inverse image with respect to R and S respectively.
     *
     * @param relation one of the input relation for union method. The other
     * input relation accessed using this operator in java.
     * @return symmetric quotient of two relation.
     */
    public Relation symetricQuotient(Relation relation) {
        Relation right = this.rightResidue(relation);
        Relation left = this.transpose().leftResidue(relation.transpose());
        return right.intersection(left);
    }

    /**
     * This static method returns an identity relation i.e. a square relation
     * with ones on the main diagonal and zero elsewhere.
     *
     * @param size the dimension of matrix. This size is set as the row and
     * column of the identity relation.
     * @return the identity relation with specified dimension.
     */
    public static Relation identity(int size) {
        int dimension = size;
        int[][] matrix = new int[dimension][dimension];
        for (int i = 0; i < dimension; i++) {
            matrix[i][i] = getLattice().GetOne();
        }
        Relation resultRelation = new Relation(dimension, dimension, getLattice());
        resultRelation.setMatrix(matrix);
        return resultRelation;
    }

    public static Relation identity(ArrayList<String> source) {
        int dimension = source.size();
        int[][] matrix = new int[dimension][dimension];
        for (int i = 0; i < dimension; i++) {
            matrix[i][i] = getLattice().GetOne();
        }
        Relation resultRelation = new Relation(dimension, dimension, getLattice());
        resultRelation.setSource(source);
        resultRelation.setTarget(source);
        resultRelation.setMatrix(matrix);
        return resultRelation;
    }

    /**
     * This method flips the relation, that is it switches the row and column by
     * producing another relation.
     *
     * @return it returns transposed relation
     */
    public Relation transpose() {
        Relation r2 = new Relation(this.getColumn(), this.getRow(), getLattice());
        for (int i = 0; i < this.getRow(); i++) {
            for (int j = 0; j < this.getColumn(); j++) {
                r2.getMatrix()[j][i] = this.getMatrix()[i][j];
            }
        }
        r2.setSource(this.getTarget());
        r2.setTarget(this.getSource());
        return r2;
    }

    /**
     * This method create a relation with 1 and 0. The relation dimension
     * depends on the input parameters. For example, if m and n two parameters
     * in the function then relation consist of (m) rows and (m+n) columns. The
     * result relation contains 1 for those indexes where row and column
     * represents same element from m and 0 elsewhere.
     *
     * @param m the first parameter.
     * @param n the second parameter.
     * @return the iota relation consist of 1 and 0.
     */
    public static Relation Iota(int m, int n) {
        int row = m;
        int column = m + n;
        int[][] matrix = new int[row][column];
        for (int r = 0; r < row; r++) {
            for (int c = 0; c < column; c++) {
                if (r == c) {
                    matrix[r][c] = 1;
                }
            }
        }
        Relation resultRelation = new Relation();
        resultRelation.setMatrix(matrix);
        return resultRelation;
    }

    /**
     * This method create a relation with 1 and 0. The relation dimension
     * depends on the input parameters. For example, if m and n two parameters
     * in the function then relation consist of (n) rows and (m+n) columns. The
     * result relation contains 1 for those indexes where row and column
     * represents same element from n and 0 elsewhere.
     *
     * @param m the first parameter.
     * @param n the second parameter.
     * @return the kappa relation consist of 1 and 0.
     */
    public static Relation Kappa(int m, int n) {
        int row = n;
        int column = m + n;
        int[][] matrix = new int[row][column];
        for (int r = 0; r < row; r++) {
            for (int c = 0; c < column; c++) {
                if (c == r + n - 1) {
                    matrix[r][c] = 1;
                }
            }
        }
        Relation resultRelation = new Relation();
        resultRelation.setMatrix(matrix);
        return resultRelation;
    }

    /**
     * This method create a relation with 1 and 0. The relation dimension
     * depends on the input parameters. For example, if m and n two parameters
     * in the function then relation consist of (m*n) rows and (m) columns. The
     * result relation contains 1 for those indexes which represents the row
     * element and 0 elsewhere.
     *
     * @param m the first parameter.
     * @param n the second parameter.
     * @return the pi relation consist of 1 and 0.
     */
    public static Relation pi(int m, int n) {
        int row = m * n;
        int column = m;
        int[][] matrix = new int[row][column];

        int column_count = 0;
        int row_count = -1;
        for (int r = 0; r < row; r++) {
            row_count++;
            for (int c = 0; c < column; c++) {
                if (c == column_count) {
                    matrix[r][column_count] = 1;
                }
            }
            if (row_count == n - 1) {
                column_count++;
                row_count = -1;
            }
        }
        Relation resultRelation = new Relation(row, column, getLattice());
        resultRelation.setMatrix(matrix);
        Utility.PrintArray(matrix);
        return resultRelation;
    }

    public static Relation pi(ArrayList<String> source, ArrayList<String> target) {
        int m = (int) Math.pow(getLattice().getNoOfElements(), source.size());
        int n = (int) Math.pow(getLattice().getNoOfElements(), target.size());

        int row = m * n;
        int column = m;

        ArrayList<String> piSource = new ArrayList<String>();

        for (String s : subSetList(source)) {
            for (String t : subSetList(target)) {
                piSource.add("(" + s + "," + t + ")");
            }
        }

        int[][] matrix = new int[row][column];

        int column_count = 0;
        int row_count = -1;
        for (int r = 0; r < row; r++) {
            row_count++;
            for (int c = 0; c < column; c++) {
                if (c == column_count) {
                    matrix[r][column_count] = 1;
                }
            }
            if (row_count == n - 1) {
                column_count++;
                row_count = -1;
            }
        }
        Relation resultRelation = new Relation(piSource, subSetList(source), getLattice());
        resultRelation.setMatrix(matrix);
        Utility.PrintArray(matrix);
        return resultRelation;
    }

    /**
     * This method create a relation with 1 and 0. The relation dimension
     * depends on the input parameters. For example, if m and n two parameters
     * in the function then relation consist of (m*n) rows and (n) columns. The
     * result relation contains 1 for those indexes which represents the row
     * element and 0 elsewhere.
     *
     * @param m the first parameter.
     * @param n the second parameter.
     * @return the rho relation consist of 1 and 0.
     */
    public static Relation rho(ArrayList<String> source, ArrayList<String> target) {
        int m = (int) Math.pow(getLattice().getNoOfElements(), source.size());
        int n = (int) Math.pow(getLattice().getNoOfElements(), target.size());

        int row = m * n;
        int column = n;

        ArrayList<String> rhoSource = new ArrayList<String>();

        for (String s : subSetList(source)) {
            for (String t : subSetList(target)) {
                rhoSource.add("(" + s + "," + t + ")");
            }
        }

        int[][] matrix = new int[row][column];
        int column_count = 0;
        int row_count = -1;
        for (int r = 0; r < row; r++) {
            row_count++;
            for (int c = 0; c < column; c++) {
                if (c == column_count) {
                    matrix[r][c] = 1;
                }
            }
            if (row_count == n - 1) {
                row_count = -1;
                column_count = 0;
            } else {
                column_count++;
            }

        }
        Relation resultRelation = new Relation(rhoSource, subSetList(target), getLattice());
        resultRelation.setMatrix(matrix);
        Utility.PrintArray(resultRelation.getMatrix());
        return resultRelation;
    }

    public static Relation rho(int m, int n) {
        int row = m * n;
        int column = n;
        int[][] matrix = new int[row][column];

        int column_count = 0;
        int row_count = -1;
        for (int r = 0; r < row; r++) {
            row_count++;
            for (int c = 0; c < column; c++) {
                if (c == column_count) {
                    matrix[r][c] = 1;
                }
            }
            if (row_count == n - 1) {
                row_count = -1;
                column_count = 0;
            } else {
                column_count++;
            }

        }
        Relation resultRelation = new Relation(row, column, getLattice());
        resultRelation.setMatrix(matrix);
        Utility.PrintArray(resultRelation.getMatrix());
        return resultRelation;
    }

    /**
     * This function takes relation as a input. If the relation entry getter
     * than 0 then this makes the entry 1 otherwise keep the same.
     *
     * @return modified matrix with 1 and 0 only
     */
    public Relation up() {
        Relation resultRelation = new Relation(this.getRow(), this.getColumn(), getLattice());
        for (int i = 0; i < this.getRow(); i++) {
            for (int j = 0; j < this.getColumn(); j++) {
                resultRelation.getMatrix()[i][j] = this.getMatrix()[i][j] != 0 ? 1 : 0;
            }
        }
        Utility.PrintArray(resultRelation.getMatrix());
        return resultRelation;
    }

    /**
     * This function takes relation as a input. If the relation entry equal to 1
     * then this makes the entry 1 otherwise keep the same.
     *
     * @return modified matrix with 1 and 0 only
     */
    public Relation down() {
        Relation resultRelation = new Relation(this.getRow(), this.getColumn(), getLattice());
        for (int i = 0; i < this.getRow(); i++) {
            for (int j = 0; j < this.getColumn(); j++) {
                resultRelation.getMatrix()[i][j] = this.getMatrix()[i][j] != 1 ? 0 : 1;
            }
        }
        Utility.PrintArray(resultRelation.getMatrix());
        return resultRelation;
    }

    public Relation Down() {
        Relation resultRelation = new Relation(this.getSource(), this.getTarget(), getLattice());
        for (int i = 0; i < this.getRow(); i++) {
            for (int j = 0; j < this.getColumn(); j++) {
                resultRelation.getMatrix()[i][j] = this.getMatrix()[i][j] != 1 ? 0 : 1;
            }
        }
        Utility.PrintArray(resultRelation.getMatrix());
        return resultRelation;
    }

    public Relation getEquivalence() {
        Relation r = this.removeZero();
        return r;
    }

    private Relation removeZero() {
        // TODO Auto-generated method stub
        boolean isZero = true;
        ArrayList<Integer> nonZeroRow = new ArrayList<>();
        for (int i = 0; i < this.getRow(); i++) {
            for (int j = 0; j < this.getColumn(); j++) {
                if (this.getMatrix()[i][j] > 0) {
                    isZero = false;
                    break;
                }
            }
            if (!isZero) {
                nonZeroRow.add(i);
                isZero = true;
            }
        }

        int[][] newMatrix = new int[nonZeroRow.size()][this.getColumn()];

        ArrayList<String> modifiedSource = new ArrayList<>();
        for (int i = 0; i < nonZeroRow.size(); i++) {
            int row = nonZeroRow.get(i);
            modifiedSource.add(this.getSource().get(row));
            for (int col = 0; col < this.getColumn(); col++) {
                newMatrix[i][col] = this.getMatrix()[row][col];
            }
        }

        Relation r = new Relation(nonZeroRow.size(), this.getColumn(), getLattice());
        r.setSource(modifiedSource);
        r.setTarget(this.getTarget());
        r.setMatrix(newMatrix);
        return r;
    }

    public Relation Not() {

        int[][] matrix = new int[this.getRow()][this.getColumn()];
        Relation resultRelation = new Relation(this.getRow(), this.getColumn(), getLattice());
        for (int i = 0; i < this.getRow(); i++) {
            for (int j = 0; j < this.getColumn(); j++) {
                matrix[i][j] = this.getMatrix()[i][j] == 1 ? 0 : 1;
            }
        }
        resultRelation.setMatrix(matrix);
        System.out.println("---Not Start----");
        Utility.PrintArray(resultRelation.getMatrix());
        return resultRelation;
    }

    // ------------------------------------***************************-----------------------------------------
    // Relational Operations End Here
    // ------------------------------------***************************-----------------------------------------
    public Relation generateFullConcept() {
        int m = (int) Math.pow(getLattice().getNoOfElements(), this.getRow());

        int n = (int) Math.pow(getLattice().getNoOfElements(), this.getColumn());

        Relation s = pi(this.getSource(), this.getTarget()).composition(this.getObjectPrime()).composition(Relation.rho(this.getSource(), this.getTarget()).transpose())
                .intersection(
                        rho(this.getSource(), this.getTarget()).composition(this.getAttributePrime()).composition(Relation.pi(this.getSource(), this.getTarget()).transpose()));
        return s.intersection(identity(s.getSource()));

    }

    public Relation generateObjectConcept() {

        Relation r = this.getObjectPrime().composition(this.getAttributePrime());
        return r.intersection(identity(r.getSource()));
    }

    public Relation generateAttributeConcept() {
        Relation r = this.getAttributePrime().composition(this.getObjectPrime());
        return r.intersection(identity(r.getSource()));

    }

    public static Relation e(int m) {
        return epsilon(m).rightResidue(epsilon(m));
    }

    public static Relation L(int m, int n) {
        int[][] l = new int[m][n];
        Relation lr = new Relation(m, n, getLattice());
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                l[i][j] = 1;
            }
        }
        lr.setMatrix(l);
        return lr;
    }

    public Relation generateCanonicalBase() {

        Relation cm = this.generateAttributeConcept().getEquivalence();
        Relation e = Relation.e(this.getColumn());
        int identitySize = (int) Math.pow(getLattice().getNoOfElements(), this.getColumn());
        Relation i = Relation.identity(identitySize);

        Relation first_part = (e.intersection(i.Not()))
                .rightResidue(cm.transpose().composition(Relation.L(cm.transpose().getColumn(), 1)));
        Relation second_part = (cm.transpose().composition(Relation.L(cm.transpose().getColumn(), 1))).Not();
        Relation a = first_part.intersection(second_part);
        
        Utility.PrintArray(a, "First a");

        Relation nexta = null;
        while (true) {
            nexta = generateNext(this, a);
            Utility.PrintArray(nexta, "Next a");
            if (Utility.isEqual(a, nexta)) {
                break;
            } else {
                a = nexta;
            }
        }

        Relation base = (nexta.composition(Relation.L(1, identitySize)))
                .intersection(this.getAttributePrime().composition(this.getObjectPrime()));

        Utility.PrintArray(base, "Base");
        return base;
    }

    public Relation generateImplicationUsingLemma() {
        Relation cm = this.generateAttributeConcept().getEquivalence();
        Relation e = e(this.getColumn());
        return cm.composition(e.transpose()).rightResidue(cm.composition(e.transpose()));
    }
    public Relation generateImplicationUsingLemma1() {
    	Relation s = this.getAttributePrime().composition(this.getObjectPrime());
        return s.intersection(Relation.identity(s.getRow())).composition(Relation.e(this.getColumn()).transpose()).rightResidue(Relation.e(this.getColumn()).transpose());

    	//return this.getAttributePrime().composition(this.getObjectPrime()).composition(Relation.e(this.getColumn()).transpose());
    	}


    public Relation generateImplicationsUsingBase() {
        int row = (int) Math.pow(getLattice().getNoOfElements(), this.getColumn());
        Relation e = Relation.e(this.getColumn());
        Relation pi = Relation.pi(row, row);
        Relation rho = Relation.rho(row, row);
        Relation ep = Relation.epsilon(this.getColumn());

        Relation r = this.generateCanonicalBase();

        Relation j = ep.composition(pi.transpose()).union(ep.composition(rho.transpose())).symetricQuotient(ep).down();
        Relation m = (pi.composition(r).composition(pi.transpose())).intersection(rho.composition(rho.transpose()));
        Relation mid = j.transpose().composition(m).composition(j);
        Relation compr = e.transpose().union(mid).union(r.composition(r)).union(r);

        Relation nextCompr = null;
        while (true) {
            nextCompr = generateNextR(this, compr);
            if (Utility.isEqual(compr, nextCompr)) {
                break;
            } else {
                compr = nextCompr;
            }
        }
        return nextCompr;
    }

    public Relation generateNextR(Relation r, Relation compr) {
        int row = (int) Math.pow(getLattice().getNoOfElements(), r.getColumn());
        Relation e = Relation.e(r.getColumn());
        Relation pi = Relation.pi(row, row);
        Relation rho = Relation.rho(row, row);
        Relation ep = Relation.epsilon(r.getColumn());

        Relation j1 = e.composition(pi.transpose()).union(e.composition(rho.transpose())).symetricQuotient(e).down();
        Relation m1 = (pi.composition(compr).composition(pi.transpose()))
                .intersection(rho.composition(rho.transpose()));
        Relation mid1 = j1.transpose().composition(m1).composition(j1);
        Relation comprr = e.transpose().union(mid1).union(compr.composition(compr)).union(compr);
        return comprr;
    }

    private Relation generateNext(Relation relation, Relation a) {
        Relation cm = this.generateAttributeConcept().getEquivalence();
        Relation e = Relation.e(this.getColumn());
        int identitySize = (int) Math.pow(getLattice().getNoOfElements(), this.getColumn());
        Relation i = Relation.identity(identitySize);

        Relation leftOfright = e.intersection(i.Not()).intersection(a.composition(Relation.L(1, identitySize)));

        Relation rightOfright = this.getAttributePrime().composition(this.getObjectPrime()).composition(e);

        Relation mid = ((leftOfright.rightResidue(rightOfright)).intersection(Relation.identity(identitySize)))
                .composition(Relation.L(identitySize, 1));

        Relation last = (cm.transpose().composition(Relation.L(cm.transpose().getColumn(), 1))).Not();
        ;

        Relation nexta = a.union(mid).intersection(last);

        Utility.PrintArray(nexta, "nexta");

        return nexta;
    }

    @Override
    public Relation getObjectPrime() {
        return this.transpose().leftResidue(epsilon(this.getSource()).transpose())
                .symetricQuotient(epsilon(this.getTarget())).Down();
    }

    @Override
    public Relation getAttributePrime() {
        return this.transpose().getPrime();
    }

    @Override
    public Relation getPrime() {
        return this.transpose().leftResidue(epsilon(this.getSource()).transpose())
                .symetricQuotient(epsilon(this.getTarget())).Down();
    }

    public static Lattice getLattice() {
        return lattice;
    }

    public void setLattice(Lattice lattice) {
        Relation.lattice = lattice;
    }

}
