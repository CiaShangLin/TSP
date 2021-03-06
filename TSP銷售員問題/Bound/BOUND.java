import com.sun.org.apache.xpath.internal.operations.Bool;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOUND {        //bround and bound

    static int[][] arr = null;
    static int min = Integer.MAX_VALUE;
    static int n = 0;


    public static void main(String[] args) {  //叫好的
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        try {


            StringTokenizer st;
            n = Integer.parseInt(br.readLine());

            int start, end, dis;
            arr = new int[n][n];
            for (int i = 0; i < n * (n - 1); i++) {
                st = new StringTokenizer(br.readLine(), " ");
                start = Integer.parseInt(st.nextToken());
                end = Integer.parseInt(st.nextToken());
                dis = Integer.parseInt(st.nextToken());
                arr[start][end] = dis;
            }

            //print(arr);

            g(0, 0, 0, 0);
            System.out.println(min);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    static void g(int go, int len, int bin, int time) {  //go=起點 len=目前走的距離 bin=走過哪個 time=已走了幾個點

        int[] bon = new int[n];

        if (time == n - 2) {  //例如0->1->2 他只能走3 他不能先走回0 不然沒辦法全部都走到
            for (int i = 1; i < n; i++) {
                if ((bin & (1 << i - 1)) == 0) { //2->3 011&100
                    len += arr[go][i]+arr[i][0];
                }
            }
            if (len < min) {
                min = len;
            }
        } else {
            for (int i = 1; i < n; i++) {
                if ((bin & (1 << i - 1)) == 0) {   //沒走過
                    int temp = arr[i][go];
                    arr[i][go] = 0;
                    bon[i-1] = bound(len + arr[go][i], bin, bin + (1 << (i - 1)));

                    arr[i][go] = temp;
                }
            }
        }


        for (int i = 0; i < (n-time)/2; i++) {
            int m = Integer.MAX_VALUE;
            int index = 0;
            for (int j = 0; j < n; j++) {
                if (bon[j] != 0 && m > bon[j]) {
                    m = bon[j];
                    index = j;  //決定往哪走 例如0->1
                }
            }
            bon[index] = 0; //走過

            if (m < min) {
                g(index + 1, len + arr[go][index + 1], bin + (1 << index), time + 1);
            } else {
                break;
            }
        }


    }


    static int bound(int len, int now, int next) {
        for (int j = 1; j < n; j++) {
            int m = Integer.MAX_VALUE;
            if((now & (1 << (j - 1))) != 0)
                continue;
            if ((now & (1 << (j - 1))) == 0) {   //沒走過的才算
                //System.out.println("bound:"+j);
                for (int k = 0; k < n; k++) {
                    if (arr[j][k] != 0 && (next & (1 << k - 1)) == 0) {  //arr=0的不看 走過的也不看
                        //System.out.println("arr[j][k]:"+j+" "+k+" arr="+arr[j][k]);
                        if (m > arr[j][k]){
                            m = arr[j][k];
                        }

                    }
                }
            }
            len += m;
        }
        //System.out.println("total:"+len);

        return len;

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




