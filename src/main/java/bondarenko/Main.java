package bondarenko;

public class Main {
    public static void main(String[] args) {
        Cashbox cashbox = new Cashbox(1000, 2);
        Thread thread = new Thread(cashbox);
        thread.start();
    }
}
