package dev.kmandalas.urlshortener.dto;

public record ErrorDto(Integer httpStatus, String exception, String message) {}


