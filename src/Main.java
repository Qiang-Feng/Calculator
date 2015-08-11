import java.util.Scanner;

public class Main
{
    public static int answer;

    public static void main(String[] args)
    {
        Scanner scan = new Scanner(System.in);

        System.out.println("Please enter in the first number:");
        int first = scan.nextInt();

        System.out.println("Please enter in the second number:");
        int second = scan.nextInt();

        System.out.println("Please enter in the operation to perform:");
        String operation = scan.next();

        switch (operation)
        {
            case "*":
            case "x":
                answer = first * second;
                break;

            case "+":
                answer = first + second;
                break;

            case "-":
                answer = first - second;
                break;

            case "/":
                answer = first / second;
                break;

            default:
                System.out.println("Operation was invalid.");
                return;
        }

        System.out.println(first + " " + operation + " " + second + " = " + answer);
    }
}
