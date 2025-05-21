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
            </div>

            <div class="profile-info">
                <p><strong>Login:</strong> ${user.login}</p>
                <p><strong>Username:</strong> ${user.username}</p>
                <p><strong>Role:</strong> ${user.authority.name}</p>
                <#if user.info?? && user.info?has_content>
                    <p><strong>About:</strong> ${user.info}</p>
                </#if>
            </div>
        </div>

    </div>
</@layout.layout>
