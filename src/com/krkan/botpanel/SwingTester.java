package com.krkan.botpanel;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SwingTester implements PropertyChangeListener {

    private static JFormattedTextField principleTextField;
    private static JFormattedTextField rateTextField;
    private static JFormattedTextField yearsTextField;
    private static JFormattedTextField amountTextField;

    public static void main(String[] args) {
        SwingTester tester = new SwingTester();
        createWindow(tester);
    }

    private static void createWindow(SwingTester tester) {
        JFrame frame = new JFrame("McDeath | Minecraft botter");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        createUI(frame, tester);
        frame.setSize(700, 300);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        ImageIcon alienIcon;

        java.net.URL imgURL = SwingTester.class.getResource("alien.jpg");
        if (imgURL != null) {
            alienIcon = new ImageIcon(imgURL);
            frame.setIconImage(alienIcon.getImage());
        } else {
            JOptionPane.showMessageDialog(frame, "Icon image not found.");
        }

    }


    private static void createUI(final JFrame frame, SwingTester tester) {
        JPanel panel = new JPanel();
        LayoutManager layout = new GridLayout(6, 2);
        panel.setLayout(layout);
        panel.setSize(300, 200);
        panel.setBorder(BorderFactory.createTitledBorder("McDeath | Official"));

        NumberFormat principleFormat = NumberFormat.getNumberInstance();
        principleTextField = new JFormattedTextField(principleFormat);
        principleTextField.setName("Principle");
        principleTextField.setColumns(10);
        JLabel principleLabel = new JLabel("Principle:");
        principleLabel.setLabelFor(principleTextField);
        principleTextField.setValue(100000d);
        principleTextField.addPropertyChangeListener("value", tester);

        NumberFormat rateFormat = NumberFormat.getPercentInstance();
        rateFormat.setMinimumFractionDigits(2);
        rateTextField = new JFormattedTextField(rateFormat);
        rateTextField.setName("Rate");
        rateTextField.setColumns(10);
        JLabel rateLabel = new JLabel("Interest Rate:");
        rateLabel.setLabelFor(rateTextField);
        rateTextField.setValue(0.1);
        rateTextField.addPropertyChangeListener("value", tester);
        yearsTextField = new JFormattedTextField();
        yearsTextField.setName("Years");
        yearsTextField.setColumns(10);
        JLabel yearLabel = new JLabel("Year(s):");
        yearLabel.setLabelFor(yearsTextField);
        yearsTextField.setValue(1d);
        yearsTextField.addPropertyChangeListener("value", tester);

        NumberFormat amountFormat = NumberFormat.getCurrencyInstance();
        amountTextField = new JFormattedTextField(amountFormat);
        amountTextField.setName("Amount");
        amountTextField.setColumns(10);
        amountTextField.setEditable(false);
        JLabel amountLabel = new JLabel("Amount:");
        amountLabel.setLabelFor(amountTextField);
        amountTextField.setValue(110000d);

        DateFormat dateFormat = new SimpleDateFormat("dd MMM yyyy");
        JFormattedTextField today = new JFormattedTextField(dateFormat);
        today.setName("Today");
        today.setColumns(10);
        today.setEditable(false);
        JLabel todayLabel = new JLabel("Date:");
        todayLabel.setLabelFor(today);
        today.setValue(new Date());

        panel.add(principleLabel);
        panel.add(principleTextField);
        panel.add(rateLabel);
        panel.add(rateTextField);
        panel.add(yearLabel);
        panel.add(yearsTextField);
        panel.add(amountLabel);
        panel.add(amountTextField);
        panel.add(todayLabel);
        panel.add(today);
        JPanel mainPanel = new JPanel();
        mainPanel.add(panel);

        JButton okButton = new JButton("START");
        JButton submitButton = new JButton("STOP");

        okButton.addActionListener(e -> JOptionPane.showMessageDialog(frame, "Napad je pokrenut"));
        submitButton.addActionListener(e -> JOptionPane.showMessageDialog(frame, "Napad je pokrenut"));

        panel.add(okButton);
        panel.add(submitButton);

        frame.getContentPane().add(mainPanel, BorderLayout.CENTER);
        panel.setLayout(layout);
        panel.add(new JLabel("Hello World!"));

        frame.getContentPane().add(panel, BorderLayout.CENTER);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        double amount;
        double rate;
        double years;
        double principle;

        principle = ((Number) principleTextField.getValue()).doubleValue();
        rate = ((Number) rateTextField.getValue()).doubleValue() * 100;
        years = ((Number) yearsTextField.getValue()).doubleValue();

        amount = principle + principle * rate * years / 100;
        amountTextField.setValue(amount);
    }
}