package mingreen.onemilegreen.Domain;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor
public class ScheduleList {
    private Long sch_id; //스케쥴 id
    private String sch_name; //일정 이름
    private Timestamp sch_st_date; //시작 시간날짜
    private Timestamp sch_end_date; //종료 시간날짜
    private int sch_max_num; //최대 참여 인원
    private int sch_sch_num; //참여인원
    private String sch_content; //일정 설명
    private String sch_location; //일정 장소
    private String sch_prepare; //준비물
}
