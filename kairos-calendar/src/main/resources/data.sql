insert into calendars (calendar_id,description, marked_as_default, title, year) values('2B477572-BD4A-4C28-A504-64C9486492CC', 'Calendario general para el año 2020', true, 'Calendario laboral 2020', 2020 );

insert into shiftplans (calendar_id, efective_end_date, end_date, start_date, shiftplan_id) values ('2B477572-BD4A-4C28-A504-64C9486492CC', '2020-06-30', '2020-06-30', '2020-01-01', '2B477572-BD4A-4C28-A504-64C948649211');
insert into shiftplans (calendar_id, efective_end_date, end_date, start_date, shiftplan_id) values ('2B477572-BD4A-4C28-A504-64C9486492CC', '2020-08-31', '2020-08-31', '2020-07-01', '2B477572-BD4A-4C28-A504-64C9486495AA');
insert into shiftplans (calendar_id, efective_end_date, end_date, start_date, shiftplan_id) values ('2B477572-BD4A-4C28-A504-64C9486492CC', '2020-12-31', '2020-12-31', '2020-09-01', '2B477572-BD4A-4C28-A504-64C9486495CC');

insert into shiftplan_workdays (breaktime_end, breaktime_start, day_of_week, rest_time, worktime_entry, worktime_exit, shiftplan_id) values ('15:30','15:00',0,'0:30','08:00','17:00', 1);
insert into shiftplan_workdays (breaktime_end, breaktime_start, day_of_week, rest_time, worktime_entry, worktime_exit, shiftplan_id) values ('15:30','15:00',1,'0:30','08:00','17:00', 1);
insert into shiftplan_workdays (breaktime_end, breaktime_start, day_of_week, rest_time, worktime_entry, worktime_exit, shiftplan_id) values ('15:30','15:00',2,'0:30','08:00','17:00', 1);
insert into shiftplan_workdays (breaktime_end, breaktime_start, day_of_week, rest_time, worktime_entry, worktime_exit, shiftplan_id) values ('15:30','15:00',3,'0:30','08:00','17:00', 1);
insert into shiftplan_workdays (breaktime_end, breaktime_start, day_of_week, rest_time, worktime_entry, worktime_exit, shiftplan_id) values (null,null,4,'00:20','08:00','20:00', 1);

insert into shiftplan_workdays (breaktime_end, breaktime_start, day_of_week, rest_time, worktime_entry, worktime_exit, shiftplan_id) values (null,null,0,'00:20','08:00','17:00', 2);
insert into shiftplan_workdays (breaktime_end, breaktime_start, day_of_week, rest_time, worktime_entry, worktime_exit, shiftplan_id) values (null,null,1,'00:20','08:00','17:00', 2);
insert into shiftplan_workdays (breaktime_end, breaktime_start, day_of_week, rest_time, worktime_entry, worktime_exit, shiftplan_id) values (null,null,2,'00:20','08:00','17:00', 2);
insert into shiftplan_workdays (breaktime_end, breaktime_start, day_of_week, rest_time, worktime_entry, worktime_exit, shiftplan_id) values (null,null,3,'00:20','08:00','17:00', 2);
insert into shiftplan_workdays (breaktime_end, breaktime_start, day_of_week, rest_time, worktime_entry, worktime_exit, shiftplan_id) values (null,null,4,'00:20','08:00','20:00', 2);

insert into shiftplan_workdays (breaktime_end, breaktime_start, day_of_week, rest_time, worktime_entry, worktime_exit, shiftplan_id) values ('15:30','15:00',0,'0:30','08:00','17:00', 3);
insert into shiftplan_workdays (breaktime_end, breaktime_start, day_of_week, rest_time, worktime_entry, worktime_exit, shiftplan_id) values ('15:30','15:00',1,'0:30','08:00','17:00', 3);
insert into shiftplan_workdays (breaktime_end, breaktime_start, day_of_week, rest_time, worktime_entry, worktime_exit, shiftplan_id) values ('15:30','15:00',2,'0:30','08:00','17:00', 3);
insert into shiftplan_workdays (breaktime_end, breaktime_start, day_of_week, rest_time, worktime_entry, worktime_exit, shiftplan_id) values ('15:30','15:00',3,'0:30','08:00','17:00', 3);
insert into shiftplan_workdays (breaktime_end, breaktime_start, day_of_week, rest_time, worktime_entry, worktime_exit, shiftplan_id) values (null,null,4,'00:20','08:00','20:00', 3);
