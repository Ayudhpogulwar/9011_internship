
import java.util.ArrayList;
import java.util.Scanner;

abstract class Passenger {
    private String name;
    private int age;

    public Passenger(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public abstract double getDiscountPercentage();
}

class RegularPassenger extends Passenger {
    public RegularPassenger(String name, int age) {
        super(name, age);
    }

    @Override
    public double getDiscountPercentage() {
        return 0;
    }
}

class StudentPassenger extends Passenger {
    public StudentPassenger(String name, int age) {
        super(name, age);
    }

    @Override
    public double getDiscountPercentage() {
        return 10;
    }
}

class Flight {
    private String flightCode;
    private String airline;
    private String source;
    private String destination;
    private int availableSeats;
    private double baseFare;

    public Flight(String flightCode, String airline, String source,
                  String destination, int availableSeats, double baseFare) {
        this.flightCode = flightCode;
        this.airline = airline;
        this.source = source;
        this.destination = destination;
        this.availableSeats = availableSeats;
        this.baseFare = baseFare;
    }

    public String getFlightCode() {
        return flightCode;
    }

    public String getAirline() {
        return airline;
    }

    public String getSource() {
        return source;
    }

    public String getDestination() {
        return destination;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public double getBaseFare() {
        return baseFare;
    }

    public boolean hasAvailableSeats(int tickets) {
        return tickets > 0 && tickets <= availableSeats;
    }

    public void bookSeats(int tickets) {
        if (hasAvailableSeats(tickets)) {
            availableSeats -= tickets;
        }
    }

    public void displayFlight() {
        System.out.println(
            flightCode + " | " + airline + " | " + source + " -> " +
            destination + " | Seats: " + availableSeats +
            " | Base Fare: Rs." + String.format("%.2f", baseFare)
        );
    }
}

class Booking {
    private String bookingId;
    private Flight flight;
    private Passenger passenger;
    private int tickets;
    private double baseFare;
    private double discount;
    private double totalFare;

    public Booking(String bookingId, Flight flight, Passenger passenger,
                   int tickets, double baseFare, double discount,
                   double totalFare) {
        this.bookingId = bookingId;
        this.flight = flight;
        this.passenger = passenger;
        this.tickets = tickets;
        this.baseFare = baseFare;
        this.discount = discount;
        this.totalFare = totalFare;
    }

    public void displaySummary() {
        System.out.println("\n========== BOOKING SUMMARY ==========");
        System.out.println("Booking ID: " + bookingId);
        System.out.println("Passenger Name: " + passenger.getName());
        System.out.println("Passenger Age: " + passenger.getAge());
        System.out.println("Passenger Type: " +
            (passenger instanceof StudentPassenger ? "Student" : "Regular"));
        System.out.println("Flight Code: " + flight.getFlightCode());
        System.out.println("Airline: " + flight.getAirline());
        System.out.println("Route: " + flight.getSource() +
                           " -> " + flight.getDestination());
        System.out.println("Number of Tickets: " + tickets);
        System.out.println("Base Fare per Ticket: Rs." +
                           String.format("%.2f", baseFare));
        System.out.println("Total Base Fare: Rs." +
                           String.format("%.2f", baseFare * tickets));
        System.out.println("Discount: Rs." +
                           String.format("%.2f", discount));
        System.out.println("Final Amount: Rs." +
                           String.format("%.2f", totalFare));
        System.out.println("Booking Status: Confirmed");
        System.out.println("=====================================");
    }
}

class FlightBookingSystem {
    private ArrayList<Flight> flights;
    private Booking latestBooking;
    private Scanner sc;
    private int bookingCounter;

    public FlightBookingSystem() {
        flights = new ArrayList<>();
        sc = new Scanner(System.in);
        latestBooking = null;
        bookingCounter = 1001;

        flights.add(new Flight("AI101", "Air India", "Nagpur",
                               "Mumbai", 50, 4500));
        flights.add(new Flight("6E202", "IndiGo", "Nagpur",
                               "Delhi", 40, 6000));
        flights.add(new Flight("SG303", "SpiceJet", "Mumbai",
                               "Bengaluru", 30, 5000));
    }

    public void displayFlights() {
        System.out.println("\n========== AVAILABLE FLIGHTS ==========");
        System.out.println("Code | Airline | Route | Seats | Base Fare");

        for (Flight flight : flights) {
            flight.displayFlight();
        }
    }

    public Flight findFlightByCode(String code) {
        for (Flight flight : flights) {
            if (flight.getFlightCode().equalsIgnoreCase(code)) {
                return flight;
            }
        }

        return null;
    }

    public boolean validateBookingDetails(Flight flight, String name,
                                          int age, int tickets) {
        if (flight == null) {
            System.out.println("Error: Flight code not found.");
            return false;
        }

        if (name == null || name.trim().isEmpty()) {
            System.out.println("Error: Passenger name cannot be empty.");
            return false;
        }

        if (age <= 0 || age > 120) {
            System.out.println("Error: Enter a valid age.");
            return false;
        }

        if (tickets <= 0) {
            System.out.println("Error: Number of tickets must be greater than zero.");
            return false;
        }

        if (!flight.hasAvailableSeats(tickets)) {
            System.out.println("Error: Not enough seats available.");
            return false;
        }

        return true;
    }

    public double calculateFare(Flight flight, Passenger passenger,
                                int tickets) {
        double totalBaseFare = flight.getBaseFare() * tickets;
        double discountAmount =
            totalBaseFare * passenger.getDiscountPercentage() / 100;

        return totalBaseFare - discountAmount;
    }

    public void bookFlight() {
        displayFlights();

        System.out.print("\nEnter flight code: ");
        String code = sc.nextLine().trim();

        Flight flight = findFlightByCode(code);

        System.out.print("Enter passenger name: ");
        String name = sc.nextLine().trim();

        int age;
        int tickets;

        try {
            System.out.print("Enter passenger age: ");
            age = Integer.parseInt(sc.nextLine().trim());

            System.out.print("Enter number of tickets: ");
            tickets = Integer.parseInt(sc.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("Error: Age and ticket count must be valid integers.");
            return;
        }

        if (!validateBookingDetails(flight, name, age, tickets)) {
            return;
        }

        System.out.println("\nPassenger Category:");
        System.out.println("1. Regular Passenger");
        System.out.println("2. Student Passenger");
        System.out.print("Enter category: ");

        int category;

        try {
            category = Integer.parseInt(sc.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("Error: Invalid passenger category.");
            return;
        }

        if (category != 1 && category != 2) {
            System.out.println("Error: Select category 1 or 2.");
            return;
        }

        Passenger passenger;

        if (category == 2) {
            passenger = new StudentPassenger(name, age);
        } else {
            passenger = new RegularPassenger(name, age);
        }

        double totalBaseFare = flight.getBaseFare() * tickets;
        double totalFare = calculateFare(flight, passenger, tickets);
        double discount = totalBaseFare - totalFare;

        System.out.println("\n========== FARE DETAILS ==========");
        System.out.println("Base Fare: Rs." +
                           String.format("%.2f", totalBaseFare));
        System.out.println("Discount: Rs." +
                           String.format("%.2f", discount));
        System.out.println("Final Fare: Rs." +
                           String.format("%.2f", totalFare));

        System.out.print("\nConfirm booking? (yes/no): ");
        String confirmation = sc.nextLine().trim();

        if (!confirmation.equalsIgnoreCase("yes")) {
            System.out.println("Booking cancelled.");
            return;
        }

        if (!flight.hasAvailableSeats(tickets)) {
            System.out.println("Booking failed: Not enough seats available.");
            return;
        }

        String bookingId = "BK" + bookingCounter++;

        Booking booking = new Booking(
            bookingId, flight, passenger, tickets,
            flight.getBaseFare(), discount, totalFare
        );

        flight.bookSeats(tickets);
        latestBooking = booking;

        System.out.println("\nBooking successful!");
        latestBooking.displaySummary();
    }

    public void viewBooking() {
        if (latestBooking == null) {
            System.out.println("No confirmed booking found.");
        } else {
            latestBooking.displaySummary();
        }
    }

    public void start() {
        System.out.println("Welcome to Kiran Academy Flight Booking System");

        while (true) {
            System.out.println("\n========== MAIN MENU ==========");
            System.out.println("1. View Flights");
            System.out.println("2. Book Flight");
            System.out.println("3. View Booking");
            System.out.println("4. Exit");
            System.out.print("Enter choice: ");

            String input = sc.nextLine().trim();

            int choice;

            try {
                choice = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Invalid choice. Please enter 1 to 4.");
                continue;
            }

            switch (choice) {
                case 1:
                    displayFlights();
                    break;

                case 2:
                    bookFlight();
                    break;

                case 3:
                    viewBooking();
                    break;

                case 4:
                    System.out.println(
                        "Thank you for using Kiran Academy Flight Booking System. Goodbye!"
                    );
                    sc.close();
                    return;

                default:
                    System.out.println("Invalid choice. Please enter 1 to 4.");
            }
        }
    }
}

public class Main {
    public static void main(String[] args) {
        FlightBookingSystem system = new FlightBookingSystem();
        system.start();
    }
}

