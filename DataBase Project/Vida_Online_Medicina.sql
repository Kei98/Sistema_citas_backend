/*Creación base de datos para citas médicas*/
DROP DATABASE IF EXISTS Vida_Online;
CREATE DATABASE Vida_Online;

/*Creación y alteración de tablas*/
USE Vida_Online;
DROP TABLE IF EXISTS Cita;
DROP TABLE IF EXISTS Medico;
DROP TABLE IF EXISTS Paciente;
DROP TABLE IF EXISTS Ebais;
DROP TABLE IF EXISTS Usuario;

USE Vida_Online;
CREATE TABLE Medico (
carnet int PRIMARY KEY NOT NULL,
nombre varchar(45) NOT NULL,
apellido varchar(45) NOT NULL,
areaM varchar(55) NOT NULL
);

USE Vida_Online;
CREATE TABLE Paciente (
id_paciente int PRIMARY KEY NOT NULL,
nombre varchar(90) NOT NULL,
direccion varchar(90) NOT NULL
);

USE Vida_Online;
CREATE TABLE Ebais (
telefono int PRIMARY KEY NOT NULL,
provincia varchar(45) NOT NULL,
canton varchar(45) NOT NULL,
distrito varchar(55) NOT NULL
);


USE Vida_Online;
CREATE TABLE Cita (
numeroCita int PRIMARY KEY auto_increment,
fecha DATE NOT NULL,
horaCita varchar(5) NOT NULL,
id_Paciente int NOT NULL,
carnet int NOT NULL,
areaAsignada varchar(50) NOT NULL,
ebais int NOT NULL,
FOREIGN KEY (id_Paciente) REFERENCES Paciente(id_paciente),
FOREIGN KEY (carnet) REFERENCES Medico(carnet),
FOREIGN KEY (ebais) REFERENCES Ebais(telefono)
);


USE Vida_Online;
CREATE TABLE Usuario (
username varchar(45) PRIMARY KEY NOT NULL,
password_ varchar(500) NOT NULL
);

