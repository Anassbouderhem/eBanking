import { CanActivateFn, Router } from '@angular/router';
import { inject } from '@angular/core';
import { Auth } from '../services/auth';

export const authentificationGuard: CanActivateFn = (route, state) => {
  const authService = inject(Auth);
  const router = inject(Router);
  if (authService.isAuthenticated == true) {
    return true;
  } else {
    router.navigateByUrl('/login');
    return false;
  }
};
