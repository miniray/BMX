package Controlador;

import java.awt.FlowLayout;
import java.awt.HeadlessException;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class Main extends JFrame {
    public Main() throws HeadlessException {
        setSize(600, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        JLabel label1 = new JLabel("Username :", JLabel.RIGHT);
        JLabel label2 = new JLabel("Password :", JLabel.RIGHT);
        JLabel label3 = new JLabel("Confirm Password :", JLabel.RIGHT);
        JLabel label4 = new JLabel("Remember Me!", JLabel.LEFT);
        JLabel label5 = new JLabel("Hello.", JLabel.CENTER);

        label5.setVerticalAlignment(1);
        label5.setToolTipText("A tool tip with me!");

        getContentPane().add(label1);
        getContentPane().add(label2);
        getContentPane().add(label3);
        getContentPane().add(label4);
        getContentPane().add(label5);
    }

    public static void main(String[] args) {
        new Main().setVisible(true);
    }
}