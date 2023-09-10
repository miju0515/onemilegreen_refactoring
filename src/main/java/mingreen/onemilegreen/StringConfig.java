package mingreen.onemilegreen;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import mingreen.onemilegreen.Repository.userRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import javax.persistence.EntityManager;
import javax.sql.DataSource;
import java.beans.ConstructorProperties;

@Configuration
@PropertySource("classpath:/application-aws.yml")
public class StringConfig {







}
