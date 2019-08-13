package View;

import ReportConstructor.BaseTest;

import DownloadUtils.DownloadWithBar;
import Utils.Settings;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;


public class Window {


    public static JTextField userText;
    public static JPasswordField passwordText;
    public static JProgressBar j ;
    public static JButton loginButton = new JButton("Login");
    public static JLabel help;
    public static JButton installUpdate = new JButton("Install update?");
    public static JButton updateButton = new JButton("No update now");
    public static JButton exitButton = new JButton("Exit");
    public static JLabel userLabel = new JLabel("Email");
    public static JLabel passwordLabel = new JLabel("Password");
    public static int numberVersion = 3;

    public static JFrame frame;


    public static void main(String[] args) throws IOException {
        frame = new JFrame("Weekly report v" + numberVersion);
        frame.setPreferredSize(new Dimension(400,250));

        // handle window close
        ImageIcon img = new ImageIcon(System.getProperty("user.dir") +"\\src\\main\\resources\\apple-touch-icon.png");


        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });


        // set up panels with buttons

        JPanel panel1 = new JPanel();
        panel1.setBackground(new Color(31,29,42));
        mainView(panel1);


        // display
        frame.getContentPane().setBackground(Color.red);
        frame.getContentPane().add(panel1);
        frame.setIconImage(img.getImage());
        frame.pack();
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);

        frame.setVisible(true);

        //Check Update, if have - updateButton Enable. Else - Disable
        if(checkUpdateStatus()){
            updateButton.setEnabled(true);
            updateButton.setText("Update now");

        fill();
        }





    }

    private static void mainView(JPanel panel) {
//        Enter in JIRA account

        panel.setLayout(null);



        JLabel h1 = new JLabel("<html> <font color='#ffffff'>Enter in</font> <font color='#ffa800'>JIRA account</font></html>");
        h1.setBounds(120,10,150,25);
        panel.add(h1);

        help = new JLabel("?");
        help.setBounds(360, 10, 15, 15);
        help.setForeground(Color.WHITE);
        panel.add(help);
        help.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                JOptionPane.showMessageDialog(loginButton, "*Запускать от имени админа." +
                        "\n*Отчет сохраняется в корневой папки с программой." +
                        "\n*Кнопка обновления появится если будет новое актуальное обновление.");
            }
        });


        userLabel.setBounds(10, 50, 80, 25);
        userLabel.setForeground(Color.WHITE);
        panel.add(userLabel);

        userText = new JTextField(20);
        userText.setBounds(180, 50, 180, 25);
        panel.add(userText);
        try {
            userText.setText(Settings.profile());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }


        passwordLabel.setBounds(10, 80, 80, 25);
        passwordLabel.setForeground(Color.WHITE);
        panel.add(passwordLabel);

        passwordText = new JPasswordField(20);
        passwordText.setBounds(180, 80, 180, 25);
        panel.add(passwordText);
        passwordText.addKeyListener(new KeyListener(){
            @Override
            public void keyTyped(KeyEvent e) {
                if(e.getKeyChar()==KeyEvent.VK_ENTER){
                    frame.dispose();
                    try {
                        BaseTest.createReport();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            }

            @Override
            public void keyPressed(KeyEvent e) {
                //Do Nothing
            }

            @Override
            public void keyReleased(KeyEvent e) {
                //Do Nothing
            }
        });



        loginButton.setBounds(10, 120, 80, 25);
        loginButton.setBorder(new LineBorder(new Color(255,158,0)));

        loginButton.setFocusPainted(false);
        loginButton.setForeground(Color.WHITE);
        loginButton.setBackground(new Color(255,158,0));
        loginButton.setFont(new Font("Roboto Mono", Font.PLAIN, 14));
        panel.add(loginButton);
        loginButton.addActionListener(new Window.LoginPressed());

        updateButton.setBounds(125, 120, 120, 25);
        updateButton.setBorder(new LineBorder(new Color(255,158,0)));
        updateButton.setFocusPainted(false);
        updateButton.setForeground(Color.WHITE);
        updateButton.setBackground(new Color(255,158,0));
        updateButton.setFont(new Font("Roboto Mono", Font.PLAIN, 14));
        updateButton.setEnabled(false);
        panel.add(updateButton);
        updateButton.addActionListener(new Window.UpdateChecker());

        installUpdate.setBounds(125, 120, 120, 25);
        installUpdate.setBorder(new LineBorder(new Color(255,158,0)));
        installUpdate.setFocusPainted(false);
        installUpdate.setForeground(Color.WHITE);
        installUpdate.setBackground(new Color(255,158,0));
        installUpdate.setFont(new Font("Roboto Mono", Font.PLAIN, 14));
        installUpdate.setVisible(false);
        panel.add(installUpdate);
        installUpdate.addActionListener(e -> {
            try {
                Desktop.getDesktop().open(new File(System.getProperty("user.dir") + "\\WeeklyReport_v" + (numberVersion+1) + ".exe"));
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });



        exitButton.setBounds(280, 120, 80, 25);
        exitButton.setBorder(new LineBorder(new Color(255,158,0)));
        exitButton.setFocusPainted(false);
        exitButton.setForeground(Color.WHITE);
        exitButton.setBackground(new Color(255,158,0));
        exitButton.setFont(new Font("Roboto Mono", Font.PLAIN, 14));
        panel.add(exitButton);
        exitButton.addActionListener(new Window.ExitActionListener());

        j = new JProgressBar(0,DownloadWithBar.getSize());
        j.setBorder(new LineBorder(new Color(255,158,0)));
        j.setBounds(10, 150, 360, 25);
        j.setMinimum(0);
        j.setMaximum(100);
        j.setStringPainted(true);
        panel.add(j);


    }

    public static boolean checkUpdateStatus() throws IOException {
        if(Settings.checkUpdate()){
            return true;
        }
        return false;
    }

    public static void fill() {
        int i = 0;
        try {
            while (i <= 100) {
                // fill the menu bar
                j.setValue(i);

                // delay the thread
                Thread.sleep(1000);
                i = (int) DownloadWithBar.getProgress();
                if(i==100){
                    updateButton.setVisible(false);
                    installUpdate.setVisible(true);
                }
            }
        } catch (Exception e) {
            System.out.println("catch v fill");
        }
    }



    public static class LoginPressed implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if(userText.getText().equals("")){
                userLabel.setForeground(Color.RED);
                return;
            }else {
                userLabel.setForeground(Color.BLACK);
            }


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

    public static class UpdateChecker implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (updateButton.getModel().isArmed()) {
                try {
                    DownloadWithBar downloadWithBar = new DownloadWithBar(new URL("https://github.com/or1k/WeeklyReport/releases/download/" + (numberVersion+1) + "/WeeklyReport_v" + (numberVersion+1) + ".exe"));
//                        updateButton.setText("Downloading");
                    if (DownloadWithBar.getStatus() == 0) {
                        updateButton.setText("Dowloading");
                    }
                    updateButton.setEnabled(false);
                } catch (MalformedURLException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }


    public static class ExitActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            exitButton = (JButton)e.getSource();
            System.exit(0);

        }
    }



}