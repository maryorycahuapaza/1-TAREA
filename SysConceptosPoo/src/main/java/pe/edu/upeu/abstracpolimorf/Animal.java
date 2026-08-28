package pe.edu.upeu.abstracpolimorf;

public abstract class Animal {
    public void sonidoAnimal(){
        System.out.println("El animal hace sonido");
    }
    static void pruebaSA(){
        System.out.println("Pprobando statico");
    }
    public abstract void dormir();

}
