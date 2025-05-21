package kg.attractor.projects.instagram.service.impl;

import kg.attractor.projects.instagram.dto.PostDto;
import kg.attractor.projects.instagram.dto.UserDto;
import kg.attractor.projects.instagram.mapper.impl.PostMapper;
import kg.attractor.projects.instagram.model.Post;
import kg.attractor.projects.instagram.model.User;
import kg.attractor.projects.instagram.repository.PostRepository;
import kg.attractor.projects.instagram.service.AuthorizedUserService;
import kg.attractor.projects.instagram.service.PostService;
import kg.attractor.projects.instagram.util.Util;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {
    private final PostMapper postMapper;
    private final AuthorizedUserService authorizedUserService;
    private final PostRepository postRepository;

    @Override
    public PostDto savePost(PostDto postDto) throws IOException {
        postDto.setUserId(authorizedUserService.getAuthorizedUser().getId());
        String fileName = Util.uploadResource(postDto.getPhoto());
        Post post = postMapper.mapToEntity(postDto);
        post.setPhoto(fileName);
        return postMapper.mapToDto(postRepository.save(post));
    }

    @Override
    public void deletePost(Long postId) {
        Long userId = authorizedUserService.getAuthorizedUser().getId();

        postRepository.findByPostIdAndUserId(userId,postId)
                .orElseThrow(()-> new NoSuchElementException("Post not found"+postId));
        postRepository.deleteById(postId);

    }

    @Override
    public List<PostDto> getAllPosts() {
        return postRepository.findAll()
                .stream()
                .map(postMapper::mapToDto)
                .toList();
    }

    @Override
    public List<PostDto> getUsersPosts(long userId) {
        return postRepository.findUsersPosts(userId)
                .stream()
                .map(postMapper::mapToDto)
                .toList();
    }
}
