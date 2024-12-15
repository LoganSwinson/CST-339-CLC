package my.gcu.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import my.gcu.data.entity.UserEntity;
import my.gcu.data.repository.UserRepository;
import my.gcu.interfaces.ServiceInterface;
import my.gcu.models.UserModel;

/**
 * Service that handles user registration operations.
 * It processes user data and saves it to the database, 
 * ensuring passwords are securely encoded before saving.
 */
@Service
public class RegisterService implements ServiceInterface
{
    @Autowired
    private UserRepository userRepo;

    @Autowired
    private BCryptPasswordEncoder passEncoder;

    /**
     * Initializes the RegisterService.
     * Currently does nothing but can be used for future setup tasks.
     */
    @Override
    public void init()
    {
        return;
    }

    /**
     * Destroys the RegisterService.
     * Currently does nothing but can be used for cleanup tasks.
     */
    @Override
    public void destroy()
    {
        return;
    }
    
    /**
     * Adds a new user to the database.
     * The user's password is encoded before being saved.
     * 
     * @param user the user to be added
     */
    public void addUser(UserModel user)
    {
        user.setPassword(passEncoder.encode(user.getPassword()));
        userRepo.save(new UserEntity(user));
    }
}
