PGDMP     (                     y           Bank    13.2    13.2 9    ?           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false            ?           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false            ?           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            ?           1262    24658    Bank    DATABASE     Q   CREATE DATABASE "Bank" WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE = 'C';
    DROP DATABASE "Bank";
                postgres    false                        2615    25240    bank    SCHEMA        CREATE SCHEMA bank;
    DROP SCHEMA bank;
                postgres    false            ?            1259    25373    account    TABLE     ?  CREATE TABLE bank.account (
    id integer NOT NULL,
    accountnumber character varying(10),
    branchid integer,
    openingbalance double precision NOT NULL,
    balance double precision,
    opendingdate date,
    deposit double precision,
    withdraw double precision,
    interestrate numeric,
    customerid integer NOT NULL,
    isactive boolean DEFAULT false,
    accounttype character varying,
    status character varying
);
    DROP TABLE bank.account;
       bank         heap    postgres    false    5            ?            1259    25212    address    TABLE     ?   CREATE TABLE bank.address (
    addressid integer NOT NULL,
    address character varying(20),
    city character varying(20),
    state character varying(2),
    country character varying(20),
    personid integer NOT NULL
);
    DROP TABLE bank.address;
       bank         heap    postgres    false    5            ?            1259    25208    address_addressid_seq    SEQUENCE     ?   CREATE SEQUENCE bank.address_addressid_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 *   DROP SEQUENCE bank.address_addressid_seq;
       bank          postgres    false    205    5            ?           0    0    address_addressid_seq    SEQUENCE OWNED BY     K   ALTER SEQUENCE bank.address_addressid_seq OWNED BY bank.address.addressid;
          bank          postgres    false    203            ?            1259    25210    address_personid_seq    SEQUENCE     ?   CREATE SEQUENCE bank.address_personid_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 )   DROP SEQUENCE bank.address_personid_seq;
       bank          postgres    false    5    205            ?           0    0    address_personid_seq    SEQUENCE OWNED BY     I   ALTER SEQUENCE bank.address_personid_seq OWNED BY bank.address.personid;
          bank          postgres    false    204            ?            1259    25336    branch    TABLE       CREATE TABLE bank.branch (
    branchid integer NOT NULL,
    bname character varying(20),
    baddress character varying(20),
    bcity character varying(20),
    branchstate character varying(4),
    branchcountry character varying(10),
    branchnumber integer
);
    DROP TABLE bank.branch;
       bank         heap    postgres    false    5            ?            1259    25334    branch_branchid_seq    SEQUENCE     ?   CREATE SEQUENCE bank.branch_branchid_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 (   DROP SEQUENCE bank.branch_branchid_seq;
       bank          postgres    false    207    5                        0    0    branch_branchid_seq    SEQUENCE OWNED BY     G   ALTER SEQUENCE bank.branch_branchid_seq OWNED BY bank.branch.branchid;
          bank          postgres    false    206            ?            1259    25367    checking_acnumber_seq    SEQUENCE     ?   CREATE SEQUENCE bank.checking_acnumber_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 *   DROP SEQUENCE bank.checking_acnumber_seq;
       bank          postgres    false    211    5                       0    0    checking_acnumber_seq    SEQUENCE OWNED BY     D   ALTER SEQUENCE bank.checking_acnumber_seq OWNED BY bank.account.id;
          bank          postgres    false    208            ?            1259    25369    checking_branchid_seq    SEQUENCE     ?   CREATE SEQUENCE bank.checking_branchid_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 *   DROP SEQUENCE bank.checking_branchid_seq;
       bank          postgres    false    211    5                       0    0    checking_branchid_seq    SEQUENCE OWNED BY     J   ALTER SEQUENCE bank.checking_branchid_seq OWNED BY bank.account.branchid;
          bank          postgres    false    209            ?            1259    25371    checking_customerid_seq    SEQUENCE     ?   CREATE SEQUENCE bank.checking_customerid_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 ,   DROP SEQUENCE bank.checking_customerid_seq;
       bank          postgres    false    5    211                       0    0    checking_customerid_seq    SEQUENCE OWNED BY     N   ALTER SEQUENCE bank.checking_customerid_seq OWNED BY bank.account.customerid;
          bank          postgres    false    210            ?            1259    25191    person    TABLE     m  CREATE TABLE bank.person (
    id integer NOT NULL,
    firstname character varying(20),
    lastname character varying(20),
    email character varying(20),
    phonenumber character varying(14),
    occupation character varying(20),
    dob date,
    isemployee boolean NOT NULL,
    password character varying(20) NOT NULL,
    customertype character varying
);
    DROP TABLE bank.person;
       bank         heap    postgres    false    5            ?            1259    25189    person_personid_seq    SEQUENCE     ?   CREATE SEQUENCE bank.person_personid_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 (   DROP SEQUENCE bank.person_personid_seq;
       bank          postgres    false    202    5                       0    0    person_personid_seq    SEQUENCE OWNED BY     A   ALTER SEQUENCE bank.person_personid_seq OWNED BY bank.person.id;
          bank          postgres    false    201            ?            1259    25427    transactionlog    TABLE       CREATE TABLE bank.transactionlog (
    id integer NOT NULL,
    txndate date NOT NULL,
    sourceaccount character varying(10),
    destinationaccount character varying(10) NOT NULL,
    amount double precision NOT NULL,
    txntype character varying NOT NULL
);
     DROP TABLE bank.transactionlog;
       bank         heap    postgres    false    5            ?            1259    25421    transfer_transnumber_seq    SEQUENCE     ?   CREATE SEQUENCE bank.transfer_transnumber_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 -   DROP SEQUENCE bank.transfer_transnumber_seq;
       bank          postgres    false    5    213                       0    0    transfer_transnumber_seq    SEQUENCE OWNED BY     N   ALTER SEQUENCE bank.transfer_transnumber_seq OWNED BY bank.transactionlog.id;
          bank          postgres    false    212            S           2604    25376 
   account id    DEFAULT     k   ALTER TABLE ONLY bank.account ALTER COLUMN id SET DEFAULT nextval('bank.checking_acnumber_seq'::regclass);
 7   ALTER TABLE bank.account ALTER COLUMN id DROP DEFAULT;
       bank          postgres    false    211    208    211            T           2604    25377    account branchid    DEFAULT     q   ALTER TABLE ONLY bank.account ALTER COLUMN branchid SET DEFAULT nextval('bank.checking_branchid_seq'::regclass);
 =   ALTER TABLE bank.account ALTER COLUMN branchid DROP DEFAULT;
       bank          postgres    false    211    209    211            P           2604    25215    address addressid    DEFAULT     r   ALTER TABLE ONLY bank.address ALTER COLUMN addressid SET DEFAULT nextval('bank.address_addressid_seq'::regclass);
 >   ALTER TABLE bank.address ALTER COLUMN addressid DROP DEFAULT;
       bank          postgres    false    203    205    205            Q           2604    25216    address personid    DEFAULT     p   ALTER TABLE ONLY bank.address ALTER COLUMN personid SET DEFAULT nextval('bank.address_personid_seq'::regclass);
 =   ALTER TABLE bank.address ALTER COLUMN personid DROP DEFAULT;
       bank          postgres    false    204    205    205            R           2604    25339    branch branchid    DEFAULT     n   ALTER TABLE ONLY bank.branch ALTER COLUMN branchid SET DEFAULT nextval('bank.branch_branchid_seq'::regclass);
 <   ALTER TABLE bank.branch ALTER COLUMN branchid DROP DEFAULT;
       bank          postgres    false    207    206    207            O           2604    25194 	   person id    DEFAULT     h   ALTER TABLE ONLY bank.person ALTER COLUMN id SET DEFAULT nextval('bank.person_personid_seq'::regclass);
 6   ALTER TABLE bank.person ALTER COLUMN id DROP DEFAULT;
       bank          postgres    false    201    202    202            V           2604    25430    transactionlog id    DEFAULT     u   ALTER TABLE ONLY bank.transactionlog ALTER COLUMN id SET DEFAULT nextval('bank.transfer_transnumber_seq'::regclass);
 >   ALTER TABLE bank.transactionlog ALTER COLUMN id DROP DEFAULT;
       bank          postgres    false    213    212    213            ?          0    25373    account 
   TABLE DATA           ?   COPY bank.account (id, accountnumber, branchid, openingbalance, balance, opendingdate, deposit, withdraw, interestrate, customerid, isactive, accounttype, status) FROM stdin;
    bank          postgres    false    211   ?A       ?          0    25212    address 
   TABLE DATA           S   COPY bank.address (addressid, address, city, state, country, personid) FROM stdin;
    bank          postgres    false    205   :C       ?          0    25336    branch 
   TABLE DATA           j   COPY bank.branch (branchid, bname, baddress, bcity, branchstate, branchcountry, branchnumber) FROM stdin;
    bank          postgres    false    207   ?C       ?          0    25191    person 
   TABLE DATA           ?   COPY bank.person (id, firstname, lastname, email, phonenumber, occupation, dob, isemployee, password, customertype) FROM stdin;
    bank          postgres    false    202   
D       ?          0    25427    transactionlog 
   TABLE DATA           g   COPY bank.transactionlog (id, txndate, sourceaccount, destinationaccount, amount, txntype) FROM stdin;
    bank          postgres    false    213   ?E                  0    0    address_addressid_seq    SEQUENCE SET     A   SELECT pg_catalog.setval('bank.address_addressid_seq', 4, true);
          bank          postgres    false    203                       0    0    address_personid_seq    SEQUENCE SET     @   SELECT pg_catalog.setval('bank.address_personid_seq', 4, true);
          bank          postgres    false    204                       0    0    branch_branchid_seq    SEQUENCE SET     ?   SELECT pg_catalog.setval('bank.branch_branchid_seq', 3, true);
          bank          postgres    false    206            	           0    0    checking_acnumber_seq    SEQUENCE SET     B   SELECT pg_catalog.setval('bank.checking_acnumber_seq', 32, true);
          bank          postgres    false    208            
           0    0    checking_branchid_seq    SEQUENCE SET     B   SELECT pg_catalog.setval('bank.checking_branchid_seq', 32, true);
          bank          postgres    false    209                       0    0    checking_customerid_seq    SEQUENCE SET     D   SELECT pg_catalog.setval('bank.checking_customerid_seq', 12, true);
          bank          postgres    false    210                       0    0    person_personid_seq    SEQUENCE SET     @   SELECT pg_catalog.setval('bank.person_personid_seq', 36, true);
          bank          postgres    false    201                       0    0    transfer_transnumber_seq    SEQUENCE SET     E   SELECT pg_catalog.setval('bank.transfer_transnumber_seq', 45, true);
          bank          postgres    false    212            \           2606    25218    address address_pkey 
   CONSTRAINT     W   ALTER TABLE ONLY bank.address
    ADD CONSTRAINT address_pkey PRIMARY KEY (addressid);
 <   ALTER TABLE ONLY bank.address DROP CONSTRAINT address_pkey;
       bank            postgres    false    205            ^           2606    25341    branch branchid 
   CONSTRAINT     Q   ALTER TABLE ONLY bank.branch
    ADD CONSTRAINT branchid PRIMARY KEY (branchid);
 7   ALTER TABLE ONLY bank.branch DROP CONSTRAINT branchid;
       bank            postgres    false    207            `           2606    25380    account checking_pkey 
   CONSTRAINT     Q   ALTER TABLE ONLY bank.account
    ADD CONSTRAINT checking_pkey PRIMARY KEY (id);
 =   ALTER TABLE ONLY bank.account DROP CONSTRAINT checking_pkey;
       bank            postgres    false    211            X           2606    25577    person email_unique 
   CONSTRAINT     M   ALTER TABLE ONLY bank.person
    ADD CONSTRAINT email_unique UNIQUE (email);
 ;   ALTER TABLE ONLY bank.person DROP CONSTRAINT email_unique;
       bank            postgres    false    202            d           2606    25434    transactionlog id 
   CONSTRAINT     M   ALTER TABLE ONLY bank.transactionlog
    ADD CONSTRAINT id PRIMARY KEY (id);
 9   ALTER TABLE ONLY bank.transactionlog DROP CONSTRAINT id;
       bank            postgres    false    213            Z           2606    25196    person person_pkey 
   CONSTRAINT     N   ALTER TABLE ONLY bank.person
    ADD CONSTRAINT person_pkey PRIMARY KEY (id);
 :   ALTER TABLE ONLY bank.person DROP CONSTRAINT person_pkey;
       bank            postgres    false    202            b           2606    25587    account uq_accountnumber 
   CONSTRAINT     Z   ALTER TABLE ONLY bank.account
    ADD CONSTRAINT uq_accountnumber UNIQUE (accountnumber);
 @   ALTER TABLE ONLY bank.account DROP CONSTRAINT uq_accountnumber;
       bank            postgres    false    211            e           2606    25219    address address_personid_fkey    FK CONSTRAINT     z   ALTER TABLE ONLY bank.address
    ADD CONSTRAINT address_personid_fkey FOREIGN KEY (personid) REFERENCES bank.person(id);
 E   ALTER TABLE ONLY bank.address DROP CONSTRAINT address_personid_fkey;
       bank          postgres    false    205    202    3162            f           2606    25578    account customerid_fk    FK CONSTRAINT     ~   ALTER TABLE ONLY bank.account
    ADD CONSTRAINT customerid_fk FOREIGN KEY (customerid) REFERENCES bank.person(id) NOT VALID;
 =   ALTER TABLE ONLY bank.account DROP CONSTRAINT customerid_fk;
       bank          postgres    false    202    211    3162            g           2606    25588     transactionlog fk_transactionlog    FK CONSTRAINT     ?   ALTER TABLE ONLY bank.transactionlog
    ADD CONSTRAINT fk_transactionlog FOREIGN KEY (sourceaccount) REFERENCES bank.account(accountnumber);
 H   ALTER TABLE ONLY bank.transactionlog DROP CONSTRAINT fk_transactionlog;
       bank          postgres    false    211    3170    213            h           2606    25593 ,   transactionlog fk_transactionlog_destaccount    FK CONSTRAINT     ?   ALTER TABLE ONLY bank.transactionlog
    ADD CONSTRAINT fk_transactionlog_destaccount FOREIGN KEY (destinationaccount) REFERENCES bank.account(accountnumber);
 T   ALTER TABLE ONLY bank.transactionlog DROP CONSTRAINT fk_transactionlog_destaccount;
       bank          postgres    false    213    211    3170            ?   ?  x????s?0?g??$gK?;r?????Х\????'?!?1-?l???,?@2?Sfa
d?6lFã?a??oz=??v?????L?eq@#???OyVҌ??tb=?|??r???}??Vޑ?<?g???????Z'?,????????3M0O4?????&??Qu?>]?xXcn(???!`S?IL???0???1??Y?V?c??7Z[Тp#3? ?a??)??MJrQ$??V??#[??@U??]???t???U?L??????8???5?<T?[.-1??\8??k?=l ?????K??Y?E??J\?w?Xn???z݃ի?Od?9?脲??x?`?'???)%?I????r?S?{??y??װ?x??!u=????|?r???T?o?1?J?????8f?r?l??u??a?%      ?   V   x?3?tN??IU(??K?,H??O-.I?L?I??u?v?4?2"?Ȉ˘34/?,??8??R! ?(??#??2?;?3 ?Ę+F??? M"A      ?   Z   x?3?tJ??V?OSv?t?,*.Q??/*??t,-.??????r???M??S.ASe?e???9?/?(??X??,?$Tn????? ?$?      ?   ?  x???M??0???!????-??R?RWQ[????ݐ?0Q?????.?ԋ?|???gƯ<?Ƕ????'2߫_?;?kt?D??e???K???d?ص$?'947??>?em??&^Vl#sG?9??k'???G?<?\?l?fa?N?,7?R?}??j?A? \?9÷??Ϯܾ??`*??D??d+~j????̀UQ?Hv?6Fi?O=}t? ??լ&@j?k?+ٜ????+y??*99	?/rj%??_O?7????????_'???m~???e?????f???t??8?$?][E?D\????r??F||?;??x?ه?Kȸ.????ܺ?e}?۱???ß??ɂ7?,??f+???@???nܪ??#?%??U1?y??r??{ONu??p?>??Nc??ۼ?? ?y???c?*?)?,?y,?.bq?Y$??K??	?l      ?   E  x???͎? ?3?K?
GݬwS?x???c1Ɲ?RB4^?93̟?8????G????b
??Μ/????f?R8!1???0c?g?a?1?{?	C'X???Ǌ?QA??????D???GUo?????Uo??z??Y?R?ի}g?R:?WJ-?u?!??cŰWN??<$¸ǔ?nk??r?Sn??n?6??8??5?h?m???[?`?,?/??:? ?6Y9??n?????Xġ?S?l??v????T??z?:????l??? n^X??:??o???:?7??)A????82I2?1͵8[???&IG:V?۲v
?????l??????_,     