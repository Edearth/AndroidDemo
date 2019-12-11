# Instrumentation test runner comparison

This repo's purpose is to serve as a comparison between different instrumentation frameworks, such as: [Spoon](https://github.com/square/spoon), [Fork](https://github.com/shazam/fork), ~~[composer](https://github.com/gojuno/composer)~~, [Marathon](https://github.com/Malinskiy/marathon) and [Gordon](https://github.com/Banno/Gordon). Conclusion will probably be added on this README file afterwards.

It can also serve as an example of good automated test practices (or tricks) like:

* Using a Mock Server so that the tests don't depend on the backend
* Using Idling Resources
* Using Dagger dependency injection in the test Module
* Using rules to grant permissions and clear SharedPreferences

I don't claim it's perfect, though. Just a starting point on those topics. 

The original app was taken from [Free Now's code challenge for Android QAs](https://github.com/freenowtech/MobileAppAutomationTest).

