package es.ozona.ignite.views.layouts;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Html;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.data.renderer.LocalDateRenderer;
import com.vaadin.flow.data.renderer.LocalDateTimeRenderer;
import com.vaadin.flow.data.renderer.Renderer;
import com.vaadin.flow.data.renderer.TemplateRenderer;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;

import es.ozona.ignite.views.MainView;

@Route(value = "hours", layout = MainView.class)
@RouteAlias(value = "", layout = MainView.class)
@PageTitle("Hours")
@CssImport("styles/views/mainwiew/main-wiew-view.css")
public class HourView extends VerticalLayout {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7183626179055340831L;
	private final List<TimeCheck> timeChecks = new ArrayList();
	private final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm");

	public HourView() {
		setId("hour-wiew");

		final Tab dayTab = new Tab("Day");
		final VerticalLayout dayLayout = new VerticalLayout();
		dayLayout.setVisible(true);
		dayLayout.getStyle().set("overflow-x", "false");

		final Tab weekTab = new Tab("Week");
		final VerticalLayout weekLayout = new VerticalLayout();
		weekLayout.setVisible(false);

		final Tab monthTab = new Tab("Month");
		final VerticalLayout monthLayout = new VerticalLayout();
		monthLayout.setVisible(false);

		final Tab yearTab = new Tab("Year");
		final VerticalLayout yearLayout = new VerticalLayout();
		yearLayout.setVisible(false);

		Map<Tab, Component> tabsToPages = new HashMap<>();
		tabsToPages.put(dayTab, dayLayout);
		tabsToPages.put(weekTab, weekLayout);
		tabsToPages.put(monthTab, monthLayout);
		tabsToPages.put(yearTab, yearLayout);

		Tabs tabs = new Tabs(dayTab, weekTab, monthTab, yearTab);
		Div layouts = new Div(dayLayout, weekLayout, monthLayout, yearLayout);

		Set<Component> pagesShown = Stream.of(dayLayout).collect(Collectors.toSet());

		tabs.addSelectedChangeListener(event -> {
			pagesShown.forEach(page -> page.setVisible(false));
			pagesShown.clear();
			Component selectedPage = tabsToPages.get(tabs.getSelectedTab());
			selectedPage.setVisible(true);
			pagesShown.add(selectedPage);
		});

		Grid<TimeCheck> grid = new Grid<>();
		grid.setWidthFull();
		grid.getStyle().set("overflow-x", "false");

		grid.addColumn(TimeCheck::getHours).setSortable(true).setFrozen(true).setHeader(new Html("<i>Día</i>"))
				.setAutoWidth(true);


		grid.addColumn(TimeCheck::getHours).setSortable(true).setFrozen(true).setHeader(new Html("<i>Horas</i>"))
				.setAutoWidth(true);
		
		grid.addColumn(TemplateRenderer
		        .<TimeCheck>of("[[item.startTime]]")
		        .withProperty("startTime", tc -> dtf.format(tc.getStartTime())))
		.setSortable(true).setFrozen(true).setHeader(new Html("<i>Entrada</i>")).setAutoWidth(true);		

		grid.addColumn(TemplateRenderer
		        .<TimeCheck>of("[[item.endTime]]")
		        .withProperty("endTime", tc -> dtf.format(tc.getEndTime())))
		.setSortable(true).setFrozen(true).setHeader(new Html("<i>Salida</i>")).setAutoWidth(true);

		grid.addColumn(TemplateRenderer
		        .<TimeCheck>of("[[item.duration]]")
		        .withProperty("duration", tc -> dtf.format(LocalDateTime.of(1, 1, 1, 0, 0).plusSeconds(tc.getDuration().getSeconds()))))
		.setSortable(true).setFrozen(true).setHeader(new Html("<i>Duración</i>")).setAutoWidth(true);

		dayLayout.add(grid);

		timeChecks.add(new TimeCheck(1, 8, LocalTime.now(), LocalTime.now(), Duration.ofHours(8)));
		timeChecks.add(new TimeCheck(1, 8, LocalTime.now(), LocalTime.now(), Duration.ofHours(8)));
		timeChecks.add(new TimeCheck(1, 8, LocalTime.now(), LocalTime.now(), Duration.ofHours(8)));

		grid.setItems(timeChecks);

		add(tabs);
		tabs.setWidthFull();

		add(layouts);
		layouts.setWidthFull();

	}

}
