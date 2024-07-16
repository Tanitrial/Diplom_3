package base;

import lombok.Data;

@Data
public class UserLogin {
    private String email;
    private String password;
    private String name;

    public UserLogin (String email, String password, String name) {
        this.email = email;
        this.password = password;
        this.name = name;
    }

    public UserLogin (String email, String password) {
        this.email = email;
        this.password = password;
    }
}