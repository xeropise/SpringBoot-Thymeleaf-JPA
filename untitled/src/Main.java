import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

public class Main {

    public static void main(String[] args) {

        String s="ABCDEFGA";

        char[] arr = s.toCharArray();
        int[] indexArr = IntStream.range(0, 26).toArray();
        Arrays.fill(indexArr, 0);

        for(char c : arr) {
            int v = c - 'A';
            indexArr[v]++;
        }

        int max = Arrays.stream(indexArr).max().getAsInt();
        int index =0;
        for(int i : indexArr) {
            if(index >= 1) {
                System.out.println("?");
            }
            if(i == max) {
                break;
            }
            index++;
        }

        System.out.println(arr[index]);
    }
}
