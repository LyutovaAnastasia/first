--
-- PostgreSQL database dump
--

-- Dumped from database version 14.0
-- Dumped by pg_dump version 14.0

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: academies; Type: TABLE; Schema: server; Owner: postgres
--

CREATE TABLE server.academies (
    id bigint NOT NULL,
    name character varying(125) NOT NULL,
    link_tag character varying(255),
    image_id bigint NOT NULL
);


ALTER TABLE server.academies OWNER TO postgres;

--
-- Name: academy_seq; Type: SEQUENCE; Schema: server; Owner: postgres
--

CREATE SEQUENCE server.academy_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE server.academy_seq OWNER TO postgres;

--
-- Name: academy_seq; Type: SEQUENCE OWNED BY; Schema: server; Owner: postgres
--

ALTER SEQUENCE server.academy_seq OWNED BY server.academies.id;


--
-- Name: categories; Type: TABLE; Schema: server; Owner: postgres
--

CREATE TABLE server.categories (
    id bigint NOT NULL,
    name character varying(125),
    count_of_classes smallint,
    section_id bigint NOT NULL
);


ALTER TABLE server.categories OWNER TO postgres;

--
-- Name: categories_academies; Type: TABLE; Schema: server; Owner: postgres
--

CREATE TABLE server.categories_academies (
    id bigint NOT NULL,
    categories_id bigint NOT NULL,
    academies_id bigint NOT NULL
);


ALTER TABLE server.categories_academies OWNER TO postgres;

--
-- Name: COLUMN categories_academies.categories_id; Type: COMMENT; Schema: server; Owner: postgres
--

COMMENT ON COLUMN server.categories_academies.categories_id IS 'categories id';


--
-- Name: COLUMN categories_academies.academies_id; Type: COMMENT; Schema: server; Owner: postgres
--

COMMENT ON COLUMN server.categories_academies.academies_id IS 'academies id';


--
-- Name: categories_academies_seq; Type: SEQUENCE; Schema: server; Owner: postgres
--

CREATE SEQUENCE server.categories_academies_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE server.categories_academies_seq OWNER TO postgres;

--
-- Name: categories_academies_seq; Type: SEQUENCE OWNED BY; Schema: server; Owner: postgres
--

ALTER SEQUENCE server.categories_academies_seq OWNED BY server.categories_academies.id;


--
-- Name: category_seq; Type: SEQUENCE; Schema: server; Owner: postgres
--

CREATE SEQUENCE server.category_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE server.category_seq OWNER TO postgres;

--
-- Name: category_seq; Type: SEQUENCE OWNED BY; Schema: server; Owner: postgres
--

ALTER SEQUENCE server.category_seq OWNED BY server.categories.id;


--
-- Name: classes; Type: TABLE; Schema: server; Owner: postgres
--

CREATE TABLE server.classes (
    id bigint NOT NULL,
    name character varying(225),
    term smallint,
    price integer,
    rating smallint,
    description character varying(225),
    count_of_reviews integer DEFAULT 0,
    link_tag character varying(225),
    image_id bigint NOT NULL,
    academy_id bigint NOT NULL,
    category_id bigint NOT NULL
);


ALTER TABLE server.classes OWNER TO postgres;

--
-- Name: class_seq; Type: SEQUENCE; Schema: server; Owner: postgres
--

CREATE SEQUENCE server.class_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE server.class_seq OWNER TO postgres;

--
-- Name: class_seq; Type: SEQUENCE OWNED BY; Schema: server; Owner: postgres
--

ALTER SEQUENCE server.class_seq OWNED BY server.classes.id;


--
-- Name: images_company; Type: TABLE; Schema: server; Owner: postgres
--

CREATE TABLE server.images_company (
    id bigint NOT NULL,
    image character varying(255) NOT NULL
);


ALTER TABLE server.images_company OWNER TO postgres;

--
-- Name: images_company_seq; Type: SEQUENCE; Schema: server; Owner: postgres
--

CREATE SEQUENCE server.images_company_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE server.images_company_seq OWNER TO postgres;

--
-- Name: images_company_seq; Type: SEQUENCE OWNED BY; Schema: server; Owner: postgres
--

ALTER SEQUENCE server.images_company_seq OWNED BY server.images_company.id;


--
-- Name: reviews; Type: TABLE; Schema: server; Owner: postgres
--

CREATE TABLE server.reviews (
    id bigint NOT NULL,
    begin_date date,
    end_date date,
    minuses character varying(255) DEFAULT 'Не указано'::character varying,
    pluses character varying(255) DEFAULT 'Не указано'::character varying,
    comment character varying(255),
    mentor boolean DEFAULT false,
    employed boolean DEFAULT false,
    date date,
    mark integer DEFAULT 0,
    active boolean DEFAULT false,
    class_id bigint NOT NULL,
    user_id bigint NOT NULL
);


ALTER TABLE server.reviews OWNER TO postgres;

--
-- Name: review_seq; Type: SEQUENCE; Schema: server; Owner: postgres
--

CREATE SEQUENCE server.review_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE server.review_seq OWNER TO postgres;

--
-- Name: review_seq; Type: SEQUENCE OWNED BY; Schema: server; Owner: postgres
--

ALTER SEQUENCE server.review_seq OWNED BY server.reviews.id;


--
-- Name: sections; Type: TABLE; Schema: server; Owner: postgres
--

CREATE TABLE server.sections (
    id bigint NOT NULL,
    name character varying(125)
);


ALTER TABLE server.sections OWNER TO postgres;

--
-- Name: TABLE sections; Type: COMMENT; Schema: server; Owner: postgres
--

COMMENT ON TABLE server.sections IS 'таблица секций направлений it курсов';


--
-- Name: COLUMN sections.id; Type: COMMENT; Schema: server; Owner: postgres
--

COMMENT ON COLUMN server.sections.id IS 'первичный ключ';


--
-- Name: COLUMN sections.name; Type: COMMENT; Schema: server; Owner: postgres
--

COMMENT ON COLUMN server.sections.name IS 'name of the section';


--
-- Name: section_seq; Type: SEQUENCE; Schema: server; Owner: postgres
--

CREATE SEQUENCE server.section_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE server.section_seq OWNER TO postgres;

--
-- Name: section_seq; Type: SEQUENCE OWNED BY; Schema: server; Owner: postgres
--

ALTER SEQUENCE server.section_seq OWNED BY server.sections.id;


--
-- Name: users; Type: TABLE; Schema: server; Owner: postgres
--

CREATE TABLE server.users (
    id bigint NOT NULL,
    nickname character varying(255) NOT NULL
);


ALTER TABLE server.users OWNER TO postgres;

--
-- Name: user_seq; Type: SEQUENCE; Schema: server; Owner: postgres
--

CREATE SEQUENCE server.user_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE server.user_seq OWNER TO postgres;

--
-- Name: user_seq; Type: SEQUENCE OWNED BY; Schema: server; Owner: postgres
--

ALTER SEQUENCE server.user_seq OWNED BY server.users.id;


--
-- Name: academies id; Type: DEFAULT; Schema: server; Owner: postgres
--

ALTER TABLE ONLY server.academies ALTER COLUMN id SET DEFAULT nextval('server.academy_seq'::regclass);


--
-- Name: categories id; Type: DEFAULT; Schema: server; Owner: postgres
--

ALTER TABLE ONLY server.categories ALTER COLUMN id SET DEFAULT nextval('server.category_seq'::regclass);


--
-- Name: categories_academies id; Type: DEFAULT; Schema: server; Owner: postgres
--

ALTER TABLE ONLY server.categories_academies ALTER COLUMN id SET DEFAULT nextval('server.categories_academies_seq'::regclass);


--
-- Name: classes id; Type: DEFAULT; Schema: server; Owner: postgres
--

ALTER TABLE ONLY server.classes ALTER COLUMN id SET DEFAULT nextval('server.class_seq'::regclass);


--
-- Name: images_company id; Type: DEFAULT; Schema: server; Owner: postgres
--

ALTER TABLE ONLY server.images_company ALTER COLUMN id SET DEFAULT nextval('server.images_company_seq'::regclass);


--
-- Name: reviews id; Type: DEFAULT; Schema: server; Owner: postgres
--

ALTER TABLE ONLY server.reviews ALTER COLUMN id SET DEFAULT nextval('server.review_seq'::regclass);


--
-- Name: sections id; Type: DEFAULT; Schema: server; Owner: postgres
--

ALTER TABLE ONLY server.sections ALTER COLUMN id SET DEFAULT nextval('server.section_seq'::regclass);


--
-- Name: users id; Type: DEFAULT; Schema: server; Owner: postgres
--

ALTER TABLE ONLY server.users ALTER COLUMN id SET DEFAULT nextval('server.user_seq'::regclass);


--
-- Data for Name: academies; Type: TABLE DATA; Schema: server; Owner: postgres
--

COPY server.academies (id, name, link_tag, image_id) FROM stdin;
2	Нетология	https://netology.ru/	1
1	SkillBox	https://skillbox.ru/	2
5	Нетология1	https://netology.ru/	1
7	Нетология2	https://netology.ru/	4
8	Нетология3	https://netology.ru/	5
9	Нетология4	https://netology.ru/	5
11	Нетология5	https://netology.ru/	5
12	Нетология6	https://netology.ru/	5
13	Нетология7	https://netology.ru/	5
14	Нетология8	https://netology.ru/	5
15	Нетология9	https://netology.ru/	5
16	Нетология10	https://netology.ru/	5
19	Нетология11	https://netology.ru/	5
21	Нетология12	https://netology.ru/	5
22	Нетология13	https://netology.ru/	5
23	Нетология14	https://netology.ru/	5
24	Нетология15	https://netology.ru/	5
\.


--
-- Data for Name: categories; Type: TABLE DATA; Schema: server; Owner: postgres
--

COPY server.categories (id, name, count_of_classes, section_id) FROM stdin;
2	Разработка игр	0	1
3	Разработка игр на Unity	0	1
5	JavaScript-разработка	0	2
6	Java-разработка	0	2
7	PHP-разработка	0	2
8	Разработка на C#	0	2
9	Разработка на C++	0	2
10	Разработка на Kotlin	0	2
11	Разработка на Swift	0	2
12	Фреймворк Laravel	0	3
13	Фреймворк Django	0	3
14	Web-разработка	0	4
15	Android-разработка	0	4
16	Мобильная разработка	0	4
17	IOS-разработка	0	4
18	Frontend-разработка	0	4
19	Верстка на HTML/CSS	0	4
20	Создание сайтов	0	4
21	QA-тестирование	0	4
22	DevOps	0	4
1	VR/AR разработка	3	1
4	Python-разработка	11	2
\.


--
-- Data for Name: categories_academies; Type: TABLE DATA; Schema: server; Owner: postgres
--

COPY server.categories_academies (id, categories_id, academies_id) FROM stdin;
1	4	1
2	4	2
3	5	19
4	6	21
5	7	21
6	6	22
7	7	22
8	6	23
9	7	23
10	8	23
11	6	24
12	7	24
13	8	24
\.


--
-- Data for Name: classes; Type: TABLE DATA; Schema: server; Owner: postgres
--

COPY server.classes (id, name, term, price, rating, description, count_of_reviews, link_tag, image_id, academy_id, category_id) FROM stdin;
2	Профессия Python-разработчик	0	113419	0	description	0	linkTag	1	1	4
3	Профессия Fullstack-разработчик на Python	0	100424	0	description	0	linkTag	1	1	4
4	Python-фреймворк Django	0	13886	0	description	0	linkTag	1	1	4
5	Fullstack-разработчик на Python	18	119400	0	description	0	linkTag	1	2	4
6	Python-разработчик с нуля	10	64800	0	description	0	linkTag	1	2	4
7	Python для анализа данных	0	31500	0	description	0	linkTag	1	2	4
8	Django. Cоздание backend-приложений	1	15900	0	description	0	linkTag	1	2	4
9	Python Basic	0	40116	0	description	0	linkTag	1	1	1
10	Python Basic	0	40116	0	description	0	linkTag	6	1	1
11	Python Basic	0	40116	0	description	0	linkTag	6	1	1
12	Python Basic	0	40116	0	description	0	linkTag	6	1	4
13	Python Basic	0	40116	0	description	0	linkTag	6	1	4
14	Python Basic	0	40116	0	description	0	linkTag	6	1	4
1	Python Basic	0	40116	2	description	1	linkTag	1	1	4
\.


--
-- Data for Name: images_company; Type: TABLE DATA; Schema: server; Owner: postgres
--

COPY server.images_company (id, image) FROM stdin;
1	image1
2	image2
3	image3
4	image4
5	image5
6	image9
\.


--
-- Data for Name: reviews; Type: TABLE DATA; Schema: server; Owner: postgres
--

COPY server.reviews (id, begin_date, end_date, minuses, pluses, comment, mentor, employed, date, mark, active, class_id, user_id) FROM stdin;
2	2022-02-11	2022-03-19	minuses	pluses	comment	f	t	2022-03-19	3	f	1	1
1	2022-02-11	2022-03-19	minuses	pluses	comment	t	f	2022-03-19	2	t	1	1
3	2022-02-11	2022-03-19	minuses	pluses	comment	t	f	2022-03-19	2	f	1	1
\.


--
-- Data for Name: sections; Type: TABLE DATA; Schema: server; Owner: postgres
--

COPY server.sections (id, name) FROM stdin;
1	Игры
2	Языки
3	Фреймворки
4	Направления
\.


--
-- Data for Name: users; Type: TABLE DATA; Schema: server; Owner: postgres
--

COPY server.users (id, nickname) FROM stdin;
1	Anastasia
\.


--
-- Name: academy_seq; Type: SEQUENCE SET; Schema: server; Owner: postgres
--

SELECT pg_catalog.setval('server.academy_seq', 24, true);


--
-- Name: categories_academies_seq; Type: SEQUENCE SET; Schema: server; Owner: postgres
--

SELECT pg_catalog.setval('server.categories_academies_seq', 13, true);


--
-- Name: category_seq; Type: SEQUENCE SET; Schema: server; Owner: postgres
--

SELECT pg_catalog.setval('server.category_seq', 1, false);


--
-- Name: class_seq; Type: SEQUENCE SET; Schema: server; Owner: postgres
--

SELECT pg_catalog.setval('server.class_seq', 14, true);


--
-- Name: images_company_seq; Type: SEQUENCE SET; Schema: server; Owner: postgres
--

SELECT pg_catalog.setval('server.images_company_seq', 6, true);


--
-- Name: review_seq; Type: SEQUENCE SET; Schema: server; Owner: postgres
--

SELECT pg_catalog.setval('server.review_seq', 3, true);


--
-- Name: section_seq; Type: SEQUENCE SET; Schema: server; Owner: postgres
--

SELECT pg_catalog.setval('server.section_seq', 1, false);


--
-- Name: user_seq; Type: SEQUENCE SET; Schema: server; Owner: postgres
--

SELECT pg_catalog.setval('server.user_seq', 1, false);


--
-- Name: academies academies_name_uniq; Type: CONSTRAINT; Schema: server; Owner: postgres
--

ALTER TABLE ONLY server.academies
    ADD CONSTRAINT academies_name_uniq UNIQUE (name);


--
-- Name: academies academy_pk; Type: CONSTRAINT; Schema: server; Owner: postgres
--

ALTER TABLE ONLY server.academies
    ADD CONSTRAINT academy_pk PRIMARY KEY (id);


--
-- Name: categories_academies categories_academies_pk; Type: CONSTRAINT; Schema: server; Owner: postgres
--

ALTER TABLE ONLY server.categories_academies
    ADD CONSTRAINT categories_academies_pk PRIMARY KEY (id);


--
-- Name: categories category_pk; Type: CONSTRAINT; Schema: server; Owner: postgres
--

ALTER TABLE ONLY server.categories
    ADD CONSTRAINT category_pk PRIMARY KEY (id);


--
-- Name: classes class_pk; Type: CONSTRAINT; Schema: server; Owner: postgres
--

ALTER TABLE ONLY server.classes
    ADD CONSTRAINT class_pk PRIMARY KEY (id);


--
-- Name: images_company images_company_image_uniq; Type: CONSTRAINT; Schema: server; Owner: postgres
--

ALTER TABLE ONLY server.images_company
    ADD CONSTRAINT images_company_image_uniq UNIQUE (image);


--
-- Name: images_company images_company_pk; Type: CONSTRAINT; Schema: server; Owner: postgres
--

ALTER TABLE ONLY server.images_company
    ADD CONSTRAINT images_company_pk PRIMARY KEY (id);


--
-- Name: reviews review_pk; Type: CONSTRAINT; Schema: server; Owner: postgres
--

ALTER TABLE ONLY server.reviews
    ADD CONSTRAINT review_pk PRIMARY KEY (id);


--
-- Name: sections section_pk; Type: CONSTRAINT; Schema: server; Owner: postgres
--

ALTER TABLE ONLY server.sections
    ADD CONSTRAINT section_pk PRIMARY KEY (id);


--
-- Name: users user_pk; Type: CONSTRAINT; Schema: server; Owner: postgres
--

ALTER TABLE ONLY server.users
    ADD CONSTRAINT user_pk PRIMARY KEY (id);


--
-- Name: categories_academies fk_categories_academies_academy; Type: FK CONSTRAINT; Schema: server; Owner: postgres
--

ALTER TABLE ONLY server.categories_academies
    ADD CONSTRAINT fk_categories_academies_academy FOREIGN KEY (academies_id) REFERENCES server.academies(id);


--
-- Name: categories_academies fk_categories_academies_category; Type: FK CONSTRAINT; Schema: server; Owner: postgres
--

ALTER TABLE ONLY server.categories_academies
    ADD CONSTRAINT fk_categories_academies_category FOREIGN KEY (categories_id) REFERENCES server.categories(id);


--
-- Name: categories fk_categories_sections; Type: FK CONSTRAINT; Schema: server; Owner: postgres
--

ALTER TABLE ONLY server.categories
    ADD CONSTRAINT fk_categories_sections FOREIGN KEY (section_id) REFERENCES server.sections(id);


--
-- Name: classes fk_classes_academies; Type: FK CONSTRAINT; Schema: server; Owner: postgres
--

ALTER TABLE ONLY server.classes
    ADD CONSTRAINT fk_classes_academies FOREIGN KEY (academy_id) REFERENCES server.academies(id);


--
-- Name: classes fk_classes_categories; Type: FK CONSTRAINT; Schema: server; Owner: postgres
--

ALTER TABLE ONLY server.classes
    ADD CONSTRAINT fk_classes_categories FOREIGN KEY (category_id) REFERENCES server.categories(id);


--
-- Name: reviews fk_classes_reviews_id; Type: FK CONSTRAINT; Schema: server; Owner: postgres
--

ALTER TABLE ONLY server.reviews
    ADD CONSTRAINT fk_classes_reviews_id FOREIGN KEY (class_id) REFERENCES server.classes(id);


--
-- Name: classes fk_images_company_class; Type: FK CONSTRAINT; Schema: server; Owner: postgres
--

ALTER TABLE ONLY server.classes
    ADD CONSTRAINT fk_images_company_class FOREIGN KEY (image_id) REFERENCES server.images_company(id);


--
-- Name: academies fk_images_company_image; Type: FK CONSTRAINT; Schema: server; Owner: postgres
--

ALTER TABLE ONLY server.academies
    ADD CONSTRAINT fk_images_company_image FOREIGN KEY (image_id) REFERENCES server.images_company(id);


--
-- Name: reviews fk_users_reviews_id; Type: FK CONSTRAINT; Schema: server; Owner: postgres
--

ALTER TABLE ONLY server.reviews
    ADD CONSTRAINT fk_users_reviews_id FOREIGN KEY (user_id) REFERENCES server.users(id);


--
-- PostgreSQL database dump complete
--

