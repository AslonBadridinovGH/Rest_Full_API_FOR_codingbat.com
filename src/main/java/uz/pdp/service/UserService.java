package uz.pdp.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.entity.User;
import uz.pdp.payload.ApiResponse;
import uz.pdp.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public ApiResponse addUser(User user) {
        boolean exists = userRepository.existsByEmail(user.getEmail());
        if (exists)
            return new ApiResponse("Such Email is already exist",false);
        User user1=new User();
        user1.setEmail(user.getEmail());
        user1.setPassword(user.getPassword());
        userRepository.save(user1);
        return new ApiResponse("User was added",true);
    }

    public List<User> getUsers() {
        List<User> userList = userRepository.findAll();
        return userList;
    }

    public User getUserById(Integer id) {

        Optional<User> optionalUser = userRepository.findById(id);
         return optionalUser.orElseGet(User::new);
        // return optionalUser.orElse(null);
    }

    public ApiResponse editUser(User user, Integer id) {

        boolean exists = userRepository.existsByEmailAndIdNot(user.getEmail(), id);
        if (exists) {
            return new ApiResponse("Such Email is already exist",false);
        }

        Optional<User> optionalUser = userRepository.findById(id);
        if (!optionalUser.isPresent())
            return new ApiResponse("User was not found",false);
        User user1 = optionalUser.get();
        user1.setPassword(user.getEmail());
        user1.setPassword(user.getPassword());
        userRepository.save(user1);
        return new ApiResponse("User was edited",true);
    }

    public ApiResponse deleteUser(Integer id) {
        try {
            userRepository.deleteById(id);
            return new ApiResponse("User was deleted",true);
        }catch (Exception e){
            return new ApiResponse("User was not deleted",false);

    }
    }
}
