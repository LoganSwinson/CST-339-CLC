package my.gcu.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import my.gcu.data.entity.UserEntity;
import my.gcu.data.repository.UserRepository;
import my.gcu.interfaces.ServiceInterface;
import my.gcu.models.UserModel;

@Service
public class RegisterService implements ServiceInterface
{
    @Autowired
    private UserRepository userRepo;

    @Autowired
    private BCryptPasswordEncoder passEncoder;

    @Override
    public void init()
    {
        return;
    }

    @Override
    public void destroy()
    {
        return;
    }
    
    public void addUser(UserModel user)
    {
        user.setPassword(passEncoder.encode(user.getPassword()));
        userRepo.save(new UserEntity(user));
    }
}
