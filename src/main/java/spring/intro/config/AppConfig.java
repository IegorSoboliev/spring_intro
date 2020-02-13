package spring.intro.config;

import spring.intro.model.User;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

@Configuration
@ComponentScan(basePackages = {
        "spring.intro.dao",
        "spring.intro.service"
})
public class AppConfig {

    @Bean
    public DataSource getDataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost/spring?serverTimezone=UTC");
        dataSource.setUsername("root");
        dataSource.setPassword("SQLsuccess8");
        return dataSource;
    }

    @Bean
    public LocalSessionFactoryBean getSessionFactory() {
        LocalSessionFactoryBean sessionFactoryBean = new LocalSessionFactoryBean();
        sessionFactoryBean.setDataSource(getDataSource());
        Properties properties = new Properties();
        properties.put("hibernate.show_sql", "true");
        properties.put("hibernate.hbm2ddl.auto", "create-drop");
        sessionFactoryBean.setHibernateProperties(properties);
        sessionFactoryBean.setAnnotatedClasses(User.class);
        return sessionFactoryBean;
    }
}
