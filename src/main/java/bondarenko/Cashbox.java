package bondarenko;

public class Cashbox extends Thread {

    private Integer cashbox;
    private Thread[] pool;
    private Clerk[] clerks;
    private Integer amountClerks;

    public Cashbox(Integer cashbox, Integer amountClerks) {
        this.cashbox = cashbox;
        this.amountClerks = amountClerks;
        this.clerks = new Clerk[amountClerks];
        this.pool = new Thread[amountClerks];
    }

    synchronized public void run() {
        for (int i = 0; i < amountClerks; i++) {
            clerks[i] = new Clerk(this);
            pool[i] = new Thread(clerks[i]);
            pool[i].start();
        }

        while (true) {
            for (int i = 0; i < amountClerks; i++) {
                if (pool[i].getState() == Thread.State.WAITING) {
                    leastQueue().addClient(new Customer());
                }
            }
        }
    }

    private Clerk leastQueue(){
        Clerk queue;
        queue = clerks[0];
        for (int i = 0; i < amountClerks; i++) {
            if (clerks[i].getQueue().size() < queue.getQueue().size()) {
                queue = clerks[i];
            }
        }
        return queue;
    }

    public Integer add(Integer cash){
        cashbox += cash;
        return cashbox;
    }

    public Integer subtract(Integer cash){
        cashbox -= cash;
        return cashbox;
    }

    public Integer getCashbox() {
        return cashbox;
    }

    public void setCashbox(Integer cashbox) {
        this.cashbox = cashbox;
    }
}
