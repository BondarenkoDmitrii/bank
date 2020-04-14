package bondarenko;

import java.util.Random;
import java.util.UUID;

public class Customer {

    private String idCustomer;
    private Integer operation;
    private Integer cash;
    private Integer time;

    public Customer() {
        this.idCustomer = UUID.randomUUID().toString();
        Random random = new Random();
        int min = 1;
        int max = 1000;
        int diff = max - min;
        this.operation = random.nextInt(2);
        this.cash = random.nextInt(diff + 1) + min;
        min = 5000;
        max = 10000;
        diff = max - min;
        this.time = random.nextInt(diff + 1) + min;
    }

    public String getIdCustomer() {
        return idCustomer;
    }

    public String getOperationName() {
        if (operation == 1){
            return "вклад";
        } else {
            return "снятие";
        }
    }

    public Integer getOperation() {
        return operation;
    }

    public void setOperation(Integer operation) {
        this.operation = operation;
    }

    public Integer getCash() {
        return cash;
    }

    public void setCash(Integer cash) {
        this.cash = cash;
    }

    public Integer getTime() {
        return time;
    }

    public void setTime(Integer time) {
        this.time = time;
    }
}
