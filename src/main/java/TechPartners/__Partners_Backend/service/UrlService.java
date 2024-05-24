package TechPartners.__Partners_Backend.service;

import TechPartners.__Partners_Backend.domain.Url;
import TechPartners.__Partners_Backend.dto.ReqUrlDto;
import TechPartners.__Partners_Backend.dto.ResUrlDto;
import TechPartners.__Partners_Backend.repository.UrlRepository;
import TechPartners.__Partners_Backend.util.SHA256;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UrlService {

    private final UrlRepository urlRepository;

    public ResUrlDto createUrl(ReqUrlDto reqUrlDto){
        String originUrl = reqUrlDto.getOriginUrl();
        String encoded = SHA256.encode(originUrl);
        Url newUrl = Url.builder()
            .originUrl(originUrl)
            .hash(encoded)
            .build();

        urlRepository.save(newUrl);

        return new ResUrlDto(
            newUrl.getOriginUrl(),
            newUrl.getShortUrl(),
            newUrl.getHash(),
            newUrl.getCreatedAt()
        );
    }

    public ResUrlDto getUrl(String hash){
        Url byHashAndDeleted = urlRepository.findByHashAndDeleted(hash, false).orElseThrow();

        return new ResUrlDto(
            byHashAndDeleted.getOriginUrl(),
            byHashAndDeleted.getShortUrl(),
            byHashAndDeleted.getHash(),
            byHashAndDeleted.getCreatedAt()
        );
    }

    public List<Url> getAllUrl(){
        return urlRepository.findAllByDeleted(false);
    }

    public void deleteShortLink(String hash){
        Url url = urlRepository.findByHashAndDeleted(hash, false).orElseThrow();
        url.deleteUrl();
        urlRepository.save(url);
    }
}
