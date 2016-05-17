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
    description character varying
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

COPY cats (id, name, status, location, date, description) FROM stdin;
7	Groodle	f	\N	2016-05-17 15:10:54.889	Tuxedo
\.


--
-- Name: cats_id_seq; Type: SEQUENCE SET; Schema: public; Owner: Guest
--

SELECT pg_catalog.setval('cats_id_seq', 7, true);


--
-- Data for Name: cats_users; Type: TABLE DATA; Schema: public; Owner: Guest
--

COPY cats_users (id, user_id, cat_id) FROM stdin;
1	1	1
\.


--
-- Name: cats_users_id_seq; Type: SEQUENCE SET; Schema: public; Owner: Guest
--

SELECT pg_catalog.setval('cats_users_id_seq', 1, true);


--
-- Data for Name: comments; Type: TABLE DATA; Schema: public; Owner: Guest
--

COPY comments (id, description, user_id, cat_id, date, username) FROM stdin;
7	This is a great cat!	0	7	2016-05-17 15:14:09.097	Scottholland19
8	This cat scratched my retina, BEWARE!	0	7	2016-05-17 15:16:22.049	cbeddoe06
9	Loves belly rubs and retinas	0	7	2016-05-17 15:17:27.099	Tacocat69
10	Just stopping by to check out the timestamp!	0	7	2016-05-17 15:26:39.918	Stuffy
11	this is a comment	3	7	2016-05-17 16:23:51.387	\N
12	man thats a fuckin wicked cat	5	7	2016-05-17 16:28:14.878	\N
\.


--
-- Name: comments_id_seq; Type: SEQUENCE SET; Schema: public; Owner: Guest
--

SELECT pg_catalog.setval('comments_id_seq', 12, true);


--
-- Data for Name: users; Type: TABLE DATA; Schema: public; Owner: Guest
--

COPY users (id, name) FROM stdin;
1	Tom Cruise
2	Scott
3	ScottHollandJimmy
4	meowthkid47
5	JDeezy
\.


--
-- Name: users_id_seq; Type: SEQUENCE SET; Schema: public; Owner: Guest
--

SELECT pg_catalog.setval('users_id_seq', 5, true);


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

