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

Date: 2017-03-24 10:33:08
*/


-- ----------------------------
-- Table structure for rec_large_person
-- ----------------------------
DROP TABLE IF EXISTS "public"."rec_large_person";
CREATE TABLE "public"."rec_large_person" (
"id" varchar(255) COLLATE "default" NOT NULL,
"duties" varchar(20) COLLATE "default",
"name" varchar(20) COLLATE "default",
"order_id" uuid NOT NULL,
"phone" varchar(20) COLLATE "default",
"room_number" varchar(20) COLLATE "default"
)
WITH (OIDS=FALSE)

;

-- ----------------------------
-- Alter Sequences Owned By 
-- ----------------------------

-- ----------------------------
-- Primary Key structure for table rec_large_person
-- ----------------------------
ALTER TABLE "public"."rec_large_person" ADD PRIMARY KEY ("id");

-- ----------------------------
-- Foreign Key structure for table "public"."rec_large_person"
-- ----------------------------
ALTER TABLE "public"."rec_large_person" ADD FOREIGN KEY ("id") REFERENCES "public"."rec_large_order" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;
