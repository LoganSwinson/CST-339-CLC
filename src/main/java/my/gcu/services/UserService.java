package my.gcu.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import my.gcu.data.entity.UserEntity;
import my.gcu.data.repository.UserRepository;
import my.gcu.models.UserModel;

/**
 * Service that manages user-related operations such as loading user details 
 * for authentication and retrieving user data.
 * Implements Spring Security's UserDetailsService for user authentication.
 */
@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    /**
     * Constructor to initialize UserRepository.
     * 
     * @param userRepository the UserRepository to interact with the database
     */
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Loads the user details based on the provided username.
     * This method is used by Spring Security during user authentication.
     * 
     * @param username the username of the user to be fetched
     * @return UserDetails object containing user data for authentication
     * @throws UsernameNotFoundException if the user is not found
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {
        // Fetch user from the database
        UserEntity user = userRepository.findByUsername(username)
            .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    
        // Return a UserDetails object with the user's credentials and role
        return User.builder()
            .username(user.getUsername())
            .password(user.getPassword()) // Hashed password
            .roles(user.getRole()) // User's role
            .build();
    }
    
    /**
     * Fetches a user by their ID.
     * Returns the user data as a UserModel, excluding admin users.
     * 
     * @param id the ID of the user to fetch
     * @return UserModel containing user data, or null if the user is an admin or not found
     */
    public UserModel getUserById(Integer id)
    {
        var userModel = userRepository.findById(id).map(UserModel::new).orElse(null);
        
        // Don't retrieve info of admin users
        if (userModel == null || userModel.getRole().equals("ADMIN"))
            return null;

        return userModel;
    }

    /**
     * Retrieves a list of all non-admin users.
     * 
     * @return a list of UserModels for non-admin users, or null if no non-admin users exist
     */
    public List<UserModel> getUserList()
    {
        var userList = new ArrayList<UserModel>();
        var userEntities = userRepository.findAll();

        // Add non-admin users to the list
        for (UserEntity userEntity : userEntities)
        {
            if (!userEntity.getRole().equals("ADMIN"))
                userList.add(new UserModel(userEntity));
        }

        // If no non-admin users, return null
        if (userList.size() == 0)
            return null;

        return userList;
    }
}
