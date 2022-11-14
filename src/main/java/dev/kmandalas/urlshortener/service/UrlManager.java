package dev.kmandalas.urlshortener.service;

import com.google.common.hash.Hashing;
import dev.kmandalas.urlshortener.domain.Url;
import dev.kmandalas.urlshortener.repository.UrlRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.nio.charset.Charset;
import java.time.LocalDateTime;

@Service
@Slf4j
public class UrlManager {

    @Value("${app.settings.base_url}")
    private String base_url;

    final UrlRepository urlRepository;

    public UrlManager(UrlRepository urlRepository) {this.urlRepository = urlRepository;}

    public String getUrlByKey(String key) {
        var entry =
            urlRepository.findById(key).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return  entry.getOriginal();
    }

    public Url shortenUrl(String url) {
        String key = Hashing
            .murmur3_32_fixed()
            .hashString(url, Charset.defaultCharset()).toString();

        Url entry = Url.of(key, url, base_url + "/" + key);
        entry.setCreatedAt(LocalDateTime.now());
        urlRepository.save(entry);

        return entry;
    }

}
