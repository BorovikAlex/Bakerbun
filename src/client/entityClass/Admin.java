package client.entityClass;

import java.util.Objects;

public class Admin {
    private int id;
    private String login;
    private String name;
    private String surname;

    public Admin() {
    }

    public Admin(int id, String login, String name, String surname) {
        this.id = id;
        this.login = login;
        this.name = name;
        this.surname = surname;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Admin admin = (Admin) o;
        return id == admin.id &&
                Objects.equals(login, admin.login) &&
                Objects.equals(name, admin.name) &&
                Objects.equals(surname, admin.surname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, login, name, surname);
    }

    @Override
    public String toString() {
        return "Admin{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                '}';
    }
}
