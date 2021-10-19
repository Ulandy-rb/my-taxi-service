update taxi_drive_info
set city_id = floor(random() * 8 + 1) where taxi_drive_info.driver_id>0;