import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class TEST2 {

    static int[][] arr = null;
    static int min = Integer.MAX_VALUE;
    static int n = 0;
    static int ans = 0;

    public static void main(String[] args) {  //報立法+剪枝
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        try {
            n = Integer.parseInt(br.readLine());
            ans = ((int) Math.pow(2, n - 1)) - 1;

            StringTokenizer st;

            int start, end, dis;
            arr = new int[n][n];
            for (int i = 0; i < n * (n - 1); i++) {
                st = new StringTokenizer(br.readLine(), " ");
                start = Integer.parseInt(st.nextToken());
                end = Integer.parseInt(st.nextToken());
                dis = Integer.parseInt(st.nextToken());
                arr[start][end] = dis;
            }

            g(0, 0);
            System.out.println(min);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    static void g(int start, int bin) {
        for (int i = start; i < n; i++) {
            if (arr[start][i] == 0) {
                continue;
            } else {
                min(arr[start][i], i, bin + (1 << (i - 1)));
            }
        }
    }


    static void min(int total, int start, int bin) {
        for (int j = 1; j < n; j++) {
            if (bin == ans) {

                total += arr[start][0];
                if (total < min)
                    min = total;
                break;

            } else if (arr[start][j] != 0 && (bin & (1 << (j - 1))) == 0) {    //AND=0 沒走過
                if (total + arr[start][j] > min) {
                    break;
                }
                //System.out.println("BIN M:"+(bin +(1 << (j - 1)))+" J="+j);
                min(total + arr[start][j], j, bin + (1 << j - 1));
            }
        }

    }


    static void print(int[][] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
    }

}

