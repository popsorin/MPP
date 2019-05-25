import Tema.Dommain.Artisti;
import org.junit.Test;

import java.sql.Date;

import static org.junit.Assert.*;
public class Teste {
    @Test
    public void main(){

        Artisti a=new Artisti("nume",1);

        assertEquals(a.getNume(),"nume");
        assertEquals(a.getId(),1);

        System.out.println("PASSED!!!!!!!!");
    }
}
