package kg.attractor.projects.instagram.service.impl;

import kg.attractor.projects.instagram.dto.AuthorityDto;
import kg.attractor.projects.instagram.mapper.impl.AuthorityMapper;
import kg.attractor.projects.instagram.repository.AuthorityRepository;
import kg.attractor.projects.instagram.service.AuthorityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class AuthorityServiceImpl implements AuthorityService {
    private final AuthorityRepository authorityRepository;
    private final AuthorityMapper authorityMapper;

    @Override
    public AuthorityDto findByAuthorityName(String authorityName) {
        Assert.isTrue(authorityName != null && !authorityName.isBlank(), "authorityName is blank");

        return authorityRepository.findAuthorityByName(authorityName)
                .map(authorityMapper::mapToDto)
                .orElseThrow(() -> new NoSuchElementException("authority by name " + authorityName + " not found"));
    }
}
