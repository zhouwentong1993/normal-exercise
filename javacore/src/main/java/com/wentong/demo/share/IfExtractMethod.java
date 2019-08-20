package com.wentong.demo.share;

import org.junit.Test;

public class IfExtractMethod {

    @Test
    public void ifExtract() {
        User user = getUser();
        if (user != null && user.mobile != null && user.mobile.length() == 11 && user.verifyCode != null) {
            // doSomething
        }
    }

    @Test
    public void ifExtract2() {
        User user = getUser();
        if (userMobileValid(user) && user.verifyCode != null) {
            // doSomething
        }
    }

    private boolean userMobileValid(User user) {
        return user != null && user.mobile != null && user.mobile.length() == 11;
    }

    private User getUser() {
        return new User("123", "123");
    }

    class User{
        String mobile;
        String verifyCode;

        public User(String mobile, String verifyCode) {
            this.mobile = mobile;
            this.verifyCode = verifyCode;
        }
    }
}
