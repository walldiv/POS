create table public.employee (
	employeeId SERIAL primary key,
	firstName varchar(15),
	lastName varchar(15),
	phoneNumber varchar(11),
	address varchar(30),
	city varchar(15),
	state varchar(15),
	dateOfBirth date,
	socialSecurity varchar(9),
	email varchar(30),
	password varchar(40) 
);
create table timeclock(
	entryId SERIAL primary key,
	employee int references employee(employeeId),
	timeIn timestamp,
	timeOut timestamp,
);
create table public.vendor(
	vendorId SERIAL primary key,
	name varchar(30),
	phoneNumber varchar(11),
	address varchar(30),
	city varchar(15),
	state varchar(15),
	email varchar(30),
);
create table public.inventoryitem(
	upc varchar(12) primary key,
	itemName varchar(15),
	category varchar(15) references category(typeName),
	desccription varchar(30),
	itemCost smallint,
	saleValue smallint,
	itemVendor int references vendor(vendorId),
	quantity int	
};
create table public.customer(
	customerId SERIAL primary key,
	firstName varchar(15),
	lastName varchar(15),
	phoneNumber varchar(11),
	address varchar(30),
	city varchar(15),
	state varchar(15),
	email varchar(30),
);
create table public.sale (
	uniqueId SERIAL primary key,
	saleDate date,
	customer varchar(11) references customer(customerId),
	items varchar(12) array references inventoryitem(upc),
	paymentMethod varchar(8),
	saleAmount smallint,
	salesman int references employee(employeeId)
);
create table public.quote (
	quoteId SERIAL primary key,
	quoteDate date,
	customer int references customer(customerId),
	items varchar(12) array references inventoryitem(upc),
	quoteAmount smallint, 
	salesman int references employee(employeeId)
);