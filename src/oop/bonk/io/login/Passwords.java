package login;

import java.util.HashMap;

public class Passwords {

    HashMap<String, String> logininfo = new HashMap<String, String>();

    Passwords() {
        // primer logininfo.put("username","password");
        logininfo.put("123", "456");
        logininfo.put("abc", "def");
        logininfo.put("xXxEpicGamerxXx", "fortnite");

    }

    protected HashMap getlogininfo() {

        return logininfo;
    }

}
