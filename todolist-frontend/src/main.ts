import { bootstrapApplication } from '@angular/platform-browser';
import { provideHttpClient, withInterceptorsFromDi } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { importProvidersFrom } from '@angular/core';
import { provideRouter } from '@angular/router';

import { App } from './app/app';
import {routes } from './app/app.routes';

bootstrapApplication(App, {
  providers: [
    importProvidersFrom(FormsModule),
    provideHttpClient(withInterceptorsFromDi()),
    provideRouter(routes)
  ]
}).catch(err => console.error(err));
