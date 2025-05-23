package kg.attractor.projects.instagram.service.impl;


import kg.attractor.projects.instagram.dto.FollowerDto;
import kg.attractor.projects.instagram.dto.UserDto;
import kg.attractor.projects.instagram.mapper.impl.FollowerMapper;
import kg.attractor.projects.instagram.repository.FollowerRepository;
import kg.attractor.projects.instagram.service.AuthorizedUserService;
import kg.attractor.projects.instagram.service.FollowerService;
import kg.attractor.projects.instagram.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class FollowerServiceImpl implements FollowerService {
    private final FollowerRepository followerRepository;
    private final FollowerMapper followerMapper;
    private final AuthorizedUserService authorizedUserService;
    private final UserService userService;

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

    @Override
    public void follow(String toFollowId) {
        UserDto userDto = authorizedUserService.getAuthorizedUser();
        UserDto toFollow = userService.findUserByLogin(toFollowId);

        FollowerDto w = new FollowerDto();
        w.setUserFollower(toFollow);
        w.setUserReceiver(userDto);

        if(!doTheyFollowEachOther(toFollow.getId(), userDto.getId())){
            followerRepository.save(followerMapper.mapToEntity(w));
            log.info("Follower followed: {}", toFollowId);
        }
        log.info("Follower followed: {}", toFollowId);



    }

    @Override
    public boolean doTheyFollowEachOther(long toFollowId, long receiverId) {
        return followerRepository.findByUserFollowerIdAndUserReceiver(toFollowId, receiverId).isPresent();
    }

}
