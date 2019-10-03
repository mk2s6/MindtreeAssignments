import java.util.*;
import java.util.concurrent.TimeUnit;
import java.text.SimpleDateFormat;
import java.time.ZoneId;

class Admin {
    private static int id = 0;
    private static final Scanner scan = new Scanner(System.in);
    private static ArrayList<ArrayList<String>> customers = new ArrayList<ArrayList<String>>();

    public static void main(String[] args) {
        int option;
        System.out.println("\n\n-------- Welcome to administrator portal--------\n");
        while (true) {
            System.out.println("\n\n--------Please select one option from the list below-------- ");
            System.out.println("1. Add Customer details");
            System.out.println("2. Book a ticket");
            System.out.println("3. Display customers");
            System.out.println("4. Exit");
            System.out.println("--------------------------------------------------------------");
            System.out.print("\nChoose an option to continue : ");
            option = scan.nextInt();

            switch (option) {
            case 1:
                System.out.println("\n------------Add customer details----------------");
                addCustomer();
                break;
            case 2:
                System.out.println("\n------------Book a ticket------------\n\n");
                if (bookTicket())
                    System.out.println("\n------------ticket confirmed------------\n");
                break;
            case 3:
                System.out.println("\n------------Display customers------------");
                displayCustomers();
                break;
            case 4:
                System.out.println("\n------------Exit------------\n\n");
                return;
            default:
                System.out.println("\n-------Please select from the given options only-------------\n");
                break;
            }
        }
    }

    // Function to add customers to the list
    public static void addCustomer() {
        String name = "", age = "", mobile = "", dob = "", address = "";
        ArrayList<String> customer = new ArrayList<String>();
        System.out.print("Enter Name of the customer : ");
        name = scan.next();
        System.out.print("Enter age of the customer : ");
        age = scan.next();
        System.out.print("Enter Mobile number of the customer : ");
        mobile = scan.next();
        System.out.print("Enter DOB of the customer(Please enter in format of DD-MM-YYYY): ");
        dob = scan.next();
        System.out.print("Enter Address(city) of the customer : ");
        scan.nextLine();
        address = scan.nextLine();
        customer.add(Integer.toString(++id));
        customer.add(name);
        customer.add(age);
        customer.add(mobile);
        customer.add(dob);
        customer.add(address);
        customers.add(customer);
    }

    // Function to book a ticket
    public static boolean bookTicket() {
        try {
            String id = "", mobile = "", source = "", destination = "", date = "", time = "";
            int idInt;
            Date td;
            System.out.print("Enter id of the customer : ");
            id = scan.next();
            idInt = Integer.parseInt(id) - 1;
            System.out.print("Enter mobile number of the customer : ");
            mobile = scan.next();
            System.out.print("Source for the journey : ");
            source = scan.next();
            System.out.print("Destination for the journey : ");
            destination = scan.next();
            System.out.print("Enter date of travel(Enter the date in DD-MM-YYYY format) : ");
            date = scan.next();
            System.out.print("Enter Time of travel(Enter the time in HH:MM format) : ");
            time = scan.next();
            td = new SimpleDateFormat("dd-MM-yyyy").parse(date);
            if (idInt < 0 || idInt >= customers.size()) {
                System.out.println("Please give a valid customer id");
                return false;
            }
            ArrayList<String> customer = customers.get(idInt);
            if (!mobile.equals(customer.get(3).toString())) {
                System.out.println("\nPlease provide a valid mobile associated with customer id\n");
                return false;
            }
            if (source.equals(destination)) {
                System.out.println("\nSource and destination can not be to same city\n");
                return false;
            }

            Date today = new Date();

            if (!today.before(td)) {
                System.out.println("Travel date can not be a past date ");
                return false;
            }
            long duration = td.getTime() - today.getTime();
            long diffInDays = TimeUnit.MILLISECONDS.toDays(duration);
            if (diffInDays > 30) {
                System.out.println("Travel date can not exceed 30 days from today ");
                return false;
            }
            return true;
        } catch (Exception e) {
            System.out.println("\n\nPlease enter a valid date in the specified format\n\n");
            return false;
        }
    }

    // function to display customers
    public static void displayCustomers() {
        String sortOrder;
        System.out.println("\n\n--------Please select one option to display customers-------- ");
        System.out.println("1. Customers by ID");
        System.out.println("2. Customers by Name");
        System.out.println("3. Customers by place");
        System.out.println("--------------------------------------------------------------");
        System.out.print("\nChoose an option to continue : ");
        sortOrder = scan.next();
        switch (sortOrder) {
        case "1":
            sortCustomersBy(0);
            break;
        case "2":
            sortCustomersBy(1);
            break;
        case "3":
            sortCustomersBy(5);
            break;
        default:
            System.out.println("\n\n-----------------------Sorting customers by ID(default)----------------\n");
            sortCustomersBy(0);
            break;
        }
    }

    // Sort customers by a specific id
    public static void sortCustomersBy(int sortBy) {
        Collections.sort(customers, new Comparator<ArrayList<String>>() {
            @Override
            public int compare(ArrayList<String> o1, ArrayList<String> o2) {
                return o1.get(sortBy).compareTo(o2.get(sortBy));
            }
        });
        System.out.printf("\n\n%-20s%-20s%-20s%-20s%-20s%-20s\n", "Id", "Name", "Age", "Mobile", "DOB", "Address");
        for (ArrayList<String> customer : customers) {
            for (int i = 0; i < customer.size(); i++)
                System.out.printf("%-20s", customer.get(i));
            System.out.print("\n");
        }
    }
}