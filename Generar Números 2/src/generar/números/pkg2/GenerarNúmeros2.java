package generar.números.pkg2;

import java.io.*;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.Scanner;


public class GenerarNúmeros2 {
 
        static int n = 1000000;
        static int[] numeros = new int[n];
    
        
        
     // Generar números aleatorios
    private static void generar() {
        
        //int n = 1000000;
        //int[] numeros = new int[n];
            
            
        for (int i = 0; i < n; i++) {
        numeros[i] = ThreadLocalRandom.current().nextInt(-10000000, 10000001);
        }//cierra for
            
        
        
        // Guardar números en archivo de texto
        String archivo = "números.txt";
        
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(archivo))) {
            
            for (int num : numeros) {
                writer.write(num + "\n");
            }//cierra for
            
        System.out.println("\nARCHIVO CREADO!\n");
            
        }//cierra try
        
        catch (IOException e) {
            System.err.println("ERROR AL CREAR EL ARCHIVO: " + e.getMessage());
            return;
        }//cierra catch 
    }
    
    


    // Ordenar números con árbol binario y método de burbuja
    private static void ordenar() {
          
    
        long TiempoInicial = System.currentTimeMillis();
        BinaryTree tree = new BinaryTree();
        
        
        for (int num : numeros) {
            tree.insert(num);
        }//cierra for
        
        
        int[] numordenados = new int[n];
        int i = 0;
        
        
        for (int num : tree) {
            numordenados[i++] = num;
        }//Cierra for
        
        
        long TiempoFinal = System.currentTimeMillis();
        
         System.out.println("SE HAN ORDENADO LOS NÚMEROS Y SE HAN GUARDADO EN UN NUEVO ARCHIVO\n");

        
        // Guardar números ordenados en archivo de texto
        String archivo2 = "números ordenados.txt";
        
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(archivo2))) {
            
            for (int num : numordenados) {
                writer.write(num + "\n");
            }//cierra for
            
        }//Cierra try
        
        catch (IOException e) {
            System.err.println("Error al escribir en archivo: " + e.getMessage());
            return;
        }//cierra catch

        
        // Mostrar tiempo de ordenamiento
        System.out.println("Tiempo de ordenamiento: " + (TiempoFinal - TiempoInicial) + " ms");
    }
        
    
    
    static class BinaryTree implements Iterable<Integer> {
        private Node raiz;

        public void insert(int value) {
            raiz = insert(raiz, value);
        }
        
        

        private Node insert(Node node, int value) {
            
            if (node == null) {
                return new Node(value);
            }//cierra if
            
            if (value < node.value) {
                node.left = insert(node.left, value);
            }//cierra if
            
            else {
                node.right = insert(node.right, value);
            }//cierra else
            
            return node;
        }
        
        

        @Override
        public Iterator<Integer> iterator() {
            List<Integer> list = new ArrayList<>();
            traverseInOrder(raiz, list);
            return list.iterator();
        }

        
        private void traverseInOrder(Node node, List<Integer> list) {
          
            if (node != null) {
                traverseInOrder(node.left, list);
                list.add(node.value);
                traverseInOrder(node.right, list);
            }//cierra if
            
        }

        
        private static class Node {
            int value;
            Node left;
            Node right;

            Node(int value) {
                this.value = value;
            }
            
        }
    }
    
    
        
        
    public static void main(String[] args) {
        
        Scanner sc = new Scanner(System.in);
        int opcion = 0;
        
        while (opcion != 3) {
        System.out.println("\n\nSeleccione una opcion:");
        System.out.println("1. Generar Archivo y numeros aleatorios");
        System.out.println("2. Ordenar los numeros generados");
        System.out.println("3. Salir");
        
        opcion = sc.nextInt();
        
        
         switch(opcion) {
            case 1:
                System.out.println("\nSelecciono Generar Archivo y Numeros Aleatorios");
                generar();
                break;
                
            case 2:
                System.out.println("\nSelecciono Ordenar los numeros generados");
                ordenar();
                break;
                
            case 3:
                System.out.println("\nPrograma Finalizado");
                break;
                
            default:
                System.out.println("\nOPCION INVALIDA!!");
                break;
            }//Cierra switch
         
        }//cierra while
    }
}    