--File Description: Create Database Script
--Authors: c3180044 c3281849 c3237808
--SENG2050 Assignment 3: Final Project


--BEGIN: CREATE DATABASE TABLES
----------------------------------------------------------------
----------------------------------------------------------------

--Creating the Users table
CREATE TABLE tbl_User (
    UserID INT NOT NULL AUTO_INCREMENT,
    Email VARCHAR(255) NOT NULL,
    Password VARCHAR(255) NOT NULL,
    FirstName VARCHAR(255) NOT NULL,
    LastName VARCHAR(255) NOT NULL,
    ContactNum CHAR(10) NOT NULL,
    Role VARCHAR(255) NOT NULL DEFAULT 'User',
    PRIMARY KEY (UserID)
);


--Creating the Category Table
CREATE TABLE tbl_Category (
    CategoryID INT NOT NULL AUTO_INCREMENT,
    Name VARCHAR(255) NOT NULL,
    PRIMARY KEY (CategoryID)
);


--Creating the Support Ticket Table
CREATE TABLE tbl_SupportTicket (
    TicketID INT NOT NULL AUTO_INCREMENT,
    Title VARCHAR(255) NOT NULL,
    Descrip VARCHAR(255) NOT NULL,
    TicketState VARCHAR(255) NOT NULL DEFAULT 'new',
    ReportedOn TIMESTAMP NOT NULL DEFAULT NOW(),
    ResolvedOn TIMESTAMP,
    IsKnowledgeBase TINYINT(1) NOT NULL DEFAULT 0,
    ResolutionDetails VARCHAR(255),
    CreatedByUserID INT NOT NULL,
    ResolvedByUserID INT,
    CategoryID INT NOT NULL,

    PRIMARY KEY (TicketID),
    FOREIGN KEY (CreatedByUserID) REFERENCES tbl_User(UserID),
    FOREIGN KEY (ResolvedByUserID) REFERENCES tbl_User(UserID),
    FOREIGN KEY (CategoryID) REFERENCES tbl_Category(CategoryID)
);


--Creating the Comment Table
CREATE TABLE tbl_Comment (
    CommentID INT NOT NULL AUTO_INCREMENT,
    CommentText VARCHAR(255) NOT NULL,
    UserID INT NOT NULL,
    TicketID NOT NULL,

    PRIMARY KEY (CommentID), 
    FOREIGN KEY (UserID) REFERENCES tbl_User(UserID),
    FOREIGN KEY (TicketID) REFERENCES tbl_SupportTicket(TicketID)
);


--Creating the IssueDetails Table
CREATE TABLE tbl_IssueDetails (
    IssueDetailsID INT NOT NULL AUTO_INCREMENT,
    QuestionText VARCHAR(255) NOT NULL,
    ResponseText VARCHAR(255) NOT NULL,
    
    PRIMARY KEY (IssueDetailsID), 
    FOREIGN KEY (TicketID) REFERENCES tbl_SupportTicket(TicketID)
);


--END: CREATE DATABASE TABLES
----------------------------------------------------------------
----------------------------------------------------------------


--BEGIN: INSERT DUMMY TEST DATA
----------------------------------------------------------------
----------------------------------------------------------------

--Inserting Values Into the Users Table
INSERT INTO tbl_User (Email, Password, FirstName, LastName, ContactNum) 
VALUES ('c3237808@uon.edu,au', 'test', 'jono', 'williams', '0412345678', 'Staff');
INSERT INTO tbl_User (Email, Password, FirstName, LastName, ContactNum) 
VALUES ('c3180044@uon.edu,au', 'test', 'Brice', 'Purton', '0412345678', 'Staff');
INSERT INTO tbl_User (Email, Password, FirstName, LastName, ContactNum) 
VALUES ('c3281849@uon.edu,au', 'test', 'Wajdi', 'Younes', '0412345678', 'Staff');
INSERT INTO tbl_User (Email, Password, FirstName, LastName, ContactNum) 
VALUES ('c3237123@uon.edu,au', 'test', 'Billy', 'Jones', '0412345678', 'User');
INSERT INTO tbl_User (Email, Password, FirstName, LastName, ContactNum) 
VALUES ('c3180456@uon.edu,au', 'test', 'Tom', 'Scott', '0412345678', 'User');
INSERT INTO tbl_User (Email, Password, FirstName, LastName, ContactNum) 
VALUES ('c3281789@uon.edu,au', 'test', 'Joe', 'Blogs', '0478945612', 'User');

--Inserting Values into the Category table
INSERT INTO tbl_Category (Name) VALUES ('Network');
INSERT INTO tbl_Category (Name) VALUES ('Software');
INSERT INTO tbl_Category (Name) VALUES ('Hardware');
INSERT INTO tbl_Category (Name) VALUES ('Email');
INSERT INTO tbl_Category (Name) VALUES ('Account');


--TODO: Insert support ticket values
--TODO: Insert comment values
--TODO: Insert IssueDetails values


--END: INSERT DUMMY TEST DATA
----------------------------------------------------------------
----------------------------------------------------------------