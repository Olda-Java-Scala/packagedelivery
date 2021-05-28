Package delivery application
=============================
Application reads user inputs from this console, inputs have to be in a specific format, about which the user is informed
at start of the run. Input consists of line of string, where there is information about postal code
and package weight. These data are saved into memory and application writes data into the console every minute. 
User can load file, in which fees for package weight are stored. If user loads this price list, application will compute price
for package delivery to every postal code and again, app writes data into the console every minute.

Build
=============================
Application can be built by Apache Maven

Java frameworks
=============================
Application is using java ver. 11 and spring boot framework ver. 2.5.0

Run
=============================
You can run application as normal spring boot application.