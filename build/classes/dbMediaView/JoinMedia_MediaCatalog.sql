/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  17jz0101 Lee Ho Jae
 * Created: 2018/11/28
 */

SELECT ID, KIND, CATEGORY, TITLE
FROM MEDIA JOIN MEDIA_CATALOG
ON MEDIA.CATALOG_ID = MEDIA_CATALOG.CATALOG_ID;

/*
SELECT ID, KING, CATEGORY, TITLE
FROM MEDIA JOIN MEDIA_CATALOG
WHERE MEDIA.CATALOG_ID = MEDIA_CATALOG.CATALOG_ID;
*/
