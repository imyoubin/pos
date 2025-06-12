show databases;

use web_db;

drop table category;
drop table menu;
drop table orders;

show tables;
-- 카테고리
create table category(
	category_id			int				primary key			auto_increment,
    category_name		varchar(100) 	not null,
    category_desc		varchar(200),
    emoji				varchar(50),
    reg_date			datetime		default		now()
);
-- 메뉴
create table menu (
	menu_id			int				primary key 			auto_increment,
    menu_name		varchar(100)	not null,
    menu_price		int				not null,
    category_id		int,
    foreign key (category_id) references category(category_id)
);
   -- 오더
 create table orders(   -- order는 예약어라서 안됨
	order_id		int				primary key				auto_increment,
	table_number	int				not null,
    quantity		int				not null,
    order_time		datetime						default	 		now(),
    payment_yn 		varchar(20)		not null		default			'N',
    menu_id			int,
    CONSTRAINT orders_fk_menu FOREIGN KEY (menu_id)
    REFERENCES menu(menu_id)
);
  
-- 카테고리 등록

insert into category (category_name, emoji)values ('김밥', '🇰🇷🍙');
insert into category (category_name, emoji)values ('분식', '🍚');
insert into category (category_name, emoji)values ('라면', '🍜');
insert into category (category_name, emoji)values ('계절메뉴', '🍜❄️' );
insert into category (category_name, emoji)values ('세트메뉴', '🍱🐷');
insert into category (category_name, emoji)values ('음료수', '🥤' );

-- 날짜
insert into orders values(null,1,1,'2025-06-11 12:02:25','N',1);
insert into orders values(null,1,1,'2025-06-11 12:07:24','N',1);
insert into orders values(null,1,2,'2025-06-11 12:07:37','N',1);
insert into orders values(null,1,2,'2025-06-10 15:47:34','N',2);
insert into orders values(null,2,2,'2025-06-10 15:47:34','N',2);
insert into orders values(null,2,2,'2025-06-09 15:47:34','N',2);
insert into orders values(null,3,2,'2025-06-09 15:47:34','N',3);
insert into orders values(null,3,1,'2025-06-08 15:47:34','N',3);
insert into orders values(null,5,1,'2025-06-08 15:47:34','N',3);
insert into orders values(null,5,1,'2025-06-07 15:47:34','N',3);

-- 수정
update category
set category_name='김밥', emoji='🇰🇷🍙'
where category_id = 1;
-- ----------------------------------------------------------------------------------------

update category
set category_desc = '김밥류'
where category_id = 1;

update category
set category_desc = '분식류'
where category_id = 2;

update category
set category_desc = '라면류'
where category_id = 3;

update category
set category_desc = '계절메뉴류'
where category_id = 4;

update category
set category_desc = '세트메뉴류'
where category_id = 5;

update category
set category_desc = '음료류'
where category_id = 6;

-- 삭제
delete from category
where category_id = 4;

-- 메뉴추가
alter table category auto_increment = 4;

select * from category;

select	category_id as '카테고리번호',
		category_name as '카테고리명',
        category_desc as '카테고리설명',
        emoji as '이모티콘',
        reg_date as '등록날짜'
from category;

select menu_id as '메뉴번호',
		menu_name as '메뉴명',
		menu_price as '가격',
		category_id as '카테고리번호'
from menu
;

select order_id as '주문번호',
		table_number as '테이블번호',
        quantity as '주문수량',
        order_time as '주문시간',
        payment_yn as '결제여부',
        menu_id as '메뉴번호'	
from orders
;

-- 계절메뉴
select    ifnull(m.menu_id, ' ') as 'menu_id' 
                  ,ifnull(m.menu_name, ' ') as 'menu_name'
                  ,ifnull(m.menu_price, ' ') as 'menu_price' 
                  ,c.category_name 
                  ,c.category_id 
         from menu m 
            right outer join category c 
         on m.category_id = c.category_id; 
insert into category

values(null, '계절메뉴', '하절기 동절기', '★', now());

insert into category
values(null, '찌게류', '뜨거워용', '☎', now());

-- 일별매출
select date_format(o.order_time, '%Y-%m-%d %H:00:00') as order_time, 
      count(*) as order_count, 
      sum(m.menu_price*o.quantity) as sales 
from orders o, menu m
where o.menu_id = m.menu_id
and date(o.order_time) = '2025-06-11'
group by date_format(o.order_time, '%Y-%m-%d %H:00:00')
order by order_time asc
;
 
-- 카테고리별 매출
select c.category_name, 
sum(o.quantity) as quantity, 
sum(o.quantity * m.menu_price) as sales
from orders o                  
join menu m 
on o.menu_id = m.menu_id 
join category c 
on m.category_id = c.category_id                       
group by c.category_name 
order by sales desc;                   
                         


-- 전체 매출 및 판매수량 합계 조회
SELECT SUM(o.quantity) AS total_quantity, 
SUM(o.quantity * m.menu_price) AS total_sales 
FROM orders o
JOIN menu m 
ON o.menu_id = m.menu_id
;