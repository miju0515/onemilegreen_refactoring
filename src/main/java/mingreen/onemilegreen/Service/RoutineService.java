package mingreen.onemilegreen.Service;


import mingreen.onemilegreen.Domain.MileEffect;
import mingreen.onemilegreen.Domain.UserRoutine;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


@Service
public class RoutineService {
    private final JdbcTemplate jdbcTemplate;
    public RoutineService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void userRoutineUpdate(int user_rou_id){
        String date="2023-07-28";
        this.jdbcTemplate.update("update UserRoutine set ur_status=2,ur_fin_time=? where user_rou_id=?",
                java.sql.Date.valueOf(date),user_rou_id);
    }

    public void insertRoutine(String fileUrl,String content,int user_rou_id){
        String date= "2023-07-28";
        this.jdbcTemplate.update("insert into UserRoutineDetail(user_rou_id,urd_cre_time,urd_image,urd_content,urd_useYN,urd_status) values(?,?,?,?,1,1)",
                user_rou_id,java.sql.Date.valueOf(date),fileUrl,content);
    }

    public void addMileEffect(int user_rou_id){
        int rou_id=jdbcTemplate.queryForObject("select rou_id from UserRoutine where user_rou_id=?",int.class,user_rou_id);
        int user_no=jdbcTemplate.queryForObject("select user_no from UserRoutine where user_rou_id=?",int.class,user_rou_id);
        String district=jdbcTemplate.queryForObject("select district from User where id=?",String.class,user_no);

        MileEffect rMileEffect =jdbcTemplate.queryForObject("select rou_mileage,rou_effect from Routine where rou_id=?", new RowMapper<MileEffect>() {
            @Override
            public MileEffect mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new MileEffect(
                        rs.getInt("rou_mileage"),
                        rs.getInt("rou_effect")
                );
            }
        }, rou_id);
        MileEffect dMileEffect=jdbcTemplate.queryForObject("select district_mile,district_effect from GreenSeoul where district_name=?", new RowMapper<MileEffect>() {
            @Override
            public MileEffect mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new MileEffect(
                        rs.getInt("district_mile"),
                        rs.getInt("district_effect")
                );
            }
        }, district);
        MileEffect uMileEffect=jdbcTemplate.queryForObject("select user_mileage,user_effect from User where id=?", new RowMapper<MileEffect>() {
            @Override
            public MileEffect mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new MileEffect(
                        rs.getInt("user_mileage"),
                        rs.getInt("user_effect")
                );
            }
        }, user_no);

        int new_u_mile=uMileEffect.getMile()+rMileEffect.getMile();
        int new_u_effect=uMileEffect.getEffect()+rMileEffect.getEffect();
        int new_d_mile=dMileEffect.getMile()+rMileEffect.getMile();
        int new_d_effect=dMileEffect.getEffect()+rMileEffect.getEffect();

        this.jdbcTemplate.update("update User set user_mileage=?,user_effect=? where id=?",
                new_u_mile,new_u_effect,user_no);
        this.jdbcTemplate.update("update GreenSeoul set district_mile=?,district_effect=? where district_name=?",
                new_d_mile,new_d_effect,district);





    }

    public List<UserRoutine> findAll(int rou_id){
        List<UserRoutine> results=jdbcTemplate.query(
                "SELECT U.id,U.user_nick,UR.rou_id,URD.urd_image,URD.urd_like " +
                        "FROM User U " +
                        "Inner Join UserRoutine UR " +
                        "on U.id=UR.user_no " +
                        "Join UserRoutineDetail URD " +
                        "on UR.user_rou_id = URD.user_rou_id " +
                        "where UR.rou_id=?",
                new RowMapper<UserRoutine>() {
            @Override
            public UserRoutine mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new UserRoutine(
                        rs.getString("U.user_nick"),
                        rs.getString("URD.urd_image"),
                        rs.getInt("URD.urd_like")
                );
            }
        },rou_id);
        return results;
    }



}
