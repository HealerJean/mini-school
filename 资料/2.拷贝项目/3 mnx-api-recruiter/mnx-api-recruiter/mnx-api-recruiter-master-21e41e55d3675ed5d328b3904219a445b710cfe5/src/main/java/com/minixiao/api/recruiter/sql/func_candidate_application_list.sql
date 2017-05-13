CREATE OR REPLACE FUNCTION func_candidate_application_list (recId UUID,positions character varying,degree character varying,school character varying ,major character varying ,stageId uuid,status character varying,labels character varying,gender character varying,department character varying,rank character varying,englishLevel character varying,reward character varying,attachment character varying,name character varying,mobile character varying,city character varying,birthFrom character varying,birthTo character varying,graduateFrom character varying,graduateTo character varying,deliveryFrom character varying,deliveryTo character varying,number integer,size integer,sort character varying)
 RETURNS setof record
AS $$
DECLARE
--f_xx 为处理之后用于查询的参数，array_xx 为该条件分割后数组，t_xx 为临时变量
rec record;
f_recId text;
f_dept text;
f_positions text;
array_degree text[];
f_degree text;
f_school text:= ' and (edu.school =''erro'' ';
t_school text;
array_school text[];
array_major text[];
f_major text:= ' and (edu.major =''erro'' ';
t_major text;
f_stage_id text;
f_status text;
f_labels text:= ' and (t.tag =''erro'' ';
t_labels text;
array_labels text[];
f_gender text;
f_department text:= ' and (job.department =''erro'' ';
t_department text;
array_department text[];
f_englishLevel text:= ' and (lan.level =''erro'' ';
t_englishLevel text;
array_englishLevel text[];
f_rank text;
f_reward text;
f_attachment text;
f_name text;
f_mobile text;
t_city text;
array_city text[];
f_city text :=' and (basic.city =''erro'' ';
f_birth_from text;
f_birth_to text;
f_graduate_from text;
f_graduate_to text;
f_number text;
f_size text;
f_delivery_from text;
f_delivery_to text;
f_sort text :=' order by ';
t_sort text;
array_sort text[];
BEGIN
--企业id

f_recId :=' a.rec_id = '''||recId||'''';


--职位名称
IF ''=positions THEN
        f_positions :='';
ELSE
f_positions := ' and job.job_name = '''||positions||'''';
End IF;

--学历
IF ''=degree THEN
        f_degree :='';
ELSE
f_degree := ' and edu.degree = '''||degree||'''';
End IF;

--学校
IF ''=school THEN
        f_school :='';
ELSE
array_school := string_to_array(school,',','');--已英文逗号分隔成字符串进行拼接
foreach t_school in array array_school
LOOP
f_school := f_school || ' or (edu.is_highest = true and edu.school = '''||t_school||''') ';
END LOOP ;
f_school :=f_school||') ';
End IF;

--专业
IF ''=major THEN
        f_major :='';
ELSE
array_major := string_to_array(major,',','');--已英文逗号分隔成字符串进行拼接
foreach t_major in array array_major
LOOP
f_major := f_major || ' or edu.major = '''||t_major||'''';
END LOOP ;
f_major :=f_major||') ';
End IF;

--阶段
f_stage_id :=' and a.stage_id = '''||stageId||'''';

--状态
IF ''=status THEN
        f_status :='';
ELSE
f_status :=' and a.status like '''||status||'''';
End IF;

--标签
IF ''=labels THEN
        f_labels :='';
ELSE
array_labels := string_to_array(labels,',','');--已英文逗号分隔成字符串进行拼接
foreach t_labels in array array_labels
LOOP
f_labels := f_labels || ' or t.tag = '''||t_labels||'''';
END LOOP ;
f_labels :=f_labels||') ';
End IF;

--性别
IF ''=gender THEN
        f_gender :='';
ELSE
f_gender :=' and basic.gender = '''||gender||''' ';
End IF;

--部门
IF ''=department THEN
        f_department :='';
ELSE
array_department := string_to_array(department,',','');--已英文逗号分隔成字符串进行拼接
foreach t_department in array array_department
LOOP
f_department := f_department || ' or job.department = '''||t_department||'''';
END LOOP ;
f_department :=f_department||') ';
End IF;

--年级排名
IF ''=rank THEN
        f_rank :='';
ELSE
f_rank := ' and (edu.is_highest = true and (edu.rank <  '''||rank||''''|| 'or edu.rank ='''||rank||''')) ';
END IF;
--英语等级
IF ''= englishLevel THEN
        f_englishLevel :='';
ELSE
array_englishLevel := string_to_array(englishLevel,',','');--已英文逗号分隔成字符串进行拼接
foreach t_englishLevel in array array_englishLevel
LOOP
f_englishLevel := f_englishLevel || ' or lan.level = '''||t_englishLevel||'''';
END LOOP ;
f_englishLevel := f_englishLevel||') ';
End IF;

--是否有获奖
IF ''=reward THEN
        f_reward :=' ';
    ELSIF 'Y'=reward THEN
        f_reward =' and a.reward notnull ';
    ELSE
        f_reward =' and a.reward isnull ';
END IF;

--是否有附件简历
IF ''=attachment THEN
        f_attachment :=' ';
    ELSIF 'Y'=attachment THEN
        f_attachment =' and a.resume notnull ';
    ELSE
        f_attachment =' and a.resume isnull ';
    END IF;

--姓名
IF ''=name THEN
        f_name :='';
ELSE
f_name :=' and basic.name like '''||name||'%''';
End IF;

--手机
IF ''=mobile THEN
        f_mobile :='';
ELSE
f_mobile :=' and basic.mobile = '''||mobile||'''';
End IF;

--城市
IF ''=city THEN
        f_city :='';
ELSE
array_city := string_to_array(city,',','');--已英文逗号分隔成字符串进行拼接
foreach t_city in array array_city
LOOP
f_city := f_city || ' or basic.city = '''||t_city||'''';
END LOOP ;
f_city := f_city||') ';
End IF;

--出生日期
IF ''=birthTo THEN
        f_birth_to :='';
ELSE
f_birth_to :=' and basic.birthday <  '''||birthTo||'''';
End IF;

IF ''=birthFrom THEN
        f_birth_from :='';
ELSE
f_birth_from :=' and basic.birthday  >'''||birthFrom||'''';
End IF;

--毕业日期
IF ''=graduateFrom THEN
        f_graduate_from :='';
ELSE
f_graduate_from :=' and (edu.is_highest = true and edu.end_date >  '''||graduateFrom||''') ';
End IF;

IF ''=graduateTo  THEN
        f_graduate_to :='';
ELSE
f_graduate_to :=' and (edu.is_highest = true and edu.end_date <  '''||graduateTo||''') ';
End IF;

--发送日期
IF ''=deliveryTo THEN
        f_delivery_to :='';
ELSE
f_delivery_to :=' and a.apply_date< '''||to_timestamp(deliveryTo, 'yy-MM-DD')||'''';
End IF;

IF ''=deliveryFrom THEN
        f_delivery_from :='';
ELSE
f_delivery_from :=' and a.apply_date> '''||to_timestamp(deliveryFrom, 'yy-MM-DD')||'''';
End IF;

--排序参数  ORDER BY Company DESC, OrderNumber ASC
IF ''=sort THEN
        f_sort :='order by apply_date desc';
ELSE
array_sort := string_to_array(sort,',','');--已英文逗号分隔成字符串进行拼接
foreach t_sort in array array_sort
LOOP
f_sort :=f_sort||t_sort||' ';
END LOOP ;
f_sort :=f_sort||',apply_date desc';
End IF;


--执行SQLZ语句
for rec in EXECUTE 'SELECT a.id,a.rec_id,a.rec_name,a.basic,a.education,a.job_target,a.language,a.meta_data,a.practices,a.resume,a.stage,a.club,a.skills,a.reward,a.certificate,a.stage_id,a.status,a.stu_id,a.update_time,a.work,a.apply_date from tb_candidate_application a
 LEFT JOIN tb_candidate_tags t ON a.id =t.application_id,
 jsonb_to_recordset(job_target) as job(id uuid,job_name varchar,job_category varchar,is_current BOOLEAN,current_status varchar,area varchar,department varchar,score DOUBLE PRECISION),
 jsonb_to_recordset(language) as lan(type varchar,level varchar),
 jsonb_to_recordset(education) as edu(rank varchar,major varchar,degree varchar,school varchar,is_highest BOOLEAN,end_date varchar),
 jsonb_to_record(basic) as basic(gender varchar,city varchar,name varchar,email varchar,mobile varchar,birthday varchar ,province varchar,political varchar)
 where '||f_recId||f_stage_id||f_name||f_positions||f_degree||f_school||f_major||f_status||f_labels||f_gender||f_department||f_englishLevel||f_rank||f_reward||f_attachment||f_mobile||f_city||f_birth_from||f_birth_to||f_graduate_from||f_graduate_to||f_delivery_from||f_delivery_to||f_sort||' limit '||size||' offset '||number||';' loop
return next rec;

end loop;

return;
END $$
LANGUAGE 'plpgsql';
