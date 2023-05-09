# TechnoNews
Techno News android application.

### Detail
TechnoNews is a smartphone app that should be able to show top headlines for a specific source (BBC news)

Constraints: <br/>
• The app must be written in Kotlin or Java, preferably Kotlin.<br/>
• The app must run on the latest released version of the target platform<br/>
• The app must be built using Android Studio 3.0+, without requiring the reviewer to modify any existing code. <br/>
• The app must support both portrait and landscape modes without crashing at any time.<br/>
• The app may use libraries for HTTP/REST or JSON, such as Retrofit2+, Moshi, Gson, etc...<br/>
• The app must include unit tests<br/>

Story 1: When the user launches the application, he should land in a screen where is possible to see top headlines for the specific news source 
Acceptance criteria: 
1. News provider name should be showed as a screen title
2. Headlines are presented in a list format.
3. Each cell should present the headline title
4. Headlines must be sorted by date
5. The user must be able to scroll through the list of headlines
6. Each cell should present headline image, if available (download and cache it, don’t bundle it) 

Story 2: When the user taps on a headline, he should be taken to a new screen 
Acceptance criteria: 
1. Tapping on a headline presents a new screen.
2. Image, title, description and content should be displayed, if available 

Story 3: When user opens the application, it should ask for a fingerprint identification, if available
Acceptance criteria: 
1. If the device has a fingerprint scanner and it’s configured in the device, user should be required to use it when he opens the application
2. If the device doesn’t have fingerprint scanner or it’s not configured, then it should open normally 

Story 4: A new flavor should be created to present news for another source
Acceptance criteria: 
1. User should land in a different news source if running another target
2. Headlines should be presented according to the target that was selecte

<img src="https://github.com/Muzafferus/TechnoNews/blob/master/images/home.png" width="216" height="468"> <img src="https://github.com/Muzafferus/TechnoNews/blob/master/images/detail.png" width="216" height="468">

### Api information
- Use this API for news - https://newsapi.org/v2/top-headlines?apiKey=API_KEY&country=us&category=technology
- For API documentation  - https://newsapi.org/docs/endpoints/top-headlines

### Android Framework

In this folder, you will find projects using the following set of Libraries. 

- MVVM
- Kotlin Coroutines
- Jetpack Navigation
- DataBinding
- Network: Retrofit2, Gson
- Restful API - Apps
- Image loader: Coil
- DI: Hilt
- DataBase: Room
- DB connection: Flow
- UI data connection: LiveData

### Test
- Write Unit test for API Request (NewsService)
- Write Unit test for Database (ArticleDao)

## Download

APK Downloads: https://github.com/Muzafferus/TechnoNews/releases/

License
--------

       Copyright 2023 Muzaffar Pashazada
       
       Licensed under the Apache License, Version 2.0 (the "License");
       you may not use this file except in compliance with the License.
       You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0
       
       Unless required by applicable law or agreed to in writing, software
       distributed under the License is distributed on an "AS IS" BASIS,
       WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
       See the License for the specific language governing permissions and
       limitations under the License.
