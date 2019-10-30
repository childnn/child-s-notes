package com.example.test;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author MiaoOne
 * 2019/8/6 17:37
 * 待测试
 */
@Deprecated
public class Demo3 {
    /**
     * Discription: 笛卡尔乘积算法
     * 把一个List{[1,2],[A,B],[a,b]} 转化成
     * List{[1,A,a],[1,A,b],[1,B,a],[1,B,b],[2,A,a],[2,A,b],[2,B,a],[2,B,b]} 数组输出
     *
     * @param dimensionValue 原List
     * @param result 通过乘积转化后的数组
     * @param layer 中间参数
     * @param currentList 中间参数
     */
    public <T> void descartes(List<List<T>> dimensionValue, List<List<T>> result, int layer, List<T> currentList) {
        if (layer < dimensionValue.size() - 1) {
            if (dimensionValue.get(layer).size() == 0) {
                descartes(dimensionValue, result, layer + 1, currentList);
            } else {
                for (int i = 0; i < dimensionValue.get(layer).size(); i++) {
                    List<T> list = new ArrayList<T>(currentList);
                    list.add(dimensionValue.get(layer).get(i));
                    descartes(dimensionValue, result, layer + 1, list);
                }
            }
        } else if (layer == dimensionValue.size() - 1) {
            if (dimensionValue.get(layer).size() == 0) {
                result.add(currentList);
            } else {
                for (int i = 0; i < dimensionValue.get(layer).size(); i++) {
                    List<T> list = new ArrayList<>(currentList);
                    list.add(dimensionValue.get(layer).get(i));
                    result.add(list);
                }
            }
        }
    }

    /**
     *  例：将 modelList 按type分组位后得到 （取ID为例）
     *  [type = 1] = [1, 2, 3],
     *  [type = 2] = [4],
     *  [type = 3] = [5, 6, 7]
     *  求笛卡尔积的结果应为[1, 4, 5] ,[1, 4, 6], [1, 4, 7],[2, 4, 5],[2, 4, 6],[2, 4, 7],[3, 4, 5],[3, 4, 6],[3, 4, 7]
     */
    @Test
    public void listToDescartes(){
        List<Model> modelList = new ArrayList<>();
        modelList.add(new Model(1,"a", "zhuoli", 1 ));
        modelList.add(new Model(2,"b", "zhuoli", 1));
        modelList.add(new Model(3,"c", "Alice", 1));

        modelList.add(new Model(4,"d", "zhuoli", 2));

        modelList.add(new Model(5,"e", "zhuoli", 3));
        modelList.add(new Model(6,"f", "Michael", 3));
        modelList.add(new Model(7,"g", "Michael", 3));

        // 按指定字段（type）分组
        Map<Integer, List<Model>> modelMap = modelList.stream().collect(Collectors.groupingBy(Model::getType));
        Collection<List<Model>> mapValues = modelMap.values();
        List<List<Model>> dimensionValue = new ArrayList<>(mapValues);	// 原List

        List<List<Model>> result = new ArrayList<>(); // 返回集合
        descartes(dimensionValue, result, 0, new ArrayList<Model>());


        for (List<Model> models : result) {
            List<Integer> resList = new ArrayList<>();
            for (Model model : models) {
                resList.add(model.getId());
            }
            System.out.println(resList.toString());
        }


    }

    @Data
    @AllArgsConstructor
    @ToString
    private class Model {
        private Integer id;

        private String name;

        private String author;

        private Integer type;
    }
}
