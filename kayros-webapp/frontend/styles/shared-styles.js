// eagerly import theme styles so as we can override them
import '@vaadin/vaadin-lumo-styles/all-imports';

import '@vaadin/vaadin-charts/theme/vaadin-chart-default-theme';

const $_documentContainer = document.createElement('template');

$_documentContainer.innerHTML = `
<custom-style>
  <style>

html {
  --lumo-primary-text-color: rgb(238, 23, 80);
  --lumo-primary-color-50pct: rgba(238, 23, 80, 0.5);
  --lumo-primary-color-10pct: rgba(238, 23, 80, 0.1);
  --lumo-primary-color: hsl(344, 86%, 51%);
}

[theme~="dark"] {
}

  </style>
</custom-style>


<dom-module id="text-field-style" theme-for="vaadin-text-field">
  <template>
    <style>[part="input-field"]{box-shadow:inset 0 0 0 1px var(--lumo-contrast-30pct);background-color:var(--lumo-base-color);}:host([invalid]) [part="input-field"]{box-shadow:inset 0 0 0 1px var(--lumo-error-color);}
    </style>
  </template>
</dom-module>`;

document.head.appendChild($_documentContainer.content);
