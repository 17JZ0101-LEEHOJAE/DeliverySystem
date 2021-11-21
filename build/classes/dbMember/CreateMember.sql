/**
 * 会員テーブル作成
 * Author:  yoshinobu
 * Created: 2018/10/02
 */
CREATE TABLE MEMBER (
    ID          INTEGER NOt NULL,
    NAME        VARCHAR(20),
    ENTRY_DATE  DATE
);
ALTER TABLE MEMBER ADD CONSTRAINT PK_MEMBER PRIMARY KEY (ID);

