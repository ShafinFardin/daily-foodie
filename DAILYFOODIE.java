import java.util.*;

abstract class FoodMenu {
    private String name;

    public FoodMenu(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public abstract void showMenu();
    public abstract int getPrice();
}

class Monday extends FoodMenu {
    public Monday() { super("Monday"); }

    public void showMenu() {
        System.out.println("Monday: Chicken + Rice (110 TK)");
    }

    public int getPrice() { return 110; }
}

class Tuesday extends FoodMenu {
    public Tuesday() { super("Tuesday"); }

    public void showMenu() {
        System.out.println("Tuesday: Fish + Rice (115 TK)");
    }

    public int getPrice() { return 115; }
}

class Wednesday extends FoodMenu {
    public Wednesday() { super("Wednesday"); }

    public void showMenu() {
        System.out.println("Wednesday: Beef + Rice (135 TK)");
    }

    public int getPrice() { return 135; }
}

class Thursday extends FoodMenu {
    public Thursday() { super("Thursday"); }

    public void showMenu() {
        System.out.println("Thursday: Chicken + Veg (105 TK)");
    }

    public int getPrice() { return 105; }
}

class Friday extends FoodMenu {
    public Friday() { super("Friday"); }

    public void showMenu() {
        System.out.println("Friday: Fish Fry + Curry (125 TK)");
    }

    public int getPrice() { return 125; }
}

class Saturday extends FoodMenu {
    public Saturday() { super("Saturday"); }

    public void showMenu() {
        System.out.println("Saturday: Khichuri + Egg (125 TK)");
    }

    public int getPrice() { return 125; }
}

class Sunday extends FoodMenu {
    public Sunday() { super("Sunday"); }

    public void showMenu() {
        System.out.println("Sunday: Chicken Roast + Fish Curry (130 TK)");
    }

    public int getPrice() { return 130; }
}

class Special1 extends FoodMenu {
    public Special1() { super("Special Steak Combo"); }

    public void showMenu() {
        System.out.println("Grilled Steak + Pasta + Juice (350 TK)");
    }

    public int getPrice() { return 350; }
}

class Special2 extends FoodMenu {
    public Special2() { super("Special BBQ Combo"); }

    public void showMenu() {
        System.out.println("BBQ Chicken + Rice + Soup (300 TK)");
    }

    public int getPrice() { return 300; }
}

class Special3 extends FoodMenu {
    public Special3() { super("Special Burger Combo"); }

    public void showMenu() {
        System.out.println("Burger + Fries + Cold Drink (250 TK)");
    }

    public int getPrice() { return 250; }
}

class Order {
    private String name;
    private String payment;
    private List<String> items;
    private List<String> schedule;
    private int total;
    private boolean status;

    public void setOrder(String name, String payment, List<String> items, List<String> schedule, int total) {
        this.name = name;
        this.payment = payment;
        this.items = items;
        this.schedule = schedule;
        this.total = total;
    }

    public void confirm() {
        status = true;
        System.out.println("Order Confirmed!");
    }

    public void cancel() {
        status = false;
        System.out.println("Order Cancelled!");
    }

    public void show() {
        System.out.println("\nORDER DETAILS");
        System.out.println("Customer Name: " + name);
        System.out.println("Items: " + items);
        System.out.println("Schedule (Day + Special): " + schedule);
        System.out.println("Payment Method: " + payment);
        System.out.println("Total Cost: " + total + " TK");
        System.out.println("Status: " + (status ? "Confirmed" : "Not Confirmed"));
    }
}

public class DAILYFOODIE {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

       
        System.out.println("=================================");
        System.out.println("            DAILY FOODIE         ");
        System.out.println("=================================\n");

        FoodMenu[] menu = {
            new Monday(), new Tuesday(), new Wednesday(),
            new Thursday(), new Friday(), new Saturday(), new Sunday(),
            new Special1(), new Special2(), new Special3()
        };

        System.out.println("===== DAILY FOODIE MENU =====");

        System.out.println("\n--- GENERAL (1-7) ---");
        for (int i = 0; i < 7; i++) {
            System.out.print((i + 1) + ". ");
            menu[i].showMenu();
        }

        System.out.println("\n--- SPECIAL (8-10) ---");
        for (int i = 7; i < menu.length; i++) {
            System.out.print((i + 1) + ". ");
            menu[i].showMenu();
        }

        // 🔥 MULTI SELECT
        System.out.print("\nSelect items : ");
        String input = sc.nextLine();

        String[] choices = input.split(",");

        List<String> items = new ArrayList<>();
        List<String> schedule = new ArrayList<>();
        int total = 0;

        String[] days = {"Monday","Tuesday","Wednesday","Thursday","Friday","Saturday","Sunday"};

        for (String c : choices) {
            int idx = Integer.parseInt(c.trim()) - 1;

            if (idx >= 0 && idx < menu.length) {

                items.add(menu[idx].getName());
                total += menu[idx].getPrice();

                if (idx >= 7) {
                    System.out.print("Choose day for " + menu[idx].getName() + " (1-7): ");
                    int d = sc.nextInt();
                    sc.nextLine();

                    schedule.add(menu[idx].getName() + " → " + days[d - 1]);
                } else {
                    schedule.add(menu[idx].getName() + " → Auto day");
                }
            }
        }

        System.out.print("Enter name: ");
        String name = sc.nextLine();

        System.out.println("\nPayment Options:");
        System.out.println("1. Card");
        System.out.println("2. Bkash");
        System.out.println("3. Nagad");
        System.out.println("4. Bank Account");
        System.out.print("Choose payment: ");

        int p = sc.nextInt();
        String payment;

        if (p == 1) payment = "Card";
        else if (p == 2) payment = "Bkash";
        else if (p == 3) payment = "Nagad";
        else if (p == 4) payment = "Bank Account";
        else payment = "Unknown";

        Order order = new Order();
        order.setOrder(name, payment, items, schedule, total);

        System.out.println("\n1. Confirm Order");
        System.out.println("2. Cancel Order");
        int ch = sc.nextInt();

        if (ch == 1) order.confirm();
        else order.cancel();

        order.show();

        sc.close();
    }
}