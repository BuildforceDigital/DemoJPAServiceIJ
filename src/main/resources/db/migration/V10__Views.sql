CREATE view "P0000ProjMembersView" ("P0000ProjectId", "A0000UserId", "Nickname", "UserName", "Gender", "FullName") as
SELECT OLINGO."P0000ProjMembers"."P0000ProjectId",
       OLINGO."P0000ProjMembers"."A0000UserId",
       OLINGO."A0000Users"."Nickname",
       OLINGO."A0000Users"."UserName",
       OLINGO."A0000Users"."Gender",
       OLINGO."A0000Users"."FullName"
FROM OLINGO."P0000ProjMembers",
     OLINGO."A0000Users"
WHERE OLINGO."P0000ProjMembers"."A0000UserId" = OLINGO."A0000Users"."Id";