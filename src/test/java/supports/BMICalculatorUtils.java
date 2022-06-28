package supports;

import java.text.DecimalFormat;

public interface BMICalculatorUtils {
    public default String bmiResult(double height, double weight){
        DecimalFormat format = new DecimalFormat("0.#");
        double bmi = weight/((height/100)*(height/100));
        return "BMI = " + format.format(bmi) + " kg/m2";
    }
}
