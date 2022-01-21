import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AccountHistoryComponent } from './bankAccount/components/account-history/account-history.component';


const routes: Routes = [
  { path: 'accounts',
    loadChildren: () => import('./bankAccount/bankaccount.module').then(m => m.BankaccountModule)
  },
  // { path: '',   redirectTo: '/', pathMatch: 'full' },
  // { path: '**', component: PageNotFoundComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
