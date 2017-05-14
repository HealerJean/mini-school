--ѧ�� student  ��

--1
--����һ���м���±��������һ���ֶ� ����ĺ������Ǳ�ʾ�ֶ�uuid
SELECT uuid_generate_v4() as uuid_accid, t_account.*  INTO tn_account from t_account;

--����±������Ϊacc_id
ALTER TABLE "public"."tn_account" ADD PRIMARY KEY ("acc_id");


--2
--С���Ѿ������ 
--����һ�ű�

DROP TABLE IF EXISTS "public"."tb_student";
CREATE TABLE "public"."tb_student" (
"id" uuid NOT NULL,
"created_on" timestamp(6),
"email" varchar(40) COLLATE "default",
"islocked" bool,
"mobile" varchar(20) COLLATE "default",
"pwd" varchar(100) COLLATE "default",
"real_name" varchar(20) COLLATE "default",
"source" varchar(20) COLLATE "default",
"updated_on" timestamp(6)
)
WITH (OIDS=FALSE)
;
ALTER TABLE "public"."tb_student" ADD PRIMARY KEY ("id");


INSERT INTO "public"."tb_student"(id,created_on,email,mobile,pwd,real_name,source,updated_on) SELECT tn_account.uuid_accid,create_date,email
,mobile,pwd,real_name,source,modify_date from tn_account; 



--3


INSERT INTO "public"."tb_stu_basic_info"(id,birthday,city,created_on,email,gender,mobile,political,province,realname,updated_on) 
SELECT DISTINCT
t1.uuid_accid,
t1."data" ->> 'birthday' as "birthday",
t1."data" ->> 'city' as "city",
t1.create_date as "created_on",
t1."data" ->> 'email' as "email",
t1."data" ->> 'sex' as "sex",
t1."data" ->> 'mobile' as "mobile",
t1."data" ->> 'political' as "political",
t1."data" ->> 'province' as "province",
t1."data" ->> 'name' as "name",
--t1."data" ->> 'serialNum' as "serialNum",
t1.modify_date as "updated_on"
FROM
tn_account_resume t1
WHERE
t1.type = '1001'; 



--4
--�Ķ��ĵط�





δ��ɵĹ���
http://git.minixiao.com/mnx-api/mnx-api-student/wikis/%E5%AD%A6%E7%94%9F%E4%B8%AA%E4%BA%BA%E8%B5%84%E6%96%99%E7%9B%B8%E5%85%B3%E6%95%B0%E6%8D%AE%E5%BA%93%E8%AE%BE%E8%AE%A1