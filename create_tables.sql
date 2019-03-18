--
-- PostgreSQL database dump
--

-- Dumped from database version 11.2
-- Dumped by pg_dump version 11.2

-- Started on 2019-03-18 16:07:22

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 6 (class 2615 OID 16394)
-- Name: scidap; Type: SCHEMA; Schema: -; Owner: postgres
--

CREATE SCHEMA scidap;


ALTER SCHEMA scidap OWNER TO postgres;

--
-- TOC entry 203 (class 1259 OID 16466)
-- Name: hibernate_sequence; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.hibernate_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.hibernate_sequence OWNER TO postgres;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 201 (class 1259 OID 16446)
-- Name: merchant_details; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.merchant_details (
    merchant_id bigint NOT NULL,
    merchant_address character varying(255),
    merchant_contact_number character varying(255),
    creation_date timestamp without time zone,
    merchant_email character varying(255),
    merchant_location character varying(255),
    merchant_menu_id character varying(255),
    modified_date timestamp without time zone,
    merchant_name character varying(255)
);


ALTER TABLE public.merchant_details OWNER TO postgres;

--
-- TOC entry 200 (class 1259 OID 16444)
-- Name: merchant_details_merchant_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.merchant_details_merchant_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.merchant_details_merchant_id_seq OWNER TO postgres;

--
-- TOC entry 2877 (class 0 OID 0)
-- Dependencies: 200
-- Name: merchant_details_merchant_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.merchant_details_merchant_id_seq OWNED BY public.merchant_details.merchant_id;


--
-- TOC entry 207 (class 1259 OID 16494)
-- Name: merchant_menu_details; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.merchant_menu_details (
    item_id bigint NOT NULL,
    item_category character varying(255),
    item_cost double precision,
    created_by character varying(255),
    creation_date timestamp without time zone,
    item_cuisine_type character varying(255),
    item_description character varying(255),
    item_discount double precision,
    item_image character varying(255),
    item_meal_type character varying(255),
    merchant_id bigint,
    modified_by character varying(255),
    modified_date timestamp without time zone,
    item_name character varying(255),
    item_selling_price double precision
);


ALTER TABLE public.merchant_menu_details OWNER TO postgres;

--
-- TOC entry 206 (class 1259 OID 16492)
-- Name: merchant_menu_details_item_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.merchant_menu_details_item_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.merchant_menu_details_item_id_seq OWNER TO postgres;

--
-- TOC entry 2878 (class 0 OID 0)
-- Dependencies: 206
-- Name: merchant_menu_details_item_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.merchant_menu_details_item_id_seq OWNED BY public.merchant_menu_details.item_id;


--
-- TOC entry 205 (class 1259 OID 16476)
-- Name: user_details; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.user_details (
    user_id bigint NOT NULL,
    password character varying(255),
    user_role character varying(255),
    user_name character varying(255)
);


ALTER TABLE public.user_details OWNER TO postgres;

--
-- TOC entry 204 (class 1259 OID 16474)
-- Name: user_details_user_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.user_details_user_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.user_details_user_id_seq OWNER TO postgres;

--
-- TOC entry 2879 (class 0 OID 0)
-- Dependencies: 204
-- Name: user_details_user_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.user_details_user_id_seq OWNED BY public.user_details.user_id;


--
-- TOC entry 198 (class 1259 OID 16423)
-- Name: merchant_details; Type: TABLE; Schema: scidap; Owner: postgres
--

CREATE TABLE scidap.merchant_details (
    merchant_id integer NOT NULL,
    merchant_name character varying(255) NOT NULL,
    merchant_location character varying(100),
    merchant_email character varying(100),
    merchant_contact_number character varying(20),
    merchant_address text,
    merchant_menu_id integer,
    created_by character varying(100),
    creation_date timestamp without time zone,
    modified_date timestamp without time zone,
    modified_by character varying(100)
);


ALTER TABLE scidap.merchant_details OWNER TO postgres;

--
-- TOC entry 199 (class 1259 OID 16431)
-- Name: merchant_menu_details; Type: TABLE; Schema: scidap; Owner: postgres
--

CREATE TABLE scidap.merchant_menu_details (
    item_id integer NOT NULL,
    merchant_id integer,
    item_category character varying(100),
    item_name text,
    item_cost double precision,
    item_discount double precision,
    item_selling_price double precision,
    item_cuisine_type character varying(100),
    item_description text,
    item_image text,
    item_meal_type character varying(15),
    creation_date timestamp without time zone,
    created_by character varying(100),
    modified_date timestamp without time zone,
    modified_by character varying(100)
);


ALTER TABLE scidap.merchant_menu_details OWNER TO postgres;

--
-- TOC entry 2880 (class 0 OID 0)
-- Dependencies: 199
-- Name: COLUMN merchant_menu_details.item_meal_type; Type: COMMENT; Schema: scidap; Owner: postgres
--

COMMENT ON COLUMN scidap.merchant_menu_details.item_meal_type IS 'Veg/NonVeg';


--
-- TOC entry 197 (class 1259 OID 16398)
-- Name: qr_merchant_mapping; Type: TABLE; Schema: scidap; Owner: postgres
--

CREATE TABLE scidap.qr_merchant_mapping (
    qr_code bigint NOT NULL,
    merchant_id integer NOT NULL,
    creation_date timestamp without time zone,
    created_by character varying(100),
    modified_date timestamp without time zone,
    modified_by character varying(100)
);


ALTER TABLE scidap.qr_merchant_mapping OWNER TO postgres;

--
-- TOC entry 202 (class 1259 OID 16455)
-- Name: user_details; Type: TABLE; Schema: scidap; Owner: postgres
--

CREATE TABLE scidap.user_details (
    user_id integer NOT NULL,
    user_name character varying(100) NOT NULL,
    password text NOT NULL,
    user_role character varying(30)
);


ALTER TABLE scidap.user_details OWNER TO postgres;

--
-- TOC entry 2722 (class 2604 OID 16449)
-- Name: merchant_details merchant_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.merchant_details ALTER COLUMN merchant_id SET DEFAULT nextval('public.merchant_details_merchant_id_seq'::regclass);


--
-- TOC entry 2724 (class 2604 OID 16497)
-- Name: merchant_menu_details item_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.merchant_menu_details ALTER COLUMN item_id SET DEFAULT nextval('public.merchant_menu_details_item_id_seq'::regclass);


--
-- TOC entry 2723 (class 2604 OID 16479)
-- Name: user_details user_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_details ALTER COLUMN user_id SET DEFAULT nextval('public.user_details_user_id_seq'::regclass);


--
-- TOC entry 2865 (class 0 OID 16446)
-- Dependencies: 201
-- Data for Name: merchant_details; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.merchant_details (merchant_id, merchant_address, merchant_contact_number, creation_date, merchant_email, merchant_location, merchant_menu_id, modified_date, merchant_name) FROM stdin;
1	hyderabad	8886728845	\N	karachibakery@gmail.com	Kothapet	12334	\N	karachi Bakery
\.


--
-- TOC entry 2871 (class 0 OID 16494)
-- Dependencies: 207
-- Data for Name: merchant_menu_details; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.merchant_menu_details (item_id, item_category, item_cost, created_by, creation_date, item_cuisine_type, item_description, item_discount, item_image, item_meal_type, merchant_id, modified_by, modified_date, item_name, item_selling_price) FROM stdin;
1	Biryani	200	\N	\N	Indian	Chicken Biryani	0	\N	Non-Veg	1	\N	\N	Chicken Biryani	200
\.


--
-- TOC entry 2869 (class 0 OID 16476)
-- Dependencies: 205
-- Data for Name: user_details; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.user_details (user_id, password, user_role, user_name) FROM stdin;
1	5f4dcc3b5aa765d61d8327deb882cf99	\N	rohit.setty926@gmail.com
\.


--
-- TOC entry 2862 (class 0 OID 16423)
-- Dependencies: 198
-- Data for Name: merchant_details; Type: TABLE DATA; Schema: scidap; Owner: postgres
--

COPY scidap.merchant_details (merchant_id, merchant_name, merchant_location, merchant_email, merchant_contact_number, merchant_address, merchant_menu_id, created_by, creation_date, modified_date, modified_by) FROM stdin;
\.


--
-- TOC entry 2863 (class 0 OID 16431)
-- Dependencies: 199
-- Data for Name: merchant_menu_details; Type: TABLE DATA; Schema: scidap; Owner: postgres
--

COPY scidap.merchant_menu_details (item_id, merchant_id, item_category, item_name, item_cost, item_discount, item_selling_price, item_cuisine_type, item_description, item_image, item_meal_type, creation_date, created_by, modified_date, modified_by) FROM stdin;
\.


--
-- TOC entry 2861 (class 0 OID 16398)
-- Dependencies: 197
-- Data for Name: qr_merchant_mapping; Type: TABLE DATA; Schema: scidap; Owner: postgres
--

COPY scidap.qr_merchant_mapping (qr_code, merchant_id, creation_date, created_by, modified_date, modified_by) FROM stdin;
\.


--
-- TOC entry 2866 (class 0 OID 16455)
-- Dependencies: 202
-- Data for Name: user_details; Type: TABLE DATA; Schema: scidap; Owner: postgres
--

COPY scidap.user_details (user_id, user_name, password, user_role) FROM stdin;
\.


--
-- TOC entry 2881 (class 0 OID 0)
-- Dependencies: 203
-- Name: hibernate_sequence; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.hibernate_sequence', 2, true);


--
-- TOC entry 2882 (class 0 OID 0)
-- Dependencies: 200
-- Name: merchant_details_merchant_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.merchant_details_merchant_id_seq', 1, true);


--
-- TOC entry 2883 (class 0 OID 0)
-- Dependencies: 206
-- Name: merchant_menu_details_item_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.merchant_menu_details_item_id_seq', 1, true);


--
-- TOC entry 2884 (class 0 OID 0)
-- Dependencies: 204
-- Name: user_details_user_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.user_details_user_id_seq', 1, true);


--
-- TOC entry 2732 (class 2606 OID 16454)
-- Name: merchant_details merchant_details_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.merchant_details
    ADD CONSTRAINT merchant_details_pkey PRIMARY KEY (merchant_id);


--
-- TOC entry 2738 (class 2606 OID 16502)
-- Name: merchant_menu_details merchant_menu_details_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.merchant_menu_details
    ADD CONSTRAINT merchant_menu_details_pkey PRIMARY KEY (item_id);


--
-- TOC entry 2736 (class 2606 OID 16484)
-- Name: user_details user_details_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_details
    ADD CONSTRAINT user_details_pkey PRIMARY KEY (user_id);


--
-- TOC entry 2730 (class 2606 OID 16438)
-- Name: merchant_menu_details merchant_menu_details_pkey; Type: CONSTRAINT; Schema: scidap; Owner: postgres
--

ALTER TABLE ONLY scidap.merchant_menu_details
    ADD CONSTRAINT merchant_menu_details_pkey PRIMARY KEY (item_id);


--
-- TOC entry 2728 (class 2606 OID 16430)
-- Name: merchant_details pk_merchant_id; Type: CONSTRAINT; Schema: scidap; Owner: postgres
--

ALTER TABLE ONLY scidap.merchant_details
    ADD CONSTRAINT pk_merchant_id PRIMARY KEY (merchant_id);


--
-- TOC entry 2726 (class 2606 OID 16402)
-- Name: qr_merchant_mapping qr_merchant_mapping_pkey; Type: CONSTRAINT; Schema: scidap; Owner: postgres
--

ALTER TABLE ONLY scidap.qr_merchant_mapping
    ADD CONSTRAINT qr_merchant_mapping_pkey PRIMARY KEY (qr_code);


--
-- TOC entry 2734 (class 2606 OID 16462)
-- Name: user_details user_details_pkey; Type: CONSTRAINT; Schema: scidap; Owner: postgres
--

ALTER TABLE ONLY scidap.user_details
    ADD CONSTRAINT user_details_pkey PRIMARY KEY (user_id);


--
-- TOC entry 2739 (class 2606 OID 16439)
-- Name: merchant_menu_details fk_merchant_id; Type: FK CONSTRAINT; Schema: scidap; Owner: postgres
--

ALTER TABLE ONLY scidap.merchant_menu_details
    ADD CONSTRAINT fk_merchant_id FOREIGN KEY (merchant_id) REFERENCES scidap.merchant_details(merchant_id);


-- Completed on 2019-03-18 16:07:22

--
-- PostgreSQL database dump complete
--

