<#import "../layout.ftlh" as layout>
<@layout.layout>
    <div class="container mt-5">
        <h1 class="mb-4 text-center">Followings</h1>

        <#if receivers?? && receivers?size gt 0>
            <div class="row">
                <#list receivers as user>
                    <div class="col-12 mb-3">
                        <a href="/users/profile/${user.login}" class="text-decoration-none text-dark">
                            <div class="card shadow-sm border-0 hover-shadow">
                                <div class="card-body d-flex align-items-center gap-3">
                                    <#if user.avatar?? && user.avatar?has_content>
                                        <img src="/images/${user.avatar}" alt="Avatar" class="rounded-circle" style="width: 50px; height: 50px; object-fit: cover;">
                                    <#else>
                                        <div class="bg-secondary text-white rounded-circle d-flex align-items-center justify-content-center" style="width: 50px; height: 50px;">
                                            <i class="bi bi-person-fill"></i>
                                        </div>
                                    </#if>

                                    <div>
                                        <h5 class="mb-0">${user.username}</h5>
                                        <small class="text-muted">@${user.login}</small>
                                    </div>
                                </div>
                            </div>
                        </a>
                    </div>
                </#list>
            </div>
        <#else>
            <p class="text-muted text-center">No users found.</p>
        </#if>
    </div>
</@layout.layout>
