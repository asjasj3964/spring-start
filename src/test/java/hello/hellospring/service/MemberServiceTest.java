package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

class MemberServiceTest {

    MemberService memberService;
    MemoryMemberRepository memberRepository;

    @BeforeEach // 동작하기 전에 넣어줌
    public void beforeEach() {
        // test 실행할 때마다 각각 생성 (test 독립적으로 실행)
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }

    @AfterEach
    public void afterEach() {
        memberRepository.clearStore();
    }

    @Test // 정상 플로우
    void join() {
        // given 어떤 상황이 주어졌는데
        Member member = new Member();
        member.setName("hello");

        // when 이를 실행했을 때
        Long saveId = memberService.join(member);

        // then 결과가 이렇게 나와야 한다. (검증)
        // 저장한 것이 레포지토리에 있는지 확인
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test // 예외 플로우
    public void 중복_회원_예외(){
        // given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring"); // 중복된 이름

        // when
        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2)); // 예외를 터지게 함
        // 오른쪽의 로직을 실행할 것인데 IllegalStateException 예외가 터져야 한다.

        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
        /*
        try {
            memberService.join(member2); // 실행
            fail("예외가 발생해야 합니다."); // 그 다음 줄로 내려가면 실패
        } catch (IllegalStateException e) {
            // 예외가 터져서 정상적으로 성공 (?)
            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
        }
        */

        // then
    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}