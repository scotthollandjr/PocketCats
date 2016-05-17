--
-- PostgreSQL database dump
--

-- Dumped from database version 9.5.2
-- Dumped by pg_dump version 9.5.2

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

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
-- Name: cats; Type: TABLE; Schema: public; Owner: SamDurfey
--

CREATE TABLE cats (
    id integer NOT NULL,
    name character varying,
    status boolean,
    location character varying,
    date timestamp without time zone,
    description character varying
);


ALTER TABLE cats OWNER TO "SamDurfey";

--
-- Name: cats_id_seq; Type: SEQUENCE; Schema: public; Owner: SamDurfey
--

CREATE SEQUENCE cats_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE cats_id_seq OWNER TO "SamDurfey";

--
-- Name: cats_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: SamDurfey
--

ALTER SEQUENCE cats_id_seq OWNED BY cats.id;


--
-- Name: cats_users; Type: TABLE; Schema: public; Owner: SamDurfey
--

CREATE TABLE cats_users (
    id integer NOT NULL,
    user_id integer,
    cat_id integer
);


ALTER TABLE cats_users OWNER TO "SamDurfey";

--
-- Name: cats_users_id_seq; Type: SEQUENCE; Schema: public; Owner: SamDurfey
--

CREATE SEQUENCE cats_users_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE cats_users_id_seq OWNER TO "SamDurfey";

--
-- Name: cats_users_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: SamDurfey
--

ALTER SEQUENCE cats_users_id_seq OWNED BY cats_users.id;


--
-- Name: comments; Type: TABLE; Schema: public; Owner: SamDurfey
--

CREATE TABLE comments (
    id integer NOT NULL,
    description character varying,
    user_id integer,
    cat_id integer,
    date timestamp without time zone
);


ALTER TABLE comments OWNER TO "SamDurfey";

--
-- Name: comments_id_seq; Type: SEQUENCE; Schema: public; Owner: SamDurfey
--

CREATE SEQUENCE comments_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE comments_id_seq OWNER TO "SamDurfey";

--
-- Name: comments_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: SamDurfey
--

ALTER SEQUENCE comments_id_seq OWNED BY comments.id;


--
-- Name: locations; Type: TABLE; Schema: public; Owner: SamDurfey
--

CREATE TABLE locations (
    id integer NOT NULL,
    lat character varying,
    lng character varying,
    cat_id integer
);


ALTER TABLE locations OWNER TO "SamDurfey";

--
-- Name: location_id_seq; Type: SEQUENCE; Schema: public; Owner: SamDurfey
--

CREATE SEQUENCE location_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE location_id_seq OWNER TO "SamDurfey";

--
-- Name: location_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: SamDurfey
--

ALTER SEQUENCE location_id_seq OWNED BY locations.id;


--
-- Name: users; Type: TABLE; Schema: public; Owner: SamDurfey
--

CREATE TABLE users (
    id integer NOT NULL,
    name character varying
);


ALTER TABLE users OWNER TO "SamDurfey";

--
-- Name: users_id_seq; Type: SEQUENCE; Schema: public; Owner: SamDurfey
--

CREATE SEQUENCE users_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE users_id_seq OWNER TO "SamDurfey";

--
-- Name: users_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: SamDurfey
--

ALTER SEQUENCE users_id_seq OWNED BY users.id;


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: SamDurfey
--

ALTER TABLE ONLY cats ALTER COLUMN id SET DEFAULT nextval('cats_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: SamDurfey
--

ALTER TABLE ONLY cats_users ALTER COLUMN id SET DEFAULT nextval('cats_users_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: SamDurfey
--

ALTER TABLE ONLY comments ALTER COLUMN id SET DEFAULT nextval('comments_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: SamDurfey
--

ALTER TABLE ONLY locations ALTER COLUMN id SET DEFAULT nextval('location_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: SamDurfey
--

ALTER TABLE ONLY users ALTER COLUMN id SET DEFAULT nextval('users_id_seq'::regclass);


--
-- Data for Name: cats; Type: TABLE DATA; Schema: public; Owner: SamDurfey
--

COPY cats (id, name, status, location, date, description) FROM stdin;
\.


--
-- Name: cats_id_seq; Type: SEQUENCE SET; Schema: public; Owner: SamDurfey
--

SELECT pg_catalog.setval('cats_id_seq', 1, false);


--
-- Data for Name: cats_users; Type: TABLE DATA; Schema: public; Owner: SamDurfey
--

COPY cats_users (id, user_id, cat_id) FROM stdin;
\.


--
-- Name: cats_users_id_seq; Type: SEQUENCE SET; Schema: public; Owner: SamDurfey
--

SELECT pg_catalog.setval('cats_users_id_seq', 1, false);


--
-- Data for Name: comments; Type: TABLE DATA; Schema: public; Owner: SamDurfey
--

COPY comments (id, description, user_id, cat_id, date) FROM stdin;
\.


--
-- Name: comments_id_seq; Type: SEQUENCE SET; Schema: public; Owner: SamDurfey
--

SELECT pg_catalog.setval('comments_id_seq', 1, false);


--
-- Name: location_id_seq; Type: SEQUENCE SET; Schema: public; Owner: SamDurfey
--

SELECT pg_catalog.setval('location_id_seq', 2, true);


--
-- Data for Name: locations; Type: TABLE DATA; Schema: public; Owner: SamDurfey
--

COPY locations (id, lat, lng, cat_id) FROM stdin;
1	45.527497	-122.669188	\N
2	45.568705	-122.672301	\N
\.


--
-- Data for Name: users; Type: TABLE DATA; Schema: public; Owner: SamDurfey
--

COPY users (id, name) FROM stdin;
\.


--
-- Name: users_id_seq; Type: SEQUENCE SET; Schema: public; Owner: SamDurfey
--

SELECT pg_catalog.setval('users_id_seq', 1, false);


--
-- Name: cats_pkey; Type: CONSTRAINT; Schema: public; Owner: SamDurfey
--

ALTER TABLE ONLY cats
    ADD CONSTRAINT cats_pkey PRIMARY KEY (id);


--
-- Name: cats_users_pkey; Type: CONSTRAINT; Schema: public; Owner: SamDurfey
--

ALTER TABLE ONLY cats_users
    ADD CONSTRAINT cats_users_pkey PRIMARY KEY (id);


--
-- Name: comments_pkey; Type: CONSTRAINT; Schema: public; Owner: SamDurfey
--

ALTER TABLE ONLY comments
    ADD CONSTRAINT comments_pkey PRIMARY KEY (id);


--
-- Name: location_pkey; Type: CONSTRAINT; Schema: public; Owner: SamDurfey
--

ALTER TABLE ONLY locations
    ADD CONSTRAINT location_pkey PRIMARY KEY (id);


--
-- Name: users_pkey; Type: CONSTRAINT; Schema: public; Owner: SamDurfey
--

ALTER TABLE ONLY users
    ADD CONSTRAINT users_pkey PRIMARY KEY (id);


--
-- Name: public; Type: ACL; Schema: -; Owner: SamDurfey
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM "SamDurfey";
GRANT ALL ON SCHEMA public TO "SamDurfey";
GRANT ALL ON SCHEMA public TO PUBLIC;


--
-- PostgreSQL database dump complete
--

