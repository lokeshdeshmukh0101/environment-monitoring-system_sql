# 🌍 Environmental Sensor Monitoring System

A **Java + MySQL based console application** designed to monitor environmental sensors, manage users, generate alerts, and maintain system analytics efficiently.

---

## 📌 Project Overview

This system simulates a **real-world environmental monitoring platform** used in industries and smart cities.

It allows:

* Sensor monitoring
* User management
* Alert generation
* Maintenance tracking
* Data analytics

---

## ⚙️ Technologies Used

| Technology        | Purpose             |
| ----------------- | ------------------- |
| Java (Core)       | Application logic   |
| JDBC              | Database connection |
| MySQL             | Database            |
| SQL Triggers      | Automation          |
| Stored Procedures | Reports             |
| SQL Functions     | Calculations        |

---

## 🏗️ System Architecture

```text id="arch2"
Main Menu → Service Layer → DAO Layer → MySQL Database
```

---

## 🗂️ Modules Implemented

### 🔹 1. Sensor Module

* Add Sensor
* View Sensors
* Update Sensor Status
* Delete Sensor
* Add Sensor Reading
* View Sensor Readings

---

### 🔹 2. Threshold Module

* View Thresholds
* Update Threshold Values

---

### 🔹 3. Location Module

* Add Location
* View Locations (Tabular Format)
* Delete Location

---

### 🔹 4. User Module (User Management)

* Register New User
* View Users (Tabular Format)
* Update User Role (ADMIN / OPERATOR / VIEWER)
* Delete User

---

### 🔹 5. Maintenance Module

* Add Maintenance Log
* View Maintenance Logs
* Automatic Next Maintenance Calculation

---

### 🔹 6. Alert Module

* View Alerts (Sensor Name + Time + Severity)
* Alerts auto-generated using triggers

---

### 🔹 7. Analytics Module

* Sensors per Location
* Sensors by Type
* Average Sensor Readings
* Recent Alerts
* Maintenance Schedule

---

## ⚡ Key Features

### ✅ User Management

* Role-based system (Admin / Operator / Viewer)

### ✅ Sensor Monitoring

* Real-time sensor data recording

### ✅ Automatic Alerts (Trigger)

* Alerts generated when threshold is exceeded

### ✅ Maintenance Tracking

* Automatic next service date

### ✅ Data Analytics

* Multiple SQL-based reports

---

## 🔄 SQL Automation

### 🔸 Triggers

* `trg_auto_alert` → generates alerts automatically
* `trg_next_maintenance` → calculates next service date

---

### 🔸 Stored Procedures

* `sp_sensors_per_location()`
* `sp_maintenance_report()`
* `sp_alert_summary()`

---

### 🔸 Functions

* `fn_sensor_count(typeId)`
* `fn_is_critical(sensorId)`

---

## 📊 Database Tables

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

## ▶️ How to Run

### Step 1

Import database:

```text id="run1"
database/env_monitoring.sql
```

### Step 2

Update DB connection:

```text id="run2"
DBConnection.java
```

### Step 3

Run:

```text id="run3"
Main.java
```

---

## 📊 Sample Flow

```text id="flow1"
Add Location → Add Sensor → Add Reading → Alert Triggered → Add Maintenance → View Analytics
```

---

## 🧠 Concepts Used

### Java

* OOP (Inheritance, Polymorphism)
* Interfaces
* Exception Handling
* Collections (List)
* Packages

### DBMS

* Normalization
* Joins
* Triggers
* Procedures
* Functions

---

## 🌐 Applications

* Smart City Monitoring
* Pollution Control Systems
* Industrial Safety Monitoring
* Environmental Research

---

## 🚀 Future Enhancements

* Web Interface (Spring Boot)
* IoT Integration
* Real-time dashboards
* Email/SMS alerts

---

## 👨‍💻 Author

Lokesh Deshmukh
B.Tech Computer Science Engineering

Lakshya Agarwal
B.Tech Computer Science Engineering

Academic Project – Environmental Monitoring System
(DBMS + Java + MySQL)

---
