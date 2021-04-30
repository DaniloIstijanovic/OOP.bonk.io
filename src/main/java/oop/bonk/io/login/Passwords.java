package oop.bonk.io.login;

import java.util.HashMap;

public class Passwords {

    final HashMap<String, String> logininfo = new HashMap<>();

    public Passwords() {
        // primer logininfo.put("username","password");
        logininfo.put("123", "456");
        logininfo.put("abc", "def");
        logininfo.put("xXxEpicGamerxXx", "fortnite");

    }

    public HashMap<String, String> getlogininfo() {

        return logininfo;
    }

}
