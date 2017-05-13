SELECT uuid_generate_v4() as uuid_accid, t_account.*  INTO a_t_account from t_account;

ALTER TABLE "public"."a_t_account" ADD PRIMARY KEY ("acc_id");

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


INSERT INTO "public"."tb_student"(id,created_on,email,mobile,pwd,real_name,source,updated_on) SELECT a_t_account.uuid_accid,create_date,email
,mobile,pwd,real_name,source,modify_date from a_t_account; 


Insert into a_t_activity(act_id,label,audit_type,resume_complete,threshold,modify_date,create_date,modify_id,create_id,status,organizer,address,end_time,start_time,limit_num,summary,publish_time,publish_type,type,name) select act_id,label,audit_type,resume_complete,threshold,modify_date,create_date,modify_id,create_id,status,organizer,address,end_time,start_time,limit_num,summary,publish_time,publish_type,type,name from t_activity;






SELECT uuid_generate_v4() as uuid_resumeid, t_account_resume.*  INTO a_t_account_resume from t_account_resume;

ALTER TABLE "public"."a_t_account_resume" ADD PRIMARY KEY ("resume_id");



UPDATE a_t_account_resume
SET uuid_resumeid = a_t_account.uuid_accid
FROM
a_t_account
WHERE
	a_t_account_resume.acc_id = a_t_account.acc_id

	

DROP TABLE IF EXISTS "public"."tb_stu_education_info";
CREATE TABLE "public"."tb_stu_education_info" (
"id" uuid NOT NULL,
"uid" uuid,
"degree" varchar(255),
"school" varchar(255),
"major" varchar(255),
"is_current_school" varchar(255),
"rank" varchar(255),
"start_date" varchar(255),
"end_date" varchar(255),
"metadata" jsonb,
"created_on" timestamp(6),
"updated_on" timestamp(6)
)
WITH (OIDS=FALSE)
;
ALTER TABLE "public"."tb_stu_education_info" ADD PRIMARY KEY ("id");




INSERT INTO "public"."tb_stu_education_info"
(id,uid,degree,school,major,is_current_school,rank,start_date,end_date,created_on,updated_on)
 SELECT DISTINCT
uuid_generate_v4(),
t1.uuid_resumeid,
t1."data" ->> 'degree' as "degree",
t1."data" ->> 'school' as "school",
t1."data" ->> 'major' as "major",
t1."data" ->> 'isHighest' as "is_current_school",
t1."data" ->> 'rank' as "rank",
t1."data" ->> 'startDate' as "start_date",
t1."data" ->> 'endDate' as "end_date",
t1.create_date as "created_on",
--t1."data" ->> 'serialNum' as "serialNum",
t1.modify_date as "updated_on"
FROM
a_t_account_resume t1
WHERE
t1.type = '2001'; 




DROP TABLE IF EXISTS "public"."tb_stu_basic_info";
CREATE TABLE "public"."tb_stu_basic_info" (
"id" uuid NOT NULL,
"birthday" varchar(15) COLLATE "default",
"city" varchar(15) COLLATE "default",
"created_on" timestamp(6),
"email" varchar(50) COLLATE "default",
"gender" varchar(4) COLLATE "default",
"metadata" varchar(15) COLLATE "default",
"mobile" varchar(20) COLLATE "default",
"political" varchar(15) COLLATE "default",
"province" varchar(15) COLLATE "default",
"realname" varchar(50) COLLATE "default",
"updated_on" timestamp(6)
)
WITH (OIDS=FALSE)

;



ALTER TABLE "public"."tb_stu_basic_info" ADD PRIMARY KEY ("id");

--创建tb_stu_other_info表
DROP TABLE IF EXISTS "public"."tb_stu_other_info";
CREATE TABLE "public"."tb_stu_other_info" (
"id" uuid NOT NULL,
"uid" uuid NOT NULL,
"type" VARCHAR(10),
"data" jsonb,
"label" VARCHAR(100),
"sortno" VARCHAR(10),
"created_on" Date,
"updated_on" Date
)
WITH (OIDS=FALSE)
;
--主键
ALTER TABLE "public"."tb_stu_other_info" ADD PRIMARY KEY ("id");
--外键
alter table tb_stu_other_info add constraint FK_other foreign key (uid) references tb_stu_basic_info(uid);

--插入信息
insert into "public"."tb_stu_other_info"
(id,uid,type,data,created_on,updated_on)
select distinct
uuid_generate_v4(),
t.uuid_accid,
t.type as "type",
t.data as "data",
t.create_date as "created_on",
--t1."data" ->> 'serialNum' as "serialNum",
t.modify_date as "updated_on"
from
tn_account_resume t
where 
t.type <> '2001' and t.type<> '1001';





UPDATE tb_stu_education_info SET is_current_school = 't' where is_current_school = 'ture';
UPDATE tb_stu_education_info SET is_current_school = 'f' where is_current_school = 'false';
UPDATE tb_stu_education_info SET is_current_school = 't' where is_current_school = '1';
UPDATE tb_stu_education_info SET is_current_school = 'f' where is_current_school = '0';
UPDATE tb_stu_education_info SET degree = '7' where degree = '未说明';


ALTER TABLE "public"."tb_stu_education_info"
ALTER COLUMN "is_current_school" TYPE bool USING is_current_school::BOOLEAN;
ALTER TABLE "public"."tb_stu_education_info"
ALTER COLUMN "degree" TYPE int USING is_current_school::INTEGER;

UPDATE tb_stu_education_info SET "rank" = '1' where "rank" = '前5%';
UPDATE tb_stu_education_info SET "rank" = '2' where "rank" = '前10%';
UPDATE tb_stu_education_info SET "rank" = '3' where "rank" = '前20%';
UPDATE tb_stu_education_info SET "rank" = '4' where "rank" = '前50%';
UPDATE tb_stu_education_info SET "rank" = '1' where "rank" = '前 5%';
UPDATE tb_stu_education_info SET "rank" = '2' where "rank" = '前 10%';
UPDATE tb_stu_education_info SET "rank" = '3' where "rank" = '前 20%';
UPDATE tb_stu_education_info SET "rank" = '4' where "rank" = '前 50%';
UPDATE tb_stu_education_info SET "rank" = '3' where "rank" = '前 30%';



UPDATE tb_stu_education_info SET "rank" = '5' where "rank" NOT in ('1','2','3','4');
UPDATE tb_stu_education_info SET "rank" = '5' where "rank" is NULL;

ALTER TABLE "public"."tb_stu_education_info"
ALTER COLUMN "is_current_school" TYPE bool USING is_current_school::BOOLEAN;
ALTER TABLE "public"."tb_stu_education_info"
ALTER COLUMN "degree" TYPE int USING degree::INTEGER;
ALTER TABLE "public"."tb_stu_education_info"
ALTER COLUMN "rank" TYPE int USING rank::INTEGER;



