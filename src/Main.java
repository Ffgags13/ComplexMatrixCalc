import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    private static final Scanner consIn = new Scanner(System.in);
    public static void main(String[] args) {
        Map<String, ComplexMatrix> dictMatrixes = new HashMap<>();
        while(true){
            System.out.println("Калькулятор матриц комплексных чисел");
            System.out.println("Выберите действие:");
            System.out.println("1: Создать новую матрицу");
            System.out.println("2: Вывести список матриц");
            System.out.println("3: Удалить матрицу");
            System.out.println("4: Действие с матрицей");
            System.out.println("5: Вывести матрицу по названию");
            System.out.println("q: Выход");
            System.out.println(":");
            String lineInput = consIn.nextLine();
            while(lineInput.isEmpty()){
                System.out.println("Повторите ввод: ");
                lineInput = consIn.nextLine();
            }
            char inputCom = lineInput.charAt(0);
            if(inputCom == 'q')
                break;
            String checkNum = "12345";
            while(checkNum.indexOf(inputCom) == -1){
                System.out.println("Повторите ввод: ");
                lineInput = consIn.nextLine();
                inputCom = lineInput.charAt(0);
            }
            switch(inputCom){

                case '1':
                    System.out.println("Введите УНИКАЛЬНОЕ имя для матрицы: ");
                    String nameLine = consIn.nextLine();
                    while(dictMatrixes.containsKey(nameLine)){
                        System.out.println("Повторите ввод: ");
                        nameLine = consIn.nextLine();
                    }
                    System.out.println("Введите размер матрицы через пробел: ");
                    String sizeLine = consIn.nextLine();

                    String []sizeLineSep = sizeLine.split("\\s* \\s*", 2);
                    while(!(sizeLineSep.length == 2) ||!(sizeLineSep[0].matches("^[+-]?[1-9]+[0-9]*$"))
                            || !(sizeLineSep[1].matches("^[+-]?[1-9]+[0-9]*$"))){
                        System.out.println("Повторите ввод: ");
                        sizeLine = consIn.nextLine();
                        sizeLineSep = sizeLine.split("\\s* \\s*", 2);
                    }
                    int []sizeArr = {Integer.parseInt(sizeLineSep[0]), Integer.parseInt(sizeLineSep[1])};
                    dictMatrixes.put(nameLine, new ComplexMatrix(MatrixInputConsole.flatComplex(0, sizeArr[0], sizeArr[1])));
                    System.out.println("Матрица создана");
                    break;

                case '2':
                    System.out.println(dictMatrixes.keySet());
                    break;

                case '3':
                    System.out.println("Введите название матрицы для удаления:");
                    System.out.println(dictMatrixes.keySet());
                    String toDelLine = consIn.nextLine();
                    if(!(toDelLine.isEmpty()) && (dictMatrixes.containsKey(toDelLine))){
                        System.out.println("Матрица " + toDelLine + " удалена. ");
                        dictMatrixes.remove(toDelLine);
                    }
                    else
                        System.out.println("Неправильное название, удаление не произведено ");
                    break;

                case '4':
                    System.out.println("Выберите матрицу над которой необходимо совершить операцию");
                    System.out.println(dictMatrixes.keySet());
                    String leftMatrix = consIn.nextLine();
                    if(!(leftMatrix.isEmpty()) && (dictMatrixes.containsKey(leftMatrix))){
                        System.out.println("Выберите действие");
                        System.out.println("1:Сложение");
                        System.out.println("2:Вычитание");
                        System.out.println("3:Умножение");
                        System.out.println("4:Умножение на число");
                        System.out.println("5:Вычисление определителя");
                        System.out.println("6:Деление");
                        System.out.println("q: Выйти");


                        String line2Input = consIn.nextLine();
                        while(line2Input.isEmpty()){
                            System.out.println("Повторите ввод: ");
                            line2Input = consIn.nextLine();
                        }
                        char input2Com = line2Input.charAt(0);
                        if(input2Com == 'q')
                            break;
                        String check2Num = "123456";
                        while(check2Num.indexOf(input2Com) == -1){
                            System.out.println("Повторите ввод: ");
                            line2Input = consIn.nextLine();
                            input2Com = line2Input.charAt(0);
                        }

                        ComplexMatrix leftOperand = dictMatrixes.get(leftMatrix);

                        if(input2Com == '1' || input2Com == '2'
                                || input2Com == '3' || input2Com == '6'){
                            System.out.println("Выберите вторую матрицу:");
                            System.out.println(dictMatrixes.keySet());
                            String rightMatrix = consIn.nextLine();
                            while(!(dictMatrixes.containsKey(rightMatrix))){
                                System.out.println("Повторите ввод: ");
                                rightMatrix = consIn.nextLine();
                            }
                            System.out.println("Введите имя для результирующей матрицы: ");
                            String resultName = consIn.nextLine();
                            while(dictMatrixes.containsKey(resultName) || resultName.isEmpty()){
                                System.out.println("Повторите ввод: ");
                                resultName = consIn.nextLine();
                            }
                            ComplexMatrix rightOperand = dictMatrixes.get(rightMatrix);
                            ComplexMatrix resultMatrix;
                            if(input2Com == '1')
                                resultMatrix = leftOperand.add(rightOperand);
                            else if(input2Com == '2')
                                resultMatrix = leftOperand.subtraction(rightOperand);
                            else if(input2Com == '3')
                                resultMatrix = leftOperand.multiplie(rightOperand);
                            else
                                resultMatrix = leftOperand.multiplie(rightOperand.inverse());
                            dictMatrixes.put(resultName, resultMatrix);
                            System.out.println("Матрица " + resultName + " создана");
                        }
                        else if(input2Com == '4'){
                            System.out.println("Введите скаляр (действительное число): ");
                            String scalarLine = consIn.nextLine();
                            while(!(scalarLine.matches("^[+-]?(?:\\d+(?:\\.\\d*)?|\\.\\d+)$"))){
                                System.out.println("Повторите ввод: ");
                                scalarLine = consIn.nextLine();
                            }
                            System.out.println("Введите имя для результирующей матрицы: ");
                            String resultName = consIn.nextLine();
                            while(dictMatrixes.containsKey(resultName) || resultName.isEmpty()){
                                System.out.println("Повторите ввод: ");
                                resultName = consIn.nextLine();
                            }
                            dictMatrixes.put(resultName,
                                    leftOperand.multiplieScalar(Double.parseDouble(scalarLine)));
                            System.out.println("Матрица " + resultName + " создана");
                        }
                        else if(input2Com == '5'){
                            System.out.println("Определитель матрицы " + leftMatrix
                                    + " = " + leftOperand.determine());
                        }
                    }
                    else
                        System.out.println("Неправильное название матрицы, действие не произведено ");
                    break;

                case '5':
                    System.out.println("Введите название матрицы для вывода:");
                    System.out.println(dictMatrixes.keySet());
                    String showLine = consIn.nextLine();
                    if(!(showLine.isEmpty()) && dictMatrixes.containsKey(showLine)){
                        System.out.println(showLine + " = ");
                        System.out.println(dictMatrixes.get(showLine));
                    }
                    else
                        System.out.println("Неправильное название ");
                    break;

                default:
                    System.out.println("Неизвестное действие");
            }
        }
    }
}