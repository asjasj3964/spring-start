package hello.hellospring.controller;

import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller // spring이 Controller 노테이션을 보고 객체를 생성해서 들고 있음(?)
public class MemberController { // spring 컨테이너에서 spring bin이 관리된다.
    // private final MemberService memberService = new MemberService();
    // spring이 관리할 경우 다 spring 컨테이너에 등록하고 spring 컨테이너에서 받아서 쓰도록 바꾸어야 한다.

    // 하나만 생성한 후 공용으로 쓴다.
    // spring 컨테이너에 등록한 후 사용한다.
    private final MemberService memberService;
    @Autowired // Controller와 Service 연결
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }
}
