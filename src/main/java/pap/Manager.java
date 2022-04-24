package pap;

        import lombok.Data;

        import java.util.HashMap;
        import java.util.Map;

@Data
public class Manager {
    public String login;
    public String password;
    public Map<String, String> userData;

    public Manager(String login, String password){
        this.login = login;
        this.password = password;
        userData = new HashMap<String, String>();
        userData.put(login, password);
    }
}
