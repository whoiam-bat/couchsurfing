import { CanActivateFn, Router } from '@angular/router';
import { StorageService } from '../storage/storage.service';
import { inject } from '@angular/core';
import {TokenService} from "../token/token.service";

export const authGuard: CanActivateFn = (route, state) => {
  const store = inject(StorageService);
  const router = inject(Router);
  const tokenService = inject(TokenService);

  if (!store.email && tokenService.isTokenNotValid()) {
    router.navigate(['login']);
    return false;
  }
  return true;
};
