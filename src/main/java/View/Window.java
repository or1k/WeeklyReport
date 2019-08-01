package View;

import ReportConstructor.BaseTest;
import Utils.Settings;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class Window {
    public static JTextField userText;
    public static JPasswordField passwordText;
    public static JButton loginButton = new JButton("login");
    public static JButton exitButton = new JButton("exit");
    public static JLabel userLabel = new JLabel("Email");
    public static JLabel passwordLabel = new JLabel("Password");

    public static JFrame frame;

    public static JRadioButton jRadioButton1;
    public static JRadioButton jRadioButton2;
    public static JRadioButton jRadioButton3;


    public static void main(String[] args) {
        frame = new JFrame("Report Constructor v1");
        frame.setPreferredSize(new Dimension(400,200));
        // handle window close

        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });


        // set up panels with buttons

        JPanel panel1 = new JPanel();
        mainView(panel1);


        // display
        frame.getContentPane().add(panel1);
        frame.pack();
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private static void mainView(JPanel panel) {
        panel.setLayout(null);

        JLabel h1 = new JLabel("Enter in JIRA account");
        h1.setBounds(120,10,150,25);
        panel.add(h1);


        userLabel.setBounds(10, 50, 80, 25);
        panel.add(userLabel);

        userText = new JTextField(20);
        userText.setBounds(200, 50, 160, 25);
        panel.add(userText);
        try {
            userText.setText(Settings.profile());
        } catch (IOException e) {
            e.printStackTrace();
        }


        passwordLabel.setBounds(10, 80, 80, 25);
        panel.add(passwordLabel);

        passwordText = new JPasswordField(20);
        passwordText.setBounds(200, 80, 160, 25);
        panel.add(passwordText);

//        linkText = new JTextField(20);
//        linkText.setBounds(200, 70, 160, 25);
//        linkText.setToolTipText("Нужна ссылка откуда пиздить подписоту!!");
//        panel.add(linkText);

//        btnBrowse.setBounds(140, 190, 100, 25);
//        panel.add(btnBrowse);
//        btnBrowse.addActionListener(new View.Window.ActionListenerSaveReport());


        loginButton.setBounds(10, 120, 80, 25);
        panel.add(loginButton);
        loginButton.addActionListener(new Window.ActionListenerForGeo());

        exitButton.setBounds(280, 120, 80, 25);
        panel.add(exitButton);
        exitButton.addActionListener(new Window.ExitActionListener());

    }


    public static class ActionListenerForGeo implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if(userText.getText().equals("")){
                userLabel.setForeground(Color.RED);
                return;
            }else {
                userLabel.setForeground(Color.BLACK);
            }

            //TODO
            // Сделать провернку пароля

            if(passwordText.getPassword().length < 2){
                passwordLabel.setForeground(Color.RED);
                return;
            }
            else {
                passwordLabel.setForeground(Color.BLACK);
            }
            loginButton = (JButton) e.getSource();
            JOptionPane.showMessageDialog(loginButton,  "СТАРТУЕМ!!!!!");
            frame.dispose();
            try {
                BaseTest.createReport();
            } catch (IOException ex) {
                ex.printStackTrace();
            }

        }
    }

//

    public static class ExitActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            exitButton = (JButton)e.getSource();
            System.exit(0);

        }
    }



}