package impact.capstone.user.service;

import impact.capstone.Config.JwtConfig;
import impact.capstone.user.model.dto.UserDTO;
import impact.capstone.user.model.entity.UserEntity;
import impact.capstone.user.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    public UserService(UserRepository userRepository, ModelMapper modelMapper, JwtConfig jwtConfig, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.jwtConfig = jwtConfig;
        this.passwordEncoder = passwordEncoder;
    }

    UserRepository userRepository;
    ModelMapper modelMapper;
    JwtConfig jwtConfig;
    PasswordEncoder passwordEncoder;

    public ResponseEntity<UserDTO> signUp(UserDTO userDTO) {
        // 1. 아이디 중복 체크
        if (userRepository.existsByUserId(userDTO.getId()))
            return ResponseEntity.badRequest().build();

        // 2. 회원 생성
        UserEntity user = new UserEntity();
        user.setUsername(userDTO.getUsername());
        user.setPassword(userDTO.getPassword());
        userRepository.save(user);

        return ResponseEntity.ok(modelMapper.map(user, UserDTO.class));
    }
}
