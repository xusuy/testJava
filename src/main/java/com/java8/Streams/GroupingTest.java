package com.java8.Streams;

import com.domain.FlowNodeExecutor;
import com.domain.ZtreeModel;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author xsy
 * @create 2019-08-13 17:49
 * @desc 测试分组
 **/
public class GroupingTest {

    private static List<FlowNodeExecutor> flowNodeExecutorList = new ArrayList<>();

    static {
        ZtreeModel ztreeModel = new ZtreeModel();
        ztreeModel.setId("1");
        ztreeModel.setName("name1");
        FlowNodeExecutor flowNodeExecutor = new FlowNodeExecutor();
        flowNodeExecutor.setId("11");
        flowNodeExecutor.setNodeId("123123123");
        List<ZtreeModel> executorList = new ArrayList<>();
        executorList.add(ztreeModel);
        flowNodeExecutor.setExecutorList(executorList);
        flowNodeExecutorList.add(flowNodeExecutor);

        ztreeModel = new ZtreeModel();
        ztreeModel.setId("2");
        ztreeModel.setName("name2");
        flowNodeExecutor = new FlowNodeExecutor();
        flowNodeExecutor.setId("22");
        flowNodeExecutor.setNodeId("456456456");
        executorList = new ArrayList<>();
        executorList.add(ztreeModel);
        flowNodeExecutor.setExecutorList(executorList);
        flowNodeExecutorList.add(flowNodeExecutor);
    }

    @Test
    public void test1() {
        //分组
        Map<String, Set<List<ZtreeModel>>> nodeExecutorMap = flowNodeExecutorList.stream().collect(
                Collectors.groupingBy(FlowNodeExecutor::getNodeId,
                        Collectors.mapping(FlowNodeExecutor::getExecutorList,
                                Collectors.toSet())));
        for (Map.Entry<String, Set<List<ZtreeModel>>> entry : nodeExecutorMap.entrySet()) {
            System.out.println("key=" + entry.getKey() + "；value=" + entry.getValue());
        }
        System.out.println("========");
        //排序
        Map<String, Set<List<ZtreeModel>>> nodeExecutorSortMap = new LinkedHashMap<>();
        nodeExecutorMap.entrySet().stream().sorted(Map.Entry.comparingByKey()).
                forEachOrdered(x -> nodeExecutorSortMap.put(x.getKey(), x.getValue()));
        nodeExecutorSortMap.entrySet().forEach(e -> System.out.println("key=" + e.getKey() + "；value=" + e.getValue()));

        System.out.println("========");
        Map<String, List<FlowNodeExecutor>> collect = flowNodeExecutorList.stream().collect(
                Collectors.groupingBy(FlowNodeExecutor::getNodeId, Collectors.toList()));
        collect.entrySet().forEach(e -> System.out.println("key=" + e.getKey() + "；value=" + e.getValue()));
    }
}
