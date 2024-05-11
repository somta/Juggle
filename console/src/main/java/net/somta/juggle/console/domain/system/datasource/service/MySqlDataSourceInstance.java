package net.somta.juggle.console.domain.system.datasource.service;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import net.somta.core.exception.BizException;
import net.somta.juggle.console.domain.system.datasource.enums.DatasourceErrorEnum;
import net.somta.juggle.core.model.DataSource;

import java.net.SocketException;
import java.sql.SQLException;

/**
 * @author husong
 * @since 1.2.0
 */
public class MySqlDataSourceInstance implements IDataSourceInstance {
    @Override
    public Object getDataSourceInstance(DataSource dataSource) {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(buildJdbcUrl(dataSource));
        config.setUsername(dataSource.getUserName());
        config.setPassword(dataSource.getPassword());
        config.setMinimumIdle(dataSource.getMinPoolSize());
        config.setMaximumPoolSize(dataSource.getMaxPoolSize());
        config.addDataSourceProperty("readTimeout", dataSource.getQueryTimeout());
        try {
            return new HikariDataSource(config);
        } catch (Exception e){
            e.getMessage();
            throw new BizException(DatasourceErrorEnum.DATASOURCE_CONNECT_ERROR);
        }
    }

    private String buildJdbcUrl(DataSource dataSource) {
        StringBuilder jdbcUrl = new StringBuilder();
        jdbcUrl.append("jdbc:mysql://");
        jdbcUrl.append(dataSource.getAddress());
        jdbcUrl.append(":");
        jdbcUrl.append(dataSource.getPort());
        jdbcUrl.append("/");
        jdbcUrl.append(dataSource.getDatabaseName());
        jdbcUrl.append(dataSource.getConnectExtInfo());
        return jdbcUrl.toString();
    }
}
