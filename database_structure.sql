create database javafcc;
use javafcc;

CREATE TABLE Sensor_Type (
    type_id INT AUTO_INCREMENT PRIMARY KEY,
    type_name VARCHAR(100) NOT NULL UNIQUE,
    description TEXT,
    measurement_unit VARCHAR(30) NOT NULL,
    category VARCHAR(60) NOT NULL
);
INSERT INTO Sensor_Type (type_name, description, measurement_unit, category) VALUES
('Temperature', 'Measures ambient temperature', '°C', 'Environmental'),
('Air Quality', 'Measures PM2.5 and PM10 levels', 'µg/m3', 'Environmental'),
('Noise', 'Measures sound levels', 'dB', 'Environmental'),
('Gas', 'Measures CO2 levels', 'ppm', 'Industrial');

CREATE TABLE Location (
    location_id INT AUTO_INCREMENT PRIMARY KEY,
    location_name VARCHAR(120) NOT NULL,
    address VARCHAR(255),
    city VARCHAR(80),
    state VARCHAR(80),
    pincode VARCHAR(20),
    latitude DECIMAL(10,7),
    longitude DECIMAL(10,7),
    zone_type VARCHAR(60)
);
INSERT INTO Location (location_name, address, city, state, pincode, latitude, longitude, zone_type) VALUES
('Shivaji Nagar', 'JM Road', 'Pune', 'Maharashtra', '411005', 18.5308, 73.8475, 'Commercial'),
('Hinjewadi IT Park', 'Phase 1', 'Pune', 'Maharashtra', '411057', 18.5912, 73.7389, 'Industrial'),
('Baner', 'Baner Road', 'Pune', 'Maharashtra', '411045', 18.5590, 73.7868, 'Residential'),
('Katraj', 'Katraj Chowk', 'Pune', 'Maharashtra', '411046', 18.4575, 73.8508, 'Residential');

CREATE TABLE Threshold (
    threshold_id INT AUTO_INCREMENT PRIMARY KEY,
    parameter_name VARCHAR(100) NOT NULL,
    min_value DECIMAL(12,4) NOT NULL,
    max_value DECIMAL(12,4) NOT NULL,
    severity_level ENUM('LOW','MEDIUM','HIGH','CRITICAL') DEFAULT 'MEDIUM',
    unit VARCHAR(30),
    CHECK (min_value < max_value)
);
INSERT INTO Threshold (parameter_name, min_value, max_value, severity_level, unit) VALUES
('Temperature', 0, 40, 'HIGH', '°C'),
('PM2.5', 0, 150, 'CRITICAL', 'µg/m3'),
('Noise', 0, 90, 'MEDIUM', 'dB'),
('CO2', 300, 1000, 'HIGH', 'ppm');

CREATE TABLE User (
    user_id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(60) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    email VARCHAR(120) NOT NULL UNIQUE,
    role ENUM('ADMIN','OPERATOR','VIEWER') DEFAULT 'VIEWER',
    created_date DATE DEFAULT (CURRENT_DATE),
    last_login DATETIME
);
INSERT INTO User (username, password, email, role) VALUES
('admin1', 'hashedpass', 'admin@env.com', 'ADMIN'),
('operator1', 'hashedpass', 'operator@env.com', 'OPERATOR'),
('viewer1', 'hashedpass', 'viewer@env.com', 'VIEWER');

CREATE TABLE Sensor (
    sensor_id INT AUTO_INCREMENT PRIMARY KEY,
    sensor_name VARCHAR(120) NOT NULL,
    model_no VARCHAR(80),
    manufacturer VARCHAR(100),
    status ENUM('ACTIVE','INACTIVE','FAULTY','MAINTENANCE') DEFAULT 'ACTIVE',
    installation_date DATE,
    type_id INT NOT NULL,
    location_id INT NOT NULL,
    threshold_id INT,
    
    FOREIGN KEY (type_id) REFERENCES Sensor_Type(type_id),
    FOREIGN KEY (location_id) REFERENCES Location(location_id),
    FOREIGN KEY (threshold_id) REFERENCES Threshold(threshold_id)
);
INSERT INTO Sensor (sensor_name, status, type_id, location_id, threshold_id) VALUES
('Temp Sensor SN1', 'ACTIVE', 1, 1, 1),
('AQ Sensor SN2', 'ACTIVE', 2, 2, 2),
('Noise Sensor SN3', 'ACTIVE', 3, 3, 3),
('Gas Sensor SN4', 'ACTIVE', 4, 4, 4);

CREATE TABLE Sensor_Reading (
    reading_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    sensor_id INT NOT NULL,
    reading_value DECIMAL(12,4) NOT NULL,
    unit VARCHAR(30),
    reading_timestamp DATETIME DEFAULT CURRENT_TIMESTAMP,
    quality_flag ENUM('GOOD','SUSPECT','BAD') DEFAULT 'GOOD',

    FOREIGN KEY (sensor_id) REFERENCES Sensor(sensor_id) ON DELETE CASCADE
);
INSERT INTO Sensor_Reading (sensor_id, reading_value, unit) VALUES
(1, 35.5, '°C'),
(2, 180.0, 'µg/m3'),   -- should trigger alert
(3, 85.0, 'dB'),
(4, 1200.0, 'ppm');    -- should trigger alert

CREATE TABLE Alert (
    alert_id INT AUTO_INCREMENT PRIMARY KEY,
    sensor_id INT NOT NULL,
    alert_type VARCHAR(80),
    alert_message TEXT,
    severity ENUM('LOW','MEDIUM','HIGH','CRITICAL') DEFAULT 'MEDIUM',
    status ENUM('OPEN','ACKNOWLEDGED','RESOLVED') DEFAULT 'OPEN',
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    resolved_at DATETIME,

    FOREIGN KEY (sensor_id) REFERENCES Sensor(sensor_id) ON DELETE CASCADE
);

CREATE TABLE Maintenance_Log (
    log_id INT AUTO_INCREMENT PRIMARY KEY,
    sensor_id INT NOT NULL,
    user_id INT NOT NULL,
    alert_id INT,
    maintenance_date DATE NOT NULL,
    maintenance_type VARCHAR(80),
    description TEXT,

    FOREIGN KEY (sensor_id) REFERENCES Sensor(sensor_id),
    FOREIGN KEY (user_id) REFERENCES User(user_id),
    FOREIGN KEY (alert_id) REFERENCES Alert(alert_id)
);
INSERT INTO Maintenance_Log (sensor_id, user_id, maintenance_date, maintenance_type, description) VALUES
(1, 2, '2025-04-01', 'Calibration', 'Routine check'),
(2, 2, '2025-04-02', 'Repair', 'Sensor recalibrated');

CREATE TABLE User_Location (
    user_id INT,
    location_id INT,
    assigned_at DATE,

    PRIMARY KEY (user_id, location_id),

    FOREIGN KEY (user_id) REFERENCES User(user_id) ON DELETE CASCADE,
    FOREIGN KEY (location_id) REFERENCES Location(location_id) ON DELETE CASCADE
);

ALTER TABLE Maintenance_Log
ADD next_maintenance_date DATE;

-- ----------------------------------------------------------------------------------------------------------------- --
DELIMITER //

-- TRIGGER: Automatically create an alert if a reading is out of bounds
CREATE TRIGGER trg_after_reading_insert
AFTER INSERT ON Sensor_Reading
FOR EACH ROW
BEGIN
    DECLARE v_max DECIMAL(12,4);
    DECLARE v_severity VARCHAR(20);
    
    SELECT t.max_value, t.severity_level INTO v_max, v_severity
    FROM Sensor s 
    JOIN Threshold t ON s.threshold_id = t.threshold_id
    WHERE s.sensor_id = NEW.sensor_id;

    IF NEW.reading_value > v_max THEN
        INSERT INTO Alert (sensor_id, alert_type, alert_message, severity, status)
        VALUES (NEW.sensor_id, 'THRESHOLD_BREACH', 
                CONCAT('High reading detected: ', NEW.reading_value), v_severity, 'OPEN');
    END IF;
END //

-- PROCEDURE: Get sensor count by location
CREATE PROCEDURE GetSensorCount(IN loc_id INT, OUT s_count INT)
BEGIN
    SELECT COUNT(*) INTO s_count FROM Sensor WHERE location_id = loc_id;
END //

DELIMITER ;
