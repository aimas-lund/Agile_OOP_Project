# Read me for Team Syntactic Sugar's Hospital Project

In this readme we'll go through different functions on how to run our program including the different features.


# Initilazation (Running the program)

Run the DemoApplication (main class, found in main/controller), then open localhost:8080 when the application is running. We advise you to use Firefox or Chrome, due to their support of our HTML and CSS tags.   

## Index page

The index page allows you to navigate to different dashboards, which holds different functions

## Login

Trying to access any dashboard (before logging in) will redirect you to the login page. We've made some different roles to illustrate part of our URL based security.
The different roles (and their access) is following:
| Username 	| Password 	| Access                      	|
|----------	|----------	|-----------------------------	|
| clerk    	| clerk    	| /clerk                      	|
| doctor   	| doctor   	| /doctor                     	|
| nurse    	| nurse    	| /nurse                      	|
| ict      	| ict      	| /ict (and all of the above) 	|

 
## Submitting actions (forms)

When you submit a form a new tab will open with either a relevant message or the submitted data (in case of registering staff/patients or adding a department).
**Telephone number** needs to have 8 digits in it.

## Database
It's required to setup our Sqllite with jdbc.
We've personally done our project in IntelliJ, so we're not sure how to do this in Eclipse.
