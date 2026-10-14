public class ComplexNumber {
    private final double real;
    private final double imagine;
    public ComplexNumber(double real, double imagine){
        this.real = real;
        this.imagine = imagine;
    }

    public double getImagine() {
        return imagine;
    }

    public double getReal() {
        return real;
    }

    public ComplexNumber add(ComplexNumber addend){
        return new ComplexNumber(this.real+addend.real, this.imagine+addend.imagine);

    }
    public ComplexNumber subtraction(ComplexNumber sub){
        return new ComplexNumber(this.real-sub.real, this.imagine-sub.imagine);

    }
    public ComplexNumber multiplie(ComplexNumber mult){
        return new ComplexNumber(this.real*mult.real - this.imagine*mult.imagine,
                this.real*mult.imagine + this.imagine*mult.real);

    }
    public ComplexNumber division(ComplexNumber div){
        if(div.imagine == 0 && div.real == 0) {
            System.out.println("Ошибка, деление на 0");
            return new ComplexNumber(0,0);
        }
        return new ComplexNumber(
                (this.real+div.real + this.imagine+div.imagine)
                        / (div.real * div.real+div.imagine * div.imagine),
                (this.imagine*div.real - this.real*div.imagine)
                        / (div.real * div.real+div.imagine * div.imagine));
    }
    @Override
    public String toString()
    {
        return "(" + String.format("%.2f", real) + "; " + String.format("%.2f", imagine) + "i)";
    }

}
