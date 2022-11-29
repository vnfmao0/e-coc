package kr.co.haesungds.login.mapper;

import kr.co.haesungds.login.dto.MemberDto;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
//@Mapper
//@Repository
public class MemberMapper {
    private static Map<Long, MemberDto> store = new ConcurrentHashMap<>();
    private static long sequence = 0L;

    public MemberDto save(MemberDto member) {

        member.setId(++sequence);
        log.info("save: member={}", member);
        store.put(member.getId(), member);

        return member;
    }

    public MemberDto findById(Long id) {
        return store.get(id);
    }

    public Optional<MemberDto> findByLoginId(String loginId) {

        return this.findAll().stream()
                .filter(m -> m.getLoginId().equals(loginId))
                .findFirst();
    }

    public List<MemberDto> findAll() {
        return new ArrayList<>(store.values());
    }

    public void clearStore() {
        store.clear();
    }

    /**
     * 테스트용 데이터 추가
     */
    @PostConstruct
    public void init() {

        MemberDto member = new MemberDto();
        member.setLoginId("test");
        member.setPassword("test!");
        member.setName("테스터");

        save(member);
    }
}
