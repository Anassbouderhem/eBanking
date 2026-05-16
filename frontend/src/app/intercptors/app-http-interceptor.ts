import { HttpInterceptorFn, HttpRequest, HttpHandlerFn, HttpEvent } from '@angular/common/http';
import { inject } from '@angular/core';
import { Auth } from '../services/auth';
import { Observable, catchError, throwError } from 'rxjs';

export const appHttpInterceptor: HttpInterceptorFn = (
  req: HttpRequest<unknown>,
  next: HttpHandlerFn
): Observable<HttpEvent<unknown>> => {
  const authService = inject(Auth);
  if (authService.isAuthenticated) {
    const newRequest = req.clone({
      headers: req.headers.set('Authorization', 'Bearer ' + authService.accessToken)
    });
    return next(newRequest).pipe(
      catchError(err=>{
        if (err.status==401){
         authService.logout()
        }
        return throwError(() => err.message);
      })
    );
  }
  return next(req);
};
