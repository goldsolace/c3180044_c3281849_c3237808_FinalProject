TODO...Setup Precedure

PC URL: http://localhost:8080/c3180044_c3281849_c3237808_FinalProject
Mobile URL: http://"Your_PC_IPv4_address":8080/c3180044_c3281849_c3237808_FinalProject

Directory Structure
.
├── Draft Documentation         # Initial documentation files
├── Final Documentation         # Revised documentation files
└── WEB-INF                     # web.xml
    ├── classes                 # Java packages
    │   └── com                 # Package Domain
    │       └── itserviceportal # Package name
    │           ├── command     # Service layer/ Behavior logic
    │           ├── controller  # Servlets for navigation
    │           ├── dao         # Data Access objects to connect to database
    │           ├── filter      # Security layer
    │           ├── listener    # Session tracking
    │           └── model       # Data objects (Beans)
    ├── lib                     # Jar Libraries
    └── view                    # Files for presentation
        ├── css                 # CSS Files
        ├── js                  # Javascript Files
        └── jsp                 # JSP files
            ├── errors          # Error pages
            ├── staff           # Staff only pages
            └── user            # User only pages


