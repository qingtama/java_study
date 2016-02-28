package Algorithm;

public class ArrayOperation {

    //二分查找算法
    public static int branchSearch(int[] array, int searchNum) {
        if (array == null)
            throw new NullPointerException("Null Referrence");
        if (array.length == 0)
            throw new IllegalArgumentException("Array Length is Zero");
        int low = 0, high = array.length;
        int middle = (high + low) / 2;
        int index = -1;
        if (searchNum < array[0] || searchNum > array[array.length - 1])
            return index;
        while (middle >= 0) {
            if (array[middle] == searchNum) {
                index = middle;
                break;
            }
            if (searchNum > array[middle]) {
                low = middle;
            } else {
                high = middle;
            }
            middle = (low + high) / 2;
        }

        return index;

    }

    // 快速排序

    public static void quickSort(int a[], int left, int right) {
        int i, j, temp;
        i = left;
        j = right;
        if (left > right)
            return;
        temp = a[left];
        while (i != j)/* 找到最终位置 */
        {
            while (a[j] >= temp && j > i)
                j--;
            if (j > i)
                a[i++] = a[j];
            while (a[i] <= temp && j > i)
                i++;
            if (j > i)
                a[j--] = a[i];

        }
        a[i] = temp;
        quickSort(a, left, i - 1);/* 递归左边 */
        quickSort(a, i + 1, right);/* 递归右边 */
    }

    // 插入排序
    // 特点：用temp保存将要排序的临时值，然后把大的值插入到这个位置。
    public static int[] insert_Sort(int[] array) {
        int i, j, temp;
        for (i = 1; i < array.length; i++) {
            for (j = i, temp = array[i]; j > 0 && temp < array[j - 1]; j--)
                array[j] = array[j - 1];
            array[j] = temp;
        }
        return array;
    }

    // 冒泡排序
    // 特点：从第一个元素开始，如果需要交换，就一直冒泡到底，如果不需要交换，就从下一个元素开始比较
    public void bubble_Sort(int[] array, int size) {
        int i, j, temp;
        for (i = size - 1; i > 1; i--)
            for (j = 0; j < i; j++)
                if (array[j] > array[j + 1]) {
                    temp = array[j + 1];
                    array[j + 1] = array[j];
                    array[j] = temp;
                }
    }

    // 交换排序
    // 特点：始终是第一个元素与其他元素一一比较，交互后，继续用第一个元素与后面元素一一比较，重复下去。
    public int[] change_Sort(int[] array, int size) {
        int i, j, temp;
        for (i = 0; i < size; i++)
            for (j = i + 1; j < size; j++)
                if (array[i] > array[j]) {
                    temp = array[j];
                    array[j] = array[i];
                    array[i] = temp;
                }
        return array;
    }

    // 选择排序一（便于区分：咱就叫：选择最小值排序法）
    // 特点：分有序区（第一个元素）和无序区（除第一元素外的元素），从无序区找出最小的元素移动到有序区
    public void SelectSort(int[] array) {
        int i, j, k;// 分别为有序区，无序区，无序区最小元素指针
        for (i = 0; i < array.length; i++) {
            k = i;
            for (j = i + 1; j < array.length; j++) {
                if (array[j] < array[k])
                    k = j;
            }
            if (k != i)// 若发现最小元素，则移动到有序区
            {
                int temp = array[k];
                array[k] = array[i];
                array[i] = array[temp];
            }
        }
    }

    // 选择排序二
    public int[] select_Sort(int[] array, int size) {
        int i, j, temp, pos;
        for (i = 0; i < size; i++) {
            for (j = i + 1, temp = array[i], pos = i; j < size; j++)
                if (temp > array[j]) {
                    temp = array[j];
                    pos = j;
                }
            array[pos] = array[i];
            array[i] = temp;
        }

        return array;
    }

    //希尔排序
    //    属于插入类排序,是将整个无序列分割成若干小的子序列分别进行插入排序
    //  排序过程：先取一个正整数d1<n，把所有序号相隔d1的数组元素放一组，组内进行直接插入排序；
    //  然后取d2<d1，重复上述分组和排序操作；直至di=1，即所有记录放进一个组中排序为止
    public static void ShellSort(int[] array) {
        int length = array.length;
        for (int h = length / 2; h > 0; h = h / 2) {
            // here is insert sort
            for (int i = h; i < length; i++) {
                int temp = array[i];
                if (temp < array[i - h]) {
                    for (int j = 0; j < i; j += h) {
                        if (temp < array[j]) {
                            temp = array[j];
                            array[j] = array[i];
                            array[i] = temp;
                        }
                    }
                }
            }
        }
    }
}