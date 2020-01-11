import { Component, Input } from '@angular/core';

@Component({
    selector: 'app-empty-search',
    template: `
        <div class="empty-search-wrapper">
            <img src="assets/img/search.png" width="200px" height="200px" style="opacity: 0.5;">
            <h2>{{ title }}</h2>
            <h6>{{ description }}</h6>
        </div>
    `,
    styles: [
        `
            .empty-search-wrapper {
                margin-right: auto;
                margin-left: auto;
                margin-top: 15%;
                text-align: center;
                color: #464646;
            }
        `
    ]
})
export class EmptySearchComponent {
    @Input() title: String
    @Input() description: string;
}
