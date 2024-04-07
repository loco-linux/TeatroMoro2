
package teatromoro2;
import java.util.Scanner;


public class TeatroMoro2 {

    // Variables estáticas
    // defino colores para optimizar visualizacion de texto del programa   
    public static String red=     "\033[31m"; 
    public static String green=   "\033[32m"; 
    public static String blue=    "\033[34m"; 
    public static String cyan=    "\033[36m"; 
    public static String reset=   "\u001B[0m";   
    
    public static double totalIngresos = 0;
    public static boolean unAdulto = false;
    
    public static void main(String[] args) {
        
        bienvenida(); // metodo que imprime una bienvenida en pantalla
        
        // Variables de input de usuario desde teclado
        Scanner teclado = new Scanner(System.in);
        
        // Definicion de variables locales
     boolean encontrado = false; 
     int opcion;
             
     while(encontrado==false){
        System.out.println("Presiona 1 si quieres "+green+"[Comprar Entrada]"+reset);
        System.out.println("Presiona 2 si quieres "+green+"[Promociones]"+reset);
        System.out.println("Presiona 3 si quieres "+green+"[Total Entradas]"+reset);
        System.out.println("Presiona 4 si quieres "+green+"[Salir]"+reset);
        
        opcion = teclado.nextInt();
        
        switch(opcion){
            case 1 -> fxComprarEntrada();
            case 2 -> promociones();
            case 3 -> Entrada.entradasVendidas();
            case 4 -> encontrado = true; // para salir del bucle while
            default -> System.out.println(red+"Ingrese una opcion valida!"+reset); // control de errores
        }
     }
     
        System.out.println("");
        System.out.println(green+"[RESUMEN]"+reset);
        System.out.println("Total de entradas vendidas: " + Entrada.cantidadEntradasVendidas);
        if(Entrada.cantidadEntradasVendidas>=5){
            totalIngresos = totalIngresos - (totalIngresos * 0.1);
        }
        Entrada.entradasVendidas();
        System.out.println("Total a pagar: $" + totalIngresos);
        
    }


    public static void bienvenida(){
        
    // Despliegue menu principal
        System.out.println(red+"*******************************");
        System.out.println(red+"********* TEATRO MORO *********");              
        System.out.println(red+"*******************************"); 
        System.out.println(cyan+"------ TICKETERA VIRTUAL ------"+reset); 
        
               
    }
        
    
    public static double fxComprarEntrada(){
        // Variables de input de usuario desde teclado
     Scanner teclado2 = new Scanner(System.in);
     
     // Variables locales
     String opcion="";
     int edad=0;
     boolean encontrado2 = false;
     
        // Abstraccion de representacion de datos
     // Declaro una matriz unidimensional para las zonas de entradas
     String[] tipoEntrada = {
                            "vip", 
                            "platea", 
                            "general"
                            };
     
     do{
        System.out.print("Ingresa la ubicacion que deseas ( ");
        for(int i=0; i<3;i++){ // Imprime las diferentes ubicaciones
            System.out.print(tipoEntrada[i]+" ");
        }
        System.out.print("): ");
        
        opcion = teclado2.nextLine().toLowerCase();
        
         switch (opcion) {
             case "vip" -> {
                 encontrado2 = true; 
                 Entrada.entradaVip++;
             }
             case "platea" -> {   
                 encontrado2 = true;
                 Entrada.entradaPlatea++;
             }
             case "general" -> {  
                 encontrado2 = true;
                 Entrada.entradaGeneral++;
             }
             default -> System.out.println(red+"Entrada no valida. Reintente..."+reset);
         }
        
     }while(encontrado2 == false);
        
        do{
        System.out.print("Ingresa edad: ");
        edad = teclado2.nextInt();
        if(edad >= 18) {
            unAdulto = true;
        }
        }while(edad<0);
        
        double precioEntrada = calcularPrecioEntrada(opcion,edad);
        System.out.println(blue+"Valor a pagar: $"+precioEntrada+reset);
        Entrada.cantidadEntradasVendidas++;
        totalIngresos += precioEntrada;
        
        return precioEntrada;
    }
    
        // Metodo para calcular precio de entrada con descuentos si requiere
    private static double calcularPrecioEntrada(String ubicacion, int edad){
        // Variables locales
        double precioBase = 0;
        double descuento = 0;
        
        switch(ubicacion){
            case "vip" -> precioBase = 35000.0;
            case "platea" -> precioBase = 30000.0;
            case "general" -> precioBase = 25000.0;
            }
               
        // Aplica descuentos segun edad
        if(edad >= 6 && edad < 18){
            descuento = precioBase * 0.1;
            } else if (edad >= 65) {
                descuento = precioBase * 0.15;
            }
        
        if(edad<6 && unAdulto == true){
            descuento = precioBase;
        }
               
        return (precioBase - descuento);
    }

    

            
    public static void promociones(){
        // metodo que informa sobre las promociones
        System.out.println(red+"-Por la compra de 5 entradas o mas, se le aplicara un 10% de descuento del total de su boleta"+reset);
        System.out.println(red+"-Menores de 6 anios acompaniados por un adulto, entran gratis"+reset);
    }
    
}



class Entrada {
    
    // Variables de instancia (global)
    static int entradaVip = 0;
    static int entradaPlatea = 0;
    static int entradaGeneral = 0;
    static int cantidadEntradasVendidas = entradaVip + entradaPlatea + entradaGeneral;
    
     
    public static void entradasVendidas(){
        boolean hayEntrada = false;
        
        if(entradaVip != 0){
            System.out.println(TeatroMoro2.red+"[VIP]: " + entradaVip+TeatroMoro2.reset);
            hayEntrada = true;
        } 
        
        if (entradaPlatea != 0){
            System.out.println(TeatroMoro2.red+"[Platea]: " + entradaPlatea+TeatroMoro2.reset);
            hayEntrada = true;
        } 
        
        if (entradaGeneral != 0){
            System.out.println(TeatroMoro2.red+"[General]: " + entradaGeneral+TeatroMoro2.reset);
            hayEntrada = true;
        }
        
        if(hayEntrada == false){
            System.out.println(TeatroMoro2.red+"Aun no compra entradas!"+TeatroMoro2.reset);
        }
        
        if(cantidadEntradasVendidas>1){
            System.out.println(TeatroMoro2.blue+"[TOTAL]: " + cantidadEntradasVendidas +TeatroMoro2.reset);
        }
        
        if(cantidadEntradasVendidas>=5){
            System.out.println(TeatroMoro2.cyan+"**Califica para promocion**" +TeatroMoro2.reset);
        }
        
    }
    
}