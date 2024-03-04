package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;

/*
정형화된 패턴 - Controller, Service, Repository
Controller를 통해 외부 요청을 받고
Service에서 비지니스 로직을 만들고
Repository에서 데이터를 저장한다.
*/
// @Repository
public class MemoryMemberRepository implements MemberRepository {
    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L; // 0, 1, 2, .. key 값 생성

    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id)); // null이어도 감쌀 수 있다.
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()
                .filter(member -> member.getName().equals(name))
                .findAny(); // 하나라도 찾음
        // member에서 가져온 name이 파라미터 name과 같은지 확인한다.
        // 하나라도 찾으면 필터링 되어 반환한다.
        // 없으면 Optional에 null이 포함되어 반환된다.
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values()); // store에 있는 member들을 반환한다.
    }

    public void clearStore() {
        store.clear(); // store를 비워줌
    }
}
