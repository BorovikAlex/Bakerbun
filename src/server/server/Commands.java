package server.server;

import server.Database.Database;

public class Commands {

    public static Object split(String command) {
        String[] commandNumber = command.split(" ", 2);
        String[] commands;
        Object result = true;
        switch (commandNumber[0]) {
            case "addClient":
                commands = command.split(" ", 5);
                result = Database.addUser(commands[1], commands[2], commands[3], commands[4]);
                break;
            case "loginClient":
                commands = command.split(" ", 3);
                result = Database.loginClient(commands[1], commands[2]);
                break;
            case "loginAdmin":
                commands = command.split(" ", 3);
                result = Database.loginAdmin(commands[1], commands[2]);
                break;
            case "loginSteward":
                commands = command.split(" ", 3);
                result = Database.loginSteward(commands[1], commands[2]);
                break;
            case "getUsers":
                result = Database.getUsers();
                break;
            case "getStewardSurname":
                result = Database.getStewardSurname();
                break;
            case "getSteward":
                result = Database.getSteward();
                break;
            case "delSteward":
                commands = command.split(" ", 2);
                result = Database.delSteward(commands[1]);
                break;
            case "addSteward":
                result = Database.addSteward(command);
                break;
            case "addOrder":
                result = Database.addOrder(command);
                break;
            case "getOrder":
                result = Database.getOrder();
                break;
            case "getId":
                result = Database.getId();
                break;
            case "getAccount":
                result = Database.getAccount();
                break;
            case "getStewardOrder":
                result = Database.getStewardOrder();
                break;
            case "setSteward":
                result = Database.setSteward(command);
                break;
            case "setAccount":
                result = Database.setAccount(command);
                break;
            case "getMenu":
                result = Database.getMenu();
                break;
            case "getMenuClient":
                result = Database.getMenuClient();
                break;
            case "getType":
                result = Database.getType();
                break;
            case "addMenu":
                commands = command.split(" ", 5);
                result = Database.addMenu(commands[1], commands[2], commands[3], commands[4]);
                break;
            case "getDish":
                result = Database.getDish();
                break;
            case "delMenu":
                commands = command.split(" ", 2);
                result = Database.delMenu(commands[1]);
                break;
            case "getClientsOrder":
                result = Database.getClientsOrder();
                break;
            case "setDish":
                result = Database.setDish(command);
                break;
            case "addClientDish":
                result = Database.addClientDish(command);
                break;
            case "getCosts":
                result = Database.getCosts();
                break;
            case "getTables":
                result = Database.getTables();
                break;
            case "getTable":
                result = Database.getTable();
                break;
            case "getStatus":
                result = Database.getStatus();
                break;
            case "setTable":
                result = Database.setTable(command);
                break;
        }
        return result;
    }

}
