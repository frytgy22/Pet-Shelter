create schema people default charset utf8 collate utf8_general_ci;

create table person
(
	id int auto_increment
		primary key,
	first_name varchar(50) not null,
	last_name varchar(50) not null,
	gender set('male', 'female', 'unknown') default 'unknown' not null
);

# create index person_first_name_idx
# 	on person (first_name);

