alter table taxi_drive_info add column if not exists city_id bigint not null default 1;
alter table taxi_drive_info
    add constraint fk_city_id
    foreign key (city_id)
    references city_queue(city_id)   ;

comment on column taxi_drive_info.city_id is'Город';