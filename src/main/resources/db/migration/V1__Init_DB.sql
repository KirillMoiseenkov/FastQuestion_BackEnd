create table questions (
        id SERIAL,
        text varchar(255),
        lang varchar(255),
        primary key (id)
    );
create table answers (
        id SERIAL,
        text varchar(255),
        lang varchar(255),
        question_id int4,
        primary key (id)
    );
alter table answers
       add constraint FK3erw1a3t0r78st8ty27x6v3g1
       foreign key (question_id)
       references questions;

