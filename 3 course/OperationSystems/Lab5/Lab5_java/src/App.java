import java.time.Duration;
import java.time.LocalDateTime;

public class App {
    public static void main(String[] args) {
        int[][] a = new int[500][500];
        double res = 0;

        LocalDateTime startTime = LocalDateTime.now();
        for (int i = 0; i < 500; i++)
        {
            for (int j = 0; j < 500; j++)
            {
                res += a[j][i];
            }
        }
        LocalDateTime endTime = LocalDateTime.now();
        Duration diff = Duration.between(startTime, endTime);
        System.out.println(startTime);
        System.out.println(endTime);
        System.out.println(diff);
    }

}
