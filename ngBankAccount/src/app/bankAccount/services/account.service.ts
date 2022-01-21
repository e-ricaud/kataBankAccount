import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Account } from '../model/account';

@Injectable()
export class AccountService {

  private usersUrl: string;

  constructor(private http: HttpClient) {
    this.usersUrl = 'http://localhost:8080/account';
  }

  public findById(id: number): Observable<Account> {
    let params = new HttpParams().set('id',id);
    return this.http.get<Account>(`${this.usersUrl}`, { params });
  }

  // public save(user: User) {
  //   return this.http.post<User>(this.usersUrl, user);
  // }
}
