import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class JAVA {

    static int[][] W;
    static int[][] D;
    static int n;
    static int A;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        A = (int) Math.pow(2, n - 1);
        W = new int[n][n];
        D = new int[n][A];

        StringTokenizer st;
        int start, end, dis;
        for (int i = 0; i < n * (n - 1); i++) {
            st = new StringTokenizer(br.readLine(), " ");
            start = Integer.parseInt(st.nextToken());
            end = Integer.parseInt(st.nextToken());
            dis = Integer.parseInt(st.nextToken());
            W[start][end] = dis;
        }

        travel();
        int min = Integer.MAX_VALUE;
        for (int i = 1; i < n; i++) {
            int ans = W[0][i] + D[i][A - 1 - (int) Math.pow(2, i - 1)];
            if (min > ans)
                min = ans;
        }
        System.out.println(min);


    }

    static void travel() {
        for (int i = 1; i < n; i++) {
            D[i][0] = W[i][0];  //初始化D[0][i]  i->0
        }

        for (int j = 1; j < A-1; j++) {    //由上而下 由座致右
            for (int i = 1; i < n; i++) {
                if (((1 << (i - 1)) & j) == 0) {

                    int count = 1;
                    int t = j;
                    while (t != 0) {
                        if ((t & 1) == 1) {

                            int a = W[i][count] + D[count][j - (int) Math.pow(2, count - 1)];

                            if (D[i][j] == 0) {
                                D[i][j] = a;
                            } else if (a < D[i][j]) {
                                D[i][j] = a;
                            }
                        }
                        t = t >> 1;
                        count++;
                    }
                }
            }
        }
    }


    static void print() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < A; j++) {
                System.out.print(D[i][j] + " ");
            }
            System.out.println();
        }
    }

}
