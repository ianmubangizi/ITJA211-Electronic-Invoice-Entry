/**
 * Author:  Ian Mubangizi <io@ianmubangizi.com>
 * Created: 31 Mar 2019
 * Database Orion
 */

/*Orion*/
create schema if not exists orion collate utf8_general_ci;

create table if not exists customer
(
  Customer_Number int(200) auto_increment,
  Name            varchar(40)    not null,
  Address         varchar(40)    not null,
  City            varchar(30)    not null,
  Province        varchar(5)     not null,
  Zip             varchar(4)     not null,
  Deposit         decimal(10, 2) not null,
  constraint Customer_Number_index
    unique (Customer_Number)
);

alter table customer
  add primary key (Customer_Number);

create table if not exists account
(
  Name            varchar(40)                 not null,
  Customer_Number int(200)                    not null,
  Balance         decimal(10, 2) default 0.00 null,
  constraint account_customer_Customer_Number_fk
    foreign key (Customer_Number) references customer (Customer_Number)
);

create table if not exists invoice
(
  Invoice_Number  int(200) auto_increment,
  Customer_Number int                         not null,
  Payment         decimal(10, 2) default 0.00 not null,
  constraint Invoice_Number_index
    unique (Invoice_Number),
  constraint invoice_customer_Customer_Number_fk
    foreign key (Customer_Number) references customer (Customer_Number)
      on update cascade on delete cascade
);

alter table invoice
  add primary key (Invoice_Number);

create table if not exists product
(
  Product_Code varchar(7)     not null,
  Description  varchar(40)    null,
  Price        decimal(10, 2) not null,
  constraint product_Product_Code_uindex
    unique (Product_Code)
);

alter table product
  add primary key (Product_Code);

create table if not exists lineitem
(
  Invoice_Number int(200)   not null,
  Product_Code   varchar(7) not null,
  Quantity       int(200)   null,
  constraint lineitem_invoice_Invoice_Number_fk
    foreign key (Invoice_Number) references invoice (Invoice_Number),
  constraint lineitem_product_Product_Code_fk
    foreign key (Product_Code) references product (Product_Code)
);

/*------------------------------------------------------------------------------------------------*/
/*------------------------------------------------------------------------------------------------*/

/*Customer*/
INSERT INTO orion.customer 
(Customer_Number, Name, Address, City, Province, Zip, Deposit)
VALUES 
(3175, 'Sam’s Small Appliance', '100 Main Street', 'Pretoria', 
'ZA-GT', '0127', 2500.00
);
INSERT INTO orion.customer 
(Customer_Number, Name, Address, City, Province, Zip, Deposit)
VALUES 
(3176, 'Electronics Unlimited', 'Pretoria', 'Pretoria', 
'ZA-GT', '0002', 3900.00
);

/*Account*/
INSERT INTO orion.account (Name, Customer_Number, Balance)
VALUES ('Sam’s Small Appliance', 3175, 0.00);
INSERT INTO orion.account (Name, Customer_Number, Balance)
VALUES ('Electronics Unlimited', 3176, 0.00);
INSERT INTO orion.account (Name, Customer_Number, Balance)
VALUES ('Sam’s Small Appliance', 3175, 0.00);

/*Invoice*/
INSERT INTO orion.invoice (Invoice_Number, Customer_Number, Payment)
VALUES (11731, 3175, 0.00);
INSERT INTO orion.invoice (Invoice_Number, Customer_Number, Payment)
VALUES (11732, 3176, 249.50);
INSERT INTO orion.invoice (Invoice_Number, Customer_Number, Payment)
VALUES (11733, 3175, 0.00);

/*LineItem*/
INSERT INTO orion.lineitem (Invoice_Number, Product_Code, Quantity)
VALUES (11731, '116-064', 3);
INSERT INTO orion.lineitem (Invoice_Number, Product_Code, Quantity)
VALUES (11731, '257-535', 1);
INSERT INTO orion.lineitem (Invoice_Number, Product_Code, Quantity)
VALUES (11731, '643-119', 2);
INSERT INTO orion.lineitem (Invoice_Number, Product_Code, Quantity)
VALUES (11732, '116-064', 10);
INSERT INTO orion.lineitem (Invoice_Number, Product_Code, Quantity)
VALUES (11733, '116-064', 2);
INSERT INTO orion.lineitem (Invoice_Number, Product_Code, Quantity)
VALUES (11733, '643-119', 1);

/*Product*/
INSERT INTO orion.product (Product_Code, Description, Price)
VALUES ('116-064', 'Toaster', 24.95);
INSERT INTO orion.product (Product_Code, Description, Price)
VALUES ('257-535', 'Hair dryer', 29.95);
INSERT INTO orion.product (Product_Code, Description, Price)
VALUES ('643-119', 'Car vacuum', 19.99);

