package Algorithm;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by hidden on 14-6-30.
 */
public class AmazonMountainSolution {
    public static void main(String args[] ) throws Exception {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT
        * 3 //3座山
        * 1,2,3 //第一座山起始位置，宽，高
        * 3,2,4 //第二座山起始位置，宽，高
        * 5,3,6 //第三座山起始位置，宽，高
        * */
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        int num = Integer.valueOf(s);
        int index = 0;
        List<String> list = new ArrayList<String>();
        while(index<num){
            list.add(br.readLine());
            index++;
        }
        int totalDistance = 0;
        for(int i = 0; i<list.size(); i++){
            String[] strs = list.get(i).split(",");
            if(i==0) {
                totalDistance += Integer.valueOf(strs[2]);
            }else{
                String[] preStrs = list.get(i-1).split(",");
                int temp = Integer.valueOf(preStrs[0])+Integer.valueOf(preStrs[1]);
                if(temp < Integer.valueOf(strs[0])) {
                    totalDistance += Integer.valueOf(strs[2]);
                }else{
                    int temp2 = Integer.valueOf(strs[2])-Integer.valueOf(preStrs[2]);
                    temp2 = temp2>0 ? temp2 : -temp2;
                    totalDistance += temp2;
                }
            }
            if(i<list.size()-1){
                String[] afterStrs = list.get(i+1).split(",");
                int temp = Integer.valueOf(strs[0])+Integer.valueOf(strs[1]);
                if(temp < Integer.valueOf(afterStrs[0])) {
                    totalDistance += Integer.valueOf(strs[2]);
                }
            }else{
                totalDistance += Integer.valueOf(strs[2]);
            }
        }
        String[] end = list.get(list.size()-1).split(",");
        totalDistance += (Integer.valueOf(end[0])+Integer.valueOf(end[1]));
        System.out.println(totalDistance);
    }
}
