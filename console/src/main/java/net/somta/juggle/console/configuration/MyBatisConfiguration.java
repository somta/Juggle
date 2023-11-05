package net.somta.juggle.console.configuration;

import com.zaxxer.hikari.HikariDataSource;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;
import java.io.IOException;


/**
 *
 * MyBatis配置类
 *
 * @Author:      husong
 * @Version:     1.0.0
 */
@Configuration
@MapperScan(value = "net.somta.juggle.console.infrastructure.mapper")
public class MyBatisConfiguration {

    @Value("${spring.datasource.driver-class-name:org.h2.Driver}")
    private String driverClassName;

    @Value("${spring.datasource.url:jdbc:h2:file:/data/db_juggle;MODE=MYSQL;DB_CLOSE_DELAY=-1;DATABASE_TO_UPPER=false;IGNORECASE=TRUE;AUTO_SERVER=TRUE;OLD_INFORMATION_SCHEMA=TRUE}")
    private String url;

    @Value("${spring.datasource.username:sa}")
    private String username;

    @Value("${spring.datasource.password:}")
    private String password;

    @Bean
    public DataSource dataSource() {
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setDriverClassName(driverClassName);
        dataSource.setJdbcUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        return dataSource;
    }

    @Bean(name="sqlSessionFactory")
    public SqlSessionFactoryBean sqlSessionFactory(DataSource dataSource) throws IOException {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        sqlSessionFactoryBean.setConfigLocation(new ClassPathResource("mybatis-config.xml"));
        sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath*:mybatis/**/*Mapper.xml"));
        return sqlSessionFactoryBean;
    }

}
