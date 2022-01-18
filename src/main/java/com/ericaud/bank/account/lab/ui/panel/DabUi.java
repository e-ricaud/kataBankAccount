package com.ericaud.bank.account.lab.ui.panel;

import com.ericaud.bank.account.lab.ui.button.OperationButton;
import com.ericaud.bank.account.service.AccountManager;
import com.ericaud.bank.account.service.AccountPrinter;
import com.ericaud.bank.account.service.AccountPrinterString;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;

public class DabUi extends JFrame {

    private AccountManager accountManager;

    private JTextField textField = new JTextField();

    public DabUi(AccountManager accountManager ){
        super();
        this.accountManager = accountManager;
        this.accountManager.getAccount().setBalance(BigDecimal.valueOf(1000.));
        build();//On initialise notre fenêtre
    }

    private void build(){
        setTitle("Distributeur de billet : Balance " + accountManager.getAccount().getBalance().toString());
        setSize(500,300);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(buildMainPane());
    }

    private JPanel buildMainPane(){
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        panel.setBackground(Color.white);

        textField.setColumns(10); //On lui donne un nombre de colonnes à afficher
        panel.add(textField);

        JButton withdrawal = new JButton(new OperationButton(this,"Withdrawal"));
        JButton deposit = new JButton(new OperationButton(this,"Deposit"));

        JButton history = new JButton("History");
        history.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setContentPane(buildHistoryPane());
                repaint();
                revalidate();
            }
        });

        panel.add(withdrawal);
        panel.add(deposit);
        panel.add(history);

        return panel;
    }

    private JPanel buildHistoryPane(){
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        panel.setBackground(Color.white);

        AccountPrinter printer = new AccountPrinterString(getAccountManager().getAccount());

        JTextArea label = new JTextArea(printer.printOperations());

        JButton back = new JButton("Back");
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setContentPane(buildMainPane());
                repaint();
                revalidate();
            }
        });

        panel.add(label);
        panel.add(back);

        return panel;
    }

    public AccountManager getAccountManager() {
        return accountManager;
    }

    public JTextField getTextField() {
        return textField;
    }
}
