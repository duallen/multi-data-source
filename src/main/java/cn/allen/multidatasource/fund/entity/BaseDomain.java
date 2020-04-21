package cn.allen.multidatasource.fund.entity;

import cn.allen.multidatasource.mdscomponent.dynamicdatasource.MultiDataSourceHolder;

/**
 * @author allen
 * @date 2020-04-20
 */
public class BaseDomain {
    private String tableSuffix;

    public String getTableSuffix(){
        this.tableSuffix = MultiDataSourceHolder.getTableIndex();
        return tableSuffix;
    }

    public void setTableSuffix(String tableSuffix){
        this.tableSuffix = tableSuffix;
    }
}
