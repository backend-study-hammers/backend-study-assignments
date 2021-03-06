package com.cherr.mov.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MemberServiceImpl implements MemberService{

    private final MemberRepository memberRepository;

    @Autowired//자동의존관게 주입, ac.getBean(MemberRepository.class)
    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public void join(MemberEntity member) {
        memberRepository.save(member);
    }

    @Override
    public MemberEntity findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }

    //테스트 용도도
   public MemberRepository getMemberRepository(){
        return memberRepository;
    }
}
