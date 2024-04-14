create sequence hibernate_sequence start 1 increment 1;
create sequence crypto_exchange_seq start 1 increment 1;

create table if not exists crypto_exchange (
   id int8,
   name varchar(255),
   url varchar(512),
   primary key (id)
);

insert into crypto_exchange(id, name, url)
values (1, 'binance', 'https://www.binance.com/bapi/composite/v1/public/marketing/symbol/list'),
       (2, 'kucoin', 'https://m.kucoin.com/_api/trade-front/market/getSymbol/USDS?lang=en_US');