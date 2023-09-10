package mingreen.onemilegreen;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name="User")
@Getter
@Setter
public class User {

    @Id
    private int user_no;

    private String user_id;
    private String user_pwd;
    private String user_nick;
    private String district;
    private String user_email;
    private String user_kakao;
    private String user_google;
    private int user_mileage;
    private int user_effct;
}
