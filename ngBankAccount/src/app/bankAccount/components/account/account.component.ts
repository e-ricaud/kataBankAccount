import { Component, OnInit } from '@angular/core';
import { Account } from '../../model/account';
import { AccountService } from '../../services/account.service';
import { MatDialog } from '@angular/material/dialog'
import { OperationDialogComponent } from '../operation-dialog/operation-dialog.component';
import { Subscription } from 'rxjs';
import { OperationType } from '../../model/operationType';
import { OperationForm } from 'src/app/shared/model/operation-form';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-account',
  templateUrl: './account.component.html'
  // styleUrls: ['./account.component.scss']
})
export class AccountComponent implements OnInit {

  accounts: Account[] = [];

  private subscriptions: Subscription = new Subscription();

  constructor(
    private dialog: MatDialog,
    private route: ActivatedRoute,
    private router : Router,
    private accountService: AccountService) {
  }

  ngOnInit() {
    this.findAllAccount();
  }

  findAllAccount() {
    this.subscriptions.add(
      this.accountService.findAllAccount().subscribe(data => {
        this.accounts = data;
      })
    )
  }

  refreshAccountById(id:number) {
    this.subscriptions.add(
      this.accountService.findById(id).subscribe(data => {
        const index = this.accounts.findIndex((x) => x.id === id)
        this.accounts[index] = data;
      })
    )
  }

  onHistoryAccount(account: Account) {
    this.router.navigate(['/accounts/history',account.id]);
  }

  onOperation(): void {
    console.log('on opération');
    const dialogRef = this.dialog.open(OperationDialogComponent, {width: '600px', disableClose: true})

    dialogRef.afterClosed().subscribe(form => {
      console.log('operationType : ' + form.operationType )
      let accountOp = this.accounts[0];
      accountOp.operations = [];
      const operationForm : OperationForm = {
        account: accountOp,
        amount: form.amount
      };
      if(form.operationType === OperationType.DEPOSIT) {
        this.subscriptions.add(
          this.accountService.doDeposit(operationForm).subscribe(
            response  => {
              if(response.code === 'ok') {
                this.refreshAccountById(1);
              }
            }
          )
        );
      } else if (form.operationType === OperationType.WITHDRAWAL) {
        this.subscriptions.add(
          this.accountService.doWithdraw(operationForm).subscribe(
            response  => {
              if(response.code === 'ok') {
                this.refreshAccountById(1);
              }
            }
          )
        );
      }
    });
  }

}
