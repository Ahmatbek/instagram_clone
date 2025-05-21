<#import "../layout.ftlh" as layout>
<@layout.layout>
    <div class="profile-container">

        <h1>User Profile</h1>

        <div class="profile-card">
            <div class="profile-avatar">
                <#if user.avatar?? && user.avatar?has_content>
                    <img src="${user.avatar}" alt="User Avatar">
                <#else>
                    <div class="profile-avatar rounded-circle bg-secondary d-flex align-items-center justify-content-center text-white mx-auto">
                        <i class="bi bi-person-fill fs-3"></i>
                    </div>
                </#if>
                <a href="/followers/userFollower" class="btn btn-success mb-4">Followers ${followers}</a>
                <a href="/followers/userReceiver" class="btn btn-success mb-4">Receivers ${receiver}</a>
            </div>

            <div class="profile-info">
                <p><strong>Login:</strong> ${user.login}</p>
                <p><strong>Username:</strong> ${user.username}</p>
                <p><strong>Role:</strong> ${user.authority.name}</p>
                <#if user.info?? && user.info?has_content>
                    <p><strong>About:</strong> ${user.info}</p>
                </#if>
            </div>

            <#if posts?? && posts?size gt 0>
                <h2 class="mt-5">Your Posts</h2>
                <div class="row row-cols-1 row-cols-md-2 row-cols-lg-3 g-4 mt-3">
                    <#list posts as post>
                        <div class="col">
                            <div class="card h-100 shadow-sm">
                                <#if post.imageUrl??>
                                    <img src="/images/${post.imageUrl}" class="card-img-top" alt="Post Photo">
                                </#if>
                                <div class="card-body">
                                    <p class="card-text">${post.description}</p>
                                </div>
                            </div>
                        </div>
                    </#list>
                </div>
            <#else>
                <p class="text-muted mt-4">You haven't posted anything yet.</p>
            </#if>

        </div>

    </div>
</@layout.layout>
