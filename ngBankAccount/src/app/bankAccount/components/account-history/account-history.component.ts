import { Component, OnInit, OnDestroy} from '@angular/core';
import { ActivatedRoute, Router, NavigationStart } from '@angular/router';
import { Subscription } from 'rxjs';
import { Account } from '../../model/account';
import { AccountService } from '../../services/account.service';

@Component({
   selector: 'app-account-history',
   templateUrl: './account-history.component.html',
   styleUrls: ['./account-history.component.scss']
 })
export class AccountHistoryComponent implements OnInit, OnDestroy {

     account?: Account;
     id: number = 0;
     private subscriptions: Subscription = new Subscription();

     constructor(
       private router:Router,
       private route:ActivatedRoute,
       private accountService: AccountService
      ){
      }

     ngOnInit() {
      this.subscriptions.add(
          this.route.params.subscribe(params => {
            this.id = +params['id'];
            this.findAccountById(this.id);
        })
      );
     }

     ngOnDestroy() {
      this.subscriptions.unsubscribe();
    }

    findAccountById(id:number) {
      this.subscriptions.add(
        this.accountService.findById(id).subscribe(data => {
          this.account = data;
        })
      )
    }

}
