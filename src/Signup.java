import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Signup extends JFrame implements ActionListener {
    JButton signup, login,showPasswordBtn;
    JTextField tfCollege, tfName, tfPrn;
    JPasswordField jfpass;
    boolean passwordVisible = false;

    Signup() {
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("signup.jpeg"));
        JLabel image = new JLabel(i1);
        image.setBounds(0, 0, 600, 500);
        add(image);

        JLabel heading = new JLabel("Signup");
        heading.setBounds(750, 60, 300, 45);
        heading.setFont(new Font("Viner Hand ITC", Font.BOLD, 40));
        heading.setForeground(new Color(30, 144, 254));
        add(heading);

        JLabel lblCollege = new JLabel("College Name:");
        lblCollege.setBounds(650, 150, 300, 20);
        lblCollege.setFont(new Font("Mongolian Baiti", Font.BOLD, 18));
        lblCollege.setForeground(new Color(30, 144, 254));
        add(lblCollege);

        tfCollege = new JTextField();
        tfCollege.setBounds(835, 150, 200, 25);
        tfCollege.setFont(new Font("Times New Roman", Font.BOLD, 20));
        add(tfCollege);

        JLabel lblPassword = new JLabel("Password:");
        lblPassword.setBounds(650, 200, 300, 20);
        lblPassword.setFont(new Font("Mongolian Baiti", Font.BOLD, 18));
        lblPassword.setForeground(new Color(30, 144, 254));
        add(lblPassword);

        jfpass = new JPasswordField();
        jfpass.setBounds(835, 200, 160, 25);
        jfpass.setFont(new Font("Times New Roman", Font.BOLD, 20));
        add(jfpass);

        // Eye icon for showing/hiding password
        ImageIcon icon = new ImageIcon(ClassLoader.getSystemResource("view.jpg"));
        Image resizedImage = icon.getImage().getScaledInstance(35, 25, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon = new ImageIcon(resizedImage);
        showPasswordBtn = new JButton(resizedIcon);
        showPasswordBtn.setBounds(1000, 200, 35, 25);
        showPasswordBtn.setBackground(Color.white);
        showPasswordBtn.setBorderPainted(false);
        showPasswordBtn.setFocusPainted(false);
        showPasswordBtn.addActionListener(this);
        add(showPasswordBtn);

        JLabel lblName = new JLabel("Name:");
        lblName.setBounds(650, 250, 300, 20);
        lblName.setFont(new Font("Mongolian Baiti", Font.BOLD, 18));
        lblName.setForeground(new Color(30, 144, 254));
        add(lblName);

        tfName = new JTextField();
        tfName.setBounds(835, 250, 200, 25);
        tfName.setFont(new Font("Times New Roman", Font.BOLD, 20));
        add(tfName);

        JLabel lblPrn = new JLabel("PRN:");
        lblPrn.setBounds(650, 300, 300, 20);
        lblPrn.setFont(new Font("Mongolian Baiti", Font.BOLD, 18));
        lblPrn.setForeground(new Color(30, 144, 254));
        add(lblPrn);

        tfPrn = new JTextField();
        tfPrn.setBounds(835, 300, 200, 25);
        tfPrn.setFont(new Font("Times New Roman", Font.BOLD, 20));
        add(tfPrn);

        signup = new JButton("Signup");
        signup.setBounds(735, 350, 120, 25);
        signup.setBackground(new Color(30, 144, 254));
        signup.setForeground(Color.BLACK);
        signup.addActionListener(this);
        add(signup);

        login = new JButton("Login");
        login.setBounds(915, 350, 120, 25);
        login.setBackground(new Color(30, 144, 254));
        login.setForeground(Color.BLACK);
        login.addActionListener(this);
        add(login);

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
        else if (ae.getSource() == signup) {
            String collegeName = tfCollege.getText();
            String password = new String(jfpass.getPassword());
            String name = tfName.getText();
            String prn = tfPrn.getText();
            if ( collegeName.isEmpty() ||name.isEmpty() || prn.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please fill in all details.");
            }
            else{
                    try {
                        database d = new database();
                        String query = "INSERT INTO users (college_name, password, name, prn) VALUES (?, ?, ?, ?)";
                        PreparedStatement pstmt = d.st.getConnection().prepareStatement(query);
                        pstmt.setString(1, collegeName);
                        pstmt.setString(2, password);
                        pstmt.setString(3, name);
                        pstmt.setString(4, prn);
                        pstmt.executeUpdate();

                        JOptionPane.showMessageDialog(null, "Signup Successful");
                        setVisible(false);
                        new Login();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
            }
        } else if (ae.getSource() == login) {
            setVisible(false);
            new Login();
        }
    }

    public static void main(String[] args) {
        new Signup();
    }
}
