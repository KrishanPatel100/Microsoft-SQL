CREATE DATABASE ClothesShop;

USE ClothesShop;

CREATE TABLE ClothesInventory (
	item		varchar(64) PRIMARY KEY,
	quantity	int NOT NULL
);



INSERT INTO ClothesInventory
VALUES ('Blue shirt', 5);

INSERT INTO ClothesInventory
VALUES ('Purple shirt', 3);

INSERT INTO ClothesInventory
VALUES ('Dark Green shirt', 2);

INSERT INTO ClothesInventory
VALUES ('Light Green shirt', 6);

INSERT INTO ClothesInventory
VALUES ('Orange shirt', 1);

INSERT INTO ClothesInventory
VALUES ('Red shirt', 0);

INSERT INTO ClothesInventory
VALUES ('Yellow shirt', 4);

INSERT INTO ClothesInventory
VALUES ('Black shirt', 5);

INSERT INTO ClothesInventory
VALUES ('Pink shirt', 2);



SELECT * FROM ClothesInventory;