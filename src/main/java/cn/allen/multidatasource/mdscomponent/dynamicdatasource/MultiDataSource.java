package cn.allen.multidatasource.mdscomponent.dynamicdatasource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * @author allen
 * @date 2020-04-21
 */
public class MultiDataSource extends AbstractRoutingDataSource {
    @Override
    protected Object determineCurrentLookupKey() {
        return MultiDataSourceHolder.getDataSourceKey();
    }
}
