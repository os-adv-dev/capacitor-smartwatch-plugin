import { registerPlugin } from '@capacitor/core';

import type { SmartwatchPlugin } from './definitions';

const Smartwatch = registerPlugin<SmartwatchPlugin>('Smartwatch', {
  web: () => import('./web').then((m) => new m.SmartwatchWeb()),
});

export * from './definitions';
export { Smartwatch };
