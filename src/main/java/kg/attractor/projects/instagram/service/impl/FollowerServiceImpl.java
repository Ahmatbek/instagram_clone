package kg.attractor.projects.instagram.service.impl;


import kg.attractor.projects.instagram.dto.FollowerDto;
import kg.attractor.projects.instagram.dto.UserDto;
import kg.attractor.projects.instagram.mapper.impl.FollowerMapper;
import kg.attractor.projects.instagram.repository.FollowerRepository;
import kg.attractor.projects.instagram.service.AuthorizedUserService;
import kg.attractor.projects.instagram.service.FollowerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FollowerServiceImpl implements FollowerService {
    private final FollowerRepository followerRepository;
    private final AuthorizedUserService authorizedUserService;
    private final FollowerMapper followerMapper;

    @Override
    public int numberOfFollowers(long id) {
        return followerRepository.getFollowers(id).size();
    }

    @Override
    public int numberOfReceivers(long id) {
        return followerRepository.getReceivers(id).size();
    }

    @Override
    public List<UserDto> userFollowers(long id) {
        List<FollowerDto> followerDtos  = followerRepository.getFollowers(id)
                .stream()
                .map(followerMapper::mapToDto)
                .toList();
        return   followerDtos.stream().map(FollowerDto::getUserReceiver).toList();
    }

    @Override
    public List<UserDto> userReceivers(long id) {
           List<FollowerDto> followerDtos =   followerRepository.getReceivers(id)
                .stream()
                .map(followerMapper::mapToDto)
                .toList();
         return   followerDtos.stream().map(FollowerDto::getUserFollower).toList();

    }

}
