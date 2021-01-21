package hello.core.order;

import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.internal.matchers.Or;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

// 자바 테스트
class OrderServiceImplTest {

    @Test
    void createOrder() {
        // 생성자 주입 방식에서 임의로 넣어버리면?
        MemoryMemberRepository memberRepository = new MemoryMemberRepository();
        memberRepository.save(new Member(1L, "name", Grade.VIP));

        OrderServiceImpl orderService = new OrderServiceImpl(memberRepository, new FixDiscountPolicy());
//        orderService.setDiscountPolicy(new RateDiscountPolicy());
//        orderService.setMemberRepository(new MemoryMemberRepository());
        Order order = orderService.createOrder(1L, "itemA", 10000);
        assertThat(order.getDiscountPrice()).isEqualTo(1000);
    }
}