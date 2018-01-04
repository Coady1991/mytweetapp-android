# mytweetapp-android

## Built in conjunction with notes and labs from https://wit-ictskills-2017.github.io/mobile-app-dev/index.html

## V1.0 App Features

### Main Features
* Users able to SignUp/Login (Users persisted)
* Users allowed to post 140 character tweets (Tweets currently not persisted at the moment)
* Users able to view their tweets on a Timeline
* Tweets can be deleted from the Timeline
* Users can write a Tweet and email it to a contact from their phones contact list
* User can change their settings (Currently not updating)

#### SplashScreen
* When the app is launched a splashscreen is presented to the user for 3 seconds
* The user is the taken to the Welcome activity

#### Welcome
* Contains a Welcome message and two buttons, Login and Signup with launch the respective activities
* If they press back they are brought back to the phones home screen (The app stays running)

#### Signup
* User must enter details for First & Last name, an email address and a password
* If any of these are missing when 'Register' is pressed, the user is given an error message and prompted to enter their details
* Email is validated to make sure the @ and . are present
* Password currently not validated for length, characters etc.

#### Login
* A user must be registered before they can log in
* If their details are not correct when 'Login' is pressed an 'Invalid Credentials' toast is generated
* If details are correct the user is taken to the Tweet activity

#### Tweet
* After a user has Logged In they are brought to this screen. If they press back they are brought back to the phones home screen and not the login page (The app stays running)
* As the user enters text the number of characters counts down from 140
* Pressing 'Tweet' generates a 'Tweet Tweeted' toast and the text is cleared
* Or the user can select a contact from their phone contacts to email the Tweet to
* If 'Tweet' pressed with no characters present a toast is generated alerting the user to enter some characters before Tweeting (Ensures no empty Tweets)
* Current Date and Time
* Menu selections for Timeline, Settings and Logout

#### Timeline
* Tweets appear in list format in Timeline
* A long press on a Tweet brings up a dialog box asking if the user wishes to delete the Tweet or not
* Menu selections for Settings and Logout
* Action bar present to navigate back to Tweet Activity

#### Settings
* Allows the user to change their email or password but currently not functional
* Menu selections for Tweet, Timeline and Logout

#### Logout
* Logs the user out of the activity and they are brought back to the Welcome activity
* If they press back they are brought back to the phones home screen and not back to the activity they logged out from (The app stays running)

## V1.1 App Features

### Added Features
* Update to look of App
* Users and Tweets retrieved from Web App database through API
* If Users or Tweets are created through the App they are stored in the database of the Web App and can be seen on the Web App
* Users can follow/unfollow other users
* When signing up, passwords are hashed and salted through Web App API which allows login for same user on the Web App
* Hashed and salted passwords are checked through the Web App API when logging into the Android App

* __NOTE:__ Currently unable to delete tweets from timeline

#### Logins for Users
email: homer@simpson.com
password: secret

email: bart@simpson.com
password: secret

#### Corresponding Web App deployed here https://mytweetapp-12.herokuapp.com/
