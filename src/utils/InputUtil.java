package utils;

import java.util.Scanner;

public class InputUtil {

    static Scanner sc = new Scanner(System.in);

    public static int readInt(String msg) {
        while(true) {
            try {
                System.out.print(msg);
                return Integer.parseInt(sc.nextLine());
            } catch(Exception e) {
                System.out.println("Invalid number!");
            }
        }
    }

    public static String readString(String msg) {
        while(true) {
            System.out.print(msg);
            String s = sc.nextLine();
            if(s.matches("[a-zA-Z ]+")) return s;
            System.out.println("Only letters allowed!");
        }
    }
    public static String readDate(String msg){

        while(true){

            System.out.print(msg);

            String input = sc.nextLine();

            if(input.matches("\\d{4}-\\d{2}-\\d{2}"))
                return input;

            System.out.println("Invalid format. Use yyyy-mm-dd");
        }
    }
    public static double readDouble(String msg) {

        while(true) {
            try {
                System.out.print(msg);
                return Double.parseDouble(sc.nextLine());
            }
            catch(Exception e) {
                System.out.println("Invalid number. Try again.");
            }
        }
    }
}