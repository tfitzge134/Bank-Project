PGDMP     /    1                y           Bank    13.2    13.2 =    �           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false            �           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false            �           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            �           1262    24658    Bank    DATABASE     Q   CREATE DATABASE "Bank" WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE = 'C';
    DROP DATABASE "Bank";
                postgres    false                        2615    25240    bank    SCHEMA        CREATE SCHEMA bank;
    DROP SCHEMA bank;
                postgres    false            �            1259    25373    account    TABLE     �  CREATE TABLE bank.account (
    id integer NOT NULL,
    accountnumber character varying(10),
    branchid integer NOT NULL,
    openingbalance double precision,
    balance double precision,
    opendingdate date,
    deposit double precision,
    withdrawl double precision,
    interestrate numeric,
    customerid integer NOT NULL,
    isactive boolean DEFAULT false,
    accounttype character varying
);
    DROP TABLE bank.account;
       bank         heap    postgres    false    5            �            1259    25212    address    TABLE     �   CREATE TABLE bank.address (
    addressid integer NOT NULL,
    address character varying(20),
    city character varying(20),
    state character varying(2),
    country character varying(20),
    personid integer NOT NULL
);
    DROP TABLE bank.address;
       bank         heap    postgres    false    5            �            1259    25208    address_addressid_seq    SEQUENCE     �   CREATE SEQUENCE bank.address_addressid_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 *   DROP SEQUENCE bank.address_addressid_seq;
       bank          postgres    false    205    5            �           0    0    address_addressid_seq    SEQUENCE OWNED BY     K   ALTER SEQUENCE bank.address_addressid_seq OWNED BY bank.address.addressid;
          bank          postgres    false    203            �            1259    25210    address_personid_seq    SEQUENCE     �   CREATE SEQUENCE bank.address_personid_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 )   DROP SEQUENCE bank.address_personid_seq;
       bank          postgres    false    5    205                        0    0    address_personid_seq    SEQUENCE OWNED BY     I   ALTER SEQUENCE bank.address_personid_seq OWNED BY bank.address.personid;
          bank          postgres    false    204            �            1259    25336    branch    TABLE       CREATE TABLE bank.branch (
    branchid integer NOT NULL,
    bname character varying(20),
    baddress character varying(20),
    bcity character varying(20),
    branchstate character varying(4),
    branchcountry character varying(10),
    branchnumber integer
);
    DROP TABLE bank.branch;
       bank         heap    postgres    false    5            �            1259    25334    branch_branchid_seq    SEQUENCE     �   CREATE SEQUENCE bank.branch_branchid_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 (   DROP SEQUENCE bank.branch_branchid_seq;
       bank          postgres    false    5    207                       0    0    branch_branchid_seq    SEQUENCE OWNED BY     G   ALTER SEQUENCE bank.branch_branchid_seq OWNED BY bank.branch.branchid;
          bank          postgres    false    206            �            1259    25367    checking_acnumber_seq    SEQUENCE     �   CREATE SEQUENCE bank.checking_acnumber_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 *   DROP SEQUENCE bank.checking_acnumber_seq;
       bank          postgres    false    5    211                       0    0    checking_acnumber_seq    SEQUENCE OWNED BY     D   ALTER SEQUENCE bank.checking_acnumber_seq OWNED BY bank.account.id;
          bank          postgres    false    208            �            1259    25369    checking_branchid_seq    SEQUENCE     �   CREATE SEQUENCE bank.checking_branchid_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 *   DROP SEQUENCE bank.checking_branchid_seq;
       bank          postgres    false    211    5                       0    0    checking_branchid_seq    SEQUENCE OWNED BY     J   ALTER SEQUENCE bank.checking_branchid_seq OWNED BY bank.account.branchid;
          bank          postgres    false    209            �            1259    25371    checking_customerid_seq    SEQUENCE     �   CREATE SEQUENCE bank.checking_customerid_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 ,   DROP SEQUENCE bank.checking_customerid_seq;
       bank          postgres    false    5    211                       0    0    checking_customerid_seq    SEQUENCE OWNED BY     N   ALTER SEQUENCE bank.checking_customerid_seq OWNED BY bank.account.customerid;
          bank          postgres    false    210            �            1259    25191    person    TABLE     j  CREATE TABLE bank.person (
    personid integer NOT NULL,
    firstname character varying(20),
    lastname character varying(20),
    email character varying(20),
    phonenumber character varying(14),
    occupation character varying(20),
    dob date,
    isemployee boolean NOT NULL,
    password character varying(20),
    customertype character varying
);
    DROP TABLE bank.person;
       bank         heap    postgres    false    5            �            1259    25189    person_personid_seq    SEQUENCE     �   CREATE SEQUENCE bank.person_personid_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 (   DROP SEQUENCE bank.person_personid_seq;
       bank          postgres    false    5    202                       0    0    person_personid_seq    SEQUENCE OWNED BY     G   ALTER SEQUENCE bank.person_personid_seq OWNED BY bank.person.personid;
          bank          postgres    false    201            �            1259    25427    transfer    TABLE     �   CREATE TABLE bank.transfer (
    id integer NOT NULL,
    dot date,
    sourceacc character varying(10),
    destinationacc character varying(10),
    transamount double precision,
    svid integer NOT NULL,
    ccid integer NOT NULL
);
    DROP TABLE bank.transfer;
       bank         heap    postgres    false    5            �            1259    25425    transfer_acnumber_seq    SEQUENCE     �   CREATE SEQUENCE bank.transfer_acnumber_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 *   DROP SEQUENCE bank.transfer_acnumber_seq;
       bank          postgres    false    5    215                       0    0    transfer_acnumber_seq    SEQUENCE OWNED BY     G   ALTER SEQUENCE bank.transfer_acnumber_seq OWNED BY bank.transfer.ccid;
          bank          postgres    false    214            �            1259    25423    transfer_acsavingid_seq    SEQUENCE     �   CREATE SEQUENCE bank.transfer_acsavingid_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 ,   DROP SEQUENCE bank.transfer_acsavingid_seq;
       bank          postgres    false    5    215                       0    0    transfer_acsavingid_seq    SEQUENCE OWNED BY     I   ALTER SEQUENCE bank.transfer_acsavingid_seq OWNED BY bank.transfer.svid;
          bank          postgres    false    213            �            1259    25421    transfer_transnumber_seq    SEQUENCE     �   CREATE SEQUENCE bank.transfer_transnumber_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 -   DROP SEQUENCE bank.transfer_transnumber_seq;
       bank          postgres    false    215    5                       0    0    transfer_transnumber_seq    SEQUENCE OWNED BY     H   ALTER SEQUENCE bank.transfer_transnumber_seq OWNED BY bank.transfer.id;
          bank          postgres    false    212            V           2604    25376 
   account id    DEFAULT     k   ALTER TABLE ONLY bank.account ALTER COLUMN id SET DEFAULT nextval('bank.checking_acnumber_seq'::regclass);
 7   ALTER TABLE bank.account ALTER COLUMN id DROP DEFAULT;
       bank          postgres    false    211    208    211            W           2604    25377    account branchid    DEFAULT     q   ALTER TABLE ONLY bank.account ALTER COLUMN branchid SET DEFAULT nextval('bank.checking_branchid_seq'::regclass);
 =   ALTER TABLE bank.account ALTER COLUMN branchid DROP DEFAULT;
       bank          postgres    false    211    209    211            X           2604    25378    account customerid    DEFAULT     u   ALTER TABLE ONLY bank.account ALTER COLUMN customerid SET DEFAULT nextval('bank.checking_customerid_seq'::regclass);
 ?   ALTER TABLE bank.account ALTER COLUMN customerid DROP DEFAULT;
       bank          postgres    false    211    210    211            S           2604    25215    address addressid    DEFAULT     r   ALTER TABLE ONLY bank.address ALTER COLUMN addressid SET DEFAULT nextval('bank.address_addressid_seq'::regclass);
 >   ALTER TABLE bank.address ALTER COLUMN addressid DROP DEFAULT;
       bank          postgres    false    205    203    205            T           2604    25216    address personid    DEFAULT     p   ALTER TABLE ONLY bank.address ALTER COLUMN personid SET DEFAULT nextval('bank.address_personid_seq'::regclass);
 =   ALTER TABLE bank.address ALTER COLUMN personid DROP DEFAULT;
       bank          postgres    false    205    204    205            U           2604    25339    branch branchid    DEFAULT     n   ALTER TABLE ONLY bank.branch ALTER COLUMN branchid SET DEFAULT nextval('bank.branch_branchid_seq'::regclass);
 <   ALTER TABLE bank.branch ALTER COLUMN branchid DROP DEFAULT;
       bank          postgres    false    206    207    207            R           2604    25194    person personid    DEFAULT     n   ALTER TABLE ONLY bank.person ALTER COLUMN personid SET DEFAULT nextval('bank.person_personid_seq'::regclass);
 <   ALTER TABLE bank.person ALTER COLUMN personid DROP DEFAULT;
       bank          postgres    false    202    201    202            Z           2604    25430    transfer id    DEFAULT     o   ALTER TABLE ONLY bank.transfer ALTER COLUMN id SET DEFAULT nextval('bank.transfer_transnumber_seq'::regclass);
 8   ALTER TABLE bank.transfer ALTER COLUMN id DROP DEFAULT;
       bank          postgres    false    215    212    215            [           2604    25431    transfer svid    DEFAULT     p   ALTER TABLE ONLY bank.transfer ALTER COLUMN svid SET DEFAULT nextval('bank.transfer_acsavingid_seq'::regclass);
 :   ALTER TABLE bank.transfer ALTER COLUMN svid DROP DEFAULT;
       bank          postgres    false    215    213    215            \           2604    25432    transfer ccid    DEFAULT     n   ALTER TABLE ONLY bank.transfer ALTER COLUMN ccid SET DEFAULT nextval('bank.transfer_acnumber_seq'::regclass);
 :   ALTER TABLE bank.transfer ALTER COLUMN ccid DROP DEFAULT;
       bank          postgres    false    215    214    215            �          0    25373    account 
   TABLE DATA           �   COPY bank.account (id, accountnumber, branchid, openingbalance, balance, opendingdate, deposit, withdrawl, interestrate, customerid, isactive, accounttype) FROM stdin;
    bank          postgres    false    211   3D       �          0    25212    address 
   TABLE DATA           S   COPY bank.address (addressid, address, city, state, country, personid) FROM stdin;
    bank          postgres    false    205   �D       �          0    25336    branch 
   TABLE DATA           j   COPY bank.branch (branchid, bname, baddress, bcity, branchstate, branchcountry, branchnumber) FROM stdin;
    bank          postgres    false    207   9E       �          0    25191    person 
   TABLE DATA           �   COPY bank.person (personid, firstname, lastname, email, phonenumber, occupation, dob, isemployee, password, customertype) FROM stdin;
    bank          postgres    false    202   �E       �          0    25427    transfer 
   TABLE DATA           ]   COPY bank.transfer (id, dot, sourceacc, destinationacc, transamount, svid, ccid) FROM stdin;
    bank          postgres    false    215   mF       	           0    0    address_addressid_seq    SEQUENCE SET     A   SELECT pg_catalog.setval('bank.address_addressid_seq', 4, true);
          bank          postgres    false    203            
           0    0    address_personid_seq    SEQUENCE SET     @   SELECT pg_catalog.setval('bank.address_personid_seq', 4, true);
          bank          postgres    false    204                       0    0    branch_branchid_seq    SEQUENCE SET     ?   SELECT pg_catalog.setval('bank.branch_branchid_seq', 3, true);
          bank          postgres    false    206                       0    0    checking_acnumber_seq    SEQUENCE SET     A   SELECT pg_catalog.setval('bank.checking_acnumber_seq', 7, true);
          bank          postgres    false    208                       0    0    checking_branchid_seq    SEQUENCE SET     A   SELECT pg_catalog.setval('bank.checking_branchid_seq', 7, true);
          bank          postgres    false    209                       0    0    checking_customerid_seq    SEQUENCE SET     C   SELECT pg_catalog.setval('bank.checking_customerid_seq', 7, true);
          bank          postgres    false    210                       0    0    person_personid_seq    SEQUENCE SET     ?   SELECT pg_catalog.setval('bank.person_personid_seq', 5, true);
          bank          postgres    false    201                       0    0    transfer_acnumber_seq    SEQUENCE SET     B   SELECT pg_catalog.setval('bank.transfer_acnumber_seq', 1, false);
          bank          postgres    false    214                       0    0    transfer_acsavingid_seq    SEQUENCE SET     D   SELECT pg_catalog.setval('bank.transfer_acsavingid_seq', 1, false);
          bank          postgres    false    213                       0    0    transfer_transnumber_seq    SEQUENCE SET     E   SELECT pg_catalog.setval('bank.transfer_transnumber_seq', 1, false);
          bank          postgres    false    212            `           2606    25218    address address_pkey 
   CONSTRAINT     W   ALTER TABLE ONLY bank.address
    ADD CONSTRAINT address_pkey PRIMARY KEY (addressid);
 <   ALTER TABLE ONLY bank.address DROP CONSTRAINT address_pkey;
       bank            postgres    false    205            b           2606    25341    branch branchid 
   CONSTRAINT     Q   ALTER TABLE ONLY bank.branch
    ADD CONSTRAINT branchid PRIMARY KEY (branchid);
 7   ALTER TABLE ONLY bank.branch DROP CONSTRAINT branchid;
       bank            postgres    false    207            d           2606    25380    account checking_pkey 
   CONSTRAINT     Q   ALTER TABLE ONLY bank.account
    ADD CONSTRAINT checking_pkey PRIMARY KEY (id);
 =   ALTER TABLE ONLY bank.account DROP CONSTRAINT checking_pkey;
       bank            postgres    false    211            f           2606    25434    transfer id 
   CONSTRAINT     G   ALTER TABLE ONLY bank.transfer
    ADD CONSTRAINT id PRIMARY KEY (id);
 3   ALTER TABLE ONLY bank.transfer DROP CONSTRAINT id;
       bank            postgres    false    215            ^           2606    25196    person person_pkey 
   CONSTRAINT     T   ALTER TABLE ONLY bank.person
    ADD CONSTRAINT person_pkey PRIMARY KEY (personid);
 :   ALTER TABLE ONLY bank.person DROP CONSTRAINT person_pkey;
       bank            postgres    false    202            g           2606    25219    address address_personid_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY bank.address
    ADD CONSTRAINT address_personid_fkey FOREIGN KEY (personid) REFERENCES bank.person(personid);
 E   ALTER TABLE ONLY bank.address DROP CONSTRAINT address_personid_fkey;
       bank          postgres    false    205    3166    202            �   y   x�u�;� D��]��	�����l�z��X����e�'�`�<�\��)����'S�w���q�q�+�/�f^�-S[�.+�U��~1���&7��/>�T������T2�C�ED~G�'�      �   m   x�3�tN��IU(��K�,H��O-.I�L�I��u�v�4�2"�Ȉ˘34/�,��8��R! �(��#?�2?;�3 �Ę˄�713��)�4U!,h"��Xʄ+F��� *)�      �   Z   x�3�tJ��V�OSv�t�,*.Q��/*��t,-.������r���M��S.ASe�e���9?/�(��X��,�$Tn����� �$�      �   �   x��н
�0����^RzNҿMG��c��F[%$u�ꍵn�-��� ܌k�۾$�:���rFD,��`[������y�1$?����P���|������{�M���N�V�FY���P�@r��\��z�L⿌x��8�ϏjHX�!N		�U�&,��}I�v��z`p�� x��}3      �      x������ � �     