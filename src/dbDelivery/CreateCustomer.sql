/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  17jz0101 Lee Ho Jae
 * Created: 2018/11/28
 */

CREATE TABLE CUSTOMER (
    CUSTOMER_ID     NUMBER(4)           NOT NULL,
    NAME            VARCHAR2(20)        NOT NULL,
    TEL             VARCHAR2(12)        NOT NULL,
    ADDRESS         VARCHAR2(30)        NOT NULL,
    POINT           NUMBER(3) DEFAULT 0 NOT NULL   
);
ALTER TABLE CUSTOMER ADD CONSTRAINT PK_CUSTOMER PRIMARY KEY (CUSTOMER_ID);

CREATE TABLE ORDERS (
    ORDER_ID        NUMBER(5)           NOT NULL,
    CUSTOMER_ID     NUMBER(4)           NOT NULL,
    ORDERDATE       DATE                NOT NULL,
    TOTALPRICE      NUMBER(6) DEFAULT 0 NOT NULL,
    DELIVERYDATE    DATE                NOT NULL,
    USEDPOINT       NUMBER(3) DEFAULT 0 NOT NULL
);
ALTER TABLE ORDERS ADD CONSTRAINT PK_ORDERS PRIMARY KEY (ORDER_ID);

ALTER TABLE ORDERS ADD CONSTRAINT FK_ORDERS_0 FOREIGN KEY (CUSTOMER_ID) REFERENCES CUSTOMER (CUSTOMER_ID);

CREATE TABLE ITEM (
    ITEM_ID     VARCHAR2(3)     NOT NULL,
    ITEM_NAME   VARCHAR2(30)    NOT NULL,
    ITEM_PRICE  NUMBER(4)
);
ALTER TABLE ITEM ADD CONSTRAINT PK_ITEM PRIMARY KEY (ITEM_ID);

CREATE TABLE ORDERDETAIL (
    ORDERDETAIL_ID  NUMBER(6)            NOT NULL,
    ITEM_ID         VARCHAR2(3)          NOT NULL,
    ORDER_ID        NUMBER(5)            NOT NULL,
    ORDERCOUNT      NUMBER(3) DEFAULT 0,   
    ORDERPRICE      NUMBER(6) DEFAULT 0  
);
ALTER TABLE ORDERDETAIL ADD CONSTRAINT PK_ORDERDETAIL PRIMARY KEY (ORDERDETAIL_ID);

ALTER TABLE ORDERDETAIL ADD CONSTRAINT FK_ORDERDETAIL_1 FOREIGN KEY (ORDER_ID) REFERENCES ORDERS (ORDER_ID);
ALTER TABLE ORDERDETAIL ADD CONSTRAINT FK_ORDERDETAIL_0 FOREIGN KEY (ITEM_ID) REFERENCES ITEM (ITEM_ID);

CREATE TABLE ORDERSLIP (
    ORDER_ID        NUMBER(5)       NOT NULL,
    ORDERSLIP_ID    NUMBER(6)       NOT NULL,
    PAID            NUMBER(1)       NOT NULL
);
ALTER TABLE ORDERSLIP ADD CONSTRAINT PK_ORDERSLIP PRIMARY KEY (ORDER_ID);

ALTER TABLE ORDERSLIP ADD CONSTRAINT FK_ORDERSLIP FOREIGN KEY (ORDER_ID) REFERENCES ORDERS (ORDER_ID);