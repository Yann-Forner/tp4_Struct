import java.util.Arrays;

public class SquareSubMatrix {
    private int dimension;
    private int[][]  matrix;
    private int firstLine;
    private int firstColumn;
    private int lastLine;
    private int lastColumn;
    private int products=0;
    private int sums=0;

    public SquareSubMatrix(int dimension, int firstLine, int firstColumn, int lastLine, int lastColumn) {
        this.dimension = dimension;
        this.firstLine = firstLine;
        this.firstColumn = firstColumn;
        this.lastLine = lastLine;
        this.lastColumn = lastColumn;
        matrix = new int[dimension][dimension];
    }

    public int getDimension() {
        return dimension;
    }

    public int getFirstLine() {
        return firstLine;
    }

    public int getFirstColumn() {
        return firstColumn;
    }

    public int getLastLine() {
        return lastLine;
    }

    public int getLastColumn() {
        return lastColumn;
    }

    public int getProducts() {
        return products;
    }

    public int getSums() {
        return sums;
    }

    public void setProducts(int products) {
        this.products = products;
    }

    public void setSums(int sums) {
        this.sums = sums;
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
        for (int i = 0; i < this.getSubMatriceDimension() ; i++) {
            for (int j = 0; j < this.getSubMatriceDimension() ; j++) {
                square.set(i,j, this.get(i,j));
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

    public SquareSubMatrix sum (SquareSubMatrix matrix){
        for (int i = 0; i < this.getSubMatriceDimension() ; i++) {
            for (int j = 0; j < this.getSubMatriceDimension() ; j++) {
                this.set(i,j,this.get(i,j)+matrix.get(i,j));
                ++sums;
            }
        }

        return this;
    }

    public void product(SquareSubMatrix submatrix){
        int[][] newMat = new int[this.getSubMatriceDimension()][this.getSubMatriceDimension()];
        for (int i = 0; i <this.getSubMatriceDimension() ; i++) {
            for (int j = 0; j < this.getSubMatriceDimension() ; j++) {
                int product = 0;
                for (int k = 0; k < this.getSubMatriceDimension() ; k++) {
                    product+= this.get(i,k)*submatrix.get(k,j);
                    this.products++;
                    this.sums++;
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

    public SquareSubMatrix power(int n){
        SquareSubMatrix temp = this.clone();
        if(n==1)return this;
        product(power(n-1));
        return temp;
    }

    public SquareSubMatrix quickpower(int n){
        SquareSubMatrix temp = this.clone();
        if(n==1)return this;
        if(n%2==0){
            product(quickpower(n/2));
        }else{
            product(quickpower((n-1)/2));
            product(temp);
        }

        return temp;
    }
    public SquareSubMatrix portion(SquareSubMatrix subMatrix, int xlin, int xcol) {
        int newSubMatrixLength = subMatrix.getSubMatriceDimension()/2;
        int debMatR = 0;
        int debMatC = 0;
        if(xlin==1)debMatR= newSubMatrixLength;
        if(xcol==1)debMatC= newSubMatrixLength;
        SquareSubMatrix newSubMatrix = new SquareSubMatrix(subMatrix.getDimension(),
                subMatrix.getFirstLine(), subMatrix.getFirstColumn(),
                (subMatrix.firstLine+subMatrix.getSubMatriceDimension()/2)-1,
                (subMatrix.getFirstColumn()+subMatrix.getSubMatriceDimension()/2)-1);

        for (int i = 0; i < newSubMatrixLength; i++) {
            for (int j = 0; j < newSubMatrixLength; j++) {
                newSubMatrix.set(i, j,subMatrix.get(debMatR + i, debMatC + j));
            }
        }
        return newSubMatrix;
    }

    public SquareSubMatrix regroupement(SquareSubMatrix A,SquareSubMatrix B,SquareSubMatrix C,SquareSubMatrix D){
        for (int i = 0; i < A.getSubMatriceDimension(); i++) {
            for (int j = 0; j < A.getSubMatriceDimension(); j++) {
                this.set(i,j,A.get(i,j));
                this.set(i+A.getSubMatriceDimension(),j,B.get(i,j));
                this.set(i,j+A.getSubMatriceDimension(),C.get(i,j));
                this.set(i+A.getSubMatriceDimension(),j+A.getSubMatriceDimension(),D.get(i,j));
            }
        }
        this.setProducts(A.getProducts()+B.getProducts()+C.getProducts()+D.getProducts());
        this.setSums(A.getSums()+B.getSums()+C.getSums()+D.getSums());
        return this;
    }


    public SquareSubMatrix quickProduct(SquareSubMatrix matB){
        if(this.getSubMatriceDimension()==2) {
            this.product(matB);
        }
        else {
            SquareSubMatrix A = portion(this,0,0).quickProduct(portion(matB,0,0));//A1*B1

            SquareSubMatrix B = portion(this,0,1).quickProduct(portion(matB,1,0));//A2*B3

            SquareSubMatrix C = portion(this,0,0).quickProduct(portion(matB,0,1));//A1*B2

            SquareSubMatrix D = portion(this,0,1).quickProduct(portion(matB,1,1));//A2*B4

            SquareSubMatrix E = portion(this,1,0).quickProduct(portion(matB,0,0));//A3*B1

            SquareSubMatrix F = portion(this,1,1).quickProduct(portion(matB,1,0));//A4*B3

            SquareSubMatrix G = portion(this,1,0).quickProduct(portion(matB,0,1));//A3*B2

            SquareSubMatrix H = portion(this,1,1).quickProduct(portion(matB,1,1));//A4*B4

            SquareSubMatrix C1 = A.sum(B);

            SquareSubMatrix C2 = C.sum(D);

            SquareSubMatrix C3 = E.sum(F);

            SquareSubMatrix C4 = G.sum(H);

            return regroupement(C1,C2,C3,C4);

        }
        return this;

    }
    public SquareSubMatrix veryQuickPower(int n){
        SquareSubMatrix temp = this.clone();
        if(n==1)return this;
        if(n%2==0){
            quickProduct(veryQuickPower(n/2));
        }else{
            quickProduct(veryQuickPower((n-1)/2));
            quickProduct(temp);
        }
        return this;
    }
}
