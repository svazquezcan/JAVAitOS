package com.daomysql;

import org.springframework.jdbc.datasource.DriverManagerDataSource;

public class DataSourceJDBC {
    
    private DriverManagerDataSource dataSource = new DriverManagerDataSource();
    private final String url = "jdbc:mysql://localhost:3306/wordfinder?useUnicode=true&characterEncoding=utf8&useSSL=false&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private final String dbUsername = "root";
    private final String dbPassword = "arkarianKirtash4";
 

    public DataSourceJDBC() {
	dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
    dataSource.setUrl(url);
    dataSource.setUsername(dbUsername);
    dataSource.setPassword(dbPassword);
    }
    
    public DriverManagerDataSource getDataSource() {
    return dataSource;
    }
}
    

