package itserviceportal.model;

import java.io.Serializable;
import java.util.*;

/**
 * SupportTicketComparator
 * 
 * @author Brice Purton
 * @studentID 3180044
 * @lastModified: 14-05-2018
 */

public class SupportTicketComparator implements Comparator<SupportTicket> {

	public enum SortOrder {ASCENDING, DESCENDING}

	private SortOrder sortOrder;
	private int sortCategory;
	private int sortState;

	public SupportTicketComparator(SortOrder sortOrder) {
		this.sortOrder = sortOrder;
		this.sortCategory = sortCategory;
		this.sortState = sortState;
	}

	@Override
	public int compare(SupportTicket ticket1, SupportTicket ticket2) {
		Date date1 = ticket1.getReportedOn();
		Date date2 = ticket2.getReportedOn();
		int compare = date1.compareTo(date2);

		if (sortOrder == SortOrder.ASCENDING) {
			return compare;
		} else {
			return -1 * compare;
		}
	}
}	

