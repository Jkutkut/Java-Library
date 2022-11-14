package jkutkut;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.channels.ReadableByteChannel;
import java.nio.charset.Charset;
import java.nio.file.Path;
import java.util.Scanner;


/**
 * Clase que "extiende" a la clase Scanner.
 * <br>
 * Añade varios métodos para mejorar la interacción con posibles usuarios.
 * <br>
 * Puesto que la implementación de la clase Scanner es <i>final</i>, no es posible
 * una implementación directa.
 * @author "Jkutkut -- Jorge Re"
 *
 */
public class SuperScanner {
    private Scanner sc;

    // Constructores de la clase (los mismos que Scanner de java.util.Scanner)

    public SuperScanner(File source) throws FileNotFoundException {
        sc = new Scanner(source);
    }

    public SuperScanner(InputStream in) {
        sc = new Scanner(in);
    }

    public SuperScanner(Path source) throws IOException {
        sc = new Scanner(source);
    }

    public SuperScanner(Readable source) {
        sc = new Scanner(source);
    }

    public SuperScanner(ReadableByteChannel source) {
        sc = new Scanner(source);
    }

    public SuperScanner(String str) {
        sc = new Scanner(str);
    }

    public SuperScanner(File source, Charset charset) throws IOException {
        sc = new Scanner(source, charset);
    }

    public SuperScanner(File source, String charsetName) throws IOException {
        sc = new Scanner(source, charsetName);
    }

    public SuperScanner(ReadableByteChannel source, Charset charset) {
        sc = new Scanner(source, charset);
    }

    public SuperScanner(ReadableByteChannel source, String charsetName) {
        sc = new Scanner(source, charsetName);
    }

    /**
     * Cierra el scanner.
     */
    public void close() {
        sc.close();
    }

    // GETTERS
    /**
     * @param question - Question to show using System.out
     * @param minLen - min length of String
     * @param maxLen - max length of String
     * @return Response given by the scanner meeting the criteria.
     */
    public String getString(String question, int minLen, int maxLen) {
        String str;
        while (true) {
            System.out.print(question);
            str = sc.nextLine();

            if (str.length() < minLen) {
                System.out.println("La longitud mínima es de " + minLen + " caracteres\n");
            }
            else if (str.length() > maxLen) {
                System.out.println("La longitud máxima es de " + maxLen + " caracteres.\n");
            }
            else {
                return str;
            }
        }
    }

    public String getString(String question) {
        System.out.print(question);
        return sc.nextLine();
    }

    /**
     * @param question - Question to show using System.out.print
     * @return Integer given by Scanner
     */
    public int getInt(String question) {
        while (true) {
            try {
                System.out.print(question);
                return Integer.parseInt(sc.nextLine());
            }
            catch (NumberFormatException e) {
                System.out.println("El valor no es un número entero válido.\n");
            }
        }
    }

    /**
     * @param question - Question to show using System.out
     * @return Integer greater or equal to 0
     */
    public int getNatural(String question) {
        int n = 0;
        boolean isNotNatural = true;
        while (isNotNatural) {
            n = getInt(question);

            if (n >= 0) {
                isNotNatural = false;
            }
            else {
                System.out.println("El número tiene que ser un natural -> [0, inf)\n");
            }
        }
        return n;
    }

    /**
     * @param question - Question to show using System.out
     * @param min - min value of the desired int
     * @param max - max value of the desired int
     * @return Integer inside the interval [min, max]
     */
    public int getIntInRange(String question, int min, int max) {
        if (min > max) {
            int swap = min;
            min = max;
            max = swap;
        }

        int n = 0;
        boolean isNotValid = true;
        while (isNotValid) {
            n = getInt(question);

            if (n >= min && n <= max) {
                isNotValid = false;
            }
            else {
                System.out.printf(
                        "El número tiene que ser un natural en el rango [%d, %d]\n\n",
                        min, max
                );
            }
        }
        return n;
    }

    /**
     * @param question - Question to show using System.out.print
     * @return Float given by Scanner
     */
    public float getFloat(String question) {
        while (true) {
            try {
                System.out.print(question);
                return Float.parseFloat(sc.nextLine());
            }
            catch (NumberFormatException e) {
                System.out.println("El valor no es un número float válido.\n");
            }
        }
    }

    /**
     * @param question - Question to show using System.out
     * @param min - min value of the desired int
     * @param max - max value of the desired int
     * @return Float inside the interval [min, max]
     */
    public float getFloatInRange(String question, float min, float max) {
        if (min > max) {
            float swap = min;
            min = max;
            max = swap;
        }

        float n = 0;
        boolean isNotValid = true;
        while (isNotValid) {
            n = getFloat(question);

            if (n >= min && n <= max) {
                isNotValid = false;
            }
            else {
                System.out.printf(
                        "El número tiene que ser un float en el rango [%f, %f]\n\n",
                        min, max
                );
            }
        }
        return n;
    }
}

