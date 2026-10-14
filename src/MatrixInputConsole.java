import java.util.Scanner;

public class MatrixInputConsole {
    private static final Scanner matrixIn = new Scanner(System.in);
    public static ComplexNumber[][] flatComplex(int mode, int rows, int cals){
        ComplexNumber[][] matrixArr = new ComplexNumber[rows][cals];
        if (mode == 0){
            System.out.println("Введите элементы матрицы через ; " +
                    "между действительной и мнимой частью комплексного числа укажите , ");
            int i = 0;
            while(i < rows){
                System.out.println("Строка " + i + ": ");
                String rawLine = matrixIn.nextLine();
                while(rawLine.isEmpty()) {
                    System.out.println("Повторите ввод:");
                    rawLine = matrixIn.nextLine();
                }
                String []cells = rawLine.split("\\s*;\\s*", cals);
                for(int ind = 0; ind < cals; ++ind){
                    String []cellsSep = cells[ind].split("\\s*,\\s*", 2 );
                    if(cellsSep[0].isEmpty())
                        cellsSep[0] ="0";
                    if(cellsSep[1].isEmpty())
                        cellsSep[1] ="0";
                    if(!(cellsSep[0].matches("^[+-]?(?:\\d+(?:\\.\\d*)?|\\.\\d+)$")
                            || !(cellsSep[1].matches("^[+-]?(?:\\d+(?:\\.\\d*)?|\\.\\d+)$") )))
                    {
                        System.out.println("Неверное число в " + ind + "элементе. Повторите ввод.");
                        break;
                    }
                    matrixArr[i][ind] = new ComplexNumber(Double.parseDouble(cellsSep[0]), Double.parseDouble(cellsSep[1]));
                    if (ind + 1 == cals)
                        ++i;
                }
            }
        }
        return matrixArr;
    }
}
