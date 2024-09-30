DROP DATABASE soltel_db;
create database soltel_db;
use soltel_db;

-- Crear la tabla Usuario
CREATE TABLE Usuario (
    Codope varchar(10) PRIMARY KEY,
    Contraseña VARCHAR(50) NOT NULL,
    activo boolean default 0
);

-- Crear la tabla Candidatos
CREATE TABLE Candidatos (
    IdCandidato INT AUTO_INCREMENT PRIMARY KEY,
    NombreCandidato VARCHAR(100) NOT NULL,
    activo boolean default 0
);

-- Crear la tabla Empresa
CREATE TABLE Empresa (
    IdEmpresa INT AUTO_INCREMENT PRIMARY KEY,
    NombreEmpresa VARCHAR(100) NOT NULL,
    activo boolean default 0
);

-- Crear la tabla Recruiting
CREATE TABLE Recruiting (
    IdRecruiting INT PRIMARY KEY,
    EmpresaId INT,
    NombreProyecto VARCHAR(50) NOT NULL,
    activo boolean default 0,
    FOREIGN KEY (EmpresaId) REFERENCES Empresa(IdEmpresa)
);

-- Crear la tabla Ubicación
CREATE TABLE Ubicacion (
    IdUbicacion INT AUTO_INCREMENT PRIMARY KEY,
    NombreProvincia VARCHAR(100) NOT NULL,
    activo boolean default 0
);

-- Crear la tabla Puesto
CREATE TABLE Puesto (
    IdPuesto INT AUTO_INCREMENT PRIMARY KEY,
    NombrePuesto VARCHAR(100) NOT NULL,
	activo boolean default 0
);

-- Crear la tabla Estado
CREATE TABLE Estado (
    IdEstado INT AUTO_INCREMENT PRIMARY KEY,
    Estado VARCHAR(50) NOT NULL,
	activo boolean default 0
);

-- Insertar valores en la tabla Estado
INSERT INTO Estado (Estado) VALUES
('Autodescartado'),
('Entrevistado'),
('Propuesto'),
('Rechazado'),
('Solicitud entrevista'),
('Solicitud incorporación'),
('Incorporado');

-- Crear la tabla Ofertas
CREATE TABLE Ofertas (
    IdOferta INT AUTO_INCREMENT PRIMARY KEY,
    NombreCandidato VARCHAR(100) NOT NULL,
    Codope varchar(10) NOT NULL,
    IdRecruiting INT NOT NULL,
    EmpresaId INT NOT NULL,
    UbicacionId INT NOT NULL,
    PuestoId INT NOT NULL,
    Tecnologias TEXT,
    Experiencia decimal(3,1),
    Salario DECIMAL(10, 2) NOT NULL,
    EstadoId INT NOT NULL,
    FechaActualizacion DATE NOT NULL,
    Observaciones TEXT,
    HistoricoCambioEstados TEXT,
    IdCandidato INT,
	activo boolean default 0,
    FOREIGN KEY (Codope) REFERENCES Usuario(Codope),
    FOREIGN KEY (IdRecruiting) REFERENCES Recruiting(IdRecruiting),
    FOREIGN KEY (EmpresaId) REFERENCES Empresa(IdEmpresa),
    FOREIGN KEY (UbicacionId) REFERENCES Ubicacion(IdUbicacion),
    FOREIGN KEY (PuestoId) REFERENCES Puesto(IdPuesto),
    FOREIGN KEY (EstadoId) REFERENCES Estado(IdEstado),
    FOREIGN KEY (IdCandidato) REFERENCES Candidatos(IdCandidato)
);