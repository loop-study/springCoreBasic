package hello.core.order;

import hello.core.annotation.MainDiscountPolicy;
import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

// 롬복을 적용해보자
// @RequiredArgsConstructor 생성자를 만들어준다. final 붙은 필드를 args 로 갖게 된다.
//@RequiredArgsConstructor
@Component
public class OrderServiceImpl implements OrderSerivce {

    // 필드 주입 방식 - 비권장한다, 테스트 힘듬, 테스트 하려면 setter 만들어서 직접 new 해야함.
//    @Autowired private MemberRepository memberRepository;// = new MemoryMemberRepository();
    private final MemberRepository memberRepository;// = new MemoryMemberRepository();
    //    private final DiscountPolicy discountPolicy = new FixDiscountPolicy();
    private final DiscountPolicy discountPolicy;// = new RateDiscountPolicy();

    // setter 방식으로 주입 start
//    @Autowired
//    public void setMemberRepository(MemberRepository memberRepository) {
//        System.out.println("set memberRepository = " + memberRepository);
//        this.memberRepository = memberRepository;
//    }
//
//    @Autowired
//    public void setDiscountPolicy(DiscountPolicy discountPolicy) {
//        System.out.println("set discountPolicy = " + discountPolicy);
//        this.discountPolicy = discountPolicy;
//    }
    // setter 방식으로 주입 end

    // @RequiredArgsConstructor 사용으로 주석처리함
    // 생성자가 하나라면 @Autowired 생략이 가능
    public OrderServiceImpl(MemberRepository memberRepository, @MainDiscountPolicy DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    // 일반 메서드 주입 - 생성자와 비슷. 잘 사용안함.
//    @Autowired
//    public void init(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
//        this.memberRepository = memberRepository;
//        this.discountPolicy = discountPolicy;
//    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);

        // 할인에 대한걸 디스카운트폴리스로 관리. 단일의 원칙 잘 설계된 케이스
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }

    // 싱글톤 테스트 용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
