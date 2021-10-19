update taxi_drive_info
set rating = round((random() * 4 + 1)::numeric,2) where taxi_drive_info.driver_id>0;