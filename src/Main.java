public class Main {
    public static void main(String[] args) {
        int[][] matrice = new int [2][2];
        matrice[0][0] = 5;
        matrice[0][1] = 6;
        matrice[1][0] = 7;
        matrice[1][1] = 8;
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {

            }
        }
        SquareSubMatrix square = new SquareSubMatrix(10,4,4,5,5);
        square.set(0,0,1);
        square.set(0,1,2);
        square.set(1,0,3);
        square.set(1,1,4);
        SquareSubMatrix square2 = new SquareSubMatrix(10,4,4,5,5);
        square2.set(0,0,1);
        square2.set(0,1,2);
        square2.set(1,0,3);
        square2.set(1,1,4);

        System.out.println(square);
        square.power(4);
        System.out.println(square);
        square2.quickpower(4);
        System.out.println(square);

    }
}
