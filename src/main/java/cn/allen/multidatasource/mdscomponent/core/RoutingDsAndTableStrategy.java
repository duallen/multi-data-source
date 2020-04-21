package cn.allen.multidatasource.mdscomponent.core;

import cn.allen.multidatasource.mdscomponent.dynamicdatasource.MultiDataSource;
import cn.allen.multidatasource.mdscomponent.dynamicdatasource.MultiDataSourceHolder;
import lombok.extern.slf4j.Slf4j;

/**
 * @author allen
 * @date 2020-04-21
 */
@Slf4j
public class RoutingDsAndTableStrategy extends AbstractRouting {
    @Override
    public String calDataSourceKey(String routingField) {
        String dataSourceKey;

        Integer routingFieldHashCode = getRoutingFieldHashCode(routingField);

        //定位库的索引值
        Integer dsIndex = routingFieldHashCode % getDsRoutingSetProperties().getDataSourceNum();

        // 根据库的索引值定位数据源的key
        dataSourceKey = getDsRoutingSetProperties().getDataSourceKeysMapping().get(dsIndex);

        // 放入线程变量
        MultiDataSourceHolder.setDataSourceHolder(dataSourceKey);

        log.info("根据路由字段:{},值:{},计算出数据库索引值:{},数据源key的值:{}",getDsRoutingSetProperties().getRoutingField(),routingField,dsIndex,dataSourceKey);

        return dataSourceKey;
    }

    @Override
    public String calTableKey(String routingField) {

        Integer routingFieldHashCode = getRoutingFieldHashCode(routingField);

        Integer tableIndex = routingFieldHashCode % getDsRoutingSetProperties().getTableNum();

        String tableSuffix = genFormatTableSuffix(tableIndex);

        MultiDataSourceHolder.setTableIndexHolder(tableSuffix);

        return tableSuffix;
    }
}
