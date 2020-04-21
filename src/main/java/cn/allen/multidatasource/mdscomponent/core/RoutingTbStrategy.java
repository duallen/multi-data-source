package cn.allen.multidatasource.mdscomponent.core;

import cn.allen.multidatasource.mdscomponent.dynamicdatasource.MultiDataSourceHolder;

/**
 * 一库多表策略
 * Created by smlz on 2019/4/17.
 */
public class RoutingTbStrategy extends AbstractRouting {

    private static final String  ROUTING_DS_STRATEGY_DS_KEY = "dataSource00";


    @Override
    public String calDataSourceKey(String routingFiled) {
        MultiDataSourceHolder.setDataSourceHolder(ROUTING_DS_STRATEGY_DS_KEY);
        return ROUTING_DS_STRATEGY_DS_KEY;
    }

    @Override
    public String calTableKey(String routingFiled) {
        //前置检查
        Integer routingFiledHashCode =  getRoutingFieldHashCode(routingFiled);

        Integer tbIndex = routingFiledHashCode % getDsRoutingSetProperties().getTableNum();

        String tableSuffix = genFormatTableSuffix(tbIndex);

        MultiDataSourceHolder.setTableIndexHolder(tableSuffix);

        return tableSuffix;
    }
}
