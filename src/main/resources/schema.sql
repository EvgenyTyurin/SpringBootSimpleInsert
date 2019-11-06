CREATE TABLE IF NOT EXISTS Ingredient (
	id varchar(4) not null,
	name varchar(25) not null
);

create table if not exists Taco (
    id identity,
    name varchar(50) not null,
    createdAt timestamp not null
);
CREATE TABLE IF NOT EXISTS Taco_Ingredients(
	taco bigint not null,
	ingredient varchar(4) not null
);

ALTER TABLE Taco_Ingredients
	ADD FOREIGN KEY (taco) REFERENCES Taco(id);
ALTER TABLE Taco_Ingredients
	ADD FOREIGN KEY (ingredient) REFERENCES Ingredient(id);

CREATE TABLE IF NOT EXISTS Taco_Order (
	id identity,
	name varchar(30) not null,
	address varchar(100) not null,
	ccNumber varchar(16) not null,
	ccExpiration varchar(5) not null,
	ccCVV varchar(3) not null,
	placedAt timestamp not null
);

CREATE TABLE IF NOT EXISTS Taco_Order_Tacos (
	tacoOrder bigint not null,
	taco bigint not null
);

ALTER TABLE Taco_Order_Tacos
	add foreign key (tacoOrder) references Taco_Order(id);	

ALTER TABLE Taco_Order_Tacos
	add foreign key (taco) references Taco(id);	
