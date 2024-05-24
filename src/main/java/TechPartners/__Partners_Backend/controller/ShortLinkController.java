package TechPartners.__Partners_Backend.controller;

import TechPartners.__Partners_Backend.domain.Url;
import TechPartners.__Partners_Backend.dto.ReqUrlDto;
import TechPartners.__Partners_Backend.dto.ResUrlDto;
import TechPartners.__Partners_Backend.service.UrlService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

@Slf4j
@RestController
@RequestMapping("/short-link")
@RequiredArgsConstructor
public class ShortLinkController {

    private final UrlService urlService;

    @GetMapping
    public ResponseEntity<List<Url>> allUrl(){
        return ResponseEntity.ok().body(urlService.getAllUrl());
    }

    @PostMapping
    public ResponseEntity<ResUrlDto> createUrl(@RequestBody ReqUrlDto reqUrlDto){
        return ResponseEntity.ok().body(urlService.createUrl(reqUrlDto));
    }

    @GetMapping("/{hash}")
    public RedirectView redirect(@PathVariable("hash") String hash){
        ResUrlDto url = urlService.getUrl(hash);
        return new RedirectView(url.getOriginUrl());
    }

    @DeleteMapping("/{hash}")
    public ResponseEntity<?> deleteUrl(@PathVariable("hash") String hash){
        urlService.deleteShortLink(hash);
        return ResponseEntity.ok().body(null);
    }
}
