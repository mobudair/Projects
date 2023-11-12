package calculatorProject;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

public class calculatorProject extends JFrame {

    private JButton Num1;
    private JButton Num2;
    private JButton Num3;
    private JButton Num4;
    private JButton Num5;
    private JButton Num6;
    private JButton Num7;
    private JButton Num8;
    private JButton Num9;
    private JButton Num0;
    private JButton Equal;
    private JButton Add;
    private JButton Subtract;
    private JButton Multiply;
    private JButton Divide;
    private JButton Solve;
    private JButton Clear;
    private double TEMP;
    private double SolveTEMP;
    private JTextField jtfResult;

    Boolean addBool = false;
    Boolean subBool = false;
    Boolean divBool = false;
    Boolean mulBool = false;
    
    String display = "";

    public calculatorProject() {
        JPanel p1 = new JPanel();
        p1.setLayout(new GridLayout(7, 1));
        p1.add(Num1 = new JButton("1"));
        p1.add(Num2 = new JButton("2"));
        p1.add(Num3 = new JButton("3"));
        p1.add(Num4 = new JButton("4"));
        p1.add(Num5 = new JButton("5"));
        p1.add(Num6 = new JButton("6"));
        p1.add(Num7 = new JButton("7"));
        p1.add(Num8 = new JButton("8"));
        p1.add(Num9 = new JButton("9"));
        p1.add(Num0 = new JButton("0")); 
        
        JPanel p2 = new JPanel();
        p2.setLayout(new FlowLayout());
        p2.add(jtfResult = new JTextField(12));
        jtfResult.setHorizontalAlignment(JTextField.RIGHT);
        jtfResult.setEditable(false);

        JPanel p3 = new JPanel();
        p3.setLayout(new GridLayout(3, 2));
        p3.add(Add = new JButton("+"));
        p3.add(Subtract = new JButton("-"));
        p3.add(Multiply = new JButton("*"));
        p3.add(Divide = new JButton("/"));
        p3.add(Solve = new JButton("="));
        p3.add(Clear = new JButton("CLEAR"));
        
        JPanel p = new JPanel();
        p.setLayout(new GridLayout());
        p.add(p2, BorderLayout.NORTH);
        p.add(p1, BorderLayout.SOUTH);
        p.add(p3, BorderLayout.EAST);

        add(p);

        Num1.addActionListener(new One());
        Num2.addActionListener(new Two());
        Num3.addActionListener(new Three());
        Num4.addActionListener(new Four());
        Num5.addActionListener(new Five());
        Num6.addActionListener(new Six());
        Num7.addActionListener(new Seven());
        Num8.addActionListener(new Eight());
        Num9.addActionListener(new Nine());
        Num0.addActionListener(new Zero());

        Add.addActionListener(new Addition());
        Subtract.addActionListener(new Subtraction());
        Multiply.addActionListener(new Multiplication());
        Divide.addActionListener(new Division());
        Solve.addActionListener(new Results());
        Clear.addActionListener(new ListenToClear());
    } 

    class ListenToClear implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            jtfResult.setText("");
            addBool = false;
            subBool = false;
            mulBool = false;
            divBool = false;
            TEMP = 0;
            SolveTEMP = 0;
        }
    }

    class One implements ActionListener {
        public void actionPerformed(ActionEvent a) {
            display = jtfResult.getText();
            jtfResult.setText(display + "1");
        }
    }

    class Two implements ActionListener {
        public void actionPerformed(ActionEvent a) {
            display = jtfResult.getText();
            jtfResult.setText(display + "2");
        }
    }

    class Three implements ActionListener {
        public void actionPerformed(ActionEvent a) {
            display = jtfResult.getText();
            jtfResult.setText(display + "3");
        }
    }

    class Four implements ActionListener {
        public void actionPerformed(ActionEvent a) {
            display = jtfResult.getText();
            jtfResult.setText(display + "4");
        }
    }

    class Five implements ActionListener {
        public void actionPerformed(ActionEvent a) {
            display = jtfResult.getText();
            jtfResult.setText(display + "5");
        }
    }

    class Six implements ActionListener {
        public void actionPerformed(ActionEvent a) {
            display = jtfResult.getText();
            jtfResult.setText(display + "6");
        }
    }

    class Seven implements ActionListener {
        public void actionPerformed(ActionEvent a) {
            display = jtfResult.getText();
            jtfResult.setText(display + "7");
        }
    }

    class Eight implements ActionListener {
        public void actionPerformed(ActionEvent a) {
            display = jtfResult.getText();
            jtfResult.setText(display + "8");
        }
    }

    class Nine implements ActionListener {
        public void actionPerformed(ActionEvent a) {
            display = jtfResult.getText();
            jtfResult.setText(display + "9");
        }
    }

    class Zero implements ActionListener {
        public void actionPerformed(ActionEvent a) {
            display = jtfResult.getText();
            jtfResult.setText(display + "0");
        }
    }

    class Addition implements ActionListener {
        public void actionPerformed(ActionEvent a) {
            TEMP = Double.parseDouble(jtfResult.getText());
            jtfResult.setText("");
            addBool = true;
        }
    }

    class Subtraction implements ActionListener {
        public void actionPerformed(ActionEvent a) {
            TEMP = Double.parseDouble(jtfResult.getText());
            jtfResult.setText("");
            subBool = true;
        }
    }

    class Multiplication implements ActionListener {
        public void actionPerformed(ActionEvent a) {
            TEMP = Double.parseDouble(jtfResult.getText());
            jtfResult.setText("");
            mulBool = true;
        }
    }

    class Division implements ActionListener {
        public void actionPerformed(ActionEvent a) {
            TEMP = Double.parseDouble(jtfResult.getText());
            jtfResult.setText("");
            divBool = true;
        }
    }

    class Results implements ActionListener {
        public void actionPerformed(ActionEvent a) {
            SolveTEMP = Double.parseDouble(jtfResult.getText());
            if (addBool == true)
                SolveTEMP = SolveTEMP + TEMP;
            else if ( subBool == true)
                SolveTEMP = TEMP - SolveTEMP;
            else if ( mulBool == true)
                SolveTEMP = SolveTEMP * TEMP;
            else if ( divBool == true)
                            SolveTEMP = SolveTEMP / TEMP;
            jtfResult.setText(  Double.toString(SolveTEMP));

            addBool = false;
            subBool = false;
            mulBool = false;
            divBool = false;
        }
    }

    public static void main(String[] args) {
        calculatorProject calc = new calculatorProject();
        calc.pack();
        calc.setLocationRelativeTo(null);
                calc.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        calc.setVisible(true);
    }

} 

