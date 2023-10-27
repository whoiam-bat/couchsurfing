import {Component, Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {User} from "../../common/user";

@Component({
  selector: 'app-hosts',
  templateUrl: './hosts.component.html',
  styleUrls: ['./hosts.component.css']
})
@Injectable({
  providedIn: 'root'
})
export class HostsComponent {

  private baseUrl = 'http://localhost:8080/api/v1/users/hosts';

  constructor(private httpClient: HttpClient) {
  }

  getHostList(page: number,
              size: number,
              location: number): Observable<HostsResponse> {

    const searchUrl = `${this.baseUrl}?location=${location}&page=${page}&size=${size}`;

    return this.httpClient.get<HostsResponse>(searchUrl);
  }

}

interface HostsResponse {
  _embedded: {
    products: User[];
  },
  page: {
    size: number,
    totalElements: number,
    totalPages: number,
    number: number
  }
}
