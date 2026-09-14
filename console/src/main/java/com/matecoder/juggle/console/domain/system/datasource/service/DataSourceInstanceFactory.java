package com.matecoder.juggle.console.domain.system.datasource.service;

import com.matecoder.juggle.core.model.DataSource;

/**
 * @author husong
 * @since 1.2.0
 */
public class DataSourceInstanceFactory {

    public static Object getDataSourceInstance(DataSource dataSource){
        IDataSourceInstance dataSourceInstance = null;
        if("MYSQL".equals(dataSource.getDataSourceType())){
            dataSourceInstance = new  MySqlDataSourceInstance();
        }
        if(dataSourceInstance != null){
            return dataSourceInstance.getDataSourceInstance(dataSource);
        } else {
           throw new RuntimeException("数据源为空或连接失败");
        }
    }


}
