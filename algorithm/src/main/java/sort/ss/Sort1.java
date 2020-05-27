package sort.ss;

import java.util.Arrays;

import static sort.ss.Sort.arr;
import static sort.ss.Sort.swap;

/**
 * ~~ Talk is cheap. Show me the code. ~~ :-)
 *
 * @author MiaoOne
 * @since 2020/5/7 17:09
 */
public class Sort1 {

    public static void main(String[] args) {
        //bubbleSort0(arr, arr.length);
        //quickSort(arr, 0, arr.length - 1);
        //insertSort(arr);
        heapSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    static void bubbleSort(int[] a, int n) {
        int i, j;
        for (i = n - 1; i > 0; i--) {
            // 将 a[0..i] 中最大的数据放在末尾
            // 第 n - i 次排序, 后 n - i 位完成升序
            for (j = 0; j < i; j++) {
                if (a[j] > a[j + 1]) {
                    swap(a, j, j + 1);
                }
            }
        }
    }

    static void bubbleSort0(int[] a, int n) {
        int i, j;
        for (i = n - 1; i > 0; i--) {
            boolean isSorted = true; // 默认已经排序完成
            // 将 a[0..i] 中最大的数据放在末尾
            // 第 n - i 次排序, 后 n - i 位完成升序
            for (j = 0; j < i; j++) {
                if (a[j] > a[j + 1]) {
                    swap(a, j, j + 1);
                    isSorted = false; // 发生交换后, 说明非有序
                }
            }

            if (isSorted) {
                break;
            }
        }
    }

    /**
     * @param arr
     * @param l   数组左边界
     * @param r   数组右边界
     */
    static void quickSort(int[] arr, int l, int r) {
        if (l < r) {
            int i, j, x;
            i = l;
            j = r;
            x = arr[i];

            while (i < j) {
                while (i < j && arr[j] > x) {
                    j--; // 从右往左找第一个小于 x 的数
                }
                if (i < j) {
                    arr[i++] = arr[j];
                }
                while (i < j && arr[i] < x) {
                    i++; // 从左往右找第一个大于 x 的数
                }
                if (i < j) {
                    arr[j--] = arr[i];
                }
            }
            arr[i] = x;
            quickSort(arr, l, i - 1);
            quickSort(arr, i + 1, r);
        }
    }

    static void insertSort(int[] a) {
        int length = a.length; // 数组长度，将这个提取出来是为了提高速度。
        int insertNum; // 要插入的数
        for (int i = 1; i < length; i++) { // 插入的次数
            insertNum = a[i]; // 要插入的数
            int j = i - 1; // 已经排序好的序列元素个数
            while (j >= 0 && a[j] > insertNum) { // 序列从后到前循环，将大于insertNum的数向后移动一格
                a[j + 1] = a[j]; // 元素移动一格
                j--;
            }
            a[j + 1] = insertNum; // 将需要插入的数放在要插入的位置。
        }
    }

    public static void heapSort(int[] a) {
        System.out.println("开始排序");
        int arrayLength = a.length;
        //循环建堆
        for (int i = 0; i < arrayLength - 1; i++) {
            //建堆
            buildMaxHeap(a, arrayLength - 1 - i);
            //交换堆顶和最后一个元素
            swap(a, 0, arrayLength - 1 - i);
            System.out.println(Arrays.toString(a));
        }
    }

    //对data数组从0到lastIndex建大顶堆
    private static void buildMaxHeap(int[] data, int lastIndex) {
        //从lastIndex处节点（最后一个节点）的父节点开始
        for (int i = (lastIndex - 1) / 2; i >= 0; i--) {
            //k保存正在判断的节点
            int k = i;
            //如果当前k节点的子节点存在
            while (k * 2 + 1 <= lastIndex) {
                //k节点的左子节点的索引
                int biggerIndex = 2 * k + 1;
                //如果biggerIndex小于lastIndex，即biggerIndex+1代表的k节点的右子节点存在
                if (biggerIndex < lastIndex) {
                    //若果右子节点的值较大
                    if (data[biggerIndex] < data[biggerIndex + 1]) {
                        //biggerIndex总是记录较大子节点的索引
                        biggerIndex++;
                    }
                }
                //如果k节点的值小于其较大的子节点的值
                if (data[k] < data[biggerIndex]) {
                    //交换他们
                    swap(data, k, biggerIndex);
                    //将biggerIndex赋予k，开始while循环的下一次循环，重新保证k节点的值大于其左右子节点的值
                    k = biggerIndex;
                } else {
                    break;
                }
            }
        }
    }

}
