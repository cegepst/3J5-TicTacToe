import java.util.Scanner;

public class Terminal {

    public static void lineBreak() {
        System.out.println();
    }


    public static int readInt(String message) {
        Scanner scanner = new Scanner(System.in);
        System.out.print(message);
        return scanner.nextInt();
    }
}
