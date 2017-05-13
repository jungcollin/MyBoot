create table user (
     username varchar(20),
     password varchar(500),
     name varchar(20),
     isAccountNonExpired boolean,
     isAccountNonLocked boolean,
     isCredentialsNonExpired boolean,
     isEnabled boolean
);

create table authority (
    username varchar(20),
    authority_name varchar(20)
);


insert into user values ('cusonar', '1234', 'YCU', true, true, true, true);
insert into user values ('abc', 'abcd', 'ABC', true, true, true, true);
insert into authority values ('cusonar', 'ADMIN');
insert into authority values ('cusonar', 'USER');
insert into authority values ('abc', 'USER');

