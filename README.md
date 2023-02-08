# NYCSchools

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


Future scope
Caching of data in offline mode is not handled.


SCREENSHOTS of the app in light mode

![Screenshot_1675837143](https://user-images.githubusercontent.com/14352106/217450608-030f74c9-10c7-4985-a61a-97f51f8bda11.png)
![Screenshot_1675837147](https://user-images.githubusercontent.com/14352106/217450624-f2410ca3-46c6-48ef-8c08-b1db709816ab.png)


DARK THEME screenshots

![Screenshot_1675836984](https://user-images.githubusercontent.com/14352106/217450649-0d66bd40-9ed5-4e41-9dce-6384058a8539.png)
![Screenshot_1675836989](https://user-images.githubusercontent.com/14352106/217450680-2c8c8647-1173-44c7-982f-b65703a7dfb4.png)

![nyc-schools](https://user-images.githubusercontent.com/14352106/217455193-eb98eaad-4f64-40ee-8d6c-3e89ae0eec05.gif)


