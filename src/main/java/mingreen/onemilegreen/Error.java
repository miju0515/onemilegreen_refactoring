package mingreen.onemilegreen;

public enum Error {
    OK(200,"200"),
    BAD_REQUEST(400,"BAD_REQUEST"),
    NOT_FOUND(404,"NOT_FOUND"),
    INTERNAL_SERVER_ERROR(500,"INTERNAL_SERVER_ERROR");

    private final int statusCode;
    private final String code;

    Error(int statusCode,String code) {
        this.statusCode = statusCode;
        this.code = code;
    }
}
