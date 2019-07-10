package marc.destefanis.bakeryservice;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BakeryController {
    private static final String bakeryName = "I Love Baguette";

    @GetMapping("/name")
    public String getBakeryName(){
        return bakeryName;
    }
}
