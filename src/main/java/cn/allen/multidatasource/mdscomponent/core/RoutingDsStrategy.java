package cn.allen.multidatasource.mdscomponent.core;

import cn.allen.multidatasource.mdscomponent.dynamicdatasource.MultiDataSourceHolder;
import lombok.extern.slf4j.Slf4j;

/**
 * 多库 一表策略
 */
@Slf4j
public class RoutingDsStrategy extends AbstractRouting {

    private static final String  ROUTING_DS_STRATEGY_TABLE_SUFFIX = "_0000";
    @Override
    public String calDataSourceKey(String routingFiled) {

        String dataSourceKey = null;

        Integer routingFiledHashCode =  getRoutingFieldHashCode(routingFiled);
        //定位库的索引值
        Integer dsIndex = routingFiledHashCode % getDsRoutingSetProperties().getDataSourceNum();

        //根据库的索引值定位 数据源的key
        dataSourceKey = getDsRoutingSetProperties().getDataSourceKeysMapping().get(dsIndex);

        //放入线程变量
        MultiDataSourceHolder.setDataSourceHolder(dataSourceKey);

        log.info("根据路由字段:{},值:{},计算出数据库索引值:{},数据源key的值:{}",getDsRoutingSetProperties().getRoutingField(),routingFiled,dsIndex,dataSourceKey);
        return dataSourceKey;
    }

    @Override
    public String calTableKey(String routingFiled) {
        MultiDataSourceHolder.setTableIndexHolder(ROUTING_DS_STRATEGY_TABLE_SUFFIX);
        return ROUTING_DS_STRATEGY_TABLE_SUFFIX;
    }
}
