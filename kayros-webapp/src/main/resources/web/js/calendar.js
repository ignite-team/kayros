function initCalendar(date, startDate, endDate, locale, events) {

	let calendarContainer = document.getElementById('calendarContainer');

	calendar = new FullCalendar.Calendar(calendarContainer, {

		headerToolbar: {
			left: 'dayGridMonth,timeGridWeek,timeGridDay',
			center: 'title',
			right: 'today prev,next'
		},
		height: 'auto',
		contentHeight: 'auto',
		expandRows: true,
		initialDate: date,
		editable: false,
		selectable: true,
		locale: locale,
		events: events,
		validRange: {
			start: startDate,
			end: endDate
		}

	});

	setTimeout(() => {

		calendar.render();

	}, 2500);

}