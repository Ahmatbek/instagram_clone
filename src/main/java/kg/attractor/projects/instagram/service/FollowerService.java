package kg.attractor.projects.instagram.service;

import kg.attractor.projects.instagram.dto.FollowerDto;
import kg.attractor.projects.instagram.dto.UserDto;

import java.util.List;

public interface FollowerService {
    int numberOfFollowers(long id);
    int numberOfReceivers(long id);
    List<UserDto> userFollowers(long id);
    List<UserDto> userReceivers(long id);
    void follow(String userId);

    boolean doTheyFollowEachOther(long toFollowId, long receiverId);
}
