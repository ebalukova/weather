package api;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

import org.apache.commons.io.IOUtils;
import org.junit.Before;
import org.junit.Test;

import entity.WeatherInfo;

/**
 * 
 * ReadersTest class
 * 
 * Тестируем правильность работы ридеров
 * 
 * 
 * @author Balukova Elena
 * @created 19 окт. 2014 г.
 * 
 */
public class ReadersTest {

    /**
     * ClassLoader
     */
    private ClassLoader classLoader;

    /**
     * Берем ClassLoader
     */
    @Before
    public void setUp() {

        classLoader = getClass().getClassLoader();
    }

    /**
     * Проверяем правильно ли распознался формат OpenWeather
     */
    @Test
    public void parseOpenWeatherTest() {

        try {
            String input =
                    IOUtils.toString(classLoader
                            .getResourceAsStream("openWeather.xml"));

            ApiXML api = new ApiXML("some url");

            WeatherInfo weather = new WeatherInfo();

            api.parse("api.readers.OpenWeatherXmlParser", input, weather);

            assertEquals("85", weather.getHumidity());
            assertEquals("1002", weather.getPressure());
            assertEquals(new Double(4.0), weather.getWindSpeed());
            assertEquals("Northwest", weather.getWindDirection());
            assertEquals("no", weather.getPrecipitation());
            assertEquals("light shower snow", weather.getDescription());
            assertEquals(new Integer(-7), weather.getCurT());
            assertEquals(new Integer(-7), weather.getMaxT());
            assertEquals(new Integer(-7), weather.getMinT());

        } catch (Exception e) {
            fail("Something wrong with test resources");
        }
    }

    /**
     * Проверяем правильно ли распознался формат WorldWeather
     */
    @Test
    public void parseWorldWeatherTest() {

        try {
            String input =
                    IOUtils.toString(classLoader
                            .getResourceAsStream("worldWeather.json"));

            ApiJSON api = new ApiJSON("some url");

            WeatherInfo weather = new WeatherInfo();
            weather.setHumidity("xxx");
            weather.setMaxT(new Integer(100));

            api.parse("api.readers.WorldWeatherJsonParser", input, weather);

            assertEquals("86", weather.getHumidity());
            assertEquals("1002", weather.getPressure());
            assertEquals(new Double(4.0), weather.getWindSpeed());
            assertEquals("NW", weather.getWindDirection());
            assertEquals("2.2", weather.getPrecipitation());
            assertEquals("Light snow", weather.getDescription());
            assertEquals(new Integer(-7), weather.getCurT());
            assertEquals(new Integer(-5), weather.getMaxT());
            assertEquals(new Integer(-10), weather.getMinT());

        } catch (Exception e) {
            fail("Something wrong with test resources");
        }
    }

    /**
     * Проверяем обработку ошибки
     */
    @Test
    public void exceptionTest() {

        try {
            ApiXML apiXml = new ApiXML("some url");
            ApiJSON apiJson = new ApiJSON("some url");

            WeatherInfo weather = new WeatherInfo();
            weather.setHumidity("xxx");

            // неверный формат входных данных для xml
            weather =
                    apiXml.parse("api.readers.OpenWeatherXmlParser", "zzzzz",
                            weather);
            assertNull(weather);

            weather = new WeatherInfo();
            weather.setHumidity("xxx");
            // неверный формат входных данных для json
            weather =
                    apiJson.parse("api.readers.WorldWeatherJsonParser",
                            "zzzzz", weather);
            assertNull(weather);

            weather = new WeatherInfo();
            weather.setHumidity("xxx");
            String input =
                    IOUtils.toString(classLoader
                            .getResourceAsStream("openWeather.xml"));
            // такого ридера не существует
            weather =
                    apiXml.parse("api.readers.WorldWeatherJsonParser", input,
                            weather);
            assertNull(weather);
        } catch (Exception e) {
            fail("Something wrong with test resources");
        }
    }

}
