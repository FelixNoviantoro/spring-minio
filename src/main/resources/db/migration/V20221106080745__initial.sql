CREATE TABLE IF NOT EXISTS public.orders (
	id serial NOT NULL,
	customer_id int4 NOT NULL,
	product varchar(255) NOT NULL,
	quantity int4 NOT NULL,
	CONSTRAINT orders_pkey PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS public.customers (
	id serial NOT NULL,
	firstname varchar(100) NOT NULL,
	lastname varchar(100) NULL,
	address varchar(255) NULL,
	city varchar(50) NULL,
	imageurl varchar NULL,
	CONSTRAINT customers_pkey PRIMARY KEY (id)
);