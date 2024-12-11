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

@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

     // Constructor to initialize UserRepository
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    // This method is used by Spring Security to load user details during login.
    // fetches the user from the database based on the provided username.
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Fetch user from the database
        UserEntity user = userRepository.findByUsername(username)
            .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    
        // Use the role from the database (assuming user.getRole() returns the correct role)
        return User.builder()
                .username(user.getUsername())
                .password(user.getPassword()) // Hashed password
                .roles(user.getRole()) // Fetch the role from the database
                .build();
    }
    
    public UserModel getUserById(Integer id)
    {
        var userModel = userRepository.findById(id).map(UserModel::new).orElse(null);
        
        // Don't retrieve the info of admins
        if (userModel == null || userModel.getRole().equals("ADMIN"))
            return null;

        return userModel;
    }

    public List<UserModel> getUserList()
    {
        var userList = new ArrayList<UserModel>();
        var userEntities = userRepository.findAll();

        // Only returns the info of non-admins
        for (UserEntity userEntity : userEntities)
        {
            if (!userEntity.getRole().equals( "ADMIN"))
                userList.add(new UserModel(userEntity));
        }

        // If there are only admins, don't return any data
        if (userList.size() == 0)
            return null;

        return userList;
    }
}
