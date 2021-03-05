# Quick Food Order System

## Description
The Quick Food Order System is Java program that was developed for partial fulfilment of the requirements for HyperionDev Certified Full Stack Web & Software Engineer Bootcamp.
The software represents an order management system with the following functionality:
 * Create a new order
 * Find an existing customer
 * Create a new customer
 * Update an existing customer
 * Find incomplete orders
 * Finalise an order
 * Find an existing order

The application is console based. The database is implemented in Microsoft SQL Server.

## Table of contents
1. Installation
2. Setup
3. Usage
4. Further help
5. License

## Installation
The following are major software components and their versions:
* The software was developed with JRE 15
* Testing software was developed in Junit 5
* JDBC driver for SQL Server integration: mssql-jdbc-9.2.0.jre15.jar
* SQL server info: SQL Server Management Studio						15.0.18206.0
** Microsoft Analysis Services Client Tools						15.0.1567.0
** Microsoft Data Access Components (MDAC)						10.0.18362.1
** Microsoft MSXML						3.0 6.0 
** Microsoft Internet Explorer						9.11.18362.0
** Microsoft .NET Framework						4.0.30319.42000
** Operating System						10.0.18363

## Setup
Setting up of the database may require some customisation. Please observe the following:
1. Make sure the tcp prototcol is set to the correct port
* Go to Computer Management / Services and applications / SQL Server network configuration /
			 protocols for SQLEXPRESS / TCP/IP - Set status to Enabled
* Double click on TCP/IP / TCP/IP Properties //IPAll: make sure TCP dynamic ports is set to 1433
2. Make sure the authentication in SSMS is set to SQL Server and Windows Authentication mode
* In SSMS, right click on the server and select properties
    Go to the Security page and select the correct radio button

## Usage
Once the application is set up and running, the usage is fairly self explanatory. Just follow the on screen prompts.

## Further help
The software is accompanied by a full suite of JavaDocs for further reference.

## License
There is no license for this software. Users are free to copy, modify and reuse as needed.
