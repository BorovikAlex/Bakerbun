package server.Database;

import java.sql.*;
import java.util.ArrayList;


public class Database {
    private static Connection connection;

    public static void connect(String database, String user, String password, String port) {
        try {
            connection = DriverManager.getConnection(("jdbc:mysql://localhost:" + port + "/" +
                    database + "?serverTimezone=UTC"), user, password);
        } catch (SQLException sqlexc) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            sqlexc.printStackTrace();
        }
    }


    public static boolean addUser(String name, String surname, String login, String password) {
        String insertClient = "INSERT INTO restaurant.client (name, surname, login, password) VALUES (?, ?, ?, ?)";

        try {
            PreparedStatement preparedStatementClient = connection.prepareStatement(insertClient);

            preparedStatementClient.setString(1, name);
            preparedStatementClient.setString(2, surname);
            preparedStatementClient.setString(3, login);
            preparedStatementClient.setString(4, password);
            preparedStatementClient.executeUpdate();
        } catch (SQLException e) {
            return false;
        }
        return true;
    }

    public static boolean loginClient(String login, String password) {
        ResultSet resultSet;

        String select = "SELECT * FROM client WHERE login=? and password=?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(select);
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            System.out.println("login func exception");
            e.printStackTrace();
        }
        return true;
    }

    public static boolean loginAdmin(String login, String password) {
        ResultSet resultSet;

        String select = "SELECT * FROM admin WHERE login=? and password=?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(select);
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            System.out.println("login func exception");
            e.printStackTrace();
        }
        return true;
    }

    public static boolean loginSteward(String login, String password) {
        ResultSet resultSet;

        String select = "SELECT * FROM steward WHERE login=? and password=?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(select);
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            System.out.println("login func exception");
            e.printStackTrace();
        }
        return true;
    }

    public static ArrayList<String> getUsers() {
        ResultSet resultSet;
        ArrayList<String> arrayList = new ArrayList<>(0);

        String select = "SELECT * FROM restaurant.client";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(select);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                StringBuilder info = new StringBuilder();
                info.append(resultSet.getString("name")).append(" ").
                        append(resultSet.getString("surname")).append(" ").
                        append(resultSet.getString("login")).append(" ");
                arrayList.add(info.toString());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return arrayList;
    }

    public static ArrayList<String> getStewardSurname() {
        ResultSet resultSet;
        ArrayList<String> steward = new ArrayList<>(0);

        String select = "SELECT surname FROM restaurant.steward";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(select);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                steward.add(resultSet.getString("surname"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return steward;
    }

    public static ArrayList<String> getSteward() {
        ResultSet resultSet;
        ArrayList<String> arrayList = new ArrayList<>(0);

        String select = "SELECT * FROM steward";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(select);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                StringBuilder info = new StringBuilder();
                info.append(resultSet.getString("name")).append(" ").
                        append(resultSet.getString("surname")).append(" ").
                        append(resultSet.getString("login")).append(" ");
                arrayList.add(info.toString());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return arrayList;
    }

    public static boolean addSteward(String order) {
        String[] infoDetails = order.split(" ", 5);
        String insert = "INSERT INTO steward (name, surname, login, password)" +
                "VALUES (?, ?, ?, ?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(insert);
            preparedStatement.setString(1, infoDetails[1]);
            preparedStatement.setString(2, infoDetails[2]);
            preparedStatement.setString(3, infoDetails[3]);
            preparedStatement.setString(4, infoDetails[4]);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public static boolean delSteward(String surname) {

        String delete = "DELETE FROM steward WHERE surname=? ";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(delete);
            preparedStatement.setString(1, surname);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            return false;
        }
        return true;
    }

    public static boolean addOrder(String order) {
        String[] infoDetails = order.split(" ", 6);
        String insert = "INSERT INTO restaurant.order (name, phone, seats, date, time)\n" +
                "VALUES (?, ?, ?, ?, ?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(insert);
            preparedStatement.setString(1, infoDetails[1]);
            preparedStatement.setString(2, infoDetails[2]);
            preparedStatement.setString(3, infoDetails[3]);
            preparedStatement.setString(4, infoDetails[4]);
            preparedStatement.setString(5, infoDetails[5]);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public static ArrayList<String> getOrder() {
        ResultSet resultSet;
        ArrayList<String> storeList = new ArrayList<>(0);

        String select = "SELECT name, phone, seats, date, time FROM restaurant.order";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(select);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                StringBuilder info = new StringBuilder();
                info.append(resultSet.getString("name")).append(" ").
                        append(resultSet.getString("phone")).append(" ").
                        append(resultSet.getString("seats")).append(" ").
                        append(resultSet.getString("date")).append(" ").
                        append(resultSet.getString("time"));
                storeList.add(info.toString());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return storeList;
    }

    public static ArrayList<String> getStewardOrder() {
        ResultSet resultSet;
        ArrayList<String> storeList = new ArrayList<>(0);

        String select = "SELECT * FROM restaurant.order";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(select);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                StringBuilder info = new StringBuilder();
                info.append(resultSet.getString("id_order")).append(" ").
                        append(resultSet.getString("name")).append(" ").
                        append(resultSet.getString("seats")).append(" ").
                        append(resultSet.getString("date")).append(" ").
                        append(resultSet.getString("time")).append(" ").
                        append(resultSet.getString("table")).append(" ").
                        append(resultSet.getString("steward")).append(" ").
                        append(resultSet.getString("account")).append(" ");
                storeList.add(info.toString());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return storeList;
    }

    public static ArrayList<String> getAccount() {
        ResultSet resultSet;
        ArrayList<String> storeList = new ArrayList<>(0);

        String select = "SELECT * FROM restaurant.order";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(select);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                StringBuilder info = new StringBuilder();
                info.append(resultSet.getString("name")).append(" ").
                        append(resultSet.getString("table")).append(" ").
                        append(resultSet.getString("date")).append(" ").
                        append(resultSet.getString("time")).append(" ").
                        append(resultSet.getString("steward")).append(" ").
                        append(resultSet.getString("account"));
                storeList.add(info.toString());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return storeList;
    }

    public static ArrayList<String> getId() {
        ResultSet resultSet;
        ArrayList<String> id_order = new ArrayList<>(0);

        String select = "SELECT id_order FROM restaurant.order";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(select);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                id_order.add(resultSet.getString("id_order"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id_order;
    }

    public static boolean setSteward(String order) {
        String[] infoDetails = order.split(" ", 4);
        String insert = "UPDATE restaurant.order SET order.table=?, steward=? WHERE id_order=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(insert);
            preparedStatement.setString(1, infoDetails[2]);
            preparedStatement.setString(2, infoDetails[3]);
            preparedStatement.setString(3, infoDetails[1]);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public static boolean setAccount(String order) {
        String[] infoDetails = order.split(" ", 4);
        String insert = "UPDATE restaurant.order SET account=? WHERE id_order=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(insert);
            preparedStatement.setString(1, infoDetails[2]);
            preparedStatement.setString(2, infoDetails[1]);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public static ArrayList<String> getMenu() {
        ResultSet resultSet;
        ArrayList<String> storeList = new ArrayList<>(0);

        String select = "SELECT * FROM restaurant.menu";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(select);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                StringBuilder info = new StringBuilder();
                info.append(resultSet.getString("id_menu")).append(" ").
                        append(resultSet.getString("type")).append(" ").
                        append(resultSet.getString("dish")).append(" ").
                        append(resultSet.getString("cost")).append(" ").
                        append(resultSet.getString("weight"));
                storeList.add(info.toString());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return storeList;
    }

    public static ArrayList<String> getMenuClient() {
        ResultSet resultSet;
        ArrayList<String> storeList = new ArrayList<>(0);

        String select = "SELECT * FROM restaurant.menu";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(select);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                StringBuilder info = new StringBuilder();
                info.append(resultSet.getString("type")).append(" ").
                        append(resultSet.getString("dish")).append(" ").
                        append(resultSet.getString("cost")).append(" ").
                        append(resultSet.getString("weight"));
                storeList.add(info.toString());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return storeList;
    }

    public static ArrayList<String> getType() {
        ResultSet resultSet;
        ArrayList<String> steward = new ArrayList<>(0);

        String select = "SELECT type FROM restaurant.type";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(select);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                steward.add(resultSet.getString("type"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return steward;
    }

    public static boolean addMenu(String type, String dish, String weight, String cost) {
        String insert = "INSERT INTO menu (type, dish, weight, cost)" +
                "VALUES (?, ?, ?, ?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(insert);
            preparedStatement.setString(1, type);
            preparedStatement.setString(2, dish);
            preparedStatement.setString(3, weight);
            preparedStatement.setString(4, cost);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public static boolean delMenu(String menu) {

        String delete = "DELETE FROM menu WHERE dish=? ";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(delete);
            preparedStatement.setString(1, menu);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            return false;
        }
        return true;
    }

    public static ArrayList<String> getDish() {
        ResultSet resultSet;
        ArrayList<String> steward = new ArrayList<>(0);

        String select = "SELECT dish FROM restaurant.menu";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(select);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                steward.add(resultSet.getString("dish"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return steward;
    }

    public static ArrayList<String> getClientsOrder() {
        ResultSet resultSet;
        ArrayList<String> storeList = new ArrayList<>(0);

        String select = "SELECT * FROM restaurant.clientsorder";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(select);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                StringBuilder info = new StringBuilder();
                info.append(resultSet.getString("id_clientsOrder")).append(" ").
                        append(resultSet.getString("name")).append(" ").
                        append(resultSet.getString("table")).append(" ").
                        append(resultSet.getString("dish")).append(" ").
                        append(resultSet.getString("amount")).append(" ").
                        append(resultSet.getString("status"));

                storeList.add(info.toString());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return storeList;
    }

    public static boolean setDish(String order) {
        String[] infoDetails = order.split(" ", 4);
        String insert = "UPDATE restaurant.clientsorder SET status=? WHERE id_clientsOrder=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(insert);
            preparedStatement.setString(1, infoDetails[2]);
            preparedStatement.setString(2, infoDetails[1]);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public static boolean addClientDish(String order) {
        String[] infoDetails = order.split(" ", 5);

        String insert;
        insert = "INSERT INTO clientsorder (clientsorder.name, clientsorder.table, clientsorder.dish, clientsorder.amount)" +
                "VALUES (?, ?, ?, ?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(insert);
            preparedStatement.setString(1, infoDetails[1]);
            preparedStatement.setString(2, infoDetails[2]);
            preparedStatement.setString(3, infoDetails[3]);
            preparedStatement.setString(4, infoDetails[4]);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public static ArrayList<String> getCosts() {
        ResultSet resultSet;
        ArrayList<String> storeList = new ArrayList<>(0);

        String select = "SELECT menu.dish, menu.cost FROM restaurant.menu";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(select);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                StringBuilder info = new StringBuilder();
                info.append(resultSet.getString("dish")).append(" ").
                        append(resultSet.getString("cost"));

                storeList.add(info.toString());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return storeList;
    }

    public static ArrayList<String> getTables() {
        ResultSet resultSet;
        ArrayList<String> storeList = new ArrayList<>(0);

        String select = "SELECT * FROM restaurant.tables";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(select);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                StringBuilder info = new StringBuilder();
                info.append(resultSet.getString("table")).append(" ").
                        append(resultSet.getString("status"));
                storeList.add(info.toString());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return storeList;
    }

    public static ArrayList<String> getTable() {
        ResultSet resultSet;
        ArrayList<String> table = new ArrayList<>(0);

        String select = "SELECT * FROM restaurant.tables";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(select);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                table.add(resultSet.getString("table"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return table;
    }

    public static ArrayList<String> getStatus() {
        ResultSet resultSet;
        ArrayList<String> steward = new ArrayList<>(0);

        String select = "SELECT status FROM restaurant.status";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(select);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                steward.add(resultSet.getString("status"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return steward;
    }

    public static boolean setTable(String order) {
        String[] infoDetails = order.split(" ", 4);
        String insert = "UPDATE restaurant.tables SET tables.status=? WHERE tables.table=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(insert);
            preparedStatement.setString(1, infoDetails[2]);
            preparedStatement.setString(2, infoDetails[1]);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}