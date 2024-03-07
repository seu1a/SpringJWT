package kr.seula.springjwt.global.jwt;

import jakarta.persistence.EntityNotFoundException;
import kr.seula.springjwt.domain.user.entity.UserEntity;
import kr.seula.springjwt.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException("User Entity Not Found"));

        return new UserDetailsImpl(userEntity);
    }
}

