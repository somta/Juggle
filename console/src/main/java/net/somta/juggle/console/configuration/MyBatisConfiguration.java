package net.somta.juggle.console.configuration;

import com.zaxxer.hikari.HikariDataSource;
import net.somta.juggle.console.contants.DbConstants;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.sql.init.SqlInitializationProperties;
import org.springframework.boot.sql.init.DatabaseInitializationMode;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.Arrays;


/**
 *
 * MyBatis配置类
 *
 * @Date:        2021-02-03
 * @Author:      husong
 * @Version:     1.0.0
 */
@Configuration
@MapperScan(value = "net.somta.juggle.console.infrastructure.mapper")
public class MyBatisConfiguration {



    @Value("${juggle.db-type}")
    private String dbType;

    @Value("${spring.datasource.driver-class-name}")
    private String driverClassName;

    @Value("${spring.datasource.url}")
    private String url;

    @Value("${spring.datasource.username}")
    private String username;

    @Value("${spring.datasource.password}")
    private String password;

    /*@Bean
    public DataSource dataSource() {
        HikariDataSource dataSource = new HikariDataSource();
        if(DbConstants.h2Name.equals(dbType)){
            dataSource.setDriverClassName(DbConstants.h2DriverClassName);
            dataSource.setJdbcUrl(DbConstants.h2Url);
            dataSource.setUsername(DbConstants.h2UserName);
            dataSource.setPassword(DbConstants.h2Password);
        }else {
            dataSource.setDriverClassName(driverClassName);
            dataSource.setJdbcUrl(url);
            dataSource.setUsername(username);
            dataSource.setPassword(password);
        }
        return dataSource;
    }*/

//    @Bean(name="sqlSessionFactory")
//    public SqlSessionFactoryBean sqlSessionFactory(DataSource dataSource) throws IOException {
//        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
//        sqlSessionFactoryBean.setDataSource(dataSource);
//        //sqlSessionFactoryBean.setConfigLocation(new ClassPathResource("mybatis-config.xml"));
//        sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath*:mybatis/**/*Mapper.xml"));
//        return sqlSessionFactoryBean;
//    }

   /* @Primary
    @Bean
    public SqlInitializationProperties sqlInitializationProperties(){
        SqlInitializationProperties sqlInitializationProperties = new SqlInitializationProperties();
        sqlInitializationProperties.setSchemaLocations(Arrays.asList("classpath:db/schema.sql"));
        sqlInitializationProperties.setDataLocations(Arrays.asList("classpath:db/data.sql"));
        sqlInitializationProperties.setMode(DatabaseInitializationMode.ALWAYS);
        // 文件模式下，每次启动都会执行初始化，应该要跳过错误，表的初始化还是应该通过maven插件来做 https://www.jianshu.com/p/4a613dcf182c
        sqlInitializationProperties.setContinueOnError(true);
        return sqlInitializationProperties;
    }*/

}
