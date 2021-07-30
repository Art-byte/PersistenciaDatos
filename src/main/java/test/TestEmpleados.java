package test;

import model.Empleado;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import java.util.GregorianCalendar;
import java.util.List;

public class TestEmpleados {

    /*
    * Esto se utiliza cuando tenemos un desarrollo en Java EE
    * para poder acceder directamente a un gestor de persistencia
    *
    * Al estar en una version community utilizare otra forma de desarrollo
    * */
    //@PersistenceContext(unitName = "Persistencia")
    private static EntityManager manager;
    private static EntityManagerFactory emf;

    @SuppressWarnings("unchecked")
    public static void main(String[] args) {

        //Creamos el gestior de persistencias
        emf = Persistence.createEntityManagerFactory("Persistencia");
        manager = emf.createEntityManager();

        /**
        List<Empleado> empleados = (List<Empleado>)manager.createQuery("from Empleado").getResultList();
        System.out.println("En la base de datos hay: " + empleados.size() + " empleados");*/

        insertInicial();

        manager.getTransaction().begin();
        Empleado e = manager.find(Empleado.class, 10L);
        e.setNombre("Karla");
        e.setApellido("Martinez");
        manager.getTransaction().commit();

        imprimirTodo();

    }



    public static void insertInicial(){
        Empleado e = new Empleado(10L, "Arturo", "Pedraza",
                new GregorianCalendar(1995, 4, 17).getTime());
        manager.getTransaction().begin();
        manager.persist(e);
        manager.getTransaction().commit();
    }

    @SuppressWarnings("unchecked")
    private static void imprimirTodo(){
        List<Empleado> empleados = (List<Empleado>)manager.createQuery("from Empleado").getResultList();
        System.out.println("En la base de datos hay: " + empleados.size() + " empleados");
        for(Empleado emp : empleados){
            System.out.println(emp.toString());
        }
    }


}
