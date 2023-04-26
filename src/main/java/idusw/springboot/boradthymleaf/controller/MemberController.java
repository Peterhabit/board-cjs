package idusw.springboot.boradthymleaf.controller;

import idusw.springboot.boradthymleaf.domain.Member;
import idusw.springboot.boradthymleaf.domain.Memo;
import idusw.springboot.boradthymleaf.service.MemberService;
import idusw.springboot.boradthymleaf.service.MemoService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/members")
public class MemberController {
    //DI - Dependency Injection
    MemberService memberService;
    public MemberController(MemberService memberService) {
        this.memberService = memberService;

    }

    @GetMapping("/login")
    public String getLoginform(Model model){
        model.addAttribute("member", Member.builder().build());

        return "/members/login";
    }

    HttpSession session = null;

    @PostMapping("/login")
    public String loginMember(@ModelAttribute("member") Member member, Model model, HttpServletRequest request) { // 로그인 처리 -> service -> repository -> service -> controller
        Member result = null;
        if((result = memberService.login(member)) != null) {// 정상적으로 레코드의 변화가 발생하는 경우 영향받는 레코드 수를 반환
            session = request.getSession();
            session.setAttribute("mb", result);
            return "redirect:/";
        }
        else
            return "/main/error";
    }
    @PostMapping("/logout")
    public String logoutMember() {
        session.invalidate();
        return "redirect:/";
    }

    @GetMapping("/register")
    public String getRegisterForm(Model model){
        model.addAttribute("member", Member.builder().build());
        return "/members/register";
    }

    @PostMapping("/register")
    public String createMember(@ModelAttribute("member") Member member, Model model){
        System.out.println("this");
        if(memberService.create(member) >0) // 정상적으로 레코드가 추가되면 추가된 레코드 수 반환
            return "redirect:/";
        else
            return "main/error";
    }

    @GetMapping("/{seq}")
    public String getMember(@PathVariable("seq") Long seq, Model model) {
        Member result = new Member();
        Member m = new Member();
        m.setSeq(seq);
        result = memberService.read(m); // 여기를 수정함
        model.addAttribute("member", result);
        return "/members/detail";
    }

    @GetMapping("/update")
    public String getUpdateform(){
        return "/members/update";
    }

    @PostMapping("/update")
    public String updateMember(){
        return "redirect:/";
    }

    @DeleteMapping("/delete")
    public String deleteMember(){
        return "redirect:/";
    }


    @GetMapping("/forgot")
    public String getForgotform(){
        return "/members/forgot-password";
    }

    @PostMapping("/forgot")
    public String forgotMemberPassword(){
            return "redirect:/";
        }



}
