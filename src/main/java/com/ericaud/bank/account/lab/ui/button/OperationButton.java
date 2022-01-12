package com.ericaud.bank.account.lab.ui.button;

import com.ericaud.bank.account.exception.InvalidOperationException;
import com.ericaud.bank.account.lab.ui.panel.DabUi;
import com.ericaud.bank.account.model.OperationType;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class OperationButton extends AbstractAction {

    private DabUi fenetre;
    private OperationType operationType;

    public OperationButton(DabUi fenetre, String texte){
        super(texte);

        if(texte.equals("Withdrawal")) {
            this.operationType = OperationType.WITHDRAWAL;
        } else {
            this.operationType = OperationType.DEPOSIT;
        }

        this.fenetre = fenetre;
    }

    public void actionPerformed(ActionEvent e) {
        String nombre1String = fenetre.getTextField().getText();//On récupère la valeur dans le premier champ
        long nombre1 = Long.parseLong(nombre1String);//On convertit cette valeur en un nombre

        switch (this.operationType) {
            case WITHDRAWAL:
                try {
                    this.fenetre.getAccountManager().doWithdraw(nombre1);
                } catch (InvalidOperationException ex) {
                    ex.printStackTrace();
                }
                break;
            case DEPOSIT:
                try {
                    this.fenetre.getAccountManager().doDeposit(nombre1);
                } catch (InvalidOperationException ex) {
                    ex.printStackTrace();
                }
                break;
        }

        this.fenetre.getTextField().setText("");
        this.fenetre.setTitle("Distributeur de billet : Balance " + this.fenetre.getAccountManager().getAccount().getBalance().toString());

    }


}