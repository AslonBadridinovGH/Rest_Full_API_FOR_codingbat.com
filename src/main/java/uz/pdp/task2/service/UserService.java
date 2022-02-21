package uz.pdp.task2.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.task2.entity.User;
import uz.pdp.task2.payload.ApiResponse;
import uz.pdp.task2.repository.UserRepository;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public ApiResponse addUser(User user) {
        boolean exists = userRepository.existsByEmail(user.getEmail());
        if (exists)
            return new ApiResponse("Such Email already exist",false);
        User user1=new User();
        user1.setEmail(user.getEmail());
        user1.setPassword(user.getPassword());
        userRepository.save(user1);
        return new ApiResponse("User added",true);
    }

    public List<User> getUsers() {
        List<User> userList = userRepository.findAll();
        return userList;
    }

    public User getUserById(Integer id) {

        Optional<User> optionalUser = userRepository.findById(id);
        // return optionalUser.orElseGet(User::new);
        return optionalUser.orElse(null);
    }

    public ApiResponse editUser(User user, Integer id) {

        boolean exists = userRepository.existsByEmailAndIdNot(user.getEmail(), id);
        if (exists)
        return new ApiResponse("Such Email already exist",false);

        Optional<User> optionalUser = userRepository.findById(id);
        if (!optionalUser.isPresent())
            return new ApiResponse("User not found",false);
        User user1 = optionalUser.get();
        user1.setPassword(user.getEmail());
        user1.setPassword(user.getPassword());
        userRepository.save(user1);
        return new ApiResponse("User edited",true);
    }

    public ApiResponse deleteUser(Integer id) {
        try {
            userRepository.deleteById(id);
            return new ApiResponse("User deleted",true);
        }catch (Exception e){
            return new ApiResponse("User not deleted",false);

    }
    }
}
