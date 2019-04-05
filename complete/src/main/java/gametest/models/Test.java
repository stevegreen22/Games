package gametest.models;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by SteveGreen on 05/04/2019.
 */
@Entity
@Builder(toBuilder = true)
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@Setter(value = AccessLevel.PACKAGE)
@Getter
public class Test {

    private String name;

//    public Test(){}

    public Test(String name){
        this.name = name;
    }

    @Id
    @GeneratedValue
    private Long id;

    public Long getId(){
        return this.id;
    }
}
