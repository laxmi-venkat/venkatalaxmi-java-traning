package org.example;

import java.util.Scanner;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Calculator1 extends JFrame implements ActionListener
{


        private JTextField display;
        private StringBuilder currentInput;
        private double result;
        private String lastOperation;

        public Calculator1() {
            currentInput = new StringBuilder();
            result = 0;
            lastOperation = "";

            setTitle("Calculator");
            setSize(400, 600);
            setDefaultCloseOperation(EXIT_ON_CLOSE);
            setLocationRelativeTo(null);
            setLayout(new BorderLayout());

            display = new JTextField();
            display.setFont(new Font("Arial", Font.PLAIN, 36));
            display.setEditable(false);
            add(display, BorderLayout.NORTH);

            JPanel panel = new JPanel();
            panel.setLayout(new GridLayout(4, 4, 10, 10));

            String[] buttons = {
                    "7", "8", "9", "/",
                    "4", "5", "6", "*",
                    "1", "2", "3", "-",
                    "0", "C", "=", "+"
            };

            for (String text : buttons) {
                JButton button = new JButton(text);
                button.setFont(new Font("Arial", Font.PLAIN, 36));
                button.addActionListener(this);
                panel.add(button);
            }

            add(panel, BorderLayout.CENTER);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();

            if (command.charAt(0) >= '0' && command.charAt(0) <= '9') {
                currentInput.append(command);
                display.setText(currentInput.toString());
            } else if (command.equals("C")) {
                currentInput.setLength(0);
                result = 0;
                lastOperation = "";
                display.setText("");
            } else if (command.equals("=")) {
                if (currentInput.length() > 0) {
                    double currentNumber = Double.parseDouble(currentInput.toString());
                    result = calculate(result, currentNumber, lastOperation);
                    display.setText(String.valueOf(result));
                    currentInput.setLength(0);
                }
            } else {
                if (currentInput.length() > 0) {
                    double currentNumber = Double.parseDouble(currentInput.toString());
                    result = calculate(result, currentNumber, lastOperation);
                    display.setText(String.valueOf(result));
                    currentInput.setLength(0);
                }
                lastOperation = command;
            }
        }

        private double calculate(double num1, double num2, String operation) {
            switch (operation) {
                case "+": return num1 + num2;
                case "-": return num1 - num2;
                case "*": return num1 * num2;
                case "/": return num1 / num2;
                default: return num2;
            }
        }

        public static void main(String[] args) {
            SwingUtilities.invokeLater(() -> {
                Calculator1 calculator = new Calculator1();
                calculator.setVisible(true);
            });
        }
    }



