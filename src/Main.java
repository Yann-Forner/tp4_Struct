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
        SquareSubMatrix square = new SquareSubMatrix(15,5,5,8,8);
        square.setSubMatrix(
                new int [][]{{7,8,9,10},{11,12,13,14},{15,16,17,18},{19,20,21,22}});
        SquareSubMatrix square3 = square.clone();
        SquareSubMatrix square2 = new SquareSubMatrix(15,5,5,8,8);
        square2.setSubMatrix(
                new int [][]{{1,2,3,4},{5,6,7,8},{9,10,11,12},{13,14,15,16}});

        ;
        System.out.println("QUICK");
        System.out.println(square.quickProduct(square2));
        square3.product(square2);
        System.out.println("PRODUCT");
        System.out.println(square3);
//        SquareSubMatrix square3 = new SquareSubMatrix(15,5,5,6,6);
//        square3.setSubMatrix(new int [][]{
//                {1,2},{3,4}
//        });
//        SquareSubMatrix square4 = square3.clone();
//        square3.product(square3);
//        System.out.println(square3);
//        square4.power(2);
//        System.out.println(square4);





    }
}
