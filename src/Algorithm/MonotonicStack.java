package Algorithm;

import com.sun.org.apache.xerces.internal.impl.xpath.regex.Match;

import java.util.*;

/**
 * 单调栈的各种应用
 */
public class MonotonicStack {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>(Arrays.asList(2,1,5,6,2,3));
        System.out.println(largestRectangleArea(list));

        List<List<Integer>> matrix= new ArrayList<>();
        matrix.add(new ArrayList<>(Arrays.asList(1,0,1,0,0)));
        matrix.add(new ArrayList<>(Arrays.asList(1,0,1,1,1)));
        matrix.add(new ArrayList<>(Arrays.asList(1,1,1,1,1)));
        matrix.add(new ArrayList<>(Arrays.asList(1,0,0,1,0)));
        System.out.println(maximalRectangle(matrix));

        maxMultipleValue(new ArrayList<>(Arrays.asList(6,2,1)));

        findLeftFirstBigger(new int[]{3,8,6,2,7,1,9,5});
    }

    /**
     * Given n non-negative integers representing the histogram's bar height where the width of each bar is 1,
     * find the area of largest rectangle in the histogram.

     * For example,
     * Given heights = [2,1,5,6,2,3],
     * return 10.

     * 使用的单调栈，以单调递增栈为例，如果要push到栈顶的元素比现有的元素小，则这些较大的栈内元素需要先pop出来，直到下一个元素小于这个要push的元素为止，然后把这个新元素push上。
     */
    static int largestRectangleArea(List<Integer> height) {
        int ret = 0;
        height.add(0);
        Stack<Integer> index = new Stack<>();
        for(int i = 0; i < height.size(); i++) {
            while(index.size() > 0 && height.get(index.peek()) >= height.get(i)) {
                //System.out.println("will pop index:"+index.peek());
                int h = height.get(index.peek());
                index.pop();
                int sidx = index.size() > 0 ? index.peek() : -1;
                ret = Math.max(ret, h * (i-sidx-1));
            }
            index.push(i);
            //System.out.println("index:"+index);
        }
        return ret;
    }

    /**
     * 升级版，计算二维数组中全为1的矩形的最大值，如下二维数组他的返回值是6
     * 二维数组同样可以看成一个柱状图，然后计算矩形面积
     * 1 0 1 0 0
     * 1 0 1 1 1
     * 1 1 1 1 1
     * 1 0 0 1 0
     */
    static int maximalRectangle(List<List<Integer>> matrix) {
        if (matrix.size()==0)  return 0;
        int count = matrix.get(0).size();
        List<Integer> height = new ArrayList<>();
        while (count>0){
            height.add(0);
            count--;
        }
        int maxRect= 0;
        for(int i = 0; i < matrix.size(); ++i) {
            for(int j = 0; j < height.size(); ++j) {
                if(matrix.get(i).get(j) == 0)
                    height.set(j,0);
                else
                    height.set(j, height.get(j)+1);
            }
            maxRect = Math.max(maxRect, largestRectangleArea(height));
            //因为内部给add了一个0所以在外部还要减掉
            height = height.subList(0, height.size()-1);
        }
        return maxRect;
    }

    /**
     * 给定一个数组序列, 需要求选出一个区间, 使得该区间是所有区间中经过如下计算的值最大的一个：
     * 区间中的最小数 * 区间所有数的和最后程序输出经过计算后的最大值即可，不需要输出具体的区间。
     * 如给定序列  [6 2 1]则根据上述公式, 可得到所有可以选定各个区间的计算值:

     * [6] = 6 * 6 = 36;
     * [2] = 2 * 2 = 4;
     * [1] = 1 * 1 = 1;
     * [6,2] = 2 * 8 = 16;
     * [2,1] = 1 * 3 = 3;
     * [6, 2, 1] = 1 * 9 = 9;

     * 从上述计算可见选定区间 [6] ，计算值为 36， 则程序输出为 36。
     * 区间内的所有数字都在[0, 100]的范围内;
     */
    static void maxMultipleValue(List<Integer> list){
        // 首尾各增加一个-1
        List<Integer> tempList = new ArrayList<>();
        tempList.add(-1);
        for(int item: list){
            tempList.add(item);
        }
        tempList.add(-1);
        int[] sum = new int[tempList.size()];
        for (int i=1; i<tempList.size()-1; i++){
            sum[i] = sum[i-1]+tempList.get(i);
        }
        //两个数组分别保存左边界和右边界
        int[] left = new int[tempList.size()];
        int[] right = new int[tempList.size()];
        Stack<Integer> s = new Stack<>();
        s.push(0);
        for(int i = 1; i<=tempList.size()-2; i++){
            int x;
            for(x=s.peek(); tempList.get(x)>=tempList.get(i); x=s.peek()){
                s.pop();
            }
            left[i] = x+1;
            s.push(i);
        }
        while(!s.empty())s.pop();
        s.push(tempList.size()-1);
        for(int i = tempList.size()-2;i>=1;i--){
            int x;
            for(x = s.peek();tempList.get(x)>=tempList.get(i);x = s.peek()){
                s.pop();
            }
            right[i] = x-1;
            s.push(i);
        }
        int mx = 0;
        for(int i = 1;i<=tempList.size()-2;i++){
            mx = Math.max(mx,tempList.get(i)*(sum[right[i]]-sum[left[i]-1]));
        }
        System.out.println(mx);
    }

    /**
     * 打印出所有元素右边第一个大于该元素的值
     * @param a
     */
    static void findLeftFirstBigger(int[] a)
    {

        Stack<Integer> s = new Stack<>();
        if(a.length <= 1)
            return;

        s.push(a[0]);
        for(int i = 1; i < a.length; i++)
        {
            while(!s.empty() && a[i] > s.peek())
            {
                System.out.println(s.peek()+","+a[i]);
                s.pop();
            }

            s.push(a[i]);
        }
    }
}
