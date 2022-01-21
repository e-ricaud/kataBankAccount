import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';


const routes: Routes = [
  { path: 'accounts',
    loadChildren: () => import('./bankAccount/bankaccount.module').then(m => m.BankaccountModule)
  // component: AccountComponent
  }
  // { path: 'addaccount', component:  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
