-- ============================================================
-- ENVIRONMENT MONITORING DATABASE (FINAL SUBMISSION)
-- ============================================================

-- =========================
-- 1. DATABASE
-- =========================
CREATE DATABASE IF NOT EXISTS env_monitoring;
USE env_monitoring;

-- =========================
-- 2. TABLES
-- =========================

CREATE TABLE Sensor_Type (
    type_id INT AUTO_INCREMENT PRIMARY KEY,
    type_name VARCHAR(100) UNIQUE NOT NULL,
    description TEXT,
    measurement_unit VARCHAR(30),
    category VARCHAR(50)
);

CREATE TABLE Location (
    location_id INT AUTO_INCREMENT PRIMARY KEY,
    location_name VARCHAR(120),
    city VARCHAR(80),
    state VARCHAR(80)
);

CREATE TABLE Threshold (
    threshold_id INT AUTO_INCREMENT PRIMARY KEY,
    parameter_name VARCHAR(100),
    min_value DECIMAL(10,2),
    max_value DECIMAL(10,2),
    severity_level ENUM('LOW','MEDIUM','HIGH','CRITICAL'),
    unit VARCHAR(30),
    CHECK (min_value < max_value)
);

CREATE TABLE User (
    user_id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(60) UNIQUE,
    email VARCHAR(120) UNIQUE,
    role ENUM('ADMIN','OPERATOR','VIEWER'),
    created_date DATE DEFAULT CURRENT_DATE
);

CREATE TABLE Sensor (
    sensor_id INT AUTO_INCREMENT PRIMARY KEY,
    sensor_name VARCHAR(100),
    model_no VARCHAR(50),
    manufacturer VARCHAR(100),
    status ENUM('ACTIVE','INACTIVE','FAULTY','MAINTENANCE') DEFAULT 'ACTIVE',
    installation_date DATE,
    type_id INT,
    location_id INT,
    threshold_id INT,
    FOREIGN KEY (type_id) REFERENCES Sensor_Type(type_id),
    FOREIGN KEY (location_id) REFERENCES Location(location_id),
    FOREIGN KEY (threshold_id) REFERENCES Threshold(threshold_id)
);

CREATE TABLE Sensor_Reading (
    reading_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    sensor_id INT,
    reading_value DECIMAL(10,2),
    reading_timestamp DATETIME DEFAULT CURRENT_TIMESTAMP,
    quality_flag ENUM('GOOD','BAD'),
    FOREIGN KEY (sensor_id) REFERENCES Sensor(sensor_id) ON DELETE CASCADE
);

CREATE TABLE Alert (
    alert_id INT AUTO_INCREMENT PRIMARY KEY,
    sensor_id INT,
    alert_message VARCHAR(255),
    severity ENUM('LOW','MEDIUM','HIGH','CRITICAL'),
    status ENUM('OPEN','RESOLVED') DEFAULT 'OPEN',
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (sensor_id) REFERENCES Sensor(sensor_id) ON DELETE CASCADE
);

CREATE TABLE Maintenance_Log (
    log_id INT AUTO_INCREMENT PRIMARY KEY,
    sensor_id INT,
    user_id INT,
    maintenance_date DATE,
    maintenance_type VARCHAR(100),
    next_maintenance_date DATE,
    FOREIGN KEY (sensor_id) REFERENCES Sensor(sensor_id),
    FOREIGN KEY (user_id) REFERENCES User(user_id)
);

CREATE TABLE User_Location (
    user_id INT,
    location_id INT,
    PRIMARY KEY (user_id, location_id),
    FOREIGN KEY (user_id) REFERENCES User(user_id),
    FOREIGN KEY (location_id) REFERENCES Location(location_id)
);

-- =========================
-- 3. SAMPLE DATA
-- =========================

INSERT INTO Location VALUES
(1,'Shivaji Nagar','Pune','Maharashtra'),
(2,'Hinjewadi IT Park','Pune','Maharashtra'),
(3,'Viman Nagar','Pune','Maharashtra');

INSERT INTO Sensor_Type VALUES
(1,'Temperature','Measures temp','°C','Environmental'),
(2,'Humidity','Measures moisture','%','Environmental'),
(3,'Air Quality','Measures AQI','ppm','Environmental');

INSERT INTO Threshold VALUES
(1,'Temperature',0,40,'HIGH','°C'),
(2,'Humidity',20,80,'MEDIUM','%'),
(3,'Air Quality',0,150,'CRITICAL','ppm');

INSERT INTO User VALUES
(1,'admin','admin@env.com','ADMIN',CURDATE()),
(2,'operator','op@env.com','OPERATOR',CURDATE()),
(3,'viewer','view@env.com','VIEWER',CURDATE());

INSERT INTO Sensor VALUES
(1,'Temp Sensor','TMP100','Bosch','ACTIVE','2025-01-01',1,1,1),
(2,'Humidity Sensor','HMD200','Siemens','ACTIVE','2025-02-01',2,2,2),
(3,'AQ Sensor','AQ300','Honeywell','ACTIVE','2025-03-01',3,3,3);

INSERT INTO Sensor_Reading(sensor_id,reading_value,quality_flag) VALUES
(1,35,'GOOD'),
(1,45,'BAD'),
(2,60,'GOOD');

INSERT INTO Maintenance_Log(sensor_id,user_id,maintenance_date,maintenance_type) VALUES
(1,1,'2025-04-01','Calibration'),
(2,2,'2025-04-02','Repair'),
(3,2,'2025-04-03','Cleaning');

INSERT INTO User_Location VALUES
(1,1),(2,2),(3,3);

-- =========================
-- 4. TRIGGERS
-- =========================

DELIMITER $$

CREATE TRIGGER trg_auto_alert
AFTER INSERT ON Sensor_Reading
FOR EACH ROW
BEGIN
    DECLARE max_val DECIMAL(10,2);

    SELECT t.max_value INTO max_val
    FROM Sensor s
    JOIN Threshold t ON s.threshold_id = t.threshold_id
    WHERE s.sensor_id = NEW.sensor_id;

    IF NEW.reading_value > max_val THEN
        INSERT INTO Alert(sensor_id,alert_message,severity)
        VALUES (NEW.sensor_id,
                CONCAT('Threshold exceeded: ', NEW.reading_value),
                'HIGH');
    END IF;
END$$

DELIMITER ;

DELIMITER $$

CREATE TRIGGER trg_next_maintenance
BEFORE INSERT ON Maintenance_Log
FOR EACH ROW
BEGIN
    SET NEW.next_maintenance_date =
    DATE_ADD(NEW.maintenance_date, INTERVAL 30 DAY);
END$$

DELIMITER ;

-- =========================
-- 5. STORED PROCEDURES
-- =========================

DELIMITER $$

CREATE PROCEDURE sp_sensors_per_location()
BEGIN
    SELECT l.location_name, COUNT(s.sensor_id) AS total_sensors
    FROM Sensor s
    JOIN Location l ON s.location_id = l.location_id
    GROUP BY l.location_name;
END$$

DELIMITER ;

DELIMITER $$

CREATE PROCEDURE sp_maintenance_report()
BEGIN
    SELECT s.sensor_name,
           m.maintenance_date,
           m.next_maintenance_date
    FROM Maintenance_Log m
    JOIN Sensor s ON m.sensor_id = s.sensor_id;
END$$

DELIMITER ;

DELIMITER $$

CREATE PROCEDURE sp_alert_summary()
BEGIN
    SELECT sensor_id, COUNT(*) AS total_alerts
    FROM Alert
    GROUP BY sensor_id;
END$$

DELIMITER ;

-- =========================
-- 6. FUNCTIONS
-- =========================

DELIMITER $$

CREATE FUNCTION fn_sensor_count(typeId INT)
RETURNS INT
DETERMINISTIC
BEGIN
    DECLARE total INT;
    SELECT COUNT(*) INTO total FROM Sensor WHERE type_id = typeId;
    RETURN total;
END$$

DELIMITER ;

DELIMITER $$

CREATE FUNCTION fn_is_critical(sensorId INT)
RETURNS VARCHAR(10)
DETERMINISTIC
BEGIN
    DECLARE cnt INT;

    SELECT COUNT(*) INTO cnt
    FROM Alert
    WHERE sensor_id = sensorId AND severity='HIGH';

    IF cnt > 3 THEN
        RETURN 'CRITICAL';
    ELSE
        RETURN 'NORMAL';
    END IF;
END$$

DELIMITER ;

