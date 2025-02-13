# spring-boot3-jwt


#User1 : Valid, has User Access
{
    "username": "kamal",
    "password": "user",
    "role" : "ROLE_USER" 
}

#User2 : Valid, has User, Admin Access
{
    "username": "prince",
    "password": "admin",
    "role" : "ROLE_ADMIN,ROLE_USER" 
}

#User2 : InValid, No Access
{
    "username": "kamal123",
    "password": "user",
    "role" : "ROLE_USER123" 
}

#to generate the token 

{
    "username": "prince",
    "password": "admin"
}

{
    "username": "kamal",
    "password": "user"
}

{
    "username": "kamal123",
    "password": "user"
}

#Post Method: 
{
    "policyName" : "Policy1",
    "policyType" : "Type2",
    "premiumAmount" : 78000,
    "userId" : 1
}