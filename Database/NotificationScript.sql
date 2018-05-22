-- Seperate script for testing purposes

-- Creating the Notification Table
CREATE TABLE tbl_Notification (
    NotificationID INT NOT NULL AUTO_INCREMENT,
    NotificationAction VARCHAR(20) NOT NULL,
	NotificationDate DATETIME NOT NULL,
    UserID INT NOT NULL,
    TicketID INT NOT NULL,

    PRIMARY KEY (NotificationID), 
    FOREIGN KEY (UserID) REFERENCES tbl_User (UserID),
    FOREIGN KEY (TicketID) REFERENCES tbl_SupportTicket(TicketID)
);

-- Insert into Notification Table

INSERT INTO tbl_Notification (NotificationAction, NotificationDate, UserID, TicketID)
VALUES ('startWork', NOW(), 3, 9);

INSERT INTO tbl_Notification (NotificationAction, NotificationDate, UserID, TicketID)
VALUES ('submitSolution', NOW(), 3, 9);

INSERT INTO tbl_Notification (NotificationAction, NotificationDate, UserID, TicketID)
VALUES ('addKnowledge', NOW(), 3, 9);

INSERT INTO tbl_Notification (NotificationAction, NotificationDate, UserID, TicketID)
VALUES ('removeKnowledge', NOW(), 3, 9);

INSERT INTO tbl_Notification (NotificationAction, NotificationDate, UserID, TicketID)
VALUES ('comment', NOW(), 3, 9);

INSERT INTO tbl_Notification (NotificationAction, NotificationDate, UserID, TicketID)
VALUES ('comment', NOW(), 3, 9);

INSERT INTO tbl_Notification (NotificationAction, NotificationDate, UserID, TicketID)
VALUES ('startWork', NOW(), 3, 9);

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
    ORDER BY NotificationDate DESC
    LIMIT 5
  ) AS n
);