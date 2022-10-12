package ch20.oracle.sec12;

import lombok.Data;

@Data
public class User {
    private String userid;
    private String username;
    private String userpassword;
    private int userage;
    private String useremail;
}
