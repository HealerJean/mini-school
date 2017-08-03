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

Date: 2017-03-24 10:16:33
*/


-- ----------------------------
-- Table structure for rec_yard_order
-- ----------------------------
DROP TABLE IF EXISTS "public"."rec_yard_order";
CREATE TABLE "public"."rec_yard_order" (
"id" varchar(255) COLLATE "default" NOT NULL,
"audit_person" varchar(32) COLLATE "default",
"audit_state" varchar(10) COLLATE "default",
"audit_time" timestamp(6),
"enterprise_id" uuid,
"order_submission_time" timestamp(6),
"recep_person" varchar(20) COLLATE "default",
"side_use" varchar(300) COLLATE "default",
"use_end_time" timestamp(6),
"use_start_time" timestamp(6),
"yard_address" varchar(100) COLLATE "default",
"yard_quantity" int4 NOT NULL,
"yard_requirements" varchar(100) COLLATE "default",
"yards_scale" varchar(50) COLLATE "default"
)
WITH (OIDS=FALSE)

;

-- ----------------------------
-- Alter Sequences Owned By 
-- ----------------------------

-- ----------------------------
-- Primary Key structure for table rec_yard_order
-- ----------------------------
ALTER TABLE "public"."rec_yard_order" ADD PRIMARY KEY ("id");
