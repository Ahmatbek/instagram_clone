<#import "../layout.ftlh" as layout>

<@layout.layout ; spring>
    <style>
        .upload-container {
            max-width: 500px;
            margin: 20px auto;
            padding: 20px;
            border: 1px solid #ddd;
            border-radius: 5px;
            box-shadow: 0 2px 4px rgba(0,0,0,0.1);
            background-color: #fff;
        }
        .form-group {
            margin-bottom: 1rem;
        }
        .form-label {
            display: block;
            margin-bottom: 0.5rem;
            font-weight: 600;
        }
        .form-control {
            width: 100%;
            padding: 0.375rem 0.75rem;
            border: 1px solid #ced4da;
            border-radius: 0.25rem;
        }
        .submit-btn {
            background-color: #4CAF50;
            color: white;
            border: none;
            padding: 0.5rem 1rem;
            border-radius: 0.25rem;
            cursor: pointer;
            transition: background-color 0.15s ease-in-out;
        }
        .submit-btn:hover {
            background-color: #3d8b40;
        }
        .form-icon {
            font-size: 1rem;
        }
    </style>

    <h1 class="pt-4">Upload New File</h1>

    <div class="upload-container">
        <form action="/posts" method="POST" enctype="multipart/form-data">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">

            <div class="form-group">
                <label for="photo" class="form-label">Select Picture</label>
                <input type="file"
                       id="photo"
                       name="photo"
                       class="form-control"
                       <#if post.photo??>value="${post.photo}!"</#if>>
                <#if springMacroRequestContext.model?has_content && springMacroRequestContext.model["postDto"]??>
                    <@spring.bind "postDto.photo"/>
                    <@spring.showErrors "<br>" "text-danger small"/>
                </#if>
            </div>

            <div class="form-group">
                <label for="description" class="form-label">
                    <i class="bi bi-card-heading form-icon me-2"></i>Описание
                </label>
                <@spring.formInput "post.description", 'class="form-control" placeholder="Введите описание" id="description"'/>
                <@spring.showErrors "<br>" "text-danger small"/>
            </div>
            
            <button type="submit" class="submit-btn">Upload File</button>
        </form>
    </div>
</@layout.layout>