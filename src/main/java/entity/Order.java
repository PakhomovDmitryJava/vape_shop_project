package entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Order {
    private Long id;
    private User user; //1
    private LocalDateTime orderDate; //2
    private boolean isPaid; //3
    private LocalDateTime paymentDate; //4
}
