## Recipe Ventures

<!-- TABLE OF CONTENTS -->
<details open="open">
  <summary><h2 style="display: inline-block">Table of Contents</h2></summary>
  <ol>
    <li>
      <a href="#about-the-project">About The Project</a>
      <ul>
        <li><a href="#built-with">Built With</a></li>
      </ul>
    </li>
    <li>
      <a href="#getting-started">Getting Started</a>
      <ul>
        <li><a href="#prerequisites">Prerequisites</a></li>
      </ul>
    </li>
  </ol>
</details>



<!-- ABOUT THE PROJECT -->
## About The Project

[![Screenshot-1610475163.png](https://i.postimg.cc/13Wmk8RM/Screenshot-1610475163.png)](https://postimg.cc/nXDf7cDQ)

Recipe Venture is a recipe look-up Android app. It prompts a user to enter any snack/meal/food of interest and then adds it to a home screen list. 
A user can then click on the given input and a specific recipe with a title, photo, and instructions will appear. If the recipe is unsatisfactory, 
a user can click to find another recipe until they are happy.

[![Screenshot-1610475720.png](https://i.postimg.cc/wBvXzRXB/Screenshot-1610475720.png)](https://postimg.cc/BjrLc6wW)
[![Screenshot-1610475714.png](https://i.postimg.cc/TPgRpCqN/Screenshot-1610475714.png)](https://postimg.cc/2qjptdtn)


### Built With

* [Kotlin]()
* [Edamam Recipe Search API]()



<!-- GETTING STARTED -->
## Getting Started

To get a local copy up and running follow these simple steps.

### Prerequisites

Add these dependencies to your build.gradle (Module: app) file if not already present.
*  Picasso Library
      *  implementation 'com.squareup.picasso:picasso:2.5.2'
*  Espresso Library
      *  androidTestImplementation 'androidx.test.espresso:espresso-core:3.1.1'
*  Room Library
      *  implementation 'androidx.room:room-runtime:2.2.5'
      *  annotationProcessor 'androidx.room:room-compiler:2.2.5'
      *  implementation 'android.arch.persistence.room:runtime:2.2.5'
      *  kapt 'android.arch.persistence.room:compiler:2.2.5'
*  GSON Library
      *  implementation 'com.google.code.gson:gson:2.8.5'
      *  implementation 'com.squareup.retrofit2:converter-gson:2.4.0'





