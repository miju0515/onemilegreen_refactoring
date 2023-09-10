package mingreen.onemilegreen.Domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserStatus {
    private int user_mile;
    private int user_effect;
    private String district;

    public UserStatus(int user_mile, int user_effect, String district) {
        this.user_mile = user_mile;
        this.user_effect = user_effect;
        this.district = district;
    }
}
