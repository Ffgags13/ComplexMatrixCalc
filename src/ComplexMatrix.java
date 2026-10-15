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

    public ComplexMatrix multiplie(ComplexMatrix mult){
        if(!(this.matrixArr[0].length == mult.matrixArr.length)){
            System.out.println("Ошибка умножения, неверный размер матриц," +
                    " исходная матрица не изменена");
            return new ComplexMatrix(this.matrixArr);
        }
        ComplexNumber[][] result =new ComplexNumber[this.matrixArr.length][mult.matrixArr[0].length];
        for(int i =0; i < this.matrixArr.length; ++i){
            for(int j =0; j < mult.matrixArr[0].length; ++j){
                ComplexNumber tempSumm = new ComplexNumber(0, 0);
                for(int s = 0; s < mult.matrixArr.length; ++s){
                    tempSumm = tempSumm.add(this.matrixArr[i][s].multiplie(matrixArr[s][j]));

                }
                    result[i][j] = tempSumm;
                }
        }
        return new ComplexMatrix(result);
    }

    public ComplexMatrix multiplieScalar(double k){
        ComplexNumber[][] result =new ComplexNumber[this.matrixArr.length][this.matrixArr[0].length];
        for (int i = 0; i < this.matrixArr.length; ++i)
            for(int j = 0; j < this.matrixArr[0].length;++j){
                result[i][j] = this.matrixArr[i][j].multiplieScalar(k);
            }
        return new ComplexMatrix(result);
    }
    private ComplexMatrix getSubMatrix(ComplexMatrix inputMatrix, int rowSkip, int colSkip){
        ComplexNumber [][]subMatrixArr = new ComplexNumber[inputMatrix.matrixArr.length][inputMatrix.matrixArr.length];
        for(int i = 0; i < inputMatrix.matrixArr.length;++i){
            if (i == rowSkip)
                continue;
            for(int j = 0; j < inputMatrix.matrixArr.length;++j){
                if(j == colSkip)
                    continue;
                subMatrixArr[i][j] = inputMatrix.matrixArr[i][j];

            }
        }
        return new ComplexMatrix(subMatrixArr);
    }
    public ComplexNumber determine(){
        if(!(this.matrixArr.length == this.matrixArr[0].length)){
            System.out.println("Ошибка, введена не квадратная матрица");
            return new ComplexNumber(0,0);
        }

        if(this.matrixArr.length == 1)
            return this.matrixArr[0][0];

        if(this.matrixArr.length == 2)
            return ((this.matrixArr[0][0].multiplie(this.matrixArr[1][1])).subtraction(
                    this.matrixArr[0][1].multiplie(this.matrixArr[1][0])));

        ComplexNumber result = new ComplexNumber(0, 0);
        int sign = 1;

        for(int j = 0; j < this.matrixArr[0].length; ++j){
            result = result.add(((this.matrixArr[0][j]).multiplieScalar((double)sign)).multiplie((getSubMatrix(this, 0, j)).determine()));
            sign = - sign;
        }
        return result;
    }

    public ComplexMatrix inverse(){
        if(this.determine().getReal() == 0 && this.determine().getImagine() == 0){
            System.out.println("Ошибка, вырожденная матрица");
            return this;
        }
        ComplexNumber[][] result =new ComplexNumber[this.matrixArr.length][this.matrixArr.length];
        for(int i = 0; i < this.matrixArr.length;++i){
            for(int j = 0; j < this.matrixArr.length;++j){
                result[j][i] = (this.matrixArr[i][j].multiplieScalar((double)(Math.pow(-1, i+j)))).division(this.determine());
            }
        }
        return new ComplexMatrix(result);
    }


}


