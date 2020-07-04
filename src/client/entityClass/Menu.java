package client.entityClass;

public class Menu {
    private int id_menu;
    private int weight;
    private double cost;
    private String dish;
    private String description;
    private String type;

    public Menu() {
    }

    public Menu(int id_menu, int weight, double cost, String dish, String description, String type) {
        this.id_menu = id_menu;
        this.weight = weight;
        this.cost = cost;
        this.dish = dish;
        this.description = description;
        this.type = type;
    }

    public int getId_menu() {
        return id_menu;
    }

    public void setId_menu(int id_menu) {
        this.id_menu = id_menu;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public String getDish() {
        return dish;
    }

    public void setDish(String dish) {
        this.dish = dish;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Menu{" +
                "id_menu=" + id_menu +
                ", weight=" + weight +
                ", cost=" + cost +
                ", dish='" + dish + '\'' +
                ", description='" + description + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
