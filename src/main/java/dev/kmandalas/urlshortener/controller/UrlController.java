package dev.kmandalas.urlshortener.controller;

import dev.kmandalas.urlshortener.domain.Url;
import dev.kmandalas.urlshortener.dto.UrlDto;
import dev.kmandalas.urlshortener.service.UrlManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/urlShortener")
@Slf4j
public class UrlController {

    private final UrlManager urlManager;

    public UrlController(UrlManager urlManager) {this.urlManager = urlManager;}

    @PostMapping
    public ResponseEntity<String> shortenUrl(@RequestBody UrlDto dto) {
        log.info("From: " + dto);
        Url shortUrlEntry = urlManager.shortenUrl(dto.url());

        log.info("To: " + shortUrlEntry);
        return ResponseEntity.ok(shortUrlEntry.getShortened());
    }

    @GetMapping(value = "/{key}")
    @ResponseBody
    public ResponseEntity<String> getUrl(@PathVariable String key) {
        log.info("Requested: " + key);
        String url = urlManager.getUrlByKey(key);

        log.info("Returned: " + url);
        return ResponseEntity.ok(url);
    }

}
