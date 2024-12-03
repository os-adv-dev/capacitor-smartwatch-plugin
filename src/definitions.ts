export interface SmartwatchPlugin {
  sendMessage(options: { value: string }): Promise<{ value: string }>;
}
