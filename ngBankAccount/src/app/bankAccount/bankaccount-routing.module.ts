import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AccountHistoryComponent } from './components/account-history/account-history.component';
import { AccountComponent } from './components/account/account.component';

const routes: Routes = [
  { path: '',
    component: AccountComponent,
  },
  { path: 'history/:id',
    component: AccountHistoryComponent
  },
  // { path: '',   redirectTo: '/', pathMatch: 'full' },
  // { path: '**', component: PageNotFoundComponent },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class BankaccountRoutingModule { }
