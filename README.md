# 🌍 Environmental Sensor Monitoring System

A **Java + MySQL based console application** designed to monitor environmental conditions using sensors, generate alerts, and manage maintenance efficiently.

---

## 📌 Project Overview

This system simulates a **real-world environmental monitoring platform** used in smart cities and industries.

It allows users to:

* Manage sensors and locations
* Record environmental readings
* Automatically detect threshold violations
* Generate alerts using SQL triggers
* Maintain service logs and schedules
* Analyze system data using SQL procedures

---

## ⚙️ Technologies Used

| Technology        | Purpose               |
| ----------------- | --------------------- |
| Java (Core)       | Application logic     |
| JDBC              | Database connectivity |
| MySQL             | Data storage          |
| SQL Triggers      | Automation            |
| Stored Procedures | Analytics             |
| SQL Functions     | Calculations          |
| OOP Concepts      | Design structure      |

---

## 🏗️ Project Architecture

The system follows a **3-layer architecture**:

```
Main (UI Layer)
   ↓
Service Layer (Business Logic)
   ↓
DAO Layer (Database Access)
   ↓
MySQL Database
```

---

## 🗂️ Project Structure

```
env-monitoring-system/
│
├── src/
│   ├── config
│   ├── model
│   ├── dao
│   ├── service
│   ├── util
│   └── main
│
├── database
│   └── env_monitoring.sql
│
└── README.md
```

---

## 🗄️ Database Design

The system uses a **relational database** with the following tables:

* Sensor_Type
* Location
* Sensor
* Sensor_Reading
* Threshold
* Alert
* Maintenance_Log
* User
* User_Location

---

## ⚡ Key Features

### 🔹 Sensor Management

* Add / Update / Delete sensors
* Track installation and status

### 🔹 Sensor Readings

* Record real-time environmental data
* Store timestamped readings

### 🔹 Threshold Monitoring

* Define safe ranges for parameters
* Detect abnormal conditions

### 🔹 Automatic Alerts (Trigger)

* Alerts generated when readings exceed limits

### 🔹 Maintenance Scheduling

* Logs maintenance activities
* Automatically calculates next service date

### 🔹 Analytics Module

* Sensors per location
* Sensors by type
* Average readings
* Maintenance schedule
* Alert summaries

---

## 🔄 SQL Automation

### 🔸 Trigger Used

* `trg_auto_alert` → Generates alerts automatically
* `trg_next_maintenance` → Calculates next maintenance date

---

### 🔸 Stored Procedures

* `sp_sensors_per_location()`
* `sp_maintenance_report()`
* `sp_alert_summary()`

---

### 🔸 SQL Functions

* `fn_sensor_count(typeId)` → Returns number of sensors
* `fn_is_critical(sensorId)` → Detects critical sensor

---

## ▶️ How to Run the Project

### Step 1: Setup Database

Import SQL file:

```
database/env_monitoring.sql
```

---

### Step 2: Configure Database Connection

Update credentials in:

```
DBConnection.java
```

Example:

```java
jdbc:mysql://localhost:3306/env_monitoring
```

---

### Step 3: Run Application

Run:

```
Main.java
```

---

## 📊 Sample Inputs

### Add Sensor

```
Temp Sensor A
TMP100
Bosch
2025-04-01
1
1
1
```

### Add Reading

```
Sensor ID: 1
Value: 45
```

👉 This will automatically generate an alert.

---

## 🧠 Concepts Used

### Java Concepts

* Inheritance
* Interfaces
* Polymorphism
* Exception Handling
* Packages
* Collections (List)

### DBMS Concepts

* Normalization
* Primary & Foreign Keys
* Joins
* Triggers
* Procedures
* Functions

---

## 🌐 Real-World Applications

* Smart Cities
* Pollution Monitoring
* Industrial Safety Systems
* Weather Monitoring
* Agriculture Systems

---

## 🚀 Future Improvements

* Web Dashboard (Spring Boot / React)
* Real-time sensor integration (IoT)
* Email/SMS alert system
* Machine learning for predictive maintenance

---

## 👨‍💻 Author

**Environmental Monitoring System**
Academic Project (DBMS + Java)

---

## ⭐ GitHub Usage

If you find this project useful:

* ⭐ Star the repository
* 🍴 Fork it
* 🛠️ Improve it

---
