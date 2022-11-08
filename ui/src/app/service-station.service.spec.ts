import { TestBed } from '@angular/core/testing';

import { ServiceStationService } from './service-station.service';

describe('ServiceStationService', () => {
  let service: ServiceStationService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ServiceStationService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
