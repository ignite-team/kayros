package es.ozona.kayros.webapp.domain.model;

import org.springframework.util.ObjectUtils;

public class CalendarEvent {

	private String id;
	private String groupId;
	private boolean allDay;
	private String start;
	private String end;
	private String title;
	private String description;
	private String url;
	private String[] classNames;
	private String color;
	private String display;

	public CalendarEvent() {

	}

	public CalendarEvent(String id, String groupId, boolean allDay, String start, String end, String title, String description, String url, String[] classNames,
			String color, String display) {

		super();
		this.id = id;
		this.groupId = groupId;
		this.allDay = allDay;
		this.start = start;
		this.end = end;
		this.title = title;
		this.description = description;
		this.url = url;
		this.classNames = classNames;
		this.color = color;
		this.display = display;

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

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getDisplay() {
		return display;
	}

	public void setDisplay(String display) {
		this.display = display;
	}

	@Override
	public int hashCode() {

		return ObjectUtils.nullSafeHashCode(new Object[] { id, groupId, allDay, start, end, title, description, url, classNames, color, display });

	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public boolean equals(Object obj) {

		if (obj == null || !(obj instanceof CalendarEvent)) {

			return false;

		}

		return this.hashCode() == obj.hashCode();

	}
}
