package cn.allen.multidatasource.mdscomponent.core;

import cn.allen.multidatasource.mdscomponent.constant.RoutingConstant;
import cn.allen.multidatasource.mdscomponent.exception.FormatTableSuffixException;
import cn.allen.multidatasource.mdscomponent.exception.LoadRoutingStrategyUnMatchException;
import cn.allen.multidatasource.mdscomponent.support.DsRoutingSetProperties;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

/**
 * 路由规则抽象类
 * @author allen
 * @date 2020-04-21
 */
@Slf4j
@EnableConfigurationProperties(value = {DsRoutingSetProperties.class})
@Data
public abstract class AbstractRouting implements Routing, InitializingBean {

    @Autowired
    private DsRoutingSetProperties dsRoutingSetProperties;

    public Integer getRoutingFieldHashCode(String routingField) {
        return Math.abs(routingField.hashCode());
    }

    public String genFormatTableSuffix(Integer tableIndex) {
        StringBuilder stringBuffer = new StringBuilder(dsRoutingSetProperties.getTableSuffixConnect());

        try {
            stringBuffer.append(String.format(dsRoutingSetProperties.getTableSuffixStyle(),tableIndex));
        } catch (Exception e) {
            log.error("格式化表后缀异常:{}",dsRoutingSetProperties.getTableSuffixStyle());
            throw new FormatTableSuffixException();
        }
        return stringBuffer.toString();
    }

    public void afterPropertiesSet() throws LoadRoutingStrategyUnMatchException {
        switch (dsRoutingSetProperties.getRoutingStrategy()) {
            case RoutingConstant.ROUTING_DS_TABLE_STRATEGY:
                checkRoutingDsTableStrategyConfig();
                break;
            case RoutingConstant.ROUTING_DS_STRATEGY:
                checkRoutingDsStategyConfig();
                break;
            case RoutingConstant.ROUTING_TABLE_STRATEGY:
                checkRoutingTableStategyConfig();
                break;
        }
    }

    /**
     * 检查多库 多表配置
     */
    private void checkRoutingDsTableStrategyConfig() {
        if(dsRoutingSetProperties.getTableNum()<=1 ||dsRoutingSetProperties.getDataSourceNum()<=1){
            log.error("你的配置项routingStrategy:{}是多库多表配置,数据库个数>1," +
                            "每一个库中表的个数必须>1,您的配置:数据库个数:{},表的个数:{}",dsRoutingSetProperties.getRoutingStrategy(),
                    dsRoutingSetProperties.getDataSourceNum(),dsRoutingSetProperties.getTableNum());
            throw new LoadRoutingStrategyUnMatchException();
        }
    }

    /**
     * 检查多库一表的路由配置项
     */
    private void checkRoutingDsStategyConfig() {
        if(dsRoutingSetProperties.getTableNum()!=1 ||dsRoutingSetProperties.getDataSourceNum()<=1){
            log.error("你的配置项routingStrategy:{}是多库一表配置,数据库个数>1," +
                            "每一个库中表的个数必须=1,您的配置:数据库个数:{},表的个数:{}",dsRoutingSetProperties.getRoutingStrategy(),
                    dsRoutingSetProperties.getDataSourceNum(),dsRoutingSetProperties.getTableNum());
            throw new LoadRoutingStrategyUnMatchException();
        }
    }

    /**
     * 检查一库多表的路由配置项
     */
    private void checkRoutingTableStategyConfig() {
        if(dsRoutingSetProperties.getTableNum()<=1 ||dsRoutingSetProperties.getDataSourceNum()!=1){
            log.error("你的配置项routingStrategy:{}是一库多表配置,数据库个数=1," +
                            "每一个库中表的个数必须>1,您的配置:数据库个数:{},表的个数:{}",dsRoutingSetProperties.getRoutingStrategy(),
                    dsRoutingSetProperties.getDataSourceNum(),dsRoutingSetProperties.getTableNum());
            throw new LoadRoutingStrategyUnMatchException();
        }
    }
}
