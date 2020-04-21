package cn.allen.multidatasource.mdscomponent.enumuration;

/**
 * Created by smlz on 2019/4/17.
 */
public enum RoutingStrategyEnum {

    /**
     * 多库 多表策略
     */
    ROUTING_DS_TABLE_STRATEGY,

    /**
     * 多库 一表策略
     */
    ROUTING_DS_STRATEGY,

    /**
     * 一库多表策略
     */
    ROUTING_TABLE_STRATEGY;
}
