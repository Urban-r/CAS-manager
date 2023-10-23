import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.Scanner;
import javax.swing.*;

public class Login {

    private static JLabel userLabel;
    private static JTextField userText;
    private static JLabel passwordLabel;
    private static JPasswordField passwordText;
    private static JButton button;
    private static JLabel success;

    public Login(String filename) {

        JPanel panel = new JPanel();
        JFrame frame = new JFrame();
        frame.setSize(350,200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);

        panel.setLayout(null);

        userLabel = new JLabel("User");
        userLabel.setBounds(10,20,80,25);
        panel.add(userLabel);

        userText = new JTextField(20);
        userText.setBounds(100,20,165,25);
        panel.add(userText);

        passwordLabel = new JLabel("Password");
        passwordLabel.setBounds(10,50,80,25);
        panel.add(passwordLabel);

        passwordText = new JPasswordField();
        passwordText.setBounds(100,50,165,25);
        panel.add(passwordText);

        button = new JButton("Login");
        button.setBounds(10,80,80,25);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {

                    InputStream stream = getClass().getClassLoader().getResourceAsStream(
                            filename);
                    Reader reader = new InputStreamReader(stream);
                    BufferedReader br = new BufferedReader(reader); // buffers the file

                    String user = br.readLine(); // reads the first line in the file as the username
                    String pass = br.readLine(); // reads the second line in the file as the password
                    String username = userText.getText();
                    String password = passwordText.getText();

                    if (username.equals(user) && password.equals(pass)) {
                        success.setText("Login successful, type into console: "+365836);
                    }
                }
                catch (IOException f)
                {
                    f.printStackTrace();
                }
            }
        });
        panel.add(button);

        success = new JLabel("");
        success.setBounds(10,110,300,25);
        panel.add(success);

        frame.setVisible(true);

    }

}
