# TechnoNews
Techno News android application.

### Detail
TechnoNews is a smartphone app that should be able to show top headlines for a specific source (BBC news)

#### Constraints: <br/>
• The app must be written in Kotlin or Java, preferably Kotlin.<br/>
• The app must run on the latest released version of the target platform<br/>
• The app must be built using Android Studio 3.0+, without requiring the reviewer to modify any existing code. <br/>
• The app must support both portrait and landscape modes without crashing at any time.<br/>
• The app may use libraries for HTTP/REST or JSON, such as Retrofit2+, Moshi, Gson, etc...<br/>
• The app must include unit tests<br/>

#### Features and Functionality

##### Displaying Top Headlines <br/>
• When the user launches the application, they are presented with a screen displaying the top headlines from a specific news source, BBC news. <br/>
• The screen title shows the news provider's name. <br/>
• Headlines are presented in a list format, sorted by date. <br/>
• Each cell in the list displays the headline title. <br/>
• The user can scroll through the list of headlines. <br/>
• If available, the headline image is displayed in each cell. Images are downloaded and cached for efficient loading. <br/>

##### Viewing Headline Details<br/>
• When the user taps on a headline, they are taken to a new screen displaying additional details. <br/>
• The new screen shows the headline image, title, description, and content, if available. <br/>

##### Fingerprint Authentication<br/>
• Upon opening the application, if the device has a fingerprint scanner and it is configured, the user is required to use fingerprint identification for authentication. <br/>
• If the device does not have a fingerprint scanner or it is not configured, the app opens normally without requiring fingerprint authentication. <br/>

##### Multiple News Sources<br/>
• The app supports multiple news sources by creating different flavors. <br/>
• Users can select a specific news source flavor, and upon launching the app, they will land in the chosen news source. <br/>
• Headlines are presented according to the selected news source flavor. <br/>
        
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

### Download

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
