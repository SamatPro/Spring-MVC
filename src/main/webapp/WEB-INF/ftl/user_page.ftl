
<html>
<head>

</head>
<body>
<table>
    <tr>
        <th>ID</th>
        <th>Name</th>
    </tr>
    <#list clients as client>
        <tr>
        <td>${client.id}</td>
        <td>${client.lastName}</td>
        </tr>
    </#list>
</table>

</body>
</html>