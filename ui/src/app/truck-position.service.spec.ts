import { TestBed } from '@angular/core/testing';

import { TruckPositionService } from './truck-position.service';

describe('TruckPositionService', () => {
  let service: TruckPositionService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(TruckPositionService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
