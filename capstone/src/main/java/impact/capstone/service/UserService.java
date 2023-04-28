package impact.capstone.service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public void signUp(UserDto userDto) {
        User user = new User();
        user.setUsername(userDto.getUsername());
        user.setPassword(userDto.getPassword());
        userRepository.save(user);
    }
}
