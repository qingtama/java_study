package Algorithm;

/**
 * Created by hidden on 15/10/24.
 *
 * 求数组左边减去右边数值的最大差值，例如下面的数组，组大差值为11-1 为10,这个题目解法挺多，自己想到一个简单的算法，记录下
 * 可以申请一个同样大小的数组b，从右边开始初始化，第i个值为从i到数组后的最小值，这样初始化时候可以用到动态规划法，i与第i+1个比较就可以找到这个值
 * 然后求出a[i]-b[i]的最大值就可以了。其实这个方法不用申请数组b，用一个数值记录当前位置到最后的最小值就可以了，再用一个变量保存最大差值
 * 从后到前遍历一遍，就求出来了代码如下
 */
public class DiffMax {

    public static void main(String[] args){
        int[] a = {5, 11, 3, 10, 6, 1, 100, 4, 3, 2};
        System.out.println(maxDiff1(a));
        System.out.println(maxDiff2(a));
    }

    private static int maxDiff1(int[] array)
    {
        int min = array[array.length-1];
        int maxDiff = 0;
        for(int i = array.length-1; i>=0; i--)
        {
            if(array[i] < min)
                min = array[i];
            if(array[i] - min > maxDiff)
                maxDiff = array[i] - min;
        }
        return maxDiff;
    }

    /**
     * 这个题目还可以转化下，构造一个数组b，b[0] = a[0]-a[1],b[1] = a[1]-a[2]...b[n]=a[n]-a[n+1];
     * b[0]+b[1] = a[0]-a[2]..b[m]+..b[n] = a[m] - a[n+1];这样就转化成了求，数组最大子数组和的问题了
     * 但是经检验貌似并不像上面写的那样。。。
     */
    private static int maxDiff2(int[] array)
    {
        int array2[] = new int[array.length-1];
        for(int i=0; i<array.length-1; i++)
            array2[i] = array[i] - array[i+1];

        int sum = array2[0];
        int begin = 0;
        int end = 0;
        int temp_add = 0;
        for(int i=0; i<array.length-1; i++)
        {
            temp_add += array2[i];
            if(temp_add >= sum)
            {
                sum = temp_add;
                end = i;
            }
        }
        int temp_sub = sum;
        for(int i=0; i<=end; i++)
        {
            temp_sub -= array2[i];
            if(temp_sub > sum)
            {
                sum = temp_sub;
                begin = i;
            }
        }
        return sum;
    }

}
