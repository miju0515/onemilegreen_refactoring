package mingreen.onemilegreen.Repository;

import mingreen.onemilegreen.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Repository
public class JdbcUserRepository{
    private final JdbcTemplate jdbcTemplate;


    public JdbcUserRepository(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }


    public User save(User user){
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("User").usingGeneratedKeyColumns("user_no");

        Map<String,Object> parmeters= new HashMap<>();
        parmeters.put("user_id",user.getUser_id());
        parmeters.put("user_pwd",user.getUser_pwd());
        parmeters.put("user_nick",user.getUser_nick());

        Number key = jdbcInsert.executeAndReturnKey((new MapSqlParameterSource(parmeters)));
        user.setUser_no(key.intValue());
        return user;
    }


}
