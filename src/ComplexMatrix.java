public class ComplexMatrix {
    private final ComplexNumber [][]matrixArr;
    ComplexMatrix(ComplexNumber [][]matrixArr){
        this.matrixArr = matrixArr;
    }

    public ComplexNumber[][] getMatrixArr() {
        return matrixArr;
    }

    public ComplexMatrix add(ComplexMatrix addend){
        if(!(this.matrixArr.length == addend.matrixArr.length
                && this.matrixArr[0].length == addend.matrixArr[0].length)){
            System.out.println("Ошибка сложение, неверный размер матриц," +
                    " исходная матрица не изменена");
            return new ComplexMatrix(this.matrixArr);
        }
       ComplexNumber[][] result =new ComplexNumber[this.matrixArr.length][this.matrixArr[0].length];
       for(int i =0; i < matrixArr.length; ++i){
           for(int j =0; j < matrixArr[0].length; ++j){
               result[i][j] = this.matrixArr[i][j].add(addend.matrixArr[i][j]);
           }
       }
       return new ComplexMatrix(result);
    }

    public ComplexMatrix subtraction(ComplexMatrix sub){
        if(!(this.matrixArr.length == sub.matrixArr.length
                && this.matrixArr[0].length == sub.matrixArr[0].length)){
            System.out.println("Ошибка вычитания, неверный размер матриц," +
                    " исходная матрица не изменена");
            return new ComplexMatrix(this.matrixArr);
        }
        ComplexNumber[][] result =new ComplexNumber[this.matrixArr.length][this.matrixArr[0].length];
        for(int i =0; i < matrixArr.length; ++i){
            for(int j =0; j < matrixArr[0].length; ++j){
                result[i][j] = this.matrixArr[i][j].subtraction(sub.matrixArr[i][j]);
            }
        }
        return new ComplexMatrix(result);
    }


}
