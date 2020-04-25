create table questions (
        id SERIAL not null,
        text varchar(255),
        lang varchar(255),
        primary key (id)
    );
create table answers (
        id SERIAL not null,
        text varchar(255),
        lang varchar(255),
        question_id int4,
        primary key (id)
    );

create table question_file (
        id SERIAL not null,
        data oid,
        file_name varchar(255),
        file_type varchar(255),
        question_id int4,
        primary key (id)
    );



alter table answers
       add constraint FK3erw1a3t0r78st8ty27x6v3g1
       foreign key (question_id)
       references questions;

alter table question_file
       add constraint FKpgdete2mtk0odamfx3v1itcj3
       foreign key (question_id)
       references questions;