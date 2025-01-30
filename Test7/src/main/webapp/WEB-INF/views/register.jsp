<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User Registration</title>
    <script>
        async function registerUser(event) {
            event.preventDefault(); // Prevent form submission from reloading the page
            const id = document.getElementById("id").value;
            const username = document.getElementById("username").value;
            const password = document.getElementById("password").value;

            const response = await fetch("${pageContext.request.contextPath}/register", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify({ id,username, password })
            });

            const result = await response.json();
            if (response.ok) {
                alert(result.message);
            } else {
                alert("Registration failed. Please try again.");
            }
        }
    </script>
</head>
<body>
    <h2>User Registration</h2>
    <form onsubmit="registerUser(event)">
    
    	<label for="username">id:</label>
        <input type="number" id="id" required><br><br>
    
        <label for="username">Username:</label>
        <input type="text" id="username" required><br><br>

        <label for="password">Password:</label>
        <input type="password" id="password" required><br><br>

        <button type="submit">Register</button>
    </form>
</body>
</html>
