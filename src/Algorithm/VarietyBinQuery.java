package Algorithm;

/**
 * Created by hidden on 16/2/21.
 * 循环有序数组的二分查找(循环有序数组是将一个有序数组切成两段，并交换位置得到引用块内容)
 * 一个按顺序排好的数组，从某一个位置开始将后面的数移动到前面。
 * 例如，"123456789" -> 从第7个元素开始移动到最前面，"789123456", 在这种情况下查找一个数。
 */
public class VarietyBinQuery {

    private static int m = 0;

    private static int search(int[] a, int n){
        int beginPos = 0;
        int endPos = a.length-1;
        if (a[beginPos] >= a[endPos]) {
            while (beginPos <= endPos) {
                int middlePos = beginPos + (endPos - beginPos)/2;
                int middleValue = a[middlePos];

                //说明这是一个在增加的循环有序数组
                if (middleValue >= a[beginPos]) {
                    //左侧单调递增
                    if (n == a[middlePos]) {
                        return middlePos;
                    }
                    else if (n < a[middlePos] && n >= a[beginPos]){
                        //一定是在左侧查找
                        endPos = middlePos - 1;
                    }
                    else{
                        //在右侧查找
                        beginPos = middlePos + 1;
                    }
                }
                else{
                    //右侧单调递增，同理
                    if (n == a[middlePos]) {
                        return middlePos;
                    }
                    else if (n > a[middlePos] && n <= a[endPos]){
                        //一定是在右侧查找
                        beginPos = middlePos + 1;
                    }
                    else{
                        //在左侧查找
                        endPos = middlePos - 1;
                    }
                }
            }
            //没找到元素
            return -1;
        }
        else{
            while (beginPos <= endPos) {
                int middlePos = beginPos + (endPos - beginPos)/2;
                int middleValue = a[middlePos];

                //说明这是一个在减少的循环有序数组
                if (middleValue >= a[beginPos]) {
                    //右侧单调递减
                    if (n == a[middlePos]) {
                        return middlePos;
                    }
                    else if (n < a[middlePos] && n >= a[endPos]){
                        //一定是在右侧查找
                        beginPos = middlePos + 1;
                    }
                    else{
                        //在右侧查找
                        endPos = middlePos - 1;
                    }
                }
                else{
                    //左侧单调递减，同理
                    if (n == a[middlePos]) {
                        return middlePos;
                    }
                    else if (n <= a[beginPos] && n > a[middlePos]){
                        //一定是在左侧查找
                        endPos = middlePos - 1;
                    }
                    else{
                        //在左侧查找
                        beginPos = middlePos + 1;
                    }
                }
            }
            //没找到元素
            return -1;
        }
    }

    public static void main(String[] args) {
        int[] a = {7,8,9,1,2,3,4,5,6};
        System.out.println(search(a, 3));
    }
}
