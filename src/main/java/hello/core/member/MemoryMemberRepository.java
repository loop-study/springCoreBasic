package hello.core.member;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * db 가 안 정해졌으니 임시로 메모리로 사용함.
 */
public class MemoryMemberRepository implements MemberRepository {

    // 동시성 이슈 때문에 ConcurrentHashMap 가 맞다.
    private static Map<Long, Member> store = new HashMap<>();

    @Override
    public void save(Member member) {
        store.put(member.getId(), member);
    }

    @Override
    public Member findById(Long memberId) {
        return store.get(memberId);
    }
}
