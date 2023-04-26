package idusw.springboot.boradthymleaf.controller;

import idusw.springboot.boradthymleaf.domain.Member;
import idusw.springboot.boradthymleaf.service.MemberService;
import idusw.springboot.boradthymleaf.service.MemoService;
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
    public String getLoginform(){
        return "/members/login";
    }

     @PostMapping("/login")
    public String PostLogin() {
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
