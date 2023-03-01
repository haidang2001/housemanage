*** Settings ***
Library    RequestsLibrary
Library     JSONLibrary
Library     Collections

*** Variables ***
${API_Base_Endpoint}        localhost:8080/

*** Test Cases ***
TC01_GetID
    create session      API_GetID_Testing     ${API_Base_Endpoint}
    ${Get_Response}=    Get On Session  API_GetID_Testing             api/account/1
    log to console      ${Get_Response.status_code}
    log to console      ${Get_Response.content}
    ${statusCode}=      convert to string                       ${Get_Response.status_code}
    should be equal     ${statusCode}                           200

TC02_Get
    create session      API_Get_Testing     ${API_Base_Endpoint}
    ${Get_Response}=    Get On Session  API_Get_Testing             api/account
    log to console      ${Get_Response.status_code}
    log to console      ${Get_Response.content}
    should be equal     ${statusCode}                           200

TC03_Post
    create session      AddData     ${API_Base_Endpoint}
    ${body}=        create dictionary       name=dang  gender=male   role=user  phone=123   email=dang@mail.com username=dangdang   password=123456
    ${header}=      create dictionary       Content-Type=application/json
    ${res}=     post request    AddData     api/account data=${body}    headers=${header}
    log to console      ${res.status_code}
    log to console      ${res.content}
    ${statusCode}=      convert to string                       ${res.status_code}
    should be equal     ${statusCode}                           201

TC04_Delete
    create session      API_Delete_Testing     ${API_Base_Endpoint}
    ${res}=     delete request      API_Delete_Testing    api/account/1
    log to console      ${res.status_code}
    log to console      ${res.content}
    ${statusCode}=      convert to string                       ${res.status_code}
    should be equal     ${statusCode}                           200
    ${jsonres}=     to json     ${res.content}
    ${status_list}=     get value from json     ${jsonres}      status
    ${status}=      get from list       ${status_list}      0
    should be equal     ${status}       true

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