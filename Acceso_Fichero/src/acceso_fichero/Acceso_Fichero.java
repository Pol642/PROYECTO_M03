package acceso_fichero;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Acceso_Fichero {

    public static void main(String[] args) {

       File doc = new File("C:/Users/alumne/Desktop/ejemplo.txt");
       
       MenuCamareros(doc);
        
      
    }
    
    public static void MenuCamareros(File doc) {

       Scanner sn = new Scanner(System.in);
       boolean salir = false;
       int opcion; 
       do{
           System.out.println("1. LISTAR MESAS");
           System.out.println("2. AÑADIR MESA");
           System.out.println("3. EDITAR/MODIFICAR MESA");
           System.out.println("4. ELIMINAR MESA");
           System.out.println("5. SALIR");
           System.out.println("ESCOGE LA OPCIÓN QUE DESEES");
           opcion = sn.nextInt();
           
           sn.nextLine();
           
           String IdMesa;
           
           switch(opcion){
               case 1:
                   System.out.println("----------------------------------");
                   System.out.println("Has seleccionado la opcion 1");
                   System.out.println("----------------------------------");
                   System.out.println("Nº - DESCRIPCION - Nº M. - C. NEN - Nº C. - VENTILADOR - JARDIN");
                   LecturaFicheroTexto(doc);
                   System.out.println("----------------------------------");
                   break;
               case 2:
                   System.out.println("----------------------------------");
                   System.out.println("Has seleccionado la opcion 2");
                   System.out.println("----------------------------------");
                   EscribirFicheroTexto(doc);
                   break;
                case 3:
                   System.out.println("----------------------------------");
                   System.out.println("Has seleccionado la opcion 3");
                   System.out.println("----------------------------------");
                   System.out.println("INTRODUCE ID DE LA MESA A EDITAR");
                   System.out.println("----------------------------------");
                   IdMesa = sn.nextLine().toLowerCase();
                   ModificarFicheroTexto(doc, IdMesa);
                   break;
                case 4:
                   System.out.println("----------------------------------");
                   System.out.println("Has seleccionado la opcion 4");
                   System.out.println("----------------------------------");
                   System.out.println("INTRODUCE ID DE LA MESA A ELIMINAR");
                   System.out.println("----------------------------------");
                   IdMesa = sn.nextLine().toLowerCase();
                   EliminarFicheroTexto(doc, IdMesa);
                   break;
                case 5:
                   salir=true;
                   break;
                default:
                   System.out.println("Solo números entre 1 y 5");
           }
       }
      while(!salir);
    }
    
    public static void LecturaFicheroTexto(File doc) {

        try {
            Scanner lector = new Scanner(doc, "ISO-8859-1");
            
            while(lector.hasNext()) {
                String linea = lector.nextLine();
                linea = linea.replace(";"," - ");
                System.out.println(linea);
            }
            
            lector.close();
        } catch (FileNotFoundException e) {
            System.out.println("Ha ocurrido un error al abrir/leer el fichero");
        }
    }
    
    public static void EscribirFicheroTexto(File doc) {

        Scanner lector = new Scanner(System.in);
        
        try {
            FileWriter escritor = new FileWriter(doc, true);
            System.out.println("INTRODUCE EL ID DE LA MESA");
            String IdMesa = lector.nextLine();
            String mesa = IdMesa + ";" + PedirDatos();
            escritor.write("\n" + mesa);
            
            escritor.close();
      
            System.out.println("ENORABUENA MESA AÑADIDA CORRECTAMENTE.");
            
        } catch (IOException e) {
            System.out.println("Ha ocurrido un error al abrir/leer el fichero");
        }
        
    }
    
    public static void ModificarFicheroTexto(File doc, String IdMesa) {

        Scanner lector = new Scanner(System.in);
        
        ArrayList<String> mesas = new ArrayList<>(); 
        
        try {

            Scanner lectorFichero = new Scanner(doc);

            while (lectorFichero.hasNext()) {

                mesas.add(lectorFichero.nextLine());

            }


            lectorFichero.close();

        } catch (Exception e) {

            System.out.println("Ha ocurrido un error al abrir/leer el fichero");

        }
        
        System.out.println(mesas);
         
        try {
            FileWriter writer = new FileWriter(doc);

          for (String linea : mesas) {
           
            String[] parts = linea.split(";");
            
            
            if (parts[0].equals(IdMesa)) {
              
                System.out.println("Encontrada.. A Modificar");
                String FraseFormada = IdMesa + ";" + PedirDatos();
                writer.write(FraseFormada + "\n");
                
            } else {
            
                System.out.println("Encontrada.. No A Modificar");
                writer.write(linea + "\n");
                  
            }
            
            
        
        }
         
        writer.close();

        } catch (Exception e) {

            System.out.println("Ha ocurrido un error al abrir/sobreescribir el fichero");

        }
               
    }
      
    public static void EliminarFicheroTexto(File doc, String IdMesa) {
        
         Scanner lector = new Scanner(System.in);
        
        ArrayList<String> mesas = new ArrayList<>(); 
        
        try {

            Scanner lectorFichero = new Scanner(doc);

            while (lectorFichero.hasNext()) {

                mesas.add(lectorFichero.nextLine());

            }


            lectorFichero.close();

        } catch (Exception e) {

            System.out.println("Ha ocurrido un error al abrir/leer el fichero");

        }
        
        
         
        try {
            FileWriter writer = new FileWriter(doc);

          for (String linea : mesas) {
           
            String[] parts = linea.split(";");
            
            if (parts[0].equals(IdMesa)) {
                
            } else {
            
                writer.write(linea + "\n");
                  
            }
        
        }
            
        writer.close();
          
        } catch (Exception e) {

            System.out.println("Ha ocurrido un error al abrir/sobreescribir el fichero");

        }
   
    }

    private static String PedirDatos() {

        Scanner sc = new Scanner(System.in);
        
        String mesa;
        
        System.out.println("INTRODUCE LA DESCRIPCION DE LA MESA");
        String descripcion = sc.nextLine();
        
        System.out.println("INTRODUCE LA CAPACIDAD MAXIMA DE LA MESA");
        int cMaxima = sc.nextInt();
        
        sc.nextLine();
        
        System.out.println("INTRODUCE SI HAY SILLAS PARA NIÑO EN LA MESA");
        String SillasNiño = sc.nextLine();
        
        System.out.println("INTRODUCE EL NUMERO DE SILLAS DE ADULTO DE LA MESA");
        int nSillas = sc.nextInt();
        
        sc.nextLine();
        
        System.out.println("INTRODUCE SI HAY VENTILADORES EN LA MESA");
        String ventilador = sc.nextLine();
        
        System.out.println("INTRODUCE SI LA MESA ESTA EN EL JARDIN");
        String jardin = sc.nextLine();
        
        mesa = descripcion + ";" + cMaxima + ";" + SillasNiño + ";" + nSillas + ";" + ventilador + ";" + jardin + ";";
        
        return mesa;

    }
    
}




  

