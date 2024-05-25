package TechPartners.__Partners_Backend.controller;

import TechPartners.__Partners_Backend.domain.Url;
import TechPartners.__Partners_Backend.dto.ReqUrlDto;
import TechPartners.__Partners_Backend.dto.ResUrlDto;
import TechPartners.__Partners_Backend.service.UrlService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

@Tag(name = "User", description = "User 관련 API 입니다.")
@CrossOrigin
@Slf4j
@RestController
@RequestMapping("/short-link")
@RequiredArgsConstructor
public class ShortLinkController {

    private final UrlService urlService;

    @Operation(
        summary = "모든 URL 리스트", description = "모든 URL 리스트를 보여줍니다."
    )
    @ApiResponse(
        responseCode = "200", description = "모든 URL 리스트를 정상적으로 출력하였습니다."
    )
    @GetMapping
    public ResponseEntity<List<Url>> allUrl() {
        return ResponseEntity.ok().body(urlService.getAllUrl());
    }

    @Operation(
        summary = "URL 생성", description = "입력받은 URL을 기반으로 short url과 해시값을 생성합니다."
    )
    @ApiResponses({
        @ApiResponse(
            responseCode = "200", description = "URL 생성에 성공하였습니다."
        ),
        @ApiResponse(
            responseCode = "400", description = "입력 데이터 형식 오류가 발생하였습니다."
        )
    })

    @PostMapping
    public ResponseEntity<ResUrlDto> createUrl(@Valid @RequestBody ReqUrlDto reqUrlDto) {
        return ResponseEntity.ok().body(urlService.createUrl(reqUrlDto));
    }

    @Operation(
        summary = "해시값으로 url 리다이렉트", description = "path값을 해시값으로 받고 그 값의 url이 있으면 원본 URL로 리다이렉트를 합니다."
    )
    @ApiResponses({
        @ApiResponse(
            responseCode = "200", description = "URL 리다이렉트에 성공하였습니다."
        ),
        @ApiResponse(
            responseCode = "404", description = "해당 리소스가 존재하지 않습니다."
        )
    })
    @GetMapping("/{hash}")
    public RedirectView redirect(@PathVariable("hash") String hash) {
        ResUrlDto url = urlService.getUrl(hash);
        return new RedirectView(url.getOriginUrl());
    }

    @Operation(
        summary = "해시값 삭제", description = "path값을 해시값으로 받고 그 값에 해당하는 url 데이터를 삭제합니다."
    )
    @ApiResponses({
        @ApiResponse(
            responseCode = "200", description = "URL 삭제에 성공하였습니다."
        ),
        @ApiResponse(
            responseCode = "404", description = "해당 리소스가 존재하지 않습니다."
        )
    })
    @DeleteMapping("/{hash}")
    public ResponseEntity<?> deleteUrl(@PathVariable("hash") String hash) {
        urlService.deleteShortLink(hash);
        return ResponseEntity.ok().body(null);
    }
}
