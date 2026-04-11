package dao;

import model.User;
import java.util.List;

public interface UserDAO {
    void add(User user) throws Exception;
    List<User> getAll() throws Exception;
	void delete(int id) throws Exception;
	void updateRole(int id,String role) throws Exception;
}