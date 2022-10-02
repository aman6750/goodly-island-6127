Tender Management System

Whenever a company requires a service / merchandise , a tender is floated. Company maintains an empaneled list of Vendors. An empaneled vendor can only bid for a tender. Every vendor can bid only once against each tender. Against each tender there may be bids from several vendors. The company will then select the most suitable bid and places the order to that vendor.

There are 2 users in the system:

Administrator and Vendor

The Role of Administrator is
------------------

1. Create new Vendor.
2. View all the vendors.
3. Create new tenders.
4. View All the Tenders.
5. View All the Bids of a tender.
6. Select a Bid.

The Role of a Vendor is:
------------------
1. View all the current Tenders.
2. Place a Bid against a Tender.
3. View status of a Bid(Whether Selected or Not)
4. View his own Bid History.


 Technologies used:-
 ----------------

Back-End Development
Java
core java
JDBC
MySQL
==== Software And Tools Required ======
: MySQL
: STS
:Git bash

==========  Database showCase ===========

mysql> use tendersb101db
Database changed
mysql> show tables;
+-------------------------+
| Tables_in_tendersb101db |
+-------------------------+
| admin                   |
| bidder                  |
| tender_status           |
| tendor                  |
| vendor                  |
+-------------------------+

======================
vendor table:

mysql> desc vendor;
+----------+--------------+------+-----+---------+-------+
| Field    | Type         | Null | Key | Default | Extra |
+----------+--------------+------+-----+---------+-------+
| vid      | varchar(25)  | NO   | PRI | NULL    |       |
| password | varchar(25)  | YES  |     | NULL    |       |
| vname    | varchar(30)  | YES  |     | NULL    |       |
| vmobile  | varchar(12)  | YES  |     | NULL    |       |
| vemail   | varchar(40)  | YES  |     | NULL    |       |
| vcompany | varchar(30)  | YES  |     | NULL    |       |
| vaddres  | varchar(100) | YES  |     | NULL    |       |
+----------+--------------+------+-----+---------+-------+

==================

tender table

mysql> desc tendor;
+--------------+--------------+------+-----+---------+-------+
| Field        | Type         | Null | Key | Default | Extra |
+--------------+--------------+------+-----+---------+-------+
| tid          | varchar(25)  | NO   | PRI | NULL    |       |
| tname        | varchar(30)  | YES  |     | NULL    |       |
| ttype        | varchar(20)  | YES  |     | NULL    |       |
| tprice       | int          | YES  |     | NULL    |       |
| tdescription | varchar(300) | YES  |     | NULL    |       |
| tdeadline    | varchar(30)  | YES  |     | NULL    |       |
| tlocation    | varchar(100) | YES  |     | NULL    |       |
+--------------+--------------+------+-----+---------+-------+

=======================
bidder table :

mysql> desc bidder;
+-----------+-------------+------+-----+---------+-------+
| Field     | Type        | Null | Key | Default | Extra |
+-----------+-------------+------+-----+---------+-------+
| bid       | varchar(25) | NO   | PRI | NULL    |       |
| vid       | varchar(25) | YES  | MUL | NULL    |       |
| tid       | varchar(25) | YES  | MUL | NULL    |       |
| bidamount | int         | YES  |     | NULL    |       |
| deadline  | varchar(20) | YES  |     | NULL    |       |
| status    | varchar(10) | YES  |     | NULL    |       |
+-----------+-------------+------+-----+---------+-------+

=========================


"Suggestions and project Improvements are always Invited!"
Thanks a lot
Project Leader
Aman yadav.
