import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Account } from '../model/account';

@Injectable()
export class AccountService {

  private usersUrl: string;

  constructor(private http: HttpClient) {
    this.usersUrl = 'http://localhost:8080/account';
  }

  public findById(id: number): Observable<Account> {
    return this.http.get<Account>(this.usersUrl+`/${id}`);
  }

  // public save(user: User) {
  //   return this.http.post<User>(this.usersUrl, user);
  // }
}
