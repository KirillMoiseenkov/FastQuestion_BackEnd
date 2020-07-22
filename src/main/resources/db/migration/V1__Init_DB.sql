create table questions (
       id  bigserial not null,
        lang varchar(255),
        probability float8,
        type_of_question int4,
        status int4,
        text varchar(255),
        primary key (id)
    );
create table answers (
       id  serial not null,
        lang varchar(255),
        text varchar(255),
        question_id int8,
        primary key (id)
    );

create table question_file (
       id  bigserial not null,
        rate int8,
        data oid,
        file_name varchar(255),
        file_type varchar(255),
        file_path varchar(255),
        version int4,
        question_id int8,
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