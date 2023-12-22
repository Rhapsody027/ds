
// 在 Java 後端中，使用 Spring Boot 或其他框架
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class YourController {

    @PostMapping("/your-endpoint")
    public String handleRequest(@RequestBody YourRequestModel requestModel) {
        // 在這裡處理接收到的字串陣列
        String[] results = requestModel.getResults();

        // 做你的處理邏輯，例如存入資料庫或返回結果
        return "成功處理了字串陣列";
    }
}
