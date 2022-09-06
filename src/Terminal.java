import java.util.Scanner;

public class Terminal {

    public static void lineBreak() {
        System.out.println();
    }


    public static int readInt(String message) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a number");
        return scanner.nextInt();
    }
}
