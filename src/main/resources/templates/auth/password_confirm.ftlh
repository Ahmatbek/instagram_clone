<#import "../layout.ftlh" as layout>
<link rel="stylesheet" href="/static/css/login.css">
<@layout.layout>
    <div class="custom-body">
        <div class="custom-login-card">
            <div class="custom-brand">
                <div class="custom-brand-logo"></div>
                <h1>Welcome To Job Search Application</h1>
                <p>Enter Your Account Data</p>
            </div>

            <form id="login-form" action="/auth/password/validate" method="get">

                <#if _csrf??>
                    <input type="hidden" name="${(_csrf.parameterName)!'csrf-param-name'}"
                           value="${(_csrf.token)!'csrf-token'}"/>
                </#if>

                <div class="custom-form-group">
                    <label for="password">Password</label>
                    <input
                            type="password"
                            name="password"
                            placeholder="Enter your password"
                            autocomplete="current-password"
                            class="custom-form-control"
                    >
                    <div class="error" id="passwordError"></div>
                    <div class="error" id="passwordError">
                        <#if password_format_error??>
                            <span style="color: red;">${password_format_error}</span>
                        </#if>
                        <#if password_valid_error??>
                            <span style="color: red;">${password_valid_error}</span>
                        </#if>
                    </div>

                </div>

                <button type="submit" class="custom-btn-primary" id="loginButton">
                    Sign in
                </button>
            </form>

            <div class="social-login">
                <p>Or continue with</p>
                <div class="social-buttons">
                    <div class="social-btn">G</div>
                    <div class="social-btn">A</div>
                    <div class="social-btn">F</div>
                </div>
            </div>

            <div class="signup-link">
                <p>Don't have an account? <a href="/auth/registration">Sign up</a></p>
            </div>
        </div>
    </div>
</@layout.layout>
