package itserviceportal.model.beans;

import java.io.Serializable;
import java.util.*;

/**
 * Comment bean
 *
 * @author Brice Purton, Jonothan Williams, Wajdi Yournes
 * @version 1.0
 * @since 19-05-2018
 */

public class Comment implements Serializable {

	private int commentID;
	private String commentText;
	private Date createdOn;
	private User createdBy;

	public Comment() {
	}

	public Comment(int commentID, String commentText, Date createdOn, User createdBy) {
		this.commentID = commentID;
		this.commentText = commentText;
		this.createdOn = createdOn;
		this.createdBy = createdBy;
	}

	public int getCommentID() { return commentID; }
	public void setCommentID(int commentID) { this.commentID = commentID; }

	public String getCommentText() { return commentText; }
	public void setCommentText(String text) { this.commentText = commentText; }

	public Date getCreatedOn() { return createdOn; }
	public void setCreatedOn(Date createdOn) { this.createdOn = createdOn; }

	public User getCreatedBy() { return createdBy; }
	public void setCreatedBy(User createdBy) { this.createdBy = createdBy; }
}
