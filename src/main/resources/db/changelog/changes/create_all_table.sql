    create table project_plan (
       id bigint auto_increment not null,
       name varchar(50),
       primary key (id)
    );

    create table task (
       id bigint auto_increment not null,
       name varchar(50),
       start_date date,
       end_date date,
       status varchar(25),
       project_plan_id bigint not null,
       primary key (id)
    );

     create table task_dependency (
       Task_id bigint not null,
       dependency_id bigint not null
     );

