package marc.destefanis.bakeryservicev2;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ChefClientImpl implements ChefClient {
    private final Logger log = org.slf4j.LoggerFactory.getLogger(ChefClientImpl.class);
    private final RestTemplate restTemplate;
    private final static String orderBaguette = "/baguette";

    @Value("${chef-service.url}")
    private String chefServiceURL;

    public ChefClientImpl() {
        this.restTemplate = new RestTemplate();
    }

    @Override
    public String getBaguette() {
        String url = chefServiceURL + orderBaguette;
        log.info("Query {} endpoint", url);
        return restTemplate.getForEntity(url, String.class).getBody();
    }
}
