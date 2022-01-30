package es.ozona.kayros.webapp.vms;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Window;

public abstract class CrudViewModel<E, F> {

	protected ListModelList<EditableItem<E>> entities;

	protected boolean displayEdit = true;

	protected FilterObject filterObject;

	protected Class<E> type;
	protected Class<F> filterType;

	@SuppressWarnings("unchecked")
	public CrudViewModel() throws InstantiationException, IllegalAccessException {
		this.type = (Class<E>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
		this.filterType = (Class<F>) ((ParameterizedType) getClass().getGenericSuperclass())
				.getActualTypeArguments()[1];
		filterObject = (FilterObject) filterType.newInstance();
	}

	public Class<E> getType() {
		return this.type;
	}

	@Init
	public void init() {

		initFilters();
		search();

	}

	public List<EditableItem<E>> getEntities() {
		return entities;
	}

	public FilterObject getFilterObject() {
		return filterObject;
	}

	public boolean isDisplayEdit() {
		return displayEdit;
	}

	@NotifyChange({ "entities", "displayEdit" })
	public void setDisplayEdit(boolean displayEdit) {
		this.displayEdit = displayEdit;
	}

	@Command
	public void changeEditableStatus(@BindingParam("entity") EditableItem<E> editableItem) {
		editableItem.setEditingStatus(!editableItem.getEditingStatus());
		refreshRowTemplate(editableItem);
	}

	@Command
	public void confirm(@BindingParam("entity") EditableItem<E> editableItem) {
		afterEditing(editableItem.getItem());
		E item = saveEntity(editableItem.getItem());
		editableItem.setItem(item);
		changeEditableStatus(editableItem);
		refreshRowTemplate(editableItem);
	}

	@Command
	public void cancel(@BindingParam("entity") EditableItem<E> editableItem) {
		if (editableItem.getCreated()) {
			entities.remove(editableItem);
		} else {
			editableItem.getAttributes().clear();
			changeEditableStatus(editableItem);
		}
	}

	@Command
	@NotifyChange({ "filters", "entities" })
	public void clear() {
		// filters.values().stream().forEach(f -> f.clear());
		search();
	}

	@SuppressWarnings("unchecked")
	@Command
	@NotifyChange({ "entities" })
	public void delete(@BindingParam("items") Set<Listitem> itemSet) {
		List<EditableItem<E>> editableItemList = itemSet.stream().map(i -> ((EditableItem<E>) i.getValue()))
				.collect(Collectors.toList());

		for (EditableItem<E> editableItem : editableItemList) {
			deleteEntity(editableItem.getItem());
			entities.remove(editableItem);
		}
	}

	@Command
	@NotifyChange({ "entities" })
	public void create() {
		EditableItem<E> editableItem = new EditableItem<E>(createEntity(), true, false);
		entities.add(0, editableItem);
		changeEditableStatus(editableItem);
	}

	protected void doBeforeShow(Map<String, Object> args) {

	}

	public void refreshRowTemplate(EditableItem<E> editableItem) {

		entities.set(entities.indexOf(editableItem), editableItem);
	}

	@Command
	@NotifyChange({ "entities" })
	public void search() {

		entities = generateEditableList(findAll(generateFilterQuery()));
	}

	@Command
	public void show(@BindingParam("page") String page, @BindingParam("index") Integer index) {
		final Map<String, Object> args = new HashMap<>();

		if (index == null) {
			args.put("bean", createEntity());
		} else {
			args.put("bean", entities.get(index).getItem());
		}

		doBeforeShow(args);
		Window window = (Window) Executions.createComponents(page, null, args);

		window.addEventListener(Events.ON_OK, new EventListener<Event>() {

			@SuppressWarnings("unchecked")
			@Override
			public void onEvent(Event event) throws Exception {
				doAfterModalWindow((E) event.getData());
				window.detach();
				search();
				// notificamos el cambio de la busqueda por comando.
//				BindUtils.postNotifyChange(CrudViewModel.this, "entities");
			}
		});

		window.doModal();

	}

	public ListModelList<EditableItem<E>> generateEditableList(List<E> list) {
		final ListModelList<EditableItem<E>> editableList = new ListModelList<EditableItem<E>>();
		list.forEach(i -> editableList.add(new EditableItem<E>(i)));
		return editableList;
	}

	protected String generateFilterQuery() {
	//	final List<String> expressions = filters.values().stream().map(e -> e.getExpression()).collect(Collectors.toList());
		return null;// RsqlExpressions.and(expressions).expression();
	}

	public E createEntity() {

		try {
			Constructor<?> constructor = type.getDeclaredConstructor();
			if (constructor == null) {
				constructor = type.getConstructors()[0];
			}
			return (E) constructor.newInstance();
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException e) {

			throw new RuntimeException("No se ha podido crear la instancia", e);
		}
	}

	public abstract void initFilters();

	public abstract List<E> findAll(String rsql);

	public abstract E saveEntity(E entity);

	public abstract void deleteEntity(E entity);

	public abstract void doAfterModalWindow(E entity);
	
	public void afterEditing(E item) {
	}

}
