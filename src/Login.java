import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Login extends JFrame implements ActionListener {
    JButton rules, signup,showPasswordBtn;
    JTextField tfUserName, jfPRN;
    JPasswordField jfpass;
    boolean passwordVisible = false;

    Login() {
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("login.jpg"));
        JLabel image = new JLabel(i1);
        image.setBounds(0, 0, 600, 500);
        add(image);

        JLabel heading = new JLabel("Let's Start");
        heading.setBounds(750, 60, 300, 45);
        heading.setFont(new Font("Viner Hand ITC", Font.BOLD, 40));
        heading.setForeground(new Color(30, 144, 254));
        add(heading);

        // Username
        JLabel userNameLabel = new JLabel("Username");
        userNameLabel.setBounds(650, 150, 300, 20);
        userNameLabel.setFont(new Font("Mongolian Baiti", Font.BOLD, 18));
        userNameLabel.setForeground(new Color(30, 144, 254));
        add(userNameLabel);

        tfUserName = new JTextField();
        tfUserName.setBounds(835, 150, 200, 25);
        tfUserName.setFont(new Font("Times New Roman", Font.BOLD, 20));
        add(tfUserName);

        // PRN
        JLabel prnLabel = new JLabel("PRN");
        prnLabel.setBounds(650, 200, 300, 20);
        prnLabel.setFont(new Font("Mongolian Baiti", Font.BOLD, 18));
        prnLabel.setForeground(new Color(30, 144, 254));
        add(prnLabel);

        jfPRN = new JTextField();
        jfPRN.setBounds(835, 200, 200, 25);
        jfPRN.setFont(new Font("Times New Roman", Font.BOLD, 20));
        add(jfPRN);

        // Password
        JLabel passLabel = new JLabel("Password");
        passLabel.setBounds(650, 250, 300, 20);
        passLabel.setFont(new Font("Mongolian Baiti", Font.BOLD, 18));
        passLabel.setForeground(new Color(30, 144, 254));
        add(passLabel);

        jfpass = new JPasswordField();
        jfpass.setBounds(835, 250, 200, 25);
        jfpass.setFont(new Font("Times New Roman", Font.BOLD, 20));
        add(jfpass);

        // Eye icon for showing/hiding password
        ImageIcon icon = new ImageIcon(ClassLoader.getSystemResource("view.jpg"));
        Image resizedImage = icon.getImage().getScaledInstance(35, 25, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon = new ImageIcon(resizedImage);
        showPasswordBtn = new JButton(resizedIcon);
        showPasswordBtn.setBounds(1040, 250, 35, 25);
        showPasswordBtn.setBackground(Color.GRAY);
        showPasswordBtn.setBorderPainted(false);
        showPasswordBtn.setFocusPainted(false);
        showPasswordBtn.addActionListener(this);
        add(showPasswordBtn);

        // Buttons
        rules = new JButton("Rules");
        rules.setBounds(735, 350, 120, 25);
        rules.setBackground(new Color(30, 144, 254));
        rules.setForeground(Color.BLACK);
        rules.addActionListener(this);
        add(rules);

        signup = new JButton("SignUp");
        signup.setBounds(915, 350, 120, 25);
        signup.setBackground(new Color(30, 144, 254));
        signup.setForeground(Color.BLACK);
        signup.addActionListener(this);
        add(signup);

        setSize(1200, 500);
        setLocation(200, 150);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == showPasswordBtn) {
            ImageIcon viewIcon = new ImageIcon(ClassLoader.getSystemResource("view.jpg"));
            Image resizedViewImage = viewIcon.getImage().getScaledInstance(35, 25, Image.SCALE_SMOOTH);
            ImageIcon resizedViewIcon = new ImageIcon(resizedViewImage);

            ImageIcon hideIcon = new ImageIcon(ClassLoader.getSystemResource("hide.jpg"));
            Image resizedHideImage = hideIcon.getImage().getScaledInstance(35, 25, Image.SCALE_SMOOTH);
            ImageIcon resizedHideIcon = new ImageIcon(resizedHideImage);
            if (passwordVisible) {
                jfpass.setEchoChar('*'); // Hide password
                showPasswordBtn.setIcon(resizedViewIcon);
            } else {
                jfpass.setEchoChar((char) 0); // Show password
                showPasswordBtn.setIcon(resizedHideIcon);
            }
            passwordVisible = !passwordVisible;
        }
        else if (ae.getSource() == rules) {
            String username = tfUserName.getText();
            String prn = jfPRN.getText();
            String password = new String(jfpass.getPassword());
            if ( username.isEmpty() || prn.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please fill in all details.");
            }
            else {
                try {
                    database d = new database();
                    String q = "SELECT * FROM users WHERE name = ? AND prn = ? AND password = ?";
                    PreparedStatement pstmt = d.st.getConnection().prepareStatement(q);
                    pstmt.setString(1, username);
                    pstmt.setString(2, prn);
                    pstmt.setString(3, password);
                    ResultSet rs = pstmt.executeQuery();

                    if (rs.next()) {
                        setVisible(false);
                        new Rules(username); // Proceed to Rules page with the username
                    } else {
                        JOptionPane.showMessageDialog(null, "Invalid Username, PRN, or Password");
                    }

                    // Optionally store additional user info
                    String q1 = "INSERT INTO info (username, prn) VALUES (?, ?)";
                    PreparedStatement pstmt2 = d.st.getConnection().prepareStatement(q1);
                    pstmt2.setString(1, username);
                    pstmt2.setString(2, prn);
                    pstmt2.executeUpdate();

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        else if (ae.getSource() == signup) {
            setVisible(false);
            new Signup();
        }else {
            setVisible(false);
        }

    }

    public static void main(String[] arg) {
        new Login();
    }
}
