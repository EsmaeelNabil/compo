# Compo

This App is to showcase the Jitpack Compose way of making android apps.

![sample1.jpg](https://github.com/EsmaeelNabil/compo/blob/master/samples/sample1.jpg)

## Features
* [Staggered Grid List of popular actors.](https://github.com/EsmaeelNabil/compo/blob/master/app/src/main/java/com/esmaeel/compo/ui/HomePage/HomePage.kt)

## Built with
  - Kotlin
  - MVVM architectural pattern
  - [Retrofit + OkHttp](https://github.com/EsmaeelNabil/compo/blob/master/app/src/main/java/com/esmaeel/compo/data/network/DataSource.kt)
  - [Coroutines + Flow](https://github.com/EsmaeelNabil/compo/blob/master/app/src/main/java/com/esmaeel/compo/ui/HomePage/HomeRepository.kt)
  - [LiveData - ViewModels](https://github.com/EsmaeelNabil/compo/blob/master/app/src/main/java/com/esmaeel/compo/ui/HomePage/HomeViewModel.kt)
  - [observeAsState for compose observing LiveData](https://github.com/EsmaeelNabil/compo/blob/master/app/src/main/java/com/esmaeel/compo/ui/HomePage/HomePage.kt#L131)

## Build.gradle dependencies are in `buildSrc` in order to have one source of truth.
* [Located in here](https://github.com/EsmaeelNabil/compo/blob/master/buildSrc/src/main/java/com/esmaeel/compo/buildsrc/Dependencies.kt)

## Possible Future work.
* `View Person Details Page`.
* `List of Person Images`.
* `View Person's Image in Full screen`.
* `Unit Tests`.
* `Ui Testing`.


## You Need the latest Canary version of Android studio with Kotlin `1.4.0` 
* [Android Studio v4](https://developer.android.com/studio#downloads)
* this app is built with Android Studio `4.2 Canary 8`












