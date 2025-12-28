package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    /**
     * 회원 서비스 객체를 생성하고 반환
     * MemberRepository 의존성을 주입하여 생성자 기반 DI 구현
     */
    @Bean
    public MemberService memberService() {
        System.out.println("AppConfig.memberService call");
        return new MemberServiceImpl(memberRepository());
    }

    /**
     * 회원 저장소 객체를 생성하고 반환
     * 현재는 메모리 기반 구현체를 사용 (향후 DB 구현체로 변경 가능)
     */
    @Bean
    public MemberRepository memberRepository() {
        System.out.println("AppConfig.memberRepository call");
        return new MemoryMemberRepository();
    }

    /**
     * 주문 서비스 객체를 생성하고 반환
     * DiscountPolicy와 MemberRepository 의존성을 주입
     */
    @Bean
    public OrderService orderService() {
        System.out.println("AppConfig.orderService call");
        return new OrderServiceImpl(
                memberRepository(), // 회원 저장소 주입
                discountPolicy() // 할인 정책 주입
        );
    }

    /**
     * 할인 정책 객체를 생성하고 반환
     * 현재는 고정 할인 정책을 사용 (향후 다른 할인 정책으로 변경 가능)
     */
    @Bean
    public DiscountPolicy discountPolicy() {
//        return new FixDiscountPolicy();
        return new RateDiscountPolicy();
    }
}