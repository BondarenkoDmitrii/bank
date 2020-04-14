package bondarenko;

import java.util.ArrayDeque;
import java.util.Queue;

public class Clerk extends Thread {

    private Cashbox cashbox;
    private Queue<Customer> queue = new ArrayDeque<Customer>();

    public Clerk(Cashbox cashbox) {
        this.queue.add(new Customer());
        this.cashbox = cashbox;
    }

    synchronized public void run() {
        while (true) {
            if (queue.size() > 0) {
                try {
                    System.out.println("Клиент: " + queue.peek().getIdCustomer());
                    System.out.println("Операция: " + queue.peek().getOperationName());
                    System.out.println("Сумма: " + queue.peek().getCash());
                    if (queue.peek().getOperation() == 1) {
                        cashbox.add(queue.peek().getCash());
                        System.out.println("Денег в кассе: " + cashbox.getCashbox());
                        sleep(queue.poll().getTime());
                    } else {
                        if (queue.peek().getCash() <= cashbox.getCashbox()) {
                            cashbox.subtract(queue.peek().getCash());
                            System.out.println("Денег в кассе: " + cashbox.getCashbox());
                            sleep(queue.poll().getTime());
                        } else {
                            System.out.println("В кассе не достаточно денег. Клиент " + queue.poll().getIdCustomer() + "не может быть обслужен.");
                        }
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }
    }

    synchronized public void addClient(Customer customer) {
        queue.add(customer);
        notify();
    }

    public Queue<Customer> getQueue() {
        return queue;
    }

    public void setQueue(Queue<Customer> queue) {
        this.queue = queue;
    }
}
