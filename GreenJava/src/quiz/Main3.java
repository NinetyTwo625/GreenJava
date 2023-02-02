package Calculator.src.quiz;

import java.util.Arrays;
import java.util.Scanner;

public class Main3 {
    static int[] arr;
    static int n;
    static int coin;

    public Main3() {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        coin = sc.nextInt();

        arr = new int[coin];
        Arrays.fill(arr, 1);

        while (true) {
            print(coin);
            count();
            if (arr[0] > n) {
                break;
            }

        }
    }

    public void print(int coin) {
        for (int i = 0; i < coin; i++) {
            System.out.print(arr[i]);
        }
        System.out.println();
    }

    public void count() {
        arr[arr.length - 1] += 1;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > n) {
                arr[i] = 1;
                arr[i - 1] += 1;
            }
            for (int j = 1; j < arr.length; j++) {
                if (arr[j] > n) {
                    arr[j] = 1;
                    arr[j - 1] += 1;
                }
            }
        }
    }

    public static void main(String[] args) {
        new Main3();
    }
}