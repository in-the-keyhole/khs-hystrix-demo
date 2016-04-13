# khs-hystrix-demo
Hystrix Demonstration Application

#How To Use
This is a simple example of a hystrix enabled application. To properly use this - import it as a general project, then configure it as a maven project and run it as a spring boot project.

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

This app also has a test project that has a single MockMVC test that can be run as a jUnit test that will spit out logs that demonstrate the different behaviors and messaging that these implementations report.

#Disclaimer
This project should not be used in production.

This project does not conform to pure rest standards.

This is a contrived project with contrived returns, don't take it too seriously.

If you want more - please see the following links:

https://github.com/Netflix/Hystrix

https://github.com/Netflix/Hystrix/tree/master/hystrix-contrib/hystrix-javanica

https://keyholesoftware.com/2016/02/01/hystrix-to-prevent-hysterix/ 

