package mingreen.onemilegreen.Domain;

import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Getter
@Table(name="GreenSeoul")
public class GreenSeoul {
    @Id
    private int district_id;

    private String district_name;
    private int district_effect;
    private int district_mile;

    public GreenSeoul(int district_id, String district_name, int district_effect, int district_mile) {
        this.district_id = district_id;
        this.district_name = district_name;
        this.district_effect = district_effect;
        this.district_mile = district_mile;
    }

    public GreenSeoul() {
    }
}
