package cn.allen.multidatasource.mdscomponent.support;

import cn.allen.multidatasource.mdscomponent.constant.RoutingConstant;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Map;

/**
 * @author allen
 * @date 2020-04-21
 */
@ConfigurationProperties(prefix = "allen.dsroutingset")
@Data
public class DsRoutingSetProperties {

    /**
     * 数据库个数 默认为1
     */
    private Integer dataSourceNum = 1;

    /**
     * 每个数据库表的个数 默认为1
     */
    private Integer tableNum = 1;

    /**
     * 路由字段 必填
     */
    private String routingField;

    /**
     * 已有数据落入对应库的关系
     */
    private Map<Integer,String> dataSourceKeysMapping;

    /**
     * 后缀连接符
     */
    private String tableSuffixConnect = "_";

    /**
     * 后缀格式
     */
    private String tableSuffixStyle = "%04d";

    /**
     * 默认分库分表策略 多库多表
     */
    private String routingStrategy = RoutingConstant.ROUTING_DS_TABLE_STRATEGY;
}
