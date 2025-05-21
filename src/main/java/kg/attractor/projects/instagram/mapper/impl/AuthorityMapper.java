package kg.attractor.projects.instagram.mapper.impl;

import kg.attractor.projects.instagram.dto.AuthorityDto;
import kg.attractor.projects.instagram.mapper.Mapper;
import kg.attractor.projects.instagram.model.Authority;
import org.springframework.stereotype.Service;

@Service
public class AuthorityMapper implements Mapper<AuthorityDto, Authority> {

    @Override
    public AuthorityDto mapToDto(Authority entity) {
        return AuthorityDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .build();
    }

    @Override
    public Authority mapToEntity(AuthorityDto dto) {
        Authority authority = new Authority();
        authority.setId(dto.getId());
        authority.setName(dto.getName());
        return authority;
    }
}
