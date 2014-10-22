/**
 * 
 */
package api.readers;

import java.text.DecimalFormat;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

import utils.ObjectUtils;
import entity.WeatherInfo;

/**
 * XML парсер для OpenWeather источника данных
 * 
 * 
 * @author Balukova Elena
 * @created 19 окт. 2014 г.
 * 
 */

public class OpenWeatherXmlParser extends AbstractXmlParser<WeatherInfo> {

    /**
     * Разница между температурой по цельсию и температурой по кельвину
     */
    private static final Double T_KELVIN = 273.15;

    /**
     * Результат работы
     */
    private WeatherInfo weather = new WeatherInfo();

    /**
     * Маркер того, что найден узел wind
     */
    private boolean foundWind = false;

    /**
     * {@inheritDoc}
     */
    @Override
    public void endElement(String uri, String localName, String qName)
            throws SAXException {

        if ("wind".equals(qName)) {
            foundWind = false;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void startElement(String uri, String localName, String qName,
            Attributes attrs) throws SAXException {

        switch (qName) {
            case "temperature":
                weather.setMaxT(((Double) (Double.parseDouble(attrs
                        .getValue("max")) - T_KELVIN)).intValue());
                weather.setMinT(((Double) (Double.parseDouble(attrs
                        .getValue("min")) - T_KELVIN)).intValue());
                weather.setCurT(((Double) (Double.parseDouble(attrs
                        .getValue("value")) - T_KELVIN)).intValue());
                // Temperature, Kelvin (subtract 273.15 to convert to Celsius)
                break;
            case "humidity":
                weather.setHumidity(attrs.getValue("value"));
                break;
            case "pressure":
                weather.setPressure(attrs.getValue("value"));
                break;
            case "wind":
                foundWind = true;
                break;
            case "speed":
                if (foundWind) {
                    weather.setWindSpeed(Double.parseDouble(new DecimalFormat(
                            "#0.0").format(Double.parseDouble(attrs
                            .getValue("value")))));
                    // метры в сек
                }
                break;
            case "direction":
                if (foundWind) {
                    weather.setWindDirection(attrs.getValue("name"));
                }
                break;
            case "clouds":
                String desc = weather.getDescription();
                if (ObjectUtils.isNull(desc) || desc.isEmpty()) {
                    weather.setDescription(attrs.getValue("name"));
                }
                break;
            case "weather":
                String value = attrs.getValue("value");
                if (!value.isEmpty()) {
                    weather.setDescription(attrs.getValue("value"));
                }
                break;
            case "precipitation":
                weather.setPrecipitation(attrs.getValue("mode"));
                break;
            default:
                break;
        }
    }

    // /////////////////////////////////////////////////////////////////////////
    // /// getters/setters
    // /////////////////////////////////////////////////////////////////////////

    /**
     * @return the weather
     */
    @Override
    public WeatherInfo getResult() {

        return weather;
    }

    /**
     * @param the
     *            weather
     */
    @Override
    public void setObject(WeatherInfo object) {

        this.weather = object;
    }

}
