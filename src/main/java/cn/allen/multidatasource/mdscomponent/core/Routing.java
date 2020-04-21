package cn.allen.multidatasource.mdscomponent.core;

/**
 * 路由规则接口
 * @author allen
 * @date 2020-04-21
 */
public interface Routing {
    /**
     * 根据规则计算出数据源key
     * @param routingField
     * @return
     */
    String calDataSourceKey(String routingField);

    /**
     * 因为路由字段不一定是integer想要对非int值取模 首先取路由字段的hash值
     * @param routingField
     * @return
     */
    Integer getRoutingFieldHashCode(String routingField);

    /**
     * 计算一个库所在表的索引值
     * @param routingField
     * @return
     */
    String calTableKey(String routingField);

    /**
     * 生成表后缀
     * @param tableIndex
     * @return
     */
    String genFormatTableSuffix(Integer tableIndex);
}
