--
-- PostgreSQL database dump
--

-- Dumped from database version 13.7
-- Dumped by pg_dump version 15.1

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

--
-- Name: public; Type: SCHEMA; Schema: -; Owner: scorpiondb
--

-- *not* creating schema, since initdb creates it


ALTER SCHEMA public OWNER TO scorpiondb;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: ing_rec; Type: TABLE; Schema: public; Owner: scorpiondb
--

CREATE TABLE public.ing_rec (
    ing_rec_id integer NOT NULL,
    rec_id integer NOT NULL,
    ing_id integer NOT NULL,
    ing_rec_quantity numeric(5,2) NOT NULL,
    ing_rec_state boolean NOT NULL
);


ALTER TABLE public.ing_rec OWNER TO scorpiondb;

--
-- Name: ing_rec_ing_rec_id_seq; Type: SEQUENCE; Schema: public; Owner: scorpiondb
--

CREATE SEQUENCE public.ing_rec_ing_rec_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.ing_rec_ing_rec_id_seq OWNER TO scorpiondb;

--
-- Name: ing_rec_ing_rec_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: scorpiondb
--

ALTER SEQUENCE public.ing_rec_ing_rec_id_seq OWNED BY public.ing_rec.ing_rec_id;


--
-- Name: ingredient; Type: TABLE; Schema: public; Owner: scorpiondb
--

CREATE TABLE public.ingredient (
    ing_id integer NOT NULL,
    ing_name character varying NOT NULL,
    ing_image character varying NOT NULL,
    ing_state boolean NOT NULL
);


ALTER TABLE public.ingredient OWNER TO scorpiondb;

--
-- Name: ingredient_ing_id_seq; Type: SEQUENCE; Schema: public; Owner: scorpiondb
--

CREATE SEQUENCE public.ingredient_ing_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.ingredient_ing_id_seq OWNER TO scorpiondb;

--
-- Name: ingredient_ing_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: scorpiondb
--

ALTER SEQUENCE public.ingredient_ing_id_seq OWNED BY public.ingredient.ing_id;


--
-- Name: recipe; Type: TABLE; Schema: public; Owner: scorpiondb
--

CREATE TABLE public.recipe (
    rec_id integer NOT NULL,
    usr_id integer NOT NULL,
    rec_name character varying NOT NULL,
    rec_description character varying NOT NULL,
    rec_duration numeric(5,2) NOT NULL,
    rec_image character varying NOT NULL,
    rec_state boolean NOT NULL
);


ALTER TABLE public.recipe OWNER TO scorpiondb;

--
-- Name: recipe_rec_id_seq; Type: SEQUENCE; Schema: public; Owner: scorpiondb
--

CREATE SEQUENCE public.recipe_rec_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.recipe_rec_id_seq OWNER TO scorpiondb;

--
-- Name: recipe_rec_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: scorpiondb
--

ALTER SEQUENCE public.recipe_rec_id_seq OWNED BY public.recipe.rec_id;


--
-- Name: user; Type: TABLE; Schema: public; Owner: scorpiondb
--

CREATE TABLE public."user" (
    usr_id integer NOT NULL,
    usr_nickname character varying NOT NULL,
    usr_email character varying NOT NULL,
    usr_password character varying NOT NULL,
    usr_avatar character varying NOT NULL,
    usr_name character varying NOT NULL,
    usr_state boolean NOT NULL
);


ALTER TABLE public."user" OWNER TO scorpiondb;

--
-- Name: users_usr_id_seq; Type: SEQUENCE; Schema: public; Owner: scorpiondb
--

CREATE SEQUENCE public.users_usr_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.users_usr_id_seq OWNER TO scorpiondb;

--
-- Name: users_usr_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: scorpiondb
--

ALTER SEQUENCE public.users_usr_id_seq OWNED BY public."user".usr_id;


--
-- Name: ing_rec ing_rec_id; Type: DEFAULT; Schema: public; Owner: scorpiondb
--

ALTER TABLE ONLY public.ing_rec ALTER COLUMN ing_rec_id SET DEFAULT nextval('public.ing_rec_ing_rec_id_seq'::regclass);


--
-- Name: ingredient ing_id; Type: DEFAULT; Schema: public; Owner: scorpiondb
--

ALTER TABLE ONLY public.ingredient ALTER COLUMN ing_id SET DEFAULT nextval('public.ingredient_ing_id_seq'::regclass);


--
-- Name: recipe rec_id; Type: DEFAULT; Schema: public; Owner: scorpiondb
--

ALTER TABLE ONLY public.recipe ALTER COLUMN rec_id SET DEFAULT nextval('public.recipe_rec_id_seq'::regclass);


--
-- Name: user usr_id; Type: DEFAULT; Schema: public; Owner: scorpiondb
--

ALTER TABLE ONLY public."user" ALTER COLUMN usr_id SET DEFAULT nextval('public.users_usr_id_seq'::regclass);


--
-- Data for Name: ing_rec; Type: TABLE DATA; Schema: public; Owner: scorpiondb
--

COPY public.ing_rec (ing_rec_id, rec_id, ing_id, ing_rec_quantity, ing_rec_state) FROM stdin;
\.


--
-- Data for Name: ingredient; Type: TABLE DATA; Schema: public; Owner: scorpiondb
--

COPY public.ingredient (ing_id, ing_name, ing_image, ing_state) FROM stdin;
\.


--
-- Data for Name: recipe; Type: TABLE DATA; Schema: public; Owner: scorpiondb
--

COPY public.recipe (rec_id, usr_id, rec_name, rec_description, rec_duration, rec_image, rec_state) FROM stdin;
\.


--
-- Data for Name: user; Type: TABLE DATA; Schema: public; Owner: scorpiondb
--

COPY public."user" (usr_id, usr_nickname, usr_email, usr_password, usr_avatar, usr_name, usr_state) FROM stdin;
1	KevinLliguisupa	kevinlliguisupa6@gmail.com	$2a$10$H5RKSe8AhRcpaSH2fKI75eS/JpAXL3wKh5LOxNlETrj903t/rXmNq	https://www.newsmangas.fr/wp-content/uploads/2022/12/SPOILER-1070-on-piece-420x280.png	Kevin Lliguisupa	t
\.


--
-- Name: ing_rec_ing_rec_id_seq; Type: SEQUENCE SET; Schema: public; Owner: scorpiondb
--

SELECT pg_catalog.setval('public.ing_rec_ing_rec_id_seq', 1, false);


--
-- Name: ingredient_ing_id_seq; Type: SEQUENCE SET; Schema: public; Owner: scorpiondb
--

SELECT pg_catalog.setval('public.ingredient_ing_id_seq', 1, false);


--
-- Name: recipe_rec_id_seq; Type: SEQUENCE SET; Schema: public; Owner: scorpiondb
--

SELECT pg_catalog.setval('public.recipe_rec_id_seq', 1, false);


--
-- Name: users_usr_id_seq; Type: SEQUENCE SET; Schema: public; Owner: scorpiondb
--

SELECT pg_catalog.setval('public.users_usr_id_seq', 1, true);


--
-- Name: ing_rec ing_rec_pk; Type: CONSTRAINT; Schema: public; Owner: scorpiondb
--

ALTER TABLE ONLY public.ing_rec
    ADD CONSTRAINT ing_rec_pk PRIMARY KEY (ing_rec_id);


--
-- Name: ingredient ingredient_pk; Type: CONSTRAINT; Schema: public; Owner: scorpiondb
--

ALTER TABLE ONLY public.ingredient
    ADD CONSTRAINT ingredient_pk PRIMARY KEY (ing_id);


--
-- Name: recipe recipe_pk; Type: CONSTRAINT; Schema: public; Owner: scorpiondb
--

ALTER TABLE ONLY public.recipe
    ADD CONSTRAINT recipe_pk PRIMARY KEY (rec_id);


--
-- Name: user users_pk; Type: CONSTRAINT; Schema: public; Owner: scorpiondb
--

ALTER TABLE ONLY public."user"
    ADD CONSTRAINT users_pk PRIMARY KEY (usr_id);


--
-- Name: ing_rec ingredient_ing_rec_fk; Type: FK CONSTRAINT; Schema: public; Owner: scorpiondb
--

ALTER TABLE ONLY public.ing_rec
    ADD CONSTRAINT ingredient_ing_rec_fk FOREIGN KEY (ing_id) REFERENCES public.ingredient(ing_id) ON UPDATE CASCADE;


--
-- Name: ing_rec recipe_ing_rec_fk; Type: FK CONSTRAINT; Schema: public; Owner: scorpiondb
--

ALTER TABLE ONLY public.ing_rec
    ADD CONSTRAINT recipe_ing_rec_fk FOREIGN KEY (rec_id) REFERENCES public.recipe(rec_id) ON UPDATE CASCADE;


--
-- Name: recipe user_recipe_fk; Type: FK CONSTRAINT; Schema: public; Owner: scorpiondb
--

ALTER TABLE ONLY public.recipe
    ADD CONSTRAINT user_recipe_fk FOREIGN KEY (usr_id) REFERENCES public."user"(usr_id) ON UPDATE CASCADE;


--
-- Name: SCHEMA public; Type: ACL; Schema: -; Owner: scorpiondb
--

REVOKE USAGE ON SCHEMA public FROM PUBLIC;
GRANT ALL ON SCHEMA public TO PUBLIC;


--
-- PostgreSQL database dump complete
--

