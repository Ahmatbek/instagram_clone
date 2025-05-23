<#import "../layout.ftlh" as layout>
<@layout.layout>
    <div class="container py-5" style="max-width: 600px;">  <!-- Instagram feed max width -->
        <h1 class="text-center mb-5">All Posts</h1>

        <#if posts?? && posts?size gt 0>
            <div class="d-flex flex-column gap-4">
                <#list posts as post>
                    <div class="card shadow-sm border-0" style="border-radius: 12px; overflow: hidden;">
                        <#if post.imageUrl?? && post.imageUrl?has_content>
                            <img src="/images/${post.imageUrl}" alt="Post Image"
                                 style="width: 100%; height: auto; max-height: 500px; object-fit: cover; display: block;">
                        </#if>
                        <div class="card-body px-3 py-2">
                            <p class="card-text" style="font-family: 'Helvetica Neue', Helvetica, Arial, sans-serif; font-size: 14px; color: #262626;">
                                ${post.description}
                            </p>
                        </div>
                    </div>
                </#list>
            </div>
        <#else>
            <p class="text-center text-muted">No posts available.</p>
        </#if>
    </div>
</@layout.layout>
