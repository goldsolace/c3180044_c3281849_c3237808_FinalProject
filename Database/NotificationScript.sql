-- Seperate script for testing purposes

-- Creating the Notification Table
CREATE TABLE tbl_Notification (
    NotificationID INT NOT NULL AUTO_INCREMENT,
    NotificationText VARCHAR(255) NOT NULL,
	NotificationDate DATETIME NOT NULL,
    UserID INT NOT NULL,
    TicketID INT NOT NULL,

    PRIMARY KEY (NotificationID), 
    FOREIGN KEY (UserID) REFERENCES tbl_User (UserID),
    FOREIGN KEY (TicketID) REFERENCES tbl_SupportTicket(TicketID)
);

-- Insert into Notification Table

INSERT INTO tbl_Notification (NotificationText, NotificationDate, UserID, TicketID)
VALUES ('<strong>Support Ticket 9</strong> has been commented on.', NOW(), 3, 9);

INSERT INTO tbl_Notification (NotificationText, NotificationDate, UserID, TicketID)
VALUES ('<strong>Support Ticket 9</strong> has been added to the knowledge base.', NOW(), 3, 9);

INSERT INTO tbl_Notification (NotificationText, NotificationDate, UserID, TicketID)
VALUES ('Solution submitted to <strong>Support Ticket 9</strong>.', NOW(), 3, 9);

INSERT INTO tbl_Notification (NotificationText, NotificationDate, UserID, TicketID)
VALUES ('2Solution submitted to <strong>Support Ticket 9</strong>.', NOW(), 3, 9);

INSERT INTO tbl_Notification (NotificationText, NotificationDate, UserID, TicketID)
VALUES ('3Solution submitted to <strong>Support Ticket 9</strong>.', NOW(), 3, 9);

INSERT INTO tbl_Notification (NotificationText, NotificationDate, UserID, TicketID)
VALUES ('4Solution submitted to <strong>Support Ticket 9</strong>.', NOW(), 3, 9);

INSERT INTO tbl_Notification (NotificationText, NotificationDate, UserID, TicketID)
VALUES ('5Solution submitted to <strong>Support Ticket 9</strong>.', NOW(), 3, 9);

INSERT INTO tbl_Notification (NotificationText, NotificationDate, UserID, TicketID)
VALUES ('1 - 4.', NOW(), 4, 9);

INSERT INTO tbl_Notification (NotificationText, NotificationDate, UserID, TicketID)
VALUES ('2 - 4', NOW(), 4, 9);

INSERT INTO tbl_Notification (NotificationText, NotificationDate, UserID, TicketID)
VALUES ('2 - 4', NOW(), 4, 9);

INSERT INTO tbl_Notification (NotificationText, NotificationDate, UserID, TicketID)
VALUES ('1 - 5', NOW(), 5, 9);

INSERT INTO tbl_Notification (NotificationText, NotificationDate, UserID, TicketID)
VALUES ('2 - 5', NOW(), 5, 9);

INSERT INTO tbl_Notification (NotificationText, NotificationDate, UserID, TicketID)
VALUES ('3 - 5', NOW(), 5, 9);

INSERT INTO tbl_Notification (NotificationText, NotificationDate, UserID, TicketID)
VALUES ('4 - 5', NOW(), 5, 9);

INSERT INTO tbl_Notification (NotificationText, NotificationDate, UserID, TicketID)
VALUES ('5 - 5', NOW(), 5, 9);

INSERT INTO tbl_Notification (NotificationText, NotificationDate, UserID, TicketID)
VALUES ('6 - 5', NOW(), 5, 9);


DROP TABLE tbl_Notification;

TRUNCATE tbl_Notification;

SELECT * FROM tbl_Notification;

-- Query will be used whenever a new notification is made to limit user notifications to 5

DELETE FROM `tbl_Notification`
WHERE userID = 5 AND NotificationID NOT IN (
  SELECT NotificationID
  FROM (
    SELECT NotificationID 
    FROM `tbl_Notification`
    WHERE userID = 5
    ORDER BY NotificationID DESC
    LIMIT 5
  ) AS n
);