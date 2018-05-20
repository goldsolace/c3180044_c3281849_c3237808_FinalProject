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
    UserPassword VARCHAR(255) NOT NULL,
    FirstName VARCHAR(255) NOT NULL,
    LastName VARCHAR(255) NOT NULL,
    ContactNum CHAR(10) NOT NULL,
    UserRole VARCHAR(255) NOT NULL DEFAULT 'User',
    PRIMARY KEY (UserID)
);


--Creating the Category Table
CREATE TABLE tbl_Category (
    CategoryID INT NOT NULL AUTO_INCREMENT,
    CatName VARCHAR(255) NOT NULL,
    PRIMARY KEY (CategoryID)
);


--Creating the Support Ticket Table
CREATE TABLE tbl_SupportTicket (
    TicketID INT NOT NULL AUTO_INCREMENT,
    Title VARCHAR(255) NOT NULL,
    Descrip VARCHAR(255) NOT NULL,
    TicketState VARCHAR(255) NOT NULL DEFAULT 'new',
    ReportedOn DATETIME NOT NULL,
    ResolvedOn DATETIME,
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
	CommentDate DATETIME NOT NULL,
    UserID INT NOT NULL,
    TicketID INT NOT NULL,

    PRIMARY KEY (CommentID), 
    FOREIGN KEY (UserID) REFERENCES tbl_User(UserID),
    FOREIGN KEY (TicketID) REFERENCES tbl_SupportTicket(TicketID)
);


--Creating the IssueDetails Table
CREATE TABLE tbl_IssueDetails (
    IssueDetailsID INT NOT NULL AUTO_INCREMENT,
    QuestionText VARCHAR(255) NOT NULL,
    ResponseText VARCHAR(255) NOT NULL,
	TicketID INT NOT NULL,
    
    PRIMARY KEY (IssueDetailsID), 
    FOREIGN KEY (TicketID) REFERENCES tbl_SupportTicket(TicketID)
);


--Creating Database View For Support Tickets
CREATE VIEW vw_SupportTickets AS
SELECT	t.TicketID, t.Title, t.Descrip, t.TicketState, t.ReportedOn, t.ResolvedOn, t.IsKnowledgeBase, t.ResolutionDetails, t.CategoryID, cat.CatName AS CategoryName, createdBy.UserID AS CreatedByUserID, createdBy.Email AS CreatedByEmail, createdBy.FirstName AS CreatedByFName, createdBy.LastName AS CreatedByLName, createdBy.ContactNum AS CreatedByContactNum, createdBy.UserRole AS CreatedByRole, resolvedBy.UserID AS ResolvedByUserID, resolvedBy.Email AS ResolvedByEmail, resolvedBy.FirstName AS ResolvedByFName, resolvedBy.LastName AS ResolvedByLName, resolvedBy.ContactNum AS ResolvedByContactNum, resolvedBy.UserRole AS ResolvedByRole
FROM tbl_SupportTicket t
INNER JOIN tbl_Category cat ON (t.CategoryID = cat.CategoryID)
INNER JOIN tbl_User createdBy ON (t.CreatedByUserID = createdBy.UserID)
LEFT JOIN tbl_User resolvedBy ON (t.ResolvedByUserID = resolvedBy.UserID);


--Creating Database View for Comments
CREATE VIEW vw_Comments
AS
SELECT c.*, u.FirstName, u.LastName, u.Email, u.ContactNum, u.UserRole
FROM tbl_Comment c
INNER JOIN tbl_User u ON (c.UserID = u.UserID);


--END: CREATE DATABASE TABLES
----------------------------------------------------------------
----------------------------------------------------------------


--BEGIN: INSERT DUMMY TEST DATA
----------------------------------------------------------------
----------------------------------------------------------------

--Inserting Values Into the Users Table
INSERT INTO tbl_User (Email, UserPassword, FirstName, LastName, ContactNum, UserRole) 
VALUES ('c3237808@uon.edu.au', 'test', 'jono', 'williams', '0412345678', 'Staff');
INSERT INTO tbl_User (Email, UserPassword, FirstName, LastName, ContactNum, UserRole)
VALUES ('c3180044@uon.edu.au', 'test', 'Brice', 'Purton', '0412345678', 'Staff');
INSERT INTO tbl_User (Email, UserPassword, FirstName, LastName, ContactNum, UserRole)
VALUES ('c3281849@uon.edu.au', 'test', 'Wajdi', 'Younes', '0412345678', 'Staff');
INSERT INTO tbl_User (Email, UserPassword, FirstName, LastName, ContactNum, UserRole)
VALUES ('c1234567@uon.edu.au', 'test', 'Billy', 'Jones', '0412345678', 'User');
INSERT INTO tbl_User (Email, UserPassword, FirstName, LastName, ContactNum, UserRole)
VALUES ('c1111111@uon.edu.au', 'test', 'Tom', 'Scott', '0412345678', 'User');
INSERT INTO tbl_User (Email, UserPassword, FirstName, LastName, ContactNum, UserRole)
VALUES ('c2222222@uon.edu.au', 'test', 'Joe', 'Blogs', '0478945612', 'User');

--Inserting Values into the Category table
INSERT INTO tbl_Category (CatName) VALUES ('Network');
INSERT INTO tbl_Category (CatName) VALUES ('Software');
INSERT INTO tbl_Category (CatName) VALUES ('Hardware');
INSERT INTO tbl_Category (CatName) VALUES ('Email');
INSERT INTO tbl_Category (CatName) VALUES ('Account');


--Inserting into tickets tables
INSERT INTO tbl_SupportTicket (Title, Descrip, TicketState, ReportedOn, ResolvedOn, IsKnowledgeBase, ResolutionDetails, CreatedByUserID, ResolvedByUserID, CategoryID)
VALUES ('No internet connection', 'Having trouble connecting to google.', 'new', '2018-05-01', NULL, 0, NULL, 6, NULL, 1);

INSERT INTO tbl_SupportTicket (Title, Descrip, TicketState, ReportedOn, ResolvedOn, IsKnowledgeBase, ResolutionDetails, CreatedByUserID, ResolvedByUserID, CategoryID)
VALUES ('Adobe Photoshop will not load', 'The application does not open when clicked.', 'in progress', '2018-05-11', NULL, 0, NULL, 6, NULL, 2);

INSERT INTO tbl_SupportTicket (Title, Descrip, TicketState, ReportedOn, ResolvedOn, IsKnowledgeBase, ResolutionDetails, CreatedByUserID, ResolvedByUserID, CategoryID)
VALUES ('Computer very slow', 'Computer in group study room is very slow', 'in progress', '2018-05-16', NULL, 0, NULL, 4, NULL, 3);

INSERT INTO tbl_SupportTicket (Title, Descrip, TicketState, ReportedOn, ResolvedOn, IsKnowledgeBase, ResolutionDetails, CreatedByUserID, ResolvedByUserID, CategoryID)
VALUES ('Can not send a email', 'email is failing', 'completed', '2018-05-10', '2018-05-18', 0, 'Reset incomming and outgoing mail settings.', 6, 1, 4);

INSERT INTO tbl_SupportTicket (Title, Descrip, TicketState, ReportedOn, ResolvedOn, IsKnowledgeBase, ResolutionDetails, CreatedByUserID, ResolvedByUserID, CategoryID)
VALUES ('My account has been locked.', 'Cannot access my account or login.', 'resolved', '2018-04-20', '2018-04-26', 1, 'Reactivated account in active directory.', 5, 2, 5);

INSERT INTO tbl_SupportTicket (Title, Descrip, TicketState, ReportedOn, ResolvedOn, IsKnowledgeBase, ResolutionDetails, CreatedByUserID, ResolvedByUserID, CategoryID)
VALUES ('Slow internet', 'The internet is super slow.', 'resolved', '2018-03-20', '2018-04-26', 1, 'Reset some network configurations.', 4, 3, 1);


--Inserting into comments table
INSERT INTO tbl_Comment (CommentText, UserID, TicketID, CommentDate)
VALUES ('Still wont open.', 6, 2, '2018-05-15');
INSERT INTO tbl_Comment (CommentText, UserID, TicketID, CommentDate)
VALUES ('Reinstalled the application. Please try again.', 1, 2, NOW());

INSERT INTO tbl_Comment (CommentText, UserID, TicketID, CommentDate)
VALUES ('Thank you, it works now.', 6, 3, '2018-05-17');
INSERT INTO tbl_Comment (CommentText, UserID, TicketID, CommentDate)
VALUES ('No problem.', 2, 3, '2018-05-18');

INSERT INTO tbl_Comment (CommentText, UserID, TicketID, CommentDate)
VALUES ('Has this been fixed yet?', 6, 4, '2018-05-11');
INSERT INTO tbl_Comment (CommentText, UserID, TicketID, CommentDate)
VALUES ('Yes. Try again now.', 3, 4, '2018-05-12');

INSERT INTO tbl_Comment (CommentText, UserID, TicketID, CommentDate)
VALUES ('I cant think of a comment', 6, 5, '2018-04-21');
INSERT INTO tbl_Comment (CommentText, UserID, TicketID, CommentDate)
VALUES ('This is a another comment', 3, 5, '2018-04-22');
INSERT INTO tbl_Comment (CommentText, UserID, TicketID, CommentDate)
VALUES ('This is a another another comment', 3, 5, '2018-04-23');

INSERT INTO tbl_Comment (CommentText, UserID, TicketID, CommentDate)
VALUES ('My internet is horrible atm.', 4, 6, '2018-03-21');
INSERT INTO tbl_Comment (CommentText, UserID, TicketID, CommentDate)
VALUES ('This issue has been resolved for you. Have a good day', 2, 6, '2018-03-22');
INSERT INTO tbl_Comment (CommentText, UserID, TicketID, CommentDate)
VALUES ('Thanks!', 4, 6, '2018-03-23');
--TODO: Insert IssueDetails values


--END: INSERT DUMMY TEST DATA
----------------------------------------------------------------
----------------------------------------------------------------