package kg.attractor.projects.instagram.mapper.impl;

import kg.attractor.projects.instagram.dto.PageHolder;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PageHolderWrapper {

    public <T> PageHolder<T> wrapPageHolder(Page<T> pages) {
        return PageHolder.<T>builder()
                .content(pages.getContent())
                .page(pages.getNumber())
                .size(pages.getSize())
                .totalPages(pages.getTotalPages())
                .hasNextPage(pages.hasNext())
                .hasPreviousPage(pages.hasPrevious())
                .build();
    }
}
