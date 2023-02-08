# NYCSchools

Steps to run this project
1. git clone git@github.com:Monosrija/nycschools.git
2. Run the app

Key points in this project
1. I have implemented this app using Kotlin and MVVM architecture. The project has 2 main parts 
  domain - This package has the data classes, networkserviceInterface and the usecase to handle the network calls.
  ui - UI has the single activity and multiple fragment structure. It contains the list and the details screen.
2. App navigation is handled using nav_graph
3. Used Hilt to acheive dependency injection
4. Used retrofit for network service.
5. Implemented unit tests.
6. Implemented LiveData, kotlin coroutines and databinding.
7. App has loading indicator to indicate when data is loading
8. App handles network errors and also when data is unavailable for a particular school
9. App supports dark theme.
10. App has custom toolbar with back button for easier navigation.
11. List screen displays data in alphabetically sorted order of school names


Future scope
1. Caching of data in offline mode is not handled.
2. Implement search feature in the list screen
3. Display more data in the details screen


SCREENSHOTS of the app in light mode

![Screenshot_1675837143](https://user-images.githubusercontent.com/14352106/217450608-030f74c9-10c7-4985-a61a-97f51f8bda11.png)

![Screenshot_1675841510](https://user-images.githubusercontent.com/14352106/217469757-0b70646d-18a2-4c41-9d63-b849c334ee36.png)


DARK THEME screenshots

![Screenshot_1675836984](https://user-images.githubusercontent.com/14352106/217450649-0d66bd40-9ed5-4e41-9dce-6384058a8539.png)

![Screenshot_1675841571](https://user-images.githubusercontent.com/14352106/217469805-33cd878a-bb38-40c7-aabe-123fa99224e6.png)

Error screens
![Screenshot_1675837163](https://user-images.githubusercontent.com/14352106/217455986-b9375d15-729a-4fff-98e7-fed7810103c2.png)

Loading sreen
![Screenshot_1675837178](https://user-images.githubusercontent.com/14352106/217456185-8ec00153-bd46-456b-9858-66d757d684cd.png)

When SAT scores are not available

![Screenshot_1675843393](https://user-images.githubusercontent.com/14352106/217470458-e3c1b923-67cc-4edf-af3c-d1d850db724e.png)

Video of the app
--------------------

![nyc-schools](https://user-images.githubusercontent.com/14352106/217472309-4fa69ea9-5616-4e32-a0c5-755a58751104.gif)




