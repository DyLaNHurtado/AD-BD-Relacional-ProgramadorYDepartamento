import model.Departamento;
import model.Programador;
import repository.RepoDepartamento;
import repository.RepoProgramador;

import java.util.List;
import java.util.Objects;

public class Main {
    public static void main(String[] args){

        RepoDepartamento repoDepartamento = new RepoDepartamento();
        RepoProgramador repoProgramador = new RepoProgramador();
        RepoProgramador.checkServer();

        RepoProgramador.initData();

        // Operaciones CRUD

        //Programador
        System.out.println("*********************************\n" +
                " PROGRAMADOR\n" +
                "*********************************");

        //SELECT * FROM programador
        Objects.requireNonNull(repoProgramador.getAll().orElse(null)).forEach(System.out::println);
        System.out.println();

        //SELECT * FROM programador WHERE id = 2
        System.out.println(repoProgramador.selectById(2).orElse(null));
        System.out.println();

        //INSERT programador
        Programador programador = new Programador(
                4,"Pepito",7,1000, List.of("Java","Python","C"),"Departamento de Desarrollo");

        System.out.println(repoProgramador.insert(programador).orElse(null));
        System.out.println();
        Objects.requireNonNull(repoProgramador.getAll().orElse(null)).forEach(System.out::println);
        System.out.println();

        //UPDATE programador
        programador = new Programador(
                4,"Juanito",9,1100, List.of("Java","C++","C"),"Departamento de Desarrollo");

        System.out.println(repoProgramador.update(programador).orElse(null));
        System.out.println();

        //DELETE programador
        System.out.println(repoProgramador.delete(programador).orElse(null));
        System.out.println();

        //Departamento

        System.out.println("*********************************\n" +
                " DEPARTAMENTO\n" +
                "*********************************");
        //SELECT * FROM departamento
        Objects.requireNonNull(repoDepartamento.getAll().orElse(null)).forEach(System.out::println);
        System.out.println();

        //SELECT * FROM departamento WHERE id = 2
        System.out.println(repoDepartamento.selectById(2).orElse(null));
        System.out.println();

        //INSERT departamento
        Departamento departamento = new Departamento(
                4,"Departamento de Primeros Auxilios",30000,"Adrian", String.join(";",List.of("MariJuana","BenitoCamela","Loli"))
        );

        System.out.println(repoDepartamento.insert(departamento).orElse(null));
        System.out.println();
        Objects.requireNonNull(repoDepartamento.getAll().orElse(null)).forEach(System.out::println);
        System.out.println();

        //UPDATE departamento
        departamento = new Departamento(
                4,"Departamento de VideoJuegos",30000,"Eneko", String.join(";",List.of("Federico","Jose Luis","Juanito"))
        );

        System.out.println(repoDepartamento.update(departamento).orElse(null));
        System.out.println();

        //DELETE departamento
        System.out.println(repoDepartamento.delete(departamento).orElse(null));
        System.out.println();

    }
}
