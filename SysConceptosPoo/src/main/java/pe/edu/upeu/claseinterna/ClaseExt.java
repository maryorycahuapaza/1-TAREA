package pe.edu.upeu.claseinterna;

public class ClaseExt {
    class ClaIntUno{
        static void saludo(){
            System.out.println("Soy clase interna Uno");
        }
    }
    class ClaIntDos{
        static void saludo(){
            System.out.println("Soy clase interna Dos");
        }
        static void mostrarInformación(){
            System.out.println("los metodos staticos se puden llamar de forma directa");
        }
    }

    public static void main(String[] args) {
        ClaseExt ce=new ClaseExt();
        ClaIntUno cIU=ce.new ClaIntUno();
        cIU.saludo();
        ClaIntDos cID=ce.new ClaIntDos();
        cID.saludo();
        ClaIntDos.mostrarInformación();
    }
}