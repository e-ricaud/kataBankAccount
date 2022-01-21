import { Component, OnInit } from '@angular/core';
import { Account } from '../model/account';
import { AccountService } from '../services/account.service';

@Component({
  selector: 'app-account',
  templateUrl: './account.component.html'
  // styleUrls: ['./account.component.scss']
})
export class AccountComponent implements OnInit {

  accounts: Account[] = [];

  constructor(private userService: AccountService) {
  }

  ngOnInit() {
    this.userService.findById(1).subscribe(data => {
      console.log('data serv ' + data.firstName);
      this.accounts = [data];
      console.log('size' + this.accounts.length)
    });
  }

}
