# insert into categories(name) VALUES('HEALTH'),('MEDICINE'),
#                                     ('NUTRITION'),('GAMES'),('TRAINING'),('CARE'),
#                                     ('CATS'),('DOGS'),('FUN'),('ADVICE'),('INFO');

create table if not exists persistent_logins(
                                  username varchar(50) not null,
                                  series varchar(64) primary key,
                                  token varchar(64) not null,
                                  last_used timestamp not null
);