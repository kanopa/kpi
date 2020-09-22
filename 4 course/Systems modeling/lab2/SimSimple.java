public class SimSimple {
    
    public static void main(String[] args) {

        System.out.println("Час моделювання | Обмеження довжини черги | Середня довжина черги | " + 
        "Середній час очікування | Ймовірність відмови | Кількість вимог | Кількість не обслугованих | Час створення | Час обробки");

        Model model = new Model(2,1,5);
        model.simulate(1000);

        model = new Model(4,4,6);
        model.simulate(1000);

        model = new Model(4,8,5);
        model.simulate(1000);

        model = new Model(10,5,10);
        model.simulate(1000);
    }
}