--
-- PostgreSQL database dump
--

-- Dumped from database version 13.2
-- Dumped by pg_dump version 13.1

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
-- Name: courses; Type: TABLE; Schema: public; Owner: terlanismayilsoy
--

CREATE TABLE public.courses (
    course_id integer NOT NULL,
    course_name character varying(100) NOT NULL,
    created_on timestamp without time zone DEFAULT now() NOT NULL
);


ALTER TABLE public.courses OWNER TO terlanismayilsoy;

--
-- Name: courses_course_id_seq; Type: SEQUENCE; Schema: public; Owner: terlanismayilsoy
--

CREATE SEQUENCE public.courses_course_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.courses_course_id_seq OWNER TO terlanismayilsoy;

--
-- Name: courses_course_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: terlanismayilsoy
--

ALTER SEQUENCE public.courses_course_id_seq OWNED BY public.courses.course_id;


--
-- Name: enrollment; Type: TABLE; Schema: public; Owner: terlanismayilsoy
--

CREATE TABLE public.enrollment (
    user_id integer NOT NULL,
    course_id integer NOT NULL
);


ALTER TABLE public.enrollment OWNER TO terlanismayilsoy;

--
-- Name: users; Type: TABLE; Schema: public; Owner: terlanismayilsoy
--

CREATE TABLE public.users (
    user_id integer NOT NULL,
    password character varying(150) NOT NULL,
    email character varying(50) NOT NULL,
    created_on timestamp without time zone DEFAULT now() NOT NULL,
    first_name character varying(50),
    last_name character varying(50),
    age integer,
    city character varying(50),
    country character varying(50),
    gender character varying(15)
);


ALTER TABLE public.users OWNER TO terlanismayilsoy;

--
-- Name: users_user_id_seq; Type: SEQUENCE; Schema: public; Owner: terlanismayilsoy
--

CREATE SEQUENCE public.users_user_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.users_user_id_seq OWNER TO terlanismayilsoy;

--
-- Name: users_user_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: terlanismayilsoy
--

ALTER SEQUENCE public.users_user_id_seq OWNED BY public.users.user_id;


--
-- Name: courses course_id; Type: DEFAULT; Schema: public; Owner: terlanismayilsoy
--

ALTER TABLE ONLY public.courses ALTER COLUMN course_id SET DEFAULT nextval('public.courses_course_id_seq'::regclass);


--
-- Name: users user_id; Type: DEFAULT; Schema: public; Owner: terlanismayilsoy
--

ALTER TABLE ONLY public.users ALTER COLUMN user_id SET DEFAULT nextval('public.users_user_id_seq'::regclass);


--
-- Data for Name: courses; Type: TABLE DATA; Schema: public; Owner: terlanismayilsoy
--

COPY public.courses (course_id, course_name, created_on) FROM stdin;
1	Web & Mobile I	2021-03-13 12:02:51.332701
2	Principles of Distributed Systems	2021-03-13 12:03:14.352969
3	Web & Mobile II	2021-03-13 12:03:14.352969
4	Software Design & Patterns	2021-03-13 12:06:47.779333
5	Introduction to Computer Networks	2021-03-13 12:09:35.379228
6	Physics I	2021-03-15 14:28:55.843686
7	Cyber Security Fundamentals	2021-03-15 14:28:55.843686
8	Linear Algebra	2021-03-15 19:17:11.750004
\.


--
-- Data for Name: enrollment; Type: TABLE DATA; Schema: public; Owner: terlanismayilsoy
--

COPY public.enrollment (user_id, course_id) FROM stdin;
46	3
46	4
47	3
\.


--
-- Data for Name: users; Type: TABLE DATA; Schema: public; Owner: terlanismayilsoy
--

COPY public.users (user_id, password, email, created_on, first_name, last_name, age, city, country, gender) FROM stdin;
46	e3e80a49af50d8b399ec866345933d89ff25f20b21c59a16609eddb91f437aab5dbf8e98016248a147bd5083fea99a0862edf0ebaec2b6950b942ace93fdeeec	tarlan@ada.edu.az	2021-03-15 23:17:37.051084	Tarlan	Ismayilsoy	22	Baku	Azerbaijan	male
47	a2dbdd77693c179d4d9c50cd86e57f33f2463715e8402b521c6c59dc16906d1f8ecbe4b4faee4bc30b73b36543a118dd574f7bc3850eb68bd4554ae9ae5beae1	jon_snow@game.of.thrones	2021-03-15 23:19:21.583599	Jon	Snow	25	Winterfell	Kingdom of the North	male
\.


--
-- Name: courses_course_id_seq; Type: SEQUENCE SET; Schema: public; Owner: terlanismayilsoy
--

SELECT pg_catalog.setval('public.courses_course_id_seq', 8, true);


--
-- Name: users_user_id_seq; Type: SEQUENCE SET; Schema: public; Owner: terlanismayilsoy
--

SELECT pg_catalog.setval('public.users_user_id_seq', 47, true);


--
-- Name: courses courses_course_name_key; Type: CONSTRAINT; Schema: public; Owner: terlanismayilsoy
--

ALTER TABLE ONLY public.courses
    ADD CONSTRAINT courses_course_name_key UNIQUE (course_name);


--
-- Name: courses courses_pkey; Type: CONSTRAINT; Schema: public; Owner: terlanismayilsoy
--

ALTER TABLE ONLY public.courses
    ADD CONSTRAINT courses_pkey PRIMARY KEY (course_id);


--
-- Name: enrollment uq_enrollment; Type: CONSTRAINT; Schema: public; Owner: terlanismayilsoy
--

ALTER TABLE ONLY public.enrollment
    ADD CONSTRAINT uq_enrollment UNIQUE (user_id, course_id);


--
-- Name: users users_pkey; Type: CONSTRAINT; Schema: public; Owner: terlanismayilsoy
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_pkey PRIMARY KEY (user_id);


--
-- Name: users_email_uindex; Type: INDEX; Schema: public; Owner: terlanismayilsoy
--

CREATE UNIQUE INDEX users_email_uindex ON public.users USING btree (email);


--
-- PostgreSQL database dump complete
--

