package dev.kmandalas.urlshortener.repository;

import dev.kmandalas.urlshortener.domain.Url;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UrlRepository extends CrudRepository<Url, String> {

}
