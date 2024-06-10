package parabolaSimulation.frame;

import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.InputStream;
import java.io.InputStreamReader;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;

public class TextFrame {
    private JFrame frame;
    private JTextArea textArea = new JTextArea();

    private String font;
    private int style;
    private int size;

    public TextFrame() {
        this("", "default");
    }

    public TextFrame(String name, String textName) {
        frame = new JFrame(name);

        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        int height = 400, width = 600, halfHeight = height / 2, halfWidth = width / 2;
        frame.setBounds(Toolkit.getDefaultToolkit().getScreenSize().width / 2 - halfWidth,
                Toolkit.getDefaultToolkit().getScreenSize().height / 2 - halfHeight, width, height);
        frame.setResizable(false);

        JScrollPane scroll = new JScrollPane();
        scroll.setOpaque(false);
        scroll.setHorizontalScrollBarPolicy(
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroll.setVerticalScrollBarPolicy(
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        textArea.setEditable(false);
        try {
            InputStream in = getClass().getResourceAsStream("/parabolaSimulation/text/" + textName + ".txt");
            textArea.read(new InputStreamReader(in), "Reading about text");
        } catch (Exception e) {
            textArea.removeAll();
            textArea.append("Error! " + e);
        }
        scroll.setViewportView(textArea);
        frame.add(scroll, BorderLayout.CENTER);


        setMenuBar();

        frame.setVisible(true);
    }

    public void setMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        frame.setJMenuBar(menuBar);

        textArea.setBackground(Color.white);

        JMenu menu_Colour = new JMenu("Colour");
        menuBar.add(menu_Colour);

        JMenuItem menu_Colour_White = new JMenuItem("White");
        menu_Colour_White.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textArea.setBackground(Color.white);
            }
        });
        menu_Colour.add(menu_Colour_White);

        JMenuItem menu_Colour_Yellow = new JMenuItem("Yellow");
        menu_Colour_Yellow.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textArea.setBackground(Color.yellow);
            }
        });
        menu_Colour.add(menu_Colour_Yellow);

        JMenuItem menu_Colour_LightGray = new JMenuItem("LightGray");
        menu_Colour_LightGray.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textArea.setBackground(Color.lightGray);
            }
        });
        menu_Colour.add(menu_Colour_LightGray);

        JMenuItem menu_Colour_Green = new JMenuItem("Green");
        menu_Colour_Green.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textArea.setBackground(Color.green);
            }
        });
        menu_Colour.add(menu_Colour_Green);

        font = "Georgia";
        style = Font.PLAIN;
        size = 20;
        textArea.setFont(new Font(font, style, size));

        JMenu menu_Size = new JMenu("Size");
        menuBar.add(menu_Size);

        JSpinner spinner = new JSpinner();
        spinner.setValue(size);
        spinner.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                size = (int) spinner.getValue();
                textArea.setFont(new Font(font, style, size));
            }
        });
        menu_Size.add(spinner);

        JMenu menu_Font = new JMenu("Font");
        menuBar.add(menu_Font);

        JMenuItem menu_Font_Georgia = new JMenuItem("Georgia");
        menu_Font_Georgia.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                font = "Georgia";
                textArea.setFont(new Font(font, style, size));
            }
        });
        menu_Font.add(menu_Font_Georgia);

        JMenuItem menu_Font_Arial = new JMenuItem("Arial");
        menu_Font_Arial.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                font = "Arial";
                textArea.setFont(new Font(font, style, size));
            }
        });
        menu_Font.add(menu_Font_Arial);

        JMenuItem menu_Font_TimesNewRoman = new JMenuItem("Times New Roman");
        menu_Font_TimesNewRoman.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                font = "Times New Roman";
                textArea.setFont(new Font(font, style, size));
            }
        });
        menu_Font.add(menu_Font_TimesNewRoman);

        JMenuItem menu_Font_Courier = new JMenuItem("Courier");
        menu_Font_Courier.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                font = "Courier";
                textArea.setFont(new Font(font, style, size));
            }
        });
        menu_Font.add(menu_Font_Courier);

        JMenuItem menu_Font_Verdana = new JMenuItem("Verdana");
        menu_Font_Verdana.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                font = "Verdana";
                textArea.setFont(new Font(font, style, size));
            }
        });
        menu_Font.add(menu_Font_Verdana);

        JMenu menu_Style = new JMenu("Style");
        menuBar.add(menu_Style);

        JMenuItem menu_Style_Plain = new JMenuItem("Plain");
        menu_Style_Plain.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                style = Font.PLAIN;
                textArea.setFont(new Font(font, style, size));
            }
        });
        menu_Style.add(menu_Style_Plain);

        JMenuItem menu_Style_Bold = new JMenuItem("Bold");
        menu_Style_Bold.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                style = Font.BOLD;
                textArea.setFont(new Font(font, style, size));
            }
        });
        menu_Style.add(menu_Style_Bold);

        JMenuItem menu_Style_Italic = new JMenuItem("Italic");
        menu_Style_Italic.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                style = Font.ITALIC;
                textArea.setFont(new Font(font, style, size));
            }
        });
        menu_Style.add(menu_Style_Italic);

        JMenuItem menu_Style_BoldAndItalic = new JMenuItem("Bold + Italic");
        menu_Style_BoldAndItalic.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                style = Font.BOLD + Font.ITALIC;
                textArea.setFont(new Font(font, style, size));
            }
        });
        menu_Style.add(menu_Style_BoldAndItalic);
    }
}
