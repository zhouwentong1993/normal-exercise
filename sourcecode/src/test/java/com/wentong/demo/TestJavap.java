package com.wentong.demo;

import java.applet.Applet;
import java.awt.*;

public class TestJavap extends Applet {

    String date;
    String email;

    public void init() {
        resize(500, 100);
        date = getParameter("LAST_UPDATED");
        email = getParameter("EMAIL");
    }

    public void paint(Graphics g) {
        g.drawString(date + " by ", 100, 15);
        g.drawString(email, 290, 15);
    }
}
