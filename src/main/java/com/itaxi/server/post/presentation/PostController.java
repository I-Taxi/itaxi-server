package com.itaxi.server.post.presentation;

import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;

import com.itaxi.server.docs.ApiDoc;
import com.itaxi.server.place.application.PlaceDto;
import com.itaxi.server.place.domain.repository.PlaceRepository;
import com.itaxi.server.post.application.PostService;
import com.itaxi.server.post.domain.repository.PostRepository;
import com.itaxi.server.post.domain.Post;
import com.itaxi.server.place.domain.Place;
import com.itaxi.server.post.application.PostDto;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import com.itaxi.server.post.application.dto.JoinerCreateDto;
import com.itaxi.server.post.application.dto.PostJoinDto;
import com.itaxi.server.post.domain.Joiner;
import com.itaxi.server.post.presentation.request.PostExitRequest;
import com.itaxi.server.post.presentation.request.PostJoinRequest;
import com.itaxi.server.post.presentation.response.PostInfoResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.annotations.ApiOperation;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/post")
public class PostController {
    private final PostService postService;
    private final PostRepository postRepository;
    private final PlaceRepository placeRepository;

    /*@GetMapping
    @ApiOperation(value = ApiDoc.TEST)
    ResponseEntity<Object> aaa(){
        System.out.println("aaaa");
        return ResponseEntity.ok(null);
    }*/

    @RequestMapping(method = RequestMethod.GET)
    public Iterable<Post> findAllPost() {
        return postRepository.findAll();
    }

    @RequestMapping(value = "/if", method = RequestMethod.GET)
    //public PostDto.Res findPost(@RequestParam("dep")final long dep_id, @RequestParam("dst")final long dst_id, , @RequestBody final PostDto.GetPostReq dto) {
    public Iterable<Post> getPost(@RequestParam("depId")final Long depId, @RequestParam("dstId")final Long dstId, @RequestParam("time")final LocalDateTime time) {
        final Place departure = placeRepository.getById(depId);
        final Place destination = placeRepository.getById(dstId);
        return postRepository.findAllByDepartureAndDestinationAndDeptTime(departure, destination, time);
        //return new PostDto.Res();
    }

    @RequestMapping(value = "/ifdto", method = RequestMethod.GET)
    public List<PostDto.Res> getPostDto(@RequestParam("depId")final Long depId, @RequestParam("dstId")final Long dstId) {
        final Long departureId = depId;
        final Place departure = placeRepository.getById(depId);
        final Place destination = placeRepository.getById(dstId);
        List<PostDto.Res> resultList = (postRepository.findAllByDepartureAndDestination(departure, destination)).stream()
                .map(m -> new PostDto.Res(m))
                .collect(Collectors.toList());
        return resultList;
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.CREATED)
    public PostDto.Res create(@RequestBody final PostDto.AddPostReq dto) {
        final Place departure = placeRepository.getById(dto.getDepId());
        final Place destination = placeRepository.getById(dto.getDstId());
        PostDto.AddPostPlaceReq postPlaceDto = new PostDto.AddPostPlaceReq(dto, departure, destination);
        return new PostDto.Res(postService.create(postPlaceDto));
    }

    @PostMapping("/{postId}/join")
    @ApiOperation(value = ApiDoc.JOIN_POST)
    public ResponseEntity<PostInfoResponse> joinPost(@PathVariable Long postId, @RequestBody PostJoinRequest request) {
        Post postInfo = postService.joinPost(postId, PostJoinDto.from(request));
        PostInfoResponse result = postService.readPost(postInfo);

        return ResponseEntity.ok(result);
    }

    @PutMapping("/{postId/join")
    @ApiOperation(value = ApiDoc.EXIT_POST)
    public ResponseEntity<String> exitPost(@PathVariable Long postId, @RequestBody PostExitRequest request) {
        String result = postService.exitPost(postId, request.getUid());

        return ResponseEntity.ok(result);
    }
}