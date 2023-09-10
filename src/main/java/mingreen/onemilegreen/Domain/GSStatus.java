package mingreen.onemilegreen.Domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GSStatus {
    private int districtTotalMileage;
    private int districtTotalEffect;
    private int districtTotalUser;
    private int districtRangking;
    private String districtName;
    private int userMileage;
    private int userEffect;
    private int seoulTotalMileage;
    private int seoulTotalEffect;
    private int seoulTotalUser;

    public GSStatus(int districtTotalMileage, int districtTotalEffect, int districtTotalUser, int districtRangking, String districtName, int userMileage, int userEffect, int seoulTotalMileage, int seoulTotalEffect, int seoulTotalUser) {
        this.districtTotalMileage = districtTotalMileage;
        this.districtTotalEffect = districtTotalEffect;
        this.districtTotalUser = districtTotalUser;
        this.districtRangking = districtRangking;
        this.districtName = districtName;
        this.userMileage = userMileage;
        this.userEffect = userEffect;
        this.seoulTotalMileage = seoulTotalMileage;
        this.seoulTotalEffect = seoulTotalEffect;
        this.seoulTotalUser = seoulTotalUser;
    }
    public GSStatus(){

    }
}
