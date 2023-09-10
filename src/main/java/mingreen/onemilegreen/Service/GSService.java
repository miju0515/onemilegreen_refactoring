package mingreen.onemilegreen.Service;


import mingreen.onemilegreen.Domain.GreenSeoul;
import mingreen.onemilegreen.Domain.SeoulStatus;
import mingreen.onemilegreen.Domain.UserStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;

@Service
public class GSService {

    private final JdbcTemplate jdbcTemplate;

    public GSService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public GreenSeoul findByDistrict(String district){

        return jdbcTemplate.queryForObject("select * from GreenSeoul where GreenSeoul.district_name=?", new RowMapper<GreenSeoul>() {
            @Override
            public GreenSeoul mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new GreenSeoul(
                        rs.getInt("district_id"),
                        rs.getString("district_name"),
                        rs.getInt("district_effect"),
                        rs.getInt("district_mile")
                );
            }
        },district);
    }

    public int countDistrictPpl(String district){
        return jdbcTemplate.queryForObject("select count(*) from User where User.district=?",int.class,district);
    }

    public int countAllPpl(){
        return jdbcTemplate.queryForObject("select count(*) from User",int.class);
    }

    public int getDistrictRanking(String district){
        return jdbcTemplate.queryForObject("select rank() over(order by GreenSeoul.district_effect) as ranking from GreenSeoul where GreenSeoul.district_name=?",int.class,district);
    }

    public UserStatus getUserData(int user_no){
        return jdbcTemplate.queryForObject("select User.user_mileage,User.user_effect,User.district from User where User.id=?", new RowMapper<UserStatus>() {
            @Override
            public UserStatus mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new UserStatus(
                        rs.getInt("user_mileage"),
                        rs.getInt("user_effect"),
                        rs.getString("district")
                );
            }
        },user_no);
    }

    public SeoulStatus getSeoulData(){
        return jdbcTemplate.queryForObject("select sum(GreenSeoul.district_mile) as sum_mile, sum(GreenSeoul.district_effect) as sum_effect from GreenSeoul", new RowMapper<SeoulStatus>() {
            @Override
            public SeoulStatus mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new SeoulStatus(
                        rs.getInt("sum_mile"),
                        rs.getInt("sum_effect")
                );

            }
        });

    }

}
