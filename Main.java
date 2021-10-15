package com.company;
import java.util.*; // сканер

public class Main {

    public static int rimToArab(String rim) {
        int decimal = 0;
        int lastNumber = 0;
        String romanNum = rim.toUpperCase(); // Поднимает значение до вернхего регистра (здесь - римские)
        for (int x = romanNum.length() - 1; x >= 0 ; x--) {
            char convertToDecimal = romanNum.charAt(x); // возвращает указанный символ из строки по индексу от 0 до -1
            switch (convertToDecimal) {
                case 'C':
                    decimal = processArab(100, lastNumber, decimal);
                    lastNumber = 100;
                    break;

                case 'L':
                    decimal = processArab(50, lastNumber, decimal);
                    lastNumber = 50;
                    break;

                case 'X':
                    decimal = processArab(10, lastNumber, decimal);
                    lastNumber = 10;
                    break;

                case 'V':
                    decimal = processArab(5, lastNumber, decimal);
                    lastNumber = 5;
                    break;

                case 'I':
                    decimal = processArab(1, lastNumber, decimal);
                    lastNumber = 1;
                    break;
            }
        }
        return decimal;
    }
    public static int processArab(int decimal, int lastNumber, int lastDecimal) {
        if (lastNumber > decimal) {
            return lastDecimal - decimal;
        } else {
            return lastDecimal + decimal;
        }
    }
    public static String arabToRim (int number) {
        int[] arab_list = new int[]{100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] rom_list = new String[]{"C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        StringBuilder rim = new StringBuilder(); // класс расширяемой строки
        for (int i = 0; i < arab_list.length; i += 1)
            while (number >= arab_list[i]){
                number -= arab_list[i];
                rim.append(rom_list[i]);
            }
        return rim.toString();
        }


    public static void main (String[] args) {
        while (true) {
            int a = 0; // неподсредственно А
            int b = 0;  // B
            boolean rim = false; // римские ли?
            int Otvet = 0; // ответ арабские
            String RomOtvet = ""; // ответ римские
            System.out.println("Введите выражение вида AхB в одну строку (где х = *, /, -, +" +
                    " \n A и B = натуральные числа от 1 до 10 в римской либо арабской системы счисления");
            Scanner vvod = new Scanner(System.in);
            String str = vvod.nextLine(); //Ввод с клавы в конрсоль строки (только её)
            String[] subStr; //массив хранения строк
            String delimeter = "[\\s+*/-]+"; // Разделитель
            subStr = str.split(delimeter); // Разделения строки str с помощью метода split()
            // Вывод результата на экран
            if( subStr.length>2){
                System.out.println("Выражение может содержать только 2 числа");
                continue;
            }
            if ((str.contains("X") || str.contains("x") || str.contains("I") || str.contains("i") || str.contains("V") ||
                    str.contains("v")) && (!str.contains("0") || !str.contains("1") || !str.contains("2") || !str.contains("3")
                    || !str.contains("4") || !str.contains("5") || !str.contains("6") || !str.contains("7") || !str.contains("8") || !str.contains("9")
                    || !str.contains("10"))) {
                a = rimToArab(subStr[0]);
                b = rimToArab(subStr[1]);
                rim = true;
            }
            else if ((!str.contains("X") || !str.contains("x") || !str.contains("I") || !str.contains("i") || !str.contains("V") ||
                    str.contains("v")) && (str.contains("0") || str.contains("1") || str.contains("2") || str.contains("3")
                    || str.contains("4") || str.contains("5") || str.contains("6") || str.contains("7") || str.contains("8") || str.contains("9")
                    || str.contains("10"))) {
                a = Integer.parseInt(subStr[0]);
                b = Integer.parseInt(subStr[1]);
                rim = false;
            }
            else {
                System.out.println("Следуйте инструкции");
            }
            if (a <= 10 && a > 0 && b > 0 && b <= 10){
                if (str.contains("+")) {
                    Otvet = a + b;
                } else if (str.contains("-")) {
                    Otvet = a - b;
                } else if (str.contains("*")) {
                    Otvet = a * b;
                } else if (str.contains("/")) {
                    Otvet = a / b;
                }
            }
            else {
                System.out.println("a и b должны быть больше 0, но мешьше 11");
                continue;
            }
            if (rim) {
                RomOtvet = arabToRim(Otvet);
                if (Otvet<=0) {
                    System.out.println("Результатом выражения в римской системе счисления не может быть отрицательным значением либо 0");
                    continue;
                }
                System.out.println(RomOtvet);

            } else System.out.println(Otvet);
        }
    }
}
