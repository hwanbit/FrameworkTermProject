package kr.ac.kopo.sun.weblogin.service;

import kr.ac.kopo.sun.weblogin.dto.MemberSignupDto;
import kr.ac.kopo.sun.weblogin.entity.Member;
import kr.ac.kopo.sun.weblogin.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.User;

@Service
@RequiredArgsConstructor
public class MemberService implements UserDetailsService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Member member = memberRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("사용자를 찾을 수 없습니다: " + username));

        return User.builder()
                .username(member.getUsername())
                .password(member.getPassword())
                .roles(member.getRole())
                .build();
    }

    @Transactional
    public Long signup(MemberSignupDto dto) {
        if (memberRepository.findByUsername(dto.getUsername()).isPresent()) {
            throw new IllegalState
            Exception("이미 존재하는 아이디입니다. 다른 아이디를 입력해주세요.");
        }

        String encodedPassword = passwordEncoder.encode(dto.getPassword());

        Member member = Member.builder()
                .username(dto.getUsername())
                .password(encodedPassword)
                .role("USER") // 기본 가입은 USER 권한 부여
                .build();

        return memberRepository.save(member).getId();
    }
}