--
-- PostgreSQL database dump
--

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;

--
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: catpics; Type: TABLE; Schema: public; Owner: Guest; Tablespace: 
--

CREATE TABLE catpics (
    id integer NOT NULL,
    image bytea
);


ALTER TABLE catpics OWNER TO "Guest";

--
-- Name: catpics_id_seq; Type: SEQUENCE; Schema: public; Owner: Guest
--

CREATE SEQUENCE catpics_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE catpics_id_seq OWNER TO "Guest";

--
-- Name: catpics_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: Guest
--

ALTER SEQUENCE catpics_id_seq OWNED BY catpics.id;


--
-- Name: cats; Type: TABLE; Schema: public; Owner: Guest; Tablespace: 
--

CREATE TABLE cats (
    id integer NOT NULL,
    name character varying,
    status boolean,
    location character varying,
    date timestamp without time zone,
    description character varying,
    user_id integer
);


ALTER TABLE cats OWNER TO "Guest";

--
-- Name: cats_id_seq; Type: SEQUENCE; Schema: public; Owner: Guest
--

CREATE SEQUENCE cats_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE cats_id_seq OWNER TO "Guest";

--
-- Name: cats_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: Guest
--

ALTER SEQUENCE cats_id_seq OWNED BY cats.id;


--
-- Name: cats_users; Type: TABLE; Schema: public; Owner: Guest; Tablespace: 
--

CREATE TABLE cats_users (
    id integer NOT NULL,
    user_id integer,
    cat_id integer
);


ALTER TABLE cats_users OWNER TO "Guest";

--
-- Name: cats_users_id_seq; Type: SEQUENCE; Schema: public; Owner: Guest
--

CREATE SEQUENCE cats_users_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE cats_users_id_seq OWNER TO "Guest";

--
-- Name: cats_users_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: Guest
--

ALTER SEQUENCE cats_users_id_seq OWNED BY cats_users.id;


--
-- Name: comments; Type: TABLE; Schema: public; Owner: Guest; Tablespace: 
--

CREATE TABLE comments (
    id integer NOT NULL,
    description character varying,
    user_id integer,
    cat_id integer,
    date timestamp without time zone,
    username character varying
);


ALTER TABLE comments OWNER TO "Guest";

--
-- Name: comments_id_seq; Type: SEQUENCE; Schema: public; Owner: Guest
--

CREATE SEQUENCE comments_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE comments_id_seq OWNER TO "Guest";

--
-- Name: comments_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: Guest
--

ALTER SEQUENCE comments_id_seq OWNED BY comments.id;


--
-- Name: users; Type: TABLE; Schema: public; Owner: Guest; Tablespace: 
--

CREATE TABLE users (
    id integer NOT NULL,
    name character varying
);


ALTER TABLE users OWNER TO "Guest";

--
-- Name: users_id_seq; Type: SEQUENCE; Schema: public; Owner: Guest
--

CREATE SEQUENCE users_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE users_id_seq OWNER TO "Guest";

--
-- Name: users_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: Guest
--

ALTER SEQUENCE users_id_seq OWNED BY users.id;


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: Guest
--

ALTER TABLE ONLY catpics ALTER COLUMN id SET DEFAULT nextval('catpics_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: Guest
--

ALTER TABLE ONLY cats ALTER COLUMN id SET DEFAULT nextval('cats_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: Guest
--

ALTER TABLE ONLY cats_users ALTER COLUMN id SET DEFAULT nextval('cats_users_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: Guest
--

ALTER TABLE ONLY comments ALTER COLUMN id SET DEFAULT nextval('comments_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: Guest
--

ALTER TABLE ONLY users ALTER COLUMN id SET DEFAULT nextval('users_id_seq'::regclass);


--
-- Data for Name: catpics; Type: TABLE DATA; Schema: public; Owner: Guest
--

COPY catpics (id, image) FROM stdin;
\.


--
-- Name: catpics_id_seq; Type: SEQUENCE SET; Schema: public; Owner: Guest
--

SELECT pg_catalog.setval('catpics_id_seq', 1, false);


--
-- Data for Name: cats; Type: TABLE DATA; Schema: public; Owner: Guest
--

COPY cats (id, name, status, location, date, description, user_id) FROM stdin;
22	Geoffrey	f	\N	2016-05-18 11:25:07.124	Maine Coon	\N
23	Appa	f	\N	2016-05-18 11:25:22.507	Siamese	\N
24	Lil' Durfy	f	\N	2016-05-18 11:27:01.138	Grey	\N
25	John Jr.	f	\N	2016-05-18 11:27:20.86	Maine Coon	\N
26	John Dow	f	\N	2016-05-18 13:46:35.743	Unknown	\N
\.


--
-- Name: cats_id_seq; Type: SEQUENCE SET; Schema: public; Owner: Guest
--

SELECT pg_catalog.setval('cats_id_seq', 26, true);


--
-- Data for Name: cats_users; Type: TABLE DATA; Schema: public; Owner: Guest
--

COPY cats_users (id, user_id, cat_id) FROM stdin;
10	7	22
11	7	23
12	8	24
13	8	25
14	7	26
\.


--
-- Name: cats_users_id_seq; Type: SEQUENCE SET; Schema: public; Owner: Guest
--

SELECT pg_catalog.setval('cats_users_id_seq', 14, true);


--
-- Data for Name: comments; Type: TABLE DATA; Schema: public; Owner: Guest
--

COPY comments (id, description, user_id, cat_id, date, username) FROM stdin;
18	this cat is so sweet	4	7	2016-05-18 08:56:59.648	meowthkid47
19	I like to pet this cat on my way to work	5	7	2016-05-18 08:57:11.104	JDeezy
20	this cat fuckin blows	4	9	2016-05-18 10:41:53.3	meowthkid47
21	warning, dont touch belly	2	9	2016-05-18 10:42:03.68	Scott
22	I found this cat on the way to school, he appears to be a racist	7	23	2016-05-18 11:25:58.919	jdeezy
23	cool cat, 10/10	8	25	2016-05-18 11:27:36.206	samuel.the.durf
\.


--
-- Name: comments_id_seq; Type: SEQUENCE SET; Schema: public; Owner: Guest
--

SELECT pg_catalog.setval('comments_id_seq', 23, true);


--
-- Data for Name: users; Type: TABLE DATA; Schema: public; Owner: Guest
--

COPY users (id, name) FROM stdin;
6	scotthollandjr
7	jdeezy
8	samuel.the.durf
\.


--
-- Name: users_id_seq; Type: SEQUENCE SET; Schema: public; Owner: Guest
--

SELECT pg_catalog.setval('users_id_seq', 8, true);


--
-- Name: catpics_pkey; Type: CONSTRAINT; Schema: public; Owner: Guest; Tablespace: 
--

ALTER TABLE ONLY catpics
    ADD CONSTRAINT catpics_pkey PRIMARY KEY (id);


--
-- Name: cats_pkey; Type: CONSTRAINT; Schema: public; Owner: Guest; Tablespace: 
--

ALTER TABLE ONLY cats
    ADD CONSTRAINT cats_pkey PRIMARY KEY (id);


--
-- Name: cats_users_pkey; Type: CONSTRAINT; Schema: public; Owner: Guest; Tablespace: 
--

ALTER TABLE ONLY cats_users
    ADD CONSTRAINT cats_users_pkey PRIMARY KEY (id);


--
-- Name: comments_pkey; Type: CONSTRAINT; Schema: public; Owner: Guest; Tablespace: 
--

ALTER TABLE ONLY comments
    ADD CONSTRAINT comments_pkey PRIMARY KEY (id);


--
-- Name: users_pkey; Type: CONSTRAINT; Schema: public; Owner: Guest; Tablespace: 
--

ALTER TABLE ONLY users
    ADD CONSTRAINT users_pkey PRIMARY KEY (id);


--
-- Name: public; Type: ACL; Schema: -; Owner: epicodus
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM epicodus;
GRANT ALL ON SCHEMA public TO epicodus;
GRANT ALL ON SCHEMA public TO PUBLIC;


--
-- PostgreSQL database dump complete
--

