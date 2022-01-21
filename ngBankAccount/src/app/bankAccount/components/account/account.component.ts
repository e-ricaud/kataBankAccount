import { Component, OnInit } from '@angular/core';
import { Account } from '../../model/account';
import { AccountService } from '../../services/account.service';
import { MatDialog } from '@angular/material/dialog'
import { OperationDialogComponent } from '../operation-dialog/operation-dialog.component';
import { Subscription } from 'rxjs';

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
    private userService: AccountService) {
  }

  ngOnInit() {
    this.userService.findById(1).subscribe(data => {
      console.log('data serv ' + data.firstName);
      this.accounts = [data];
      console.log('size' + this.accounts.length)
    });
  }

  onOperation(): void {
    console.log('on opération');
    const dialogRef = this.dialog.open(OperationDialogComponent, {width: '600px', disableClose: true})

    dialogRef.afterClosed().subscribe(dialogResult => {
      if(dialogResult) {
        // Refresh Account
      }
    })
  }

}
