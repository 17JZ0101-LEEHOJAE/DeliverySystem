/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  17jz0101 Lee HO Jae
 * Created: 2018/11/28
 */

CREATE TABLE MEDIA (
    ID          CHAR(6) NOT NULL,
    CATALOG_ID  INTEGER NOT NULL,
    KIND        VARCHAR(10)
);
ALTER TABLE MEDIA ADD CONSTRAINT PK_MEDIA PRIMARY KEY (ID);

CREATE TABLE MEDIA_CATALOG (
    CATALOG_ID  INTEGER NOT NULL,
    CATEGORY    VARCHAR(10),
    TITLE       VARCHAR(30)
);
ALTER TABLE MEDIA_CATALOG ADD CONSTRAINT PK_MEDIA_CATALOG PRIMARY KEY (CATALOG_ID);

ALTER TABLE MEDIA ADD CONSTRAINT FK_MEDIA_0 FOREIGN KEY (CATALOG_ID) REFERENCES MEDIA_CATALOG (CATALOG_ID);
