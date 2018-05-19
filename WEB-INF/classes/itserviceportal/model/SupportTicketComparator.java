package itserviceportal.model;

import java.io.Serializable;
import java.util.*;

/**
 * SupportTicketComparator
 *
 * @author Brice Purton, Jonothan Williams, Wajdi Yournes
 * @version 1.0
 * @since 19-05-2018
 */

public class SupportTicketComparator implements Comparator<SupportTicket> {

	public enum SortOrder { 
		ASCENDING, DESCENDING
	}

	public enum SortDate { 
		REPORTED, RESOLVED
	}

	private SortOrder sortOrder;
	private SortDate sortDate;
	private int sortCategory;
	private int sortState;

	public SupportTicketComparator(SortOrder sortOrder, SortDate sortDate) {
		this.sortOrder = sortOrder;
		this.sortDate = sortDate;
		this.sortCategory = sortCategory;
		this.sortState = sortState;
	}

	@Override
	public int compare(SupportTicket ticket1, SupportTicket ticket2) {
		Date date1 = ticket1.getReportedOn();
		Date date2 = ticket2.getReportedOn();

		// Sort by date resolved
		if (sortDate == SortDate.RESOLVED) {
			date1 = ticket1.getResolvedOn();
			date2 = ticket2.getResolvedOn();
		}

		int compare = date1.compareTo(date2);

		// Sort by ascending or descending date
		if (sortOrder == SortOrder.ASCENDING) {
			return compare;
		} else {
			return -1 * compare;
		}
	}
}	

