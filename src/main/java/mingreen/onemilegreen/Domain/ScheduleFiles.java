package mingreen.onemilegreen.Domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor
public class ScheduleFiles {
    private Long feed_id;
    private String user_nick;
    private String sch_name;
    private Timestamp sch_st_date;
    private Timestamp sch_end_date;
    private Timestamp feed_created;
    private String feed_image;
    private int feed_like;
}
