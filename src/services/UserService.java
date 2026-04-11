package services;

import dao.*;
import model.User;
import java.util.*;

public class UserService {

    private UserDAO dao = new UserDAOImpl();

    public void add(String name, String role) throws Exception {
        dao.add(new User(0, name, role));
    }
    public void delete(int id) throws Exception {
        dao.delete(id);
    }
    public void updateRole(int id,String role) throws Exception {

        dao.updateRole(id,role);
    }

    public void view() throws Exception {

        List<User> list = dao.getAll();

        System.out.println("\n---- USERS ----");

        System.out.println("+----+------------+----------+");
        System.out.println("| ID | Username   |Role      |");
        System.out.println("+----+------------+----------+");

        for(User u:list){

            System.out.printf("| %-2d | %-10s | %-8s |\n",
                    u.getUserId(),
                    u.getUsername(),
                    u.getRole());
        }

        System.out.println("+----+------------+----------+");
    }
}