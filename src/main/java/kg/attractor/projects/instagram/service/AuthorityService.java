package kg.attractor.projects.instagram.service;

import kg.attractor.projects.instagram.dto.AuthorityDto;

public interface AuthorityService {
    AuthorityDto findByAuthorityName(String authorityName);
}
