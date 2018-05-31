SETUP INSTRUCTIONS:
===========================================================================
1. Navigate to META-INF directory.
2. Update the "context.xml" file by commenting out or uncommenting a specific database resource
    - If at the University, uncomment the "At Uni Resource" and comment out the "At home resource"
    - If at home or any other network uncomment the "At home resource" and comment out the "At Uni resource" then create a Putty connection following the instructions found on blackboard.
3. Compile the application then run PATH TO TOMCAT\bin\startup.bat.
4. Setup is completed, database is pre-populated with data and login credientials (see below).
5. Lanuch the application by visiting one the of the below URL's found in the "URLS" section (see below).
===========================================================================


COMPILATION:
===========================================================================
1. Download and Install JDK (Recommened version 8)
2. Download Tomcat 8.5
3. Add both the following libraries to your classpath
	PATH TO TOMCAT\lib\servlet-api.jar
	PATH TO TOMCAT\lib\jsp-api.jar
4. Navigate to the root directory PATH TO TOMCAT\webapps\c3180044_c3281849_c3237808_FinalProject
5. Compile the java package located in WEB-INF/classes/

PREWRITTEN JAVAC COMMANDS

# UNIX
javac WEB-INF/classes/itserviceportal/controller/*.java WEB-INF/classes/itserviceportal/model/*/*.java WEB-INF/classes/itserviceportal/customtags/*.java

# MS-DOS
javac WEB-INF\classes\itserviceportal\controller\*.java WEB-INF\classes\itserviceportal\model\beans\*.java WEB-INF\classes\itserviceportal\model\datalayer\*.java WEB-INF\classes\itserviceportal\customtags\*.java

===========================================================================


TEST LOGIN CREDENTIALS:
===========================================================================
Test login credientials have been stored inside the database for testing purposes. Below are the
login credients for both users and staff:
________________________________________________________________
|        EMAIL          |     PASSWORD        |    USER ROLE    |
|  c3237808@uon.edu.au  |      test1234       |      Staff      |
|  c3180044@uon.edu.au  |      test1234       |      Staff      |
|  c3281849@uon.edu.au  |      test1234       |      Staff      |
|  c1234567@uon.edu.au  |      test1234       |      User       |
|  c1111111@uon.edu.au  |      test1234       |      User       |
|  c2222222@uon.edu.au  |      test1234       |      User       |
|_______________________________________________________________|

===========================================================================


URLs:
===========================================================================
PC URL: http://localhost:8080/c3180044_c3281849_c3237808_FinalProject
Mobile URL: http://"Your_PC_IPv4_address":8080/c3180044_c3281849_c3237808_FinalProject
===========================================================================


DIRECTORY STRUCTURE:
===========================================================================
.
├── Database                    # CreateDBScript.sql
├── Draft Documentation         # Initial documentation files
├── Final Documentation         # Revised documentation files
├── META-INF                    # context.xml
└── WEB-INF                     # web.xml, customtags.tld
    ├── classes                 #
    │   └── itserviceportal     #
    │       ├── controller      # Servlets for navigation, Filters for authentication, Listeners for session management
    │       ├── customtags      # JSP SimpleTags
    │       └── model			# Java Package
    │           ├── beans       # Beans
    │           └── datalayer   # Data Access Objects
    ├── lib                     # Jar Libraries
    └── view                    #
        ├── css                 # CSS Files
        ├── js                  # Javascript Files
        └── jsp                 # All access pages
            ├── includes        # JSPs only included/imported in other pages
            ├── staff           # Staff only pages
            ├── user            # User/Staff only pages
            └── userx           # User only pages

===========================================================================




