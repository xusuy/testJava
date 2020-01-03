package com.java8.Streams;

import com.domain.FlowNodeExecutor;
import com.domain.ZtreeModel;
import com.util.MathTimeUtil;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author xsy
 * @create 2019-08-16 9:37
 * @desc
 **/
public class GroupingDateTest {

    private static List<FlowNodeExecutor> flowNodeExecutorList = new ArrayList<>();

    static {
        Date nowDate = new Date();
        Date d1 = MathTimeUtil.beforeDate(nowDate, 1, 0);
        Date d2 = MathTimeUtil.laterDate(nowDate, 1);
        Date d3 = MathTimeUtil.beforeDate(d2, 1, 0);
        FlowNodeExecutor flowNodeExecutor = new FlowNodeExecutor();
        flowNodeExecutor.setId("11");
        flowNodeExecutor.setNodeId("456456456");
        flowNodeExecutor.setCreateTime(d1);
        flowNodeExecutorList.add(flowNodeExecutor);

        flowNodeExecutor = new FlowNodeExecutor();
        flowNodeExecutor.setId("22");
        flowNodeExecutor.setNodeId("456456456");
        flowNodeExecutor.setCreateTime(d3);
        flowNodeExecutorList.add(flowNodeExecutor);

        flowNodeExecutor = new FlowNodeExecutor();
        flowNodeExecutor.setId("33");
        flowNodeExecutor.setNodeId("123123123");
        flowNodeExecutor.setCreateTime(nowDate);
        flowNodeExecutorList.add(flowNodeExecutor);

        flowNodeExecutor = new FlowNodeExecutor();
        flowNodeExecutor.setId("55");
        flowNodeExecutor.setNodeId("123123123");
        flowNodeExecutor.setCreateTime(d2);
        flowNodeExecutorList.add(flowNodeExecutor);
    }

    @Test
    public void test1() {
        //分组
        Map<String, Set<List<ZtreeModel>>> nodeExecutorMap = flowNodeExecutorList.stream().collect(
                Collectors.groupingBy(FlowNodeExecutor::getNodeId,
                        Collectors.mapping(FlowNodeExecutor::getExecutorList,
                                Collectors.toSet())));
        nodeExecutorMap.entrySet().forEach(e -> System.out.println("key=" + e.getKey() + "；value=" + e.getValue()));
        //排序
        Map<String, Set<List<ZtreeModel>>> nodeExecutorSortMap = new LinkedHashMap<>();
        nodeExecutorMap.entrySet().stream().sorted(Map.Entry.comparingByKey()).
                forEachOrdered(x -> nodeExecutorSortMap.put(x.getKey(), x.getValue()));
        nodeExecutorSortMap.entrySet().forEach(e -> System.out.println("key=" + e.getKey() + "；value=" + e.getValue()));

        //按日期分组
        Map<String, List<FlowNodeExecutor>> flowNodeMap = flowNodeExecutorList.stream().collect(Collectors.groupingBy(
                FlowNodeExecutor::getCreateTimeDate));
        for (Map.Entry<String, List<FlowNodeExecutor>> entry : flowNodeMap.entrySet()) {
            System.out.println("key=" + entry.getKey() + "；value=" + entry.getValue());
        }
        //排过序的
        List<FlowNodeExecutor> flowNodeSortMap = new ArrayList<>();
        for (Map.Entry<String, List<FlowNodeExecutor>> flowEntry : flowNodeMap.entrySet()) {
            int i = 0;
            for (FlowNodeExecutor flow : flowEntry.getValue()) {
                flow.setCreateDateItem(i > 0 ? flowEntry.getKey() + "_" + i : flowEntry.getKey());
                flowNodeSortMap.add(flow);
                i++;
            }
        }
        System.out.println("flowNodeSortMap====" + flowNodeSortMap);
    }
}
