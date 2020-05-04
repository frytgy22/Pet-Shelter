# password=password

# insert into users(email, name, password) VALUES('admin@1.ua', 'admin',
#                                                 '$2a$10$GVdyIMr61bhkul6eW1ZzPu2zRhFnpYqpp.oq5OFB4JH.sW.49X/Zi');
# insert into user_role VALUES(1, 'ROLE_ADMIN');

# insert into categories(name) VALUES('HEALTH'),('MEDICINE'),
#                                     ('NUTRITION'),('GAMES'),('TRAINING'),('CARE'),
#                                     ('CATS'),('DOGS'),('FUN'),('ADVICE'),('INFO');

create table if not exists persistent_logins(
                                  username varchar(50) not null,
                                  series varchar(64) primary key,
                                  token varchar(64) not null,
                                  last_used timestamp not null
);