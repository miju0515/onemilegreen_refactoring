package mingreen.onemilegreen.Domain;

import lombok.Getter;

@Getter
public class CommuList {
    private int com_id;
    private String com_name;
    private String com_content;
    private String com_category;
    private String com_district;
    private String com_image;
    private int com_num_people;
    private int com_badge;
    private int com_leader_id;

    public CommuList(int com_id, String com_name, String com_content, String com_category, String com_district, String com_image, int com_num_people, int com_badge, int com_leader_id) {
        this.com_id = com_id;
        this.com_name = com_name;
        this.com_content = com_content;
        this.com_category = com_category;
        this.com_district = com_district;
        this.com_image = com_image;
        this.com_num_people = com_num_people;
        this.com_badge = com_badge;
        this.com_leader_id = com_leader_id;
    }
}
