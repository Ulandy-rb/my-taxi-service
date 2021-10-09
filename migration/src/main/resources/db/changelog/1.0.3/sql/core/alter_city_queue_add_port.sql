alter table city_queue add column if not exists port int not null default 0;
comment on column city_queue.port is 'Порт очереди';