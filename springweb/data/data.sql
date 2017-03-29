create table persistent_logins (
    username varchar(64) not null,
    series varchar(64) primary key,
    token varchar(64) not null,
    last_used timestamp not null
);

insert into user(id,username,password,enabled,accountNonExpired,accountNonLocked,credentialsNonExpired,descn) 
values(1,'admin','a40546cc4fd6a12572828bb803380888ad1bfdab',1,1,1,1,'管理员');
insert into user(id,username,password,enabled,accountNonExpired,accountNonLocked,credentialsNonExpired,descn)
values(2,'user','b6b1f4781776979c0775c71ebdd8bdc084aac5fe',1,1,1,1,'用户');

insert into role(id,name,descn) values(1,'ROLE_ADMIN','管理员角色');
insert into role(id,name,descn) values(2,'ROLE_USER','用户角色');

insert into user2role(user_id,role_id) values(1,1);
insert into user2role(user_id,role_id) values(1,2);
insert into user2role(user_id,role_id) values(2,2);

insert into resource(id,icon,name,grade,url,_order,descn,parent_id) values(1,'','系统管理','1','',1,'', null);
insert into resource(id,icon,name,grade,url,_order,descn,parent_id) values(2,'','用户管理','2','/user',1,'','1');
insert into resource(id,icon,name,grade,url,_order,descn,parent_id) values(3,'','角色管理','2','/role',2,'','1');
insert into resource(id,icon,name,grade,url,_order,descn,parent_id) values(4,'','资源管理','2','/resource',3,'','1');
insert into resource(id,icon,name,grade,url,_order,descn,parent_id) values(5,'','日志管理','1','',2,'', null);

insert into resource2role(resource_id,role_id) values(1,1);
insert into resource2role(resource_id,role_id) values(2,1);
insert into resource2role(resource_id,role_id) values(3,1);
insert into resource2role(resource_id,role_id) values(4,1);
insert into resource2role(resource_id,role_id) values(5,1);