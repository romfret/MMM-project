package locusta.project.services;


import java.util.List;

import javax.ejb.Remote;

import locusta.project.entities.User;

@Remote
public interface ServicesUser {

    public User registration(User user);
    public void unRegistration(User user);
    public String encryptPassword(String password);
    
    public User getById(int id);
    public User getByUserName(String userName); 
    public List<User> searchUsers(String somethingLikeThat); 
    
    //TODO Put others methods here
	
}
