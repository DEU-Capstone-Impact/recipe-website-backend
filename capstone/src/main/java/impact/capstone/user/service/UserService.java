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

import java.util.Map;
import java.util.Optional;

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

    public ResponseEntity<String> signIn(Long userId, String userPassword) {
        // 1. 아이디 존재  여부 확인
        Optional<UserEntity> user = userRepository.findById(userId);
        if (!user.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        // 2. 로그인
        if (passwordEncoder.matches(userPassword, user.get().getPassword())) {
            return ResponseEntity.ok(jwtConfig.createToken(user.get()));
        }
        return ResponseEntity.badRequest().build();
    }

    public ResponseEntity<UserDTO> updateAccount(UserDTO userDTO, String token) {
        Map<String, Object> user = jwtConfig.verifyJWT(token);
        Long tmp = Long.parseLong(String.valueOf(user.get("userId")));
        UserEntity originUser = userRepository.findById(tmp).get();
        UserEntity modifiedUser = modelMapper.map(userDTO, UserEntity.class);

        //userName 입력, 이전과 다르면 갱신
        if (modifiedUser.getUsername() != null && modifiedUser.getUsername() != originUser.getUsername()) {
            originUser.setUsername(modifiedUser.getUsername());
        }

        originUser = userRepository.save(originUser);
        return ResponseEntity.ok(modelMapper.map(modifiedUser, UserDTO.class));
    }
}
