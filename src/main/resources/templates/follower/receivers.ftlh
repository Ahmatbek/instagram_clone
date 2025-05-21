<#import "../layout.ftlh" as layout>
<@layout.layout>
    <div class="container mt-5">
        <h1 class="mb-4">Users</h1>

        <#if receivers?? && receivers?size gt 0>
            <div class="list-group">
                <#list receivers as user>
                    <a href="/users/profile/${user.login}" class="list-group-item list-group-item-action d-flex justify-content-between align-items-center">
                        <div>
                            <strong>${user.username}</strong>
                            <span class="text-muted">(@${user.login})</span>
                        </div>
                    </a>
                </#list>
            </div>
        <#else>
            <p class="text-muted">No users found.</p>
        </#if>
    </div>
</@layout.layout>
