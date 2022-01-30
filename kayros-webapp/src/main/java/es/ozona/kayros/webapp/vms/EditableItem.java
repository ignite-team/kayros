package es.ozona.kayros.webapp.vms;

import java.util.HashMap;
import java.util.Map;

public class EditableItem<E> {
	private E item;
	private Boolean editingStatus;
	private Boolean created;
	private Map<String, Object> attributes = new HashMap<String, Object>();

	public EditableItem() {
		this(null, false, false);
	}

	public EditableItem(E item, Boolean created, Boolean editingStatus) {
		this.item = item;
		this.editingStatus = editingStatus;
		this.created = created;
	}

	public EditableItem(E item) {
		this(item, false, false);
	}

	public E getItem() {
		return item;
	}

	public void setItem(E item) {
		this.item = item;
	}

	public Boolean getEditingStatus() {
		return editingStatus;
	}

	public void setEditingStatus(Boolean editingStatus) {
		this.editingStatus = editingStatus;
	}		
	
	public Boolean getCreated() {
		return created;
	}

	public void setCreated(Boolean created) {
		this.created = created;
	}

	public Map<String, Object> getAttributes() {
		return attributes;
	}

	public void setAttributes(Map<String, Object> attributes) {
		this.attributes = attributes;
	}

	@Override
	public String toString() {	
		return item.toString();
	}
}
