/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * 会員テーブル作成
 * Author:  17jz0101 Lee Ho Jae
 * Created: 2018/10/31
 */
DROP TABLE MEMBER;
CREATE TABLE MEMBER (
    ID          NUMBER(4) NOT NULL,
    NAME        VARCHAR2(10),
    ENTRY_DATE  DATE
);
ALTER TABLE MEMBER ADD CONSTRAINT PK_MEMBER PRIMARY KEY (ID);
