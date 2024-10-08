import { Component } from '@angular/core';

@Component({
  selector: 'app-loading',
  template: `
  <div aria-label="ofertas" role="status" class="loader">
    <svg class="icon" viewBox="0 0 256 256">
      <line x1="128" y1="32" x2="128" y2="64" stroke-linecap="round" stroke-linejoin="round" stroke-width="24"></line>
      <line x1="195.9" y1="60.1" x2="173.3" y2="82.7" stroke-linecap="round" stroke-linejoin="round" stroke-width="24"></line>
      <line x1="224" y1="128" x2="192" y2="128" stroke-linecap="round" stroke-linejoin="round" stroke-width="24"></line>
      <line x1="195.9" y1="195.9" x2="173.3" y2="173.3" stroke-linecap="round" stroke-linejoin="round" stroke-width="24"></line>
      <line x1="128" y1="224" x2="128" y2="192" stroke-linecap="round" stroke-linejoin="round" stroke-width="24"></line>
      <line x1="60.1" y1="195.9" x2="82.7" y2="173.3" stroke-linecap="round" stroke-linejoin="round" stroke-width="24"></line>
      <line x1="32" y1="128" x2="64" y2="128" stroke-linecap="round" stroke-linejoin="round" stroke-width="24"></line>
      <line x1="60.1" y1="60.1" x2="82.7" y2="82.7" stroke-linecap="round" stroke-linejoin="round" stroke-width="24"></line>
    </svg>
    <span class="loading-text">Añadiendo ofertas</span>
  </div>
  `,

  styles: [`
    .loader {
      display: flex;
      align-items: center;
    }
    
    .icon {
      height: 1.5rem;
      width: 1.5rem;
      animation: spin 1s linear infinite;
      stroke: rgba(107, 114, 128, 1);
    }
    
    .loading-text {
      font-size: 0.75rem;
      line-height: 1rem;
      font-weight: 500;
      color: rgba(107, 114, 128, 1);
    }
    
    @keyframes spin {
      to {
        transform: rotate(360deg);
      }
    }
  `],
  standalone: true, // Esto es clave para que sea un componente standalone
})
export class LoadingComponent {

}
