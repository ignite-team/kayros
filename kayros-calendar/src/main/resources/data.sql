delete from shiftplan_workdays;
delete from shiftplans;
delete from calendars;

insert into calendars (calendar_id,description, marked_as_default, title, year) values('2b477572bd4a4c28a50464c9486492cc', 'Calendario general para el año 2021', true, 'Calendario laboral 2021', 2021 );

insert into shiftplans (calendar_id, efective_end_date, end_date, start_date, shiftplan_id) values ('2b477572bd4a4c28a50464c9486492cc', '2021-06-30', '2021-06-30', '2021-01-01', '2b477572bd4a4c28a50464c948649211');
insert into shiftplans (calendar_id, efective_end_date, end_date, start_date, shiftplan_id) values ('2b477572bd4a4c28a50464c9486492cc', '2021-08-31', '2021-08-31', '2021-07-01', '2b477572bd4a4c28a50464c9486495aa');
insert into shiftplans (calendar_id, efective_end_date, end_date, start_date, shiftplan_id) values ('2b477572bd4a4c28a50464c9486492cc', '2021-12-31', '2021-12-31', '2021-09-01', '2b477572bd4a4c28a50464c9486495cc');

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
