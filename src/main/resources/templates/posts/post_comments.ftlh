<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Post View</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.8.0/font/bootstrap-icons.css">
    <style>
        .comment-avatar {
            width: 40px;
            height: 40px;
            border-radius: 50%;
            background-color: #6c757d;
            color: white;
            display: flex;
            align-items: center;
            justify-content: center;
            font-weight: bold;
            margin-right: 12px;
        }

        .comment-date {
            font-size: 0.8rem;
            color: #6c757d;
        }
    </style>
</head>
<body class="bg-light">
<div class="container py-5">
    <div class="card mb-4">
        <#if post.imageUrl??>
            <img src="/images/${post.imageUrl}" class="card-img-top" style="max-height: 500px; object-fit: cover;">
        </#if>
        <div class="card-body">
            <p class="card-text">${post.description}</p>

            <div class="d-flex align-items-center mb-3">
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

    <div class="card mb-4">
        <div class="card-body">
            <h5 class="card-title">Add Comment</h5>
            <form action="/comments" method="post">
                <#if _csrf??>
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                </#if>
                <div class="mb-3">
                    <input type="hidden" name="postId" value="${post.id}" class="form-control" rows="3">
                    <label>
                        <input name="message" class="form-control" rows="3" placeholder="Your comment..." required>
                    </label>
                </div>
                <button type="submit" class="btn btn-primary">Submit</button>
            </form>
        </div>
    </div>

    <div class="card">
        <div class="card-header">
            <h5 class="mb-0">Comments (${comments.content?size})</h5>
        </div>
        <div class="card-body">
            <#if comments.content?? && comments.content?size gt 0>
                <#list comments.content as comment>
                    <div class="mb-3 pb-3 border-bottom">
                        <div class="d-flex">
                            <div class="comment-avatar">
                                <#if comment.userDto?? && comment.userDto.username??>
                                    ${comment.userDto.username?substring(0,1)?upper_case}
                                <#else>
                                    <i class="bi bi-person"></i>
                                </#if>
                            </div>

                            <div class="flex-grow-1">
                                <div class="d-flex justify-content-between align-items-start">
                                    <div>
                                       <#if comment.userDto.avatar??> <img src="/images/${comment.userDto.avatar}" alt="">
                                       <#else>
                                           <div class="rounded-circle bg-secondary d-flex align-items-center justify-content-center text-white mb-3"
                                                style="width: 150px; height: 150px;">
                                               <i class="bi bi-person-fill fs-1"></i>
                                           </div>
                                       </#if>
                                        <h6 class="mb-1">${comment.userDto.username!'Anonymous'}</h6>
                                        <small class="comment-date">
                                            <#if comment.createdAt??>
                                                ${comment.createdAt}
                                            <#else>
                                                Just now
                                            </#if>
                                        </small>
                                    </div>
                                    <form action="/comments/${comment.id}" method="post">
                                        <#if _csrf??>
                                            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                                        </#if>
                                        <input type="hidden" name="_method" value="delete"/>
                                        <button type="submit" class="btn btn-sm btn-outline-danger">
                                            <i class="bi bi-trash"></i>
                                        </button>
                                    </form>
                                </div>
                                <p class="mt-2 mb-0">${comment.message}</p>
                            </div>
                        </div>
                    </div>
                </#list>
            <#else>
                <p class="text-muted">No comments yet</p>
            </#if>
        </div>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>