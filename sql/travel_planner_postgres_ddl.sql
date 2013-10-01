-- Sequence: user_id_seq

-- DROP SEQUENCE user_id_seq;

CREATE SEQUENCE user_id_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 3
  CACHE 1;
ALTER TABLE user_id_seq
  OWNER TO svdiufdculjrdb;


--  OWNER TO postgres;  -- this is for local database, above owner is on HerokuPostgresql



-- Table: app_users

-- DROP TABLE app_users;

CREATE TABLE app_users
(
  user_id integer NOT NULL DEFAULT nextval('user_id_seq'::regclass),
  username character varying(20),
  password character varying(20),
  firstname character varying(40),
  middlename character varying(40),
  lastname character varying(40),
  address character varying(60),
  phone character varying(20),
  email character varying(40),
  create_date_time timestamp with time zone NOT NULL DEFAULT now(),
  CONSTRAINT app_users_pkey PRIMARY KEY (user_id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE app_users
  OWNER TO svdiufdculjrdb;
  
  
  --  OWNER TO postgres;  -- this is for local database, above owner is on HerokuPostgresql

  
  
  
  -- Sequence: list_id_seq

-- DROP SEQUENCE list_id_seq;

CREATE SEQUENCE list_id_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 4
  CACHE 1;
ALTER TABLE list_id_seq
  OWNER TO svdiufdculjrdb;



  --  OWNER TO postgres;  -- this is for local database, above owner is on HerokuPostgresql
  
  
  

-- Table: favorite_lists

-- DROP TABLE favorite_lists;

CREATE TABLE favorite_lists
(
  list_id integer NOT NULL DEFAULT nextval('list_id_seq'::regclass),
  fav_list_name character varying(45),
  fav_list_type character varying(45),
  user_id integer,
  create_date_time timestamp with time zone NOT NULL DEFAULT now(),
  CONSTRAINT favorite_lists_pkey PRIMARY KEY (list_id),
  CONSTRAINT favorite_lists_user_id_fkey FOREIGN KEY (user_id)
      REFERENCES app_users (user_id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE favorite_lists
  OWNER TO svdiufdculjrdb;

  --  OWNER TO postgres;  -- this is for local database, above owner is on HerokuPostgresql
  
  
-- Index: fki_user_id

-- DROP INDEX fki_user_id;

CREATE INDEX fki_user_id
  ON favorite_lists
  USING btree
  (user_id);


  
  
  -- Sequence: item_id_seq

-- DROP SEQUENCE item_id_seq;

CREATE SEQUENCE item_id_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 3
  CACHE 1;
ALTER TABLE item_id_seq
  OWNER TO svdiufdculjrdb;

  --  OWNER TO postgres;  -- this is for local database, above owner is on HerokuPostgresql
  
  
  
  
  
-- Table: favorite_items

-- DROP TABLE favorite_items;

CREATE TABLE favorite_items
(
  item_id integer NOT NULL DEFAULT nextval('item_id_seq'::regclass),
  fav_item_name character varying(45),
  fav_item_type character varying(45),
  location_description character varying(125),
  address character varying(45),
  city character varying(45),
  state character varying(45),
  country character varying(45),
  zip character varying(20),
  phone character varying(20),
  notes character varying(125),
  list_id integer,
  create_date_time timestamp with time zone NOT NULL DEFAULT now(),
  CONSTRAINT favorite_items_pkey PRIMARY KEY (item_id),
  CONSTRAINT favorite_items_list_id_key FOREIGN KEY (list_id)
      REFERENCES favorite_lists (list_id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE favorite_items
  OWNER TO svdiufdculjrdb;

  --  OWNER TO postgres;  -- this is for local database, above owner is on HerokuPostgresql
  
  
  

-- Index: fki_favorite_items_list_id_key

-- DROP INDEX fki_favorite_items_list_id_key;

CREATE INDEX fki_favorite_items_list_id_key
  ON favorite_items
  USING btree
  (list_id);


  
  
  
-- Sequence: plan_id_seq

-- DROP SEQUENCE plan_id_seq;

CREATE SEQUENCE plan_id_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 3
  CACHE 1;
ALTER TABLE plan_id_seq
  OWNER TO svdiufdculjrdb;

  --  OWNER TO postgres;  -- this is for local database, above owner is on HerokuPostgresql
  
  
  
  
-- Table: travel_plans

-- DROP TABLE travel_plans;

CREATE TABLE travel_plans
(
  plan_id integer NOT NULL DEFAULT nextval('plan_id_seq'::regclass),
  trav_plan_name character varying(45),
  trav_plan_description character varying(125),
  trav_plan_details text,
  user_id integer,
  create_date_time timestamp with time zone NOT NULL DEFAULT now(),
  CONSTRAINT travel_plans_pkey PRIMARY KEY (plan_id),
  CONSTRAINT travel_plans_user_id_key FOREIGN KEY (user_id)
      REFERENCES app_users (user_id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE travel_plans
  OWNER TO svdiufdculjrdb;

  --  OWNER TO postgres;  -- this is for local database, above owner is on HerokuPostgresql
  
  
  

-- Index: fki_travel_plans_user_id_key

-- DROP INDEX fki_travel_plans_user_id_key;

CREATE INDEX fki_travel_plans_user_id_key
  ON travel_plans
  USING btree
  (user_id);

