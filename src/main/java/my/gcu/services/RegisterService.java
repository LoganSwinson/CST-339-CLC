package my.gcu.services;

import org.springframework.beans.factory.annotation.Autowired;

import my.gcu.data.entity.UserEntity;
import my.gcu.data.entity.repository.UserRepository;
import my.gcu.interfaces.ServiceInterface;
import my.gcu.models.UserModel;

public class RegisterService implements ServiceInterface
{
    @Autowired
    private UserRepository userRepo;

    @Override
    public void init()
    {
        UserModel.init((int)userRepo.count());
        return;
    }

    @Override
    public void destroy()
    {
        return;
    }
    
    public void addUser(UserModel user)
    {
        userRepo.save(new UserEntity(user));
    }
}
