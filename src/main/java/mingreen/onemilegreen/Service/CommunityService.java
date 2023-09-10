package mingreen.onemilegreen.Service;


import mingreen.onemilegreen.Domain.CommuList;
import mingreen.onemilegreen.Domain.ScheduleFiles;
import mingreen.onemilegreen.Domain.ScheduleList;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.List;

@Service
public class CommunityService {
    private final JdbcTemplate jdbcTemplate;

    public CommunityService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Long createCommunity(String com_name,String com_content,String com_category,String com_district,int com_max_mem,int id,String fileUrl){
        String sql = "insert into Community(com_name,com_content,com_category,com_district,com_max_mem,com_leader_id,com_image) values(?,?,?,?,?,?,?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection ->{
            PreparedStatement preparedStatement = connection.prepareStatement(sql,new String[] {"com_id"});
            preparedStatement.setString(1,com_name);
            preparedStatement.setString(2,com_content);
            preparedStatement.setString(3,com_category);
            preparedStatement.setString(4,com_district);
            preparedStatement.setInt(5,com_max_mem);
            preparedStatement.setInt(6,id);
            preparedStatement.setString(7,fileUrl);
            return preparedStatement;
        },keyHolder);


        Long key = keyHolder.getKey().longValue();
        return key;
    }

    public void createSchedule(String sch_name, String sch_st_date, String sch_end_date, String sch_location, int sch_min_num, int sch_max_num, String sch_content){
        String sql="insert into CommunitySchedule(sch_name,sch_st_date,sch_end_date,sch_location,sch_min_num,sch_max_num,sch_content) values(?,?,?,?,?,?,?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(con -> {
            PreparedStatement preparedStatement = con.prepareStatement(sql,new String[] {"sch_id"});
            preparedStatement.setString(1,sch_name);
            preparedStatement.setTimestamp(2, Timestamp.valueOf(sch_st_date));
            preparedStatement.setTimestamp(3,Timestamp.valueOf(sch_end_date));
            preparedStatement.setString(4,sch_location);
            preparedStatement.setInt(5,sch_min_num);
            preparedStatement.setInt(6,sch_max_num);
            preparedStatement.setString(7,sch_content);
            return preparedStatement;
        },keyHolder);
    }

    public List<CommuList> getMyCommunity(int user_no){
        List<CommuList> communityList = jdbcTemplate.query("select C.com_id, C.com_name, C.com_content, C.com_category, C.com_district, C.com_image, C.com_num_mem, C.com_badge, C.com_leader_id " +
                "From User U " +
                "Join UserCommunity UC " +
                "on U.user_no = UC.user_no " +
                "join Community C " +
                "on UC.com_id=C.com_id " +
                "where U.user_no=? and UC.user_com_status=1 ", new ComMapper(),user_no);
        return communityList;
    }

    public List<CommuList> getAllCommunity(int user_no){
        List<CommuList> allCommunityList = jdbcTemplate.query("select C.com_id, C.com_name, C.com_content, C.com_category, C.com_district, C.com_image, C.com_num_mem, C.com_badge, C.com_leader_id\n" +
                "from User U\n" +
                "join Community C\n" +
                "on U.district=C.com_district\n" +
                "where U.user_no=?",new ComMapper(),user_no);
        return allCommunityList;
    }

    public List<CommuList> getCommunity(int user_no, String category){
        List<CommuList> communityList = jdbcTemplate.query("select C.com_id, C.com_name, C.com_content, C.com_category, C.com_district, C.com_image, C.com_num_mem, C.com_badge, C.com_leader_id\n" +
                "from User U\n" +
                "join Community C\n" +
                "on U.district=C.com_district\n" +
                "where U.user_no=? and C.com_category=?", new ComMapper(),user_no,category);
        return communityList;
    }

    private final class ComMapper implements RowMapper<CommuList>{
        public CommuList mapRow(ResultSet rs, int rowNum) throws SQLException{
            return new CommuList(
                    rs.getInt("com_id"),
                    rs.getString("com_name"),
                    rs.getString("com_content"),
                    rs.getString("com_category"),
                    rs.getString("com_district"),
                    rs.getString("com_image"),
                    rs.getInt("com_num_mem"),
                    rs.getInt("com_badge"),
                    rs.getInt("com_leader_id")
            );
        }
    }


    public List<ScheduleFiles> getScheduleFiles(Long sch_id){
        List<ScheduleFiles> results=jdbcTemplate.query("select U.user_nick, CF.feed_id, CF.feed_created, CF.feed_image, CF.feed_like, CS.sch_name, CS.sch_st_date,CS.sch_end_date\n" +
                "from User U\n" +
                "join CommunityFeed CF\n" +
                "on U.user_no=CF.user_no2\n" +
                "join CommunitySchedule CS\n" +
                "on CF.sch_id=CS.sch_id\n" +
                "where sch_id=?", new RowMapper<ScheduleFiles>() {
            @Override
            public ScheduleFiles mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new ScheduleFiles(
                        rs.getLong("feed_id"),
                        rs.getString("user_nick"),
                        rs.getString("sch_name"),
                        rs.getTimestamp("sch_st_date"),
                        rs.getTimestamp("sch_end_date"),
                        rs.getTimestamp("feed_created"),
                        rs.getString("feed_image"),
                        rs.getInt("feed_like")
                );
            }
        },sch_id);
        return results;

    }

    public List<ScheduleList> getDaySchedules(Long com_id, Date sch_st_date){
        List<ScheduleList> results =jdbcTemplate.query("select CS.sch_id,CS.sch_name,CS.sch_st_date,CS.sch_end_date,CS.sch_max_num,CS.sch_content ,CS.sch_sch_num,CS.sch_location,CS.sch_prepare from one_mile_green.CommunitySchedule CS where com_id=? and sch_st_date=?", new RowMapper<ScheduleList>() {
            @Override
            public ScheduleList mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new ScheduleList(
                        rs.getLong("sch_id"),
                        rs.getString("sch_name"),
                        rs.getTimestamp("sch_st_date"),
                        rs.getTimestamp("sch_end_date"),
                        rs.getInt("sch_max_num"),
                        rs.getInt("sch_sch_num"),
                        rs.getString("sch_content"),
                        rs.getString("sch_location"),
                        rs.getString("sch_prepare")
                );
            }
        },com_id,sch_st_date);
        return results;
    }

    public List<ScheduleList> getAllSchedule(Long com_id){
        List<ScheduleList> results =jdbcTemplate.query("select CS.sch_id,CS.sch_name,CS.sch_st_date,CS.sch_end_date,CS.sch_max_num,CS.sch_content ,CS.sch_sch_num,CS.sch_location,CS.sch_prepare from one_mile_green.CommunitySchedule CS where com_id=?", new RowMapper<ScheduleList>() {
            @Override
            public ScheduleList mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new ScheduleList(
                        rs.getLong("sch_id"),
                        rs.getString("sch_name"),
                        rs.getTimestamp("sch_st_date"),
                        rs.getTimestamp("sch_end_date"),
                        rs.getInt("sch_max_num"),
                        rs.getInt("sch_sch_num"),
                        rs.getString("sch_content"),
                        rs.getString("sch_location"),
                        rs.getString("sch_prepare")
                );
            }
        },com_id);
        return results;
    }



}
