import { WebPlugin } from '@capacitor/core';

import type { SmartwatchPlugin } from './definitions';

export class SmartwatchWeb extends WebPlugin implements SmartwatchPlugin {
  async echo(options: { value: string }): Promise<{ value: string }> {
    console.log('ECHO', options);
    return options;
  }
}
