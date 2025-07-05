package com.example.mcpnlp;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class Main {
    public static void main(String[] args) {
        final JTextField inputField = new JTextField();
        final JButton searchButton = new JButton("Search");
        final JTextArea resultArea = new JTextArea();
        JFrame frame = new JFrame("MCP NLP Search");
        resultArea.setLineWrap(true);

        frame.setSize(500, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);

        inputField.setBounds(20, 20, 440, 30);
        searchButton.setBounds(20, 60, 100, 30);
        resultArea.setBounds(20, 100, 440, 150);

        frame.add(inputField);
        frame.add(searchButton);
        frame.add(resultArea);

        searchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String input = inputField.getText();
                String field = InputParser.extractField(input);
                String searchValue = InputParser.extractSearchValue(input);
                try {
                    String json = ApiService.fetchUserData(searchValue);
                    String result = ResponseExtractor.getFieldValue(json, field);
                    resultArea.setText(result);
                } catch (Exception ex) {
                    resultArea.setText("Error: " + ex.getMessage());
                }
            }
        });

        frame.setVisible(true);
    }
}
