package parabolaSimulation.frame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.JMenu;
import javax.swing.JLabel;
import java.awt.Font;

import javax.swing.JSplitPane;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JSlider;
import javax.swing.KeyStroke;
import java.awt.event.KeyEvent;

public class MainFrame {
    private JFrame frame;
    private JPanel panel = new JPanel();
    private JTextField textFieldA;
    private JTextField textFieldB;
    private JTextField textFieldC;
    private JSplitPane splitPane_Enter;
    private JMenuBar menuBar;

    private double a, b, c;

    private parabolaSimulation.draw.DrawParabola chart = new parabolaSimulation.draw.DrawParabola();

    private int width, height;
    private int halfWidth, halfHeight;

    private Color backColor = Color.YELLOW;
    private JTextField textFieldDelete;

    public MainFrame() {
        this("");
    }

    public MainFrame(String title) {

        chart.draw(Color.YELLOW);

        //set UI
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        //set JFrame
        frame = new JFrame(title);
        frame.setFont(new Font("Dialog", Font.PLAIN, 43));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        width = 1400;
        height = 800;
        halfWidth = width / 2;
        halfHeight = height / 2;
        frame.setBounds(Toolkit.getDefaultToolkit().getScreenSize().width / 2 - halfWidth,
                Toolkit.getDefaultToolkit().getScreenSize().height / 2 - halfHeight, width, height);
        frame.setResizable(false);

        //set JPanel
        panel = new JPanel();
        panel.setBorder(new EmptyBorder(5, 5, 5, 5));
        panel.setLayout(new BorderLayout(0, 0));
        frame.setContentPane(panel);

        //set MenuBar, Chart, and Enter
        setMenuBar();
        setChartAndEnter();

        frame.setFocusable(true);
        frame.requestFocus();
        frame.pack();
        frame.setVisible(true);
    }

    private void changeColor(Color backColor) {
        this.backColor = backColor;
        panel.setBackground(backColor);
        // chart.changeColor(backColor);

        // panel.repaint();

        panel.removeAll();
        panel.add(menuBar, BorderLayout.NORTH);
        panel.add(splitPane_Enter, BorderLayout.SOUTH);

        // panel.repaint();
        chart.changeColor(backColor);
        panel.add(chart.panel, BorderLayout.CENTER);
        panel.updateUI();

    }

    private void setMenuBar() {
        menuBar = new JMenuBar();
        menuBar.setOpaque(false);
        menuBar.setBorderPainted(false);
        panel.add(menuBar, BorderLayout.NORTH);

        panel.setBackground(backColor);

        JMenu menu_Color = new JMenu("Color");
        menu_Color.setToolTipText("Background Color");
        menuBar.add(menu_Color);
        menu_Color.setMnemonic(KeyEvent.VK_C);

        JMenuItem menu_Colour_White = new JMenuItem("White");
        menu_Colour_White.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_W, InputEvent.ALT_DOWN_MASK));
        menu_Colour_White.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changeColor(Color.WHITE);
            }
        });
        menu_Color.add(menu_Colour_White);

        JMenuItem menu_Colour_Yellow = new JMenuItem("Yellow");
        menu_Colour_Yellow.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Y, InputEvent.ALT_DOWN_MASK));
        menu_Colour_Yellow.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changeColor(Color.YELLOW);
            }
        });
        menu_Color.add(menu_Colour_Yellow);

        JMenuItem menu_Colour_LightGray = new JMenuItem("LightGray");
        menu_Colour_LightGray.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L, InputEvent.ALT_DOWN_MASK));
        menu_Colour_LightGray.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changeColor(Color.LIGHT_GRAY);
            }
        });
        menu_Color.add(menu_Colour_LightGray);

        JMenuItem menu_Colour_Green = new JMenuItem("Green");
        menu_Colour_Green.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_G, InputEvent.ALT_DOWN_MASK));
        menu_Colour_Green.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changeColor(Color.GREEN);
            }
        });
        menu_Color.add(menu_Colour_Green);

        JMenu menu_Speed = new JMenu("Speed");
        menu_Speed.setMnemonic(KeyEvent.VK_S);
        menu_Speed.setToolTipText("Set speed of generating parabola");
        menuBar.add(menu_Speed);

        //JLabel label_speed = new JLabel("Sleep 1 milliseconds to 100 millisecond");
        //menu_Speed.add(label_speed);

        JSlider slider = new JSlider(1, 101, 51);
        slider.setPaintLabels(true);
        slider.setPaintTicks(true);
        slider.setMinorTickSpacing(5);
        slider.setMajorTickSpacing(25);
        slider.setToolTipText("Sleep 1 milliseconds to 100 millisecond");
        slider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                parabolaSimulation.draw.DynamicParabola.sleep = slider.getValue();
            }
        });
        menu_Speed.add(slider);

        JMenu menu_Delete = new JMenu("Delete");
        menu_Delete.setMnemonic(KeyEvent.VK_D);
        menu_Delete.setToolTipText("Delete parabola");
        menuBar.add(menu_Delete);

        JMenuItem menu_Delete_Reset = new JMenuItem("Reset");
        menu_Delete_Reset.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, InputEvent.ALT_DOWN_MASK));
        menu_Delete_Reset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // chart.reset();

                // panel.remove(chart.panel);
                panel.removeAll();
                panel.add(menuBar, BorderLayout.NORTH);
                panel.add(splitPane_Enter, BorderLayout.SOUTH);

                panel.repaint();
                chart = new parabolaSimulation.draw.DrawParabola(backColor);
                panel.add(chart.panel, BorderLayout.CENTER);
                panel.updateUI();

                // chart = chart.resetChart(backColor);
                // chart.panel.repaint();
                // panel.remove(chart.panel);
                // panel.repaint();

                // chart = new parabolaSimulation.draw.DrawParabola(backColor);
                // panel.add(chart.panel, BorderLayout.CENTER);
                // panel.repaint();

                // panel.updateUI();
                // frame.pack();

                // chart = new parabolaSimulation.draw.DrawParabola(backColor);
                // panel.updateUI();
            }
        });
        menu_Delete.add(menu_Delete_Reset);

        JSplitPane splitPane_Delete = new JSplitPane();
        splitPane_Delete.setEnabled(false);
        menu_Delete.add(splitPane_Delete);

        JLabel label_Delete = new JLabel("    Order of Parabola: ");
        splitPane_Delete.setLeftComponent(label_Delete);

        JSplitPane splitPane_Delete1 = new JSplitPane();
        splitPane_Delete1.setEnabled(false);
        splitPane_Delete.setRightComponent(splitPane_Delete1);

        textFieldDelete = new JTextField();
        splitPane_Delete1.setLeftComponent(textFieldDelete);
        textFieldDelete.setColumns(5);

        JButton menu_Delete_Button = new JButton("Enter");
        menu_Delete_Button.setMnemonic(KeyEvent.VK_ENTER);
        menu_Delete_Button.setForeground(Color.BLACK);
        menu_Delete_Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                chart.deleteParabola(Integer.parseInt(textFieldDelete.getText()) - 1);
                // panel.repaint();
            }
        });
        splitPane_Delete1.setRightComponent(menu_Delete_Button);

        JMenu menu_Help = new JMenu("Help");
        menu_Help.setMnemonic(KeyEvent.VK_H);
        menu_Help.setToolTipText("Others");
        menuBar.add(menu_Help);

        JMenuItem menu_Help_About = new JMenuItem("About");
        menu_Help_About.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, InputEvent.ALT_DOWN_MASK));
        menu_Help_About.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new parabolaSimulation.About();
            }
        });
        menu_Help.add(menu_Help_About);

        JMenuItem menu_Help_Keys = new JMenuItem("Keys");
        menu_Help_Keys.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_K, InputEvent.ALT_DOWN_MASK));
        menu_Help_Keys.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new parabolaSimulation.Keys();
            }
        });
        menu_Help.add(menu_Help_Keys);

        JMenuItem menu_Help_Exit = new JMenuItem("Exit");
        menu_Help_Exit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, InputEvent.ALT_DOWN_MASK));
        menu_Help_Exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });
        menu_Help.add(menu_Help_Exit);
    }

    private void setChartAndEnter() {
        splitPane_Enter = new JSplitPane();
        splitPane_Enter.setOpaque(false);
        splitPane_Enter.setEnabled(false);
        panel.add(splitPane_Enter, BorderLayout.SOUTH);

        JSplitPane splitPane_Enter1 = new JSplitPane();
        splitPane_Enter1.setOpaque(false);
        splitPane_Enter1.setEnabled(false);
        splitPane_Enter.setRightComponent(splitPane_Enter1);

        JSplitPane splitPane_Enter2 = new JSplitPane();
        splitPane_Enter2.setOpaque(false);
        splitPane_Enter2.setEnabled(false);
        splitPane_Enter1.setRightComponent(splitPane_Enter2);

        JSplitPane splitPane_Enter3 = new JSplitPane();
        splitPane_Enter3.setOpaque(false);
        splitPane_Enter3.setEnabled(false);
        splitPane_Enter2.setRightComponent(splitPane_Enter3);

        textFieldC = new JTextField();
        textFieldC.setFont(new Font("Arial", Font.PLAIN, 22));
        textFieldC.setText("c=");
        textFieldC.setOpaque(false);
        splitPane_Enter3.setLeftComponent(textFieldC);
        textFieldC.setColumns(7);
        textFieldC.setToolTipText("Example: c=5.5");

        textFieldB = new JTextField();
        textFieldB.setFont(new Font("Arial", Font.PLAIN, 22));
        textFieldB.setText("b=");
        textFieldB.setOpaque(false);
        splitPane_Enter2.setLeftComponent(textFieldB);
        textFieldB.setColumns(7);
        textFieldB.setToolTipText("Example: b=8.6");

        textFieldA = new JTextField();
        textFieldA.setBackground(Color.WHITE);
        textFieldA.setFont(new Font("Arial", Font.PLAIN, 22));
        textFieldA.setText("a=");
        textFieldA.setOpaque(false);
        splitPane_Enter1.setLeftComponent(textFieldA);
        textFieldA.setColumns(7);
        textFieldA.setToolTipText("Example: a=12.3");

        JLabel label_Enter = new JLabel("Enter a,b,c of ax^2+bx+c ");
        label_Enter.setToolTipText("Formula of parabola: y=ax^2+bx+c");
        label_Enter.setFont(new Font("Arial", Font.PLAIN, 22));
        splitPane_Enter.setLeftComponent(label_Enter);

        JButton buttonEnter = new JButton("Enter");
        buttonEnter.setToolTipText("Generate parabola");
        buttonEnter.setFont(new Font("Arial", Font.PLAIN, 22));
        frame.getRootPane().setDefaultButton(buttonEnter);
        buttonEnter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    a = Double.parseDouble(textFieldA.getText().substring(2));
                    b = Double.parseDouble(textFieldB.getText().substring(2));
                    c = Double.parseDouble(textFieldC.getText().substring(2));
                } catch (Exception e1) {
                    new parabolaSimulation.frame.ErrorFrame(e1);
                    return;
                }

                chart.draw(a, b, c);

				/*try {
					Thread.sleep(9000);
				} catch (Exception e2) {}*/

                //panel.add(dynamicParabola.getChart(), BorderLayout.CENTER);
                //panel.updateUI();
            }
        });
        splitPane_Enter3.setRightComponent(buttonEnter);

        panel.add(chart.panel, BorderLayout.CENTER);
    }
}
