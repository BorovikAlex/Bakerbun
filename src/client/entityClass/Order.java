package client.entityClass;

public class Order {
    private int id_order;
    private String name;
    private String phone;
    private int seats;
    private int table;
    private String date;
    private String time;
    private String steward;
    private double account;
    private String dish;
    private int amount;
    private String status;


    public Order() {
    }

    public Order(int id_order, String name, String phone, int seats, int table, String date, String time, String steward, double account) {
        this.id_order = id_order;
        this.name = name;
        this.phone = phone;
        this.seats = seats;
        this.table = table;
        this.date = date;
        this.time = time;
        this.steward = steward;
        this.account = account;
    }

    public int getId_order() {
        return id_order;
    }

    public void setId_order(int id_order) {
        this.id_order = id_order;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getTable() {
        return table;
    }

    public void setTable(int table) {
        this.table = table;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getSteward() {
        return steward;
    }

    public void setSteward(String steward) {
        this.steward = steward;
    }

    public double getAccount() {
        return account;
    }

    public void setAccount(double account) {
        this.account = account;
    }

    public int getSeats() {
        return seats;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }

    public String getDish() {
        return dish;
    }

    public void setDish(String dish) {
        this.dish = dish;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id_order=" + id_order +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", seats=" + seats +
                ", table=" + table +
                ", date='" + date + '\'' +
                ", time='" + time + '\'' +
                ", steward='" + steward + '\'' +
                ", account=" + account +
                ", dish='" + dish + '\'' +
                ", amount=" + amount +
                ", status='" + status + '\'' +
                '}';
    }
}
