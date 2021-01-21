package hello.core.order;

import hello.core.AppConfig;
import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class OrderSerivceTest {

    MemberService memberService;// = new MemberServiceImpl();
    OrderSerivce orderSerivce;// = new OrderServiceImpl();

    @BeforeEach
    public void beforeEach() {
        AppConfig appConfig = new AppConfig();
        memberService = appConfig.memberService();
        orderSerivce = appConfig.orderSerivce();
    }

    @Test
    void createOrder() {
        Long memberId = 1L;
        Member member = new Member(memberId, "memberA", Grade.VIP);
        memberService.join(member);

        Order order = orderSerivce.createOrder(memberId, "itemA", 10000);
        Assertions.assertThat(order.getDiscountPrice()).isEqualTo(1000);
    }

    // 필드 주입 방식 - 테스트가 안된다.
    // setter 를 만들어야 테스트가 가능해짐.
//    @Test
//    void fieldInjectionTest() {
//        OrderServiceImpl orderService = new OrderServiceImpl();
//        orderService.setMemberRepository(new MemoryMemberRepository());
//        orderService.setDiscountPolicy(new RateDiscountPolicy());

//        orderService.createOrder(1L, "itemA", 10000); // null 에러 발생
//    }
}