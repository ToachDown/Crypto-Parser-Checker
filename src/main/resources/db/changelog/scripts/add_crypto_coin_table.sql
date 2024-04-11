create sequence crypto_coin_seq start 1 increment 1;

create table if not exists crypto_coin (
   id int8,
   name varchar(255),
   buy_price float8,
   sell_price float8,
   total_supply int8,
   day_volume float8,
   market_cup float8,
   day_change float8,
   created date,
   crypto_exchange_id int8,
   primary key (id)
);

alter table if exists crypto_coin
    add constraint crypto_coin_crypto_exchange_fk
        foreign key (crypto_exchange_id) references crypto_exchange;