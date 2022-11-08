package team05.mw.smallrye;

import lombok.NonNull;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Objects;

public interface ConfigReadable {

    InputStream asInputStream() throws IOException;

    static @NonNull ConfigReadable of(URL url) {
        Objects.requireNonNull(url);
        return new UrlConfigReadable(url);
    }

}
