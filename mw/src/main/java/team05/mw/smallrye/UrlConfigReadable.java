package team05.mw.smallrye;

import lombok.NonNull;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.Objects;

public class UrlConfigReadable implements ConfigReadable {

    private final URL url;

    UrlConfigReadable(URL url) {
        Objects.requireNonNull(url);
        this.url = url;
    }

    @NonNull
    @Override
    public InputStream asInputStream() throws IOException {
        URLConnection con = url.openConnection();
        con.setUseCaches(true);
        try {
            return url.openStream();
        } catch (IOException ioe) {
            if (con instanceof HttpURLConnection) {
                ((HttpURLConnection) con).disconnect();
            }
            throw ioe;
        }
    }

}
