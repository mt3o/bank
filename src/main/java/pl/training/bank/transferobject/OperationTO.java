package pl.training.bank.transferobject;

public class OperationTO {

    public enum Type {

        DEPOSIT, WITHDRAW

    }

    private Type type;
    private long funds;

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public long getFunds() {
        return funds;
    }

    public void setFunds(long funds) {
        this.funds = funds;
    }

}
