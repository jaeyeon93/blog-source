create table cancel_request
(
    id                                  bigint unsigned auto_increment primary key comment 'PK',
    transaction_id                      varchar(24) not null comment '거래 ID',
    mert_id                             varchar(24) not null comment '상점 ID',
    partial_cancel_transaction_sequence int null comment '부분취소 키',
    reg_ts                              timestamp default current_timestamp comment '등록일시'
)

create table blog_test(
                          id bigint unsigned auto_increment primary key comment 'PK',
                          test_string varchar(24),
                          test_boolean tinyint(1),
                          test_int int,
                          test_long bigint,
                          test_big_decimal decimal(10, 6),
                          test_double double,
                          test_float float,
                          test_byte tinyint,
                          test_short smallint,
                          reg_ts timestamp default current_timestamp comment '등록일시'
)
;