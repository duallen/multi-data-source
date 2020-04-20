package cn.allen.multidatasource.fund.entity;

/**
 * @author allen
 * @date 2020-04-20
 */
public class BaseDomain {
    private String tableSuffix;

    public String getTableSuffix(){
        // todo: 构造后缀
        return tableSuffix;
    }

    public void setTableSuffix(String tableSuffix){
        this.tableSuffix = tableSuffix;
    }
}
