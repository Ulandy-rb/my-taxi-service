update taxi_drive_info
set rating = floor(random() * 9 + 1) where taxi_drive_info.driver_id>0;