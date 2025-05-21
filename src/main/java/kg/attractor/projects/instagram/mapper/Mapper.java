package kg.attractor.projects.instagram.mapper;

public interface Mapper<D, E> {
    D mapToDto(E entity);
    E mapToEntity(D dto);
}
