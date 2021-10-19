alter table order_total add if not exists start_trip timestamp;
alter table order_total add if not exists end_trip timestamp;