import { Component, Input, OnInit, ViewEncapsulation } from '@angular/core';

@Component({
  selector: 'app-icon',
  templateUrl: './icon.component.html',
  styleUrls: ['./icon.component.css'],
  encapsulation: ViewEncapsulation.None
})
export class IconComponent implements OnInit {

  @Input() icon: string = '';
  @Input() color: string = '';
  @Input() customCss: string = '';

  constructor() { }

  ngOnInit(): void {
  }

}
