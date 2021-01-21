package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderSerivce;
import hello.core.order.OrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(
        basePackages = "hello.core", // 특정 패키지영역 지정 가능, 디폴트는 해당 클래스의 패키지가 기준이 된다.
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
)
public class AutoAppConfig {

    // 필드 주입 테스트 방법...
//    @Autowired MemberRepository memberRepository;
//    @Autowired DiscountPolicy discountPolicy;
//
//    @Bean
//    OrderSerivce orderSerivce() {
//        return new OrderServiceImpl(memberRepository, discountPolicy);
//    }

    // 같은 이름으로 충돌남
//    @Bean(name = "memoryMemberRepository")
//    MemberRepository memberRepository() {
//        return new MemoryMemberRepository();
//    }

}
