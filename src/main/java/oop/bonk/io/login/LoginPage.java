package oop.bonk.io.login;

import oop.bonk.io.Root;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

public class LoginPage implements ActionListener {

    final JFrame frame = new JFrame();
    final JButton loginButton = new JButton("Login");
    final JButton resetButton = new JButton("Reset");
    final JTextField userIDField = new JTextField();
    final JPasswordField userPasswordField = new JPasswordField();
    final JLabel userID = new JLabel("username:");
    final JLabel userPasswordLabel = new JLabel("password:");
    final JLabel message = new JLabel();
    final HashMap<String, String> logininfo;

    public LoginPage(HashMap<String, String> loginI) {

        logininfo = loginI;
        // velicine
        userID.setBounds(50, 100, 75, 25);
        userPasswordLabel.setBounds(50, 150, 75, 25);

        message.setBounds(125, 250, 250, 35);
        message.setFont(new Font(null, Font.ITALIC, 25));

        userIDField.setBounds(125, 100, 200, 25);
        userPasswordField.setBounds(125, 150, 200, 25);

        loginButton.setBounds(125, 200, 100, 25);
        loginButton.setFocusable(false);
        loginButton.addActionListener(this);

        resetButton.setBounds(225, 200, 100, 25);
        resetButton.setFocusable(false);
        resetButton.addActionListener(this);

        frame.add(userID);
        frame.add(userPasswordLabel);
        frame.add(message);
        frame.add(userIDField);
        frame.add(userPasswordField);
        frame.add(loginButton);
        frame.add(resetButton);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(420, 420);
        frame.setLayout(null);
        frame.setVisible(true);


        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
        int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
        frame.setLocation(x, y);


    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == resetButton) {
            userIDField.setText("");
            userPasswordField.setText("");
        }

        if (e.getSource() == loginButton) {

            String userID = userIDField.getText();
            String password = String.valueOf(userPasswordField.getPassword());

            if (logininfo.containsKey(userID)) {
                if (logininfo.get(userID).equals(password)) {
                    message.setForeground(Color.green);
                    message.setText("Radi");
                    frame.dispose();
                    //WelcomePage welcomePage = new WelcomePage(userID);
                    Root root = new Root();
                } else {
                    message.setForeground(Color.red);
                    message.setText("Ne radi");
                }

            } else {
                message.setForeground(Color.red);
                message.setText("nepostojeci user");
            }
        }
    }
}