package cn.allen.multidatasource.mdscomponent.support;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author allen
 * @date 2020-04-21
 */
@ConfigurationProperties(prefix = "spring.datasource")
@Data
public class DruidProperties {

    private String druid00username;
    private String druid00password;
    private String druid00jdbcUrl;
    private String druid00driverClass;

    private String druid01username;
    private String druid01password;
    private String druid01jdbcUrl;
    private String druid01driverClass;

    private String druid02username;
    private String druid02password;
    private String druid02jdbcUrl;
    private String druid02driverClass;

}
