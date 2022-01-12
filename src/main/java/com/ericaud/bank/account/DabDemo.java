package com.ericaud.bank.account;

import com.ericaud.bank.account.lab.ui.panel.DabUi;
import com.ericaud.bank.account.model.Account;
import com.ericaud.bank.account.service.AccountManager;

import javax.swing.*;

public class DabDemo {

    public static void main(String[] args) {
        AccountManager accountManager = new AccountManager(new Account());

        SwingUtilities.invokeLater(new Runnable(){
            public void run(){
                DabUi fenetre = new DabUi(accountManager);
                fenetre.setVisible(true);
            }
        });
    }

}
