# Spring Security
 - framework that provides authentication , authorization, protection againts common attacks
 - class that support imperative & reactive app

# Features

## Auth ( support servlet & webflux env)

- Password Storage ( interface PasswordEncoder interfaces) 
  - default implementation : NoOpPasswordEncoder
  - others : BcryptPasswordEncoder,
- Delegating Password Encoder
  - Ensuring that password encoded by using the current password storage recommendation
  - allow validating using modern & legacy format
  - allow ugprading encoding the future