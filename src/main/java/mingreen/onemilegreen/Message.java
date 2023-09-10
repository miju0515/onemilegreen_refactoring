package mingreen.onemilegreen;

import lombok.Data;

@Data
public class Message {
    private int status;
    private String message;
    private Object data;

    public Message() {
        this.status = StatusCode.BAD_REQUEST;
        this.message = null;
        this.data = null;
    }


}
