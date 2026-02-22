/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/SQLTemplate.sql to edit this template
 */
/**
 * Author:  SENA
 * Created: 22/02/2026
 */

CREATE TABLE IF NOT EXISTS estudiante (
  id INT AUTO_INCREMENT PRIMARY KEY,
  documento VARCHAR(20) NOT NULL UNIQUE,
  nombres VARCHAR(80) NOT NULL,
  apellidos VARCHAR(80) NOT NULL,
  email VARCHAR(120) NULL,
  telefono VARCHAR(20) NULL,
  fecha_nacimiento DATE NULL,
  activo BOOLEAN NOT NULL DEFAULT TRUE,
  created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  updated_at TIMESTAMP NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP
);

INSERT INTO estudiante (documento, nombres, apellidos, email, telefono, fecha_nacimiento, activo)
VALUES
('1001', 'Ana', 'Gómez', 'ana.gomez@mail.com', '3001112233', '2004-05-12', TRUE),
('1002', 'Luis', 'Pérez', 'luis.perez@mail.com', '3004445566', '2003-11-02', TRUE);