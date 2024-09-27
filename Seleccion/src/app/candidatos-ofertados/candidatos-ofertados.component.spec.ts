import { ComponentFixture, TestBed } from '@angular/core/testing';
import { CandidatosOfertadosComponent } from './candidatos-ofertados.component';

describe('CandidatosOfertadosComponent', () => {
  let component: CandidatosOfertadosComponent;
  let fixture: ComponentFixture<CandidatosOfertadosComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CandidatosOfertadosComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(CandidatosOfertadosComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
