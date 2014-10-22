/**
 * 
 */
package api.readers;

import java.text.DecimalFormat;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import entity.WeatherInfo;

/**
 * Json парсер для OpenWeather источника данных
 * 
 * 
 * @author Balukova Elena
 * @created 19 окт. 2014 г.
 * 
 */

public class WorldWeatherJsonParser implements AbstractJsonParser<WeatherInfo> {

    /**
     * Разница между мил/с и м/с
     */
    private static final Double DIFF_SPEED = 0.44704;

    /**
     * {@inheritDoc}
     */
    @Override
    public WeatherInfo parse(String input, WeatherInfo weather) {

        try {
            JSONObject jdata = new JSONObject(input);
            JSONObject data = jdata.getJSONObject("data");
            JSONArray weathersCur = data.getJSONArray("current_condition");

            JSONObject weatherCur = weathersCur.getJSONObject(0);
            weather.setHumidity(weatherCur.getString("humidity"));
            weather.setPressure(weatherCur.getString("pressure"));
            weather.setCurT(weatherCur.getInt("temp_C"));
            // миль в час -> метры в сек
            weather.setWindSpeed(Double.parseDouble(new DecimalFormat("#0.0")
                    .format(weatherCur.getDouble("windspeedMiles")
                            * DIFF_SPEED)));
            weather.setWindDirection(weatherCur.getString("winddir16Point"));

            JSONArray weathers = data.getJSONArray("weather");
            JSONObject weatherDay = weathers.getJSONObject(0);
            weather.setMaxT(weatherDay.getInt("tempMaxC"));
            weather.setMinT(weatherDay.getInt("tempMinC"));
            weather.setPrecipitation(weatherDay.getString("precipMM"));

            JSONArray desc = weatherDay.getJSONArray("weatherDesc");
            weather.setDescription(desc.getJSONObject(0).getString("value"));
        } catch (JSONException e) {
            weather = null;
        }

        return weather;
    }
}
