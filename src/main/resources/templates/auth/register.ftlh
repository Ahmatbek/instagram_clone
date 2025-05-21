<#import "../layout.ftlh" as layout>

<@layout.layout ; spring>

    <link rel="stylesheet" href="/static/css/login.css">

    <div class="custom-body">
    <div class="custom-login-card">
    <div class="custom-brand">
        <div class="custom-brand-logo"></div>
        <h1>Welcome To Job Search Application</h1>
        <p>Enter Your Account Data</p>
    </div>

    <form method="post" action="/auth/registration" id="registerForm">
        <#if _csrf??>
            <input type="hidden" name="${(_csrf.parameterName)!'csrf-param-name'}"
                   value="${(_csrf.token)!'csrf-token'}"/>
        </#if>
        <div class="custom-form-group">
            <label for="name">User Name</label>
            <@spring.formInput "userDto.username", 'class="custom-form-control" id="name" placeholder="John"'/>
            <@spring.showErrors "<br>" "text-danger small"/>
        </div>

        <div class="custom-form-group">
            <label for="email">Login</label>
            <@spring.formInput "userDto.login", 'class="custom-form-control" id="login" placeholder="login" type="text"'/>
            <@spring.showErrors "<br>" "text-danger small"/>
        </div>

        <div class="custom-form-group">
            <label for="password">Password</label>
            <@spring.formInput "userDto.password", 'class="custom-form-control" id="password" type="password" placeholder="Enter your password" autocomplete="new-password" required'/>
            <@spring.showErrors "<br>" "text-danger small"/>
        </div>

        <div class="custom-form-group">
            <label for="email">User Info</label>
            <@spring.formInput "userDto.info", 'class="custom-form-control" id="info" placeholder="info" type="text"'/>
            <@spring.showErrors "<br>" "text-danger small"/>
        </div>

        <button type="submit" class="custom-btn-primary">Register</button>
    </form>

</@layout.layout>