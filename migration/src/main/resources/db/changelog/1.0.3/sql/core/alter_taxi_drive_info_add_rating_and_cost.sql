alter table taxi_drive_info add column if not exists rating real default 1 check ( rating >= 1 and rating <=5 );

comment on column taxi_drive_info.rating is 'Рейтинг водителя';

alter table taxi_drive_info add column if not exists minute_cost int default 10;

comment on column taxi_drive_info.minute_cost is 'Стоимость за минуту';