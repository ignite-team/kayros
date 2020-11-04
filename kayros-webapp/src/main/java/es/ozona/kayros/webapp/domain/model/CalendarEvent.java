package es.ozona.kayros.webapp.domain.model;

import org.springframework.util.ObjectUtils;

public class CalendarEvent {

	private String id;
	private String groupId;
	private boolean allDay;
	private String start;
	private String end;
	private String title;
	private String url;
	private String[] classNames;

	public CalendarEvent() {

	}

	public CalendarEvent(String id, String groupId, boolean allDay, String start, String end, String title, String url, String[] classNames) {

		super();
		this.id = id;
		this.groupId = groupId;
		this.allDay = allDay;
		this.start = start;
		this.end = end;
		this.title = title;
		this.url = url;
		this.classNames = classNames;

	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public boolean getAllDay() {
		return allDay;
	}

	public void setAllDay(boolean allDay) {
		this.allDay = allDay;
	}

	public String getStart() {
		return start;
	}

	public void setStart(String start) {
		this.start = start;
	}

	public String getEnd() {
		return end;
	}

	public void setEnd(String end) {
		this.end = end;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String[] getClassNames() {
		return classNames;
	}

	public void setClassNames(String[] classNames) {
		this.classNames = classNames;
	}

	@Override
	public int hashCode() {

		return ObjectUtils.nullSafeHashCode(new Object[] { id, groupId, allDay, start, end, title, url, classNames });

	}

	@Override
	public boolean equals(Object obj) {

		if (obj == null || !(obj instanceof CalendarEvent)) {

			return false;

		}

		return this.hashCode() == obj.hashCode();

	}
}
