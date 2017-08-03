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

Date: 2017-03-24 10:40:50
*/


-- ----------------------------
-- Table structure for rec_large_sign
-- ----------------------------
DROP TABLE IF EXISTS "public"."rec_large_sign";
CREATE TABLE "public"."rec_large_sign" (
"id" varchar(255) COLLATE "default" NOT NULL,
"position_id" uuid NOT NULL,
"sign_id" uuid,
"sign_name" varchar(20) COLLATE "default",
"sign_time" timestamp(6),
"sign_type" varchar(20) COLLATE "default"
)
WITH (OIDS=FALSE)

;

-- ----------------------------
-- Alter Sequences Owned By 
-- ----------------------------

-- ----------------------------
-- Primary Key structure for table rec_large_sign
-- ----------------------------
ALTER TABLE "public"."rec_large_sign" ADD PRIMARY KEY ("id");

-- ----------------------------
-- Foreign Key structure for table "public"."rec_large_sign"
-- ----------------------------
ALTER TABLE "public"."rec_large_sign" ADD FOREIGN KEY ("id") REFERENCES "public"."rec_large_order" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;
