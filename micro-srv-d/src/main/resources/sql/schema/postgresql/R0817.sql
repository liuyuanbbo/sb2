-- ---------------------------------------------------------------------------------------------------------------------
/*
 Navicat Premium Data Transfer

 Source Server         : postgre-localhost
 Source Server Type    : PostgreSQL
 Source Server Version : 150003 (150003)
 Source Host           : localhost:5432
 Source Catalog        : hrdb
 Source Schema         : public

 Target Server Type    : PostgreSQL
 Target Server Version : 150003 (150003)
 File Encoding         : 65001

 Date: 17/08/2024 10:52:12
*/


-- ----------------------------
-- Table structure for departments
-- ----------------------------
DROP TABLE IF EXISTS "public"."departments";
CREATE TABLE "public"."departments"
(
    "department_id"   int4                                       NOT NULL,
    "department_name" varchar(30) COLLATE "pg_catalog"."default" NOT NULL,
    "manager_id"      int4,
    "location_id"     int4
)
;

-- ----------------------------
-- Records of departments
-- ----------------------------
INSERT INTO "public"."departments"
VALUES (10, 'Administration', 200, 1700);
INSERT INTO "public"."departments"
VALUES (20, 'Marketing', 201, 1800);
INSERT INTO "public"."departments"
VALUES (30, 'Purchasing', 114, 1700);
INSERT INTO "public"."departments"
VALUES (40, 'Human Resources', 203, 2400);
INSERT INTO "public"."departments"
VALUES (50, 'Shipping', 121, 1500);
INSERT INTO "public"."departments"
VALUES (60, 'IT', 103, 1400);
INSERT INTO "public"."departments"
VALUES (70, 'Public Relations', 204, 2700);
INSERT INTO "public"."departments"
VALUES (80, 'Sales', 145, 2500);
INSERT INTO "public"."departments"
VALUES (90, 'Executive', 100, 1700);
INSERT INTO "public"."departments"
VALUES (100, 'Finance', 108, 1700);
INSERT INTO "public"."departments"
VALUES (110, 'Accounting', 205, 1700);
INSERT INTO "public"."departments"
VALUES (120, 'Treasury', NULL, 1700);
INSERT INTO "public"."departments"
VALUES (130, 'Corporate Tax', NULL, 1700);
INSERT INTO "public"."departments"
VALUES (140, 'Control And Credit', NULL, 1700);
INSERT INTO "public"."departments"
VALUES (150, 'Shareholder Services', NULL, 1700);
INSERT INTO "public"."departments"
VALUES (160, 'Benefits', NULL, 1700);
INSERT INTO "public"."departments"
VALUES (170, 'Manufacturing', NULL, 1700);
INSERT INTO "public"."departments"
VALUES (180, 'Construction', NULL, 1700);
INSERT INTO "public"."departments"
VALUES (190, 'Contracting', NULL, 1700);
INSERT INTO "public"."departments"
VALUES (200, 'Operations', NULL, 1700);
INSERT INTO "public"."departments"
VALUES (210, 'IT Support', NULL, 1700);
INSERT INTO "public"."departments"
VALUES (220, 'NOC', NULL, 1700);
INSERT INTO "public"."departments"
VALUES (230, 'IT Helpdesk', NULL, 1700);
INSERT INTO "public"."departments"
VALUES (240, 'Government Sales', NULL, 1700);
INSERT INTO "public"."departments"
VALUES (250, 'Retail Sales', NULL, 1700);
INSERT INTO "public"."departments"
VALUES (260, 'Recruiting', NULL, 1700);
INSERT INTO "public"."departments"
VALUES (270, 'Payroll', NULL, 1700);

-- ----------------------------
-- Indexes structure for table departments
-- ----------------------------
CREATE INDEX "dept_location_ix" ON "public"."departments" USING btree (
                                                                       "location_id" "pg_catalog"."int4_ops" ASC NULLS
                                                                       LAST
    );

-- ----------------------------
-- Primary Key structure for table departments
-- ----------------------------
ALTER TABLE "public"."departments"
    ADD CONSTRAINT "dept_id_pk" PRIMARY KEY ("department_id");

-- ----------------------------
-- Foreign Keys structure for table departments
-- ----------------------------
ALTER TABLE "public"."departments"
    ADD CONSTRAINT "dept_mgr_fk" FOREIGN KEY ("manager_id") REFERENCES "public"."employees" ("employee_id") ON DELETE NO ACTION ON UPDATE NO ACTION;



-- ----------------------------
-- Table structure for employees
-- ----------------------------
DROP TABLE IF EXISTS "public"."employees";
CREATE TABLE "public"."employees"
(
    "employee_id"    int4                                       NOT NULL,
    "first_name"     varchar(20) COLLATE "pg_catalog"."default",
    "last_name"      varchar(25) COLLATE "pg_catalog"."default" NOT NULL,
    "email"          varchar(25) COLLATE "pg_catalog"."default" NOT NULL,
    "phone_number"   varchar(20) COLLATE "pg_catalog"."default",
    "hire_date"      date                                       NOT NULL,
    "job_id"         varchar(10) COLLATE "pg_catalog"."default" NOT NULL,
    "salary"         numeric(8, 2),
    "commission_pct" numeric(2, 2),
    "manager_id"     int4,
    "department_id"  int4
)
;

-- ----------------------------
-- Records of employees
-- ----------------------------
INSERT INTO "public"."employees"
VALUES (100, 'Steven', 'King', 'SKING', '515.123.4567', '2003-06-17', 'AD_PRES', 24000.00, NULL, NULL, 90);
INSERT INTO "public"."employees"
VALUES (101, 'Neena', 'Kochhar', 'NKOCHHAR', '515.123.4568', '2005-09-21', 'AD_VP', 17000.00, NULL, 100, 90);
INSERT INTO "public"."employees"
VALUES (102, 'Lex', 'De Haan', 'LDEHAAN', '515.123.4569', '2001-01-13', 'AD_VP', 17000.00, NULL, 100, 90);
INSERT INTO "public"."employees"
VALUES (103, 'Alexander', 'Hunold', 'AHUNOLD', '590.423.4567', '2006-01-03', 'IT_PROG', 9000.00, NULL, 102, 60);
INSERT INTO "public"."employees"
VALUES (104, 'Bruce', 'Ernst', 'BERNST', '590.423.4568', '2007-05-21', 'IT_PROG', 6000.00, NULL, 103, 60);
INSERT INTO "public"."employees"
VALUES (105, 'David', 'Austin', 'DAUSTIN', '590.423.4569', '2005-06-25', 'IT_PROG', 4800.00, NULL, 103, 60);
INSERT INTO "public"."employees"
VALUES (106, 'Valli', 'Pataballa', 'VPATABAL', '590.423.4560', '2006-02-05', 'IT_PROG', 4800.00, NULL, 103, 60);
INSERT INTO "public"."employees"
VALUES (107, 'Diana', 'Lorentz', 'DLORENTZ', '590.423.5567', '2007-02-07', 'IT_PROG', 4200.00, NULL, 103, 60);
INSERT INTO "public"."employees"
VALUES (108, 'Nancy', 'Greenberg', 'NGREENBE', '515.124.4569', '2002-08-17', 'FI_MGR', 12008.00, NULL, 101, 100);
INSERT INTO "public"."employees"
VALUES (109, 'Daniel', 'Faviet', 'DFAVIET', '515.124.4169', '2002-08-16', 'FI_ACCOUNT', 9000.00, NULL, 108, 100);
INSERT INTO "public"."employees"
VALUES (110, 'John', 'Chen', 'JCHEN', '515.124.4269', '2005-09-28', 'FI_ACCOUNT', 8200.00, NULL, 108, 100);
INSERT INTO "public"."employees"
VALUES (111, 'Ismael', 'Sciarra', 'ISCIARRA', '515.124.4369', '2005-09-30', 'FI_ACCOUNT', 7700.00, NULL, 108, 100);
INSERT INTO "public"."employees"
VALUES (112, 'Jose Manuel', 'Urman', 'JMURMAN', '515.124.4469', '2006-03-07', 'FI_ACCOUNT', 7800.00, NULL, 108, 100);
INSERT INTO "public"."employees"
VALUES (113, 'Luis', 'Popp', 'LPOPP', '515.124.4567', '2007-12-07', 'FI_ACCOUNT', 6900.00, NULL, 108, 100);
INSERT INTO "public"."employees"
VALUES (114, 'Den', 'Raphaely', 'DRAPHEAL', '515.127.4561', '2002-12-07', 'PU_MAN', 11000.00, NULL, 100, 30);
INSERT INTO "public"."employees"
VALUES (115, 'Alexander', 'Khoo', 'AKHOO', '515.127.4562', '2003-05-18', 'PU_CLERK', 3100.00, NULL, 114, 30);
INSERT INTO "public"."employees"
VALUES (116, 'Shelli', 'Baida', 'SBAIDA', '515.127.4563', '2005-12-24', 'PU_CLERK', 2900.00, NULL, 114, 30);
INSERT INTO "public"."employees"
VALUES (117, 'Sigal', 'Tobias', 'STOBIAS', '515.127.4564', '2005-07-24', 'PU_CLERK', 2800.00, NULL, 114, 30);
INSERT INTO "public"."employees"
VALUES (118, 'Guy', 'Himuro', 'GHIMURO', '515.127.4565', '2006-11-15', 'PU_CLERK', 2600.00, NULL, 114, 30);
INSERT INTO "public"."employees"
VALUES (119, 'Karen', 'Colmenares', 'KCOLMENA', '515.127.4566', '2007-08-10', 'PU_CLERK', 2500.00, NULL, 114, 30);
INSERT INTO "public"."employees"
VALUES (120, 'Matthew', 'Weiss', 'MWEISS', '650.123.1234', '2004-07-18', 'ST_MAN', 8000.00, NULL, 100, 50);
INSERT INTO "public"."employees"
VALUES (121, 'Adam', 'Fripp', 'AFRIPP', '650.123.2234', '2005-04-10', 'ST_MAN', 8200.00, NULL, 100, 50);
INSERT INTO "public"."employees"
VALUES (122, 'Payam', 'Kaufling', 'PKAUFLIN', '650.123.3234', '2003-05-01', 'ST_MAN', 7900.00, NULL, 100, 50);
INSERT INTO "public"."employees"
VALUES (123, 'Shanta', 'Vollman', 'SVOLLMAN', '650.123.4234', '2005-10-10', 'ST_MAN', 6500.00, NULL, 100, 50);
INSERT INTO "public"."employees"
VALUES (124, 'Kevin', 'Mourgos', 'KMOURGOS', '650.123.5234', '2007-11-16', 'ST_MAN', 5800.00, NULL, 100, 50);
INSERT INTO "public"."employees"
VALUES (125, 'Julia', 'Nayer', 'JNAYER', '650.124.1214', '2005-07-16', 'ST_CLERK', 3200.00, NULL, 120, 50);
INSERT INTO "public"."employees"
VALUES (126, 'Irene', 'Mikkilineni', 'IMIKKILI', '650.124.1224', '2006-09-28', 'ST_CLERK', 2700.00, NULL, 120, 50);
INSERT INTO "public"."employees"
VALUES (127, 'James', 'Landry', 'JLANDRY', '650.124.1334', '2007-01-14', 'ST_CLERK', 2400.00, NULL, 120, 50);
INSERT INTO "public"."employees"
VALUES (128, 'Steven', 'Markle', 'SMARKLE', '650.124.1434', '2008-03-08', 'ST_CLERK', 2200.00, NULL, 120, 50);
INSERT INTO "public"."employees"
VALUES (129, 'Laura', 'Bissot', 'LBISSOT', '650.124.5234', '2005-08-20', 'ST_CLERK', 3300.00, NULL, 121, 50);
INSERT INTO "public"."employees"
VALUES (130, 'Mozhe', 'Atkinson', 'MATKINSO', '650.124.6234', '2005-10-30', 'ST_CLERK', 2800.00, NULL, 121, 50);
INSERT INTO "public"."employees"
VALUES (131, 'James', 'Marlow', 'JAMRLOW', '650.124.7234', '2005-02-16', 'ST_CLERK', 2500.00, NULL, 121, 50);
INSERT INTO "public"."employees"
VALUES (132, 'TJ', 'Olson', 'TJOLSON', '650.124.8234', '2007-04-10', 'ST_CLERK', 2100.00, NULL, 121, 50);
INSERT INTO "public"."employees"
VALUES (133, 'Jason', 'Mallin', 'JMALLIN', '650.127.1934', '2004-06-14', 'ST_CLERK', 3300.00, NULL, 122, 50);
INSERT INTO "public"."employees"
VALUES (134, 'Michael', 'Rogers', 'MROGERS', '650.127.1834', '2006-08-26', 'ST_CLERK', 2900.00, NULL, 122, 50);
INSERT INTO "public"."employees"
VALUES (135, 'Ki', 'Gee', 'KGEE', '650.127.1734', '2007-12-12', 'ST_CLERK', 2400.00, NULL, 122, 50);
INSERT INTO "public"."employees"
VALUES (136, 'Hazel', 'Philtanker', 'HPHILTAN', '650.127.1634', '2008-02-06', 'ST_CLERK', 2200.00, NULL, 122, 50);
INSERT INTO "public"."employees"
VALUES (137, 'Renske', 'Ladwig', 'RLADWIG', '650.121.1234', '2003-07-14', 'ST_CLERK', 3600.00, NULL, 123, 50);
INSERT INTO "public"."employees"
VALUES (138, 'Stephen', 'Stiles', 'SSTILES', '650.121.2034', '2005-10-26', 'ST_CLERK', 3200.00, NULL, 123, 50);
INSERT INTO "public"."employees"
VALUES (139, 'John', 'Seo', 'JSEO', '650.121.2019', '2006-02-12', 'ST_CLERK', 2700.00, NULL, 123, 50);
INSERT INTO "public"."employees"
VALUES (140, 'Joshua', 'Patel', 'JPATEL', '650.121.1834', '2006-04-06', 'ST_CLERK', 2500.00, NULL, 123, 50);
INSERT INTO "public"."employees"
VALUES (141, 'Trenna', 'Rajs', 'TRAJS', '650.121.8009', '2003-10-17', 'ST_CLERK', 3500.00, NULL, 124, 50);
INSERT INTO "public"."employees"
VALUES (142, 'Curtis', 'Davies', 'CDAVIES', '650.121.2994', '2005-01-29', 'ST_CLERK', 3100.00, NULL, 124, 50);
INSERT INTO "public"."employees"
VALUES (143, 'Randall', 'Matos', 'RMATOS', '650.121.2874', '2006-03-15', 'ST_CLERK', 2600.00, NULL, 124, 50);
INSERT INTO "public"."employees"
VALUES (144, 'Peter', 'Vargas', 'PVARGAS', '650.121.2004', '2006-07-09', 'ST_CLERK', 2500.00, NULL, 124, 50);
INSERT INTO "public"."employees"
VALUES (145, 'John', 'Russell', 'JRUSSEL', '011.44.1344.429268', '2004-10-01', 'SA_MAN', 14000.00, 0.40, 100, 80);
INSERT INTO "public"."employees"
VALUES (146, 'Karen', 'Partners', 'KPARTNER', '011.44.1344.467268', '2005-01-05', 'SA_MAN', 13500.00, 0.30, 100, 80);
INSERT INTO "public"."employees"
VALUES (147, 'Alberto', 'Errazuriz', 'AERRAZUR', '011.44.1344.429278', '2005-03-10', 'SA_MAN', 12000.00, 0.30, 100, 80);
INSERT INTO "public"."employees"
VALUES (148, 'Gerald', 'Cambrault', 'GCAMBRAU', '011.44.1344.619268', '2007-10-15', 'SA_MAN', 11000.00, 0.30, 100, 80);
INSERT INTO "public"."employees"
VALUES (149, 'Eleni', 'Zlotkey', 'EZLOTKEY', '011.44.1344.429018', '2008-01-29', 'SA_MAN', 10500.00, 0.20, 100, 80);
INSERT INTO "public"."employees"
VALUES (150, 'Peter', 'Tucker', 'PTUCKER', '011.44.1344.129268', '2005-01-30', 'SA_REP', 10000.00, 0.30, 145, 80);
INSERT INTO "public"."employees"
VALUES (151, 'David', 'Bernstein', 'DBERNSTE', '011.44.1344.345268', '2005-03-24', 'SA_REP', 9500.00, 0.25, 145, 80);
INSERT INTO "public"."employees"
VALUES (152, 'Peter', 'Hall', 'PHALL', '011.44.1344.478968', '2005-08-20', 'SA_REP', 9000.00, 0.25, 145, 80);
INSERT INTO "public"."employees"
VALUES (153, 'Christopher', 'Olsen', 'COLSEN', '011.44.1344.498718', '2006-03-30', 'SA_REP', 8000.00, 0.20, 145, 80);
INSERT INTO "public"."employees"
VALUES (154, 'Nanette', 'Cambrault', 'NCAMBRAU', '011.44.1344.987668', '2006-12-09', 'SA_REP', 7500.00, 0.20, 145, 80);
INSERT INTO "public"."employees"
VALUES (155, 'Oliver', 'Tuvault', 'OTUVAULT', '011.44.1344.486508', '2007-11-23', 'SA_REP', 7000.00, 0.15, 145, 80);
INSERT INTO "public"."employees"
VALUES (156, 'Janette', 'King', 'JKING', '011.44.1345.429268', '2004-01-30', 'SA_REP', 10000.00, 0.35, 146, 80);
INSERT INTO "public"."employees"
VALUES (157, 'Patrick', 'Sully', 'PSULLY', '011.44.1345.929268', '2004-03-04', 'SA_REP', 9500.00, 0.35, 146, 80);
INSERT INTO "public"."employees"
VALUES (158, 'Allan', 'McEwen', 'AMCEWEN', '011.44.1345.829268', '2004-08-01', 'SA_REP', 9000.00, 0.35, 146, 80);
INSERT INTO "public"."employees"
VALUES (159, 'Lindsey', 'Smith', 'LSMITH', '011.44.1345.729268', '2005-03-10', 'SA_REP', 8000.00, 0.30, 146, 80);
INSERT INTO "public"."employees"
VALUES (160, 'Louise', 'Doran', 'LDORAN', '011.44.1345.629268', '2005-12-15', 'SA_REP', 7500.00, 0.30, 146, 80);
INSERT INTO "public"."employees"
VALUES (161, 'Sarath', 'Sewall', 'SSEWALL', '011.44.1345.529268', '2006-11-03', 'SA_REP', 7000.00, 0.25, 146, 80);
INSERT INTO "public"."employees"
VALUES (162, 'Clara', 'Vishney', 'CVISHNEY', '011.44.1346.129268', '2005-11-11', 'SA_REP', 10500.00, 0.25, 147, 80);
INSERT INTO "public"."employees"
VALUES (163, 'Danielle', 'Greene', 'DGREENE', '011.44.1346.229268', '2007-03-19', 'SA_REP', 9500.00, 0.15, 147, 80);
INSERT INTO "public"."employees"
VALUES (164, 'Mattea', 'Marvins', 'MMARVINS', '011.44.1346.329268', '2008-01-24', 'SA_REP', 7200.00, 0.10, 147, 80);
INSERT INTO "public"."employees"
VALUES (165, 'David', 'Lee', 'DLEE', '011.44.1346.529268', '2008-02-23', 'SA_REP', 6800.00, 0.10, 147, 80);
INSERT INTO "public"."employees"
VALUES (166, 'Sundar', 'Ande', 'SANDE', '011.44.1346.629268', '2008-03-24', 'SA_REP', 6400.00, 0.10, 147, 80);
INSERT INTO "public"."employees"
VALUES (167, 'Amit', 'Banda', 'ABANDA', '011.44.1346.729268', '2008-04-21', 'SA_REP', 6200.00, 0.10, 147, 80);
INSERT INTO "public"."employees"
VALUES (168, 'Lisa', 'Ozer', 'LOZER', '011.44.1343.929268', '2005-03-11', 'SA_REP', 11500.00, 0.25, 148, 80);
INSERT INTO "public"."employees"
VALUES (169, 'Harrison', 'Bloom', 'HBLOOM', '011.44.1343.829268', '2006-03-23', 'SA_REP', 10000.00, 0.20, 148, 80);
INSERT INTO "public"."employees"
VALUES (170, 'Tayler', 'Fox', 'TFOX', '011.44.1343.729268', '2006-01-24', 'SA_REP', 9600.00, 0.20, 148, 80);
INSERT INTO "public"."employees"
VALUES (171, 'William', 'Smith', 'WSMITH', '011.44.1343.629268', '2007-02-23', 'SA_REP', 7400.00, 0.15, 148, 80);
INSERT INTO "public"."employees"
VALUES (172, 'Elizabeth', 'Bates', 'EBATES', '011.44.1343.529268', '2007-03-24', 'SA_REP', 7300.00, 0.15, 148, 80);
INSERT INTO "public"."employees"
VALUES (173, 'Sundita', 'Kumar', 'SKUMAR', '011.44.1343.329268', '2008-04-21', 'SA_REP', 6100.00, 0.10, 148, 80);
INSERT INTO "public"."employees"
VALUES (174, 'Ellen', 'Abel', 'EABEL', '011.44.1644.429267', '2004-05-11', 'SA_REP', 11000.00, 0.30, 149, 80);
INSERT INTO "public"."employees"
VALUES (175, 'Alyssa', 'Hutton', 'AHUTTON', '011.44.1644.429266', '2005-03-19', 'SA_REP', 8800.00, 0.25, 149, 80);
INSERT INTO "public"."employees"
VALUES (176, 'Jonathon', 'Taylor', 'JTAYLOR', '011.44.1644.429265', '2006-03-24', 'SA_REP', 8600.00, 0.20, 149, 80);
INSERT INTO "public"."employees"
VALUES (177, 'Jack', 'Livingston', 'JLIVINGS', '011.44.1644.429264', '2006-04-23', 'SA_REP', 8400.00, 0.20, 149, 80);
INSERT INTO "public"."employees"
VALUES (178, 'Kimberely', 'Grant', 'KGRANT', '011.44.1644.429263', '2007-05-24', 'SA_REP', 7000.00, 0.15, 149, NULL);
INSERT INTO "public"."employees"
VALUES (179, 'Charles', 'Johnson', 'CJOHNSON', '011.44.1644.429262', '2008-01-04', 'SA_REP', 6200.00, 0.10, 149, 80);
INSERT INTO "public"."employees"
VALUES (180, 'Winston', 'Taylor', 'WTAYLOR', '650.507.9876', '2006-01-24', 'SH_CLERK', 3200.00, NULL, 120, 50);
INSERT INTO "public"."employees"
VALUES (181, 'Jean', 'Fleaur', 'JFLEAUR', '650.507.9877', '2006-02-23', 'SH_CLERK', 3100.00, NULL, 120, 50);
INSERT INTO "public"."employees"
VALUES (182, 'Martha', 'Sullivan', 'MSULLIVA', '650.507.9878', '2007-06-21', 'SH_CLERK', 2500.00, NULL, 120, 50);
INSERT INTO "public"."employees"
VALUES (183, 'Girard', 'Geoni', 'GGEONI', '650.507.9879', '2008-02-03', 'SH_CLERK', 2800.00, NULL, 120, 50);
INSERT INTO "public"."employees"
VALUES (184, 'Nandita', 'Sarchand', 'NSARCHAN', '650.509.1876', '2004-01-27', 'SH_CLERK', 4200.00, NULL, 121, 50);
INSERT INTO "public"."employees"
VALUES (185, 'Alexis', 'Bull', 'ABULL', '650.509.2876', '2005-02-20', 'SH_CLERK', 4100.00, NULL, 121, 50);
INSERT INTO "public"."employees"
VALUES (186, 'Julia', 'Dellinger', 'JDELLING', '650.509.3876', '2006-06-24', 'SH_CLERK', 3400.00, NULL, 121, 50);
INSERT INTO "public"."employees"
VALUES (187, 'Anthony', 'Cabrio', 'ACABRIO', '650.509.4876', '2007-02-07', 'SH_CLERK', 3000.00, NULL, 121, 50);
INSERT INTO "public"."employees"
VALUES (188, 'Kelly', 'Chung', 'KCHUNG', '650.505.1876', '2005-06-14', 'SH_CLERK', 3800.00, NULL, 122, 50);
INSERT INTO "public"."employees"
VALUES (189, 'Jennifer', 'Dilly', 'JDILLY', '650.505.2876', '2005-08-13', 'SH_CLERK', 3600.00, NULL, 122, 50);
INSERT INTO "public"."employees"
VALUES (190, 'Timothy', 'Gates', 'TGATES', '650.505.3876', '2006-07-11', 'SH_CLERK', 2900.00, NULL, 122, 50);
INSERT INTO "public"."employees"
VALUES (191, 'Randall', 'Perkins', 'RPERKINS', '650.505.4876', '2007-12-19', 'SH_CLERK', 2500.00, NULL, 122, 50);
INSERT INTO "public"."employees"
VALUES (192, 'Sarah', 'Bell', 'SBELL', '650.501.1876', '2004-02-04', 'SH_CLERK', 4000.00, NULL, 123, 50);
INSERT INTO "public"."employees"
VALUES (193, 'Britney', 'Everett', 'BEVERETT', '650.501.2876', '2005-03-03', 'SH_CLERK', 3900.00, NULL, 123, 50);
INSERT INTO "public"."employees"
VALUES (194, 'Samuel', 'McCain', 'SMCCAIN', '650.501.3876', '2006-07-01', 'SH_CLERK', 3200.00, NULL, 123, 50);
INSERT INTO "public"."employees"
VALUES (195, 'Vance', 'Jones', 'VJONES', '650.501.4876', '2007-03-17', 'SH_CLERK', 2800.00, NULL, 123, 50);
INSERT INTO "public"."employees"
VALUES (196, 'Alana', 'Walsh', 'AWALSH', '650.507.9811', '2006-04-24', 'SH_CLERK', 3100.00, NULL, 124, 50);
INSERT INTO "public"."employees"
VALUES (197, 'Kevin', 'Feeney', 'KFEENEY', '650.507.9822', '2006-05-23', 'SH_CLERK', 3000.00, NULL, 124, 50);
INSERT INTO "public"."employees"
VALUES (198, 'Donald', 'OConnell', 'DOCONNEL', '650.507.9833', '2007-06-21', 'SH_CLERK', 2600.00, NULL, 124, 50);
INSERT INTO "public"."employees"
VALUES (199, 'Douglas', 'Grant', 'DGRANT', '650.507.9844', '2008-01-13', 'SH_CLERK', 2600.00, NULL, 124, 50);
INSERT INTO "public"."employees"
VALUES (200, 'Jennifer', 'Whalen', 'JWHALEN', '515.123.4444', '2003-09-17', 'AD_ASST', 4400.00, NULL, 101, 10);
INSERT INTO "public"."employees"
VALUES (201, 'Michael', 'Hartstein', 'MHARTSTE', '515.123.5555', '2004-02-17', 'MK_MAN', 13000.00, NULL, 100, 20);
INSERT INTO "public"."employees"
VALUES (202, 'Pat', 'Fay', 'PFAY', '603.123.6666', '2005-08-17', 'MK_REP', 6000.00, NULL, 201, 20);
INSERT INTO "public"."employees"
VALUES (203, 'Susan', 'Mavris', 'SMAVRIS', '515.123.7777', '2002-06-07', 'HR_REP', 6500.00, NULL, 101, 40);
INSERT INTO "public"."employees"
VALUES (204, 'Hermann', 'Baer', 'HBAER', '515.123.8888', '2002-06-07', 'PR_REP', 10000.00, NULL, 101, 70);
INSERT INTO "public"."employees"
VALUES (205, 'Shelley', 'Higgins', 'SHIGGINS', '515.123.8080', '2002-06-07', 'AC_MGR', 12008.00, NULL, 101, 110);
INSERT INTO "public"."employees"
VALUES (206, 'William', 'Gietz', 'WGIETZ', '515.123.8181', '2002-06-07', 'AC_ACCOUNT', 8300.00, NULL, 205, 110);

-- ----------------------------
-- Indexes structure for table employees
-- ----------------------------
CREATE INDEX "emp_department_ix" ON "public"."employees" USING btree (
                                                                      "department_id" "pg_catalog"."int4_ops" ASC NULLS
                                                                      LAST
    );
CREATE INDEX "emp_job_ix" ON "public"."employees" USING btree (
                                                               "job_id" COLLATE "pg_catalog"."default"
                                                               "pg_catalog"."text_ops" ASC NULLS LAST
    );
CREATE INDEX "emp_manager_ix" ON "public"."employees" USING btree (
                                                                   "manager_id" "pg_catalog"."int4_ops" ASC NULLS LAST
    );
CREATE INDEX "emp_name_ix" ON "public"."employees" USING btree (
                                                                "last_name" COLLATE "pg_catalog"."default"
                                                                "pg_catalog"."text_ops" ASC NULLS LAST,
                                                                "first_name" COLLATE "pg_catalog"."default"
                                                                "pg_catalog"."text_ops" ASC NULLS LAST
    );

-- ----------------------------
-- Uniques structure for table employees
-- ----------------------------
ALTER TABLE "public"."employees"
    ADD CONSTRAINT "emp_email_uk" UNIQUE ("email");

-- ----------------------------
-- Checks structure for table employees
-- ----------------------------
ALTER TABLE "public"."employees"
    ADD CONSTRAINT "emp_salary_min" CHECK (salary > 0:: numeric);

-- ----------------------------
-- Primary Key structure for table employees
-- ----------------------------
ALTER TABLE "public"."employees"
    ADD CONSTRAINT "emp_emp_id_pk" PRIMARY KEY ("employee_id");

-- ----------------------------
-- Foreign Keys structure for table employees
-- ----------------------------
ALTER TABLE "public"."employees"
    ADD CONSTRAINT "emp_dept_fk" FOREIGN KEY ("department_id") REFERENCES "public"."departments" ("department_id") ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE "public"."employees"
    ADD CONSTRAINT "emp_job_fk" FOREIGN KEY ("job_id") REFERENCES "public"."jobs" ("job_id") ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE "public"."employees"
    ADD CONSTRAINT "emp_manager_fk" FOREIGN KEY ("manager_id") REFERENCES "public"."employees" ("employee_id") ON DELETE NO ACTION ON UPDATE NO ACTION;



-- ----------------------------
-- Table structure for jobs
-- ----------------------------
DROP TABLE IF EXISTS "public"."jobs";
CREATE TABLE "public"."jobs"
(
    "job_id"     varchar(10) COLLATE "pg_catalog"."default" NOT NULL,
    "job_title"  varchar(35) COLLATE "pg_catalog"."default" NOT NULL,
    "min_salary" int4,
    "max_salary" int4
)
;

-- ----------------------------
-- Records of jobs
-- ----------------------------
INSERT INTO "public"."jobs"
VALUES ('AD_PRES', 'President', 20080, 40000);
INSERT INTO "public"."jobs"
VALUES ('AD_VP', 'Administration Vice President', 15000, 30000);
INSERT INTO "public"."jobs"
VALUES ('AD_ASST', 'Administration Assistant', 3000, 6000);
INSERT INTO "public"."jobs"
VALUES ('FI_MGR', 'Finance Manager', 8200, 16000);
INSERT INTO "public"."jobs"
VALUES ('FI_ACCOUNT', 'Accountant', 4200, 9000);
INSERT INTO "public"."jobs"
VALUES ('AC_MGR', 'Accounting Manager', 8200, 16000);
INSERT INTO "public"."jobs"
VALUES ('AC_ACCOUNT', 'Public Accountant', 4200, 9000);
INSERT INTO "public"."jobs"
VALUES ('SA_MAN', 'Sales Manager', 10000, 20080);
INSERT INTO "public"."jobs"
VALUES ('SA_REP', 'Sales Representative', 6000, 12008);
INSERT INTO "public"."jobs"
VALUES ('PU_MAN', 'Purchasing Manager', 8000, 15000);
INSERT INTO "public"."jobs"
VALUES ('PU_CLERK', 'Purchasing Clerk', 2500, 5500);
INSERT INTO "public"."jobs"
VALUES ('ST_MAN', 'Stock Manager', 5500, 8500);
INSERT INTO "public"."jobs"
VALUES ('ST_CLERK', 'Stock Clerk', 2008, 5000);
INSERT INTO "public"."jobs"
VALUES ('SH_CLERK', 'Shipping Clerk', 2500, 5500);
INSERT INTO "public"."jobs"
VALUES ('IT_PROG', 'Programmer', 4000, 10000);
INSERT INTO "public"."jobs"
VALUES ('MK_MAN', 'Marketing Manager', 9000, 15000);
INSERT INTO "public"."jobs"
VALUES ('MK_REP', 'Marketing Representative', 4000, 9000);
INSERT INTO "public"."jobs"
VALUES ('HR_REP', 'Human Resources Representative', 4000, 9000);
INSERT INTO "public"."jobs"
VALUES ('PR_REP', 'Public Relations Representative', 4500, 10500);

-- ----------------------------
-- Primary Key structure for table jobs
-- ----------------------------
ALTER TABLE "public"."jobs"
    ADD CONSTRAINT "job_id_pk" PRIMARY KEY ("job_id");