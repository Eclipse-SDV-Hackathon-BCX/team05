package team05.mw.smallrye;

import lombok.extern.slf4j.Slf4j;
import org.eclipse.microprofile.config.spi.Converter;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

@Slf4j
public class ConfigReadableConverter implements Converter<ConfigReadable> {

    @Override
    public ConfigReadable convert(String value) throws IllegalArgumentException, NullPointerException {
        URL url = getResource(value, ConfigReadableConverter.class);
        if (url == null) {
            return null;
        }
        return ConfigReadable.of(url);
    }

    public InputStream getResourceAsStream(String resourceName, Class<?> callingClass) {
        URL url = getResource(resourceName, callingClass);
        try {
            return (url != null) ? url.openStream() : null;
        } catch (IOException ioe) {
            log.error(ioe.getMessage());
            return null;
        }
    }
    public URL getResource(String resourceName, Class<?> callingClass) {
        URL url = Thread.currentThread().getContextClassLoader().getResource(resourceName);

        if (url == null) {
            url = ConfigReadableConverter.class.getClassLoader().getResource(resourceName);
        }

        if (url == null) {
            ClassLoader cl = callingClass.getClassLoader();

            if (cl != null) {
                url = cl.getResource(resourceName);
            }
        }

        if ((url == null) && (resourceName != null)
                && ((resourceName.length() == 0) || (resourceName.charAt(0) != '/'))
                && !(resourceName.contains("classpath:") || resourceName.contains("file:"))) {
            return getResource('/' + resourceName, callingClass);
        }

        return url;
    }

}
