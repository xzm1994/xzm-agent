//package com.example.xzmagent.config;
//
//import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
//import org.apache.ibatis.session.SqlSessionFactory;
//import org.mybatis.spring.SqlSessionTemplate;
//import org.mybatis.spring.annotation.MapperScan;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.boot.jdbc.DataSourceBuilder;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.jdbc.datasource.DataSourceTransactionManager;
//
//import javax.sql.DataSource;
//
//@Configuration
//@MapperScan(basePackages = "com.example.xzmagent.repository.pg", sqlSessionTemplateRef = "pgSqlSessionTemplate")
//public class PostgreSQLDataSourceConfig {
//
//    @Bean(name = "pgDataSource")
//    @ConfigurationProperties(prefix = "spring.datasource.pg")
//    public DataSource pgDataSource() {
//        return DataSourceBuilder.create().build();
//    }
//
//    @Bean(name = "pgSqlSessionFactory")
//    public SqlSessionFactory pgSqlSessionFactory(@Qualifier("pgDataSource") DataSource dataSource) throws Exception {
//        MybatisSqlSessionFactoryBean bean = new MybatisSqlSessionFactoryBean();
//        bean.setDataSource(dataSource);
//        return bean.getObject();
//    }
//
//    @Bean(name = "pgTransactionManager")
//    public DataSourceTransactionManager pgTransactionManager(@Qualifier("pgDataSource") DataSource dataSource) {
//        return new DataSourceTransactionManager(dataSource);
//    }
//
//    @Bean(name = "pgSqlSessionTemplate")
//    public SqlSessionTemplate pgSqlSessionTemplate(@Qualifier("pgSqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
//        return new SqlSessionTemplate(sqlSessionFactory);
//    }
//
//    @Bean(name = "pgJdbcTemplate")
//    public JdbcTemplate pgJdbcTemplate(@Qualifier("pgDataSource") DataSource dataSource) {
//        return new JdbcTemplate(dataSource);
//    }
//}