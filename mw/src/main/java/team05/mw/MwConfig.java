package team05.mw;

import io.smallrye.config.ConfigMapping;
import io.smallrye.config.WithConverter;
import team05.mw.smallrye.ConfigReadable;
import team05.mw.smallrye.ConfigReadableConverter;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

@ConfigMapping(prefix = "team05.mw")
public interface MwConfig {
    @WithConverter(ConfigReadableConverter.class)
    ConfigReadable raststaetten();

    default String raststaettenJson() {
        try (InputStream is = raststaetten().asInputStream()) {
            return new String(is.readAllBytes(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            return null;
        }
    }
}
