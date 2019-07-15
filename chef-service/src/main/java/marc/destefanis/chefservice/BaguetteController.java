package marc.destefanis.chefservice;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BaguetteController {
    private static final String baguette = "" +
            "           (\n" +
            "            )  (\n" +
            "           (    )\n" +
            "     ____........____\n" +
            "_-='    /   /   /    `=-._\n" +
            " --..__/___/___/___/___..--";

    @GetMapping("baguette")
    public String getBaguette(){
        return baguette;
    }
}
