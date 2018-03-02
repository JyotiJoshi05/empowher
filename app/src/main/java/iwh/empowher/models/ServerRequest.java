package iwh.empowher.models;

/**
 * Created by Jyoti Joshi on 18-02-2018.
 */

public class ServerRequest {

    private String operation;
    private User user;

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
