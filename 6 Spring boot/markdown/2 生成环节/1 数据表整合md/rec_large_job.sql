/*
Navicat PGSQL Data Transfer

Source Server         : localhost_career
Source Server Version : 90601
Source Host           : localhost:5432
Source Database       : dlut
Source Schema         : public

Target Server Type    : PGSQL
Target Server Version : 90601
File Encoding         : 65001

Date: 2017-03-24 10:36:51
*/


-- ----------------------------
-- Table structure for rec_large_job
-- ----------------------------
DROP TABLE IF EXISTS "public"."rec_large_job";
CREATE TABLE "public"."rec_large_job" (
"name" varchar(255) COLLATE "default" NOT NULL,
"position_id" varchar(20) COLLATE "default" NOT NULL,
"id" varchar(255) COLLATE "default"
)
WITH (OIDS=FALSE)

;

-- ----------------------------
-- Alter Sequences Owned By 
-- ----------------------------

-- ----------------------------
-- Primary Key structure for table rec_large_job
-- ----------------------------
ALTER TABLE "public"."rec_large_job" ADD PRIMARY KEY ("name");

-- ----------------------------
-- Foreign Key structure for table "public"."rec_large_job"
-- ----------------------------
ALTER TABLE "public"."rec_large_job" ADD FOREIGN KEY ("id") REFERENCES "public"."rec_large_order" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;
