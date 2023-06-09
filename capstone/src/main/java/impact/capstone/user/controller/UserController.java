package impact.capstone.user.controller;

import impact.capstone.user.model.dto.UserDTO;
import impact.capstone.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
    UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/signup")
    @Operation(summary = "회원가입", description = "유저 정보를 입력받아 회원가입 하는 기능")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "회원가입 성공"),
            @ApiResponse(responseCode = "400", description = "ID가 중복입니다.")
    })
    public ResponseEntity<UserDTO> signup(@RequestBody UserDTO userDTO) {
        return userService.signUp(userDTO);
    }

    @PostMapping("/login")
    @Operation(summary = "로그인", description = "유저 정보를 입력받아 로그인하는 기능")
    @ResponseBody
    public ResponseEntity<String> signIn(@RequestBody UserDTO userDTO) {
        return userService.signIn(userDTO.getId(), userDTO.getPassword());
    }

    @PostMapping("/updateuserinfo")
    @Operation(summary = "회원 정보 수정", description = "회원 정보를 수정한다")
    public ResponseEntity<UserDTO> updateAccount(
            @Parameter(name = "JWT", description = "jwt")
            @RequestHeader(value = "jwt") String token,
            @RequestBody UserDTO userDTO) {
        return userService.updateAccount(userDTO, token);
    }

    @PostMapping("/deleteuser")
    @Operation(summary = "회원 탈퇴", description = "회원 정보를 삭제한다")
    public ResponseEntity<String> deleteAccount(
            @Parameter(name = "JWT", description = "유저 토큰")
            @RequestHeader(value = "jwt") String token) {
        return userService.deleteAccount(token);
    }
}
