//강사님 풀이
//DFS(완전탐색) & 백트래킹 알고리즘 문제
//일일히 값을 하나하나 넣어서 확인하는 류

package Calculator.src.quiz;

import java.util.Scanner;

public class Main2 {
    static int ar[];
    static int n, m;

    public static void dice(int p) {
        if (p == m) {
            for (int i = 0; i < ar.length; i++) {
                System.out.print(ar[i] + 1);
            }
            System.out.println();
            return;
        }
        for (int i = 0; i < n; i++) {
            ar[p] = i;
            dice(p + 1);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        sc.close();

        ar = new int[m];

        dice(0);
    }
}