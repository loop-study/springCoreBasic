package hello.core.common;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.UUID;

//스프링 띄울 시점에 에러 발생 - scope request 에러시, 고객이 요청해야 생성되기 때문에 찾을 수가 없음.
//해결 방안 - Provider 를 사용하자
//권장 사항 - 스프링 인터셉터를 사용하자, 예제를 위해 컨트롤러에서 작성함.
//proxyMode 를 사용하려면 value 명시해줘야함, 가짜 타겟을 만들어줌.
@Component
@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class MyLogger {

    private String uuid;
    private String requestURL;

    public void setRequestURL(String requestURL) {
        this.requestURL = requestURL;
    }

    public void log(String message) {
        System.out.println("[" + uuid + "][" + requestURL + "]" + message);
    }

    @PostConstruct
    public void init() {
        uuid = UUID.randomUUID().toString();
        System.out.println("[" + uuid + "] request scope bean create: " + this);
    }

    @PreDestroy
    public void close() {
        System.out.println("[" + uuid + "] request scope bean close: " + this);
    }
}
