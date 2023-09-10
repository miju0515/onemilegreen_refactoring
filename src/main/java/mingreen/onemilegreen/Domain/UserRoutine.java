package mingreen.onemilegreen.Domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRoutine {
    private String user_nick;
    private String urd_image;
    private int urd_like;

    public UserRoutine(String user_nick, String urd_image, int urd_like) {
        this.user_nick = user_nick;
        this.urd_image = urd_image;
        this.urd_like = urd_like;
    }
}
