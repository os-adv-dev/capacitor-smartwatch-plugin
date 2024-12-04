# Capacitor Smartwatch Plugin

A Capacitor plugin to enable communication between a Capacitor app and a smartwatch. This plugin allows your Capacitor app to send data, information, and templates to a smartwatch application. Currently, this functionality is **only supported on Android devices**.

To use this plugin effectively, you must also add and build the [smartwatch application](https://github.com/os-adv-dev/smartwatch) as it contains the smartwatch-side implementation required to receive and process the data.

---

## Features
- Send information, data, and templates from a Capacitor app to a connected smartwatch.
- Built to integrate seamlessly with the smartwatch application available [here](https://github.com/os-adv-dev/smartwatch).
- Currently supports Android smartwatches only.

---

## Installation

Install the plugin in your Capacitor app:

```bash
npm install capacitor-smartwatch-plugin
npx cap sync