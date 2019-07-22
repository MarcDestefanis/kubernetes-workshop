package marc.destefanis.bakeryservicev1;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("order")
public class OrderController {
    private static final String baguette = "" +
            "     ____........____\n" +
            "_-='    /       /    `=-._\n" +
            " --..__/___/___/___/___..--";

    @GetMapping("baguette")
    public String getBaguette(){
        return baguette;
    }
}
