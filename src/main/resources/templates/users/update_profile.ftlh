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

    <h1 class="pt-4">Update Profile</h1>

    <div class="upload-container">
        <form action="/users/update/profile" method="POST" enctype="multipart/form-data">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
            <@spring.formHiddenInput "inputUserDto.id", 'class="form-control"'/>

            <div class="form-group">
                <label for="photo" class="form-label">Select Picture</label>
                <input type="file"
                       id="photo"
                       name="avatar"
                       <#if inputUserDto.avatar??>value="${inputUserDto.avatar}" </#if>
                       class="form-control">
            </div>

            <div class="form-group">
                <label for="info" class="form-label">
                    <i class="bi bi-card-heading form-icon me-2"></i>name
                </label>
                <@spring.formInput "inputUserDto.username", 'class="form-control" placeholder="Введите Имя" id="inputUserDtoname"'/>
                <@spring.showErrors "<br>" "text-danger small"/>
            </div>

            <div class="form-group">
                <label for="info" class="form-label">
                    <i class="bi bi-card-heading form-icon me-2"></i>Info
                </label>
                <@spring.formInput "inputUserDto.info", 'class="form-control" placeholder="Введите информацию" id="info"'/>
                <@spring.showErrors "<br>" "text-danger small"/>
            </div>

            <button type="submit" class="submit-btn">Upload Profile</button>
        </form>
    </div>
</@layout.layout>