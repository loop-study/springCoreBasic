package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderSerivce;
import hello.core.order.OrderServiceImpl;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;

/**
 * @Configurale 로 붙인다
 */
@Configurable
public class AppConfig {

//    @Bean(name="mmm") // 관례상 default name 을 사용함.
    @Bean
    public MemberService memberService() {
        return new MemberServiceImpl(memberRepository());
    }

    @Bean
    public OrderSerivce orderSerivce() {
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    @Bean
    private MemoryMemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

    @Bean
    private DiscountPolicy discountPolicy() {
//        return new FixDiscountPolicy();
        return new RateDiscountPolicy();
    }
}
