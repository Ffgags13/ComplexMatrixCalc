import java.util.Scanner;

public class Main {
    private static final Scanner consIn = new Scanner(System.in);
    public static void main(String[] args) {

        while(true){
            System.out.println("Калькулятор матриц комплексных чисел");
            System.out.println("Выберите действие:");
            System.out.println("1: Создать новую матрицу");
            System.out.println("2: Редактировать матрицу");
            System.out.println("3: Удалить матрицу");
            System.out.println("4: Действие с матрицей");
            System.out.println("q: Выход");
            String lineInput = consIn.nextLine();
            while(lineInput.isEmpty()){
                System.out.println("Повторите ввод:");
                lineInput = consIn.nextLine();
            }
            char inputCom = lineInput.charAt(0);
            if(inputCom == 'q')
                break;
            String checkNum = "1234";
            while(checkNum.indexOf(inputCom) == -1){
                System.out.println("Повторите ввод:");
                lineInput = consIn.nextLine();
                inputCom = lineInput.charAt(0);
            }
            switch(inputCom){
                case '1':
                    System.out.println("Введите размер матрицы через пробел");
            }
        }
    }
}
