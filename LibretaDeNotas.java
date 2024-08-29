import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class LibretaDeNotas {
    static final HashMap<String, ArrayList<Float>> alumnos = new HashMap<>(); // Hashmap con los alumnos

    static final Scanner lector = new Scanner(System.in); // Lector general

    public static void main(String[] args) {
        iniciar();
    }

    /* Punto de partida del menu */
    static public void iniciar(){
        // Muestro el menú
        boolean salir = false; // mientras esto sea falso, el bucle se repite
        String opcion; // La opcion ingresada por el usuario

        while (!salir){
            System.out.println("Selecciona una opcion del menú;\n");

            System.out.println("1. Mostrar alumnos");
            System.out.println("2. Agregar alumno");
            System.out.println("3. Calcular promedio, notas maxima y minima");
            System.out.println("4. Mostrar promedio por estudiante"); // Opcion 1
            System.out.println("5. Mostrar resultado por estudiante (Aprueba o Reprueba)"); // Opcion 2
            System.out.println("6. Mostrar si la nota esta por sobre o por debajo del promedio"); // Opcion 3
            System.out.println("0. Salir"); // Si se ingresa un 0 salgo del menu y del bucle
            opcion = lector.nextLine();
            System.out.println("\n");

            switch (opcion.toLowerCase()) {
                case "1":
                    smMuestraAlumnos();
                    break;
                case "2":
                    smAgregaAlumnos();
                    break;
                case "3":
                    smCalcularVarios();
                    break;
                case "4":
                    smCalculaPromedioXEstudiante();
                    break;
                case "5":
                    smCalculaResultadoXEstudiante();
                    break;
                case "6":
                    smCalculaNivelPromedioXEstudiante();
                    break;
                case "0":
                    salir = true;
                    break;
                default:
                    System.out.println("Opcion no valida");
                    break;
            }
        }

        System.out.println("Saliendo...");
    }

    /**
     * Sub menu que muestra los alumnos ingresados
     */
    static private void smMuestraAlumnos(){
        if (alumnos.isEmpty()) { // Si no hay alumnos ingresados muestro mensaje
            System.out.println("No hay alumnos para mostrar");
        } else { // Imprimo los alumnos
            System.out.println("Alumnos:");

            alumnos.forEach((nombre, lista_notas) -> {
                System.out.println("Nombre: " + nombre);
                System.out.println("Notas: " + lista_notas + "\n");
            });

            System.out.println("\n");
        }
    }

    /**
     * Sub menu que agrega alumnos
     */
    static private void smAgregaAlumnos(){
        int cantidad_alumnos; // variable para definir la cantidad de alumnos a registrar
        int cantidad_notas; // Variable para definir la cantidad de notas por alumno

        System.out.println("Ingrese cantidad de alumnos a registrar");
        cantidad_alumnos = validaEnteros(lector.next());
        System.out.println("Ingrese cantidad de notas a registrar");
        cantidad_notas = validaEnteros(lector.next());

        // Segun la cantidad de alumnos ingresados, pregunto el nombre
        for (int i = 1; i <= cantidad_alumnos; i++) {
            String nombre = "sinnombre"+i; // El usuario podria no ingresar nada
            ArrayList<Float> notas = new ArrayList<>();

            System.out.println("ingrese nombre para el alumno numero " + i +" :");
            nombre = lector.next();

            // Segun la cantidad de notas por alumno, pregunto las notas
            for (int j = 1; j <= cantidad_notas; j++) {
                System.out.println("Ingrese nota " + j + ":");
                notas.add(validaNota(lector.next()));
            }

            alumnos.put(nombre, notas); // Agrego los alumnos al Hashmap, el nombre siendo la llave y las notas el valor

            System.out.println("Alumno " + nombre + " Ingresado correctamente");
        }

        System.out.println("\nAlumnos ingresados correctamente\n");
    }

    /**
     *  Calcula Promedio, Nota maxima y nota minima por alumno
     */
    static private void smCalcularVarios(){
        if (alumnos.isEmpty()) { // Si no hay alumnos ingresados muestro mensaje
            System.out.println("No hay alumnos para mostrar");
        } else {
            System.out.println("Alumnos:");
            alumnos.forEach((nombre, lista_notas) -> {
                System.out.println("Nombre: " + nombre);
                System.out.println("promedio: " + calculaPromedio(lista_notas));
                System.out.println("Nota maxima: " + calculaNotaMaxima(lista_notas));
                System.out.println("Nota minima: " + calculaNotaMinima(lista_notas) + "\n");
            });
        }
    }

    /**
     * Sub menu para calcular el promedio por estudiante
     */
    static private void smCalculaPromedioXEstudiante(){
        if (alumnos.isEmpty()) { // Si no hay alumnos ingresados muestro mensaje
            System.out.println("No hay alumnos para mostrar");
        } else {
            System.out.println("Alumnos:");
            alumnos.forEach((nombre, lista_notas) -> {
                System.out.println("Nombre: " + nombre);
                System.out.println("promedio: " + calculaPromedio(lista_notas));
                System.out.println("\n");
            });
            System.out.println("\n");
        }
    }

    /**
     * Sub menu para calcular el resultado final por estudiante (Aprueba o reprueba)
     */
    static private void smCalculaResultadoXEstudiante(){
        if (alumnos.isEmpty()) { // Si no hay alumnos ingresados muestro mensaje
            System.out.println("No hay alumnos para mostrar");
        } else {
            System.out.println("Alumnos:");
            alumnos.forEach((nombre, lista_notas) -> {
                System.out.println("Nombre: " + nombre);
                System.out.println("Resultado: " + ((calculaResultado(lista_notas)) ? "Aprobado" : "Reprobado"));
                System.out.println("\n");
            });
            System.out.println("\n");
        }
    }

    /**
     * Sub menu para calcular el nivel del alumno segun el promedio del curso
     */
    static public void smCalculaNivelPromedioXEstudiante(){
        if (alumnos.isEmpty()) { // Si no hay alumnos ingresados muestro mensaje
            System.out.println("No hay alumnos para mostrar");
        } else {
            float promedio_general = calculaPromedioCurso();

            System.out.println("Alumnos:");
            alumnos.forEach((nombre, lista_notas) -> { // Recorro el hashmap
                System.out.println("Nombre: " + nombre);
                System.out.println("Nivel: " + (calculaPromedio(lista_notas) < promedio_general ? "Bajo el promedio" : "Sobre el promedio"));
                System.out.println("\n");
            });
            System.out.println("\n");
        }
    }

    /**
     * Funcion que calcula el promedio de notas
     */
    static private float calculaPromedio(ArrayList<Float> notas){
        float promedio = 0;
        int suma = 0;

        for (Float nota : notas) { // Sumo todas las notas
            suma += nota;
        }

        promedio = (float) suma / notas.size(); // Luego divido la suma de las notas por la cantidad de estas

        return promedio;
    }

    /**
     * Funcion para calcular la nota maxima del alumno
     */
    static private float calculaNotaMaxima(ArrayList<Float> notas){
        float notaMaxima = 0;
        for (Float nota : notas) {
            if (nota > notaMaxima) {
                notaMaxima = nota;
            }
        }

        return notaMaxima;
    }

    /**
     * Funcion para calcular la nota minima del alumno
     */
    static private float calculaNotaMinima(ArrayList<Float> notas){
        float notaMinima = 100;
        for (Float nota : notas) {
            if (nota < notaMinima) {
                notaMinima = nota;
            }
        }

        return notaMinima;
    }

    /**
     * Funcion para calcular el resultado final del alumno (Aprueba o Reprueba)
     */
    static private boolean calculaResultado(ArrayList<Float> notas){
        boolean resultado = true;
        float promedio = calculaPromedio(notas);

        if (promedio <= 5) {
            resultado = false;
        }

        return resultado;
    }

    /**
     * Funcion para calcular el promedio general del curso
     */
    static private float calculaPromedioCurso(){
        float promedio_global = 0;

        for (ArrayList<Float> notas : alumnos.values()) {
            promedio_global += calculaNotaMaxima(notas);
        }

        return promedio_global / alumnos.size();
    }

    /**
     * Funcion que valida que la nota ingresada desde el escaner sea valida
     * float positivo entre 0 y 10
     */
    static private float validaNota(String nota){
        float nota_validada = 0;
        boolean ok = false;

        while (!ok) { // mientras que el valor no sea valido vuelvo a pedirlo
            try {
                nota_validada = Float.parseFloat(nota);
                if (nota_validada >= 0 && nota_validada <= 10) {
                    ok = true;
                } else {
                    throw new Exception("Nota Invalida");
                }
            } catch (Exception e) {
                System.out.println("Debes ingresar un valor numerico positivo entre 0 y 10");
                System.out.println("Ingrese la nota nuevamente");
                nota = lector.next();
            }
        }

        return nota_validada;
    }

    /**
     * Funcion que valida que el valor ingresada desde el escaner sea valido
     * Entero positivo
     */
    static private int validaEnteros(String numero){
        int numero_validado = 0;
        boolean ok = false;

        while (!ok){ // mientras que el valor no sea valido vuelvo a pedirlo
            try {
                numero_validado = Integer.parseInt(numero);
                if (numero_validado < 0 ){
                    throw new Exception("Numero Invalido");
                } else {
                    ok = true;
                }
            } catch (Exception e) {
                System.out.println("Debes ingresar un valor numerico positivo");
                System.out.println("Ingresa el numero nuavamente");
                numero = lector.next();
            }
        }

        return numero_validado;
    }
}


//Recorrer el HashMap y Evaluar Calificaciones:
//Utiliza un bucle (por ejemplo, for o foreach) para recorrer el HashMap de calificaciones.
//Calcular Promedio, Nota Máxima y Mínima por Estudiante:
//Después de evaluar las calificaciones, calcula y muestra para cada estudiante:
//Promedio de Notas: Suma todas las calificaciones y divide por la cantidad total de notas.
//Nota Máxima: Encuentra la calificación más alta en el ArrayList.
//Nota Mínima: Encuentra la calificación más baja en el ArrayList.
//Menú de Opciones:
//Muestra un menú con las siguientes opciones:
//1. Mostrar el Promedio de Notas por Estudiante.
//2. Mostrar si la Nota es Aprobatoria o Reprobatoria por Estudiante.
//3. Mostrar si la Nota está por Sobre o por Debajo del Promedio del Curso por Estudiante.
//0. Salir del Menú.
//Utiliza un bucle para permitir al usuario seleccionar opciones hasta que ingrese 0 para salir.
//Operaciones del Menú:
//Opción 1: Mostrar el Promedio de Notas por Estudiante.
//Muestra el promedio de notas por cada estudiante calculado previamente.
//Opción 2: Mostrar si la Nota es Aprobatoria o Reprobatoria por Estudiante.
//Solicita al usuario ingresar el nombre de un estudiante y una nota, luego verifica si es aprobatoria o reprobatoria según una nota de aprobación definida.
//Opción 3: Mostrar si la Nota está por Sobre o por Debajo del Promedio del Curso por Estudiante.
//Solicita al usuario ingresar el nombre de un estudiante y una nota, luego verifica si está por sobre o por debajo del promedio general.
//Validaciones:
//Implementa validaciones para asegurar que las notas ingresadas estén en un rango válido y que la cantidad de alumnos sea un número positivo.