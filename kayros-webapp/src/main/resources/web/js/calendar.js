function initCalendar(date, startDate, endDate, locale, events) {

	let calendarContainer = document.getElementById('calendarContainer');

	var calendar = new FullCalendar.Calendar(calendarContainer, {

		headerToolbar: {
			left: 'dayGridMonth,timeGridWeek,timeGridDay',
			center: 'title',
			right: 'today prev,next'
		},
		height: 'auto',
		contentHeight: 'auto',
		initialDate: date,
		locale: locale,
		events: events,
		displayEventEnd: true,
		validRange: {
			start: startDate,
			end: endDate
		}

	});

	calendar.render();

}