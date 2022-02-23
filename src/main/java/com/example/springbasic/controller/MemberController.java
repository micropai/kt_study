package com.example.springbasic.controller;

import com.example.springbasic.dto.req.MemberReqDto;
import com.example.springbasic.entities.Member;
import com.example.springbasic.service.MemberService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/member")
@RequiredArgsConstructor
@Tag(name = "MemberEndpoint", description = "member 에 대한 curd 를 api 를 제공")
public class MemberController {

    private final MemberService memberService;

    @Operation(operationId = "selectMemberDetail",
            summary = "멤버 단건 조회",
            description = "멤버 ID 기준의 상세조회",
            tags = { "MemberEndpoint" },
            responses = {
                    @ApiResponse(responseCode = "200", description = "successful operation",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE ,
                                    schema = @Schema(implementation = MemberResponse.class))) ,
                    @ApiResponse(description = "Not found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal error", responseCode = "500", content = @Content)
            }
    )
    @GetMapping("/{memberId}")
    public ResponseEntity<?> select(@PathVariable("memberId") Long memberId){
        return ResponseEntity.ok(memberService.selectById(memberId, MemberResponse.class));
    }

    @Operation(summary = "멤버 다건 조회",
            description = "검색조건에 의한 멤버 다건 조회",
            tags = { "MemberEndpoint" },
            responses = {
                    @ApiResponse(responseCode = "200", description = "successful operation",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE ,
                                    array = @ArraySchema(schema = @Schema(implementation = MemberResponse.class)))) ,
                    @ApiResponse(description = "Not found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal error", responseCode = "500", content = @Content)
            }
    )
    @GetMapping
    public ResponseEntity<?> select(@Valid MemberRequest req){
        return ResponseEntity.ok(memberService.select(req, MemberResponse.class));
    }

    /**
     * 아이템 생성
     * @param request
     * @return
     */
    @Operation(summary = "멤버 생성",
            description = "신규 멤버 을 추가한다.",
            tags = { "ItemEndpoint" },
            responses = {
                    @ApiResponse(responseCode = "201", description = "created",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE ,
                                    schema = @Schema(implementation = MemberResponse.class))) ,
                    @ApiResponse(description = "Not found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal error", responseCode = "500", content = @Content)
            }
    )
    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody MemberRequest request) {
        MemberResponse response = memberService.insert(request, MemberResponse.class);
        return
                ResponseEntity.created(
                                ServletUriComponentsBuilder
                                        .fromCurrentContextPath().path("/item/" + response.getMemberId())
                                        .buildAndExpand(response.getMemberId()).toUri())
                        .body(response);
    }

}
