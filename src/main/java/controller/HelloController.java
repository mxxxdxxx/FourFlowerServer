package controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


/**
 * 클래스를 Spring MVC 컨트롤러로 표시하는 데 사용
 * HTTP 요청을 처리
 */
@Controller
public class HelloController {
    /**
     * @GetMapping("hello-mvc")
     * 지정된 URL 경로("/hello-mvc")가 포함된 HTTP GET 요청을 helloMvc 메서드에 매핑
     */
    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam("user_name") String name, Model model) {
        /**
         * @RequestParam("user_name") : name이 HTTP 요청의 "user_name"이라는 쿼리 매개변수 값으로 채워져야 함
         * model: 뷰에 전달될 데이터를 나타냅니다.
         */
        model.addAttribute("user_name", name);
        return "hello-template";
        /**
         * 요청을 처리한 후 프레임워크가 "hello-template"이라는 뷰를 렌더링해야 함
         */
    }
}