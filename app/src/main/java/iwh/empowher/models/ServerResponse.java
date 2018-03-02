package iwh.empowher.models;

/**
 * Created by Jyoti Joshi on 18-02-2018.
 */

public class ServerResponse {
    private String result;
    private String message;
    private User user;

    public String getResult() {
        return result;
    }

    public String getMessage() {
        return message;
    }

    public User getUser() {
        return user;
    }
}
