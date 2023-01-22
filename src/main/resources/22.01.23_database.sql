PGDMP     -    3                 {            hpotions    9.6.24    9.6.24 '    �           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                       false            �           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                       false            �           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                       false                        2615    2200    public    SCHEMA        CREATE SCHEMA public;
    DROP SCHEMA public;
             postgres    false            �           0    0    SCHEMA public    COMMENT     6   COMMENT ON SCHEMA public IS 'standard public schema';
                  postgres    false    3            �            1259    16440    hibernate_sequence    SEQUENCE     {   CREATE SEQUENCE public.hibernate_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 )   DROP SEQUENCE public.hibernate_sequence;
       public       postgres    false    3            �            1259    16452 
   ingredient    TABLE     \   CREATE TABLE public.ingredient (
    id bigint NOT NULL,
    name character varying(255)
);
    DROP TABLE public.ingredient;
       public         postgres    false    3            �            1259    16457    potion    TABLE     �   CREATE TABLE public.potion (
    id bigint NOT NULL,
    brewing_status integer,
    name character varying(255),
    brewer_student_id bigint,
    recipe_id bigint
);
    DROP TABLE public.potion;
       public         postgres    false    3            �            1259    16462    potion_ingredients    TABLE     n   CREATE TABLE public.potion_ingredients (
    potion_id bigint NOT NULL,
    ingredients_id bigint NOT NULL
);
 &   DROP TABLE public.potion_ingredients;
       public         postgres    false    3            �            1259    16465    recipe    TABLE     o   CREATE TABLE public.recipe (
    id bigint NOT NULL,
    name character varying(255),
    student_id bigint
);
    DROP TABLE public.recipe;
       public         postgres    false    3            �            1259    16470    recipe_ingredients    TABLE     n   CREATE TABLE public.recipe_ingredients (
    recipe_id bigint NOT NULL,
    ingredients_id bigint NOT NULL
);
 &   DROP TABLE public.recipe_ingredients;
       public         postgres    false    3            �            1259    16423    room    TABLE     K   CREATE TABLE public.room (
    id bigint NOT NULL,
    capacity integer
);
    DROP TABLE public.room;
       public         postgres    false    3            �            1259    16428    room_residents    TABLE     f   CREATE TABLE public.room_residents (
    room_id bigint NOT NULL,
    residents_id bigint NOT NULL
);
 "   DROP TABLE public.room_residents;
       public         postgres    false    3            �            1259    16433    student    TABLE     �   CREATE TABLE public.student (
    id bigint NOT NULL,
    house_type integer,
    name character varying(255),
    pet_type integer
);
    DROP TABLE public.student;
       public         postgres    false    3            �           0    0    hibernate_sequence    SEQUENCE SET     A   SELECT pg_catalog.setval('public.hibernate_sequence', 80, true);
            public       postgres    false    188            }          0    16452 
   ingredient 
   TABLE DATA               .   COPY public.ingredient (id, name) FROM stdin;
    public       postgres    false    189   �+       ~          0    16457    potion 
   TABLE DATA               X   COPY public.potion (id, brewing_status, name, brewer_student_id, recipe_id) FROM stdin;
    public       postgres    false    190   ,                 0    16462    potion_ingredients 
   TABLE DATA               G   COPY public.potion_ingredients (potion_id, ingredients_id) FROM stdin;
    public       postgres    false    191   n,       �          0    16465    recipe 
   TABLE DATA               6   COPY public.recipe (id, name, student_id) FROM stdin;
    public       postgres    false    192   �,       �          0    16470    recipe_ingredients 
   TABLE DATA               G   COPY public.recipe_ingredients (recipe_id, ingredients_id) FROM stdin;
    public       postgres    false    193   �,       y          0    16423    room 
   TABLE DATA               ,   COPY public.room (id, capacity) FROM stdin;
    public       postgres    false    185   �,       z          0    16428    room_residents 
   TABLE DATA               ?   COPY public.room_residents (room_id, residents_id) FROM stdin;
    public       postgres    false    186   /-       {          0    16433    student 
   TABLE DATA               A   COPY public.student (id, house_type, name, pet_type) FROM stdin;
    public       postgres    false    187   r-       �           2606    16456    ingredient ingredient_pkey 
   CONSTRAINT     X   ALTER TABLE ONLY public.ingredient
    ADD CONSTRAINT ingredient_pkey PRIMARY KEY (id);
 D   ALTER TABLE ONLY public.ingredient DROP CONSTRAINT ingredient_pkey;
       public         postgres    false    189    189            �           2606    16461    potion potion_pkey 
   CONSTRAINT     P   ALTER TABLE ONLY public.potion
    ADD CONSTRAINT potion_pkey PRIMARY KEY (id);
 <   ALTER TABLE ONLY public.potion DROP CONSTRAINT potion_pkey;
       public         postgres    false    190    190            �           2606    16469    recipe recipe_pkey 
   CONSTRAINT     P   ALTER TABLE ONLY public.recipe
    ADD CONSTRAINT recipe_pkey PRIMARY KEY (id);
 <   ALTER TABLE ONLY public.recipe DROP CONSTRAINT recipe_pkey;
       public         postgres    false    192    192            �           2606    16427    room room_pkey 
   CONSTRAINT     L   ALTER TABLE ONLY public.room
    ADD CONSTRAINT room_pkey PRIMARY KEY (id);
 8   ALTER TABLE ONLY public.room DROP CONSTRAINT room_pkey;
       public         postgres    false    185    185            �           2606    16432 "   room_residents room_residents_pkey 
   CONSTRAINT     s   ALTER TABLE ONLY public.room_residents
    ADD CONSTRAINT room_residents_pkey PRIMARY KEY (room_id, residents_id);
 L   ALTER TABLE ONLY public.room_residents DROP CONSTRAINT room_residents_pkey;
       public         postgres    false    186    186    186            �           2606    16437    student student_pkey 
   CONSTRAINT     R   ALTER TABLE ONLY public.student
    ADD CONSTRAINT student_pkey PRIMARY KEY (id);
 >   ALTER TABLE ONLY public.student DROP CONSTRAINT student_pkey;
       public         postgres    false    187    187            �           2606    16439 +   room_residents uk_p2dvre9hlcvlgwhpugbl537p3 
   CONSTRAINT     n   ALTER TABLE ONLY public.room_residents
    ADD CONSTRAINT uk_p2dvre9hlcvlgwhpugbl537p3 UNIQUE (residents_id);
 U   ALTER TABLE ONLY public.room_residents DROP CONSTRAINT uk_p2dvre9hlcvlgwhpugbl537p3;
       public         postgres    false    186    186                       2606    16493 "   recipe fk3rbjrtjbld5nat3ilc6ar5myp    FK CONSTRAINT     �   ALTER TABLE ONLY public.recipe
    ADD CONSTRAINT fk3rbjrtjbld5nat3ilc6ar5myp FOREIGN KEY (student_id) REFERENCES public.student(id);
 L   ALTER TABLE ONLY public.recipe DROP CONSTRAINT fk3rbjrtjbld5nat3ilc6ar5myp;
       public       postgres    false    192    187    2036            �           2606    16473 "   potion fk5alxs6090m52vuwbtowvfrw3m    FK CONSTRAINT     �   ALTER TABLE ONLY public.potion
    ADD CONSTRAINT fk5alxs6090m52vuwbtowvfrw3m FOREIGN KEY (brewer_student_id) REFERENCES public.student(id);
 L   ALTER TABLE ONLY public.potion DROP CONSTRAINT fk5alxs6090m52vuwbtowvfrw3m;
       public       postgres    false    190    187    2036            �           2606    16478 "   potion fke7ipbtypgop4r6vwd2ux0avrl    FK CONSTRAINT     �   ALTER TABLE ONLY public.potion
    ADD CONSTRAINT fke7ipbtypgop4r6vwd2ux0avrl FOREIGN KEY (recipe_id) REFERENCES public.recipe(id);
 L   ALTER TABLE ONLY public.potion DROP CONSTRAINT fke7ipbtypgop4r6vwd2ux0avrl;
       public       postgres    false    192    190    2042                       2606    16498 .   recipe_ingredients fkg8ssng1dqiwgcptjptldels74    FK CONSTRAINT     �   ALTER TABLE ONLY public.recipe_ingredients
    ADD CONSTRAINT fkg8ssng1dqiwgcptjptldels74 FOREIGN KEY (ingredients_id) REFERENCES public.ingredient(id);
 X   ALTER TABLE ONLY public.recipe_ingredients DROP CONSTRAINT fkg8ssng1dqiwgcptjptldels74;
       public       postgres    false    189    193    2038                       2606    16503 .   recipe_ingredients fkhnsmvxdlwxqq6x2wbgnoef5gr    FK CONSTRAINT     �   ALTER TABLE ONLY public.recipe_ingredients
    ADD CONSTRAINT fkhnsmvxdlwxqq6x2wbgnoef5gr FOREIGN KEY (recipe_id) REFERENCES public.recipe(id);
 X   ALTER TABLE ONLY public.recipe_ingredients DROP CONSTRAINT fkhnsmvxdlwxqq6x2wbgnoef5gr;
       public       postgres    false    2042    192    193            �           2606    16447 *   room_residents fkkiu2ujmu2q0fs1sxyud060vme    FK CONSTRAINT     �   ALTER TABLE ONLY public.room_residents
    ADD CONSTRAINT fkkiu2ujmu2q0fs1sxyud060vme FOREIGN KEY (room_id) REFERENCES public.room(id);
 T   ALTER TABLE ONLY public.room_residents DROP CONSTRAINT fkkiu2ujmu2q0fs1sxyud060vme;
       public       postgres    false    186    185    2030                        2606    16488 .   potion_ingredients fkoiqs2fvuv19yuxuh2irflvb3t    FK CONSTRAINT     �   ALTER TABLE ONLY public.potion_ingredients
    ADD CONSTRAINT fkoiqs2fvuv19yuxuh2irflvb3t FOREIGN KEY (potion_id) REFERENCES public.potion(id);
 X   ALTER TABLE ONLY public.potion_ingredients DROP CONSTRAINT fkoiqs2fvuv19yuxuh2irflvb3t;
       public       postgres    false    190    191    2040            �           2606    16483 .   potion_ingredients fkpo1oi0ccrhfnsjcfbsto56rpb    FK CONSTRAINT     �   ALTER TABLE ONLY public.potion_ingredients
    ADD CONSTRAINT fkpo1oi0ccrhfnsjcfbsto56rpb FOREIGN KEY (ingredients_id) REFERENCES public.ingredient(id);
 X   ALTER TABLE ONLY public.potion_ingredients DROP CONSTRAINT fkpo1oi0ccrhfnsjcfbsto56rpb;
       public       postgres    false    2038    189    191            �           2606    16442 *   room_residents fkroev77gycjbit298knqeu0nsg    FK CONSTRAINT     �   ALTER TABLE ONLY public.room_residents
    ADD CONSTRAINT fkroev77gycjbit298knqeu0nsg FOREIGN KEY (residents_id) REFERENCES public.student(id);
 T   ALTER TABLE ONLY public.room_residents DROP CONSTRAINT fkroev77gycjbit298knqeu0nsg;
       public       postgres    false    186    187    2036            }   A   x�ȱ�@����O`:/
���+/��\�d&ژ�v��X+d,����?k@��B���      ~   E   x�U˱� ���?a	&`�9P']󊋒J�cg�@r�Ԡ귄O��O��j���f� =},R         E   x�5ʻ�0��Z7L��N�.��$Ҽ�`�XG�q�ą����o���·�fTÎC�߅���� �s�      �      x������ � �      �      x������ � �      y   "   x�34�4�24��\&f �DX �b���� X��      z   3   x�ȹ 0��&`��?G�F�h�!c|>���oQ�����=HΣ�      {   �   x�]��
� ��1EݍI�9���EI�����R���<�8B�2���L��SF���ʪ�Y�cYr5n��A��m��zM���ܗ�9�i
�s6%Lf	� 9I����N�@p@�B�B�B����������������������.�������V��,�_��1�?f2��Ek��J��     