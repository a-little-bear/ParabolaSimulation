package parabolaSimulation.frame;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;

public class ErrorFrame extends JFrame {
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField textField;

    public static void main(String[] args) {
        new ErrorFrame();
    }

    public ErrorFrame() {
        this(new Exception("Error"));
    }

    public ErrorFrame(Exception e) {
        setTitle("Error");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);

        textField = new JTextField(e.toString());
        textField.setFont(new Font("Georgia", Font.BOLD + Font.ITALIC, 30));
        System.out.print(textField.getSize().width);
        contentPane.add(textField, BorderLayout.CENTER);
        //textField.setColumns(10);

        int width = 1000;
        int height = 125;
        setBounds(Toolkit.getDefaultToolkit().getScreenSize().width / 2 - width / 2,
                Toolkit.getDefaultToolkit().getScreenSize().height / 2 - height / 2, width, height);

        setVisible(true);
    }

}
