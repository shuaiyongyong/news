select * from topic
select * from  COMMENTS
select * from NEWS where NID = 173;
select * from NEWS_users;
select table_name from user_tables
select 1 from news_users where uname='admin' and upwd='a'


select nid,ntitle,ncreatedate from
(select n.*,rownum rn from
(select  * from NEWS order by 5)n where rownum<9)
where rn>7

select * from news where ntid in(1,2,5) order by 2,5;

select * from(select * from news where ntid=1 order by 5) where rownum<=5  union
select * from(select * from news where ntid=2 order by 5) where rownum<=5 union
select * from(select * from news where ntid=5 order by 5) where rownum<=5 


select * from news where 
ntid in(select * from(select ntid from news where ntid=1 order by 5) where rownum<=5 )
or
ntid in(select * from(select ntid from news where ntid=2 order by 5) where rownum<=5 )
or
ntid in(select * from(select ntid from news where ntid=5 order by 5) where rownum<=5 )


select nid,ntid,ntitle from news where 
nid in(select nid from(select * from news where ntid=1 order by 5) where rownum<=5 ) 
or nid in(select nid from(select * from news where ntid=2 order by 5) where rownum<=5 ) 
or nid in(select nid from(select * from news where ntid=5 order by 5) where rownum<=5 )

select * from news_users

select ceil(count(1)/20) tp from news

create sequence seq_comment start with 111;


drop trigger YC.CID_TG;


select seq_comment.nextval from dual;
drop table news_archive;

create table news_archive as select NID,NTID,
NTITLE,NAUTHOR,NCREATEDATE,NPICPATH,to_lob(NCONTENT) NCONTENT,sysdate nMODIFYDATE,
NSUMMARY from news where 1 != 1;

--ORA-01536: 超出表空间 'USERS' 的空间限额
alter user jsp quota unlimited on users;

select * from news_archive;

create sequence seq_news start with 166;

select * from topic;

select * from news;

select max(tid) from topic;

create sequence seq_topicId start with 32;

select ceil(count(1)/5) tp from topic

select * from (select n.*,rownum rn from(select  * from topic order by 5)n where rownum<=5) where rn>5

select * from news_users
create sequence seq_usid start with 2;
select * from news_users where usid=1;
select * from news_users where uname='a' and upwd='a'
insert into comments values(seq_comment.nextval,207,'叽叽叽叽叽叽',sysdate,'127.0.0.1','辣鸡')

alter table news_users add limits int default 0
update news_users set limits = 1 where uname = 'admin'
select * from news
select * from news_archive
select * from (select n.*,rownum rn from(select  * from news_archive order by 1)n where rownum<=5) where rn>5


select * from (select n.*,rownum rn from(select  * from news_archive order by 1)n where rownum<=10) where rn>0

select   *   from   user_constraints  where   table_name   =   upper('news_archive');
select   *   from   cols   WHERE   TABLE_name=upper('news_archive');
select   *   from   cols   WHERE   TABLE_name=upper('news');