import { bootstrapApplication } from '@angular/platform-browser';
import { provideHttpClient, withInterceptorsFromDi } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { importProvidersFrom } from '@angular/core';

import { App } from './app/app';

bootstrapApplication(App, {
  providers: [
    importProvidersFrom(FormsModule),
    provideHttpClient(withInterceptorsFromDi())
  ]
}).catch(err => console.error(err));
