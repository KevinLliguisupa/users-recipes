
CREATE SEQUENCE public.ingredient_ing_id_seq;

CREATE TABLE public.ingredient (
                ing_id INTEGER NOT NULL DEFAULT nextval('public.ingredient_ing_id_seq'),
                ing_name VARCHAR NOT NULL,
                ing_image VARCHAR NOT NULL,
                ing_state BOOLEAN NOT NULL,
                CONSTRAINT ingredient_pk PRIMARY KEY (ing_id)
);


ALTER SEQUENCE public.ingredient_ing_id_seq OWNED BY public.ingredient.ing_id;

CREATE SEQUENCE public.users_usr_id_seq;

CREATE TABLE public.users (
                usr_id INTEGER NOT NULL DEFAULT nextval('public.users_usr_id_seq'),
                usr_nickname VARCHAR NOT NULL,
                usr_email VARCHAR NOT NULL,
                usr_password VARCHAR NOT NULL,
                usr_avatar VARCHAR NOT NULL,
                usr_name VARCHAR NOT NULL,
                usr_state BOOLEAN NOT NULL,
                CONSTRAINT users_pk PRIMARY KEY (usr_id)
);


ALTER SEQUENCE public.users_usr_id_seq OWNED BY public.users.usr_id;

CREATE SEQUENCE public.recipe_rec_id_seq;

CREATE TABLE public.recipe (
                rec_id INTEGER NOT NULL DEFAULT nextval('public.recipe_rec_id_seq'),
                usr_id INTEGER NOT NULL,
                rec_name VARCHAR NOT NULL,
                rec_description VARCHAR NOT NULL,
                rec_duration NUMERIC(5,2) NOT NULL,
                rec_image VARCHAR NOT NULL,
                rec_state BOOLEAN NOT NULL,
                CONSTRAINT recipe_pk PRIMARY KEY (rec_id)
);


ALTER SEQUENCE public.recipe_rec_id_seq OWNED BY public.recipe.rec_id;

CREATE SEQUENCE public.ing_rec_ing_rec_id_seq;

CREATE TABLE public.ing_rec (
                ing_rec_id INTEGER NOT NULL DEFAULT nextval('public.ing_rec_ing_rec_id_seq'),
                rec_id INTEGER NOT NULL,
                ing_id INTEGER NOT NULL,
                ing_rec_quantity NUMERIC(5,2) NOT NULL,
                ing_rec_state BOOLEAN NOT NULL,
                CONSTRAINT ing_rec_pk PRIMARY KEY (ing_rec_id)
);


ALTER SEQUENCE public.ing_rec_ing_rec_id_seq OWNED BY public.ing_rec.ing_rec_id;

ALTER TABLE public.ing_rec ADD CONSTRAINT ingredient_ing_rec_fk
FOREIGN KEY (ing_id)
REFERENCES public.ingredient (ing_id)
ON DELETE NO ACTION
ON UPDATE CASCADE
NOT DEFERRABLE;

ALTER TABLE public.recipe ADD CONSTRAINT user_recipe_fk
FOREIGN KEY (usr_id)
REFERENCES public.users (usr_id)
ON DELETE NO ACTION
ON UPDATE CASCADE
NOT DEFERRABLE;

ALTER TABLE public.ing_rec ADD CONSTRAINT recipe_ing_rec_fk
FOREIGN KEY (rec_id)
REFERENCES public.recipe (rec_id)
ON DELETE NO ACTION
ON UPDATE CASCADE
NOT DEFERRABLE;