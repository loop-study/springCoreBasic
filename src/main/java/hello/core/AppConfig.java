package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderSerivce;
import hello.core.order.OrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Configuration 로 붙인다
 */
@Configuration
public class AppConfig {

    /**
     * @Bean memberService -> new MemorymemberRepository()
     * @Bean orderService -> new MemorymemberRepository()
     * MemorymemberRepository 여러번 호출이되네 싱글톤이 꺠지는거 아닌가?
     */

    //예상 결과
    //call AppConfig.memberService
    //call AppConfig.memberRepository
    //call AppConfig.memberRepository
    //call AppConfig.orderService
    //call AppConfig.memberRepository

    //실행 결과
    //call AppConfig.memberService
    //call AppConfig.memberRepository
    //call AppConfig.orderService

    // @Autowired MemberRepository memberRepository; // 추후에 다룰 예정

//    @Bean(name="mmm") // 관례상 default name 을 사용함.
    @Bean
    public MemberService memberService() {
        System.out.println("call AppConfig.memberService");
        return new MemberServiceImpl(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
        System.out.println("call AppConfig.memberRepository");
        return new MemoryMemberRepository();
    }

    @Bean
    public OrderSerivce orderSerivce() {
        System.out.println("call AppConfig.orderService");
        return new OrderServiceImpl(memberRepository(), discountPolicy());
        //return null;
    }

    @Bean
    public DiscountPolicy discountPolicy() {
//        return new FixDiscountPolicy();
        return new RateDiscountPolicy();
    }


}
