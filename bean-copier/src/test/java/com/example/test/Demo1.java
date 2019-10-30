package com.example.test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author MiaoOne
 * 2019/8/6 15:16
 * 多个数组的数据排列组合: 笛卡尔积
 */
public class Demo1 {
    /**
     * @param dataList   数据源: 包含了所有数组的集合
     * @param index 从 dataList 的第几号索引开始排列组合
     * @param resultList 存放已经排列好的数组的集合
     * @return resultList
     */
    private static List<Object[]> combination(List<Object[]> dataList, int index, List<Object[]> resultList) {
        if (index == dataList.size()) {
            return resultList;
        }

        // 构造 存放数组的新的集合
        List<Object[]> resultList0 = new ArrayList<>();

        if (index == 0) {
            Object[] sourceArr = dataList.get(0);
            for (Object obj : sourceArr) {
                // 把 数据源的 0 号索引(第一个 数组)的每一个元素放入新的数组, 并放入集合
                resultList0.add(new Object[]{obj});
            }
        } else {
            // 获取 数据源 指定索引的 元素(数组)
            Object[] sourceArr = dataList.get(index);

            // 将 resultList 的每一个元素(数组), 与 待拼接数组的每一个元素 排列组合
            // 这里的 resultList 就是上一次 存放了每一个数组的集合
            // 获取 resultList 中的每一个组合好的数组
            for (Object[] resultArr : resultList) {
                // 获取未参与组合的数组中每个元素, 组合到已经组合过的数组之后
                for (Object obj : sourceArr) {
                    //复制数组并扩充新元素
                    // resultArr 长度加 1, 往后追加一个元素
                    Object[] objArrCopy = new Object[resultArr.length + 1];
                    // 把 resultArr 的每一个元素复制到 新数组(长度加一)
                    System.arraycopy(resultArr, 0, objArrCopy, 0, resultArr.length);
                    // 将 数据源的元素赋值给 新数组
                    objArrCopy[objArrCopy.length - 1] = obj;

                    //追加到结果集
                    resultList0.add(objArrCopy);
                }
            }
        }
        // 递归
        return combination(dataList, ++index, resultList0);
    }

    public static void main(String[] args) {
        Object[] arr1 = new Object[]{"A1", "A2", "A3"};
        Object[] arr2 = new Object[]{"B1", "B2", "B3", "B4"};
        Object[] arr3 = new Object[]{"C1", "C2", "C3", "C4", "C5"};

        List<Object[]> dataList = new ArrayList<>();
        dataList.add(arr1);
        dataList.add(arr2);
        dataList.add(arr3);
        List<Object[]> resultList = combination(dataList, 0, null);

        //打印组合结果
        for (int i = 0; i < resultList.size(); i++) {
            Object[] objArr = resultList.get(i);
            System.out.print("\n组合" + (i + 1) + "---");
            for (Object obj : objArr) {
                System.out.print(obj + " ");
            }
        }
    }

}