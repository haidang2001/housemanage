*** Settings ***
Library    RequestsLibrary
Library     JSONLibrary
Library     Collections

*** Variables ***
${API_Base_Endpoint}        http://localhost:8080/
${user}    admin
${passwd}    123456
&{headers}  Content-Type=application/json  Authorization=Basic YWRtaW46MTIzNDU2

*** Test Cases ***
TC01_TestGetAccountByID
    ${auth}=  Create List   ${user}  ${passwd}
    create session      API_GetID_Testing     ${API_Base_Endpoint}      headers=${headers}  auth=${auth}
    ${Get_Response}=    Get On Session  API_GetID_Testing             api/account/1

    log to console      ${Get_Response.status_code}
    log to console      ${Get_Response.content}

    ${statusCode}=      convert to string                       ${Get_Response.status_code}
    should be equal     ${statusCode}                           200
    ${body}=        convert to string                           ${Get_Response.content}
    should be equal      ${body}     {"status":0,"message":"Account found with id: 1","data":{"id":1,"name":"manager","birthDate":"2000-03-02T00:00:00.000+00:00","gender":"female","role":"manager","phone":"0395677415","email":"manager@gmail.com","idNumber":123,"house":{"id":1,"location":"457 Truong Trinh QTan Binh","name":"Truong Chinh","establishDate":"01/07/2022","totalRooms":4,"manager":"Thao Nguyen","status":"active","description":"house at TC street","image":"C:/vid"},"position":"bve","username":"manager","password":"$2a$10$1OVs2CkpofvA3ZxUmq3ureA19xtaGl9cZbDEzRwVURc.Ou/4XkC8u","startedDate":"2000-03-02T00:00:00.000+00:00","status":"working","description":"hello"}}
    ${contentTypeValue}=     get from dictionary     ${Get_Response.headers}     Content-Type
    should be equal     ${contentTypeValue}     application/json

TC02_TestGetListAccount
    ${auth}=  Create List   ${user}  ${passwd}
    create session      API_Get_Testing     ${API_Base_Endpoint}    headers=${headers}  auth=${auth}
    ${Get_Response}=    Get On Session  API_Get_Testing             api/account

    log to console      ${Get_Response.status_code}
    log to console      ${Get_Response.content}

    ${statusCode}=      convert to string                       ${Get_Response.status_code}
    should be equal     ${statusCode}                           200
    ${body}=        convert to string                           ${Get_Response.content}
    should be equal      ${body}     [{"id":1,"name":"manager","birthDate":"2000-03-02T00:00:00.000+00:00","gender":"female","role":"manager","phone":"0395677415","email":"manager@gmail.com","idNumber":123,"house":{"id":1,"location":"457 Truong Trinh QTan Binh","name":"Truong Chinh","establishDate":"01/07/2022","totalRooms":4,"manager":"Thao Nguyen","status":"active","description":"house at TC street","image":"C:/vid"},"position":"bve","username":"manager","password":"$2a$10$1OVs2CkpofvA3ZxUmq3ureA19xtaGl9cZbDEzRwVURc.Ou/4XkC8u","startedDate":"2000-03-02T00:00:00.000+00:00","status":"working","description":"hello"},{"id":2,"name":"admin","birthDate":"2000-03-02T00:00:00.000+00:00","gender":"female","role":"admin","phone":"0395677415","email":"admin@gmail.com","idNumber":123,"house":{"id":1,"location":"457 Truong Trinh QTan Binh","name":"Truong Chinh","establishDate":"01/07/2022","totalRooms":4,"manager":"Thao Nguyen","status":"active","description":"house at TC street","image":"C:/vid"},"position":"bve","username":"admin","password":"$2a$10$GoliH4KUachLu8uRf/TTiugXHt0hssqE5F5qOySQ0mB9ZAQJ0kT9O","startedDate":"2000-03-02T00:00:00.000+00:00","status":"working","description":"hello"}]
    ${contentTypeValue}=     get from dictionary     ${Get_Response.headers}     Content-Type
    should be equal     ${contentTypeValue}     application/json


TC03_TestPostNewAccount
    create session      AddNewAccount     ${API_Base_Endpoint}
    ${body}=        create dictionary       name=dang  gender=male   role=user  phone=123   email=dang@mail.com username=dangdang   password=123456
    ${header}=      create dictionary       Content-Type=application/json
    ${res}=     post request    AddNewAccount     api/account/add     data=${body}    headers=${header}

    log to console      ${res.status_code}
    log to console      ${res.content}

    ${statusCode}=      convert to string                       ${res.status_code}
    should be equal     ${statusCode}                           200
    ${contentBody}=        convert to string                           ${res.content}
    should contain      ${contentBody}     0
    should contain      ${contentBody}     Add successful account
    should contain      ${contentBody}     {"id":159,"name":"dang","birthDate":null,"gender":"male","role":"user","phone":"123","email":"dang@mail.com username=dangdang","idNumber":0,"house":null,"position":null,"username":null,"password":"$2a$10$6WvYnRyEpXeZdbluY4Sw5euxhvfB9l/ohrviMzbsvyqObScJ5YS3q","startedDate":null,"status":null,"description":null}
    ${contentTypeValue}=     get from dictionary     ${Get_Response.headers}     Content-Type
    should be equal     ${contentTypeValue}     application/json

TC04_DeleteAccountById
    ${auth}=  Create List   ${user}  ${passwd}
    create session      API_Delete_Testing     ${API_Base_Endpoint}
    ${res}=     delete request      API_Delete_Testing    api/account/161       headers=${headers}

    log to console      ${res.status_code}
    log to console      ${res.content}

    ${statusCode}=      convert to string                       ${res.status_code}
    should be equal     ${statusCode}                           200
    ${res_body}=        convert to string                       ${res.content}
    should contain      ${res_body}     0
    should contain      ${res_body}     Delete account successfully

    #comment
#    ${jsonres}=     to json     ${res.content}
#    ${status_list}=     get value from json     ${jsonres}      status
#    ${status}=      get from list       ${status_list}      0
#    should be equal     ${status}       true

TC06_UpdateAccountById
    ${auth}=  Create List   ${user}  ${passwd}
    create session      UpdateAccount     ${API_Base_Endpoint}     headers=${headers}  auth=${auth}
    ${body}=        create dictionary       name=dang  gender=male   role=user  phone=123   email=dang@mail.com username=dangdang   password=123456
    ${header}=      create dictionary       Content-Type=application/json
    ${res}=     put request    UpdateAccount     api/account/163     data=${body}    headers=${header}

    log to console      ${res.status_code}
    log to console      ${res.content}

    ${statusCode}=      convert to string                       ${res.status_code}
    should be equal     ${statusCode}                           200
    ${body}=        convert to string                           ${res.content}
    should contain      ${body}     0
    should contain      ${body}     Update account successfully
    should contain      ${body}     dang
#    ${contentTypeValue}=     get from dictionary     ${Get_Response.headers}     Content-Type
#    should be equal     ${contentTypeValue}     application/json


TC01_GetIDHouse
    create session      API_GetID_Testing     ${API_Base_Endpoint}
    ${Get_Response}=    Get On Session  API_GetID_Testing             api/house/1

    log to console      ${Get_Response.status_code}
    log to console      ${Get_Response.content}

    ${statusCode}=      convert to string                       ${Get_Response.status_code}
    should be equal     ${statusCode}                           200

TC02_GetHouses
    create session      API_Get_Testing     ${API_Base_Endpoint}
    ${Get_Response}=    Get On Session  API_Get_Testing             api/house

    log to console      ${Get_Response.status_code}
    log to console      ${Get_Response.content}
    should be equal     ${statusCode}                           200

TC01_GetIDRoom
    create session      API_GetID_Testing     ${API_Base_Endpoint}
    ${Get_Response}=    Get On Session  API_GetID_Testing             api/room/1

    log to console      ${Get_Response.status_code}
    log to console      ${Get_Response.content}
    ${statusCode}=      convert to string                       ${Get_Response.status_code}
    should be equal     ${statusCode}                           200

TC02_GetRooms
    create session      API_Get_Testing     ${API_Base_Endpoint}
    ${Get_Response}=    Get On Session  API_Get_Testing             api/room

    log to console      ${Get_Response.status_code}
    log to console      ${Get_Response.content}
    should be equal     ${statusCode}                           200