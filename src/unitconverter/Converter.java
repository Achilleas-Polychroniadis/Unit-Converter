package unitconverter;

import java.util.HashMap;
import java.util.function.Function;

public class Converter {
    static final HashMap<String, Double> lengthConversions = new HashMap<>();
    static final HashMap<String, Function<Double, Double>> temperatureConversions = new HashMap<>();
    static final HashMap<String, Double> weightConversions = new HashMap<>();
    
    static {
        //Length conversions
        lengthConversions.put("CentimetersToMeters", 0.01);
        lengthConversions.put("MetersToCentimeters", 100.0);

        lengthConversions.put("CentimetersToKilometers", 0.00001);
        lengthConversions.put("KilometersToCentimeters", 100000.0);

        lengthConversions.put("CentimetersToInches", 0.393701);
        lengthConversions.put("InchesToCentimeters", 2.54);

        lengthConversions.put("CentimetersToFeet", 0.0328084);
        lengthConversions.put("FeetToCentimeters", 30.48);

        lengthConversions.put("CentimetersToMiles", 0.00000621371);
        lengthConversions.put("MilesToCentimeters", 160934.0);

        lengthConversions.put("MetersToKilometers", 0.001);
        lengthConversions.put("KilometersToMeters", 1000.0);

        lengthConversions.put("MetersToInches", 39.3701);
        lengthConversions.put("InchesToMeters", 0.0254);

        lengthConversions.put("MetersToFeet", 3.28084);
        lengthConversions.put("FeetToMeters", 0.3048);

        lengthConversions.put("MetersToMiles", 0.000621371);
        lengthConversions.put("MilesToMeters", 1609.34);

        lengthConversions.put("KilometersToInches", 39370.1);
        lengthConversions.put("InchesToKilometers", 0.0000254);

        lengthConversions.put("KilometersToFeet", 3280.84);
        lengthConversions.put("FeetToKilometers", 0.0003048);

        lengthConversions.put("KilometersToMiles", 0.621371);
        lengthConversions.put("MilesToKilometers", 1.60934);

        lengthConversions.put("InchesToFeet", 0.0833333);
        lengthConversions.put("FeetToInches", 12.0);

        lengthConversions.put("InchesToMiles", 0.0000157828);
        lengthConversions.put("MilesToInches", 63360.0);

        lengthConversions.put("FeetToMiles", 0.000189394);
        lengthConversions.put("MilesToFeet", 5280.0);
        
        //Temperature conversions
        temperatureConversions.put("CelsiusToFahrenheit", c -> ( (c * 9 / 5) + 32) );
        temperatureConversions.put("FahrenheitToCelsius", f -> ( (f - 32) * 5 / 9) );

        temperatureConversions.put("CelsiusToKelvin", c -> ( c + 273.15) );
        temperatureConversions.put("KelvinToCelsius", k -> ( k - 273.15) );

        temperatureConversions.put("FahrenheitToKelvin", f -> ( (f - 32) * 5 / 9 + 273.15) );
        temperatureConversions.put("KelvinToFahrenheit", k -> ( (k - 273.15) * 9 / 5 + 32) );
        
        //Weight conversions
        weightConversions.put("GramsToKilograms", 0.001);
        weightConversions.put("KilogramsToGrams", 1000.0);

        weightConversions.put("GramsToPounds", 0.00220462);
        weightConversions.put("PoundsToGrams", 453.592);

        weightConversions.put("KilogramsToPounds", 2.20462);
        weightConversions.put("PoundsToKilograms", 0.453592);
    }
    
    public static double convert(String type, String conversion1, String conversion2, double value) {
        double v = 0.0;
        if(conversion1.equals(conversion2)) v = value;
        else {
            switch(type) {
                case "Length":
                    v = value * lengthConversions.get((conversion1 + "To" + conversion2));
                    break;
                case "Temperature":
                    v = temperatureConversions.get((conversion1 + "To" + conversion2)).apply(value);
                    break;
                case "Weight":
                    v = value * weightConversions.get((conversion1 + "To" + conversion2));
                    break;
            }
        }
        return v;
    }
}
