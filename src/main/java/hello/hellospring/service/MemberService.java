package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

// @Service // spring이 서비스임을 확인하고 spring 컨테이너에 멤버 서비스를 등록해준다.
public class MemberService {
    private final MemberRepository memberRepository;

    // 같은 인스턴스를 쓰게 설정
    // @Autowired
    public MemberService(MemberRepository memberRepository) { // 외부에서 넣어줌
        this.memberRepository = memberRepository;
    }

    // 회원가입
    public Long join(Member member) {
        validateDuplicatedMember(member); // 중복 회원 검증
        memberRepository.save(member); // 통과하면 저장
        return member.getId();
    }

    // 같은 이름이 있는 중복 회원 X
    private void validateDuplicatedMember(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(m -> { // null이 아니라 값이 존재한다면 동작
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    // 전체 회원 조회
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);
    }
}
