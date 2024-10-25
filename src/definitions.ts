export interface SmartwatchPlugin {
  echo(options: { value: string }): Promise<{ value: string }>;
}
