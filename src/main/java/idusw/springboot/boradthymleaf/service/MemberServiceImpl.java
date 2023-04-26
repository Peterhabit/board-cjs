package idusw.springboot.boradthymleaf.service;

import idusw.springboot.boradthymleaf.domain.Member;
import idusw.springboot.boradthymleaf.domain.Memo;
import idusw.springboot.boradthymleaf.entity.MemberEntity;
import idusw.springboot.boradthymleaf.entity.MemoEntity;
import idusw.springboot.boradthymleaf.repository.MemberRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberServiceImpl implements MemberService {
    //DI - Ioc : 제어의 역전
    MemberRepository memberRepository;
    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
    }

    @Override
    public int create(Member m) {
        MemberEntity entity = MemberEntity.builder()
                .seq(m.getSeq())
                .email(m.getEmail())
                .name(m.getName())
                .pw(m.getPw())
                .build();
        if(memberRepository.save(entity)!= null) // 저장성공
            return 1;
        else
            return 0;
    }

    @Override
    public Member read(Member m) {
        MemberEntity e = memberRepository.getById(m.getSeq());
        Member result = new Member(); // DTO (Data Transfer Object) : Controller - Service or Controller - View
       System.out.println(e);
        result.setSeq(e.getSeq());
        result.setEmail(e.getEmail());
        result.setName(e.getName());
        return result;
    }

    @Override
    public List<Member> readList() {
        return null;
    }

    @Override
    public int update(Member m) {
        return 0;
    }

    @Override
    public int delete(Member m) {
        return 0;
    }

    @Override
    public Member login(Member m) {
        MemberEntity e = memberRepository.getByEmailPw(m.getEmail(), m.getPw());
        Member result = null; // DTO (Data Transfer Object) : Controller - Service or Controller - View
        if(e != null) {
            result.setSeq(e.getSeq());
            result.setEmail(e.getEmail());
            result.setName(e.getName());
        }
        return result;
    }
}
