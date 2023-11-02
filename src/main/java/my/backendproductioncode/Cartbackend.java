package my.backendproductioncode;

public class Cartbackend {
    private CartArray carts;

    public Cartbackend() {
        this.carts = new CartArray();
    }

    public void add(Cart cart) {
        carts.add(cart);
    }

    public void remove(int index) {
        resetID(index - 1, carts.remove(index - 1).getId());
    }

    public void removeRange(int from, int to) {
        int id = carts.get(from - 1).getId();
        carts.removeRange(from - 1, to);
        resetID(from - 1, id);
    }

    public void changeQuantity(int index, int quantity) {
        carts.get(index - 1).setQuantity(quantity);
    }

    public int getNextId() {
        return carts.size() + 1;
    }

    public boolean isEmpty() {
        return carts.isEmpty();
    }

    public double display() {
        double totalCost = 0;
        for (Cart cart : carts) {
            System.out.print(cart);
            totalCost += cart.getCost();
        }
        return totalCost;
    }

    public void displayBill() {
        System.out.println();
        System.out.print(String.format("\n\u2588 %-81s \u2588   %02d\t     \u2588   %09.02f\t\t \u2588\n", " ",
                carts.size(),  display()));
        System.out.print(String.format("\u2588 %-81s \u2588 %s       \u2588   %09.02f\t\t \u2588\n", " ", "GST 18%:",
                ( display() * 18) / 100));
        System.out.print(String.format("\u2588 %-81s \u2588 %s   \u2588   %09.02f\t\t \u2588\n", " ", "Grand Total:",
                ( display() * 18) / 100 +  display()));
    }

    private void resetID(int index, int id) {
        while (index < carts.size()) {
            carts.get(index++).setId(id++);
        }
    }
}
