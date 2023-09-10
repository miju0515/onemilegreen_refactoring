package mingreen.onemilegreen.Domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Date;

@Entity
public class Routine {
    @Id
    private int rou_id;

    private int rou_time;
    private Date rou_st_date;
    private Date rou_end_date;
    private int rou_status;
    private String rou_content;
    private int rou_mileage;
    private String rou_effect_desc;
    private String rou_pass_img;
    private String rou_np_img;
    private String rou_desc;
    private String rou_dayofweek;

}
