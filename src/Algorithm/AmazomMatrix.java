package Algorithm;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hidden on 14-6-30.
 * matrix is right -> left down -> left -> up
 * loop
 * 遍历矩阵路线为从左到右，然后沿着左下方向，然后再向左，然后再向上，就是一个漩涡路线
 */
public class AmazomMatrix {
    static int[] convertMatrix(int width, int height, int[] matrix) {
        List<Integer> temp = new ArrayList<Integer>();
        int times = 0;
        while (2*(times)<height) {
            int i=times,j=times;
            while (j < width-times*2) {
                temp.add(matrix[getIndex(i,j,width)]);
                j++;
            }
            j--;
            while(j>times && i<height-times-1){
                j--;
                i++;
                temp.add(matrix[getIndex(i,j,width)]);
            }
            while(j>times && i>times){
                j--;
                temp.add(matrix[getIndex(i,j,width)]);
            }
            while(i>times+1){
                i--;
                temp.add(matrix[getIndex(i,j,width)]);
            }
            times++;
        }
        int[] res = new int[temp.size()];
        for (int i = 0; i < temp.size(); i++) {
            res[i] = temp.get(i);
        }
        return res;
    }

    static int getIndex(int i, int j, int width){
        return i*width+j;
    }

    public static void main(String[] args) {
        int[] matrix = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24};
        int[] res = convertMatrix(6, 4, matrix);
        for (int i : res) {
            System.out.println(i);
        }
    }
}
