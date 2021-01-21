package hello.core.lifecycle;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

//import org.springframework.beans.factory.DisposableBean;
//import org.springframework.beans.factory.InitializingBean;
//implements InitializingBean, DisposableBean
public class NetworkClient  {

    private String url;

    public NetworkClient() {
        //생성자 내부에서 하는건 - 객체의 값 세팅 수준
        //외부 연결 맺는 무거운 작업은 별도의 메소드로 하자.
        System.out.println("생성자 호출, url = " + url);

        //의존관계 종료 후 실행되게 수정됨
//        connet();
//        call("초기화 연결 메시지");
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    //서비스 시작 시 호출
    public void connet() {
        System.out.println("connect = " + url);
    }

    public void call(String name) {
        System.out.println("call: " + url + " message = " + name);
    }

    //서비스 종료 시 호출
    public void disconnect() {
        System.out.println("close = " + url);
    }

    //의존관계가 끝나면 실행 - 2003년에 나옴. 현재는 안씀.
    //단점 외부라이브러리에 사용 못함(코드 수정못함)
//    @Override
//    public void afterPropertiesSet() throws Exception {
//        System.out.println("afterPropertiesSet");
//        connet();
//        call("초기화 연결 메시지");
//    }
//
//    //빈이 종료 될 때 실행됨
//    @Override
//    public void destroy() throws Exception {
//        System.out.println("destroy");
//        disconnect();
//    }


    //javax.?? 로 시작하면 자바에서 공식적으로 지원.
    @PostConstruct
    public void init() {
        System.out.println("NetworkClient.init");
        connet();
        call("초기화 연결 메시지");
    }

    @PreDestroy
    public void close() {
        System.out.println("NetworkClient.close");
        disconnect();
    }
}
