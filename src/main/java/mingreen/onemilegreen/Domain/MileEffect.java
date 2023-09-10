package mingreen.onemilegreen.Domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MileEffect {
    private int mile;
    private int effect;

    public MileEffect(int mile, int effect) {
        this.mile = mile;
        this.effect = effect;
    }
}
