package sv.com.bandesal.pruebatecnica.dto;

import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import sv.com.bandesal.pruebatecnica.model.Blog;
import sv.com.bandesal.pruebatecnica.model.Reader;
import java.security.SecureRandom;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BlogReaderDto {

    @Transient
    private int id = Integer.parseInt(numeroAleatorio());
    private Blog blog;
    private Reader reader;

    public String numeroAleatorio() {
        char [] chars = "0123456789".toCharArray();
        var charsLength = chars.length;
        SecureRandom random = new SecureRandom();

        //Generamos el numero de 8 posiciones usando el arreglo chars
        return IntStream.range(0, 8)
                .mapToObj(i -> String.valueOf(chars[random.nextInt(charsLength)]))
                .collect(Collectors.joining());
    }
}
