package kr.seula.springjwt.domain.user.service;

import jakarta.transaction.Transactional;
import kr.seula.springjwt.domain.user.dto.RegisterDTO;
import kr.seula.springjwt.domain.user.entity.UserEntity;
import kr.seula.springjwt.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public void register(RegisterDTO registerDTO) {
        userRepository.save(
                new UserEntity(
                        registerDTO.getEmail(),
                        registerDTO.getPassword()
                )
        );
    }

}
