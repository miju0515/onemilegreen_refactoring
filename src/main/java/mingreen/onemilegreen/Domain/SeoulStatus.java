package mingreen.onemilegreen.Domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SeoulStatus {
    private int seoul_mile;
    private int seoul_effect;

    public SeoulStatus(int seoul_mile, int seoul_effect) {
        this.seoul_mile = seoul_mile;
        this.seoul_effect = seoul_effect;
    }
}
