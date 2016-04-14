# khs-hystrix-demo
Hystrix Demonstration Application

#How To Use
This is a simple example of a hystrix enabled Java Spring application. To properly use this - import it as a general project, then configure it as a maven project and run it as a spring boot project.

This app is a headless service that has the following endpoints:

**http://{hostname}/api/payloads - GET**

returns a ResponseEntity with a list of a sample JSON payload and the proper http code

**http://{hostname}/api/payloads/hystrix - GET**

returns a ResponseEntity with a list of a sample JSON payload and the proper http code from hystrix enabled commands

**http://{hostname}/api/payloads/fail - GET**

returns a ResponseEntity with a list of a sample JSON payload and the proper http code from normal service endpoints desinged to fail

**http://{hostname}/api/payloads/fail/hystrix - GET**

returns a ResponseEntity with a list of a sample JSON payload and the proper http code from hystrix enabled command endpoints desinged to fail

#How to Test
All of these endpoints can be tested from postman (a google chrome app) to see the sample returns. To help ensure that failures are seen, these service are very unreliable. At random intervals every service throw an exception. To see how these behave it is most easily illustrated by having your Spring service console open so you can read the application logs on your screen, then right next to that (or on another monitor if you have one) exercise the endpoints in postman. Send several messages to each endpoint over and over again by clicking multiple times.  This will illustrate how normal failures would appear and how hystrix failures would appear.  

This app also has a test project that has a single MockMVC test that can be run as a jUnit test that will spit out logs that demonstrate the different behaviors and messaging that these implementations report.

#How to see metrics
The hystrix stream and health endpoints are exposed for this app and can be accessed via the following endpoints:

**http://{hostname}/api/health**

**http://{hostname}/api/hystrix.stream**

These can also be used in tandem with a hystrix dashboard to visualize the data and status of the app.  The repo for Hystrix Dashboard is:

https://github.com/spring-cloud-samples/hystrix-dashboard.git



#Disclaimer
This project should not be used in production.

This project does not conform to pure rest standards.

This is a contrived project with contrived returns, don't take it too seriously.

If you want more - please see the following links:

https://github.com/Netflix/Hystrix

https://github.com/Netflix/Hystrix/tree/master/hystrix-contrib/hystrix-javanica

https://keyholesoftware.com/2016/02/01/hystrix-to-prevent-hysterix/ 

