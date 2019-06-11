package io.pivotal.pal.tracker;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class EnvController {
    private Map<String, String> env = new HashMap<>();

    public EnvController(@Value("${port:8080}") String port,
                         @Value("${memory.limit:2048}") String memLimit,
                         @Value("${cf.instance.index:5}") String cfInstanceIndex,
                         @Value("${cf.instance.addr:NO SET}") String cfInstanceAddress) {
        env.put("PORT", port);
        env.put("MEMORY_LIMIT", memLimit);
        env.put("CF_INSTANCE_INDEX", cfInstanceIndex);
        env.put("CF_INSTANCE_ADDR", cfInstanceAddress);
    }

    @GetMapping("/env")
    public Map<String, String> getEnv() {
        return env;
    }
}
