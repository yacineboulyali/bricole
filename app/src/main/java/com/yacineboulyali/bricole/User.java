package com.yacineboulyali.bricole;

/**
 * Created by sahri on 6/21/2017.
 */

public class User {

    private String uLogin;
    private  String uPass;

    public User(String uLogin, String uPass) {
        this.uLogin = uLogin;
        this.uPass = uPass;
    }

    public String getuLogin() {
        return uLogin;
    }

    public void setuLogin(String uLogin) {
        this.uLogin = uLogin;
    }

    public void setuPass(String uPass) {
        this.uPass = uPass;
    }

    public String getuPass() {
        return uPass;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (!uLogin.equals(user.uLogin)) return false;
        return uPass.equals(user.uPass);

    }

    @Override
    public int hashCode() {
        int result = uLogin.hashCode();
        result = 31 * result + uPass.hashCode();
        return result;
    }

}
