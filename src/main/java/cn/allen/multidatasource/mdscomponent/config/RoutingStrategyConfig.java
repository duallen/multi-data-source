package cn.allen.multidatasource.mdscomponent.config;

import cn.allen.multidatasource.mdscomponent.core.Routing;
import cn.allen.multidatasource.mdscomponent.core.RoutingDsAndTableStrategy;
import cn.allen.multidatasource.mdscomponent.core.RoutingDsStrategy;
import cn.allen.multidatasource.mdscomponent.core.RoutingTbStrategy;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author allen
 * @date 2020-04-21
 */
@Configuration
public class RoutingStrategyConfig {

    @Bean
    @ConditionalOnProperty(prefix = "allen.dsroutingset",name = "routingStrategy",havingValue = "ROUTING_DS_TABLE_STRATEGY")
    public Routing routingDsAndTbStrategy(){
        return new RoutingDsAndTableStrategy();
    }

    @Bean
    @ConditionalOnProperty(prefix = "allen.dsroutingset",name = "routingStrategy",havingValue = "ROUTING_DS_STRATEGY")
    public Routing routingDsStrategy(){
        return new RoutingDsStrategy();
    }

    @Bean
    @ConditionalOnProperty(prefix = "allen.dsroutingset",name = "routingStrategy",havingValue = "ROUTING_TABLE_STRATEGY")
    public Routing routingTableStrategy(){
        return new RoutingTbStrategy();
    }
}
