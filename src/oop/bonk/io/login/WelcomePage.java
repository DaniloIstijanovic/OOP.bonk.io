package login;

import java.awt.*;
import javax.swing.*;

public class WelcomePage {

    JFrame frame = new JFrame();
    JLabel welcome = new JLabel("Hello!");

    WelcomePage(String userID) {

        welcome.setBounds(0, 0, 200, 35);
        welcome.setFont(new Font(null, Font.PLAIN, 25));
        welcome.setText("Hello " + userID);

        frame.add(welcome);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(420, 420);
        frame.setLayout(null);
        frame.setVisible(true);
    }
}