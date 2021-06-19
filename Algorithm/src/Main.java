import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int count = sc.nextInt();


        for(int i=(count-1); i>=0; i--) {
            for(int j=i; j>0; j--) {
                System.out.print(" ");
            }

            for(int k=i; k<count; k++) {
                System.out.print("*");
            }
            System.out.println("");
        }

    }
}
