package net.somta.juggle.console.domain.system.datasource.service;

import net.somta.juggle.core.model.DataSource;

/**
 * @author husong
 * @since 1.2.0
 */
public class DataSourceInstanceFactory {

    public static Object getDataSourceInstance(DataSource dataSource){
        IDataSourceInstance dataSourceInstance = null;
        if("MySql".equals(dataSource.getDataSourceType())){
            dataSourceInstance = new  MySqlDataSourceInstance();
        }
        if(dataSourceInstance != null){
            return dataSourceInstance.getDataSourceInstance(dataSource);
        }
        return null;
    }


}
