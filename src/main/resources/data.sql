--    application.yaml파일에   jpa.defer-datasource-initialization: true 설정이 되어있어야 함

insert into account_user(id, name, created_at, updated_at)
values (1, 'Pororo', now(), now());

insert into account_user(id, name, created_at, updated_at)
values (2, 'Lupi', now(), now());

insert into account_user(id, name, created_at, updated_at)
values (3, 'Edi', now(), now());