DROP DATABASE soltel_db;
create database soltel_db;
use soltel_db;

-- Crear la tabla Usuario
CREATE TABLE Usuario (
    Codope INT PRIMARY KEY,
    Contraseña VARCHAR(50) NOT NULL
);

-- Crear la tabla Candidatos
CREATE TABLE Candidatos (
    Id INT AUTO_INCREMENT PRIMARY KEY,
    NombreCandidato VARCHAR(100) NOT NULL
);

-- Crear la tabla Empresa
CREATE TABLE Empresa (
    Id INT AUTO_INCREMENT PRIMARY KEY,
    NombreEmpresa VARCHAR(100) NOT NULL
);

-- Crear la tabla Recruiting
CREATE TABLE Recruiting (
    IdRecruiting INT PRIMARY KEY,
    EmpresaId INT,
    NombreProyecto VARCHAR(50) NOT NULL,
    FOREIGN KEY (EmpresaId) REFERENCES Empresa(Id)
);

-- Crear la tabla Ubicación
CREATE TABLE Ubicacion (
    Id INT AUTO_INCREMENT PRIMARY KEY,
    NombreProvincia VARCHAR(100) NOT NULL
);

-- Crear la tabla Puesto
CREATE TABLE Puesto (
    Id INT AUTO_INCREMENT PRIMARY KEY,
    NombrePuesto VARCHAR(100) NOT NULL
);

-- Crear la tabla Estado
CREATE TABLE Estado (
    Id INT AUTO_INCREMENT PRIMARY KEY,
    Estado VARCHAR(50) NOT NULL
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
    Id INT AUTO_INCREMENT PRIMARY KEY,
    NombreCandidato VARCHAR(100) NOT NULL,
    Codope INT NOT NULL,
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
    FOREIGN KEY (Codope) REFERENCES Usuario(Codope),
    FOREIGN KEY (IdRecruiting) REFERENCES Recruiting(IdRecruiting),
    FOREIGN KEY (EmpresaId) REFERENCES Empresa(Id),
    FOREIGN KEY (UbicacionId) REFERENCES Ubicacion(Id),
    FOREIGN KEY (PuestoId) REFERENCES Puesto(Id),
    FOREIGN KEY (EstadoId) REFERENCES Estado(Id),
    FOREIGN KEY (IdCandidato) REFERENCES Candidatos(Id)
);


-- Insertar valores en la tabla Usuario
INSERT INTO Usuario (Codope, Contraseña) VALUES
(1, 'password1'),
(2, 'password2');

-- Insertar valores en la tabla Candidatos
INSERT INTO Candidatos (NombreCandidato) VALUES
('Candidato 1'),
('Candidato 2');

-- Insertar valores en la tabla Empresa
INSERT INTO Empresa (NombreEmpresa) VALUES
('Empresa 1'),
('Empresa 2');

-- Insertar valores en la tabla Recruiting
INSERT INTO Recruiting (IdRecruiting, EmpresaId, NombreProyecto) VALUES
(1, 1, 'Proyecto A'),
(2, 2, 'Proyecto B');

-- Insertar valores en la tabla Ubicación
INSERT INTO Ubicacion (NombreProvincia) VALUES
('Provincia 1'),
('Provincia 2');

-- Insertar valores en la tabla Puesto
INSERT INTO Puesto (NombrePuesto) VALUES
('Puesto 1'),
('Puesto 2');

-- Insertar valores en la tabla Ofertas
INSERT INTO Ofertas (NombreCandidato, Codope, IdRecruiting, EmpresaId, UbicacionId, PuestoId, Tecnologias, Experiencia, Salario, EstadoId, FechaActualizacion, Observaciones, HistoricoCambioEstados, IdCandidato) VALUES
('Candidato 1', 1, 1, 1, 1, 1, 'Tecnología A', 2.5, 50000.00, 1, '2023-01-01', 'Observación 1', 'Histórico 1', 1),
('Candidato 2', 2, 1, 1, 2, 2, 'Tecnología B', 3.0, 60000.00, 2, '2023-02-01', 'Observación 2', 'Histórico 2', 2);