package cn.allen.multidatasource.mdscomponent.config;

import cn.allen.multidatasource.mdscomponent.dynamicdatasource.MultiDataSource;
import cn.allen.multidatasource.mdscomponent.support.DruidProperties;
import cn.allen.multidatasource.mdscomponent.support.DsRoutingSetProperties;
import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author allen
 * @date 2020-04-21
 */
@EnableConfigurationProperties({DsRoutingSetProperties.class, DruidProperties.class})
@Configuration
public class DataSourceConfig {
    @Autowired
    private DsRoutingSetProperties dsRoutingSetProperties;

    @Autowired
    private DruidProperties druidProperties;

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.druid00")
    public DataSource dataSource00(){
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUsername(druidProperties.getDruid00username());
        dataSource.setPassword(druidProperties.getDruid00password());
        dataSource.setUrl(druidProperties.getDruid00jdbcUrl());
        dataSource.setDriverClassName(druidProperties.getDruid00driverClass());

        return dataSource;
    }

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.druid01")
    public DataSource dataSource01(){
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUsername(druidProperties.getDruid01username());
        dataSource.setPassword(druidProperties.getDruid01password());
        dataSource.setUrl(druidProperties.getDruid01jdbcUrl());
        dataSource.setDriverClassName(druidProperties.getDruid01driverClass());

        return dataSource;
    }

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.druid02")
    public DataSource dataSource02(){
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUsername(druidProperties.getDruid02username());
        dataSource.setPassword(druidProperties.getDruid02password());
        dataSource.setUrl(druidProperties.getDruid02jdbcUrl());
        dataSource.setDriverClassName(druidProperties.getDruid02driverClass());

        return dataSource;
    }

    // 注入我们自己的datasource
    @Bean("multiDataSource")
    public MultiDataSource multiDataSource(){
        MultiDataSource multiDataSource = new MultiDataSource();

        Map<Object,Object> targetDataSources = new HashMap<>();
        targetDataSources.put("dataSource00",dataSource00());
        targetDataSources.put("dataSource01",dataSource01());
        targetDataSources.put("dataSource02",dataSource02());

        multiDataSource.setTargetDataSources(targetDataSources);

        multiDataSource.setDefaultTargetDataSource(dataSource00());

        Map<Integer,String> setMappings = new HashMap<>();
        setMappings.put(0,"dataSource00");
        setMappings.put(1,"dataSource01");
        setMappings.put(2,"dataSource02");
        dsRoutingSetProperties.setDataSourceKeysMapping(setMappings);

        return multiDataSource;
    }

    @Bean
    public SqlSessionFactory sqlSessionFactory(@Qualifier("multiDataSource") MultiDataSource multiDataSource) throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(multiDataSource);
        sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:/mybatis/mapper/*.xml"));
        return sqlSessionFactoryBean.getObject();
    }

    @Bean
    public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

    @Bean
    public DataSourceTransactionManager transactionManager(@Qualifier("multiDataSource") MultiDataSource multiDataSource){
        return new DataSourceTransactionManager(multiDataSource);
    }

}
