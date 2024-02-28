// CS-280
// Jared Bechtold
// Project 1
// 2/20/2024


import java.util.ArrayList;
import java.util.Scanner;


class Car {
    // Car instance variables
    private static int lastCarID = 0; // Keeps track of the previous car ID
    private int carID;
    private String carType;
    private String make;
    private String model;
    private int capacity;
    private String color;
    private int mileage;
    private boolean reserved;

    // Parameterized constructor for car
    public Car(String carType, String make, String model, int capacity, String color, 
    int mileage, boolean reserved) {
        carID = ++lastCarID; // Sets carID to 1 more than the previous car ID
        this.carType = carType;
        this.make = make;
        this.model = model;
        this.capacity = capacity;
        this.color = color;
        this.mileage = mileage;
        this.reserved = reserved;
    }

    // Car getters
    public int getCarID() { return carID; }
    public String getCarType() { return carType; }
    public String getMake() { return make; }
    public String getModel() { return model; }
    public int getCapacity() { return capacity; }
    public String getColor() { return color; }
    public int getMileage() { return mileage; }
    public boolean getReserved() { return reserved; }

    // Car setters
    public void setMileage(int mileage) { this.mileage = mileage; }
    public void setReserved(boolean reserved) { this.reserved = reserved; }

    // Prints the given cars info
    public void getCarInfo() {
        String reservationStatus;
        // Check if reserved
        if (this.getReserved() == true) {
            reservationStatus = "Reserved";
        } else {
            reservationStatus = "Available";
        }
        // Print formatting
        System.out.printf("""
        ID: %d | Type: %s | Make: %s | Model: %s | Capacity: %d | Color: %s | Mileage: %d | %s\n""", 
        getCarID(), getCarType(), getMake(), getModel(), getCapacity(),
        getColor(), getMileage(), reservationStatus);
    }

    // Prints info for every car in the system
    public static void listCars(ArrayList<Car> cars) {
        for (Car car : cars) {
            car.getCarInfo();
        }
    }

    // Prints info for any cars that are not reserved
    public static void listAvailableCars(ArrayList<Car> cars) {
        for (Car car : cars) {
            if (car.getReserved() == false) {
                car.getCarInfo();
            }
        }
    }

    // Prints info for any cars matching the given car type
    public static void searchCar(ArrayList<Car> cars, String carType) {
        for (Car car : cars) {
            if (car.getCarType().equalsIgnoreCase(carType) == true) {
                car.getCarInfo();
            }
        }
    }

    // Prints info for cars with a capacity >= the given capacity
    public static void searchCar(ArrayList<Car> cars, int capacity) {
        for (Car car : cars) {
            if (car.getCapacity() >= capacity) {
                car.getCarInfo();
            }
        }
    }

    // Prints info for cars with matching type and capacity >= to the given capacity
    public static void searchCar(ArrayList<Car> cars, String carType, int capacity) {
        for (Car car : cars) {
            // Check both carType and capacity
            if (car.getCarType().equalsIgnoreCase(carType) == true && car.getCapacity() >= capacity) {
                car.getCarInfo();
            }
        }
    }

    // Removes car object from the Car ArrayList
    public static void deleteCar(ArrayList<Car> cars, Car car) {
        cars.remove(car);
    }
}


class Reservation {
    private static int lastResID = 0; // Keeps track of the previous reservation ID
    private int resID;
    private int carID;
    private String firstName;
    private String lastName;
    private String dateOfBirth;
    private int phoneNumber;
    private String email;
    private int resLength;
    private int numOfDrivers;


    public Reservation(int carID, String firstName, String lastName, String dateOfBirth, int phoneNumber, 
    String email, int resLength, int numOfDrivers) {
        resID = ++lastResID; // Set resID to previous resID plus one
        this.carID = carID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.resLength = resLength;
        this.numOfDrivers = numOfDrivers;
    }

    // Reservation getters
    public int getResID() { return resID; }
    public int getCarID() { return carID; }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public String getDateOfBirth() { return dateOfBirth; }
    public int getPhoneNumber() { return phoneNumber; }
    public String getEmail() { return email; }
    public int getResLength() { return resLength; }
    public int getNumOfDrivers() { return numOfDrivers; }

    // Prints info of the given reservation
    public void getReservationInfo() {
        System.out.printf("""
        Reservation ID: %d | Car ID: %d | Name: %s %s | DOB: %s | Phone #: %d | Email: %s | Length: %d | Drivers: %d\n""", 
        getResID(), getCarID(), getFirstName(), getLastName(), getDateOfBirth(), 
        getPhoneNumber(), getEmail(), getResLength(), getNumOfDrivers());
    }

    // Lists all reservations and their info
    public static void listReservations(ArrayList<Reservation> reservations) {
        for (Reservation reservation : reservations) {
            reservation.getReservationInfo();
        }
    }

    // Remove reservation object from the Reservation ArrayList
    public static void deleteReservation(ArrayList<Reservation> reservations, Reservation res) {
        reservations.remove(res);
    }

    // Creates and prints invoice
    public void printInvoice(ArrayList<Car> cars) {
        int typePrice = 0;

        System.out.println("\n" + "-".repeat(10) + " Invoice " + "-".repeat(10));
        for (Car car : cars) {
            if (car.getCarID() == getCarID()) {
                if (car.getCarType().equalsIgnoreCase("Sedan")) {
                    typePrice = 100;
                }
                else if (car.getCarType().equalsIgnoreCase("Coupe")) {
                    typePrice = 150;
                }
                else if (car.getCarType().equalsIgnoreCase("SUV")) {
                    typePrice = 200;
                }
                else if (car.getCarType().equalsIgnoreCase("Truck")) {
                    typePrice = 250;
                }
                System.out.printf("Base price per day for %s: $%d\n", car.getCarType(), typePrice);
                System.out.printf("Price for days rented: %d\n", (typePrice * getResLength()));
                System.out.printf("Price per driver: $50");
                System.out.printf("\nTotal: $%d", (typePrice * getResLength()) + (getResLength() * 20));
            }
        }
    }
}


public class CarRentalSystem {

    public static void main(String[] args) {
        // Lists to hold car, reservation and invoice objects
        ArrayList<Car> cars = new ArrayList<Car>();
        ArrayList<Reservation> reservations = new ArrayList<Reservation>();

        Car car1 = new Car("Sedan", "Pontiac", "Grand Am", 5, "Silver", 200000, false);
        Car car2 = new Car("Truck", "Chevrolet", "Silverado HD", 6, "White", 60000, true);
        cars.add(car1);
        cars.add(car2);

        Reservation res1 = new Reservation(1, "Jared", "Bechtold", "01/01/1969", 1112223344, "jared.bechtold@und.edu", 2, 1);
        Reservation res2 = new Reservation(2, "Connor", "McGreggor", "02/02/1985", 1238904758, "N/A", 10, 2);
        reservations.add(res1);
        reservations.add(res2);

        mainMenu(cars, reservations);
    }

    // Provides the main menu fro the program
    public static void mainMenu(ArrayList<Car> cars, ArrayList<Reservation> reservations) {
        String userInput;
        int resID;
        Scanner scanner = new Scanner(System.in);

        while (true) {
        // Prints main menu options and gets user input
            System.out.println("\n" + "-".repeat(10) + " Car Rental Management System " + "-".repeat(10));
            System.out.println("1. Car Options\n2. Reservation Options\n3. Print Invoice\n0. Quit");
            System.out.print("\nSelect an option: ");
            userInput = scanner.nextLine();
            // Checks user input and calls appropriate method or breaks
            if (userInput.equals("1")) {
                carMenu(cars);
            } 
            else if (userInput.equals("2")) {
                resMenu(reservations, cars);
            }
            else if (userInput.equals("3")) {
                // Create invoice
                System.out.print("Enter reservation id: ");
                resID = scanner.nextInt();
                scanner.nextLine(); // Consume newline
                for (Reservation res : reservations) {
                    if (res.getResID() == resID) {
                        res.printInvoice(cars);
                    }
                }
            } 
            else if (userInput.equals("0")) {
                break;
            } 
            else {
                System.out.println("\nPlease select a valid option\n");
            }
        }
        scanner.close();
    }

    // Provides car menu for the program
    public static void carMenu(ArrayList<Car> cars) {
        String userInput;
        int carID;
        String type;
        String make;
        String model;
        int capacity;
        String color;
        int mileage;
        Scanner scanner = new Scanner(System.in);

        while (true) {
        // Prints car menu options and gets selection
            System.out.println("\n" + "-".repeat(10) + " Car Options " + "-".repeat(10));
            System.out.println("1. Add Car\n2. List Cars\n3. List Available Cars\n4. Get Car Info\n" +
                                "5. Search Car\n6. Delete Car\n7. Set Car Mileage\n0. Back");
            System.out.print("\nSelect an option: ");
            userInput = scanner.nextLine();

            if (userInput.equals("1")) {
            // Get input for each car attribute and add to list
                System.out.print("\nEnter type (Sedan, Coupe, Truck, SUV): ");
                type = scanner.nextLine();
                System.out.print("Enter make: ");
                make = scanner.nextLine();
                System.out.print("Enter model: ");
                model = scanner.nextLine();
                System.out.print("Enter capacity: ");
                capacity = scanner.nextInt();
                scanner.nextLine(); // Consume newline character
                System.out.print("Enter color: ");
                color = scanner.nextLine();
                System.out.print("Enter mileage: ");
                mileage = scanner.nextInt();
                scanner.nextLine();
                // Create and add car to list
                cars.add(new Car(type, make, model, capacity, color, mileage, false));
            } 
            else if (userInput.equals("2")) {
                // List cars
                System.out.println();
                Car.listCars(cars);
            } 
            else if (userInput.equals("3")) {
                // List available cars
                System.out.println();
                Car.listAvailableCars(cars);    
            } 
            else if (userInput.equals("4")) {
                // Get car info
                System.out.println();
                System.out.print("Enter car id: ");
                carID = scanner.nextInt();
                scanner.nextLine();
                System.out.println();
                for (Car car : cars) {
                // Get car with matching ID    
                    if (car.getCarID() == carID) {
                        car.getCarInfo();
                    }
                }
            } 
            else if (userInput.equals("5")) {
                System.out.println();
                System.out.println("Fill in both answers, or one answer and 0");
                System.out.print("Enter car type or 0: ");
                userInput = scanner.nextLine();
                if (userInput.equals("0") == false) {
                    // Entered type path
                    type = userInput;
                    System.out.print("Enter capacity or 0: ");
                    int input = scanner.nextInt();
                    scanner.nextLine(); // consume newline
                    if (input != 0) {
                        // Entered type and capacity path
                        capacity = input;
                        System.out.println();
                        Car.searchCar(cars, type, capacity); // call with both parameters
                    } else {
                        // Entered only type path
                        System.out.println();
                        Car.searchCar(cars, type); // call with only type
                    }
                } else {
                    // Enter only capacity path
                    System.out.print("Enter capacity: ");
                    capacity = scanner.nextInt();
                    scanner.nextLine(); // consume newline
                    System.out.println();
                    Car.searchCar(cars, capacity); // call with only capacity
                }
            }
            else if (userInput.equals("6")) {
                // Delete car
                System.out.print("\nEnter car id: ");
                carID = scanner.nextInt();
                scanner.nextLine(); // conmsume newline
                for (Car car : cars) {
                    // find car with matching id
                    if (car.getCarID() == carID) {
                        Car.deleteCar(cars, car);
                    }
                }
            }
            else if (userInput.equals("7")) {
                // set mileage
                System.out.print("\nEnter car id: ");
                carID = scanner.nextInt();
                scanner.nextLine(); // conmsume newline
                for (Car car : cars) {
                    // find car with matching id
                    if (car.getCarID() == carID) {
                        System.out.print("\nEnter new mileage: ");
                        mileage = scanner.nextInt();
                        scanner.nextLine(); // consume newline
                        car.setMileage(mileage);
                    }
                }
            }
            else if (userInput.equals("0")) {
                break;
            } 
            else {
                System.out.println("\nPlease select a valid option");
            }
        }
        scanner.close();
    }

    
    public static void resMenu(ArrayList<Reservation> reservations, ArrayList<Car> cars) {
        String userInput;
        int resID;
        int carID;
        String firstName;
        String lastName;
        String dateOfBirth;
        int phoneNumber;
        String email;
        int resLength;
        int numOfDrivers;
        Scanner scanner = new Scanner(System.in);

        while (true) {
            // Prints reservation menu options and gets selection
            System.out.println("\n" + "-".repeat(10) + " Reservation Options " + "-".repeat(10));
            System.out.println("1. Add Reservation\n2. List Reservations\n3. Get Renter Info" + 
            "\n4. Delete Reservation\n0. Back");
            System.out.print("\nSelect an option: ");
            userInput = scanner.nextLine();

            if (userInput.equals("1")) {
                // get input for each res attribute and add to list
                System.out.print("Enter car id: ");
                carID = scanner.nextInt();
                scanner.nextLine();
                System.out.print("Enter first name: ");
                firstName = scanner.nextLine();
                System.out.print("Enter last name: ");
                lastName = scanner.nextLine();
                System.out.print("Enter date of birth (MM/DD/YYYY): ");
                dateOfBirth = scanner.nextLine();
                System.out.print("Enter phone number (xxxxxxxxxx): ");
                phoneNumber = scanner.nextInt();
                scanner.nextLine();
                System.out.print("Enter email: ");
                email = scanner.nextLine();
                System.out.print("Enter reservation length: ");
                resLength = scanner.nextInt();
                scanner.nextLine();
                System.out.print("Enter number of drivers: ");
                numOfDrivers = scanner.nextInt();
                scanner.nextLine();
                // Create and add res to list
                reservations.add(new Reservation(carID, firstName, lastName, dateOfBirth,
                phoneNumber, email, resLength, numOfDrivers));
                for (Car car : cars) {
                    // Set car to reserved
                    if (car.getCarID() == carID) {
                        car.setReserved(true);
                    }
                }
            }
            else if (userInput.equals("2")) {
                // List reservations
                System.out.println();
                Reservation.listReservations(reservations);
            }
            else if (userInput.equals("3")) {
                // Get reservation info
                System.out.print("\nEnter reservation id: ");
                resID = scanner.nextInt();
                scanner.nextLine();
                System.out.println();
                for (Reservation reservation : reservations) { 
                    if (reservation.getResID() == resID) { // check if ids match
                        reservation.getReservationInfo();
                    }
                }
            }
            else if (userInput.equals("4")) {
                // Delete reservation
                System.out.print("Enter reservation id: ");
                resID = scanner.nextInt();
                scanner.nextLine();
                for (Reservation reservation : reservations) { 
                    if (reservation.getResID() == resID) { // check if ids match
                        Reservation.deleteReservation(reservations, reservation);
                    }
                }
            }
            else if (userInput.equals("0")) {
                break;
            }
            else {
                System.out.println("\nPlease enter a valid option");
            }
        }
        scanner.close();
    }
}
