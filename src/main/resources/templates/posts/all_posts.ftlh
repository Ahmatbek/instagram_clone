<#import "../layout.ftlh" as layout>
<@layout.layout>
    <div class="container py-5" style="max-width: 600px;">
        <h1 class="text-center mb-5">All Posts</h1>

        <#if posts.content?? && posts.content?size gt 0>
            <div class="d-flex flex-column gap-4">
                <#list posts.content as post>
                    <div class="card shadow-sm border-0" style="border-radius: 12px; overflow: hidden;">
                        <div class="card-header bg-white border-0 d-flex align-items-center"
                             style="padding: 12px 16px; cursor: pointer;"
                             onclick="window.location.href='/users/profile/${post.userDto.login}'">

                            <#if post.userDto.profileImageUrl?? && post.userDto.avatar?has_content>
                                <img src="/images/${post.userDto.avatar}"
                                     class="rounded-circle me-2"
                                     style="width: 32px; height: 32px; object-fit: cover;">
                            <#else>
                                <div class="rounded-circle bg-secondary text-white d-flex align-items-center justify-content-center me-2"
                                     style="width: 32px; height: 32px;">
                                    <i class="bi bi-person"></i>
                                </div>
                            </#if>

                            <div class="d-flex flex-column">
                                <span class="fw-bold">${post.userDto.username!'Unknown'}</span>
                                <small class="text-muted">${post.userDto.login!'@user'}</small>
                            </div>
                        </div>

                        <div onclick="window.location.href='/posts/comment/${post.id}'" style="cursor: pointer;">
                            <#if post.imageUrl?? && post.imageUrl?has_content>
                                <img src="/images/${post.imageUrl}" alt="Post Image"
                                     style="width: 100%; height: auto; max-height: 500px; object-fit: cover; display: block;">
                            </#if>
                        </div>

                        <div class="card-body px-3 py-2">
                            <p class="card-text" style="font-family: 'Helvetica Neue', Helvetica, Arial, sans-serif; font-size: 14px; color: #262626;">
                                ${post.description}
                            </p>

                            <div class="d-flex justify-content-center align-items-center mb-3">
                                <#if post.likedByCurrentUser?? && post.likedByCurrentUser>
                                    <form action="/likes/dislike/${post.id}" method="post" onClick="event.stopPropagation();">
                                        <#if _csrf??>
                                            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                                        </#if>
                                        <button type="submit" class="btn btn-sm btn-primary rounded-start-pill">
                                            <div class="d-flex align-items-center">
                                                <i class="bi bi-hand-thumbs-up-fill me-1"></i>
                                                <span>${post.likesCount!0}</span>
                                            </div>
                                        </button>
                                    </form>
                                <#else>
                                    <form action="/likes/like/${post.id}" method="post" class="me-2" onClick="event.stopPropagation();">
                                        <#if _csrf??>
                                            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                                        </#if>
                                        <button type="submit" class="btn btn-sm btn-outline-secondary rounded-start-pill">
                                            <div class="d-flex align-items-center">
                                                <i class="bi bi-hand-thumbs-up me-1"></i>
                                                <span>${post.likesCount!0}</span>
                                            </div>
                                        </button>
                                    </form>
                                </#if>
                            </div>
                        </div>
                    </div>
                </#list>
            </div>
        <#else>
            <p class="text-center text-muted">No posts available.</p>
        </#if>
    </div>

    <style>
        .card-header:hover {
            background-color: #f8f9fa !important;
        }
    </style>
</@layout.layout>