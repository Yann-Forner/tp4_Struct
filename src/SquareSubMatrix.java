import java.util.Arrays;

public class SquareSubMatrix {
    private int dimension;
    private int[][]  matrix;
    private int firstLine;
    private int firstColumn;
    private int lastLine;
    private int lastColumn;

    public SquareSubMatrix(int dimension, int firstLine, int firstColumn, int lastLine, int lastColumn) {
        this.dimension = dimension;
        this.firstLine = firstLine;
        this.firstColumn = firstColumn;
        this.lastLine = lastLine;
        this.lastColumn = lastColumn;
        matrix = new int[dimension][dimension];
    }

    public void setMatrix(SquareSubMatrix matrix) {
        this.matrix = matrix.getMatrix();
    }
    public void setSubMatrix(int [][] matrix){
        for (int i = 0; i < this.getSubMatriceDimension()  ; i++) {
            for (int j = 0; j < this.getSubMatriceDimension() ; j++) {
                this.set(i, j, matrix[i][j]);
            }
        }
    }

    public int[][] getMatrix() {
        return matrix;
    }

    public int[][] getSubMatrix(){
        int[][] subMat = new int[this.getSubMatriceDimension()][this.getSubMatriceDimension()];
        for (int i = 0; i < this.getSubMatriceDimension() ; i++) {
            for (int j = 0; j < this.getSubMatriceDimension() ; j++) {
                subMat[i][j] = this.get(i,j);
            }
        }
        return subMat;
    }

    public SquareSubMatrix clone(){
        SquareSubMatrix square = new SquareSubMatrix(this.dimension,this.firstLine,this.firstColumn,this.lastLine,this.lastColumn);
        for (int i = 0; i <dimension ; i++) {
            for (int j = 0; j <dimension ; j++) {
                square.set(i,j ,this.matrix[i][j]);
            }
        }
        return square;
    }
    public int getMatriceDimension(){
        return dimension;
    }
    public int getSubMatriceDimension(){
        return this.lastLine + 1 - this.firstLine;
    }

    public int get(int row , int col){
        return this.matrix[row + firstLine][col + firstColumn];

    }
    public void set(int row, int col , int value){
        this.matrix[row+firstLine][col+firstColumn] = value;
    }

    public void sum (SquareSubMatrix matrix){
        for (int i = 0; i < this.getSubMatriceDimension() ; i++) {
            for (int j = 0; j < this.getSubMatriceDimension() ; j++) {
                this.set(i,j,this.get(i,j)+matrix.get(i,j));
            }
        }
    }

    public void product(int [][] submatrix){
        int[][] newMat = new int[this.getSubMatriceDimension()][this.getSubMatriceDimension()];
        for (int i = 0; i <this.getSubMatriceDimension() ; i++) {
            for (int j = 0; j < this.getSubMatriceDimension() ; j++) {
                int product = 0;
                for (int k = 0; k < this.getSubMatriceDimension() ; k++) {
                    product+= this.get(i,k)*submatrix[k][j];
                }
                newMat[i][j] = product;
            }
        }
        this.setSubMatrix(newMat);
    }

    @Override
    public String toString() {
        String mat ="[\n";
        for (int i = 0; i < this.getSubMatriceDimension() ; i++) {
            mat+="[";
            for (int j = 0; j < this.getSubMatriceDimension() ; j++) {
                mat+="|";
                mat+=this.get(i,j);
            }
            mat+="]\n";
        }
        mat+="]";
        return mat;
    }

    public int[][] power(int n){
        int[][] temp = this.getSubMatrix();
        if(n==1)return getSubMatrix();
        product(power(n-1));
        return temp;
    }

    public int[][] quickpower(int n){
        int[][] temp = this.getSubMatrix();
        if(n==1)return getSubMatrix();
        if(n%2==0){
            product(quickpower(n/2));
        }else{
            product(quickpower((n-1)/2));
            product(temp);
        }

        return temp;
    }

    public int[][] quickProduct(int[][] matB){
        return null;
    }

}
