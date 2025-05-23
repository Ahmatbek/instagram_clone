<#import "../layout.ftlh" as layout>
<@layout.layout>
    <div class="container py-5">

        <!-- Profile Section -->
        <div class="d-flex flex-column align-items-center text-center mb-5">
            <#if user.avatar?? && user.avatar?has_content>
                <img src="/images/${user.avatar}" class="rounded-circle mb-3" alt="Avatar"
                     style="width: 150px; height: 150px; object-fit: cover;">
            <#else>
                <div class="rounded-circle bg-secondary d-flex align-items-center justify-content-center text-white mb-3"
                     style="width: 150px; height: 150px;">
                    <i class="bi bi-person-fill fs-1"></i>
                </div>
            </#if>

            <h3 class="fw-bold mb-0">${user.username}</h3>
            <p class="text-muted">@${user.login}</p>

            <div class="d-flex justify-content-center gap-4 mb-3">
                <div><strong>${posts?size}</strong> Posts</div>
                <div><strong>${followers}</strong> Followers</div>
                <div><strong>${receiver}</strong> Following</div>
            </div>

            <div class="d-flex flex-wrap justify-content-center gap-2">
               <#if followed>
               <#else>
                   <form action="/followers/${user.login}" method="post" class="d-inline">
                   <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                   <button type="submit" class="btn btn-outline-dark">Follow</button>
               </form>
               </#if>

                <a href="/followers/userFollower" class="btn btn-outline-dark">Followers</a>
                <a href="/followers/userReceiver" class="btn btn-outline-dark">Following</a>
            </div>

            <#if user.info?? && user.info?has_content>
                <p class="mt-3">${user.info}</p>
            </#if>
        </div>

        <!-- Posts Grid -->
        <h4 class="text-center mb-4">Your Posts</h4>

        <#if posts?? && posts?size gt 0>
            <div class="row row-cols-3 g-3">
                <#list posts as post>
                    <div class="col">
                        <div class="card border-0 shadow-sm h-100">
                            <#if post.imageUrl??>
                                <a href="/posts/comment/${post.id}">
                                    <img src="/images/${post.imageUrl}" class="card-img-top" style="object-fit: cover; height: 300px;" alt="Post Image">
                                </a>
                            </#if>
                            <div class="card-body text-center">
                                <p class="card-text">${post.description}</p>
                            </div>
                        </div>

                    </div>
                </#list>
            </div>
        <#else>
            <p class="text-center text-muted">You haven't posted anything yet.</p>
        </#if>
    </div>
</@layout.layout>
