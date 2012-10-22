A sample app showing off [Trailblazer](https://github.com/gsoltis/Trailblazer) and [Firebase](http://www.firebase.com)
======================================================================================================================

This app demonstrates using the Trailblazer library to track a user's location. The user can turn the service on and off with a checkbox, and the app puts an icon in the notification bar when it is running.

The app will upload a stream of location data to Firebase. An example of using the location data is included in findlizzy.html. It uses the Firebase API to listen to the stream of the locations being posted by the device, and as it gets them, it updates a Google Map.

You can open findlizzy.html directly in a browser, no server is required for this example.