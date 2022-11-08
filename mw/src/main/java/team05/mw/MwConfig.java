package team05.mw;

import io.smallrye.config.ConfigMapping;
import io.smallrye.config.WithConverter;
import team05.mw.smallrye.ConfigReadable;
import team05.mw.smallrye.ConfigReadableConverter;

@ConfigMapping(prefix = "team05.mw")
public interface MwConfig {
    @WithConverter(ConfigReadableConverter.class)
    ConfigReadable raststaetten();
}
