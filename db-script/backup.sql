--
-- PostgreSQL database dump
--

-- Dumped from database version 15.2 (Debian 15.2-1.pgdg110+1)
-- Dumped by pg_dump version 15.2 (Debian 15.2-1.pgdg110+1)

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
-- Name: cama; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.cama (
    id integer NOT NULL,
    numero integer,
    estado character varying(50),
    id_habitacion integer
);


ALTER TABLE public.cama OWNER TO postgres;

--
-- Name: cama_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.cama_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.cama_id_seq OWNER TO postgres;

--
-- Name: cama_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.cama_id_seq OWNED BY public.cama.id;


--
-- Name: habitacion; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.habitacion (
    id integer NOT NULL,
    numero integer,
    id_ubicacion integer
);


ALTER TABLE public.habitacion OWNER TO postgres;

--
-- Name: habitacion_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.habitacion_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.habitacion_id_seq OWNER TO postgres;

--
-- Name: habitacion_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.habitacion_id_seq OWNED BY public.habitacion.id;


--
-- Name: internacion; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.internacion (
    id integer NOT NULL,
    paciente character varying(100),
    fecha date,
    diagnostico character varying(100),
    id_cama integer
);


ALTER TABLE public.internacion OWNER TO postgres;

--
-- Name: internacion_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.internacion_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.internacion_id_seq OWNER TO postgres;

--
-- Name: internacion_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.internacion_id_seq OWNED BY public.internacion.id;


--
-- Name: ubicacion; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.ubicacion (
    id integer NOT NULL,
    hospital character varying(100),
    nombre character varying(100)
);


ALTER TABLE public.ubicacion OWNER TO postgres;

--
-- Name: ubicacion_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.ubicacion_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.ubicacion_id_seq OWNER TO postgres;

--
-- Name: ubicacion_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.ubicacion_id_seq OWNED BY public.ubicacion.id;


--
-- Name: cama id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.cama ALTER COLUMN id SET DEFAULT nextval('public.cama_id_seq'::regclass);


--
-- Name: habitacion id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.habitacion ALTER COLUMN id SET DEFAULT nextval('public.habitacion_id_seq'::regclass);


--
-- Name: internacion id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.internacion ALTER COLUMN id SET DEFAULT nextval('public.internacion_id_seq'::regclass);


--
-- Name: ubicacion id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.ubicacion ALTER COLUMN id SET DEFAULT nextval('public.ubicacion_id_seq'::regclass);


--
-- Data for Name: cama; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.cama (id, numero, estado, id_habitacion) FROM stdin;
1	102	Libre	2
2	101	Ocupada	3
\.


--
-- Data for Name: habitacion; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.habitacion (id, numero, id_ubicacion) FROM stdin;
1	102	1
2	103	1
3	104	4
\.


--
-- Data for Name: internacion; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.internacion (id, paciente, fecha, diagnostico, id_cama) FROM stdin;
1	Cristian Quevedo	2023-09-14	Dolor de cabeza	1
\.


--
-- Data for Name: ubicacion; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.ubicacion (id, hospital, nombre) FROM stdin;
1	Hospital de prueba	Ciudad de La Rioja
4	Hospital 2	Ciudad de Catamarca
\.


--
-- Name: cama_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.cama_id_seq', 2, true);


--
-- Name: habitacion_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.habitacion_id_seq', 3, true);


--
-- Name: internacion_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.internacion_id_seq', 1, true);


--
-- Name: ubicacion_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.ubicacion_id_seq', 4, true);


--
-- Name: cama cama_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.cama
    ADD CONSTRAINT cama_pkey PRIMARY KEY (id);


--
-- Name: habitacion habitacion_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.habitacion
    ADD CONSTRAINT habitacion_pkey PRIMARY KEY (id);


--
-- Name: internacion internacion_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.internacion
    ADD CONSTRAINT internacion_pkey PRIMARY KEY (id);


--
-- Name: ubicacion ubicacion_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.ubicacion
    ADD CONSTRAINT ubicacion_pkey PRIMARY KEY (id);


--
-- Name: cama cama_habitacion_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.cama
    ADD CONSTRAINT cama_habitacion_fkey FOREIGN KEY (id_habitacion) REFERENCES public.habitacion(id);


--
-- Name: habitacion habitacion_ubicacion_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.habitacion
    ADD CONSTRAINT habitacion_ubicacion_fkey FOREIGN KEY (id_ubicacion) REFERENCES public.ubicacion(id);


--
-- Name: internacion internacion_cama_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.internacion
    ADD CONSTRAINT internacion_cama_fkey FOREIGN KEY (id_cama) REFERENCES public.cama(id);


--
-- PostgreSQL database dump complete
--

