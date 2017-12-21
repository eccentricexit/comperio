# Comperio

[![standard-readme compliant](https://img.shields.io/badge/readme%20style-standard-brightgreen.svg?style=flat-square)](https://github.com/RichardLitt/standard-readme) [![Build Status](https://travis-ci.org/mtsalenc/comperio.svg?branch=master)](https://travis-ci.org/mtsalenc/comperio)

> Learn a new language with teachers near you. Make yourself available to students.

This app was built for educational purposes, part of my nanodegree program.

A nodejs server is a companion project to this one. It is a firebase cloud messaging server and handles persistance on mongodb. More information [here](https://github.com/mtsalenc/comperio-backend)

Feel free to play `POST` and `GET` the endpoint but please don't be mean to it so I don't have to take it down. It is a mighty raspberrypi, kindly maintained by a [wizard](https://github.com/tfalencar).

## Features

- Firebase Cloud Messaging
- AdMob Insterstitial Ads, flavours
- Content Providers
- Loaders, Fragments, Bottom Navigation
- SyncAdapters and SyncService
- Retrofit, Picasso
- MVVM, Observer Pattern
- Data Binding
- Google Location Services

## Saving Battery and Bandwidth

To keep the local database in sync with the one controlled by [comperio-backend](https://github.com/tfalencar), the app makes use of a small protocol powered by fcm:

1. A device (e.g. browser) posts a new schedule to the endpoint.
2. The endpoint stores it on mongodb.
3. The endpoint sends a message to all devices through fcm, notifying them that the db has changed.
4. The message triggers a service that fetches new data from the endpoint.

This way, there is no need for the app to periodically query the db for new data, saving battery and bandwidth.

## Screenshots
![screenshot1](https://raw.githubusercontent.com/mtsalenc/comperio/master/docs/screenshots/Screenshot_20171219-210847.png)
![screenshot2](https://raw.githubusercontent.com/mtsalenc/comperio/master/docs/screenshots/Screenshot_20171219-214927.png)
![screenshot3](https://raw.githubusercontent.com/mtsalenc/comperio/master/docs/screenshots/Screenshot_20171219-210823.png)


## Install

There is a paid and a free flavour to the app. The latter implements ads.

To install the free version, with an available device on adb, go to root project folder and run:

```
./gradlew installFreeRelease
```
or
```
./gradlew installPaidRelease
```
for the ad free version.

(Or just open it on Android Studio and hit play.)

## Contribute

PRs accepted.

## License

MIT © Matheus Faria de Alencar
