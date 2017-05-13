package ipc.am.lab_20.db.repository.functions;

import ipc.am.lab_20.db.entity.User;

/**
 * Created by Gevorg on 5/8/2017.
 */

public interface User_I {
    void createUser(User u);
    boolean validateUser(String login,String pass);
    String findUser(String login,String pass);
    int findUserByID(String login,String pass);
}
