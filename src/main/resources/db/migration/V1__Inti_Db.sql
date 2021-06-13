create sequence hibernate_sequence start 1 increment 1;

create table crypto_coin (
        id int8,
        amount float8,
        logo varchar(255),
        name varchar(255),
        price float8,
        crypto_exchange_id int8,
        primary key (id)
);

create table crypto_exchange (
        id int8,
        name varchar(255),
        url varchar(512),
        primary key (id)
);

alter table if exists crypto_coin
    add constraint crypto_coin_crypto_exchange_fk
        foreign key (crypto_exchange_id) references crypto_exchange;

insert into crypto_exchange(id, name, url)
values (1, 'binance', 'https://www.binance.com/bapi/composite/v1/public/marketing/symbol/list'),
       (2, 'kraken', 'https://www.kraken.com/api/internal/cryptowatch/markets/assets?asset=USD&limit=100&assetName=new'),
       (3, 'kucoin', 'https://m.kucoin.com/_api/currency/prices?base=USD&targets=&lang=en_US');
