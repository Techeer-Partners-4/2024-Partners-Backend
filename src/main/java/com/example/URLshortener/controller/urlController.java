package com.example.URLshortener.controller;


import com.example.URLshortener.bean.Url;
import com.example.URLshortener.dao.UrlDaoService;
import com.example.URLshortener.exception.UrlNotfoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;
import java.net.URI;

@RestController
//@Api(value = "URLshortner Controller")
public class urlController {

    private UrlDaoService service;

    public urlController(UrlDaoService service) {
        this.service = service;
    }

    @GetMapping("/urls")
    //@ApiOperation(value="URL 목록 전체조회")
    public List<Url> retrieveAllUrl() {
        List<Url> urls = service.findAll();
        return urls;
    }

    @GetMapping("/urls/{urlid}")
    public Url retrieveUrl(@PathVariable int urlid, boolean active) {
        Url url =  service.findOne(urlid, active);

        if (url == null) {
            throw new UrlNotfoundException(String.format("ID[%s] not found", urlid));
        }

        return url;

    }

    @PostMapping("/urls")
    public ResponseEntity<Url> createUrl(@RequestBody Url url) {
        Url savedUrl = service.save(url);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{urlid}")
                .buildAndExpand(savedUrl.getUrlid())
                .toUri();

        return ResponseEntity.created(location).build();
    }
    @DeleteMapping("/urls/{urlid}")
    public ResponseEntity deleteUrl(@PathVariable int urlid) {
        Url deletedUrl = service.deleteById(urlid);

        if (deletedUrl == null) {
            throw new UrlNotfoundException(String.format("ID[%s] not found",urlid));
        }

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/short-links/{hash}")
    public ResponseEntity<Void> redirectUrl(@PathVariable String hash) {
        Url url = service.findByHash(hash);
        if (url.isActive()) {
            return ResponseEntity.status(302).location(URI.create(url.getOriginurl())).build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}