import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.net.*;
import org.json.*;

@WebServlet("/WeatherServlet")
public class WeatherServlet extends HttpServlet {
    private final String API_KEY = "f2daec18d6a8d2ddbadce3903d62fc12"; // 🔁 Replace with your key

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String cityName = request.getParameter("city");
        String city = URLEncoder.encode(cityName, "UTF-8");
        String apiURL = "https://api.openweathermap.org/data/2.5/weather?q=" + city + "&appid=" + API_KEY + "&units=metric";

        URL url = new URL(apiURL);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");

        try {
            
            StringBuffer content;
            try (BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
                String inputLine;
                content = new StringBuffer();
                while ((inputLine = in.readLine()) != null) {
                    content.append(inputLine);
                }
            }
            conn.disconnect();

            // Parse JSON response
            JSONObject json = new JSONObject(content.toString());

            String weather = json.getJSONArray("weather").getJSONObject(0).getString("description");
            double temp = json.getJSONObject("main").getDouble("temp");
            int humidity = json.getJSONObject("main").getInt("humidity");
            double wind = json.getJSONObject("wind").getDouble("speed");

            // Set data in request scope
            request.setAttribute("city", cityName);
            request.setAttribute("weather", weather);
            request.setAttribute("temp", temp);
            request.setAttribute("humidity", humidity);
            request.setAttribute("wind", wind);
        }catch(IOException | JSONException e) {
            request.setAttribute("error", " Something went wrong. Please try again.");
        }

        // Forward to JSP
        RequestDispatcher rd = request.getRequestDispatcher("result.jsp");
        rd.forward(request, response);
    }
}
