--
-- PostgreSQL database dump
--

-- Dumped from database version 11.2
-- Dumped by pg_dump version 11.2

-- Started on 2019-03-22 10:00:19

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
-- TOC entry 5 (class 2615 OID 16394)
-- Name: scidap; Type: SCHEMA; Schema: -; Owner: postgres
--

CREATE SCHEMA scidap;


ALTER SCHEMA scidap OWNER TO postgres;

--
-- TOC entry 200 (class 1259 OID 16504)
-- Name: hibernate_sequence; Type: SEQUENCE; Schema: scidap; Owner: postgres
--

CREATE SEQUENCE scidap.hibernate_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE scidap.hibernate_sequence OWNER TO postgres;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 197 (class 1259 OID 16423)
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
-- TOC entry 198 (class 1259 OID 16431)
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
-- TOC entry 2849 (class 0 OID 0)
-- Dependencies: 198
-- Name: COLUMN merchant_menu_details.item_meal_type; Type: COMMENT; Schema: scidap; Owner: postgres
--

COMMENT ON COLUMN scidap.merchant_menu_details.item_meal_type IS 'Veg/NonVeg';


--
-- TOC entry 201 (class 1259 OID 16506)
-- Name: order_details; Type: TABLE; Schema: scidap; Owner: postgres
--

CREATE TABLE scidap.order_details (
    order_id integer NOT NULL,
    merchant_id integer NOT NULL,
    item_id integer NOT NULL,
    order_status character varying(100),
    order_created_time timestamp without time zone,
    order_created_by integer,
    order_modified_time timestamp without time zone,
    order_modified_by integer
);


ALTER TABLE scidap.order_details OWNER TO postgres;

--
-- TOC entry 196 (class 1259 OID 16398)
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
-- TOC entry 199 (class 1259 OID 16455)
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
-- TOC entry 2839 (class 0 OID 16423)
-- Dependencies: 197
-- Data for Name: merchant_details; Type: TABLE DATA; Schema: scidap; Owner: postgres
--

COPY scidap.merchant_details (merchant_id, merchant_name, merchant_location, merchant_email, merchant_contact_number, merchant_address, merchant_menu_id, created_by, creation_date, modified_date, modified_by) FROM stdin;
1	Corner Bakery	Kothapet	Cornerbakery@gmail.com	8886728845	hyderabad	12334	\N	\N	\N	\N
\.


--
-- TOC entry 2840 (class 0 OID 16431)
-- Dependencies: 198
-- Data for Name: merchant_menu_details; Type: TABLE DATA; Schema: scidap; Owner: postgres
--

COPY scidap.merchant_menu_details (item_id, merchant_id, item_category, item_name, item_cost, item_discount, item_selling_price, item_cuisine_type, item_description, item_image, item_meal_type, creation_date, created_by, modified_date, modified_by) FROM stdin;
2	1	Biryani	Mutton Biryani	250	0	250	Indian	Mutton Biryani	\N	Non-Veg	\N	\N	\N	\N
4	1	Biryani	Chicken Biryani	200	0	200	Indian	Chicken Biryani	\N	Non-Veg	\N	\N	\N	\N
\.


--
-- TOC entry 2843 (class 0 OID 16506)
-- Dependencies: 201
-- Data for Name: order_details; Type: TABLE DATA; Schema: scidap; Owner: postgres
--

COPY scidap.order_details (order_id, merchant_id, item_id, order_status, order_created_time, order_created_by, order_modified_time, order_modified_by) FROM stdin;
\.


--
-- TOC entry 2838 (class 0 OID 16398)
-- Dependencies: 196
-- Data for Name: qr_merchant_mapping; Type: TABLE DATA; Schema: scidap; Owner: postgres
--

COPY scidap.qr_merchant_mapping (qr_code, merchant_id, creation_date, created_by, modified_date, modified_by) FROM stdin;
\.


--
-- TOC entry 2841 (class 0 OID 16455)
-- Dependencies: 199
-- Data for Name: user_details; Type: TABLE DATA; Schema: scidap; Owner: postgres
--

COPY scidap.user_details (user_id, user_name, password, user_role) FROM stdin;
3	rohit.setty926@gmail.com	5f4dcc3b5aa765d61d8327deb882cf99	\N
\.


--
-- TOC entry 2850 (class 0 OID 0)
-- Dependencies: 200
-- Name: hibernate_sequence; Type: SEQUENCE SET; Schema: scidap; Owner: postgres
--

SELECT pg_catalog.setval('scidap.hibernate_sequence', 4, true);


--
-- TOC entry 2709 (class 2606 OID 16438)
-- Name: merchant_menu_details merchant_menu_details_pkey; Type: CONSTRAINT; Schema: scidap; Owner: postgres
--

ALTER TABLE ONLY scidap.merchant_menu_details
    ADD CONSTRAINT merchant_menu_details_pkey PRIMARY KEY (item_id);


--
-- TOC entry 2713 (class 2606 OID 16510)
-- Name: order_details order_details_pkey; Type: CONSTRAINT; Schema: scidap; Owner: postgres
--

ALTER TABLE ONLY scidap.order_details
    ADD CONSTRAINT order_details_pkey PRIMARY KEY (order_id);


--
-- TOC entry 2707 (class 2606 OID 16430)
-- Name: merchant_details pk_merchant_id; Type: CONSTRAINT; Schema: scidap; Owner: postgres
--

ALTER TABLE ONLY scidap.merchant_details
    ADD CONSTRAINT pk_merchant_id PRIMARY KEY (merchant_id);


--
-- TOC entry 2705 (class 2606 OID 16402)
-- Name: qr_merchant_mapping qr_merchant_mapping_pkey; Type: CONSTRAINT; Schema: scidap; Owner: postgres
--

ALTER TABLE ONLY scidap.qr_merchant_mapping
    ADD CONSTRAINT qr_merchant_mapping_pkey PRIMARY KEY (qr_code);


--
-- TOC entry 2711 (class 2606 OID 16462)
-- Name: user_details user_details_pkey; Type: CONSTRAINT; Schema: scidap; Owner: postgres
--

ALTER TABLE ONLY scidap.user_details
    ADD CONSTRAINT user_details_pkey PRIMARY KEY (user_id);


--
-- TOC entry 2714 (class 2606 OID 16439)
-- Name: merchant_menu_details fk_merchant_id; Type: FK CONSTRAINT; Schema: scidap; Owner: postgres
--

ALTER TABLE ONLY scidap.merchant_menu_details
    ADD CONSTRAINT fk_merchant_id FOREIGN KEY (merchant_id) REFERENCES scidap.merchant_details(merchant_id);


--
-- TOC entry 2716 (class 2606 OID 16516)
-- Name: order_details order_details_item_id_fkey; Type: FK CONSTRAINT; Schema: scidap; Owner: postgres
--

ALTER TABLE ONLY scidap.order_details
    ADD CONSTRAINT order_details_item_id_fkey FOREIGN KEY (item_id) REFERENCES scidap.merchant_menu_details(item_id);


--
-- TOC entry 2715 (class 2606 OID 16511)
-- Name: order_details order_details_merchant_id_fkey; Type: FK CONSTRAINT; Schema: scidap; Owner: postgres
--

ALTER TABLE ONLY scidap.order_details
    ADD CONSTRAINT order_details_merchant_id_fkey FOREIGN KEY (merchant_id) REFERENCES scidap.merchant_details(merchant_id);


-- Completed on 2019-03-22 10:00:19

--
-- PostgreSQL database dump complete
--

