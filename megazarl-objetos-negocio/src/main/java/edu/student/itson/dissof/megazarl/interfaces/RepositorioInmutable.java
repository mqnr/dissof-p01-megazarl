package edu.student.itson.dissof.megazarl.interfaces;

import java.util.stream.Stream;

/**
 * RepositorioInmutable.java
 *
 * <p>Repositorio para objetos inmutables.
 *
 * <p>En concreto, si se tiene la garantía de que los objetos contenidos son inmutables, tenemos libertad de ofrecer
 * un método {@code stream}.
 *
 * @author Yuri Germán García López (ID: 00000252583)
 * @author Luis Rafael Lagarda Encinas (ID: 00000252607)
 * @author Vladimir Iván Mendoza Baypoli (ID: 00000252758)
 * @author Manuel Romo López (ID: 00000253080)
 * @author Martín Zamorano Acuña (ID: 00000251923)
 */
public interface RepositorioInmutable<T> extends Repositorio<T> {
    /**
     * Proporciona un stream de los elementos inmutables contenidos en el repositorio.
     *
     * @return un stream de elementos de tipo T
     */
    Stream<T> stream();
}
