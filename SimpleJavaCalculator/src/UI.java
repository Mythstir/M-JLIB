

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.SwingConstants;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class UI extends JFrame {

 //Initiate Buttons
 private JButton Numbered_button[], Add_button, Minus_button, Multiply_button, Divide_button, Clear_button, Egal_button, Point_button;

 //Initiate Buttons Value
 private final String[] button_value = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};

 //Initiate Panel
 private final DrawPanel panel_button;

 //Initiate Label
 private JLabel label;

 //Initiate the needed operation keeper
 private String operation = "";

 private double number = 0;

 //To keep in order the operations
 private boolean Exe_operate = false, upgrade = false;

 //================Calculator Constructor================//
 public UI() {

  this.setTitle("Calculator v1.0");
  this.setSize(300, 450);
  this.setLocationRelativeTo(null);
  this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  this.setResizable(false);
  this.setVisible(true);
 
  //To create a pleasant GUI
  panel_button = new DrawPanel();

  this.getContentPane().add(panel_button);
  panel_button.setLayout(null);

  //Name binding the buttons
  Add_button = new JButton("+");
  Minus_button = new JButton("-");
  Multiply_button = new JButton("*");
  Divide_button = new JButton("/");
  Clear_button = new JButton("C");
  Egal_button = new JButton("=");
  Point_button = new JButton("."); 
 
  Numbered_button = new JButton[10];  

  //Name binding the labels
  label = new JLabel("0",SwingConstants.RIGHT);
  label.setLocation(10, 40);
  label.setSize(270, 25);
  label.setFont(new Font("Arial", Font.PLAIN, 25));
  panel_button.add(label);

  //Add numbered buttons onto panel
  for (int i = 0; i < button_value.length; i++) {
   Numbered_button[i] = new JButton(button_value[i]);
   Numbered_button[i].setFont(new Font("Aria", Font.PLAIN, 20));
   switch(i){
    case 0: 
     panel_button.add(Numbered_button[i]);
     Numbered_button[i].setBounds(10, 80, 60, 60);  
     Numbered_button[i].addActionListener(new Figure());
     break;
    case 3:
     panel_button.add(Numbered_button[i]);
     Numbered_button[i].setBounds(10, 160, 60, 60);
     Numbered_button[i].addActionListener(new Figure());
     break;
    case 6:
     panel_button.add(Numbered_button[i]);
     Numbered_button[i].setBounds(10, 240, 60, 60);
     Numbered_button[i].addActionListener(new Figure());
     break;
    case 1:
     panel_button.add(Numbered_button[i]);
     Numbered_button[i].setBounds(80, 80, 60, 60);
     Numbered_button[i].addActionListener(new Figure());
     break;
    case 4:
     panel_button.add(Numbered_button[i]);
     Numbered_button[i].setBounds(80, 160, 60, 60);
     Numbered_button[i].addActionListener(new Figure());
     break;
    case 7:
     panel_button.add(Numbered_button[i]);
     Numbered_button[i].setBounds(80, 240, 60, 60);
     Numbered_button[i].addActionListener(new Figure());
     break;
    case 2:
     panel_button.add(Numbered_button[i]);
     Numbered_button[i].setBounds(150, 80, 60, 60);
     Numbered_button[i].addActionListener(new Figure());
     break;
    case 5:
     panel_button.add(Numbered_button[i]);
     Numbered_button[i].setBounds(150, 160 ,60 ,60);
     Numbered_button[i].addActionListener(new Figure());
     break;
    case 8:
     panel_button.add(Numbered_button[i]);
     Numbered_button[i].setBounds(150, 240, 60, 60);
     Numbered_button[i].addActionListener(new Figure());
     break;
    case 9:
     panel_button.add(Numbered_button[i]);
     Numbered_button[i].setBounds(10, 320, 60, 60);  
     Numbered_button[i].addActionListener(new Figure());
     break;
   }
  }

  //Add operation buttons onto panel
  panel_button.add(Add_button);
  Add_button.addActionListener(new Addition());
  Add_button.setBounds(230, 161, 50, 40);

  panel_button.add(Minus_button);
  Minus_button.addActionListener(new Substraction());
  Minus_button.setBounds(230, 221, 50, 40);

  panel_button.add(Multiply_button);
  Multiply_button.addActionListener(new Multiplication());
  Multiply_button.setBounds(230, 281, 50, 40);

  panel_button.add(Divide_button);
  Divide_button.addActionListener(new Division());
  Divide_button.setBounds(230, 339, 50, 40);

  panel_button.add(Clear_button);
  Clear_button.addActionListener(new Clear());
  Clear_button.setBounds(220, 80, 70, 70);
  Clear_button.setFont(new Font("Aria", Font.BOLD, 25));
  Clear_button.setForeground(Color.RED);

  panel_button.add(Egal_button);
  Egal_button.addActionListener(new Egal());
  Egal_button.setBounds(80, 320, 60, 60);
  Egal_button.setFont(new Font("Aria", Font.BOLD, 16));

  panel_button.add(Point_button);
  Point_button.addActionListener(new Point());
  Point_button.setBounds(150, 320, 60, 60);
  Point_button.setFont(new Font("Aria", Font.BOLD, 25));

  panel_button.add(label);
 }

 //========================Computations========================//
 private void Calculate() {
  if(operation.equals("+")) {
   number = number + Double.valueOf(label.getText()).doubleValue();
   label.setText(String.valueOf(number));
  }

  if(operation.equals("-")) {
   number = number - Double.valueOf(label.getText()).doubleValue();
   label.setText(String.valueOf(number));
  }
  
  if(operation.equals("*")) {
   number = number * Double.valueOf(label.getText()).doubleValue();
   label.setText(String.valueOf(number));
  }

  if(operation.equals("/")) {
   if (number!= 0) {
    number = number / Double.valueOf(label.getText()).doubleValue();
    label.setText(String.valueOf(number));
   }
   else {
    label.setText(String.valueOf(Double.POSITIVE_INFINITY));
   }
  }
 }

 //====================Handlers====================//
 class Figure implements ActionListener {
  public void actionPerformed(ActionEvent e) {
   String str = ((JButton)e.getSource()).getText();
   if(upgrade) {
    upgrade = false;
   }
   else{
    if(!label.getText().equals("0"))
     str = label.getText() + str;
   }
   label.setText(str);
  }
 }

 //The Decimalization anonymous class
 class Point implements ActionListener {
  public void actionPerformed(ActionEvent e) {
   String str = ((JButton)e.getSource()).getText();
   if(upgrade) {
    upgrade = false;
   }
   else {
    if(!label.getText().equals("0"))
     str = label.getText() + str;
   }
   label.setText(str);
  }
 }

 //The Addition anonymous class
 class Addition implements ActionListener {
  public void actionPerformed(ActionEvent e) {
   if(Exe_operate) { 
    Calculate();
    label.setText(String.valueOf(number));
   }
   else {
    number = Double.valueOf(label.getText()).doubleValue();
    Exe_operate = true;
   }
   operation = "+";
   upgrade = true;
  } 
 }
 
 //The Multiplication anonymous class
 class Multiplication implements ActionListener {
  public void actionPerformed(ActionEvent e) {
   if(Exe_operate) { 
    Calculate();
    label.setText(String.valueOf(number));
   }
   else {
    number = Double.valueOf(label.getText()).doubleValue();
    Exe_operate = true;
   }
   operation = "*";
   upgrade = true;
  }
 }
 
 //The Division anonymous class
 class Division implements ActionListener {
  public void actionPerformed(ActionEvent e) {
   if(Exe_operate) { 
    Calculate();
    label.setText(String.valueOf(number));
   }
   else {
    number = Double.valueOf(label.getText()).doubleValue();
    Exe_operate = true;
   }
   operation = "/";
   upgrade = true;
  }
 }
  
 //The Substraction anonymous class
 class Substraction implements ActionListener {
  public void actionPerformed(ActionEvent e) {
   if(Exe_operate) { 
    Calculate();
    label.setText(String.valueOf(number));
   }
   else {
    number = Double.valueOf(label.getText()).doubleValue();
    Exe_operate = true;
   }
   operation = "-";
   upgrade = true;
  }
 }

 //The Clear anonymous class
 class Clear implements ActionListener {
  public void actionPerformed(ActionEvent e) {
   Exe_operate = false;
   upgrade = true;
   number = 0;
   label.setText("0");
   operation = "";
  }
 } 

 //The Equalization anonymous class
 class Egal implements ActionListener {
  public void actionPerformed(ActionEvent e) {
   Calculate();  
   upgrade = true;
   Exe_operate = false;
  }
 }

 //The JPanel Manager
 class DrawPanel extends JPanel {
  @Override
  public void paintComponent(Graphics g) {
   Graphics2D g2 = (Graphics2D) g;
   super.paintComponent(g2);
   g2.setPaint(Color.lightGray);
   g2.fill(new Rectangle2D.Double(0, 0, 300, 450));
   g2.setPaint(Color.gray);
   g2.fill(new Rectangle2D.Double(10, 10, 280, 60));
  }
 }
}
