# Instrumentation test runner comparison

This repo's purpose is to serve as a comparison between different Android instrumentation frameworks, such as: [Spoon](https://github.com/square/spoon), [Fork](https://github.com/shazam/fork), ~~[composer](https://github.com/gojuno/composer)~~, [Marathon](https://github.com/Malinskiy/marathon) and [Gordon](https://github.com/Banno/Gordon). Conclusion will probably be added on this README file afterwards.

It can also serve as an example of good automated test practices (or tricks) like:

* Using a Mock Server so that the tests don't depend on the backend
* Using Idling Resources
* Using Dagger dependency injection in the test Module
* Using rules to grant permissions and clear SharedPreferences

I don't claim it's perfect, though. Just a starting point on those topics. 

The original app was taken from [Free Now's code challenge for Android QAs](https://github.com/freenowtech/MobileAppAutomationTest).

# What we tested and how

5 test runners were tested:
* **Spoon**, the one we're using right now.
* **Fork**, a Spoon extension.
* **Gordon**, a replacement for Spoon and Fork.
* **Marathon**, the replacement for **Composer** (the one used at passenger).
* **JUnit**, as a base ground / control group.

To test them, we implemented 10 very simple test cases and we ran all of them with each test runner. The test cases were:
* 4 successfull tests.
* 4 flaky tests (randomly fail at the beginning).
* 2 failing tests.

Different configurations were used for each runner, since all of them offer different capabilities. When possible we tried running the test cases in parallel in 2 emulators. We also executed them multiple times, so all the results are an average of the results from all executions. Details and explanations will be added in each test runner's conclusions.

# Conclusions

### Quick comparison

Getting the lowest execution time from all different test runners, here is a table to compare them:

| JUnit  | Spoon  | Fork   | Gordon | Marathon |
| ------ | ------ | ------ | ------ | -------- |
| 20 sec | 30 sec | 50 sec | 24 sec | 32 sec   |

And here's another table comparing the report size in MB.

| JUnit  | Spoon  | Fork   | Gordon | Marathon |
| ------ | ------ | ------ | ------ | -------- |
| no report | 2.9 MB | 8 MB | 0.03 MB | 1.3 MB |

### JUnit

The tests were executed in a single device in Junit, without counting installation time. These tests did not generate an HTML report, did not retry failing tests and they didn't handle any state clean up after execution (even though SharedPreferences were cleaned manually after each test).

The purpose of these tests was to get a baseline to start making comparisons between test runners. We got an execution time of just over **20 seconds**.

### Spoon

This is the one we're using right now.

##### Pros

* Currently in use -> There is no development cost, since it's already implemented.
* It allows parallel execution.
* Biggest amount of contributors to the repo (58).

##### Cons

* Old and not updated anymore **(!)**. Last commit is from Oct 30, 2018.
* No retry policy
* Can't filter by device, executes on all available devices

##### Timing

Testing with different configurations, we found that enabling "singleInstrumentationCall" decreased the execution time considerably.

| Configuration | Single Instr. Disabled | Single Instr. Enabled |
| ------------- | ---------------------- | --------------------- |
| 2 devices, 2 shards |    38 sec        |       30 sec          |

##### Report

* 2.9 MB in size
* Contains logs + stacktrace
* Difficult to navigate when there are a lot of test cases

### Fork

This one was a bit of a let down. Personally I was expecting something great from it, but it was slow, heavy and caused some issues with ADB / Android Studio.

##### Pros

* Allows parallel execution.
* Can set up device pools (filtering devices by screen size, API level, etc.).
* Can be configured to retry failed tests.
* Second biggest amount of contributors (16).

##### Cons

* The slowest to execute, probably because of the video recording and the big report generation.
* For some reason, after executing once the emulators became unresponsive. I don't know if this was a problem with ADB or Android Studio. It shouldn't be an issue for the CI, but local executions might be troublesome.
* The biggest report by far. Weights almost 3x as much as the Spoon report.

##### Timing

This one was the slowest to execute. Without any retrying policy and using 2 emulators, it took a whooping 50 seconds to execute the tests.

| Configuration | Execution time |
| ------------- | -------------- | 
| 2 devices, 2 shards, no retries | 50 sec |

##### Report

* 8 MB report. The biggest by far.
* It includes images, gifs and a video recording of the execution (even though I was unable to view it from the browser).
* It includes colored log and stacktrace in the html report.

### Gordon

This could be a good replacement, emphasizing fast execution, lightweight report and ease of use.

##### Pros

* Allows parallel execution.
* Can set up some limited device pools (execute for each device, execute once on any device, execute on specific devices).
* Simple way to run specific tests / packages from the command line (`./gradlew gordon --tests=path.to.package.ClassName`).
* Simple, lightweight report.
* Smart retry policy (if test failed, add it again at the end).
* If a failed test is retried and it finishes successfully, the report clearly marks it as flaky and the execution passes.
* Fast execution (without retries).

##### Cons

* Report doesn't contain log. But it still has the stacktrace.
* Limited device pools.
* Lowest number of contributors to the repo (4)

##### Timing

| Configuration | Retry disabled | 2 retries allowed |
| ------------- | -------------- | ----------------- |
| 2 devices, 2 shards |  24 sec  |    1 min 12 sec   |

##### Report

* Lightest report in the group, only 28 KB.
* It doesn't include images or videos.
* It doesn't include log.
* Differentiates between failed / flaky tests.
* It includes stacktrace for failed / flaky tests.
* It doesn't include info for passed tests.

### Marathon

This is the replacement for Composer, the one that is being used in Passenger. Apparently it supports both Android and iOS execution, so it could be interesting if we decide to align with iOS on this.

This could be the best possible replacement if we managed to use multiple emulators with it.

##### Pros

* Configuration file separated from build.gradle -> no need to sync after changing config.
* Good looking, easy to navigate report with colored logs. And it's approximately half the size of Spoon's report. Also it seems to record video, even though I can't seem to open the files in the html report.
* Fast execution. 

##### Cons

* Configuration is in YAML and it's too complex. Check out how many things can be added [here](https://malinskiy.github.io/marathon/doc/configuration.html) and you will see it can quickly become difficult to understand.
* Can't be used with emulators (unless modified? It's open source, so we could change the code). It uses the device serial number to differentiate between devices, so when trying to use multiple emulators it just registers 1 of them. This is a huge issue for us, since we rely on creating multiple emulator instances to test in parallel.
* It might have been only me, but using it broke ADB. I couldn't start a server afterwards from the command line.
* Quite new. Repo was created on Apr 13, 2018 and the current version is 0.5.0 
* Relatively low number of contributors (9).

##### Timing

These tests were done with 1 emulated device and 1 real device, since we can't use 2 emulated devices at the same time.

| Config | Execution time |
| ------ | -------------- |
| 1 emulator, 1 real device, 2 retries | 32 sec |
| 1 emulator, 2 retries | 45 sec |

##### Report

* Half the size of the Spoon report (1.3 MB).
* Colored logs + stacktrace.
* Gif with the last seconds before a crash.

