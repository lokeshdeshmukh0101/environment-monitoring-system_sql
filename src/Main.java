import services.*;

import utils.InputUtil;
import utils.TableUtil;

public class Main {

    static SensorService sensorService = new SensorService();
    static UserService userService = new UserService();
    static ReadingService readingService = new ReadingService();
    static AlertService alertService = new AlertService();
    static LocationService locationService = new LocationService();
    static MaintenanceService maintenanceService = new MaintenanceService();
    static AnalyticsService analyticsService = new AnalyticsService();
    static ThresholdService thresholdService = new ThresholdService();

    public static void main(String[] args) {

        while (true) {

            TableUtil.title("MAIN MENU");

            TableUtil.line(2);
            TableUtil.row("Option", "Module");
            TableUtil.line(2);

            TableUtil.row("1", "Sensor Module");
            TableUtil.row("2", "Threshold Module");
            TableUtil.row("3", "Location Module");
            TableUtil.row("4", "User Module");
            TableUtil.row("5", "Maintenance Module");
            TableUtil.row("6", "Alert Module");
            TableUtil.row("7", "Analytics");
            TableUtil.row("8", "Exit");

            TableUtil.line(2);

            int ch = InputUtil.readInt("Choice: ");

            switch (ch) {
                case 1 -> sensorMenu();
                case 2 -> thresholdMenu();
                case 3 -> locationMenu();
                case 4 -> userMenu();
                case 5 -> maintenanceMenu();
                case 6 -> alertMenu();
                case 7 -> analyticsMenu();
                case 8 -> { return; }
                default -> System.out.println("❌ Invalid choice");
            }
        }
    }

    // ================= SENSOR =================
    static void sensorMenu() {

        while (true) {

            TableUtil.title("SENSOR MODULE");

            TableUtil.line(2);
            TableUtil.row("Option", "Action");
            TableUtil.line(2);

            TableUtil.row("1", "Add Sensor");
            TableUtil.row("2", "View Sensors");
            TableUtil.row("3", "Update Sensor");
            TableUtil.row("4", "Delete Sensor");
            TableUtil.row("5", "Add Sensor Reading");
            TableUtil.row("6", "View Sensor Readings");
            TableUtil.row("7", "Back");

            TableUtil.line(2);

            int ch = InputUtil.readInt("Choice: ");

            try {
                switch (ch) {
                    case 1 -> {
                        sensorService.add(InputUtil.readString("Name: "));
                        System.out.println("✅ Sensor Created Successfully");
                    }
                    case 2 -> sensorService.view();
                    case 3 -> {
                        sensorService.update(
                                InputUtil.readInt("ID: "),
                                InputUtil.readString("Status: ")
                        );
                        System.out.println("✅ Sensor Updated Successfully");
                    }
                    case 4 -> {
                        sensorService.delete(InputUtil.readInt("ID: "));
                        System.out.println("✅ Sensor Deleted Successfully");
                    }
                    case 5 -> {
                    	readingService.add(
                    	        InputUtil.readInt("Sensor ID: "),
                    	        InputUtil.readDouble("Value: ")
                    	);
                        System.out.println("✅ Reading Added Successfully");
                    }
                    case 6 -> readingService.view();
                    case 7 -> { return; }
                }
            } catch (Exception e) {
                System.out.println("❌ Error: " + e.getMessage());
            }
        }
    }

    // ================= USER =================
    static void userMenu() {

        while (true) {

            TableUtil.title("USER MODULE");

            TableUtil.line(2);
            TableUtil.row("Option", "Action");
            TableUtil.line(2);

            TableUtil.row("1", "Add User");
            TableUtil.row("2", "View Users");
            TableUtil.row("3", "Update User Access");
            TableUtil.row("4", "Delete User");
            TableUtil.row("5", "Back");

            TableUtil.line(2);

            int ch = InputUtil.readInt("Choice: ");

            try {
                switch (ch) {
                    case 1 -> {
                        userService.add(
                                InputUtil.readString("Name: "),
                                InputUtil.readString("Role: ")
                        );
                        System.out.println("✅ User Created Successfully");
                    }
                    case 2 -> userService.view();
                    case 3 -> {

                        int id = InputUtil.readInt("User ID: ");

                        System.out.println("Roles: ADMIN / OPERATOR / VIEWER");

                        String role = InputUtil.readString("New Role: ");

                        userService.updateRole(id,role);

                        System.out.println("User role updated successfully");
                    }
                    case 4 -> {
                        userService.delete(InputUtil.readInt("User ID: "));
                        System.out.println("✅ User Deleted Successfully");
                    }
                    case 5 -> { return; }
                }
            } catch (Exception e) {
                System.out.println("❌ Error: " + e.getMessage());
            }
        }
    }

    // ================= LOCATION =================
    static void locationMenu() {

        while (true) {

            TableUtil.title("LOCATION MODULE");

            TableUtil.line(2);
            TableUtil.row("Option", "Action");
            TableUtil.line(2);

            TableUtil.row("1", "Add Location");
            TableUtil.row("2", "View Locations");
            TableUtil.row("3", "Delete Location");
            TableUtil.row("4", "Back");

            TableUtil.line(2);

            int ch = InputUtil.readInt("Choice: ");

            try {
                switch (ch) {
                    case 1 -> {
                        locationService.add(
                                InputUtil.readString("Name: "),
                                InputUtil.readString("City: ")
                        );
                        System.out.println("✅ Location Created Successfully");
                    }
                    case 2 -> locationService.view();
                    case 3 -> {
                        locationService.delete(InputUtil.readInt("Location ID: "));
                        System.out.println("✅ Location Deleted Successfully");
                    }
                    case 4 -> { return; }
                }
            } catch (Exception e) {
                System.out.println("❌ Error: " + e.getMessage());
            }
        }
    }

    // ================= MAINTENANCE =================
    static void maintenanceMenu() {

        while (true) {

            TableUtil.title("MAINTENANCE MODULE");

            TableUtil.line(2);
            TableUtil.row("Option", "Action");
            TableUtil.line(2);

            TableUtil.row("1", "Add Log");
            TableUtil.row("2", "View Logs");
            TableUtil.row("3", "Back");

            TableUtil.line(2);

            int ch = InputUtil.readInt("Choice: ");

            try {
                switch (ch) {
                case 1 -> {
                    maintenanceService.add(
                            InputUtil.readInt("Sensor ID: "),
                            InputUtil.readDate("Maintenance Date (yyyy-mm-dd): "),
                            InputUtil.readString("Maintenance Type: ")
                    );
                    System.out.println("✅ Maintenance Log Added Successfully");
                }
                    case 2 -> maintenanceService.view();
                    case 3 -> { return; }
                }
            } catch (Exception e) {
                System.out.println("❌ Error: " + e.getMessage());
            }
        }
    }

    // ================= ALERT =================
    static void alertMenu() {

        while (true) {

            TableUtil.title("ALERT MODULE");

            TableUtil.line(2);
            TableUtil.row("Option", "Action");
            TableUtil.line(2);

            TableUtil.row("1", "View Alerts");
            TableUtil.row("2", "Back");

            TableUtil.line(2);

            int ch = InputUtil.readInt("Choice: ");

            try {
                switch (ch) {
                    case 1 -> alertService.view();
                    case 2 -> { return; }
                }
            } catch (Exception e) {
                System.out.println("❌ Error: " + e.getMessage());
            }
        }
    }

    // ================= THRESHOLD =================
    static void thresholdMenu(){

        while(true){

            TableUtil.title("THRESHOLD MODULE");

            TableUtil.line(2);
            TableUtil.row("Option","Action");
            TableUtil.line(2);

            TableUtil.row("1","View Thresholds");
            TableUtil.row("2","Update Threshold");
            TableUtil.row("3","Back");

            TableUtil.line(2);

            int ch = InputUtil.readInt("Choice: ");

            try{

                switch(ch){

                    case 1 -> thresholdService.view();

                    case 2 -> {

                        int id = InputUtil.readInt("Threshold ID: ");
                        double min = InputUtil.readDouble("New Min Value: ");
                        double max = InputUtil.readDouble("New Max Value: ");

                        thresholdService.update(id,min,max);

                        System.out.println("✅ Threshold Updated Successfully");
                    }

                    case 3 -> { return; }

                }

            }catch(Exception e){
                System.out.println(e.getMessage());
            }
        }
    }

    // ================= ANALYTICS =================
    static void analyticsMenu() {

        while (true) {

            TableUtil.title("ANALYTICS");

            TableUtil.line(2);
            TableUtil.row("Option", "Action");
            TableUtil.line(2);

            TableUtil.row("1", "Sensors per Location");
            TableUtil.row("2", "Sensors by Type");
            TableUtil.row("3", "Recent Alerts");
            TableUtil.row("4", "Avg Sensor Readings");
            TableUtil.row("5", "Maintenance Schedule");
            TableUtil.row("6", "Back");

            TableUtil.line(2);

            int ch = InputUtil.readInt("Choice: ");

            switch (ch) {
                case 1 -> analyticsService.sensorsPerLocation();
                case 2 -> analyticsService.sensorsByType();
                case 3 -> analyticsService.recentAlerts();
                case 4 -> analyticsService.avgReadings();
                case 5 -> analyticsService.maintenanceSchedule();
                case 6 -> { return; }
            }
        }
    }
}