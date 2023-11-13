CREATE SCHEMA IF NOT EXISTS sfseal_reference;

-- reference表
CREATE TABLE sfseal_reference.reference_data
(
    id              BIGSERIAL PRIMARY KEY NOT NULL,
    parent_id       BIGINT NOT NULL,
    language        INTEGER NOT NULL,
    category        TEXT NOT NULL,
    key             TEXT NOT NULL,
    display_key     TEXT NOT NULL,
    display_order   INTEGER NOT NULL,
    description     TEXT NOT NULL,
    is_active      BOOLEAN DEFAULT TRUE NOT NULL,
    attributes      JSONB,
    last_update_date TIMESTAMP(3) WITHOUT TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT reference_unique_key UNIQUE (category, key)
);

COMMENT ON COLUMN sfseal_reference.reference_data.id IS 'ID, sequence自动生成';
COMMENT ON COLUMN sfseal_reference.reference_data.parent_id IS '父级ID';
COMMENT ON COLUMN sfseal_reference.reference_data.language IS '语言';
COMMENT ON COLUMN sfseal_reference.reference_data.category IS '类别';
COMMENT ON COLUMN sfseal_reference.reference_data.key IS '关键字';
COMMENT ON COLUMN sfseal_reference.reference_data.display_key IS '显示值';
COMMENT ON COLUMN sfseal_reference.reference_data.display_order IS '显示顺序';
COMMENT ON COLUMN sfseal_reference.reference_data.description IS '描述';
COMMENT ON COLUMN sfseal_reference.reference_data.is_active IS '是否有效';
COMMENT ON COLUMN sfseal_reference.reference_data.attributes IS '其它数据';
COMMENT ON COLUMN sfseal_reference.reference_data.last_update_date IS '最后修改时间';

-- reference历史表
CREATE TABLE sfseal_reference.reference_data_history
(
    history_id          BIGSERIAL NOT NULL,
    action_type         CHAR(1)   NOT NULL,
    LIKE sfseal_reference.reference_data
);

INSERT INTO sfseal_reference.reference_data(id, parent_id, "language", category, "key", display_key, display_order, description, attributes)
VALUES (10001, 0, 1, 'LOCK_STATUS', 'STOCK', '库存', 0, '库存', null ),
       (10002, 0, 1, 'LOCK_STATUS', 'DISTRIBUTE', '已分发', 0, '已分发', null ),
       (10003, 0, 1, 'LOCK_STATUS', 'UNUSED', '未使用', 0, '未使用', null ),
       (10004, 0, 1, 'LOCK_STATUS', 'INUSE', '使用中', 0, '使用中', null ),
       (10005, 0, 1, 'LOCK_STATUS', 'USED', '已使用', 0, '已使用', null );