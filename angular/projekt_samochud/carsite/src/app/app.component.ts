import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Game } from './game';
import { Observable } from 'rxjs';


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  games!: Observable<Game[]>;
  readonly apiEndpoint= "https://www.cheapshark.com/api/1.0/deals"
  constructor(private http: HttpClient) {

  }
  public getListForQuery(cena: string, typ:string) {
    this.games = this.http.get<Game[]>(this.apiEndpoint + typ + cena + "&?pageSize=10");
  }
}
