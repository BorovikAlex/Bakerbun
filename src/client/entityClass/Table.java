package client.entityClass;

public class Table {
    private int id;
    private int table;
    private String status;

    public Table() {
    }

    public Table(int id, int table, String status) {
        this.id = id;
        this.table = table;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTable() {
        return table;
    }

    public void setTable(int table) {
        this.table = table;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Table{" +
                "id=" + id +
                ", table=" + table +
                ", status='" + status + '\'' +
                '}';
    }
}
