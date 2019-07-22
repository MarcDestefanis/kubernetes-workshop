package marc.destefanis.bakeryservicev2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("order")
public class OrderController {
    private ChefClient chefClient;

    @Autowired
    public OrderController(ChefClient chefClient) {
        this.chefClient = chefClient;
    }

    @GetMapping("baguette")
    public String getBaguette(){
        return chefClient.getBaguette();
    }
}
