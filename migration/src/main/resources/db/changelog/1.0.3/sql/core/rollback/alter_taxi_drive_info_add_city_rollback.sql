alter table taxi_drive_info drop column if exists city_id,
drop constraint if exists fk_city_id;