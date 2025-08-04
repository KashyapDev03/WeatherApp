<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    String city = (String) request.getAttribute("city");
    String weather = (String) request.getAttribute("weather");
    Double temp = (Double) request.getAttribute("temp");
    Integer humidity = (Integer) request.getAttribute("humidity");
    Double wind = (Double) request.getAttribute("wind");
    String error = (String) request.getAttribute("error");
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Weather Result</title>
    <link rel="stylesheet" href="style.css">
</head>
<body>
    <div class="card">
        <% if (error != null) { %>
            <h2 class="error"><%= error %></h2>
        <% } else { %>
            <div class="weather">
                <img src="images/rain.png" class="weather-icon" alt="Weather Icon">
                <h2 class="city"><%= city %></h2>
                <h1 class="temp"><%= temp %>°C</h1>
                <p><%= weather %></p>
                <div class="details">
                    <div class="col">
                        <img src="images/humidity.png" alt="Humidity Icon">
                        <div>
                            <p class="humidity"><%= humidity %>%</p>
                            <p>Humidity</p>
                        </div>
                    </div>
                    <div class="col">
                        <img src="images/wind.png" alt="Wind Icon">
                        <div>
                            <p class="wind"><%= wind %> m/s</p>
                            <p>Wind Speed</p>
                        </div>
                    </div>
                </div>
            </div>
        <% } %>

        <!-- Back button styled like a real button -->
        <a href="index.html">🔙 Search another</a>
    </div>
</body>
</html>
