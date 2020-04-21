package cn.allen.multidatasource.mdscomponent.dynamicdatasource;

/**
 * @author allen
 * @date 2020-04-21
 */
public class MultiDataSourceHolder {
    private static final ThreadLocal<String> dataSourceHolder = new ThreadLocal<>();

    private static final ThreadLocal<String> tableIndexHolder = new ThreadLocal<>();

    public static void setDataSourceHolder(String dsKey){
        dataSourceHolder.set(dsKey);
    }

    public static String getDataSourceKey(){
        return dataSourceHolder.get();
    }

    public static void clearDataSourceKey(){
        dataSourceHolder.remove();
    }

    public static void setTableIndexHolder(String tableIndex){
        tableIndexHolder.set(tableIndex);
    }

    public static String getTableIndex(){
        return tableIndexHolder.get();
    }

    public static void clearTableIndex(){
        tableIndexHolder.remove();
    }

}
