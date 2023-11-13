-- auto Generated on 2020-02-18
-- DROP TABLE IF EXISTS sfseal_user.user_address;
CREATE TABLE sfseal_user.user_address
(
    address_id       bigint PRIMARY KEY       NOT NULL,
    name             varchar(100),
    address          varchar(200),
    lng_lat          point,
    city             varchar(100),
    create_date      timestamp with time zone NOT NULL,
    last_update_date timestamp with time zone NOT NULL,
    last_update_user varchar(200)             NOT NULL
);
CREATE INDEX user_address_name ON sfseal_user.user_address (name);
CREATE INDEX user_address_address ON sfseal_user.user_address (address);
CREATE INDEX user_address_city ON sfseal_user.user_address (city);

COMMENT ON COLUMN sfseal_user.user_address.address_id IS '地址ID';
COMMENT ON COLUMN sfseal_user.user_address.name IS '位置名称';
COMMENT ON COLUMN sfseal_user.user_address.address IS '位置地址';
comment on column sfseal_user.user_address.lng_lat is '经纬度（用于地图精准定位）';
COMMENT ON COLUMN sfseal_user.user_address.city IS '位置所在城市';
COMMENT ON COLUMN sfseal_user.user_address.create_date IS '创建时间';
COMMENT ON COLUMN sfseal_user.user_address.last_update_date IS '上次更新时间';
COMMENT ON COLUMN sfseal_user.user_address.last_update_user IS '上次更新人';

CREATE TABLE sfseal_user.user_address_history
(
    history_id  BIGSERIAL PRIMARY KEY,
    action_type CHAR(1) NOT NULL,
    LIKE sfseal_user.user_address
);