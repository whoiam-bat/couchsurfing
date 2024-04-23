import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class StorageService {

  set token(token: string) {
    localStorage.setItem("token", token);
  }

  set userId(userId: string) {
    localStorage.setItem("userId", userId);
  }

  get token(): string | null {
    return localStorage.getItem("token") as string;
  }

  get userId(): string | null {
    return localStorage.getItem("userId") as string;
  }

}
