# My-Compose-App

This app contains 3 parts:
* Movie page
* Crypto page (cryptocurrency latest prices)
* Task page (simple To-Do list)

In the movie page, top IMDb movies are displayed, which are loaded from an API. In the crypto page, the last price of famous cryptocurrencies is loaded from an API and displayed in a list. Finally, the task page is a simple To-Do list where users can add and remove tasks. To ensure good performance, reusability and separating the UI logic from business logic, I implemented the MVVM architecture to update the UI using view models.

## Features
* Design UI using Jetpack Compose
* Handle HTTP requests using the Kotlin Ktor library
* Implement Dependency Injection using Hilt
* Save data into a local database using Room
* Utilize Remote Mediator and Paging to load data
* Update local data periodically from remote servers in the background using WorkManager
* Save key-value data using DataStore
* Lazy loading images using Glide library
* Light and Dark theme
* Splash Screen with animation
* Using Google Material Components
* Search Movies by title
* Saving states after configuration changes (screen rotation)
* State Hoisting

## Screenshots

<div style="display: flex;">
<img src="https://raw.githubusercontent.com/alidev20002/My-Compose-App/5b027e36f608f70cf0b0abf46ce56a6000bc70d4/screenshots/Screenshot_20230625_170644_com.example.composeproject.jpg" width="300">

<img src="https://github.com/alidev20002/My-Compose-App/blob/master/screenshots/Screenshot_20230625_170658_com.example.composeproject.jpg?raw=true" width="300">

<img src="https://github.com/alidev20002/My-Compose-App/blob/master/screenshots/Screenshot_20230625_170705_com.example.composeproject.jpg?raw=true" width="300">

<img src="https://github.com/alidev20002/My-Compose-App/blob/master/screenshots/Screenshot_20230625_170722_com.example.composeproject.jpg?raw=true" width="300">

<img src="https://github.com/alidev20002/My-Compose-App/blob/master/screenshots/Screenshot_20230625_170729_com.example.composeproject.jpg?raw=true" width="300">

<img src="https://github.com/alidev20002/My-Compose-App/blob/master/screenshots/Screenshot_20230625_170742_com.example.composeproject.jpg?raw=true" width="300">

<img src="https://github.com/alidev20002/My-Compose-App/blob/master/screenshots/Screenshot_20230625_170805_com.example.composeproject.jpg?raw=true" width="300">

<img src="https://github.com/alidev20002/My-Compose-App/blob/master/screenshots/Screenshot_20230625_170813_com.example.composeproject.jpg?raw=true" width="300">

<img src="https://github.com/alidev20002/My-Compose-App/blob/master/screenshots/Screenshot_20230625_170929_com.example.composeproject.jpg?raw=true" width="300">

<img src="https://github.com/alidev20002/My-Compose-App/blob/master/screenshots/Screenshot_20230625_170914_com.example.composeproject.jpg?raw=true" width="300">
</div>
