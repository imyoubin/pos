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

select 	menu_id as '메뉴번호',
		menu_name as '메뉴명',
        menu_price as '가격',
        category_id as '카테고리번호'
from manu
;

-- 카테고리별 매출
select *
from orders o
join menu m
on o.menu_id=m.menu_id
join category_id c
on c.category_id=c.category_name=c,emoji
group by c.category_id, c.category_name, c.emoji
order by total_sales DESC;
;

select 	order_id as '주문번호',
		table_number as '테이블번호',
        quantity as '주문수량',
        order_time as '주문시간',
        payment_yn as '결제여부',
        menu_id as '메뉴번호'	
from orders
;
