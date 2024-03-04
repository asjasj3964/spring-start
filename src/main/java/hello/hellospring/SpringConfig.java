package hello.hellospring;

import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import hello.hellospring.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration // Configuration을 확인하고
public class SpringConfig {
    @Bean // spring bean에 등록해야 함을 인식한다.
    public MemberService memberService() { // 이 로직을 호출해 spring bean에 등록해준다.
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() { // 이 로직을 호출해 spring bean에 등록해준다.
        return new MemoryMemberRepository();
    }
}
