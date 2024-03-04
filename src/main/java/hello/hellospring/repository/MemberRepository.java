package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Member save(Member memeber); // 회원을 저장소에 저장
    Optional<Member> findById(Long id); // 저장소에서 id로 회원 조회
    Optional<Member> findByName(String name); // 저장소에서 name으로 회원 조회
    List<Member> findAll(); // 저장된 모든 회원 리스트를 반환
}
