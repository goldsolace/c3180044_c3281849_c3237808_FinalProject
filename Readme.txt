SETUP INSTRUCTIONS:
===========================================================================
1. Navigate to META-INF directory.
2. Update the "context.xml" file by commenting out or uncommenting a specific database resource
    - If at the University, uncomment the "At Uni Resource" and comment out the "At home resource"
    - If at home or any other network uncomment the "At home resource" and comment out the "At Uni resource" then create a Putty connection following the instructions found on blackboard.
3. Setup is completed, database is pre-populated with data and login credientials (see below).
4. Lanuch the application by visiting one the of the below URL's found in the "URLS" section (see below).
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
└── WEB-INF                     # web.xml
    ├── classes                 # Java packages
    │   └── com                 # Package Domain
    │       └── itserviceportal # Package name
    │           ├── controller  # Servlets for navigation, Filters for authorisation, Listeners for session tracking
    │           └── model       # Data objects (Beans) and Data Access objects
    ├── lib                     # Jar Libraries
    └── view                    # Files for presentation
        ├── css                 # CSS Files
        ├── js                  # Javascript Files
        └── jsp                 # JSP files
            ├── errors          # Error pages
            ├── staff           # Staff only pages
            └── user            # User only pages

===========================================================================




