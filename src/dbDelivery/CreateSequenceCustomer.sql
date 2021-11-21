/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  17jz0101 Lee Ho Jae
 * Created: 2019/01/17
 */

CREATE SEQUENCE S_CUSTOMER
    START WITH 1001
    INCREMENT BY 1
    MAXVALUE 9999
    MINVALUE 1001
    NOCYCLE
;

CREATE SEQUENCE S_ORDERS
    START WITH 10001
    INCREMENT BY 1
    MAXVALUE 99999
    MINVALUE 10001
    NOCYCLE
;

CREATE SEQUENCE S_ORDER_DETAIL
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 999999
    MINVALUE 1
    NOCYCLE
;

CREATE SEQUENCE S_ORDER_SLIP
    START WITH 100001
    INCREMENT BY 1
    MAXVALUE 999999
    MINVALUE 100001
    NOCYCLE
;